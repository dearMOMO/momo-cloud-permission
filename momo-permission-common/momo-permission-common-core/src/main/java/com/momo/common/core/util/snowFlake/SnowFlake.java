package com.momo.common.core.util.snowFlake;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @program: momo-cloud-permission
 * @description: 基于Twitter的分布式自增ID算法Snowflake实现分布式有序
 * @author: Jie Li
 * @create: 2019-07-30 15:10
 **/
@Slf4j
public class SnowFlake {

    /**
     * 起始时间戳，用于用当前时间戳减去这个时间戳，算出偏移量
     * 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动）
     **/
    private final long startTime = 1519740777809L;

    /**
     * workerId占用的位数5（表示只允许workId的范围为：0-1023）
     **/
    private final long workerIdBits = 5L;
    /**
     * dataCenterId占用的位数：5
     **/
    private final long dataCenterIdBits = 5L;
    /**
     * 序列号占用的位数：12（表示只允许workId的范围为：0-4095）
     **/
    private final long sequenceBits = 12L;

    /**
     * workerId可以使用的最大数值：31
     **/
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /**
     * dataCenterId可以使用的最大数值：31
     **/
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    private final long workerIdShift = sequenceBits;
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /**
     * 用mask防止溢出:位与运算保证计算的结果范围始终是 0-4095
     **/
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long workerId;
    /**
     * 数据标识 ID 部分
     */
    private long dataCenterId;
    /**
     * 并发控制
     */
    private long sequence = 0L;
    /**
     * 上次生产 ID 时间戳
     */
    private long lastTimestamp = -1L;
    /**
     * 时间回拨最长时间(ms)，超过这个时间就抛出异常
     */
    private long timestampOffset = 5L;
    private boolean isClock = false;

    /**
     * 基于Snowflake创建分布式ID生成器
     * <p>
     * 注：sequence
     *
     * @param workerId     工作机器ID,数据范围为0~31
     * @param dataCenterId 数据中心ID,数据范围为0~31
     */
    public SnowFlake(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    public void setClock(boolean clock) {
        isClock = clock;
    }

    /**
     * <p>
     * 获取 maxWorkerId
     * </p>
     */
    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuilder mpid = new StringBuilder();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (StringUtils.isNotEmpty(name)) {
            /*
             * GET jvmPid
             */
            mpid.append(name.split("@")[0]);
        }
        /*
         * MAC + PID 的 hashcode 获取16个低位
         */
        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }

    /**
     * <p>
     * 数据标识id部分
     * </p>
     */
    protected static long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                if (null != mac) {
                    id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                    id = id % (maxDatacenterId + 1);
                }
            }
        } catch (Exception e) {
            log.warn(" getDatacenterId: " + e.getMessage());
        }
        return id;
    }

    /**
     * 获取ID
     *
     * @return
     */
    public synchronized Long nextId() {
        long timestamp = this.timeGen();

        // 闰秒：如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            long offset = lastTimestamp - timestamp;
            if (offset <= timestampOffset) {
                try {
                    this.wait(offset << 1);
                    timestamp = this.timeGen();
                    if (timestamp < lastTimestamp) {
                        throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
            }
        }

        // 解决跨毫秒生成ID序列号始终为偶数的缺陷:如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            // 通过位与运算保证计算的结果范围始终是 0-4095
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = this.tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        /*
         * 1.左移运算是为了将数值移动到对应的段(41、5、5，12那段因为本来就在最右，因此不用左移)
         * 2.然后对每个左移后的值(la、lb、lc、sequence)做位或运算，是为了把各个短的数据合并起来，合并成一个二进制数
         * 3.最后转换成10进制，就是最终生成的id
         */
        return ((timestamp - startTime) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    /**
     * 保证返回的毫秒数在参数之后(阻塞到下一个毫秒，直到获得新的时间戳)
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }

        return timestamp;
    }

    /**
     * 获得系统当前毫秒数
     *
     * @return timestamp
     */
    private long timeGen() {
        if (isClock) {
            // 解决高并发下获取时间戳的性能问题
            return SystemClock.now();
        } else {
            return System.currentTimeMillis();
        }
    }

    public static void main(String[] args) {
        SnowFlake snowFlake = new SnowFlake(1, 1);
        for (int i = 0; i < (1 << 12); i++) {
            Long id = snowFlake.nextId();
            System.out.println(id);
        }
    }
}
