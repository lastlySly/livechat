/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : livechat

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 13/07/2018 19:45:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city_sheet
-- ----------------------------
DROP TABLE IF EXISTS `city_sheet`;
CREATE TABLE `city_sheet`  (
  `city_id` int(11) NOT NULL COMMENT '城市ID',
  `city_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市名字',
  `city_provinceid` int(11) NULL DEFAULT NULL COMMENT '城市所属省份ID（指向省份表province_sheet，外键）',
  PRIMARY KEY (`city_id`) USING BTREE,
  INDEX `fk_provincesheet_citysheet`(`city_provinceid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for friendgroups_sheet
-- ----------------------------
DROP TABLE IF EXISTS `friendgroups_sheet`;
CREATE TABLE `friendgroups_sheet`  (
  `friendgroups_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '好友分组id',
  `friendgroups_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `friendgroups_userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID，分组所属（外键）',
  PRIMARY KEY (`friendgroups_id`) USING BTREE,
  INDEX `fk_usersheet_friendgroupssheet`(`friendgroups_userid`) USING BTREE,
  CONSTRAINT `fk_usersheet_friendgroupssheet` FOREIGN KEY (`friendgroups_userid`) REFERENCES `userinfo_sheet` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friendgroups_sheet
-- ----------------------------
INSERT INTO `friendgroups_sheet` VALUES (3, '好友', 'e6a83fb1-f56d-472e-9c1d-f570992cfdbd');
INSERT INTO `friendgroups_sheet` VALUES (4, '家人', 'e6a83fb1-f56d-472e-9c1d-f570992cfdbd');
INSERT INTO `friendgroups_sheet` VALUES (5, '好友', 'ff8b5efa-e7f2-4601-a424-5f1e606c0b16');
INSERT INTO `friendgroups_sheet` VALUES (6, '家人', 'ff8b5efa-e7f2-4601-a424-5f1e606c0b16');
INSERT INTO `friendgroups_sheet` VALUES (7, '好友', 'f5bd0d11-6b26-4472-907e-5b9d27038d3e');
INSERT INTO `friendgroups_sheet` VALUES (8, '家人', 'f5bd0d11-6b26-4472-907e-5b9d27038d3e');
INSERT INTO `friendgroups_sheet` VALUES (9, '好友', '5f00f6a9-32f4-4607-89c9-fc520ebeb556');
INSERT INTO `friendgroups_sheet` VALUES (10, '家人', '5f00f6a9-32f4-4607-89c9-fc520ebeb556');
INSERT INTO `friendgroups_sheet` VALUES (11, '好友', 'a62bf61b-0ee2-4bcf-afca-da48b5246ed6');
INSERT INTO `friendgroups_sheet` VALUES (12, '家人', 'a62bf61b-0ee2-4bcf-afca-da48b5246ed6');
INSERT INTO `friendgroups_sheet` VALUES (13, '好友', '12f398f8-45e4-4368-b991-3e8682f23bdc');
INSERT INTO `friendgroups_sheet` VALUES (14, '家人', '12f398f8-45e4-4368-b991-3e8682f23bdc');
INSERT INTO `friendgroups_sheet` VALUES (15, '好友', '4aa72df3-bd0f-4c83-a402-b576cd2cb41a');
INSERT INTO `friendgroups_sheet` VALUES (16, '家人', '4aa72df3-bd0f-4c83-a402-b576cd2cb41a');

-- ----------------------------
-- Table structure for friends_sheet
-- ----------------------------
DROP TABLE IF EXISTS `friends_sheet`;
CREATE TABLE `friends_sheet`  (
  `friends_id` int(11) NOT NULL AUTO_INCREMENT,
  `friends_friendid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '朋友的ID(外键)',
  `friends_userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自己的ID（外键）',
  `friends_remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注昵称',
  `friends_friendgroupsid` int(11) NULL DEFAULT NULL COMMENT '所属分组（外键）',
  PRIMARY KEY (`friends_id`) USING BTREE,
  INDEX `fk_usersheet_friendssheet_friendid`(`friends_friendid`) USING BTREE,
  INDEX `fk_usersheet_friendssheet_userid`(`friends_userid`) USING BTREE,
  INDEX `fk_friendgroupssheet_friendssheet`(`friends_friendgroupsid`) USING BTREE,
  CONSTRAINT `fk_friendgroupssheet_friendssheet` FOREIGN KEY (`friends_friendgroupsid`) REFERENCES `friendgroups_sheet` (`friendgroups_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_usersheet_friendssheet_friendid` FOREIGN KEY (`friends_friendid`) REFERENCES `userinfo_sheet` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_usersheet_friendssheet_userid` FOREIGN KEY (`friends_userid`) REFERENCES `userinfo_sheet` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friends_sheet
-- ----------------------------
INSERT INTO `friends_sheet` VALUES (5, '4aa72df3-bd0f-4c83-a402-b576cd2cb41a', 'ff8b5efa-e7f2-4601-a424-5f1e606c0b16', '小弟', 6);
INSERT INTO `friends_sheet` VALUES (6, 'ff8b5efa-e7f2-4601-a424-5f1e606c0b16', '4aa72df3-bd0f-4c83-a402-b576cd2cb41a', '大哥', 16);
INSERT INTO `friends_sheet` VALUES (7, '12f398f8-45e4-4368-b991-3e8682f23bdc', '4aa72df3-bd0f-4c83-a402-b576cd2cb41a', '校友', 15);
INSERT INTO `friends_sheet` VALUES (8, '4aa72df3-bd0f-4c83-a402-b576cd2cb41a', '12f398f8-45e4-4368-b991-3e8682f23bdc', '小友', 13);

-- ----------------------------
-- Table structure for messages_sheet
-- ----------------------------
DROP TABLE IF EXISTS `messages_sheet`;
CREATE TABLE `messages_sheet`  (
  `messages_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `messages_postmessages` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '消息内容',
  `messages_status` bit(1) NULL DEFAULT NULL COMMENT '接收状态',
  `messages_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `messages_typeid` int(11) NULL DEFAULT NULL COMMENT '消息类型ID（指向消息类型表messagestype_sheet，外键）',
  `messages_from_userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送者ID（指向用户表user_sheet，外键）',
  `messages_to_userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收者ID（指向用户表user_sheet，外键）',
  PRIMARY KEY (`messages_id`) USING BTREE,
  INDEX `fk_usersheet_messagessheet_from`(`messages_from_userid`) USING BTREE,
  INDEX `fk_usersheet_messagessheet_to`(`messages_to_userid`) USING BTREE,
  INDEX `fk_messagestypesheet_messagessheet`(`messages_typeid`) USING BTREE,
  CONSTRAINT `fk_usersheet_messagessheet_from` FOREIGN KEY (`messages_from_userid`) REFERENCES `userinfo_sheet` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_usersheet_messagessheet_to` FOREIGN KEY (`messages_to_userid`) REFERENCES `userinfo_sheet` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messages_sheet
-- ----------------------------
INSERT INTO `messages_sheet` VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for messagestype_sheet
-- ----------------------------
DROP TABLE IF EXISTS `messagestype_sheet`;
CREATE TABLE `messagestype_sheet`  (
  `messagestype_id` int(11) NOT NULL COMMENT '消息类型ID',
  `mesagestype_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型名称',
  PRIMARY KEY (`messagestype_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messagestype_sheet
-- ----------------------------
INSERT INTO `messagestype_sheet` VALUES (1, '系统消息');
INSERT INTO `messagestype_sheet` VALUES (2, '好友请求');
INSERT INTO `messagestype_sheet` VALUES (3, '普通消息');

-- ----------------------------
-- Table structure for nation_sheet
-- ----------------------------
DROP TABLE IF EXISTS `nation_sheet`;
CREATE TABLE `nation_sheet`  (
  `nation_id` int(11) NOT NULL COMMENT '国家ID',
  `nation_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家名',
  PRIMARY KEY (`nation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nation_sheet
-- ----------------------------
INSERT INTO `nation_sheet` VALUES (1, '中国');

-- ----------------------------
-- Table structure for permission_sheet
-- ----------------------------
DROP TABLE IF EXISTS `permission_sheet`;
CREATE TABLE `permission_sheet`  (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_rolename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for province_sheet
-- ----------------------------
DROP TABLE IF EXISTS `province_sheet`;
CREATE TABLE `province_sheet`  (
  `province_id` int(11) NOT NULL COMMENT '省份ID',
  `province_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份名称',
  `province_nationid` int(11) NULL DEFAULT NULL COMMENT '所属国家ID（指向国家表nation_sheet，外键）',
  PRIMARY KEY (`province_id`) USING BTREE,
  INDEX `fk_nationsheet_provincesheet`(`province_nationid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of province_sheet
-- ----------------------------
INSERT INTO `province_sheet` VALUES (1, '北京市', 1);
INSERT INTO `province_sheet` VALUES (2, '天津市', 1);
INSERT INTO `province_sheet` VALUES (3, '上海市', 1);
INSERT INTO `province_sheet` VALUES (4, '重庆市', 1);
INSERT INTO `province_sheet` VALUES (5, '河北省', 1);
INSERT INTO `province_sheet` VALUES (6, '山西省', 1);
INSERT INTO `province_sheet` VALUES (7, '台湾省', 1);
INSERT INTO `province_sheet` VALUES (8, '辽宁省', 1);
INSERT INTO `province_sheet` VALUES (9, '吉林省', 1);
INSERT INTO `province_sheet` VALUES (10, '黑龙江省', 1);
INSERT INTO `province_sheet` VALUES (11, '江苏省', 1);
INSERT INTO `province_sheet` VALUES (12, '浙江省', 1);
INSERT INTO `province_sheet` VALUES (13, '安徽省', 1);
INSERT INTO `province_sheet` VALUES (14, '福建省', 1);
INSERT INTO `province_sheet` VALUES (15, '江西省', 1);
INSERT INTO `province_sheet` VALUES (16, '山东省', 1);
INSERT INTO `province_sheet` VALUES (17, '河南省', 1);
INSERT INTO `province_sheet` VALUES (18, '湖北省', 1);
INSERT INTO `province_sheet` VALUES (19, '湖南省', 1);
INSERT INTO `province_sheet` VALUES (20, '广东省', 1);
INSERT INTO `province_sheet` VALUES (21, '甘肃省', 1);
INSERT INTO `province_sheet` VALUES (22, '四川省', 1);
INSERT INTO `province_sheet` VALUES (23, '贵州省', 1);
INSERT INTO `province_sheet` VALUES (24, '海南省', 1);
INSERT INTO `province_sheet` VALUES (25, '云南省', 1);
INSERT INTO `province_sheet` VALUES (26, '青海省', 1);
INSERT INTO `province_sheet` VALUES (27, '陕西省', 1);
INSERT INTO `province_sheet` VALUES (28, '广西壮族自治区', 1);
INSERT INTO `province_sheet` VALUES (29, '西藏自治区', 1);
INSERT INTO `province_sheet` VALUES (30, '宁夏回族自治区', 1);
INSERT INTO `province_sheet` VALUES (31, '新疆维吾尔自治区', 1);
INSERT INTO `province_sheet` VALUES (32, '内蒙古自治区', 1);
INSERT INTO `province_sheet` VALUES (33, '澳门特别行政区', 1);
INSERT INTO `province_sheet` VALUES (34, '香港特别行政区', 1);

-- ----------------------------
-- Table structure for roles_sheet
-- ----------------------------
DROP TABLE IF EXISTS `roles_sheet`;
CREATE TABLE `roles_sheet`  (
  `roles_id` int(11) NOT NULL AUTO_INCREMENT,
  `roles_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roles_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roles_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles_sheet
-- ----------------------------
INSERT INTO `roles_sheet` VALUES (11, 'lastly', 'user');
INSERT INTO `roles_sheet` VALUES (12, 'mu10nian18', 'user');
INSERT INTO `roles_sheet` VALUES (13, 'lastlysly', 'user');
INSERT INTO `roles_sheet` VALUES (14, 'cotesl', 'user');
INSERT INTO `roles_sheet` VALUES (15, 'lastly01', 'user');
INSERT INTO `roles_sheet` VALUES (16, 'lastly02', 'user');
INSERT INTO `roles_sheet` VALUES (17, 'admin22', 'user');
INSERT INTO `roles_sheet` VALUES (18, 'lastly', 'user');

-- ----------------------------
-- Table structure for userinfo_sheet
-- ----------------------------
DROP TABLE IF EXISTS `userinfo_sheet`;
CREATE TABLE `userinfo_sheet`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `user_login_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登陆账户',
  `user_nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登陆密码',
  `user_gender` bit(1) NULL DEFAULT NULL COMMENT '用户性别',
  `user_birthday` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `user_telephone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_motto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名，座右铭',
  `user_synopsis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `user_nation_id` int(11) NULL DEFAULT NULL COMMENT '国家（指向国家表nation_sheet，外键）',
  `user_province_id` int(11) NULL DEFAULT NULL COMMENT '省份（指向省份表province_sheet，外键）',
  `user_city_id` int(11) NULL DEFAULT NULL COMMENT '城市(指向城市表city_sheet，外键)',
  `user_userstate_id` int(11) NULL DEFAULT NULL COMMENT '用户状态ID（指向用户状态表userstate_sheet，外键）',
  `user_realname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户真实姓名',
  `user_age` int(11) NULL DEFAULT NULL COMMENT '用户年龄',
  `user_vocation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `user_headportrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `user_password_salt` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加盐',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_login_id_unique`(`user_login_id`) USING BTREE COMMENT '用户登录账号唯一',
  INDEX `fk_nationsheet_usersheet`(`user_nation_id`) USING BTREE,
  INDEX `fk_citysheet_usersheet`(`user_city_id`) USING BTREE,
  INDEX `fk_provincesheet_usersheet`(`user_province_id`) USING BTREE,
  INDEX `fk_userstatesheet_usersheet`(`user_userstate_id`) USING BTREE,
  CONSTRAINT `fk_userstatesheet_usersheet` FOREIGN KEY (`user_userstate_id`) REFERENCES `userstate_sheet` (`userstate_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo_sheet
-- ----------------------------
INSERT INTO `userinfo_sheet` VALUES ('12f398f8-45e4-4368-b991-3e8682f23bdc', 'admin22', '那时雨', '88505542abcf4ab991bfb33bd43dc575', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'img/default_head.png', 'admin22');
INSERT INTO `userinfo_sheet` VALUES ('4aa72df3-bd0f-4c83-a402-b576cd2cb41a', 'lastly', '篝', 'f8b5b7a90d38a1162b5b00e4ce1b4704', b'1', '2018-07-11 22:00:00', '12345678901', '123@163.com', '没有人会相信未来，谁都无法接受未来...', 'null', NULL, 3, 7, NULL, NULL, 21, 'java开发工程师', 'img/default_head.png', 'lastly');
INSERT INTO `userinfo_sheet` VALUES ('5f00f6a9-32f4-4607-89c9-fc520ebeb556', 'lastly01', '那时', '98056c112fe8c27c75d1919c54fa5b67', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'img/default_head.png', 'lastly01');
INSERT INTO `userinfo_sheet` VALUES ('a62bf61b-0ee2-4bcf-afca-da48b5246ed6', 'lastly02', '汐', '29f39bfe0ae2074ef58191bc5894d51f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'img/default_head.png', 'lastly02');
INSERT INTO `userinfo_sheet` VALUES ('e6a83fb1-f56d-472e-9c1d-f570992cfdbd', 'mu10nian18', '汐', '1ba0a8b71c4eac42bb51d079cd159d4a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'img/default_head.png', 'mu10nian18');
INSERT INTO `userinfo_sheet` VALUES ('f5bd0d11-6b26-4472-907e-5b9d27038d3e', 'cotesl', 'cote', '9939e2c55496068b7d46a44a74e0a760', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'img/default_head.png', 'cotesl');
INSERT INTO `userinfo_sheet` VALUES ('ff8b5efa-e7f2-4601-a424-5f1e606c0b16', 'lastlysly', '汐', 'd6352e24e6dcf695ae1ba6d15c6244f0', b'1', NULL, '', '', '这个世界没有奇迹..', '', NULL, 3, 7, NULL, NULL, 16, '', 'img/default_head.png', 'lastlysly');

-- ----------------------------
-- Table structure for userstate_sheet
-- ----------------------------
DROP TABLE IF EXISTS `userstate_sheet`;
CREATE TABLE `userstate_sheet`  (
  `userstate_id` int(11) NOT NULL AUTO_INCREMENT,
  `userstate_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态名字',
  PRIMARY KEY (`userstate_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
