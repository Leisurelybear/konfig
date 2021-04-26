/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : lite_config

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2021-03-01 16:54:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_account
-- ----------------------------
DROP TABLE IF EXISTS `auth_account`;
CREATE TABLE `auth_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id，唯一',
  `user_name` varchar(20) NOT NULL COMMENT '用户登录名 唯一',
  `nickname` varchar(30) NOT NULL COMMENT '用户昵称',
  `password` varchar(255) NOT NULL COMMENT 'MD5(明文密码+盐)',
  `salt` varchar(255) NOT NULL COMMENT '盐',
  `email` varchar(255) NOT NULL COMMENT '电子邮箱',
  `from_account_id` int(11) DEFAULT NULL COMMENT '添加该用户的上级用户id',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建/添加该用户的时间，存时间戳',
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_account
-- ----------------------------
INSERT INTO `auth_account` VALUES ('1', 'root', 'root', 'b305cadbb3bce54f3aa59c64fec00dea', 'salt', 'root@mail.com', null, '1614007740', '密码：password；盐：salt; 密码是md5(passwordsalt, 32)');

-- ----------------------------
-- Table structure for auth_group
-- ----------------------------
DROP TABLE IF EXISTS `auth_group`;
CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增唯一id',
  `group_name` varchar(255) NOT NULL COMMENT '组名，为了识别不同的组',
  `group_role` int(255) DEFAULT NULL COMMENT '该组权限',
  `root_account_id` int(11) NOT NULL COMMENT '该组创建者ID，拥有最大权限',
  `update_time` bigint(20) DEFAULT NULL,
  `update_account_id` int(11) DEFAULT NULL,
  `is_del` int(11) NOT NULL DEFAULT '0' COMMENT '0存在，1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_group
-- ----------------------------
INSERT INTO `auth_group` VALUES ('1', 'wheel', '1', '1', '1614007740', null, '0');

-- ----------------------------
-- Table structure for auth_mapping_usergroup
-- ----------------------------
DROP TABLE IF EXISTS `auth_mapping_usergroup`;
CREATE TABLE `auth_mapping_usergroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `account_id` int(11) NOT NULL COMMENT '关联用户的id',
  `group_id` int(11) NOT NULL COMMENT '组id，对应auth_group表',
  `update_time` bigint(20) DEFAULT NULL,
  `update_account_id` int(11) DEFAULT NULL,
  `is_del` int(11) NOT NULL DEFAULT '0' COMMENT '0删除，1存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='该表为用户与用户组对应表，一个组有多个用户，一个用户可以在多个组';

-- ----------------------------
-- Records of auth_mapping_usergroup
-- ----------------------------
INSERT INTO `auth_mapping_usergroup` VALUES ('1', '1', '1', null, null, '0');

