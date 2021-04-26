/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : lite_config

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2021-04-27 02:27:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_account
-- ----------------------------
DROP TABLE IF EXISTS `auth_account`;
CREATE TABLE `auth_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id，唯一',
  `username` varchar(20) NOT NULL COMMENT '用户登录名 唯一',
  `password` varchar(255) NOT NULL COMMENT 'MD5(明文密码+盐)，如md5("123456"+"sdwfdasd")',
  `salt` varchar(255) DEFAULT '' COMMENT '盐',
  `email` varchar(255) NOT NULL COMMENT '电子邮箱，可以用来登录',
  `is_del` int(11) DEFAULT '0' COMMENT '0未删除，1已经删除（不能登录）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_account
-- ----------------------------
INSERT INTO `auth_account` VALUES ('1', 'root', '$2a$10$pJ8m..Zd3eS7qEmMSSA81OglC2hb6re1.IN..LYomj5dsnYGBGlOu', '', 'root@mail.com', '0');
INSERT INTO `auth_account` VALUES ('6', 'newuser', '$2a$10$VMOpg45jy8NMCD3CS5a7WePJQ5B6MPfcD9BKUY9xhQKBeXZvLtQnW', null, 'zhangxujie@gmail.com', '0');
INSERT INTO `auth_account` VALUES ('7', 'Leo', '$2a$10$FqSOsKVA1jME4hC7.ncfGeSYBO15Cxia0F8Xi2JidPg1EYsMAYG4G', null, 'zhangxujie@gmail.com', '0');
INSERT INTO `auth_account` VALUES ('10', 'newuser1', '$2a$10$krocMPRpEm7I30WQYUrkReYVX1D97waUxMmm5sGgNEhyc1nPa2ws.', null, 'abc@gmail.com', '0');
INSERT INTO `auth_account` VALUES ('11', 'newuser2', '$2a$10$IFEjRokaUR9LFv/lZ4TFTef64tef5vDy8icnIeEH6aqCwRm5.Q7J.', null, 'abc@gmail.com', '0');
INSERT INTO `auth_account` VALUES ('12', 'newuser3', '$2a$10$pKpyTdjUWnAvWG6UYQxBwu4D8.7Zp3NyoMI.szV8EBppOC5vr7BxG', null, 'abc@gmail.com', '0');
INSERT INTO `auth_account` VALUES ('13', 'newuser4', '$2a$10$zRg78dpC9VmNBV25KsfvO.Qg4kQH9iXrqtbL7fA3rqNGGBTHVAGPu', null, 'abc@gmail.com', '0');
INSERT INTO `auth_account` VALUES ('14', 'newuser5', '$2a$10$IVdyBSJ.PkX6vxpsHuhX/uWTJEF2vEtsGUWXjDN0xfXCaO/xyR9.2', null, 'abc@gmail.com', '0');
INSERT INTO `auth_account` VALUES ('15', 'newuser6', '$2a$10$w/RPjN9NZABJAYN.F5nHLeGGgwP45MqiiUun6iCcLIriBA9AQ00Zq', null, 'abc@gmail.com', '0');
INSERT INTO `auth_account` VALUES ('16', 'newuser7', '$2a$10$4eGqmHg4LmaB7FqH7WJmEOrYX8tRtchPYTlV18NhCZ7Qvb2moCtm6', null, 'abc@gmail.com', '0');
INSERT INTO `auth_account` VALUES ('17', 'newuser8', '$2a$10$CAlk4ZviLtRq98A/zUB6kelScFd0YWBC3iINpGot4YFKBggaX84Xm', null, 'abc@gmail.com', '0');
INSERT INTO `auth_account` VALUES ('18', 'newuser9', '$2a$10$dG5Ret./amnysN6wUfRw/eXW64nQ1gsTtrA0ERMIDMGC6tyPVGDq2', null, 'abc@gmail.com', '0');

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
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identity_type` varchar(255) DEFAULT 'USER' COMMENT '[USER | GROUP]',
  `identity_id` int(11) DEFAULT NULL COMMENT 'group_id或者account_id，视action_type决定',
  `permission` varchar(255) DEFAULT 'cfg:read' COMMENT '[cfg|user] : [read|edit] 或者 root拥有所有权限',
  `comment` varchar(255) DEFAULT 'identity_type=[USER | GROUP]     permission=[Cfg|User] : [Read|Edit] 或者 Root拥有所有权限' COMMENT '注释',
  `is_del` int(11) DEFAULT '0' COMMENT '0表示有效，1表示已经删除',
  `time` bigint(20) DEFAULT NULL COMMENT '操作时间',
  `operator_account_id` int(11) DEFAULT NULL COMMENT '操作者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES ('1', 'USER', '1', 'root', 'permission=[user | cfg] : [U/G]-[userid/groupid] : [C/CS]-[configId/cfg_collectionId]', '0', '1614007740', '1');
