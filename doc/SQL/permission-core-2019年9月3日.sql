/*
Navicat MySQL Data Transfer

Source Server         : mariadb
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : permission-core

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-09-03 13:45:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl` (
  `id` bigint(20) NOT NULL COMMENT '权限id',
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_acl_action` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '按钮动作类型(交给前端处理）',
  `sys_acl_router` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '所属页面(交给前端处理)',
  `sys_acl_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '权限名称',
  `sys_acl_component_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '前端组件名称',
  `sys_acl_parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父亲权限id',
  `sys_acl_level` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '权限层级',
  `sys_acl_permission_code` varchar(8) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1' COMMENT '菜单系统类型 ',
  `sys_acl_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '请求的url, 可以填正则表达式',
  `sys_acl_type` int(1) NOT NULL DEFAULT 2 COMMENT '类型，-1系统 0:目录 1：菜单，2：按钮，3：其他',
  `sys_acl_icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '图标class',
  `sys_acl_seq` int(11) NOT NULL DEFAULT 1 COMMENT '权限在当前模块下的顺序，由小到大',
  `sys_acl_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '权限码',
  `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  `disabled_flag` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0启用  1禁用',
  `del_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `sys_acl_uuid` (`uuid`) USING BTREE,
  KEY `sys_acl_module_id` (`sys_acl_parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限点表';

-- ----------------------------
-- Records of sys_acl
-- ----------------------------
INSERT INTO `sys_acl` VALUES ('195332810905620480', 'c70a20be0a2e4a1889af5d69263dc972', 'list', 'AuthorUser', '用户列表', 'AuthorUser', '195332810922397709', '0.195332810922397711.195332810922397710.195332810922397709', '1', '/api/platform/sysUser/sysUserList/v1', '1', '', '1', '', '', '0', '0', '李杰', '2019-04-13 11:22:15', '2019-08-22 10:29:16', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810905620484', '5f2056a6a51e441bb904dc6709a817e4', 'list', 'AclTree', '菜单列表', 'AclTree', '195332810922397708', '0.195332810922397711.195332810922397710.195332810922397708', '1', '/api/platform/acl/aclTree/v1', '1', '', '1', '', '', '0', '0', '', '2019-04-13 11:26:58', '2019-08-22 10:29:39', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810905620488', '2c0208809d974940b284ce4522d3e4dd', 'list', 'AuthorRole', '角色列表', 'AuthorRole', '195332810922397707', '0.195332810922397711.195332810922397710.195332810922397707', '1', '/api/platform/role/roleList/v1', '1', '', '1', '', '', '0', '0', '', '2019-04-13 11:30:28', '2019-08-28 09:59:00', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810909814787', '1b8ac96d955e4e78b8e0820b6e2fd890', 'list', 'SysEnterpriseList', '企业列表', 'SysEnterpriseList', '195332810922397705', '0.195332810922397711.195332810922397710.195332810922397705', '1', '/api/platform/enterprise/sysUserGroupPage/v1', '1', '', '1', '', '', '0', '0', '', '2019-04-15 11:09:36', '2019-08-28 11:42:40', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810922397703', '188696496605106236', 'index', '', '首页', '', '195332810922397711', '0.195332810922397711', '1', '188696496605106236', '1', '', '1', '', '', '0', '0', '', '2019-08-02 15:11:28', '2019-08-28 14:09:55', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810922397704', '38b8f82768e9407aa0abea4b83f3a0b0', '', '', '产品提优', '', '195332810922397711', '0.195332810922397711', '1', '188696496605106237', '1', '', '1', '', '', '0', '0', '', '2019-08-02 15:11:02', '2019-08-28 14:10:02', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810922397705', '188696496605106238', 'manager', 'SysEnterpriseUserList', '企业管理', 'SysEnterpriseUserList', '195332810922397710', '0.195332810922397711.195332810922397710', '1', '188696496605106238', '0', '', '1', '', '', '0', '0', '', '2019-08-02 15:10:29', '2019-08-28 14:09:35', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810922397706', 'afae14c9a9d843c6b865872abd75955f', 'manager', '', '部门管理', '', '195332810922397710', '0.195332810922397711.195332810922397710', '1', '188696496605106239', '0', '', '1', '', '', '0', '0', '', '2019-08-02 15:09:53', '2019-08-21 23:01:21', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810922397707', 'f2d1ea1a116343b6ba3910d3734cbe72', 'RoleManager', 'AuthorRole', '角色管理', '', '195332810922397710', '0.195332810922397711.195332810922397710', '1', '/api/platform/role/catalogue/v1', '0', '', '1', '', '', '0', '0', '', '2019-08-02 15:09:04', '2019-08-28 14:09:07', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810922397708', 'b593168f64724ef2aadb55e90bf2fe53', 'AclTreeManager', 'AclTree', '菜单管理', '', '195332810922397710', '0.195332810922397711.195332810922397710', '1', '/api/platform/acl/catalogue/v1', '0', '', '1', '', '', '0', '0', '', '2019-08-02 15:08:01', '2019-08-28 14:09:13', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810922397709', 'e88c27d65f4a4e75bd5842175bfd6371', 'UserManager', 'AuthorUser', '用户管理', '', '195332810922397710', '0.195332810922397711.195332810922397710', '1', '/api/platform/sysUser/catalogue/v1', '0', '', '1', '', '', '0', '0', '', '2019-08-02 15:07:44', '2019-08-28 14:09:20', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810922397710', '61fcd23e1aad4a2581160de1fce545a7', 'manager', 'Auth', '权限管理', 'Auth', '195332810922397711', '0.195332810922397711', '1', '61fcd23e1aad4a2581160de1fce545a7', '0', '', '1', '', '', '0', '0', '', '2019-08-02 15:07:11', '2019-08-21 23:01:31', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810922397711', '61fcd23e1aad4a2581160de1fce545a6', 'manager', '', '后台管理模块', '', '0', '0', '1', '188696496605106244', '-1', '', '1', '', '', '0', '0', '', '2019-08-02 15:04:14', '2019-08-02 15:04:16', '');
INSERT INTO `sys_acl` VALUES ('195866352387493888', '5d166d20fb6945a786dc68e9db7bd706', 'add', 'AuthorUser', '新增', '', '195332810922397709', '0.195332810922397711.195332810922397710.195332810922397709', '1', '/api/platform/sysUser/sysUserAdd/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 09:55:55', '2019-08-28 09:58:08', '李杰');
INSERT INTO `sys_acl` VALUES ('195866564606693376', '149ffb4d4a734a4a891cfbab5ff0155e', 'edit', 'AuthorUser', '编辑', '', '195332810922397709', '0.195332810922397711.195332810922397710.195332810922397709', '1', '/api/platform/sysUser/sysUserModify/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 09:56:45', '2019-08-28 09:58:16', '李杰');
INSERT INTO `sys_acl` VALUES ('195866757108469760', '6e718ba386dd4da2bc8650b115203b0b', 'editPwd', 'AuthorUser', '修改密码', '', '195332810922397709', '0.195332810922397711.195332810922397710.195332810922397709', '1', '/api/platform/sysUser/sysUserPwd/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 09:57:31', '2019-08-28 09:58:22', '李杰');
INSERT INTO `sys_acl` VALUES ('195867199867588608', '651de34888fe4f8e870c4b66ad5f3259', 'roleShow', 'AuthorUser', '角色给用户(回显)', '', '195332810922397709', '0.195332810922397711.195332810922397710.195332810922397709', '1', '/api/platform/sysUser/userCheckRoles/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 09:59:17', '2019-08-28 09:58:27', '李杰');
INSERT INTO `sys_acl` VALUES ('195867319661105152', 'a0218f58b2854b84bd54608bef95453d', 'roleAuthor', 'AuthorUser', '角色给用户(授权)', '', '195332810922397709', '0.195332810922397711.195332810922397710.195332810922397709', '1', '/api/platform/sysUser/rolesToUser/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 09:59:45', '2019-08-28 09:58:32', '李杰');
INSERT INTO `sys_acl` VALUES ('195867479266955264', 'cea452c89a77455ea4ed743a5b37a014', 'status', 'AuthorUser', '状态', '', '195332810922397709', '0.195332810922397711.195332810922397710.195332810922397709', '1', '/api/platform/sysUser/sysUserStatus/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:00:23', '2019-08-28 09:58:39', '李杰');
INSERT INTO `sys_acl` VALUES ('195869456721907712', '3d6d98cd8f12482896a6371247fb212e', 'moduleAdd', 'AclTree', '新增权限系统', '', '195332810922397708', '0.195332810922397711.195332810922397710.195332810922397708', '1', '/api/platform/acl/saveSys/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:08:15', '2019-08-22 10:08:15', '李杰');
INSERT INTO `sys_acl` VALUES ('195869677409406976', '77aaeeccdcc447d1848017c45d960735', 'edit', 'AclTree', '编辑', '', '195332810922397708', '0.195332810922397711.195332810922397710.195332810922397708', '1', '/api/platform/acl/modify/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:09:07', '2019-08-22 10:09:07', '李杰');
INSERT INTO `sys_acl` VALUES ('195869776344649728', 'ef2b4f4b97cd4ad58ee83899ab36d19d', 'detail', 'AclTree', '查询详情', '', '195332810922397708', '0.195332810922397711.195332810922397710.195332810922397708', '1', '/api/platform/acl/detail/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:09:31', '2019-08-22 10:09:31', '李杰');
INSERT INTO `sys_acl` VALUES ('195869910314913792', '8670234a1f984917ae448df41d0101e1', 'add', 'AclTree', '新增', '', '195332810922397708', '0.195332810922397711.195332810922397710.195332810922397708', '1', '/api/platform/acl/save/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:10:03', '2019-08-22 10:10:03', '李杰');
INSERT INTO `sys_acl` VALUES ('195870845300772864', 'b30d24305f1f4def86bb8d3657b7fa4d', 'add', 'AuthorRole', '新增', '', '195332810922397707', '0.195332810922397711.195332810922397710.195332810922397707', '1', '/api/platform/role/save/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:13:46', '2019-08-28 09:59:15', '李杰');
INSERT INTO `sys_acl` VALUES ('195871284125634560', 'c893cb23b0b34c44b7f88888d289b936', 'edit', 'AuthorRole', '编辑', '', '195332810922397707', '0.195332810922397711.195332810922397710.195332810922397707', '1', '/api/platform/role/modify/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:15:31', '2019-08-28 09:59:19', '李杰');
INSERT INTO `sys_acl` VALUES ('195871372877107200', '7ba96f13f8bc4848b66f6ceca94c345e', 'status', 'AuthorRole', '变更角色状态', '', '195332810922397707', '0.195332810922397711.195332810922397710.195332810922397707', '1', '/api/platform/role/status/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:15:52', '2019-08-28 09:59:23', '李杰');
INSERT INTO `sys_acl` VALUES ('195872957032173568', 'fecaff51af264f5aa32fce6dc2adfaba', 'detail', 'SysEnterpriseList', '企业详情', '', '195332810922397705', '0.195332810922397711.195332810922397710.195332810922397705', '1', '/api/platform/enterprise/detail/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:22:09', '2019-09-02 17:19:17', '员工');
INSERT INTO `sys_acl` VALUES ('195873316945399808', 'dc33944794704f0e94236edb2dff33e6', 'add', 'SysEnterpriseList', '新增', '', '195332810922397705', '0.195332810922397711.195332810922397710.195332810922397705', '1', '/api/platform/enterprise/save/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:23:35', '2019-08-28 11:43:00', '李杰');
INSERT INTO `sys_acl` VALUES ('195873379507638272', 'd279c68cb9484eb8bcbb36e675b2f452', 'edit', 'SysEnterpriseList', '编辑', '', '195332810922397705', '0.195332810922397711.195332810922397710.195332810922397705', '1', '/api/platform/enterprise/modify/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:23:50', '2019-08-28 11:43:06', '李杰');
INSERT INTO `sys_acl` VALUES ('195873691517718528', '83dc22991ed14e8b8210118ca5bdbb4f', 'aclsToVip', 'SysEnterpriseList', '为企业授权(授权)', '', '195332810922397705', '0.195332810922397711.195332810922397710.195332810922397705', '1', '/api/platform/enterprise/aclsToEnterprise/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:25:05', '2019-08-28 11:43:12', '李杰');
INSERT INTO `sys_acl` VALUES ('195873832010125312', '821540d6a1d04b969ab7b6d6d5dce8f0', 'aclDetail', 'SysEnterpriseList', '为企业授权(回显)', '', '195332810922397705', '0.195332810922397711.195332810922397710.195332810922397705', '1', '/api/platform/enterprise/aclDetail/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:25:38', '2019-08-28 11:43:26', '李杰');
INSERT INTO `sys_acl` VALUES ('195873978903040000', 'c4dd9b8b74154bfe85dedb7fa1d0da72', 'listRole', 'SysEnterpriseList', '企业角色列表', '', '198088282079367168', '0.195332810922397711.195332810922397710.195332810922397705.198088282079367168', '1', '/api/platform/enterprise/roleList/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-22 10:26:13', '2019-08-28 13:05:37', '李杰');
INSERT INTO `sys_acl` VALUES ('197810122012102656', '8d5b6d89e1204305b70b3a455270e17f', 'aclShow', 'AuthorRole', '权限给角色(回显)', '', '195332810922397707', '0.195332810922397711.195332810922397710.195332810922397707', '1', '/api/platform/role/roleHaveAclTree/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-27 18:39:46', '2019-08-28 09:59:27', '李杰');
INSERT INTO `sys_acl` VALUES ('197810639492747264', '2ff2433314254f2b8b0c03ea356c7948', 'aclAuthor', 'AuthorRole', '权限给角色(授权)', '', '195332810922397707', '0.195332810922397711.195332810922397710.195332810922397707', '1', '/api/platform/role/aclsToRole/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-27 18:41:49', '2019-08-28 09:59:31', '李杰');
INSERT INTO `sys_acl` VALUES ('198058969976147968', '52130fdd357642488e0040277e9cfbe7', 'SysEnterpriseUserList', 'SysEnterpriseUserList', '企业用户管理', 'SysEnterpriseUserList', '195332810922397705', '0.195332810922397711.195332810922397710.195332810922397705', '1', '/api/platform/enterprise/userManager/v1', '1', '', '1', '', '', '0', '0', '李杰', '2019-08-28 11:08:36', '2019-08-28 11:22:52', '李杰');
INSERT INTO `sys_acl` VALUES ('198060110281576448', 'a0dcd785acf74c19838299f1fad042fa', 'userList', 'SysEnterpriseList', '企业用户列表', '', '198058969976147968', '0.195332810922397711.195332810922397710.195332810922397705.198058969976147968', '1', '/api/platform/enterprise/userList/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 11:13:07', '2019-08-28 11:30:16', '李杰');
INSERT INTO `sys_acl` VALUES ('198062985040760832', 'c87fd3a3c2e941c2b7a50db3a9cd0e4c', 'add', 'SysEnterpriseUserList', '企业用户新增', '', '198058969976147968', '0.195332810922397711.195332810922397710.195332810922397705.198058969976147968', '1', '/api/platform/enterprise/userAdd/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 11:24:33', '2019-08-28 11:31:08', '李杰');
INSERT INTO `sys_acl` VALUES ('198063131954647040', '4fffd3a677fd42fab14fd28e6c1f6b6c', 'edit', 'SysEnterpriseUserList', '企业用户编辑', '', '198058969976147968', '0.195332810922397711.195332810922397710.195332810922397705.198058969976147968', '1', '/api/platform/enterprise/userModify/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 11:25:08', '2019-08-28 11:25:08', '李杰');
INSERT INTO `sys_acl` VALUES ('198063685430808576', '297c8af9b87d4ac6912630774fdbc3d6', 'userPwd', 'SysEnterpriseUserList', '企业用户密码修改', '', '198058969976147968', '0.195332810922397711.195332810922397710.195332810922397705.198058969976147968', '1', '/api/platform/enterprise/sysUserPwd/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 11:27:20', '2019-08-28 11:31:47', '李杰');
INSERT INTO `sys_acl` VALUES ('198064017837789184', '5562663686ee4c998c061ee692e4dc5e', 'userCheckRoles', 'SysEnterpriseUserList', '企业用户授权角色(回显)', '', '198058969976147968', '0.195332810922397711.195332810922397710.195332810922397705.198058969976147968', '1', '/api/platform/enterprise/userCheckRoles/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 11:28:39', '2019-08-28 11:28:39', '李杰');
INSERT INTO `sys_acl` VALUES ('198064089065459712', 'e7a7882e3881490e9c3eaba155273284', 'rolesToUser', 'SysEnterpriseUserList', '企业用户授权角色(授权)', '', '198058969976147968', '0.195332810922397711.195332810922397710.195332810922397705.198058969976147968', '1', '/api/platform/enterprise/rolesToUser/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 11:28:56', '2019-08-28 11:31:36', '李杰');
INSERT INTO `sys_acl` VALUES ('198064269110153216', 'de0b28094cf5492f99f4991cb7071c03', 'userStatus', 'SysEnterpriseUserList', '企业用户状态', '', '198058969976147968', '0.195332810922397711.195332810922397710.195332810922397705.198058969976147968', '1', '/api/platform/enterprise/userStatus/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 11:29:39', '2019-08-28 11:29:39', '李杰');
INSERT INTO `sys_acl` VALUES ('198069104119255040', 'f12a0448208a4e84ba4748050b9b4f25', 'status', 'SysEnterpriseList', '企业状态', '', '195332810922397705', '0.195332810922397711.195332810922397710.195332810922397705', '1', '/api/platform/enterprise/status/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 11:48:52', '2019-08-28 11:48:52', '李杰');
INSERT INTO `sys_acl` VALUES ('198088282079367168', '303d4540ccd74c5c9daf6e0cac587564', 'SysEnterpriseRoleList', 'SysEnterpriseRoleList', '企业角色管理', 'SysEnterpriseRoleList', '195332810922397705', '0.195332810922397711.195332810922397710.195332810922397705', '1', '/api/platform/enterprise/roleManager/v1', '1', '', '1', '', '', '0', '0', '李杰', '2019-08-28 13:05:04', '2019-08-28 13:05:04', '李杰');
INSERT INTO `sys_acl` VALUES ('198088655322091520', 'f396973958a94b43971df535489d8e81', 'add', 'SysEnterpriseRoleList', '企业角色新增', '', '198088282079367168', '0.195332810922397711.195332810922397710.195332810922397705.198088282079367168', '1', '/api/platform/enterprise/roleAdd/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 13:06:33', '2019-08-28 13:06:33', '李杰');
INSERT INTO `sys_acl` VALUES ('198088766655696896', 'cff2fa8bb01a4897b83e160f0d346f6c', 'edit', 'SysEnterpriseRoleList', '企业角色编辑', '', '198088282079367168', '0.195332810922397711.195332810922397710.195332810922397705.198088282079367168', '1', '/api/platform/enterprise/roleModify/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 13:07:00', '2019-08-28 13:07:00', '李杰');
INSERT INTO `sys_acl` VALUES ('198088981710245888', '69673415b9b549c98bbdd790d75b9da8', 'roleHaveAclTree', 'SysEnterpriseRoleList', '企业角色授权(查看)', '', '198088282079367168', '0.195332810922397711.195332810922397710.195332810922397705.198088282079367168', '1', '/api/platform/enterprise/roleHaveAclTree/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 13:07:51', '2019-08-28 13:07:51', '李杰');
INSERT INTO `sys_acl` VALUES ('198090905197088768', '7f411f0a08ad46ca990f43cc6b852fcf', 'aclsToRole', 'SysEnterpriseRoleList', '企业角色授权(授权)', '', '198088282079367168', '0.195332810922397711.195332810922397710.195332810922397705.198088282079367168', '1', '/api/platform/enterprise/aclsToRole/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 13:15:29', '2019-08-28 13:15:29', '李杰');
INSERT INTO `sys_acl` VALUES ('198092153824612352', 'd55579ea91964c9aa8c79e2caf5a16bb', 'roleStatus', 'SysEnterpriseRoleList', '企业角色状态', '', '198088282079367168', '0.195332810922397711.195332810922397710.195332810922397705.198088282079367168', '1', '/api/platform/enterprise/roleStatus/v1', '2', '', '1', '', '', '0', '0', '李杰', '2019-08-28 13:20:27', '2019-08-28 13:20:27', '李杰');

-- ----------------------------
-- Table structure for sys_bug
-- ----------------------------
DROP TABLE IF EXISTS `sys_bug`;
CREATE TABLE `sys_bug` (
  `id` bigint(20) NOT NULL,
  `uuid` varchar(32) NOT NULL,
  `sys_product_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '登记人',
  `sys_product_img` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '图片',
  `sys_product_detail` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT 'BUG详细说明',
  `sys_product_reply` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '回复',
  `disabled_flag` int(1) NOT NULL DEFAULT 1 COMMENT '状态 0启用  1禁用',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品提优';

-- ----------------------------
-- Records of sys_bug
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL COMMENT '部门id',
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_dept_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '部门名称',
  `sys_dept_parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '上级部门id',
  `sys_dept_level` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '部门层级',
  `sys_dept_seq` int(11) NOT NULL DEFAULT 0 COMMENT '部门在当前层级下的顺序，由小到大',
  `sys_is_leaf` int(1) NOT NULL DEFAULT 1 COMMENT '是否是叶子节点 0 是   1不是',
  `group_id` bigint(20) NOT NULL COMMENT '第三方组 kagome-momo-open-source默认为1',
  `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '备注',
  `disabled_flag` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0启用  1禁用',
  `del_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `sys_dept_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', 'qwertyuiop', '组织架构', '0', '0', '0', '1', '1', '', '1', '0', 'sys', '2019-01-08 08:58:46', '2019-01-08 08:58:50', 'sys');
INSERT INTO `sys_dept` VALUES ('2', 'rtyhujikl', '指挥部', '1', '0.1', '0', '1', '1', '', '0', '0', '', '2019-01-15 09:55:36', '2019-01-15 09:55:40', '');
INSERT INTO `sys_dept` VALUES ('3', '56789', '商务部', '2', '0.1.2', '0', '1', '1', '', '0', '0', 'sys', '2019-01-15 09:56:11', '2019-01-15 09:56:13', 'sys');
INSERT INTO `sys_dept` VALUES ('19', '0216789944a142948ab9e4025290c7f9', '测试首级部门', '3', '0.1.2.3', '5', '1', '1', '我是备注', '0', '0', '', '2019-03-08 16:39:18', '2019-03-08 16:39:18', '');
INSERT INTO `sys_dept` VALUES ('24', '3b11c7d89a9d47e8b0e391d125c023de', '测试3级部门3', '23', '0.23', '1', '1', '1', '我是备注', '0', '0', '', '2019-03-08 16:57:54', '2019-03-08 16:57:54', '');
INSERT INTO `sys_dept` VALUES ('25', '89efb313bc7e4c3faca760cd99c4b311', '测试3级部门3', '23', '0.23', '1', '0', '1', '我是备注', '1', '0', '', '2019-03-08 16:57:59', '2019-03-08 16:57:59', '');
INSERT INTO `sys_dept` VALUES ('26', 'a03946d75050424685bc5d12cc165012', '测试页面新增部门', '1', '0.1', '2', '1', '1', '测试页面新增部门', '0', '0', '李杰', '2019-04-14 17:03:19', '2019-04-14 17:03:19', '李杰');
INSERT INTO `sys_dept` VALUES ('27', 'e28ced868f03420fb58664bc84f1d185', '测试2级部门3', '19', '0.1.2.3.19', '1', '1', '1', '我是备注', '0', '0', '李杰', '2019-04-22 17:03:58', '2019-04-22 17:03:58', '李杰');
INSERT INTO `sys_dept` VALUES ('28', '63eac50aa14d428781e1fb8ef9e3ae72', '测试新版部门', '26', '0.1.26', '5', '1', '1', '', '1', '0', '李杰', '2019-04-23 19:31:05', '2019-04-23 19:31:05', '李杰');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` bigint(20) NOT NULL,
  `uuid` varchar(32) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户名',
  `user_login_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '登录名',
  `user_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户ip',
  `user_login_type` int(1) DEFAULT NULL COMMENT '登录类型',
  `user_login_device` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '登录设备',
  `user_login_sys` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '操作系统',
  `user_login_browser` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '获取浏览器类型',
  `tenant_id` bigint(20) DEFAULT -1 COMMENT '租户id',
  `disabled_flag` int(1) DEFAULT 0 COMMENT '状态 0启用  1禁用',
  `create_time` datetime DEFAULT NULL COMMENT '登录时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL COMMENT '角色id',
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `sys_role_type` int(1) NOT NULL DEFAULT 1 COMMENT '角色的类型，0：管理员(老板)，1：管理员(员工)  2:普通员工 3:其他',
  `tenant_id` bigint(20) NOT NULL DEFAULT -1 COMMENT '租户id',
  `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '备注',
  `disabled_flag` int(1) NOT NULL DEFAULT 0 COMMENT '是否被禁用  0否 1禁用',
  `del_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT 'update_by',
  PRIMARY KEY (`id`),
  KEY `sys_role_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('196642288150122496', '0c0d4a606e324bdeae70bbe33e963eb7', '管理员', '0', '1', '', '0', '0', '李杰', '2019-08-24 13:19:12', '2019-08-24 13:19:18', '李杰');
INSERT INTO `sys_role` VALUES ('196642358887059456', '33e646cb176b4a62b9314aa357ff0d03', '普通管理员', '1', '1', '', '0', '0', '李杰', '2019-08-24 13:19:29', '2019-08-24 13:19:35', '李杰');
INSERT INTO `sys_role` VALUES ('196642408342097920', 'd4a96268a95e421f8ea813e0887fc87e', '员工', '2', '1', '', '0', '0', '李杰', '2019-08-24 13:19:41', '2019-08-24 13:19:41', '李杰');
INSERT INTO `sys_role` VALUES ('196960960383160320', '459ad7b61beb48128058e048b89db9cb', '普通管理员1', '1', '1', '', '0', '0', '李杰', '2019-08-25 10:25:30', '2019-08-25 10:25:30', '李杰');
INSERT INTO `sys_role` VALUES ('196961031556304896', 'd0f69cef4dd94433993a54ff8b309746', '员工1', '2', '1', '', '0', '0', '李杰', '2019-08-25 10:25:47', '2019-09-02 17:19:09', '员工');
INSERT INTO `sys_role` VALUES ('196961065861517312', 'b3afd39636c242bd885cbc8673e75d50', '管理员1', '0', '1', '', '0', '0', '李杰', '2019-08-25 10:25:55', '2019-08-25 16:39:48', '李杰');
INSERT INTO `sys_role` VALUES ('197019200911446016', '4b75c74ea9c547dabdcdcb6872a7f9a8', '管理员-艾欧尼亚', '0', '195193136069349376', '', '0', '0', '李杰', '2019-08-25 14:16:55', '2019-08-25 16:43:52', '李杰');
INSERT INTO `sys_role` VALUES ('197027793358426112', '9ea51f5299ca41beb9aa29f8f85ad110', '普通管理员-艾欧尼亚', '1', '195193136069349376', '', '0', '0', '李杰', '2019-08-25 14:51:04', '2019-08-25 14:51:16', '李杰');
INSERT INTO `sys_role` VALUES ('197027820764008448', '9777a10b96b94ab699a699b1ddbf0f30', '员工-艾欧尼亚', '2', '195193136069349376', '', '0', '0', '李杰', '2019-08-25 14:51:10', '2019-08-25 16:34:42', 'aoni');
INSERT INTO `sys_role` VALUES ('197032542359130112', 'd82dc7a63bfd4aa6a12693d452e55854', '测试-员工', '2', '195193136069349376', '', '1', '0', '李杰', '2019-08-25 15:09:56', '2019-09-02 17:18:32', '员工');
INSERT INTO `sys_role` VALUES ('197032585631764480', '8f316acac49e4da59b2d10f159ccf15f', '测试-普通管理员', '1', '195193136069349376', '', '0', '0', '李杰', '2019-08-25 15:10:06', '2019-08-25 15:48:10', 'aoni');
INSERT INTO `sys_role` VALUES ('197032610738868224', '4989f7739b2c453295194155788bdcf4', '测试-普通管理员2', '2', '195193136069349376', '', '1', '0', '李杰', '2019-08-25 15:10:12', '2019-09-02 17:18:29', '员工');

-- ----------------------------
-- Table structure for sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl` (
  `id` bigint(20) NOT NULL,
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_role_id` bigint(20) NOT NULL COMMENT '角色id',
  `sys_acl_id` bigint(20) NOT NULL COMMENT '权限id',
  `sys_acl_permission_code` varchar(8) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1' COMMENT '权限系统类型',
  `tenant_id` bigint(20) NOT NULL DEFAULT -1 COMMENT '租户id',
  `disabled_flag` int(1) NOT NULL DEFAULT 0 COMMENT '是否被禁用  0否 1禁用',
  `del_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `sys_role_id` (`sys_role_id`) USING BTREE,
  KEY `sys_acl_id` (`sys_acl_id`) USING BTREE,
  KEY `sys_acl_permission_type` (`sys_acl_permission_code`),
  KEY `group_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色和权限中间表';

-- ----------------------------
-- Records of sys_role_acl
-- ----------------------------
INSERT INTO `sys_role_acl` VALUES ('197052717686984704', '93099174e57e47d4b703e044c133109c', '196961065861517312', '195332810922397711', '1', '1', '0', '0', '李杰', '2019-08-25 16:30:06', '2019-08-25 16:30:06', '李杰');
INSERT INTO `sys_role_acl` VALUES ('197052717686984705', '25e702636e5e4dd8b482a63426d24b82', '196961065861517312', '195332810922397710', '1', '1', '0', '0', '李杰', '2019-08-25 16:30:06', '2019-08-25 16:30:06', '李杰');
INSERT INTO `sys_role_acl` VALUES ('197052717686984706', '4130aa8231d6448ca5b80cdfec90ea40', '196961065861517312', '195870845300772864', '1', '1', '0', '0', '李杰', '2019-08-25 16:30:06', '2019-08-25 16:30:06', '李杰');
INSERT INTO `sys_role_acl` VALUES ('197052717686984707', 'd6f16ca48edf4082b3b2357203d96fb8', '196961065861517312', '195332810905620480', '1', '1', '0', '0', '李杰', '2019-08-25 16:30:06', '2019-08-25 16:30:06', '李杰');
INSERT INTO `sys_role_acl` VALUES ('197052717686984708', 'ec1e52a0b3724ceea8f7774779713c09', '196961065861517312', '195332810922397709', '1', '1', '0', '0', '李杰', '2019-08-25 16:30:06', '2019-08-25 16:30:06', '李杰');
INSERT INTO `sys_role_acl` VALUES ('197052717686984709', 'cda72c4f920d48f8ad82adc8db161fa1', '196961065861517312', '195332810922397707', '1', '1', '0', '0', '李杰', '2019-08-25 16:30:06', '2019-08-25 16:30:06', '李杰');
INSERT INTO `sys_role_acl` VALUES ('197053625896407040', '5e3f76b068554463a33f5c7dd7710ba3', '197027793358426112', '195332810905620488', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:33:43', '2019-08-25 16:33:43', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053625900601344', '4cf404cfa8c34ddabd6513fd0f277e49', '197027793358426112', '195332810922397711', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:33:43', '2019-08-25 16:33:43', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053625900601345', '0d10c9a1cdb946218f3cd0f887110311', '197027793358426112', '195332810922397710', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:33:43', '2019-08-25 16:33:43', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053625900601346', 'ce9830d5cbc0429db227990b47ac44d6', '197027793358426112', '195871372877107200', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:33:43', '2019-08-25 16:33:43', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053625900601347', '9057a8f5d1a542769446b1ecd13cc876', '197027793358426112', '195871284125634560', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:33:43', '2019-08-25 16:33:43', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053625900601348', 'e60ea9b7355f4678a6c540e6117b50d7', '197027793358426112', '195332810922397707', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:33:43', '2019-08-25 16:33:43', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053704711573504', '9150188bc0814b7bafd2c4574f5d5d8b', '197027820764008448', '195332810922397707', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:34:02', '2019-08-25 16:34:02', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053704711573505', '0e2b96b1ba604ce1b7bb53deddc35564', '197027820764008448', '195332810922397709', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:34:02', '2019-08-25 16:34:02', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053704711573506', 'ce8b3758e04b497db8ed990553a3ce69', '197027820764008448', '195332810922397710', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:34:02', '2019-08-25 16:34:02', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053704711573507', 'ed4e909a4068498aa75c464092f6fe9b', '197027820764008448', '195332810922397711', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:34:02', '2019-08-25 16:34:02', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053704711573508', 'a10ac929a14d4d32b9f6b0855ddf6b1b', '197027820764008448', '195866352387493888', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:34:02', '2019-08-25 16:34:02', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053704711573509', 'e60f3326098847398464dfb17aaeb6e9', '197027820764008448', '195867199867588608', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:34:02', '2019-08-25 16:34:02', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053704711573510', 'a26be2857c4c466ca90d08a5b2cc0c78', '197027820764008448', '195870845300772864', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:34:02', '2019-08-25 16:34:02', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('197053704711573511', '10264d1f2cbf44ea8a0d7d1de7483dc6', '197027820764008448', '195871372877107200', '1', '195193136069349376', '0', '0', 'aoni', '2019-08-25 16:34:02', '2019-08-25 16:34:02', 'aoni');
INSERT INTO `sys_role_acl` VALUES ('198089256797868032', '65deaa8e1dd446ab94281a7232987e6d', '197019200911446016', '195332810922397711', '1', '195193136069349376', '0', '0', '李杰', '2019-08-28 13:08:56', '2019-08-28 13:08:56', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198089256797868033', 'df159051cf3c46b1a8f8c45d49de155d', '197019200911446016', '195332810922397710', '1', '195193136069349376', '0', '0', '李杰', '2019-08-28 13:08:56', '2019-08-28 13:08:56', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198089256797868034', '725a08ad89c146e39aa4fc1565206542', '197019200911446016', '195332810922397706', '1', '195193136069349376', '0', '0', '李杰', '2019-08-28 13:08:56', '2019-08-28 13:08:56', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198101633320751104', '4144882103bf4b4297f8e7cbf24c3b9e', '197032585631764480', '195332810922397703', '1', '195193136069349376', '0', '0', '李杰', '2019-08-28 13:58:07', '2019-08-28 13:58:07', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198101633320751105', '470aa6af22d34ddcbc0d85f29dbaf06d', '197032585631764480', '195332810922397705', '1', '195193136069349376', '0', '0', '李杰', '2019-08-28 13:58:07', '2019-08-28 13:58:07', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198101633320751106', 'a7529953e3a44d418cf87f1d2148e3c9', '197032585631764480', '195332810922397711', '1', '195193136069349376', '0', '0', '李杰', '2019-08-28 13:58:07', '2019-08-28 13:58:07', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198101633320751107', '8431fa0c4c184e89a4ebb6c1e1d6f2a5', '197032585631764480', '195332810922397710', '1', '195193136069349376', '0', '0', '李杰', '2019-08-28 13:58:07', '2019-08-28 13:58:07', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198101633320751108', '1d3e8220022c468e88824441f05398b8', '197032585631764480', '195332810922397706', '1', '195193136069349376', '0', '0', '李杰', '2019-08-28 13:58:07', '2019-08-28 13:58:07', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198101633320751109', '7547f6357dd34735a866c7440f7ce02b', '197032585631764480', '195872957032173568', '1', '195193136069349376', '0', '0', '李杰', '2019-08-28 13:58:07', '2019-08-28 13:58:07', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267421589504', '39b2073b44c2438d820649eef0faf246', '196960960383160320', '195332810905620480', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755328', 'e2b5975332c4441bbf30402201361abf', '196960960383160320', '195332810905620488', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755329', 'b6fffbfd444f446db8d4f3c129996e63', '196960960383160320', '195332810922397707', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755330', 'f61e2c6db971470ca7c825e272559e5c', '196960960383160320', '195332810922397709', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755331', '6b8a972bc1a54f38bb447efff19e0619', '196960960383160320', '195332810922397710', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755332', '5e1acce949874fce8d14315655ea21fd', '196960960383160320', '195332810922397711', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755333', 'fb9d4dcd6dbc4194b1d9bd3e052429f7', '196960960383160320', '195866352387493888', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755334', '872d34d029da4cd59cd66193b559c60c', '196960960383160320', '195866564606693376', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755335', 'cb14653f982f44fba962695be1cba534', '196960960383160320', '195866757108469760', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755336', '4c247ece5aa6498b83cadf254cfa5ace', '196960960383160320', '195867199867588608', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755337', '741bcb5c836344e9acaae2ed0a30177d', '196960960383160320', '195867319661105152', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755338', 'aafc33e525c14b97b174c4eaf44910ba', '196960960383160320', '195867479266955264', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('198103267446755339', 'a8fadd84c95a4ad3b69f34c5701084d9', '196960960383160320', '195870845300772864', '1', '1', '0', '0', '李杰', '2019-08-28 14:04:37', '2019-08-28 14:04:37', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416406933504', '24d72a76bd1641a2952bed69cc66bdbd', '196642408342097920', '195332810905620480', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416406933505', '608fa5b31a6148eda07f778599c6cf55', '196642408342097920', '195332810905620484', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416406933506', 'b5c25a75b21441169ad85ab514aa0e2d', '196642408342097920', '195332810905620488', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416406933507', 'e11a1889defa48ea95bd8beb59515c48', '196642408342097920', '195332810909814787', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416406933508', '904f4d736c904dec8570592899244f63', '196642408342097920', '195332810922397703', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416406933509', '40e784d5c47646728edc2f1c9f3a8160', '196642408342097920', '195332810922397704', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416406933510', 'd28feec34abb42389a0363b0600d9a7b', '196642408342097920', '195332810922397705', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416406933511', 'e60ce9956ff747f59172d8ee5dd73e0b', '196642408342097920', '195332810922397706', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416406933512', 'ca7fc3866d354ab58b0976c79c7d1c4f', '196642408342097920', '195332810922397707', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416406933513', 'c591e39fb09d4def8263a6addc1e4cbd', '196642408342097920', '195332810922397708', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127808', 'ff1700dfd86e49ce9f4b88343b423fbb', '196642408342097920', '195332810922397709', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127809', '858a4230641140a69f6f17575b6c363d', '196642408342097920', '195332810922397710', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127810', 'b65c23c6ee3648d8a87fb1c9b3ac9b77', '196642408342097920', '195332810922397711', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127811', 'bef433a572234b90ba914bab6a276e3e', '196642408342097920', '195866352387493888', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127812', 'aceaef659ab541678ae69305377682bd', '196642408342097920', '195866564606693376', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127813', '160bcdbc951949a29651b446e697132e', '196642408342097920', '195866757108469760', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127814', '4f67d42f721b4cf39fd8cb6b0c636991', '196642408342097920', '195867199867588608', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127815', '969f52bd6e8e44c4b64364584da8645f', '196642408342097920', '195867319661105152', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127816', '156e555f31324f7790d810b5fb581489', '196642408342097920', '195867479266955264', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127817', '57ef06cd27da47e190475a194e8b4c18', '196642408342097920', '195869456721907712', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127818', '13e4aa1e501744c9bebb862308d148db', '196642408342097920', '195869677409406976', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127819', 'c6a3996ef87949e2b568ce220bf2bde2', '196642408342097920', '195869776344649728', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127820', '0199fe8e274f4ad5a59f5f913415d9ab', '196642408342097920', '195869910314913792', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127821', '4b137e9649074a46aba39cc6c40e1562', '196642408342097920', '195870845300772864', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127822', '4b245849799143d49a43759d6b19c2d4', '196642408342097920', '195871284125634560', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127823', '898a82ec04ea4d58a89ce5ea403ec275', '196642408342097920', '195871372877107200', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127824', '370985e0933d4b50977e3a9263e974f1', '196642408342097920', '195872957032173568', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127825', '3c9705efb6394a5cb5eb643843654d70', '196642408342097920', '195873316945399808', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127826', 'fa71a9704854487192ee225c39430c94', '196642408342097920', '195873379507638272', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127827', 'ead8617a49c548968ab8cf39cedbeb73', '196642408342097920', '195873691517718528', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127828', '350c87a8f7b042ec94a2d4fe605e3a08', '196642408342097920', '195873832010125312', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127829', '89238861b54340d0848b2a00f07eab27', '196642408342097920', '195873978903040000', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127830', 'd1bd185e229b4bc89ca173b6f8fad5ee', '196642408342097920', '197810122012102656', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127831', '6db72abb48644a49b342eabb220707eb', '196642408342097920', '197810639492747264', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127832', 'a44e93edba82476a8ebffb0921a3209a', '196642408342097920', '198058969976147968', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127833', 'c1c63fc73aef4edbb1cbb83c67deadf4', '196642408342097920', '198060110281576448', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127834', 'bdcd37bc3115452892f7e8c5ec24be8b', '196642408342097920', '198062985040760832', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127835', '4e52d2557a404d1ca0feef91f176a66c', '196642408342097920', '198063131954647040', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127836', 'a5a32955414f408bb9127f9e205e4034', '196642408342097920', '198063685430808576', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127837', '4408ade353dd4c35aeda6a33daf50946', '196642408342097920', '198064017837789184', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127838', '2429b1d0a9f24873b29c28259ac71b00', '196642408342097920', '198064089065459712', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127839', '1f5398651233436093af75aa67e3442a', '196642408342097920', '198064269110153216', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127840', '42a6dded8e714283bf20b01ccaa0ff75', '196642408342097920', '198069104119255040', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127841', '8a6faf8b2e26490183f3da0c48c60d91', '196642408342097920', '198088282079367168', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127842', 'f7175c48683148a6a9811da714bcbc99', '196642408342097920', '198088655322091520', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127843', '5b413355942c4757b7c50575b5ab0e49', '196642408342097920', '198088766655696896', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127844', '9c2d0d6fece640e1b66dc4c80e7e207a', '196642408342097920', '198088981710245888', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127845', 'af4675b7b68c4b26bb1cfe3fe86ef2aa', '196642408342097920', '198090905197088768', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199944416411127846', 'feff6e40169943a9bf69e832333e7709', '196642408342097920', '198092153824612352', '1', '1', '0', '0', '李杰', '2019-09-02 16:00:41', '2019-09-02 16:00:41', '李杰');
INSERT INTO `sys_role_acl` VALUES ('199964040267698176', '7209cf40b206482293dd36a861d09a5c', '197032610738868224', '195332810905620488', '1', '195193136069349376', '0', '0', '员工', '2019-09-02 17:18:40', '2019-09-02 17:18:40', '员工');
INSERT INTO `sys_role_acl` VALUES ('199964040276086784', '8a14f1dc121040f7bee69c4213101e3d', '197032610738868224', '195332810922397706', '1', '195193136069349376', '0', '0', '员工', '2019-09-02 17:18:40', '2019-09-02 17:18:40', '员工');
INSERT INTO `sys_role_acl` VALUES ('199964040276086785', '6c4cf244ecee40dda80eb594ae11c3fb', '197032610738868224', '195332810922397707', '1', '195193136069349376', '0', '0', '员工', '2019-09-02 17:18:40', '2019-09-02 17:18:40', '员工');
INSERT INTO `sys_role_acl` VALUES ('199964040276086786', 'c4368ed14bb64c66b5afb88364fec835', '197032610738868224', '195332810922397709', '1', '195193136069349376', '0', '0', '员工', '2019-09-02 17:18:40', '2019-09-02 17:18:40', '员工');
INSERT INTO `sys_role_acl` VALUES ('199964040276086787', '85b1a488ff874e0bb36f733babc95ea3', '197032610738868224', '195332810922397710', '1', '195193136069349376', '0', '0', '员工', '2019-09-02 17:18:40', '2019-09-02 17:18:40', '员工');
INSERT INTO `sys_role_acl` VALUES ('199964040276086788', 'a441211a491d4ef8bb516d0d7571f642', '197032610738868224', '195332810922397711', '1', '195193136069349376', '0', '0', '员工', '2019-09-02 17:18:40', '2019-09-02 17:18:40', '员工');
INSERT INTO `sys_role_acl` VALUES ('199964040276086789', 'bc8a8758d1f64006881f38dbd93c4f8d', '197032610738868224', '195866352387493888', '1', '195193136069349376', '0', '0', '员工', '2019-09-02 17:18:40', '2019-09-02 17:18:40', '员工');
INSERT INTO `sys_role_acl` VALUES ('199964040276086790', 'f5ab0aed1e684ed28350a246a1155847', '197032610738868224', '195867199867588608', '1', '195193136069349376', '0', '0', '员工', '2019-09-02 17:18:40', '2019-09-02 17:18:40', '员工');
INSERT INTO `sys_role_acl` VALUES ('199964040276086791', 'b2d16a1f7fd64acdbe6b48256c71d7b7', '197032610738868224', '195867319661105152', '1', '195193136069349376', '0', '0', '员工', '2019-09-02 17:18:40', '2019-09-02 17:18:40', '员工');
INSERT INTO `sys_role_acl` VALUES ('199964040276086792', 'a5122e2b09f842bd9c56fd4eb8a734be', '197032610738868224', '195871284125634560', '1', '195193136069349376', '0', '0', '员工', '2019-09-02 17:18:40', '2019-09-02 17:18:40', '员工');
INSERT INTO `sys_role_acl` VALUES ('199964123231031296', '8324be3dfb524cf4a25829276417f025', '196961031556304896', '195332810922397703', '1', '1', '0', '0', '员工', '2019-09-02 17:18:59', '2019-09-02 17:18:59', '员工');
INSERT INTO `sys_role_acl` VALUES ('199964123231031297', 'c74639dd44604915825e5f85acd7ed30', '196961031556304896', '195332810922397711', '1', '1', '0', '0', '员工', '2019-09-02 17:18:59', '2019-09-02 17:18:59', '员工');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` bigint(20) NOT NULL,
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `tenant_id` bigint(20) NOT NULL DEFAULT -1 COMMENT '租户id',
  `del_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常，1-删除)',
  `disabled_flag` int(1) DEFAULT 0 COMMENT '状态 0启用  1禁用',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  KEY `group_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色和用户中间表';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('196691248474624000', 'e8e736edef3d4b5ea2c53e9b2d633c44', '196642358887059456', '1', '1', '0', '0', '员工', '2019-08-24 16:33:45', '2019-08-24 16:33:45', '员工');
INSERT INTO `sys_role_user` VALUES ('197033715669536768', '4c424d89fea744118356e2bba9c7e8f2', '197032542359130112', '197002518000177152', '195193136069349376', '0', '0', '李杰', '2019-08-25 15:14:36', '2019-08-25 15:14:36', '李杰');
INSERT INTO `sys_role_user` VALUES ('197033715669536769', '9e0f344081e74f7b9d681bb16b10174c', '197032585631764480', '197002518000177152', '195193136069349376', '0', '0', '李杰', '2019-08-25 15:14:36', '2019-08-25 15:14:36', '李杰');
INSERT INTO `sys_role_user` VALUES ('197033715669536770', 'edce1e4db57444e99930447a3700e960', '197019200911446016', '197002518000177152', '195193136069349376', '0', '0', '李杰', '2019-08-25 15:14:36', '2019-08-25 15:14:36', '李杰');
INSERT INTO `sys_role_user` VALUES ('199944293069230080', 'b71f3ab76b65490db6ae96c4108c7fcc', '196642408342097920', '196614602946121728', '1', '0', '0', '李杰', '2019-09-02 16:00:12', '2019-09-02 16:00:12', '李杰');
INSERT INTO `sys_role_user` VALUES ('199944293069230081', 'ec690ed6e05f4e4790e86b3d7d395edc', '196642358887059456', '196614602946121728', '1', '0', '0', '李杰', '2019-09-02 16:00:12', '2019-09-02 16:00:12', '李杰');

-- ----------------------------
-- Table structure for sys_routes
-- ----------------------------
DROP TABLE IF EXISTS `sys_routes`;
CREATE TABLE `sys_routes` (
  `p_id` int(11) NOT NULL,
  `uuid` varchar(32) DEFAULT NULL,
  `sys_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '这个路由的唯一id，不定义的话为一个uuid',
  `sys_predicates` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '表示这个路由的请求匹配规则，只有符合这个规则的请求才会走这个路由。为一个数组，每个规则为并且的关系',
  `sys_filters` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '请求转发前的filter，为一个数组',
  `sys_uri` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT 'http请求为lb://前缀 + 服务id；ws请求为lb:ws://前缀 + 服务id；表示将请求负载到哪一个服务上',
  `sys_order` int(11) NOT NULL COMMENT '这个路由的执行order',
  `sys_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '路由名称',
  `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '备注',
  `disabled_flag` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0启用  1禁用',
  `del_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`p_id`),
  UNIQUE KEY `id` (`sys_id`) USING BTREE,
  UNIQUE KEY `sys_uri` (`sys_uri`) USING BTREE,
  UNIQUE KEY `sys_name` (`sys_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_routes
-- ----------------------------
INSERT INTO `sys_routes` VALUES ('2', null, 'system-core', '[{\"args\":{\"_genkey_0\":\"/api/platform/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"1\"},\"name\":\"StripPrefix\"},{\"args\":{\"redis-rate-limiter.burstCapacity\":\"30\",\"redis-rate-limiter.replenishRate\":\"20\",\"keyResolver\":\"#{@apiKeyResolver}\"},\"name\":\"RequestRateLimiter\"}]', 'lb://system-core', '1', '后台管理系统', '', '0', '0', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '');
INSERT INTO `sys_routes` VALUES ('4', null, 'test-core', '[{\"args\":{\"_genkey_0\":\"/test/platform/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"1\"},\"name\":\"StripPrefix\"},{\"args\":{\"redis-rate-limiter.burstCapacity\":\"30\",\"redis-rate-limiter.replenishRate\":\"20\",\"keyResolver\":\"#{@apiKeyResolver}\"},\"name\":\"RequestRateLimiter\"}]', 'lb://test-core', '9', '测试路由', '', '0', '0', '', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `sys_user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '姓名',
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_user_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '手机号',
  `sys_user_email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '邮箱',
  `sys_user_province_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '省 名字',
  `sys_user_province` bigint(20) DEFAULT NULL COMMENT '省',
  `sys_user_city_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '市名字',
  `sys_user_city` bigint(20) DEFAULT NULL COMMENT '市',
  `sys_user_area_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '区名字',
  `sys_user_area` bigint(20) DEFAULT NULL COMMENT '区',
  `tenant_id` bigint(20) DEFAULT -1 COMMENT '租户id',
  `remark` varchar(128) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '备注',
  `disabled_flag` int(1) DEFAULT 0 COMMENT '是否被禁用  0否 1禁用',
  `del_flag` int(1) DEFAULT 0 COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `sys_user_name` (`sys_user_name`) USING BTREE,
  KEY `sys_user_phone` (`sys_user_phone`),
  KEY `sys_user_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户基础表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '李杰', 'add6df8ac7f84211ba2303630fca10db', '15050451502', '15050451502@163.com', '江苏省', '1', '江苏省', '1', '江苏省', '1', '1', null, '0', '0', '枸杞', '2019-01-11 15:09:26', '2019-04-21 11:11:26', '李杰');
INSERT INTO `sys_user` VALUES ('196614602946121728', '员工', 'ea5cd2fe546c4625ac4c192ac6a8e720', '', 'yg@qq.com', '', null, '', null, '', null, '1', '', '0', '0', '李杰', '2019-08-24 11:29:12', '2019-09-02 17:19:22', '员工');
INSERT INTO `sys_user` VALUES ('197002518000177152', 'aoni', '8c5eb406a37d4dba89d9e23cbea596cf', '', 'aoni@qq.com', '', null, '', null, '', null, '195193136069349376', '', '0', '0', '李杰', '2019-08-25 13:10:38', '2019-08-25 13:10:38', '李杰');

-- ----------------------------
-- Table structure for sys_user_account_pwd
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_account_pwd`;
CREATE TABLE `sys_user_account_pwd` (
  `id` bigint(20) NOT NULL,
  `uuid` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT 'uuid',
  `sys_user_login_name` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '登录名',
  `sys_user_pwd` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `sys_login_number` int(3) DEFAULT 1 COMMENT '账号允许登录的次数 -1 不限次数 ，0禁止登陆',
  `sys_user_auth_salt` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '撒盐',
  `sys_user_id` bigint(20) NOT NULL COMMENT '关联用户表id',
  `tenant_id` bigint(20) NOT NULL DEFAULT -1 COMMENT '租户id',
  `remark` varchar(128) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '备注',
  `disabled_flag` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0启用  1禁用',
  `del_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户账号密码';

-- ----------------------------
-- Records of sys_user_account_pwd
-- ----------------------------
INSERT INTO `sys_user_account_pwd` VALUES ('187630174626516995', 'add6df8ac7f84211ba2303630fca10db', 'momo', 'fb0429f8fe60d027ae94ddade5532373f72eddf37f5be40ab14b1336aa43565c60c898a86e594a188a9d0dd0cb4461629d8807090cd10d28bb9d5fa6680db01a', '5', 'ec312b5292094dcf8118e793f55b05e7', '1', '1', '我是备注', '1', '0', 'momo', '2019-07-30 16:28:37', '2019-07-30 16:28:39', 'momo');
INSERT INTO `sys_user_account_pwd` VALUES ('196614603013230592', 'cd3e3b69efa246e6b85adc2d9775a7e9', 'yg@qq.com', '8df60a3995809d6bc6df002a0c5a7ce0c5da2ac8d3504b0bc150732890db915206c8c2f88935afb819f127ea93d63d4df69523020270173330f484d282a2c2b8', '1', 'd6575c0be5d74339af3b5afe8148128c', '196614602946121728', '1', '', '0', '0', '李杰', '2019-08-24 11:29:12', '2019-08-24 11:29:12', '李杰');
INSERT INTO `sys_user_account_pwd` VALUES ('197002518180532224', '6132d4e97e9a4f0b8f90953e996be1cb', 'aoni@qq.com', '0c6dafb7cc9e713c94c3b1323a2a46a7ca2b84ada2750d7d1de963dacf28e50541133a220078a484152b4d6603faf0bd9fe65ea9406e2bdb0d2c6a4bfd0dfae3', '1', '9567d2937eb547df8eb6c2c60746590f', '197002518000177152', '195193136069349376', '', '0', '0', '李杰', '2019-08-25 13:10:38', '2019-08-25 13:10:38', '李杰');

-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept` (
  `id` bigint(20) NOT NULL,
  `uuid` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `sys_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `sys_dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户id',
  `disabled_flag` int(1) NOT NULL DEFAULT 0 COMMENT '是否被禁用  0否 1禁用',
  `del_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户和部门';

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group` (
  `id` bigint(20) NOT NULL,
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'uuid',
  `user_group_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '用户组名称/第三方公司名称',
  `sys_account_start_time` datetime DEFAULT NULL COMMENT '账号开通时间 (不传值，默认系统时间)',
  `sys_account_end_time` datetime DEFAULT NULL COMMENT '账号结束时间',
  `sys_open_day` int(11) NOT NULL DEFAULT 0 COMMENT '开通的天数 -1 不限次数 自己公司公司所有',
  `sys_create_account_num` int(11) NOT NULL DEFAULT 0 COMMENT '可以开通子账户的个数 -1不限制次数  id为1 为自己公司，不限次数',
  `sys_open_account_num` int(11) NOT NULL DEFAULT 0 COMMENT '已开通账号个数',
  `name_top` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '顶部名称',
  `name_bottom` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '版权',
  `disabled_flag` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0启用  1禁用',
  `remark` varchar(64) NOT NULL DEFAULT '' COMMENT '备注',
  `del_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `user_group_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方权限组';

-- ----------------------------
-- Records of sys_user_group
-- ----------------------------
INSERT INTO `sys_user_group` VALUES ('1', 'fghjk', 'kagome-momo-open-source', '2019-01-27 17:48:39', '2060-12-01 18:52:41', '0', '0', '0', '', '', '0', '', '0', '', '2019-01-27 17:49:04', '2019-01-27 17:49:06', '');
INSERT INTO `sys_user_group` VALUES ('2', '0a8dc63ab9a04b82a9fda25ea4be6fdb', '阿里爸爸', null, null, '0', '0', '0', '阿里爸爸', 'Copyright © 2018-2019 阿里爸爸, All Rights Reserved. 苏ICP备18055393号', '0', '', '0', '李杰', '2019-04-18 11:02:37', '2019-04-24 14:27:38', '李杰');
INSERT INTO `sys_user_group` VALUES ('3', '88b33625c6a2402e99345968c530337f', '腾讯爸爸', null, null, '0', '0', '0', '腾讯爸爸', 'Copyright © 2018-2019 腾讯爸爸, All Rights Reserved. 苏ICP备18055393号', '0', '', '0', '李杰', '2019-04-18 11:11:11', '2019-04-23 21:07:56', '李杰');
INSERT INTO `sys_user_group` VALUES ('195193136069349376', 'a64fe4a034654af89fe6c845eae73159', '艾欧尼亚', '2019-01-20 13:20:33', '2021-01-04 02:02:00', '0', '0', '0', '', '', '0', '艾欧尼亚', '0', '李杰', '2019-08-20 13:20:47', '2019-08-25 22:45:00', '李杰');

-- ----------------------------
-- Table structure for sys_website_visible
-- ----------------------------
DROP TABLE IF EXISTS `sys_website_visible`;
CREATE TABLE `sys_website_visible` (
  `id` bigint(20) NOT NULL,
  `visible_number` bigint(20) NOT NULL DEFAULT 0 COMMENT '访问总人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站访问总人数';

-- ----------------------------
-- Records of sys_website_visible
-- ----------------------------
INSERT INTO `sys_website_visible` VALUES ('1', '2');
