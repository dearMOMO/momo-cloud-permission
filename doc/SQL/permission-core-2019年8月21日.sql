/*
Navicat MySQL Data Transfer

Source Server         : mariadb
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : permission-core

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-08-21 10:22:23
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
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0启用  1禁用',
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
INSERT INTO `sys_acl` VALUES ('195332810905620480', 'c70a20be0a2e4a1889af5d69263dc972', 'list', 'AuthUser', '用户列表', 'AuthUser', '3', '0.195332810922397711.195332810922397710.195332810922397709', '1', '1', '1', '', '1', '', '', '0', '0', '李杰', '2019-04-13 11:22:15', '2019-04-13 11:22:17', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810905620484', '5f2056a6a51e441bb904dc6709a817e4', 'list', 'AclTree', '菜单列表', 'AclTree', '4', '0.195332810922397711.195332810922397710.195332810922397708', '1', '5', '1', '', '1', '', '', '0', '0', '', '2019-04-13 11:26:58', '2019-04-13 11:27:00', '');
INSERT INTO `sys_acl` VALUES ('195332810905620488', '2c0208809d974940b284ce4522d3e4dd', 'list', 'AuthRole', '角色列表', 'RoleList', '5', '0.195332810922397711.195332810922397710.195332810922397707', '1', '9', '1', '', '1', '', '', '0', '0', '', '2019-04-13 11:30:28', '2019-04-13 11:30:30', '');
INSERT INTO `sys_acl` VALUES ('195332810909814787', '1b8ac96d955e4e78b8e0820b6e2fd890', 'list', 'AuthVip', '企业列表', 'enterpriseList', '8', '0.195332810922397711.195332810922397710.195332810922397705', '1', '18', '1', '', '1', '', '', '0', '0', '', '2019-04-15 11:09:36', '2019-04-23 20:31:17', '李杰');
INSERT INTO `sys_acl` VALUES ('195332810922397703', '188696496605106236', '', '', '首页', '', '0', '0.195332810922397711', '1', '188696496605106236', '1', '', '1', '', '', '0', '0', '', '2019-08-02 15:11:28', '2019-08-02 15:11:30', '');
INSERT INTO `sys_acl` VALUES ('195332810922397704', '38b8f82768e9407aa0abea4b83f3a0b0', '', '', '产品提优', '', '0', '0.195332810922397711', '1', '188696496605106237', '1', '', '1', '', '', '0', '0', '', '2019-08-02 15:11:02', '2019-08-02 15:11:03', '');
INSERT INTO `sys_acl` VALUES ('195332810922397705', '188696496605106238', '', '', '企业管理', '', '0', '0.195332810922397711.195332810922397710', '1', '188696496605106238', '1', '', '1', '', '', '0', '0', '', '2019-08-02 15:10:29', '2019-08-02 15:10:30', '');
INSERT INTO `sys_acl` VALUES ('195332810922397706', 'afae14c9a9d843c6b865872abd75955f', '', '', '部门管理', '', '0', '0.195332810922397711.195332810922397710', '1', '188696496605106239', '1', '', '1', '', '', '0', '0', '', '2019-08-02 15:09:53', '2019-08-02 15:09:55', '');
INSERT INTO `sys_acl` VALUES ('195332810922397707', 'f2d1ea1a116343b6ba3910d3734cbe72', '', '', '角色管理', '', '0', '0.195332810922397711.195332810922397710', '1', '188696496605106240', '1', '', '1', '', '', '0', '0', '', '2019-08-02 15:09:04', '2019-08-02 15:09:06', '');
INSERT INTO `sys_acl` VALUES ('195332810922397708', 'b593168f64724ef2aadb55e90bf2fe53', '', '', '菜单管理', '', '0', '0.195332810922397711.195332810922397710', '1', '188696496605106242', '1', '', '1', '', '', '0', '0', '', '2019-08-02 15:08:01', '2019-08-02 15:07:58', '');
INSERT INTO `sys_acl` VALUES ('195332810922397709', 'e88c27d65f4a4e75bd5842175bfd6371', '', '', '用户管理', 'AuthUser', '0', '0.195332810922397711.195332810922397710', '1', '188696496605106242', '1', '', '1', '', '', '0', '0', '', '2019-08-02 15:07:44', '2019-08-02 15:07:46', '');
INSERT INTO `sys_acl` VALUES ('195332810922397710', '61fcd23e1aad4a2581160de1fce545a7', '', '', '权限管理', '', '0', '0.195332810922397711', '1', '61fcd23e1aad4a2581160de1fce545a7', '0', '', '1', '', '', '0', '0', '', '2019-08-02 15:07:11', '2019-08-02 15:07:12', '');
INSERT INTO `sys_acl` VALUES ('195332810922397711', '61fcd23e1aad4a2581160de1fce545a6', '', '', '后台管理模块', '', '0', '0', '1', '188696496605106244', '-1', '', '1', '', '', '0', '0', '', '2019-08-02 15:04:14', '2019-08-02 15:04:16', '');

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
  `flag` int(1) NOT NULL DEFAULT 1 COMMENT '处理状态 0 已处理 1未处理',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '',
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
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0启用  1禁用',
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
  `flag` int(1) DEFAULT NULL COMMENT '状态 0启用  1禁用',
  `create_time` datetime DEFAULT NULL COMMENT '登录时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('187646644395839488', 'a3f689d278364cc5a69d409dff4c6326', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:33:44', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187646839196094464', '06481702693940ac984df8ed90361b97', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:34:36', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187647636403261440', '23d870f9e7a04e359d426645ef5e270d', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:38:01', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187647759900348416', '1a4293b28c2e4039b9de7d68cb6d0fea', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:38:15', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187647833896259584', 'f5f34b13267d4850b16d89b1ac18497d', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:38:30', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187647839583735808', 'b24829545ddc4fafa4599728d85474b1', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:38:32', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187649755432751104', '7f5c1bc132d647f7a68040f56508c9ad', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:46:05', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187649863322832896', '7d1ded67ab77428aa996078f506986bb', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:46:31', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187649894301962240', '94708631250e44da86ed1cdc892b385b', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:46:38', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187649898773090304', 'e331b5b5661d40c4927046055e5b44d8', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:46:40', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187651938232438784', '51a3bf683de24bf189b8de507f4d2855', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:54:46', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187651957702397952', 'e2961f28e98f48828f3e1dd75622cf3a', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:54:50', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187651962110611456', '41f3f8e90075452b8c2064b30bfde9ee', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:54:51', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652005072867328', '77c2267ca25443efb6302885705939a6', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:02', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652009866956800', '72852bb8a25849dca43f8620f552788b', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:03', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652014229032960', 'fe58e40b187449dab69fae6f02134811', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:04', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652021162217472', '43a35cdbc55a44f39459737c87f1315e', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:06', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652025121640448', 'aff303a659f34f27b2a9062441770b6a', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:07', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652029504688128', 'cdaa264588494a68be39a9da6d221806', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:08', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652032579112960', 'a12d8f9f9d1c4ce1a9f1105ef347ba74', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:08', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652035452211200', 'e2bfc82503114698b9cf8e1df0d3f5e7', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:09', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652040279855104', '3e3f2a4f2df841debc8c82ac750d250c', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:10', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652043509469184', 'acb31aa5cbe0400792c29a45419dc12f', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:11', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652046403538944', 'b0d5e0a714644031a1409f9d8b5d5c61', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:12', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652049939337216', '9976322bcdb047c0a0dc01a4e9cb72a0', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:55:12', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187652652887314432', '57c8129217e44dbc8d25cba4d0ae130d', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-30 17:57:36', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187738924628709376', '31950605c38d48d6bd5dd8f711d4678a', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-30 23:40:25', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187884344373809152', '3b31dadd543745afac586e163097846d', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:18:16', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187884506164891648', 'e1df8615193a470390dd8ff84099e244', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:18:54', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187884617729183744', '6351b5cfb57448778ce4137e7adebaae', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:19:21', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187884712709197824', '9e1e7bcdd691493fbd2f3ef8427cd653', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:19:44', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187884747337371648', '92f02f5d4c7c49abb4796dfa72ce9a8e', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:19:52', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187884752253095936', '3a4bb73a829744fa993315498159b4d3', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:19:53', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187884756665503744', '4716afec5cb14c66af9e8a140c7e9ad4', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:19:54', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187884760918528000', '42344c1021f848a78a0d24f5ecce3503', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:19:55', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187886285069881344', '18dd1343fc894753adbd6ea2c490e847', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:25:59', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187886318120996864', 'c9ff30d3178f4c6daa7f869d9c8ce908', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:26:06', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187886345002291200', 'de13bdf3b9054baa8a27e5b4c1d1f5d3', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:26:13', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187890189933350912', 'efd37f06c7c64126bff43f0e630b7635', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:41:30', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187892147712823296', '75efb0f9b0224e4b97ca1cdc58641a0c', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 09:49:16', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187895059096670208', 'd4aa29810b794600aa6558a0d1e9c8b4', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 10:00:51', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187905757667266560', 'e1447d05c9e842e789fe3eca7907abf4', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 10:43:24', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187905997111693312', 'b0ea29a4ef794d589fa5f687ef097daa', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 10:44:19', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187906120143212544', '0a43598bf3a44d45a7661e29014a0d6f', '1', '李杰', 'momo', '', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 10:44:48', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187906419364859904', '467064e4303140e185a5451f4379570c', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 10:45:59', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187906596897165312', 'da5a174b02e643fd952812ade983bac8', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 10:46:41', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187911070642475008', 'f5fa6a5680324d31a8cab44a31e1332b', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 11:04:29', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187911278558318592', '587fdde45da8462382e63566eb59dbd9', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 11:05:17', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187911589423353856', 'c6e0a5ac8eec4856bc44a07706dbd449', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 11:06:32', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187911781878992896', '300143979c0e41f193974ebf9dff53f2', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 11:07:18', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187912357769515008', 'f358a0cd8c6c47f48ca2d5ebc194d40a', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 11:09:35', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187913249654706176', '705effdbb8584b37af74622a7a7781c6', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 11:13:08', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187913406794305536', '161331e5985e4c918f57da6d93794a59', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 11:13:45', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187914766549913600', '5362872b95c94908810563e3d3ddd372', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 11:19:09', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187915425508626432', 'deef32e7658441e18cce15b000022181', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 11:21:46', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187915649362825216', '61b35d7ac392455a9b335157a06d5644', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 11:22:39', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187915667419303936', 'b177165fc9e44156a4500ccc29fa3c53', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 11:22:44', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187918687855054848', '5146fff7c5ca4fb49952590b95f08e46', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 11:34:44', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187919481287348224', '456d987f2e454a649f39bdcefeaed094', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 11:37:53', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187928856861216768', '34515992cfd0466182f4f1c2c99bdea3', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 12:15:08', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187935669853753344', 'c34475cb06eb4f36b5f2256a8c51621b', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 12:42:13', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187937338431770624', 'a466929a829d43c2a850dcc4b3314876', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 12:48:51', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187937899042443264', 'a4192e5b347645739e9446a6974fece2', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 12:51:04', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187938047151706112', 'd881801046d5431699cce3fcff12af45', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 12:51:40', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187938200810033152', 'dc8b924a324742b1a9bdc54c6fa50c01', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 12:52:16', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187938262797651968', 'ca87ece38466470daf3ea3d69b826700', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 12:52:32', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187938493501149184', 'f10e4a22be8f48929ffe456d5262b268', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 12:53:26', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187938682840420352', 'b5ccb2a8a7ae47719c492f8bc390988c', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 12:54:11', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187939688244449280', 'f2745884ecb742f98865ea70ccdef1d9', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 12:58:11', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187939710021275648', '6827d847a120403e8bc1a2ccb490a47b', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 12:58:16', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187941009144025088', 'be802fd35b084d1ba848a823c94a01c5', '1', '李杰', 'momo', '192.168.20.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:03:26', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187941364833587200', '4d630a30567a43188bdf26aec6c20489', '1', '李杰', 'momo', '192.168.20.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:04:50', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187941844515164160', '13d1a4cbbe19473184251bfe48da0ad1', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:06:45', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187941983594090496', '32af664736e94b6e9b28b75bd9397bb7', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:07:18', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187942088338444288', '54c3b9291fc645dda2502daf4434592f', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:07:43', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187942114313768960', '58084e4952c448e3835beb08198419f3', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:07:49', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187942525389115392', 'f2e353ac179a487dbd6f043a4b58a666', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:09:27', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187942736459075584', 'a7d1ec8b1e4148c19b87d2799331b0af', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:10:18', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187942815974690816', '7a84abf9845645c284bb30b52ba49357', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:10:36', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187942888137691136', '51d60dda950c4cfeb5dea678ca3c3996', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:10:54', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187942925173395456', 'a4c313aa46b54b1ea67a23ea7e35207d', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:11:02', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187942957796691968', '40b132744e64499580a3d7236e9ac674', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 13:11:10', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187943155797200896', 'e02535e655724990adf49f1dce883b29', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:11:57', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187943528977010688', '53707505989e43f798217eba47382250', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:13:28', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187943632815394816', 'c69c229a7b1e48308f4496fe507be7f0', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:13:51', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187943637567541248', 'dc27b7da28254b37b0ea780d1b94c021', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:13:52', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187944620179722240', '6edf3ab7e53c4dfea2f81dcbffbd4fe3', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:17:47', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187944633546969088', '0460a1d2b6a84f70b0333b2aa119e7d5', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:17:50', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187947027404034048', '1f8a62311fc143f4a44f7fded9ec7b59', '1', '李杰', 'momo', '', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:27:21', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187948674901479424', '2dd875305b61481295366975f33745a7', '1', '李杰', 'momo', '192.168.1.233', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 13:33:54', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187963592866402304', '383faeb7ab094f9db24cfb108d53e270', '1', '李杰', 'momo', '', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 14:33:10', null, null, null);
INSERT INTO `sys_login_log` VALUES ('187970198479966208', '44b11c4045ae4cb9aa961d11a04ab9db', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-07-31 14:59:25', null, null, null);
INSERT INTO `sys_login_log` VALUES ('188086105294376960', '24637e91b80045c486855752c4fff08c', '1', '李杰', 'momo', '192.168.31.74', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 22:39:59', null, null, null);
INSERT INTO `sys_login_log` VALUES ('188087078091886592', 'a0de747bb3fa45e281b0ada5d2295774', '1', '李杰', 'momo', '192.168.31.74', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 22:43:51', null, null, null);
INSERT INTO `sys_login_log` VALUES ('188087855606796288', '776b2949a8a9479198dfc292c4333cde', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 22:46:57', null, null, null);
INSERT INTO `sys_login_log` VALUES ('188089657815339008', 'b9fbc4edae134ae8bfee4e8a10788377', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 22:54:06', null, null, null);
INSERT INTO `sys_login_log` VALUES ('188091062475165696', 'b84979be3c064cae8ccfc40aa594a1fd', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 22:59:41', null, null, null);
INSERT INTO `sys_login_log` VALUES ('188091262593798144', '3b1bf422e70c4ef098e7671db73dd4c3', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 23:00:29', null, null, null);
INSERT INTO `sys_login_log` VALUES ('188091419343327232', '2b1c0e69daee4c539c275d3dbf83ddbb', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 23:01:06', null, null, null);
INSERT INTO `sys_login_log` VALUES ('188091632342667264', 'c1c1f6f7b20e4cc1b7d27751e47c9eac', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 23:01:57', null, null, null);
INSERT INTO `sys_login_log` VALUES ('188091742086631424', '81675ea070704a97b5abe2570af04897', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-07-31 23:02:23', null, null, null);
INSERT INTO `sys_login_log` VALUES ('188093985124913152', '14def425eb044b6eafe47b8f74d348c8', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-07-31 23:11:18', null, null, null);
INSERT INTO `sys_login_log` VALUES ('188244645355065344', '32a8a9411d1346fab714f5eccd8ad53d', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-01 09:09:58', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188248847208812544', '886627881e36497aaac1a6c244dd03f3', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-01 09:26:40', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188249213765816320', '2ee2b104d5b7405e8c880e7fdc0ebd41', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-01 09:28:07', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188381782209597440', '5cc9a0b69dfa4db6b0f87c9e45f5713b', '1', '李杰', 'momo', '', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-01 18:14:54', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188381834667757568', '9146b6c3c1c4431a8bcd9a91e853aa13', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-01 18:15:07', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188597556991889408', '9e9909a83cbc4c3dbac019b8d2bbada5', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 08:32:19', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188597627523305472', '021562025b0a445fb205fa0e8a592ca5', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-02 08:32:36', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188597722390073344', '1a0d240ae6454ff8a2aacaed701cb21d', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 08:32:58', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188598278768693248', '7702766190034ce1a54fec54d4fe09a4', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 08:35:11', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188598375699058688', 'de3b9f3aed0a4c03a52c4e1a256e473e', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 08:35:34', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188598496587288576', 'a80e97b6390942cda1652c7233840195', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 08:36:03', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188598637687869440', '7965637d650e4aea8adf709111d23b03', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 08:36:37', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188598761877016576', '6e585b1e72c14650bdcdfef4682dc09b', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 08:37:06', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188599610992889856', '6cac36ca731d4e8aa25082e3fcbc49d2', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 08:40:29', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188605493323894784', 'bf7a591d60074c12974ea72b2de02027', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 09:03:51', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188691118664323072', '4f8afa09c81f4090a379ff1b0a766b76', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-02 14:44:07', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188691161878237184', '7f12195f17c64416a6f0e18caef36804', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-02 14:44:16', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188695323642826752', 'b2caabd8026a4c6b9a054b0fd789496d', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 15:00:49', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188702108453834752', '0e0d1cbf59554a9bb97fbf076f6deabb', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 15:27:46', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188702147905458176', '6664cc3d11104f6fb9c7e71efd059422', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-02 15:27:55', '', null, '');
INSERT INTO `sys_login_log` VALUES ('188991367354126336', '766bcd1184bd493182ce10463dfb5e5f', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-03 10:37:11', '', null, '');
INSERT INTO `sys_login_log` VALUES ('189411005506392064', '693ad191b12d4feca2ecbe191394d526', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-04 14:24:40', '', null, '');
INSERT INTO `sys_login_log` VALUES ('189720243994759168', '0015f9e2a80f4c3cba4eef5c7899c4d3', '1', '李杰', 'momo', '', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-05 10:53:28', '', null, '');
INSERT INTO `sys_login_log` VALUES ('189901383187697664', 'cb479170d5ec40f2ba24a978ff0e1705', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-05 22:53:15', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190052891036356608', '21e21f5bb51d40d1b1064dd90587efa5', '1', '李杰', 'momo', '', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-06 08:55:18', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190123054075088896', '54af834a5a464236b3102a4847e58f42', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-06 13:34:06', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190253320944881664', '46e8f15a4a7e401dbeadf65e55d5c83e', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-06 22:11:44', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190481593255727104', '5f30e57f6a7b4b2385d1a3dac39da806', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 13:18:48', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190482254512918528', '09cff398f96045b0a6be232d351d0c65', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 13:21:26', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190485987552530432', 'a11460c4d8424d09a024771cc09f4aab', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-07 13:36:16', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190494573842272256', '3fd7a07795654e63b888ed7ecd8c07e7', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:10:23', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190494665802387456', '7888227c13b2461b85c3d70696058ddb', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:10:45', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190494880282316800', '4e860b6d922f47d2add0ad259c0c7777', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:11:36', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190495151746060288', '07a4d8f1890945efb91d33cc5b5d2d79', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:12:41', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190495263016751104', 'f258779072e64de2b15a421e931c40e9', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:13:07', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190495488452202496', 'ee1a043a2da84b68ba4d35cb2a4c4928', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:14:01', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190498819862040576', 'b222c673dbfa4cb4923ba03d18542faa', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:27:15', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190498952091668480', '6ee8a921e643472d9f17a971b218c81e', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:27:47', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190499344879849472', 'ece9a289e77d40648121def870334593', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:29:20', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190499584626266112', 'f4f199647c944421bfcea1856569c51a', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:30:18', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190501492912951296', '5767848c15b940b19343d4422b5d6b9b', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:37:53', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190502429035466752', 'a97855f4d5fd4d808baab3c9b96c6b26', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:41:36', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190503984564408320', '9cb0b93eadb64f0ea350375023811090', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:47:47', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190504572509360128', 'a6bea7901e4f451385e39154ea28fa53', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:50:07', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190504605828911104', 'ee93a0c12adb4bd1a18756eef3f7d1d2', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:50:15', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190504809055522816', '607847b8108444f29bf94498a1acab22', '1', '李杰', 'momo', '192.168.20.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:51:03', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190504950177075200', '3c4e42b0f67142a4809d3bcb1d034547', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:51:37', '', null, '');
INSERT INTO `sys_login_log` VALUES ('190506758094065664', '66a469cadb7c422ba7aea7f9d542adf8', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-07 14:58:48', '', null, '');
INSERT INTO `sys_login_log` VALUES ('191344365497618432', '83183e913ec444d6b8713f97f7b869a3', '1', '李杰', 'momo', '192.168.139.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-09 22:27:09', '', null, '');
INSERT INTO `sys_login_log` VALUES ('191344385168904192', 'f04dfe47624c494fa833c67418117327', '1', '李杰', 'momo', '192.168.139.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-09 22:27:14', '', null, '');
INSERT INTO `sys_login_log` VALUES ('191706821789093888', '67e8fe514bc24daa95fca9d15dec7431', '1', '李杰', 'momo', '', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-10 22:27:25', '', null, '');
INSERT INTO `sys_login_log` VALUES ('191722405243260928', '09c56609dbd9401fae213d2516df16d8', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-10 23:29:21', '', null, '');
INSERT INTO `sys_login_log` VALUES ('191886759141969920', '05746f0ae13c4b3a8ef532efb5361fcf', '1', '李杰', 'momo', '192.168.139.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-11 10:22:26', '', null, '');
INSERT INTO `sys_login_log` VALUES ('191900339182637056', '251ef4646ff849c6b5c0c93fe7329617', '1', '李杰', 'momo', '192.168.139.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-11 11:16:24', '', null, '');
INSERT INTO `sys_login_log` VALUES ('192436580563161088', '3d7679028c5143a898e9670449012676', '1', '李杰', 'momo', '192.168.139.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-12 22:47:14', '', null, '');
INSERT INTO `sys_login_log` VALUES ('193017587511201792', 'c7c2649aa92948b5a49c39ac42af9fc1', '1', '李杰', 'momo', '192.168.139.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-14 13:15:56', '', null, '');
INSERT INTO `sys_login_log` VALUES ('193043461203693568', '75e7cf95aea34d25849f92077cc5e053', '1', '李杰', 'momo', '192.168.139.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-14 14:58:45', '', null, '');
INSERT INTO `sys_login_log` VALUES ('193386254958727168', 'ab4d173854af418ea47df74cc20edfd0', '1', '李杰', 'momo', '', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-15 13:40:54', '', null, '');
INSERT INTO `sys_login_log` VALUES ('193742973614297088', '1aeb666204ef44f9a8d2c507096ab034', '1', '李杰', 'momo', '192.168.139.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-16 13:18:22', '', null, '');
INSERT INTO `sys_login_log` VALUES ('193802697932148736', '0685db451dbb406991394ae872c511b1', '1', '李杰', 'momo', '192.168.139.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-16 17:15:41', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194107942499389440', '69f98dfd413b4dc49f40a003d643c0c7', '1', '李杰', 'momo', '192.168.139.1', '1', 'Unknown', 'Unknown', 'Unknown', '1', null, '2019-08-17 13:28:37', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194163284792971264', '229e0ac838a5478a83e35c9432e0044c', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-17 17:08:32', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194166525983330304', '2c49b9b71e06488cad2431b1ad67f4f1', '1', '李杰', 'momo', '', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-17 17:21:25', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194170272486658048', 'd4efa34dff8344da8d87a7a069e47d92', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-17 17:36:18', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194171333502963712', '8c896427c83843b1a1322fb21b71d723', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-17 17:40:31', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194171746121814016', '973c400d6340433f97a441a350c0a90b', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-17 17:42:09', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194171887423721472', 'a0b7cbd235ea453ebe9204e58dbec847', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-17 17:42:43', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194172129003048960', '25476a9ec4e54aa188da1e5da36142a4', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-17 17:43:40', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194172487746064384', '3792b377f6014435a8fd1f0d0ee04d03', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-17 17:45:06', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194172886905393152', 'b5b08a85570d45b297d90b548c3b922f', '1', '李杰', 'momo', '', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-17 17:46:41', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194909580818518016', '3297d57892c14fd289495c09852b91c4', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-19 18:34:03', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194910140833599488', '42b5b728446a40148f1f32dfb1b14fc2', '194910014362750976', '测试', 'test@qq.com', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Firefox', '1', null, '2019-08-19 18:36:16', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194912192410292224', '5f17868d16554685b030e57410a5e25f', '194910014362750976', '测试', 'test@qq.com', '127.0.0.1', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-19 18:44:25', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194953567973543936', '0b8e3debcde8463b8d6c338cf97f15b4', '194910014362750976', '测试', 'test@qq.com', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-19 21:28:50', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194953679953072128', '954fdc98a3b24cb69530373c15116530', '194910014362750976', '测试', 'test@qq.com', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Firefox', '1', null, '2019-08-19 21:29:17', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194954444302061568', '3f17aeee79ea4c6abf3c7394086ead3a', '194910014362750976', '测试', 'test@qq.com', '', '1', 'Windows 8.1', 'Windows 8.1', 'Firefox', '1', null, '2019-08-19 21:32:19', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194958542611025920', 'da60d59654b9417fbd144444849af2a3', '194910014362750976', '测试', 'test@qq.com', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-19 21:48:36', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194958806076231680', '0432a1aa29c047d9b353a22fa9c5eeb6', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Firefox', '1', null, '2019-08-19 21:49:39', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194962651678380032', '33b76c45ac9c4935b478b9c905fc9be1', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-19 22:04:56', '', null, '');
INSERT INTO `sys_login_log` VALUES ('194973411519369216', '53458e0a127745168bc1cc546219f39b', '1', '李杰', 'momo', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-19 22:47:41', '', null, '');
INSERT INTO `sys_login_log` VALUES ('195158425016078336', 'd1970bfd216e49e4a546bc4e732a462b', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-20 11:02:52', '', null, '');
INSERT INTO `sys_login_log` VALUES ('195193033778663424', 'f3a63624411348e1aa13e598757496f4', '1', '李杰', 'momo', '127.0.0.1', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-20 13:20:23', '', null, '');
INSERT INTO `sys_login_log` VALUES ('195222627592310784', '81b24a7131b3476fbefa1465cf5827f1', '1', '李杰', 'momo', '', '1', 'Windows 8.1', 'Windows 8.1', 'Chrome', '1', null, '2019-08-20 15:17:59', '', null, '');
INSERT INTO `sys_login_log` VALUES ('195330534442930176', 'c4f7d010ffd640e9a24c0858de584586', '1', '李杰', 'momo', '', '1', '未知设备', 'Unknown', 'Unknown', '1', null, '2019-08-20 22:26:46', '', null, '');
INSERT INTO `sys_login_log` VALUES ('195330680819945472', 'fd3c1a4cc6fd44fa98667d074dada4ba', '194910014362750976', '测试', 'test@qq.com', '127.0.0.1', '1', 'Windows 8.1', 'Windows 8.1', 'Firefox', '1', null, '2019-08-20 22:27:21', '', null, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL COMMENT '角色id',
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `sys_role_type` int(1) NOT NULL DEFAULT 1 COMMENT '角色的类型，0：管理员(老板)，1：管理员(员工) 2其他',
  `tenant_id` bigint(20) NOT NULL DEFAULT -1 COMMENT '租户id',
  `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '备注',
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '是否被禁用  0否 1禁用',
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
INSERT INTO `sys_role` VALUES ('194913401527472128', '9d287ba9c807413e83a62f9dc450d7c1', '测试角色', '1', '1', '', '0', '0', '李杰', '2019-08-19 18:49:14', '2019-08-20 15:35:49', '李杰');

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
  `del_flag` int(1) NOT NULL DEFAULT 0 COMMENT '删除状态(0-正常，1-删除)',
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '是否被禁用  0否 1禁用',
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
INSERT INTO `sys_role_acl` VALUES ('195226006934654976', 'c6d1502b995e450e8f9a1203003c83bb', '194913401527472128', '5', '1', '1', '0', '0', '李杰', '2019-08-20 15:31:24', '2019-08-20 15:31:24', '李杰');
INSERT INTO `sys_role_acl` VALUES ('195226006943043584', '219c1bbfe0a14dadad52e72312f99630', '194913401527472128', '54', '1', '1', '0', '0', '李杰', '2019-08-20 15:31:24', '2019-08-20 15:31:24', '李杰');
INSERT INTO `sys_role_acl` VALUES ('195226006943043585', '17e858b0afa54f65b91da7e7ee3ef31c', '194913401527472128', '53', '1', '1', '0', '0', '李杰', '2019-08-20 15:31:24', '2019-08-20 15:31:24', '李杰');
INSERT INTO `sys_role_acl` VALUES ('195226006943043586', '06cd7a30b50541a985e28a14a7400f44', '194913401527472128', '4', '1', '1', '0', '0', '李杰', '2019-08-20 15:31:24', '2019-08-20 15:31:24', '李杰');
INSERT INTO `sys_role_acl` VALUES ('195226006943043587', 'd945a392b7f34dc5903340452aed3dc0', '194913401527472128', '1', '1', '1', '0', '0', '李杰', '2019-08-20 15:31:24', '2019-08-20 15:31:24', '李杰');

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
  `flag` int(1) DEFAULT 0 COMMENT '状态 0启用  1禁用',
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
INSERT INTO `sys_role_user` VALUES ('195222839358525440', '', '194913401527472128', '194910014362750976', '1', '0', '0', '李杰', '2019-08-20 15:18:49', '2019-08-20 15:18:49', '李杰');

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
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0启用  1禁用',
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
  `flag` int(1) DEFAULT 0 COMMENT '是否被禁用  0否 1禁用',
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
INSERT INTO `sys_user` VALUES ('2', 'test', '691d837480824acfa6aad086898026d0', '15050451501', '15050451501@163.com', '', null, '', null, '', null, '1', null, '0', '0', '李杰', '2019-04-21 12:20:18', '2019-08-19 18:35:11', '李杰');
INSERT INTO `sys_user` VALUES ('3', '啊啊', 'd04b26d876574a3998917e65739ac984', 'a', 'a@aa.com', '', null, '', null, '', null, '1', null, '1', '0', '李杰', '2019-04-23 21:21:05', '2019-08-20 15:23:37', '李杰');
INSERT INTO `sys_user` VALUES ('4', 'aaaa', '7575a0f64189451ba6b7aac8da797a83', '1', '1@qq.com', '', null, '', null, '', null, '1', null, '1', '0', '李杰', '2019-04-24 21:57:54', '2019-08-20 15:23:33', '李杰');
INSERT INTO `sys_user` VALUES ('190262632786300928', '新增用户', 'e41cf73639c047a0aede6ecb2f1ae9f4', '', '', '', null, '', null, '', null, '1', '我是备注', '0', '0', '李杰', '2019-08-06 22:48:44', '2019-08-20 15:20:09', '李杰');
INSERT INTO `sys_user` VALUES ('190263331309883392', '新增用户', 'da9246da45924aecb67cb00c581aaa13', '', 'test@163.com', '', null, '', null, '', null, '1', '我是备注', '1', '0', '李杰', '2019-08-06 22:51:30', null, '李杰');
INSERT INTO `sys_user` VALUES ('190263921813360640', '用户编辑', '8767f0bff61847cb945d8c5567c5f37c', '', 'test@163.com', '', null, '', null, '', null, '1', '我是备注', '1', '0', '李杰', '2019-08-06 22:53:51', '2019-08-07 17:09:22', '李杰');
INSERT INTO `sys_user` VALUES ('194910014362750976', '测试', 'fe62c15190ae41c5aabe044205908b83', '', 'test@qq.com', '', null, '', null, '', null, '1', '我是测试备注', '0', '0', '李杰', '2019-08-19 18:35:46', '2019-08-20 15:31:09', '李杰');

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
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0启用  1禁用',
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
INSERT INTO `sys_user_account_pwd` VALUES ('190263922811604992', '8767f0bff61847cb945d8c5567c5f37c', 'test@163.com', 'ea2ebf0029902281b3090e84e2aa8088383e0bbed9a8288ad379c26b41357c7dc0e295194ef1863b44e0c499804d45f26331e761212c6f49e6433db257eafd92', '1', '4d1f5913a30f4467a335d89982903090', '190263921813360640', '1', '我是备注', '1', '0', '李杰', '2019-08-06 22:53:51', '2019-08-06 22:53:51', '李杰');
INSERT INTO `sys_user_account_pwd` VALUES ('194910014664740864', '0203294c607d4cbc91c6a00da6cdf82a', 'test@qq.com', '3871b3ff4b3c3568fcbd4201f5a1ea1f50418ff092d623ef7df62c88e1f2af869b7a6b262015a76239285537d28287f86d1f87e7d8451a938d0419680837340f', '1', 'a2d7fae58c8b4149a852fc547c58f645', '194910014362750976', '1', '我是测试备注', '0', '0', '李杰', '2019-08-19 18:35:46', '2019-08-19 18:35:46', '李杰');

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
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '是否被禁用  0否 1禁用',
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
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0启用  1禁用',
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
INSERT INTO `sys_user_group` VALUES ('195193136069349376', 'a64fe4a034654af89fe6c845eae73159', '艾欧尼亚', '2019-01-20 13:20:33', '2021-01-04 02:02:00', '0', '0', '0', '', '', '1', '艾欧尼亚', '0', '李杰', '2019-08-20 13:20:47', '2019-08-20 15:36:01', '李杰');

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