-- ----------------------------
-- Table structure for auth_roles
-- ----------------------------
DROP TABLE IF EXISTS `auth_roles`;
CREATE TABLE `auth_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL COMMENT '权限名',
  `role_number` int(11) NOT NULL COMMENT '权限对应数字',
  `role_info` varchar(255) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='该表为不可变权限表';

-- ----------------------------
-- Records of auth_roles
-- ----------------------------
INSERT INTO `auth_roles` VALUES ('1', 'USER_ROOT', '901', '所有用户操作均可');
INSERT INTO `auth_roles` VALUES ('2', 'USER_EDIT', '902', '所有用户信息均可修改（（不可删除））');
INSERT INTO `auth_roles` VALUES ('3', 'USER_READ', '903', '（默认）可以查看一般用户信息');
INSERT INTO `auth_roles` VALUES ('4', 'ALL_CONFIG_ROOT', '101', '所有配置根权限');
INSERT INTO `auth_roles` VALUES ('5', 'ALL_CONFIG_EDIT', '102', '所有配置可以编辑（不可删除）');
INSERT INTO `auth_roles` VALUES ('6', 'ALL_CONFIG_READ', '103', '所有配置均可查看');
INSERT INTO `auth_roles` VALUES ('7', 'GROUP_CONFIG_ROOT', '1', null);

-- ----------------------------
-- Table structure for cfg_config
-- ----------------------------
DROP TABLE IF EXISTS `cfg_config`;
CREATE TABLE `cfg_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置集合自增id',
  `collection_id` int(11) NOT NULL,
  `cfg_name` varchar(255) DEFAULT NULL COMMENT '配置名称，为了用户识别',
  `cfg_key` varchar(255) NOT NULL COMMENT '配置key',
  `cfg_value` text COMMENT '存放配置内容',
  `is_del` int(255) NOT NULL DEFAULT '0' COMMENT '0存在，1已经删除',
  `is_draft` int(255) NOT NULL DEFAULT '0' COMMENT '0=非草稿，1=草稿',
  `update_account_id` int(11) DEFAULT NULL,
  `update_time` bigint(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cfg_config
-- ----------------------------
INSERT INTO `cfg_config` VALUES ('1', '1', 'cfg_test', 'country', '[\'china\', \'United States\', \'Japan\']', '0', '0', '1', '1614146788');

-- ----------------------------
-- Table structure for cfg_config_collection
-- ----------------------------
DROP TABLE IF EXISTS `cfg_config_collection`;
CREATE TABLE `cfg_config_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(255) DEFAULT NULL,
  `is_del` int(11) NOT NULL DEFAULT '0',
  `is_draft` int(11) NOT NULL DEFAULT '0',
  `update_account_id` int(11) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cfg_config_collection
-- ----------------------------
INSERT INTO `cfg_config_collection` VALUES ('1', 'ct_test_1', '0', '0', '1', '1614146788');

-- ----------------------------
-- Table structure for cfg_mapping_groupconfig
-- ----------------------------
DROP TABLE IF EXISTS `cfg_mapping_groupconfig`;
CREATE TABLE `cfg_mapping_groupconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL COMMENT '组id',
  `roles_id` int(11) NOT NULL COMMENT '拥有的权限',
  `config_collection_id` int(11) NOT NULL COMMENT '配置',
  `config_id` int(11) NOT NULL DEFAULT '0' COMMENT '配置id，拥有权限则可以操作，默认0为堆当前集合拥有全部权限',
  `is_del` int(5) NOT NULL DEFAULT '0' COMMENT '删除标志，0为存在，1为删除',
  `update_time` bigint(30) DEFAULT NULL,
  `update_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cfg_mapping_groupconfig
-- ----------------------------
INSERT INTO `cfg_mapping_groupconfig` VALUES ('1', '1', '4', '1', '0', '0', '1614146788', '1');

-- ----------------------------
-- Table structure for op_log
-- ----------------------------
DROP TABLE IF EXISTS `op_log`;
CREATE TABLE `op_log` (
  `id` int(11) NOT NULL,
  `log` varchar(255) DEFAULT NULL,
  `update_account_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `update_username` varchar(255) DEFAULT NULL COMMENT '操作人用户名',
  `update_time` bigint(20) DEFAULT NULL,
  `op_type` varchar(255) DEFAULT NULL COMMENT '操作类型：create，delete，update，query',
  `op_table` varchar(255) DEFAULT NULL COMMENT '数据库表，如果涉及到数据库需要填写',
  `op_field` varchar(255) DEFAULT NULL COMMENT '操作字段，如果涉及到数据库需要填写',
  `data_before` varchar(255) DEFAULT NULL,
  `data_after` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of op_log
-- ----------------------------

-- ----------------------------
-- Table structure for user_favorites
-- ----------------------------
DROP TABLE IF EXISTS `user_favorites`;
CREATE TABLE `user_favorites` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `config_id` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL COMMENT '注释',
  `is_del` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户收藏的配置';

-- ----------------------------
-- Records of user_favorites
-- ----------------------------
