package com.momo.mapper.res.authority;

import com.google.common.collect.Lists;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * @ProjectName: momo-cloud-permission
 * @Package: com.momo.mapper.res.authority
 * @Description: 比较两个list集合里的值 大小 ,并知道最大的那个值在哪个list里
 * @Author: Jie Li
 * @CreateDate: 2019/8/24 0024 15:05
 * @UpdateDate: 2019/8/24 0024 15:05
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"roleType"})
public class CheckTwoSetSizeRes {
    //set类型  1 当前登录用户所拥有的 角色类型
    //        2 为用户授权所拥有的 角色类型
    private Integer whichType;
    //需要比较的值
    private Integer roleType;

    /**
     * @param currentRoleType 1 当前登录用户所拥有的 角色类型
     * @param authorRoleType  2 为用户授权所拥有的 角色类型
     * @return
     */
    public static boolean CheckTwoSetSize(Set<Integer> currentRoleType, Set<Integer> authorRoleType) {
        if (CollectionUtils.isEmpty(currentRoleType)) {
            return true;
        }
        if (CollectionUtils.isEmpty(authorRoleType)) {
            return false;
        }
        //角色的类型，0：管理员(老板)，1：管理员(员工)  2:普通员工 3:其他
        if (currentRoleType.contains(0)) {
            return false;
        }
        List<CheckTwoSetSizeRes> checkTwoSetSizeRes = Lists.newArrayList();
        currentRoleType.forEach(integer -> {
            CheckTwoSetSizeRes setSizeRes = new CheckTwoSetSizeRes();
            setSizeRes.setWhichType(1);
            setSizeRes.setRoleType(integer);
            checkTwoSetSizeRes.add(setSizeRes);
        });
        authorRoleType.forEach(integer -> {
            CheckTwoSetSizeRes setSizeRes = new CheckTwoSetSizeRes();
            setSizeRes.setWhichType(2);
            setSizeRes.setRoleType(integer);
            checkTwoSetSizeRes.add(setSizeRes);
        });
        checkTwoSetSizeRes.sort(deptSeqComparator);
        return !checkTwoSetSizeRes.get(0).getWhichType().equals(1);
    }

    //由小到大
    //优先保证 当前登录用户所拥有的 角色类型  在前面
    private static Comparator<CheckTwoSetSizeRes> deptSeqComparator = new Comparator<CheckTwoSetSizeRes>() {
        @Override
        public int compare(CheckTwoSetSizeRes o1, CheckTwoSetSizeRes o2) {
            Integer size = o1.getRoleType() - o2.getRoleType();
            if (size.equals(0)) {
                return o1.getWhichType() - o2.getWhichType();
            }
            return o1.getRoleType() - o2.getRoleType();
        }
    };
}
