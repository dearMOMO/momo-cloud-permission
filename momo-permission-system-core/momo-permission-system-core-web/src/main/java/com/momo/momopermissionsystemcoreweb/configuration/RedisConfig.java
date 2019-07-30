package com.momo.momopermissionsystemcoreweb.configuration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * 	https://gitee.com/pomZhiXia/boot-root
 */
@Configuration
public class RedisConfig {

	@Value("${spring.redis.database}")
	private Integer database;

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private Integer port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.timeout}")
	private Long timeout;

	@Value("${spring.redis.lettuce.pool.max-idle}")
	private Integer maxIdle;

	@Value("${spring.redis.lettuce.pool.min-idle}")
	private Integer minIdle;

	@Value("${spring.redis.lettuce.pool.max-active}")
	private Integer maxActive;

	@Value("${spring.redis.lettuce.pool.max-wait}")
	private Long maxWait;

	@Bean
	public LettuceConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig<Object> genericObjectPoolConfig) {
		// 单机版配置
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setDatabase(database);
		redisStandaloneConfiguration.setHostName(host);
		redisStandaloneConfiguration.setPort(port);
		redisStandaloneConfiguration.setPassword(RedisPassword.of(password));

		// 集群版配置
//		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//		String[] serverArray = clusterNodes.split(",");
//		Set<RedisNode> nodes = new HashSet<RedisNode>();
//		for (String ipPort : serverArray) {
//			String[] ipAndPort = ipPort.split(":");
//			nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
//		}
//		redisClusterConfiguration.setPassword(RedisPassword.of(password));
//		redisClusterConfiguration.setClusterNodes(nodes);
//		redisClusterConfiguration.setMaxRedirects(maxRedirects);

		LettuceClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration.builder()
				.commandTimeout(Duration.ofMillis(timeout)).poolConfig(genericObjectPoolConfig).build();

		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration,
				lettuceClientConfiguration);
		return lettuceConnectionFactory;
	}

	/**
	 * GenericObjectPoolConfig 连接池配置
	 *
	 * @return
	 */
	@Bean
	public GenericObjectPoolConfig<Object> genericObjectPoolConfig() {
		GenericObjectPoolConfig<Object> genericObjectPoolConfig = new GenericObjectPoolConfig<Object>();
		genericObjectPoolConfig.setMaxIdle(maxIdle);
		genericObjectPoolConfig.setMinIdle(minIdle);
		genericObjectPoolConfig.setMaxTotal(maxActive);
		genericObjectPoolConfig.setMaxWaitMillis(maxWait);
		return genericObjectPoolConfig;
	}

	/**
	 * 设置 redisTemplate 序列化方式
	 * 
	 * @param lettuceConnectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(lettuceConnectionFactory);
		// 设置值（value）的序列化采用FastJsonRedisSerializer。
//		FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
//		redisTemplate.setValueSerializer(fastJsonRedisSerializer);
//		redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
//		// 设置键（key）的序列化采用StringRedisSerializer。
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//		redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
		RedisSerializer stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		redisTemplate.setHashValueSerializer(stringSerializer);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

}
