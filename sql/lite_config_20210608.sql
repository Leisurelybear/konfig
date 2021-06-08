/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : lite_config

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2021-06-08 21:28:07
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
INSERT INTO `auth_account` VALUES ('20', 'jason', '$2a$10$PPiJ7te0lGq65uCKmrCDYe7ua1EzDUKsCLCsnR1wb7ZONRSZYc7Ci', null, 'jasonzhang666@163.com', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_group
-- ----------------------------
INSERT INTO `auth_group` VALUES ('1', 'wheel', '1', '1', '1614007740', '1', '0');
INSERT INTO `auth_group` VALUES ('2', 'car', '4', '1', '1622276685', '1', '0');
INSERT INTO `auth_group` VALUES ('3', '{\"groupName\":\"test\"}', '4', '1', '1622276792', '1', '1');
INSERT INTO `auth_group` VALUES ('4', 'test', '4', '1', '1622277374', '1', '1');
INSERT INTO `auth_group` VALUES ('5', 'test', '4', '1', '1622277451', '1', '1');
INSERT INTO `auth_group` VALUES ('6', '111', '4', '1', '1622517370', '1', '1');
INSERT INTO `auth_group` VALUES ('7', 'group-newuser', '4', '6', '1622701301', '6', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COMMENT='该表为用户与用户组对应表，一个组有多个用户，一个用户可以在多个组';

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
INSERT INTO `auth_user` VALUES ('15', '17', '1', '1622276141', '1', '1');
INSERT INTO `auth_user` VALUES ('16', '18', '1', '1622276266', '1', '1');
INSERT INTO `auth_user` VALUES ('17', '18', '1', '1622276308', '1', '1');
INSERT INTO `auth_user` VALUES ('18', '16', '1', '1622276342', '1', '1');
INSERT INTO `auth_user` VALUES ('19', '1', '2', '1622276685', '1', '0');
INSERT INTO `auth_user` VALUES ('20', '1', '3', '1622276792', '1', '1');
INSERT INTO `auth_user` VALUES ('21', '1', '4', '1622277374', '1', '1');
INSERT INTO `auth_user` VALUES ('22', '1', '5', '1622277451', '1', '1');
INSERT INTO `auth_user` VALUES ('23', '12', '5', '1622277461', '1', '1');
INSERT INTO `auth_user` VALUES ('24', '19', '5', '1622510304', '1', '1');
INSERT INTO `auth_user` VALUES ('25', '1', '6', '1622517370', '1', '1');
INSERT INTO `auth_user` VALUES ('26', '19', '6', '1622517376', '1', '1');
INSERT INTO `auth_user` VALUES ('27', '6', '7', '1622701301', '6', '0');
INSERT INTO `auth_user` VALUES ('28', '10', '7', '1622701309', '6', '0');
INSERT INTO `auth_user` VALUES ('29', '11', '7', '1622701310', '6', '0');
INSERT INTO `auth_user` VALUES ('30', '12', '7', '1622701311', '6', '0');
INSERT INTO `auth_user` VALUES ('31', '13', '7', '1622701311', '6', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

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
INSERT INTO `cfg_audit` VALUES ('16', '18', '下线申请 | 配置集ID：18', '0', '1', null, '1622277791', null);
INSERT INTO `cfg_audit` VALUES ('17', '10', '上线申请 | 配置集ID：10', '1', '1', '6', '1622513672', '1622513686');
INSERT INTO `cfg_audit` VALUES ('18', '19', '上线申请 | 配置集ID：19', '1', '6', '1', '1622517309', '1622517319');
INSERT INTO `cfg_audit` VALUES ('19', '19', '下线申请 | 配置集ID：19', '0', '6', null, '1622640386', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4;

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
INSERT INTO `cfg_config` VALUES ('42', '19', '123', '2323', '2323', '0', '1', 'newuser', 'newuser', '1622517287', '1622517287');
INSERT INTO `cfg_config` VALUES ('43', '20', '1', 'hello', 'world', '1', '1', 'root', 'root', '1622792557', '1622702820');
INSERT INTO `cfg_config` VALUES ('44', '20', '2', 'chinese', '中国人', '1', '1', 'root', 'root', '1622792569', '1622702839');
INSERT INTO `cfg_config` VALUES ('45', '20', 'name-1', 'hello', 'world', '1', '1', 'root', 'root', '1622792817', '1622702820');
INSERT INTO `cfg_config` VALUES ('46', '20', 'name-2', 'chinese', '中国人', '1', '1', 'root', 'root', '1622792695', '1622702839');
INSERT INTO `cfg_config` VALUES ('47', '20', 'name-3', 'chinese', '中国人', '1', '1', 'root', 'root', '1622792712', '1622702839');
INSERT INTO `cfg_config` VALUES ('48', '20', 'name-2', 'chinese', '中国人', '1', '1', 'root', 'root', '1622792737', '1622702839');
INSERT INTO `cfg_config` VALUES ('49', '20', 'name-3', 'chinese', '中国人', '1', '1', 'root', 'root', '1622792791', '1622702839');
INSERT INTO `cfg_config` VALUES ('50', '20', 'name-2', 'chinese', '中国人', '1', '1', 'root', 'root', '1622793035', '1622702839');
INSERT INTO `cfg_config` VALUES ('51', '20', 'name-4', 'hello', 'world', '1', '1', 'root', 'root', '1622792905', '1622702820');
INSERT INTO `cfg_config` VALUES ('52', '20', 'name-1', 'hello', 'world', '0', '1', 'root', 'root', '1622792905', '1622702820');
INSERT INTO `cfg_config` VALUES ('53', '20', 'name-0', 'chinese', '中国人', '1', '1', 'root', 'root', '1622793070', '1622702839');
INSERT INTO `cfg_config` VALUES ('54', '20', 'name-2', 'chinese', '中国人', '1', '1', 'root', 'root', '1622806628', '1622702839');
INSERT INTO `cfg_config` VALUES ('55', '20', 'name-3', 'chinese', '中国人', '0', '1', 'root', 'root', '1622806628', '1622702839');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

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
INSERT INTO `cfg_config_collection` VALUES ('19', '19', 'new', '0', '1', 'root', 'root', '1622517225', '1622517225');
INSERT INTO `cfg_config_collection` VALUES ('20', '20', 'test_conf', '0', '0', 'root', 'root', '1622702782', '1622702782');

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
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4;

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
INSERT INTO `cfg_permission` VALUES ('32', '1', '0', '5', '9', '1622277816', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('33', '1', '0', '2', '1', '1622510288', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('34', '0', '1', '0', '10', '1622513658', 'newuser', '6', '1');
INSERT INTO `cfg_permission` VALUES ('35', '0', '1', '0', '10', '1622513663', 'newuser', '6', '1');
INSERT INTO `cfg_permission` VALUES ('36', '0', '1', null, '19', '1622517225', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('37', '0', '13', '0', '19', '1622517244', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('38', '0', '14', '0', '19', '1622517245', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('39', '0', '6', '0', '19', '1622517264', 'root', '1', '1');
INSERT INTO `cfg_permission` VALUES ('40', '0', '6', '0', '19', '1622517283', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('41', '1', '0', '1', '19', '1622517297', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('42', '0', '1', null, '20', '1622702782', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('43', '0', '6', '0', '20', '1622702859', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('44', '0', '1', null, '-1', '1622702898', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('45', '0', '1', null, '-1', '1622702916', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('46', '0', '1', null, '-1', '1622703044', 'root', '1', '0');
INSERT INTO `cfg_permission` VALUES ('47', '0', '20', '0', '20', '1623044422', 'root', '1', '0');

-- ----------------------------
-- Table structure for op_log
-- ----------------------------
DROP TABLE IF EXISTS `op_log`;
CREATE TABLE `op_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of op_log
-- ----------------------------
INSERT INTO `op_log` VALUES ('1', '用户登录', '0', 'root', '1622618390', 'USER', null, null, '', '');
INSERT INTO `op_log` VALUES ('2', '用户登录', '0', 'newuser', '1622618449', 'USER', null, null, '', '');
INSERT INTO `op_log` VALUES ('3', '申请配置集上线/下线', '6', 'newuser', '1622640386', 'CONFIG', null, null, '', 'collectionId: 19');
INSERT INTO `op_log` VALUES ('4', '用户组删除', '6', 'newuser', '1622700990', 'GROUP', null, null, 'groupId：6', '');
INSERT INTO `op_log` VALUES ('5', '用户登录', '0', 'root', '1622701224', 'USER', null, null, '', 'username=root');
INSERT INTO `op_log` VALUES ('6', '用户组删除', '1', 'root', '1622701255', 'GROUP', null, null, 'groupId：5', '');
INSERT INTO `op_log` VALUES ('7', '用户登录', '0', 'newuser', '1622701283', 'USER', null, null, '', 'username=newuser');
INSERT INTO `op_log` VALUES ('8', '用户组创建', '6', 'newuser', '1622701301', 'GROUP', null, null, '', 'GroupCreateReq(groupName=group-newuser)');
INSERT INTO `op_log` VALUES ('9', '用户登录', '0', 'root', '1622702661', 'USER', null, null, '', 'username: root');
INSERT INTO `op_log` VALUES ('10', '创建配置集', '1', 'root', '1622702782', 'CONFIG', null, null, '', 'collectionName: test_conf');
INSERT INTO `op_log` VALUES ('11', '添加配置', '1', 'root', '1622702821', 'CONFIG', null, null, '', 'AddConfigReq(collectionId=20, configName=1, configKey=hello, configValue=world)');
INSERT INTO `op_log` VALUES ('12', '添加配置', '1', 'root', '1622702839', 'CONFIG', null, null, '', 'AddConfigReq(collectionId=20, configName=2, configKey=chinese, configValue=中国人)');
INSERT INTO `op_log` VALUES ('13', '添加权限', '1', 'root', '1622702859', 'PERMISSION', null, null, '', 'CreatePermissionReq(collectionId=20, accountIds=[6], groupIds=[])');
INSERT INTO `op_log` VALUES ('14', '配置集上线/下线', '1', 'root', '1622703224', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('15', '配置集上线/下线', '1', 'root', '1622703242', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('16', '配置集上线/下线', '1', 'root', '1622706236', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('17', '配置集上线/下线', '1', 'root', '1622706294', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('18', '配置集上线/下线', '1', 'root', '1622706309', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('19', '配置集上线/下线', '1', 'root', '1622706316', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('20', '配置集上线/下线', '1', 'root', '1622706319', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('21', '配置集上线/下线', '1', 'root', '1622706692', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('22', '配置集上线/下线', '1', 'root', '1622707082', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('23', '配置集上线/下线', '1', 'root', '1622707091', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('24', '配置集上线/下线', '1', 'root', '1622707096', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('25', '用户登录', '0', 'root', '1622791886', 'USER', null, null, '', 'username: root');
INSERT INTO `op_log` VALUES ('26', '配置集上线/下线', '1', 'root', '1622791896', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('27', '配置集上线/下线', '1', 'root', '1622792011', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('28', '配置集上线/下线', '1', 'root', '1622792017', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('29', '配置集上线/下线', '1', 'root', '1622792118', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('30', '配置集上线/下线', '1', 'root', '1622792120', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('31', '配置集上线/下线', '1', 'root', '1622792528', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('32', '配置集上线/下线', '1', 'root', '1622792537', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('33', '更新配置', '1', 'root', '1622792557', 'CONFIG', null, null, '', 'UpdateConfigReq(username=root, accountId=1, id=43, collectionId=20, cfgName=name-1, cfgKey=hello, cfgValue=world)');
INSERT INTO `op_log` VALUES ('34', '更新配置', '1', 'root', '1622792569', 'CONFIG', null, null, '', 'UpdateConfigReq(username=root, accountId=1, id=44, collectionId=20, cfgName=name-2, cfgKey=chinese, cfgValue=中国人)');
INSERT INTO `op_log` VALUES ('35', '配置集上线/下线', '1', 'root', '1622792604', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('36', '配置集上线/下线', '1', 'root', '1622792691', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('37', '更新配置', '1', 'root', '1622792695', 'CONFIG', null, null, '', 'UpdateConfigReq(username=root, accountId=1, id=46, collectionId=20, cfgName=name-3, cfgKey=chinese, cfgValue=中国人)');
INSERT INTO `op_log` VALUES ('38', '更新配置', '1', 'root', '1622792712', 'CONFIG', null, null, '', 'UpdateConfigReq(username=root, accountId=1, id=47, collectionId=20, cfgName=name-2, cfgKey=chinese, cfgValue=中国人)');
INSERT INTO `op_log` VALUES ('39', '更新配置', '1', 'root', '1622792737', 'CONFIG', null, null, '', 'UpdateConfigReq(username=root, accountId=1, id=48, collectionId=20, cfgName=name-3, cfgKey=chinese, cfgValue=中国人)');
INSERT INTO `op_log` VALUES ('40', '更新配置', '1', 'root', '1622792791', 'CONFIG', null, null, '', 'UpdateConfigReq(username=root, accountId=1, id=49, collectionId=20, cfgName=name-2, cfgKey=chinese, cfgValue=中国人)');
INSERT INTO `op_log` VALUES ('41', '更新配置', '1', 'root', '1622792817', 'CONFIG', null, null, '', 'UpdateConfigReq(username=root, accountId=1, id=45, collectionId=20, cfgName=name-4, cfgKey=hello, cfgValue=world)');
INSERT INTO `op_log` VALUES ('42', '更新配置', '1', 'root', '1622792905', 'CONFIG', null, null, '', 'UpdateConfigReq(username=root, accountId=1, id=51, collectionId=20, cfgName=name-1, cfgKey=hello, cfgValue=world)');
INSERT INTO `op_log` VALUES ('43', '更新配置', '1', 'root', '1622793035', 'CONFIG', null, null, '', 'UpdateConfigReq(username=root, accountId=1, id=50, collectionId=20, cfgName=name-0, cfgKey=chinese, cfgValue=中国人)');
INSERT INTO `op_log` VALUES ('44', '更新配置', '1', 'root', '1622793070', 'CONFIG', null, null, '', 'UpdateConfigReq(username=root, accountId=1, id=53, collectionId=20, cfgName=name-2, cfgKey=chinese, cfgValue=中国人)');
INSERT INTO `op_log` VALUES ('45', '配置集上线/下线', '1', 'root', '1622793090', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('46', '配置集上线/下线', '1', 'root', '1622793218', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('47', '配置集上线/下线', '1', 'root', '1622793222', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('48', '配置集上线/下线', '1', 'root', '1622793254', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('49', '配置集上线/下线', '1', 'root', '1622793272', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('50', '配置集上线/下线', '1', 'root', '1622793274', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('51', '配置集上线/下线', '1', 'root', '1622793302', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('52', '配置集上线/下线', '1', 'root', '1622793390', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('53', '配置集上线/下线', '1', 'root', '1622793391', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('54', '配置集上线/下线', '1', 'root', '1622793537', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('55', '配置集上线/下线', '1', 'root', '1622793541', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('56', '配置集上线/下线', '1', 'root', '1622793570', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('57', '配置集上线/下线', '1', 'root', '1622794054', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('58', '配置集上线/下线', '1', 'root', '1622794154', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('59', '配置集上线/下线', '1', 'root', '1622794284', 'CONFIG', null, null, '', 'collectionId: 19');
INSERT INTO `op_log` VALUES ('60', '配置集上线/下线', '1', 'root', '1622794355', 'CONFIG', null, null, '', 'collectionId: 19');
INSERT INTO `op_log` VALUES ('61', '配置集上线/下线', '1', 'root', '1622794381', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('62', '配置集上线/下线', '1', 'root', '1622794384', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('63', '配置集上线/下线', '1', 'root', '1622795849', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('64', '配置集上线/下线', '1', 'root', '1622795860', 'CONFIG', null, null, '', 'collectionId: 19');
INSERT INTO `op_log` VALUES ('65', '配置集上线/下线', '1', 'root', '1622795865', 'CONFIG', null, null, '', 'collectionId: 19');
INSERT INTO `op_log` VALUES ('66', '配置集上线/下线', '1', 'root', '1622796322', 'CONFIG', null, null, '', 'collectionId: 19');
INSERT INTO `op_log` VALUES ('67', '配置集上线/下线', '1', 'root', '1622796326', 'CONFIG', null, null, '', 'collectionId: 19');
INSERT INTO `op_log` VALUES ('68', '配置集上线/下线', '1', 'root', '1622796339', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('69', '配置集上线/下线', '1', 'root', '1622796340', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('70', '配置集上线/下线', '1', 'root', '1622796352', 'CONFIG', null, null, '', 'collectionId: 19');
INSERT INTO `op_log` VALUES ('71', '配置集上线/下线', '1', 'root', '1622806595', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('72', '更新配置', '1', 'root', '1622806628', 'CONFIG', null, null, '', 'UpdateConfigReq(username=root, accountId=1, id=54, collectionId=20, cfgName=name-3, cfgKey=chinese, cfgValue=中国人)');
INSERT INTO `op_log` VALUES ('73', '配置集上线/下线', '1', 'root', '1622806630', 'CONFIG', null, null, '', 'collectionId: 20');
INSERT INTO `op_log` VALUES ('74', '用户登录', '0', 'root', '1623043175', 'USER', null, null, '', 'username: root');
INSERT INTO `op_log` VALUES ('75', '修改用户密码', '1', 'root', '1623044133', 'USER', null, null, '', 'accountid：20');
INSERT INTO `op_log` VALUES ('76', '修改用户密码', '1', 'root', '1623044140', 'USER', null, null, '', 'accountid：20');
INSERT INTO `op_log` VALUES ('77', '添加权限', '1', 'root', '1623044422', 'PERMISSION', null, null, '', 'CreatePermissionReq(collectionId=20, accountIds=[20], groupIds=[])');

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