INSERT INTO `auth_permission` VALUES ('2', 'GROUP', '1', 'root', '用户组1对配置集1有权限', '0', '1614007741', '1');
INSERT INTO `auth_permission` VALUES ('3', 'USER', '1', 'user', '用户1对用户操作有权限', '0', '1614007741', '1');
INSERT INTO `auth_permission` VALUES ('4', 'USER', '6', 'cfg', '用户6对配置有操作权限', '0', '1614007741', '1');
INSERT INTO `auth_permission` VALUES ('5', 'USER', '7', 'cfg', null, '0', '1614007741', '1');
INSERT INTO `auth_permission` VALUES ('6', 'USER', '10', 'cfg', null, '0', '1618768044255', null);
INSERT INTO `auth_permission` VALUES ('7', 'USER', '11', 'cfg', null, '0', '1618768059820', null);
INSERT INTO `auth_permission` VALUES ('8', 'USER', '12', 'cfg', null, '0', '1618768062277', null);
INSERT INTO `auth_permission` VALUES ('9', 'USER', '13', 'cfg', null, '0', '1618768064954', null);
INSERT INTO `auth_permission` VALUES ('10', 'USER', '14', 'cfg', null, '0', '1618768068151', null);
INSERT INTO `auth_permission` VALUES ('11', 'USER', '15', 'cfg', null, '0', '1618768070467', null);
INSERT INTO `auth_permission` VALUES ('12', 'USER', '16', 'cfg', null, '0', '1618768073344', null);
INSERT INTO `auth_permission` VALUES ('13', 'USER', '17', 'cfg', null, '0', '1618768076630', null);
INSERT INTO `auth_permission` VALUES ('14', 'USER', '18', 'cfg', null, '0', '1618768080578', null);

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL COMMENT '权限名',
  `role_info` varchar(255) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='该表为不可变权限表';

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('1', 'ROOT', '所有操作均可');
INSERT INTO `auth_role` VALUES ('2', 'USER_EDIT', '所有用户信息均可修改（（不可删除））');
INSERT INTO `auth_role` VALUES ('3', 'USER_READ', '（默认）可以查看一般用户信息');
INSERT INTO `auth_role` VALUES ('4', 'CONFIG_ROOT', '所有配置根权限');
INSERT INTO `auth_role` VALUES ('5', 'CONFIG_EDIT', '所有配置可以编辑（不可删除）');
INSERT INTO `auth_role` VALUES ('6', 'CONFIG_READ', '所有配置均可查看');

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `account_id` int(11) NOT NULL COMMENT '关联用户的id',
  `group_id` int(11) NOT NULL COMMENT '组id，对应auth_group表',
  `update_time` bigint(20) DEFAULT NULL,
  `update_account_id` int(11) DEFAULT NULL,
  `is_del` int(11) NOT NULL DEFAULT '0' COMMENT '0删除，1存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='该表为用户与用户组对应表，一个组有多个用户，一个用户可以在多个组';

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1', '1', '1', null, null, '0');

-- ----------------------------
-- Table structure for cfg_audit
-- ----------------------------
DROP TABLE IF EXISTS `cfg_audit`;
CREATE TABLE `cfg_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `cfg_collection_id` int(11) NOT NULL COMMENT '对应cfg_config_collection表中的自增id',
  `status` int(3) DEFAULT '0' COMMENT '审核状态：0待审核，1审核成功，2审核失败',
  `applicant_aid` int(11) NOT NULL COMMENT '申请人ID，对应account_id',
  `reviewer_aid` int(11) DEFAULT NULL COMMENT '审核者的account_id',
  `submit_time` bigint(20) DEFAULT NULL COMMENT '提交申请的时间',
  `handle_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cfg_audit
