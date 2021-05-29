/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : lite_config

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2021-05-29 16:40:36
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

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
INSERT INTO `auth_account` VALUES ('19', 'guest', '$2a$10$ZjOIKl9/BcFXZuN3Ts1W/eDH8bTJDDnaUIjBdwWTRzYksSas1HnAi', null, 'abc@gmail.com', '0');
INSERT INTO `auth_account` VALUES ('20', 'jason', '$2a$10$Su8XZ12otjm62DabzMpV/erOUFe143MqTbmp6xq.111899ycswL9C', null, 'jasonzhang666@163.com', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_group
-- ----------------------------
INSERT INTO `auth_group` VALUES ('1', 'wheel', '1', '1', '1614007740', '1', '0');
INSERT INTO `auth_group` VALUES ('2', 'car', '4', '1', '1622276685', '1', '0');
INSERT INTO `auth_group` VALUES ('3', '{\"groupName\":\"test\"}', '4', '1', '1622276792', '1', '1');
INSERT INTO `auth_group` VALUES ('4', 'test', '4', '1', '1622277374', '1', '1');
INSERT INTO `auth_group` VALUES ('5', 'test', '4', '1', '1622277451', '1', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES ('1', 'USER', '1', 'root', 'permission=[user | cfg] : [U/G]-[userid/groupid] : [C/CS]-[configId/cfg_collectionId]', '0', '1614007740', '1');
INSERT INTO `auth_permission` VALUES ('2', 'GROUP', '1', 'root', '用户组1对配置集1有权限', '0', '1614007741', '1');
INSERT INTO `auth_permission` VALUES ('3', 'USER', '1', 'user', '用户1对用户操作有权限', '0', '1614007741', '1');
INSERT INTO `auth_permission` VALUES ('4', 'USER', '6', 'cfg', '用户6对配置有操作权限', '0', '1614007741', '1');
INSERT INTO `auth_permission` VALUES ('5', 'USER', '7', 'cfg', null, '0', '1614007741', '1');
INSERT INTO `auth_permission` VALUES ('6', 'USER', '10', 'cfg', null, '0', '1618768044', null);
INSERT INTO `auth_permission` VALUES ('7', 'USER', '11', 'cfg', null, '0', '1618768059', null);
INSERT INTO `auth_permission` VALUES ('8', 'USER', '12', 'cfg', null, '0', '1618768062', null);
INSERT INTO `auth_permission` VALUES ('9', 'USER', '13', 'cfg', null, '0', '1618768064', null);
INSERT INTO `auth_permission` VALUES ('10', 'USER', '14', 'cfg', null, '0', '1618768068', null);
INSERT INTO `auth_permission` VALUES ('11', 'USER', '15', 'cfg', null, '0', '1618768070', null);
INSERT INTO `auth_permission` VALUES ('12', 'USER', '16', 'cfg', null, '0', '1618768073', null);
INSERT INTO `auth_permission` VALUES ('13', 'USER', '17', 'cfg', null, '0', '1618768076', null);
INSERT INTO `auth_permission` VALUES ('14', 'USER', '18', 'cfg', null, '0', '1618768080', null);
INSERT INTO `auth_permission` VALUES ('15', 'USER', '19', 'cfg', null, '0', '1621143868544', null);
INSERT INTO `auth_permission` VALUES ('16', 'USER', '20', 'cfg', null, '0', '1622190289872', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='该表为用户与用户组对应表，一个组有多个用户，一个用户可以在多个组';

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1', '1', '1', '1614007740', '1', '0');
INSERT INTO `auth_user` VALUES ('2', '6', '1', '1614007740', '1', '0');
INSERT INTO `auth_user` VALUES ('3', '7', '1', '1614007740', '1', '0');
INSERT INTO `auth_user` VALUES ('4', '18', '1', '1622273122', '1', '1');
INSERT INTO `auth_user` VALUES ('5', '17', '1', '1622273503', '1', '1');
INSERT INTO `auth_user` VALUES ('6', '16', '1', '1622273512', '1', '1');
INSERT INTO `auth_user` VALUES ('7', '15', '1', '1622273517', '1', '0');
INSERT INTO `auth_user` VALUES ('8', '17', '1', '1622275869', '1', '1');
INSERT INTO `auth_user` VALUES ('9', '16', '1', '1622275975', '1', '1');
INSERT INTO `auth_user` VALUES ('10', '16', '1', '1622276077', '1', '1');
INSERT INTO `auth_user` VALUES ('11', '14', '1', '1622276082', '1', '0');
INSERT INTO `auth_user` VALUES ('12', '18', '1', '1622276133', '1', '1');
INSERT INTO `auth_user` VALUES ('13', '17', '1', '1622276134', '1', '1');
INSERT INTO `auth_user` VALUES ('14', '18', '1', '1622276140', '1', '1');
INSERT INTO `auth_user` VALUES ('15', '17', '1', '1622276141', '1', '0');
INSERT INTO `auth_user` VALUES ('16', '18', '1', '1622276266', '1', '1');
INSERT INTO `auth_user` VALUES ('17', '18', '1', '1622276308', '1', '0');
INSERT INTO `auth_user` VALUES ('18', '16', '1', '1622276342', '1', '0');
INSERT INTO `auth_user` VALUES ('19', '1', '2', '1622276685', '1', '0');
INSERT INTO `auth_user` VALUES ('20', '1', '3', '1622276792', '1', '1');
INSERT INTO `auth_user` VALUES ('21', '1', '4', '1622277374', '1', '1');
INSERT INTO `auth_user` VALUES ('22', '1', '5', '1622277451', '1', '0');
INSERT INTO `auth_user` VALUES ('23', '12', '5', '1622277461', '1', '0');

-- ----------------------------
-- Table structure for cfg_audit
-- ----------------------------
DROP TABLE IF EXISTS `cfg_audit`;
CREATE TABLE `cfg_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `cfg_collection_id` int(11) NOT NULL COMMENT '对应cfg_config_collection表中的自增id',
  `content` varchar(255) DEFAULT '' COMMENT '上线/下线',
  `status` int(3) DEFAULT '0' COMMENT '审核状态：0待审核，1审核成功，2审核失败',
  `applicant_aid` int(11) NOT NULL COMMENT '申请人ID，对应account_id',
  `reviewer_aid` int(11) DEFAULT NULL COMMENT '审核者的account_id',
  `submit_time` bigint(20) DEFAULT NULL COMMENT '提交申请的时间',
  `handle_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cfg_audit
-- ----------------------------
INSERT INTO `cfg_audit` VALUES ('4', '9', '上线', '1', '6', '1', '1621602784', '1621672840');
INSERT INTO `cfg_audit` VALUES ('5', '1', '下线', '1', '6', '1', '1621662754', '1621672848');
INSERT INTO `cfg_audit` VALUES ('6', '9', '下线', '1', '6', '1', '1621672925', '1621672967');
INSERT INTO `cfg_audit` VALUES ('7', '9', '', '1', '6', '1', '1621673373', '1621673574');
INSERT INTO `cfg_audit` VALUES ('8', '9', '上线申请 | 配置集ID：9', '2', '6', '1', '1621771256', '1621771459');
INSERT INTO `cfg_audit` VALUES ('9', '9', '上线申请 | 配置集ID：9', '2', '6', '1', '1621773686', '1621773925');
INSERT INTO `cfg_audit` VALUES ('10', '9', '上线申请 | 配置集ID：9', '1', '6', '1', '1621773963', '1621773976');
INSERT INTO `cfg_audit` VALUES ('11', '9', '下线申请 | 配置集ID：9', '1', '6', '1', '1621774585', '1621835898');
INSERT INTO `cfg_audit` VALUES ('12', '9', '下线申请 | 配置集ID：9', '1', '6', '1', '1621836600', '1621836638');
INSERT INTO `cfg_audit` VALUES ('13', '9', '上线申请 | 配置集ID：9', '1', '6', '1', '1621836665', '1621836677');
INSERT INTO `cfg_audit` VALUES ('14', '17', '上线申请 | 配置集ID：17', '1', '1', '12', '1621951917', '1621951932');
INSERT INTO `cfg_audit` VALUES ('15', '17', '上线申请 | 配置集ID：17', '0', '1', null, '1622008410', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;

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
INSERT INTO `cfg_config` VALUES ('15', '3', 'draft_pwd_JASON', 'passwd', 'p@ssw0rd', '1', '1', 'root', 'root', '1621342243', '1614146788');
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
INSERT INTO `cfg_config` VALUES ('31', '3', 'draft_pwd_JASON', 'passwd', 'p@ssw0rd111', '0', '1', 'root', 'root', '1621342243', '1614146788');
INSERT INTO `cfg_config` VALUES ('32', '3', 'test', 'name', 'aaa', '0', '1', 'root', 'root', '1621342434', '1621342434');
INSERT INTO `cfg_config` VALUES ('33', '9', 'test', '11', '222', '0', '1', 'root', 'root', '1621524994', '1621524994');
INSERT INTO `cfg_config` VALUES ('34', '10', 'cfg_1', 'name', 'Mark', '0', '1', 'newuser', 'newuser', '1621663383', '1621663383');
INSERT INTO `cfg_config` VALUES ('35', '9', '添加配置', 'ok', 'yes', '0', '1', 'newuser', 'newuser', '1621771246', '1621771246');
INSERT INTO `cfg_config` VALUES ('36', '11', 'test', '11', '222', '0', '1', 'root', 'root', '1621524994', '1621524994');
INSERT INTO `cfg_config` VALUES ('37', '11', '添加配置', 'ok', 'yes', '0', '1', 'newuser', 'newuser', '1621771246', '1621771246');
INSERT INTO `cfg_config` VALUES ('38', '11', 'test', '1', '1', '0', '1', 'root', 'root', '1621836705412', '1621836705');
INSERT INTO `cfg_config` VALUES ('39', '17', 'config', 'key1', 'val1', '0', '1', 'newuser3', 'newuser3', '1621951855', '1621951855');
INSERT INTO `cfg_config` VALUES ('40', '17', 'new', '1', '2', '0', '1', 'root', 'root', '1621951915', '1621951915');
INSERT INTO `cfg_config` VALUES ('41', '18', 'test', '1', '222', '0', '1', 'newuser3', 'newuser3', '1622027776', '1622027776');

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cfg_config_collection
-- ----------------------------
INSERT INTO `cfg_config_collection` VALUES ('1', '1', 'ct_test_1', '0', '0', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config_collection` VALUES ('2', '2', 'chat', '0', '0', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config_collection` VALUES ('3', '3', 'draft_1', '0', '1', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config_collection` VALUES ('5', '5', 'ct_test_1', '0', '1', 'root', 'root', '1614146788', '1614146788');
INSERT INTO `cfg_config_collection` VALUES ('6', '6', 'chat', '1', '1', 'root', 'root', '1618584164', '1614146788');
INSERT INTO `cfg_config_collection` VALUES ('8', '8', 'test_cfg_coll', '1', '1', 'root', 'root', '1618938482', '1618938482');
INSERT INTO `cfg_config_collection` VALUES ('9', '9', '测试配置权限', '0', '1', 'root', 'root', '1621524977', '1621524977');
INSERT INTO `cfg_config_collection` VALUES ('10', '10', 'newUserConfig', '0', '1', 'newuser', 'newuser', '1621662893', '1621662893');
INSERT INTO `cfg_config_collection` VALUES ('11', '11', '测试配置权限', '1', '1', 'root', 'root', '1621836705', '1621524977');
INSERT INTO `cfg_config_collection` VALUES ('12', '12', 'newnewconfig', '1', '1', 'newuser3', 'newuser3', '1621951561', '1621951561');
INSERT INTO `cfg_config_collection` VALUES ('13', '13', 'newnewconfig', '1', '1', 'newuser3', 'newuser3', '1621951669', '1621951669');
INSERT INTO `cfg_config_collection` VALUES ('14', '14', 'newnewconfig', '1', '1', 'newuser3', 'newuser3', '1621951738', '1621951738');
INSERT INTO `cfg_config_collection` VALUES ('15', '15', 'newnewconfig', '1', '1', 'newuser3', 'newuser3', '1621951774', '1621951774');
INSERT INTO `cfg_config_collection` VALUES ('16', '16', 'ceshi', '1', '1', 'root', 'root', '1621951811', '1621951811');
INSERT INTO `cfg_config_collection` VALUES ('17', '17', 'newnewconfig', '1', '1', 'newuser3', 'newuser3', '1621951840', '1621951840');
INSERT INTO `cfg_config_collection` VALUES ('18', '18', 'newuser3_conf', '0', '0', 'newuser3', 'newuser3', '1622027707', '1622027707');

-- ----------------------------
-- Table structure for cfg_permission
-- ----------------------------
DROP TABLE IF EXISTS `cfg_permission`;
CREATE TABLE `cfg_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '0=用户权限；1=用户组权限',
  `account_id` int(11) DEFAULT '0' COMMENT '用户ID：account_id',
  `group_id` int(11) DEFAULT '0' COMMENT '用户组ID',
  `collection_id` int(11) NOT NULL DEFAULT '0' COMMENT '配置集合ID',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `create_username` varchar(255) DEFAULT NULL COMMENT '创建者username',
  `create_account_id` int(11) DEFAULT NULL COMMENT '创建者用户ID',
  `is_del` int(4) DEFAULT '0' COMMENT '软删除标志位，1删除，0存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cfg_permission
-- ----------------------------
INSERT INTO `cfg_permission` VALUES ('1', '0', '1', '0', '1', '1614146788', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('2', '1', '0', '1', '1', '1614146788', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('3', '0', '1', '0', '9', '1621524977', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('4', '0', '6', '0', '9', '1621524977', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('5', '0', '6', null, '10', '1621662893', 'newuser', '6', '0');
INSERT INTO `cfg_permission` VALUES ('6', '0', '1', '0', '11', '1621662893', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('7', '0', '19', '0', '9', '1621936988', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('8', '0', '19', '0', '9', '1621937047', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('9', '0', '19', '0', '9', '1621937109', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('10', '0', '19', '0', '9', '1621941694', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('11', '1', '0', '1', '9', '1621947924', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('12', '1', '0', '1', '9', '1621948112', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('13', '0', '10', '0', '9', '1621949030', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('14', '0', '11', '0', '9', '1621949031', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('15', '0', '12', '0', '9', '1621949032', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('16', '0', '13', '0', '9', '1621949032', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('17', '0', '14', '0', '9', '1621949033', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('18', '0', '15', '0', '9', '1621949033', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('19', '0', '16', '0', '9', '1621949034', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('20', '0', '17', '0', '9', '1621949034', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('21', '0', '18', '0', '9', '1621949035', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('22', '0', '12', null, '12', '1621951561', 'newuser3', '12', '0');
INSERT INTO `cfg_permission` VALUES ('23', '0', '12', null, '13', '1621951669', 'newuser3', '12', '0');
INSERT INTO `cfg_permission` VALUES ('24', '0', '12', null, '14', '1621951738', 'newuser3', '12', '0');
INSERT INTO `cfg_permission` VALUES ('25', '0', '12', null, '15', '1621951774', 'newuser3', '12', '0');
INSERT INTO `cfg_permission` VALUES ('26', '0', '1', null, '16', '1621951811', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('27', '0', '12', null, '17', '1621951840', 'newuser3', '12', '0');
INSERT INTO `cfg_permission` VALUES ('28', '0', '1', '0', '17', '1621951891', 'newuser3', '12', '0');
INSERT INTO `cfg_permission` VALUES ('29', '0', '12', null, '18', '1622027707', 'newuser3', '12', '0');
INSERT INTO `cfg_permission` VALUES ('30', '0', '1', '0', '18', '1622028650', 'newuser3', '12', '1');
INSERT INTO `cfg_permission` VALUES ('31', '0', '1', '0', '18', '1622028655', 'newuser3', '12', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '1', '白月光', '18731302887', 'https://tva3.sinaimg.cn/large/9bd9b167ly1g1p9q62elej20b40b40te.jpg', null, '无', '1614007740', '0');
INSERT INTO `user_info` VALUES ('2', '6', 'newuser', null, null, '1616236555', null, '1616236555', '0');
INSERT INTO `user_info` VALUES ('3', '7', 'Leo', null, null, '1616237760', null, '1616237760', '0');
INSERT INTO `user_info` VALUES ('4', '10', 'newuser1', null, null, '1618768044', null, '1618768044', '0');
INSERT INTO `user_info` VALUES ('5', '11', 'newuser2', null, null, '1618768059', null, '1618768059', '0');
INSERT INTO `user_info` VALUES ('6', '12', 'newuser3', null, null, '1618768062', null, '1618768062', '0');
INSERT INTO `user_info` VALUES ('7', '13', 'newuser4', null, null, '1618768064', null, '1618768064', '0');
INSERT INTO `user_info` VALUES ('8', '14', 'newuser5', null, null, '1618768068', null, '1618768068', '0');
INSERT INTO `user_info` VALUES ('9', '15', 'newuser6', null, null, '1618768070', null, '1618768070', '0');
INSERT INTO `user_info` VALUES ('10', '16', 'newuser7', null, null, '1618768073', null, '1618768073', '0');
INSERT INTO `user_info` VALUES ('11', '17', 'newuser8', null, null, '1618768076', null, '1618768076', '0');
INSERT INTO `user_info` VALUES ('12', '18', 'newuser9', null, null, '1618768080', null, '1618768080', '0');
INSERT INTO `user_info` VALUES ('13', '19', 'guest', null, null, '1621143868541', null, '1621143868541', '0');
INSERT INTO `user_info` VALUES ('14', '20', 'jason', null, null, '1622190289869', null, '1622190289869', '0');
