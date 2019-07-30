/*
Navicat MySQL Data Transfer

Source Server         : big
Source Server Version : 50505
Source Host           : 111.231.117.239:3306
Source Database       : system-core

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-04-25 10:59:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `sys_acl_uuid` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_acl_action` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '按钮动作类型(交给前端处理）',
  `sys_acl_router` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '所属页面(交给前端处理)',
  `sys_acl_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `sys_acl_module_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '权限所在的权限模块id',
  `sys_acl_permission_type` bigint(20) NOT NULL DEFAULT 1 COMMENT '菜单系统类型 1 系统管理 2资产管理',
  `sys_acl_url` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '请求的url, 可以填正则表达式',
  `sys_acl_type` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '2' COMMENT '类型，1：菜单，2：按钮，3：其他',
  `sys_acl_icon` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '图标class',
  `sys_acl_seq` int(11) NOT NULL DEFAULT 1 COMMENT '权限在当前模块下的顺序，由小到大',
  `sys_acl_status` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态 0启用  1禁用',
  `remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '备注',
  `del_flag` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人',
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '页面名称(前端控制)',
  `sys_acl_code` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '权限码',
  PRIMARY KEY (`id`),
  KEY `sys_acl_uuid` (`sys_acl_uuid`) USING BTREE,
  KEY `sys_acl_module_id` (`sys_acl_module_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限点表';

-- ----------------------------
-- Records of sys_acl
-- ----------------------------
INSERT INTO `sys_acl` VALUES ('1', 'c70a20be0a2e4a1889af5d69263dc972', 'list', 'AuthUser', '用户列表', '3', '1', '1', '1', '', '1', '0', '', '0', '李杰', '2019-04-13 11:22:15', '2019-04-13 11:22:17', '李杰', 'AuthUser', '');
INSERT INTO `sys_acl` VALUES ('2', '83d9a58f6fd14d4db74817fed97475dc', 'add', 'AuthUser', '用户新增', '3', '1', '2', '2', '', '1', '0', '', '0', '', '2019-04-13 11:24:21', '2019-04-13 11:24:23', '', '', '');
INSERT INTO `sys_acl` VALUES ('3', '497d93f779ce43568c46f81ddca3de60', 'edit', 'AuthUser', '用户编辑', '3', '1', '3', '2', '', '1', '0', '', '0', '', '2019-04-13 11:25:13', '2019-04-13 11:25:15', '', '', '');
INSERT INTO `sys_acl` VALUES ('4', '9374d79237fa4bcdae03365615d5c6b2', 'status', 'AuthUser', '用户状态', '3', '1', '4', '2', '', '1', '0', '', '0', '', '2019-04-13 11:26:00', '2019-04-13 11:26:02', '', '', '');
INSERT INTO `sys_acl` VALUES ('5', '5f2056a6a51e441bb904dc6709a817e4', 'list', 'AuthMenu', '菜单列表', '4', '1', '5', '1', '', '1', '0', '', '0', '', '2019-04-13 11:26:58', '2019-04-13 11:27:00', '', 'AuthMenu', '');
INSERT INTO `sys_acl` VALUES ('6', '39dce012e9a34cfdb6032f2eb6639990', 'add', 'AuthMenu', '菜单新增', '4', '1', '6', '2', '', '1', '0', '', '0', '', '2019-04-13 11:28:05', '2019-04-13 11:28:08', '', '', '');
INSERT INTO `sys_acl` VALUES ('7', 'd048e8e849c64e68a0b68915c6ef71ef', 'edit', 'AuthMenu', '菜单编辑', '4', '1', '7', '2', '', '1', '0', '', '0', '', '2019-04-13 11:29:17', '2019-04-13 11:29:19', '', '', '');
INSERT INTO `sys_acl` VALUES ('8', 'a2e4ba37251e4468935d17352cc111b5', 'status', 'AuthMenu', '菜单状态', '4', '1', '8', '2', '', '1', '0', '', '0', '', '2019-04-13 11:29:52', '2019-04-13 11:29:55', '', '', '');
INSERT INTO `sys_acl` VALUES ('9', '2c0208809d974940b284ce4522d3e4dd', 'list', 'AuthRole', '角色列表', '5', '1', '9', '1', '', '1', '0', '', '0', '', '2019-04-13 11:30:28', '2019-04-13 11:30:30', '', 'AuthRole', '');
INSERT INTO `sys_acl` VALUES ('10', 'dfaec89540004be396b4956f8a7afe01', 'add', 'AuthRole', '角色新增', '5', '1', '10', '2', '', '1', '0', '', '0', '', '2019-04-13 11:30:49', '2019-04-13 11:30:51', '', '', '');
INSERT INTO `sys_acl` VALUES ('11', 'd8c65104a60c47da90172f4df5f5bf0d', 'edit', 'AuthRole', '角色编辑', '5', '1', '11', '2', '', '1', '0', '', '0', '', '2019-04-13 11:31:07', '2019-04-13 11:31:10', '', '', '');
INSERT INTO `sys_acl` VALUES ('12', 'e2096d71322b4569aa7557ad683d0b61', 'status', 'AuthRole', '角色状态', '5', '1', '12', '2', '', '1', '0', '', '0', '', '2019-04-13 11:31:28', '2019-04-13 11:31:31', '', '', '');
INSERT INTO `sys_acl` VALUES ('13', '161e5cafd28749e88a891d3d7c74ac11', 'list', 'AuthDept', '部门列表', '6', '1', '13', '1', '', '1', '0', '', '0', '', '2019-04-13 11:31:58', '2019-04-13 11:32:00', '', 'AuthDept', '');
INSERT INTO `sys_acl` VALUES ('14', 'e110e8e9a8334cc7967f2c1c1b2e1cd5', 'add', 'AuthDept', '部门新增', '6', '1', '14', '2', '', '1', '0', '', '0', '', '2019-04-13 11:32:15', '2019-04-13 11:32:17', '', '', '');
INSERT INTO `sys_acl` VALUES ('15', '1e70ec5ae18b495da661f6fd4ce9b20f', 'edit', 'AuthDept', '部门编辑', '6', '1', '16', '2', '', '1', '0', '', '0', '', '2019-04-13 11:32:31', '2019-04-13 11:32:33', '', '', '');
INSERT INTO `sys_acl` VALUES ('16', '91a7d8d84394425abd5095a24fff6db2', 'status', 'AuthDept', '部门状态', '6', '1', '17', '2', '', '1', '0', '', '0', '', '2019-04-13 11:32:49', '2019-04-13 11:32:50', '', '', '');
INSERT INTO `sys_acl` VALUES ('17', '1b8ac96d955e4e78b8e0820b6e2fd890', 'list', 'AuthVip', '企业列表', '8', '1', '18', '1', '', '1', '0', '', '0', '', '2019-04-15 11:09:36', '2019-04-23 20:31:17', '李杰', 'AuthVip', '');
INSERT INTO `sys_acl` VALUES ('22', '1f1dda57bf1f485eb31dbf4eb9b13d99', 'edit', 'AuthVip', '企业编辑', '8', '1', '按钮3', '2', '', '1', '0', '', '0', '李杰', '2019-04-16 13:33:54', '2019-04-16 14:16:37', '李杰', '', '');
INSERT INTO `sys_acl` VALUES ('23', 'a4b649e5964b4a5aae79d466034914ec', 'index', 'index', '首页列表', '10', '1', '/index', '1', '', '1', '0', '', '0', '李杰', '2019-04-22 16:58:48', '2019-04-22 16:58:48', '李杰', 'Dashboard', '');
INSERT INTO `sys_acl` VALUES ('28', 'cdc6340a2aaf4e56b350ba3eb657fa1b', 'author', 'AuthRole', '角色授权', '5', '1', 'mmp', '2', '', '1', '0', '', '0', '李杰', '2019-04-23 20:42:45', '2019-04-23 20:42:45', '李杰', '', '');
INSERT INTO `sys_acl` VALUES ('29', '1f1dda57bf1f485eb31dbf4eb9b13dmn', 'add', 'AuthVip', '企业新增', '8', '1', '29', '2', '', '1', '0', '', '0', '', '2019-04-24 15:38:11', '2019-04-24 15:38:14', '', '', '');
INSERT INTO `sys_acl` VALUES ('30', 'cdc6340a2aaf4e56b350ba3eb657df1b', 'author', 'AuthVip', '授权', '8', '1', '30', '2', '', '1', '0', '', '0', '', '2019-04-24 15:38:51', '2019-04-24 15:38:54', '', '', '');
INSERT INTO `sys_acl` VALUES ('31', 'e110e8e9a8334cdf967f2c1c1b2e1cd5', 'dept', 'AuthVip', '部门', '8', '1', '31', '2', '', '1', '0', '', '0', '', '2019-04-24 15:39:27', '2019-04-24 15:39:28', '', '', '');
INSERT INTO `sys_acl` VALUES ('32', 'e110e8e9a8334cc7967fmnjc1b2e1cd5', 'role', 'AuthVip', '角色', '8', '1', '32', '2', '', '1', '0', '', '0', '', '2019-04-24 15:39:51', '2019-04-24 15:39:53', '', '', '');
INSERT INTO `sys_acl` VALUES ('33', 'e110e8e9a8334ccdf67fmnjc1b2e1cd5', 'status', 'AuthVip', '状态', '8', '1', '33', '2', '', '1', '0', '', '0', '', '2019-04-24 15:40:11', '2019-04-24 15:40:14', '', '', '');
INSERT INTO `sys_acl` VALUES ('34', 'e110e8e9aui34cc7967fmnjc1b2e1cd5', 'detail', 'AuthVip', '详情', '8', '1', '34', '2', '', '1', '0', '', '0', '', '2019-04-24 15:40:39', '2019-04-24 15:40:41', '', '', '');
INSERT INTO `sys_acl` VALUES ('35', '497d93f779ce43568c46df1ddca3de60', 'detail', 'AuthUser', '详情', '3', '1', '35', '2', '', '1', '0', '', '0', '', '2019-04-24 15:45:11', '2019-04-24 15:45:13', '', '', '');
INSERT INTO `sys_acl` VALUES ('36', '1f1dda57bf1f485eb31dbfdfb9b13dmn', 'role', 'AuthUser', '角色', '3', '1', '36', '2', '', '1', '0', '', '0', '', '2019-04-24 15:45:54', '2019-04-24 15:45:55', '', '', '');
INSERT INTO `sys_acl` VALUES ('37', 'dffdda57bf1f485eb31dbfdfb9b13dmn', 'pwd', 'AuthUser', '修改密码', '3', '1', '37', '2', '', '1', '0', '', '0', '', '2019-04-24 15:47:47', '2019-04-24 15:47:49', '', '', '');
INSERT INTO `sys_acl` VALUES ('38', '1f1dda57bf1f485eb3mnbf4eb9b13d99', 'userlist', 'AuthVip', '企业用户列表', '8', '1', '39', '2', '', '1', '0', '', '0', '', '2019-04-24 17:17:32', '2019-04-24 17:17:34', '', '', '');
INSERT INTO `sys_acl` VALUES ('39', 'sf10e8e9aui34cc7967fmnjc1b2e1cd5', 'userRoleAuthor', 'VipUserList', '角色授权', '8', '1', '40', '2', '', '1', '0', '', '0', '', '2019-04-24 17:19:11', '2019-04-24 17:19:13', '', '', '');
INSERT INTO `sys_acl` VALUES ('40', '850e0fba11784add9f0de0d887ad4458', 'userRole', 'VipUserList', '企业用户角色', '8', '1', '41', '2', '', '1', '0', '', '0', '李杰', '2019-04-24 22:04:05', '2019-04-24 22:04:05', '李杰', '', '');
INSERT INTO `sys_acl` VALUES ('41', '560745db4324470380b3a475d49a1489', 'userStatus', 'VipUserList', '用户状态', '8', '1', '42', '2', '', '1', '0', '', '0', '李杰', '2019-04-24 22:05:08', '2019-04-24 22:05:54', '李杰', '', '');
INSERT INTO `sys_acl` VALUES ('42', 'bf73494c64ad4ce197fa82902e638eeb', 'userEdit', 'VipUserList', '用户编辑', '8', '1', '43', '2', '', '1', '0', '', '0', '李杰', '2019-04-24 22:05:42', '2019-04-24 22:05:42', '李杰', '', '');
INSERT INTO `sys_acl` VALUES ('43', 'adc20512d6b7489bb050b458bc7f088c', 'userDetail', 'VipUserList', '用户详情', '8', '1', '44', '2', '', '1', '0', '', '0', '李杰', '2019-04-24 22:06:36', '2019-04-24 22:06:36', '李杰', '', '');
INSERT INTO `sys_acl` VALUES ('44', '28be2ea876044c0ebf148b12731415e4', 'userPwd', 'VipUserList', '修改密码', '8', '1', '46', '2', '', '1', '0', '', '0', '李杰', '2019-04-24 22:07:08', '2019-04-24 22:07:08', '李杰', '', '');
INSERT INTO `sys_acl` VALUES ('45', 'mkbe2ea876044c0ebf148b12731415e4', 'productionList', 'Production', '产品提优列表', '9', '1', '46', '1', '', '1', '0', '', '0', '', '2019-04-25 09:36:58', '2019-04-25 09:37:00', '', 'Production', '');

-- ----------------------------
-- Table structure for sys_acl_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl_module`;
CREATE TABLE `sys_acl_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限模块id',
  `sys_acl_module_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_acl_module_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '权限模块名称',
  `sys_acl_module_parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '上级权限模块id',
  `sys_ac_modulel_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态 0启用  1禁用',
  `sys_acl_module_level` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '权限模块层级',
  `sys_acl_permission_type` bigint(20) NOT NULL DEFAULT 1 COMMENT '菜单系统类型 1 系统管理 2资产管理',
  `sys_acl_module_seq` int(11) NOT NULL DEFAULT 1 COMMENT '权限模块在当前层级下的顺序，由小到大',
  `sys_acl_module_icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '图标class',
  `sys_acl_module_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '权限模块码',
  `sys_acl_module_remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `sys_acl_module_uuid` (`sys_acl_module_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='权限模块表   引入权限模块就可以很容易把菜单层级定义出来，每个菜单项下面有哪些功能就可以在权限模块下面定义权限点，然后就可以根据每个人分配到的权限生成不同的基于权限的菜单';

-- ----------------------------
-- Records of sys_acl_module
-- ----------------------------
INSERT INTO `sys_acl_module` VALUES ('1', '61fcd23e1aad4a2581160de1fce545a6', '后台管理模块', '0', '0', '0', '1', '1', '', '', '', '0', '李杰', '2019-03-20 09:11:51', '2019-03-20 09:11:51', '李杰');
INSERT INTO `sys_acl_module` VALUES ('2', '61fcd23e1aad4a2581160de1fce545a7', '权限管理', '1', '0', '0.1', '1', '2', '', '', '', '0', '李杰', '2019-04-13 11:14:30', '2019-04-24 22:32:24', '李杰');
INSERT INTO `sys_acl_module` VALUES ('3', 'e88c27d65f4a4e75bd5842175bfd6371', '用户管理', '2', '0', '0.1.2', '1', '10', '', '', '', '0', '李杰', '2019-04-15 10:45:12', '2019-04-15 10:45:14', '李杰');
INSERT INTO `sys_acl_module` VALUES ('4', 'b593168f64724ef2aadb55e90bf2fe53', '菜单管理', '2', '0', '0.1.2', '1', '4', '', '', '', '0', '李杰', '2019-04-15 10:45:47', '2019-04-15 10:45:49', '李杰');
INSERT INTO `sys_acl_module` VALUES ('5', 'f2d1ea1a116343b6ba3910d3734cbe72', '角色管理', '2', '0', '0.1.2', '1', '5', '', '', '', '0', '李杰', '2019-04-15 10:46:23', '2019-04-15 10:46:20', '李杰');
INSERT INTO `sys_acl_module` VALUES ('6', 'afae14c9a9d843c6b865872abd75955f', '部门管理', '2', '0', '0.1.2', '1', '6', '', '', '', '0', '李杰', '2019-04-15 10:46:42', '2019-04-15 10:46:40', '李杰');
INSERT INTO `sys_acl_module` VALUES ('7', 'a', '模块编辑', '2', '0', '0.1.2', '1', '2', '', '', '', '0', '', '2019-04-16 09:09:51', '2019-04-16 09:46:05', '李杰');
INSERT INTO `sys_acl_module` VALUES ('8', 'a3faf869a12a4117bdf1663156776be2', '企业管理', '2', '0', '0.1.2', '1', '2', '', '', '', '0', '李杰', '2019-04-16 13:24:33', '2019-04-23 20:31:27', '李杰');
INSERT INTO `sys_acl_module` VALUES ('9', '38b8f82768e9407aa0abea4b83f3a0b0', '产品提优', '1', '0', '0.1', '1', '2', '', '', '', '0', '李杰', '2019-04-16 13:25:56', '2019-04-16 13:26:36', '李杰');
INSERT INTO `sys_acl_module` VALUES ('10', '1066bb636b75413ca7a026482181c0a6', '首页', '1', '0', '0.1', '1', '1', '', '', '', '0', '李杰', '2019-04-22 16:58:12', '2019-04-22 16:58:12', '李杰');
INSERT INTO `sys_acl_module` VALUES ('11', 'b9d91c4399584278a522f62fbc653742', 'a', '1', '1', '0.1', '1', '1', '', '', '', '0', '李杰', '2019-04-23 20:31:48', '2019-04-23 20:31:48', '李杰');

-- ----------------------------
-- Table structure for sys_bug
-- ----------------------------
DROP TABLE IF EXISTS `sys_bug`;
CREATE TABLE `sys_bug` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_product_name` varchar(32) NOT NULL DEFAULT '' COMMENT '登记人',
  `sys_product_img` varchar(32) NOT NULL DEFAULT '' COMMENT '图片',
  `sys_product_status` char(1) NOT NULL DEFAULT '1' COMMENT '处理状态 0 已处理 1未处理',
  `sys_product_detail` varchar(64) NOT NULL DEFAULT '' COMMENT 'BUG详细说明',
  `sys_product_reply` varchar(32) NOT NULL DEFAULT '' COMMENT '回复',
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `sys_dept_uuid` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_dept_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `sys_dept_parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '上级部门id',
  `sys_dept_level` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '部门层级',
  `sys_dept_seq` int(11) NOT NULL DEFAULT 0 COMMENT '部门在当前层级下的顺序，由小到大',
  `sys_is_leaf` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1' COMMENT '是否是叶子节点 0 是   1不是',
  `sys_dept_status` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态 0启用  1禁用',
  `sys_dept_remark` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
  `group_id` bigint(20) NOT NULL COMMENT '第三方组 kagome-momo-open-source默认为1',
  `del_flag` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `sys_dept_uuid` (`sys_dept_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', 'qwertyuiop', '组织架构', '0', '0', '0', '1', '1', '', '1', '0', 'sys', '2019-01-08 08:58:46', '2019-01-08 08:58:50', 'sys');
INSERT INTO `sys_dept` VALUES ('2', 'rtyhujikl', '指挥部', '1', '0.1', '0', '1', '0', '', '1', '0', '', '2019-01-15 09:55:36', '2019-01-15 09:55:40', '');
INSERT INTO `sys_dept` VALUES ('3', '56789', '商务部', '2', '0.1.2', '0', '1', '0', '', '1', '0', 'sys', '2019-01-15 09:56:11', '2019-01-15 09:56:13', 'sys');
INSERT INTO `sys_dept` VALUES ('19', '0216789944a142948ab9e4025290c7f9', '测试首级部门', '3', '0.1.2.3', '5', '1', '0', '我是备注', '1', '0', '', '2019-03-08 16:39:18', '2019-03-08 16:39:18', '');
INSERT INTO `sys_dept` VALUES ('24', '3b11c7d89a9d47e8b0e391d125c023de', '测试3级部门3', '23', '0.23', '1', '1', '0', '我是备注', '1', '0', '', '2019-03-08 16:57:54', '2019-03-08 16:57:54', '');
INSERT INTO `sys_dept` VALUES ('25', '89efb313bc7e4c3faca760cd99c4b311', '测试3级部门3', '23', '0.23', '1', '0', '1', '我是备注', '1', '0', '', '2019-03-08 16:57:59', '2019-03-08 16:57:59', '');
INSERT INTO `sys_dept` VALUES ('26', 'a03946d75050424685bc5d12cc165012', '测试页面新增部门', '1', '0.1', '2', '1', '0', '测试页面新增部门', '1', '0', '李杰', '2019-04-14 17:03:19', '2019-04-14 17:03:19', '李杰');
INSERT INTO `sys_dept` VALUES ('27', 'e28ced868f03420fb58664bc84f1d185', '测试2级部门3', '19', '0.1.2.3.19', '1', '1', '0', '我是备注', '1', '0', '李杰', '2019-04-22 17:03:58', '2019-04-22 17:03:58', '李杰');
INSERT INTO `sys_dept` VALUES ('28', '63eac50aa14d428781e1fb8ef9e3ae72', '测试新版部门', '26', '0.1.26', '5', '1', '1', '', '1', '0', '李杰', '2019-04-23 19:31:05', '2019-04-23 19:31:05', '李杰');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `user_login_name` varchar(32) DEFAULT '' COMMENT '登录名',
  `user_ip` varchar(32) DEFAULT '' COMMENT '用户ip',
  `user_login_type` varchar(32) DEFAULT '' COMMENT '登录设备',
  `user_login_sys` varchar(32) DEFAULT '' COMMENT '操作系统',
  `user_login_browser` varchar(32) DEFAULT NULL COMMENT '获取浏览器类型',
  `create_time` datetime DEFAULT NULL COMMENT '登录时间',
  `group_id` bigint(20) DEFAULT NULL COMMENT '第三方组 小为默认为1',
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `sys_role_uuid` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_role_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `sys_role_type` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1' COMMENT '角色的类型，0：管理员角色，1：普通用户 2其他',
  `remark` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
  `group_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '第三方组 kagome-momo-open-source默认为1',
  `sys_role_status` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '是否被禁用  0否 1禁用',
  `del_flag` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT 'update_by',
  PRIMARY KEY (`id`),
  KEY `sys_role_uuid` (`sys_role_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('2', '429ada7dcef64153a0435ddfd37fa1ff', '测试角色', '1', '我是备注', '2', '1', '0', '李杰', '2019-03-22 13:26:25', '2019-03-22 14:22:35', '李杰');
INSERT INTO `sys_role` VALUES ('3', '4bcbb4b41153498c8d2717ffa74415ef', '测试角q色', '0', '我是备注', '2', '0', '0', '李杰', '2019-03-22 13:41:38', '2019-03-22 13:41:38', '李杰');
INSERT INTO `sys_role` VALUES ('4', '4bcbb4b41153498c8d2717ffa7441545', '腾讯爸爸管理员', '0', '', '3', '0', '0', '李杰', '2019-04-19 11:03:47', '2019-04-19 11:03:47', '李杰');
INSERT INTO `sys_role` VALUES ('5', '4bcbb4b41153498c8d2717ffa7441sfd', '普通员工1', '1', '', '3', '0', '0', '李杰', '2019-04-19 11:28:54', '2019-04-20 13:36:55', '李杰');
INSERT INTO `sys_role` VALUES ('6', '4bcbb4b41153498c8d2717ffa7441578', 'MOMO', '0', '', '1', '0', '0', '李杰', '2019-04-20 11:21:33', '2019-04-21 13:50:04', '李杰');
INSERT INTO `sys_role` VALUES ('7', '3356a188544b4c05ad5b91eb06604d57', '测试新增角色', '1', '', '1', '0', '0', '李杰', '2019-04-20 14:45:04', '2019-04-23 20:15:59', '李杰');
INSERT INTO `sys_role` VALUES ('8', 'a4134bed8b274601a654616a0878e24e', '新版本角色新增', '1', '', '1', '0', '0', '李杰', '2019-04-23 20:22:19', '2019-04-23 20:22:19', '李杰');
INSERT INTO `sys_role` VALUES ('9', '682085b52b714b91bb3e2fd885200829', 'aa', '1', '', '3', '1', '0', '李杰', '2019-04-23 20:58:26', '2019-04-23 21:07:08', '李杰');

-- ----------------------------
-- Table structure for sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_role_acl_uuid` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_role_id` bigint(20) NOT NULL COMMENT '角色id',
  `sys_acl_id` bigint(20) NOT NULL COMMENT '权限id',
  `sys_acl_permission_type` bigint(20) NOT NULL DEFAULT 1 COMMENT '菜单系统类型 1 系统管理 2资产管理',
  `group_id` bigint(20) NOT NULL COMMENT '第三方组 kagome-momo-open-source默认为1',
  `del_flag` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `sys_role_id` (`sys_role_id`) USING BTREE,
  KEY `sys_acl_id` (`sys_acl_id`) USING BTREE,
  KEY `sys_acl_permission_type` (`sys_acl_permission_type`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色和权限中间表';

-- ----------------------------
-- Records of sys_role_acl
-- ----------------------------
INSERT INTO `sys_role_acl` VALUES ('14', '99a916413c804a19a516b83e99345664', '4', '9', '1', '3', '0', '李杰', '2019-04-20 13:06:26', '2019-04-20 13:06:26', '李杰');
INSERT INTO `sys_role_acl` VALUES ('15', 'a366c4072ff844609b05507668dc3111', '4', '10', '1', '3', '0', '李杰', '2019-04-20 13:06:26', '2019-04-20 13:06:26', '李杰');
INSERT INTO `sys_role_acl` VALUES ('16', '38dccbd8463a49c4afa5e4cabee598a8', '4', '11', '1', '3', '0', '李杰', '2019-04-20 13:06:26', '2019-04-20 13:06:26', '李杰');
INSERT INTO `sys_role_acl` VALUES ('17', 'ac05d047abaa45b0ba39ae6ce21c0d16', '4', '12', '1', '3', '0', '李杰', '2019-04-20 13:06:26', '2019-04-20 13:06:26', '李杰');
INSERT INTO `sys_role_acl` VALUES ('18', 'cffb9487bb4f4b60b59810d007997501', '4', '2', '1', '3', '0', '李杰', '2019-04-20 13:06:26', '2019-04-20 13:06:26', '李杰');
INSERT INTO `sys_role_acl` VALUES ('19', 'b771ef54446a45aaad898aa2af329bf1', '4', '3', '1', '3', '0', '李杰', '2019-04-20 13:06:26', '2019-04-20 13:06:26', '李杰');
INSERT INTO `sys_role_acl` VALUES ('20', '384ed2082b224314930722c809c361e0', '4', '4', '1', '3', '0', '李杰', '2019-04-20 13:06:26', '2019-04-20 13:06:26', '李杰');
INSERT INTO `sys_role_acl` VALUES ('21', 'abf980477ccf470db74f1bbc4d427657', '3', '17', '1', '2', '0', '李杰', '2019-04-20 13:09:30', '2019-04-20 13:09:30', '李杰');
INSERT INTO `sys_role_acl` VALUES ('22', '3a1c16e77e55461aa720003c1b5aa690', '3', '1', '1', '2', '0', '李杰', '2019-04-20 13:09:30', '2019-04-20 13:09:30', '李杰');
INSERT INTO `sys_role_acl` VALUES ('23', '86f89e07e2af43d1b6906bdb00ec5f8b', '7', '5', '1', '1', '0', '李杰', '2019-04-20 15:31:25', '2019-04-20 15:31:25', '李杰');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_role_user_uuid` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `group_id` bigint(20) NOT NULL COMMENT '第三方组 kagome-momo-open-source默认为1',
  `del_flag` char(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(32) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色和用户中间表';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('12', '9188390384ac4bb59bb118f75ae0818e', '7', '1', '1', '0', '李杰', '2019-04-21 14:02:32', '2019-04-21 14:02:32', '李杰');
INSERT INTO `sys_role_user` VALUES ('13', 'df0026d830b9413abd53dcb60a90b0a9', '7', '2', '1', '0', '李杰', '2019-04-23 21:12:09', '2019-04-23 21:12:09', '李杰');
INSERT INTO `sys_role_user` VALUES ('14', 'f79d1fa114f74299a3b670db5fcd6e37', '7', '4', '1', '0', '李杰', '2019-04-24 22:29:47', '2019-04-24 22:29:47', '李杰');

-- ----------------------------
-- Table structure for sys_routes
-- ----------------------------
DROP TABLE IF EXISTS `sys_routes`;
CREATE TABLE `sys_routes` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_id` varchar(32) NOT NULL DEFAULT '' COMMENT '这个路由的唯一id，不定义的话为一个uuid',
  `sys_predicates` varchar(1024) NOT NULL DEFAULT '' COMMENT '表示这个路由的请求匹配规则，只有符合这个规则的请求才会走这个路由。为一个数组，每个规则为并且的关系',
  `sys_filters` varchar(1024) NOT NULL DEFAULT '' COMMENT '请求转发前的filter，为一个数组',
  `sys_uri` varchar(64) NOT NULL DEFAULT '' COMMENT 'http请求为lb://前缀 + 服务id；ws请求为lb:ws://前缀 + 服务id；表示将请求负载到哪一个服务上',
  `sys_order` int(11) NOT NULL COMMENT '这个路由的执行order',
  `sys_name` varchar(16) NOT NULL COMMENT '路由名称',
  PRIMARY KEY (`p_id`),
  UNIQUE KEY `id` (`sys_id`) USING BTREE,
  UNIQUE KEY `sys_uri` (`sys_uri`) USING BTREE,
  UNIQUE KEY `sys_name` (`sys_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_routes
-- ----------------------------
INSERT INTO `sys_routes` VALUES ('2', 'system-core', '[{\"args\":{\"_genkey_0\":\"/api/platform/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"1\"},\"name\":\"StripPrefix\"},{\"args\":{\"redis-rate-limiter.burstCapacity\":\"30\",\"redis-rate-limiter.replenishRate\":\"20\",\"keyResolver\":\"#{@apiKeyResolver}\"},\"name\":\"RequestRateLimiter\"}]', 'lb://system-core', '1', '后台管理系统');
INSERT INTO `sys_routes` VALUES ('4', 'test-core', '[{\"args\":{\"_genkey_0\":\"/test/platform/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"1\"},\"name\":\"StripPrefix\"},{\"args\":{\"redis-rate-limiter.burstCapacity\":\"30\",\"redis-rate-limiter.replenishRate\":\"20\",\"keyResolver\":\"#{@apiKeyResolver}\"},\"name\":\"RequestRateLimiter\"}]', 'lb://test-core', '9', '测试路由');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sys_user_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `sys_user_uuid` varchar(32) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '唯一，32位字符串，查询用',
  `sys_user_login_name` varchar(32) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '登录名',
  `sys_user_pwd` varchar(128) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '密码',
  `sys_user_auth_salt` varchar(32) COLLATE utf8_unicode_ci DEFAULT '1234' COMMENT '撒盐',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '关联部门id',
  `sys_user_phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `sys_user_email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `sys_user_province_name` varchar(16) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '省 名字',
  `sys_user_province` bigint(20) DEFAULT NULL COMMENT '省',
  `sys_user_city_name` varchar(16) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '市名字',
  `sys_user_city` bigint(20) DEFAULT NULL COMMENT '市',
  `sys_user_area_name` varchar(16) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '区名字',
  `sys_user_area` bigint(20) DEFAULT NULL COMMENT '区',
  `group_id` bigint(20) DEFAULT 1 COMMENT '第三方组 kagome-momo-open-source默认为1',
  `sys_login_number` int(3) DEFAULT 1 COMMENT '账号允许登录的次数 -1 不限次数 ，如需登录次数为0，请禁用该账号，减少代码复杂度',
  `is_forbidden` char(1) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '是否被禁用  0否 1禁用',
  `del_flag` char(1) COLLATE utf8_unicode_ci DEFAULT '0' COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(32) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) COLLATE utf8_unicode_ci DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `sys_user_name` (`sys_user_name`) USING BTREE,
  KEY `sys_user_login_name` (`sys_user_login_name`) USING BTREE,
  KEY `sys_user_phone` (`sys_user_phone`),
  KEY `sys_user_uuid` (`sys_user_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '李杰', 'add6df8ac7f84211ba2303630fca10db', 'momo', 'e659d44b5681093257a99d1ab84c45db385f3001', 'momo', '1', '15050451502', '15050451502@163.com', '江苏省', '1', '江苏省', '1', '江苏省', '1', '1', '3', '0', '0', '枸杞', '2019-01-11 15:09:26', '2019-04-21 11:11:26', '李杰');
INSERT INTO `sys_user` VALUES ('2', 'test', '691d837480824acfa6aad086898026d0', 'test', '4767b7f9e15d7fd74fa6202ca4f9062decae8a85', 'u5d446', null, '15050451501', '15050451501@163.com', '', null, '', null, '', null, '1', '2', '0', '0', '李杰', '2019-04-21 12:20:18', '2019-04-24 14:26:02', '李杰');
INSERT INTO `sys_user` VALUES ('3', '啊啊', 'd04b26d876574a3998917e65739ac984', 'a', '00aef15483cb8db7a12d0fb1740871faa7b113cd', '0oo0gq', null, 'a', 'a@aa.com', '', null, '', null, '', null, '1', '1', '1', '0', '李杰', '2019-04-23 21:21:05', '2019-04-24 21:56:07', '李杰');
INSERT INTO `sys_user` VALUES ('4', 'aaaa', '7575a0f64189451ba6b7aac8da797a83', 'aaaa', '9301756862ea68f74090b2d13aa9e3b233c6c19a', 'aakwy0', null, '1', '1@qq.com', '', null, '', null, '', null, '1', '1', '0', '0', '李杰', '2019-04-24 21:57:54', '2019-04-24 22:29:58', '李杰');

-- ----------------------------
-- Table structure for sys_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_group_uuid` varchar(32) NOT NULL COMMENT 'uuid',
  `user_group_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户组名称/第三方公司名称',
  `sys_account_start_time` datetime DEFAULT NULL COMMENT '账号开通时间 (不传值，默认系统时间)',
  `sys_account_end_time` datetime DEFAULT NULL COMMENT '账号结束时间',
  `sys_open_day` varchar(8) NOT NULL DEFAULT '0' COMMENT '开通的天数 -1 不限次数 自己公司公司所有',
  `sys_create_account_num` int(11) NOT NULL DEFAULT 0 COMMENT '可以开通子账户的个数 -1不限制次数  id为1 为自己公司，不限次数',
  `sys_open_account_num` int(11) NOT NULL DEFAULT 0 COMMENT '已开通账号个数',
  `name_top` varchar(32) NOT NULL DEFAULT '' COMMENT '顶部名称',
  `name_bottom` varchar(256) NOT NULL DEFAULT '' COMMENT '版权',
  `user_group_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态 0启用  1禁用',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态(0-正常，1-删除)',
  `create_by` varchar(16) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` varchar(16) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `user_group_uuid` (`user_group_uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='第三方权限组';

-- ----------------------------
-- Records of sys_user_group
-- ----------------------------
INSERT INTO `sys_user_group` VALUES ('1', 'fghjk', 'kagome-momo-open-source', '2019-01-27 17:48:39', '2019-01-27 17:48:41', '0', '0', '0', '', '', '0', '0', '', '2019-01-27 17:49:04', '2019-01-27 17:49:06', '');
INSERT INTO `sys_user_group` VALUES ('2', '0a8dc63ab9a04b82a9fda25ea4be6fdb', '阿里爸爸', null, null, '0', '0', '0', '阿里爸爸', 'Copyright © 2018-2019 阿里爸爸, All Rights Reserved. 苏ICP备18055393号', '0', '0', '李杰', '2019-04-18 11:02:37', '2019-04-24 14:27:38', '李杰');
INSERT INTO `sys_user_group` VALUES ('3', '88b33625c6a2402e99345968c530337f', '腾讯爸爸', null, null, '0', '0', '0', '腾讯爸爸', 'Copyright © 2018-2019 腾讯爸爸, All Rights Reserved. 苏ICP备18055393号', '0', '0', '李杰', '2019-04-18 11:11:11', '2019-04-23 21:07:56', '李杰');

-- ----------------------------
-- Table structure for sys_website_visible
-- ----------------------------
DROP TABLE IF EXISTS `sys_website_visible`;
CREATE TABLE `sys_website_visible` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `visible_number` bigint(20) NOT NULL DEFAULT 0 COMMENT '访问总人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='网站访问总人数';

-- ----------------------------
-- Records of sys_website_visible
-- ----------------------------
INSERT INTO `sys_website_visible` VALUES ('1', '1062');