-- ----------------------------

-- ----------------------------
-- Table structure for cfg_config
-- ----------------------------
DROP TABLE IF EXISTS `cfg_config`;
CREATE TABLE `cfg_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置集合自增id',
  `collection_id` int(11) NOT NULL COMMENT '对应配置集合中的自增ID',
  `cfg_name` varchar(255) DEFAULT NULL COMMENT '配置名称，为了用户识别',
  `cfg_key` varchar(255) NOT NULL COMMENT '配置key',
  `cfg_value` text COMMENT '存放配置内容',
  `is_del` int(255) NOT NULL DEFAULT '0' COMMENT '0存在，1已经删除',
  `is_draft` int(255) NOT NULL DEFAULT '0' COMMENT '0=非草稿，1=草稿,，准备弃用，只对集粒度控制',
  `update_username` varchar(40) DEFAULT NULL COMMENT '更新者用户名',
  `create_username` varchar(40) DEFAULT NULL COMMENT '创建者用户名',
  `update_time` bigint(30) DEFAULT NULL COMMENT '更新时间',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cfg_config
-- ----------------------------
INSERT INTO `cfg_config` VALUES ('1', '1', 'cfg_test', 'country', '{\r\n    \"country\":[\"china\", \"United States\", \"Japan\"]\r\n}', '0', '0', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config` VALUES ('2', '2', 'cfg_2', 'username', 'Leon', '0', '0', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config` VALUES ('3', '1', 'cfg_ip', 'pro', '192.168.1.1', '0', '0', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config` VALUES ('4', '3', 'cfg_draft_1', 'pwd', 'p@ssw0rd', '1', '1', 'root', 'root', '1618508370', '1614146788');
INSERT INTO `cfg_config` VALUES ('5', '3', 'cfg_draft_1', 'password', 'p@ssw0rd', '1', '1', 'root', 'root', '1618508471', '1614146788');
INSERT INTO `cfg_config` VALUES ('6', '3', 'cfg_draft_10086', 'password', 'p@ssw0rd', '1', '1', 'root', 'root', '1618508561', '1614146788');
INSERT INTO `cfg_config` VALUES ('7', '3', 'cfg_draft_pwd', 'password', 'p@ssw0rd', '1', '1', 'root', 'root', '1618508611', '1614146788');
INSERT INTO `cfg_config` VALUES ('8', '3', 'cfg_draft_pwd', 'password', 'p@ssw0rd', '1', '1', 'root', 'root', '1618508767', '1614146788');
INSERT INTO `cfg_config` VALUES ('9', '3', 'cfg_draft_pwd', 'password', 'p@ssw0rd', '1', '1', 'root', 'root', '1618508901', '1614146788');
INSERT INTO `cfg_config` VALUES ('10', '3', 'cfg_draft_pwd', 'password', 'p@ssw0rd', '1', '1', 'root', 'root', '1618508960', '1614146788');
INSERT INTO `cfg_config` VALUES ('11', '3', 'cfg_draft_pwd', 'password', 'p@ssw0rd', '1', '1', 'root', 'root', '1618509205', '1614146788');
INSERT INTO `cfg_config` VALUES ('12', '3', 'cfg_draft_pwd', 'passwd', 'p@ssw0rd', '1', '1', 'root', 'root', '1618509312', '1614146788');
INSERT INTO `cfg_config` VALUES ('13', '3', 'cfg_draft_pwd', 'passwd', 'p@ssw0rd', '1', '1', 'root', 'root', '1618509480', '1614146788');
INSERT INTO `cfg_config` VALUES ('14', '3', 'draft_pwd', 'passwd', 'p@ssw0rd', '1', '1', 'root', 'root', '1618509738', '1614146788');
INSERT INTO `cfg_config` VALUES ('15', '3', 'draft_pwd_JASON', 'passwd', 'p@ssw0rd', '0', '1', 'root', 'root', '1618509738', '1614146788');
INSERT INTO `cfg_config` VALUES ('18', '5', 'cfg_test_json', 'country', '{\r\n    \"country\":[\"china\", \"United States\", \"Japan\"]\r\n}', '0', '1', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config` VALUES ('19', '5', 'cfg_ip', 'pro', '192.168.1.1', '0', '1', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config` VALUES ('20', '6', 'cfg_2_test', 'username', 'Leon', '1', '1', 'root', 'root', '1619447861', '1614146788');
INSERT INTO `cfg_config` VALUES ('21', '6', 'chat_param', 'username', 'Leon', '1', '1', 'root', 'root', '1619459959', '1614146788');
INSERT INTO `cfg_config` VALUES ('22', '6', '11', '222', null, '1', '1', 'newuser', 'newuser', '1619458295', '1619457886');
INSERT INTO `cfg_config` VALUES ('23', '6', '11', '222', '3333', '1', '1', 'newuser', 'newuser', '1619461458', '1619457886');
INSERT INTO `cfg_config` VALUES ('24', '6', '1', '2', null, '0', '1', 'newuser', 'newuser', '1619458860', '1619458860');
INSERT INTO `cfg_config` VALUES ('28', '6', 'test', '111', '222', '0', '1', 'newuser', 'newuser', '1619459352', '1619459352');
INSERT INTO `cfg_config` VALUES ('29', '6', '吃了么', '好不好', '不好', '0', '1', 'newuser', 'newuser', '1619459441', '1619459441');
INSERT INTO `cfg_config` VALUES ('30', '6', 'chat_param', 'username', 'Leon2', '0', '1', 'newuser', 'root', '1619459959', '1614146788');

-- ----------------------------
-- Table structure for cfg_config_collection
-- ----------------------------
DROP TABLE IF EXISTS `cfg_config_collection`;
CREATE TABLE `cfg_config_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collection_id` int(11) DEFAULT NULL COMMENT '不同集合有不同的id，同一个cid，会有线上版本和草稿版本',
  `c_name` varchar(255) DEFAULT NULL,
  `is_del` int(11) NOT NULL DEFAULT '0',
  `is_draft` int(11) NOT NULL DEFAULT '0',
  `update_username` varchar(40) DEFAULT NULL,
  `create_username` varchar(40) DEFAULT NULL,
  `update_time` bigint(30) DEFAULT NULL,
  `create_time` bigint(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cfg_config_collection
-- ----------------------------
INSERT INTO `cfg_config_collection` VALUES ('1', '1', 'ct_test_1', '0', '0', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config_collection` VALUES ('2', '2', 'chat', '0', '0', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config_collection` VALUES ('3', '3', 'draft_1', '0', '1', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config_collection` VALUES ('5', '5', 'ct_test_1', '0', '1', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config_collection` VALUES ('6', '6', 'chat', '1', '1', 'root', 'root', '1618584164', '1614146788');
INSERT INTO `cfg_config_collection` VALUES ('8', '8', 'test_cfg_coll', '1', '1', 'root', 'root', '1618938482', '1618938482');

-- ----------------------------
-- Table structure for cfg_permission
-- ----------------------------
DROP TABLE IF EXISTS `cfg_permission`;
CREATE TABLE `cfg_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `type` int(255) DEFAULT '1' COMMENT '1=用户+配置集合；2=用户组+配置集合；之后待定',
  `account_id` int(11) DEFAULT '0' COMMENT '用户粒度权限，则该值 大于 0',
  `group_id` int(11) DEFAULT '0' COMMENT '用户组权限，则该值 大于 0',
  `config_id` int(11) DEFAULT '0' COMMENT '如果针对单独配置赋予权限，则该值 大于 0，暂时不用',
  `collection_id` int(11) DEFAULT '0' COMMENT '如果针对集合赋予权限，则该值 大于 0',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `create_username` varchar(255) DEFAULT NULL COMMENT '创建者username',
  `create_account_id` int(11) DEFAULT NULL,
  `is_del` int(4) DEFAULT '0' COMMENT '软删除标志位，1删除，0存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cfg_permission
-- ----------------------------

-- ----------------------------
-- Table structure for old_auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `old_auth_permission`;
CREATE TABLE `old_auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identity_type` int(255) DEFAULT '0' COMMENT '0表示作用于用户，1表示作用域group用户组',
  `identity_id` int(11) DEFAULT NULL COMMENT 'group_id或者account_id，视action_type决定',
  `role` varchar(255) DEFAULT NULL,
  `cfg_type` int(11) DEFAULT '0' COMMENT '0表示针对单条配置，1表示针对配置集合',
  `cfg_id` int(11) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT 'permission=[user | cfg] : [U/G]-[userid/groupid] : [C/CS]-[configId/cfg_collectionId]；U表示用户，G表示用户组，后接ID；C表示配置，CS表示配置集，后接ID' COMMENT 'U表示用户，G表示用户组，后接ID；C表示配置，CS表示配置集，后接ID',
  `is_del` int(11) DEFAULT '0' COMMENT '0表示有效，1表示已经删除',
  `time` bigint(20) DEFAULT NULL COMMENT '操作时间',
  `operator_account_id` int(11) DEFAULT NULL COMMENT '操作者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of old_auth_permission
-- ----------------------------
INSERT INTO `old_auth_permission` VALUES ('1', '0', '1', '4', '0', '1', 'cfg:U-1:C-1', 'permission=[user | cfg] : [U/G]-[userid/groupid] : [C/CS]-[configId/cfg_collectionId]', '0', '1614007740', '1');
INSERT INTO `old_auth_permission` VALUES ('2', '1', '1', '4', '1', '1', 'cfg', '用户组1对配置集1有权限', '0', '1614007741', '1');
INSERT INTO `old_auth_permission` VALUES ('3', '0', '1', '1', '-1', '-1', 'user:U-1', '用户1对用户操作有权限', '0', '1614007741', '1');
INSERT INTO `old_auth_permission` VALUES ('4', '0', '6', null, null, null, 'cfg:read', null, null, null, null);
INSERT INTO `old_auth_permission` VALUES ('5', '0', '7', null, null, null, 'cfg:read', null, null, null, null);

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
  `cfg_type` int(255) DEFAULT '0' COMMENT '0表示配置config，1表示配置集合',
  `cfg_id` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL COMMENT '注释',
  `is_del` int(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户收藏的配置';

-- ----------------------------
-- Records of user_favorites
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `img_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `last_accessed_time` bigint(20) DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL COMMENT '额外信息，写一些注释什么的',
  `update_time` bigint(20) DEFAULT NULL,
  `is_del` int(11) DEFAULT '0' COMMENT '0存在，1已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '1', '白月光', '18731302887', 'https://tva3.sinaimg.cn/large/9bd9b167ly1g1p9q62elej20b40b40te.jpg', null, '无', '1614007740', '0');
INSERT INTO `user_info` VALUES ('2', '6', 'newuser', null, null, '1616236555812', null, '1616236555812', '0');
INSERT INTO `user_info` VALUES ('3', '7', 'Leo', null, null, '1616237760512', null, '1616237760512', '0');
INSERT INTO `user_info` VALUES ('4', '10', 'newuser1', null, null, '1618768044251', null, '1618768044251', '0');
INSERT INTO `user_info` VALUES ('5', '11', 'newuser2', null, null, '1618768059817', null, '1618768059817', '0');
INSERT INTO `user_info` VALUES ('6', '12', 'newuser3', null, null, '1618768062275', null, '1618768062275', '0');
INSERT INTO `user_info` VALUES ('7', '13', 'newuser4', null, null, '1618768064951', null, '1618768064951', '0');
INSERT INTO `user_info` VALUES ('8', '14', 'newuser5', null, null, '1618768068144', null, '1618768068144', '0');
INSERT INTO `user_info` VALUES ('9', '15', 'newuser6', null, null, '1618768070464', null, '1618768070464', '0');
INSERT INTO `user_info` VALUES ('10', '16', 'newuser7', null, null, '1618768073342', null, '1618768073342', '0');
INSERT INTO `user_info` VALUES ('11', '17', 'newuser8', null, null, '1618768076625', null, '1618768076625', '0');
INSERT INTO `user_info` VALUES ('12', '18', 'newuser9', null, null, '1618768080575', null, '1618768080575', '0');
