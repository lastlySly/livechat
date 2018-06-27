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

 Date: 27/06/2018 17:25:49
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
  INDEX `fk_provincesheet_citysheet`(`city_provinceid`) USING BTREE,
  CONSTRAINT `fk_provincesheet_citysheet` FOREIGN KEY (`city_provinceid`) REFERENCES `province_sheet` (`province_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  CONSTRAINT `fk_messagestypesheet_messagessheet` FOREIGN KEY (`messages_typeid`) REFERENCES `messagestype_sheet` (`messagestype_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_usersheet_messagessheet_from` FOREIGN KEY (`messages_from_userid`) REFERENCES `userinfo_sheet` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_usersheet_messagessheet_to` FOREIGN KEY (`messages_to_userid`) REFERENCES `userinfo_sheet` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for messagestype_sheet
-- ----------------------------
DROP TABLE IF EXISTS `messagestype_sheet`;
CREATE TABLE `messagestype_sheet`  (
  `messagestype_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息类型ID',
  `mesagestype_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型名称',
  PRIMARY KEY (`messagestype_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  INDEX `fk_nationsheet_provincesheet`(`province_nationid`) USING BTREE,
  CONSTRAINT `fk_nationsheet_provincesheet` FOREIGN KEY (`province_nationid`) REFERENCES `nation_sheet` (`nation_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles_sheet
-- ----------------------------
INSERT INTO `roles_sheet` VALUES (1, 'admin', 'user');
INSERT INTO `roles_sheet` VALUES (2, 'admin', 'user');
INSERT INTO `roles_sheet` VALUES (3, 'admin2', 'user');

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
  CONSTRAINT `fk_citysheet_usersheet` FOREIGN KEY (`user_city_id`) REFERENCES `city_sheet` (`city_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_nationsheet_usersheet` FOREIGN KEY (`user_nation_id`) REFERENCES `nation_sheet` (`nation_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_provincesheet_usersheet` FOREIGN KEY (`user_province_id`) REFERENCES `province_sheet` (`province_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_userstatesheet_usersheet` FOREIGN KEY (`user_userstate_id`) REFERENCES `userstate_sheet` (`userstate_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo_sheet
-- ----------------------------
INSERT INTO `userinfo_sheet` VALUES ('369fe261-ade4-4fc5-a4ca-42775a1e0376', 'admin', 'lastly', '038bdaf98f2037b31f1e75b5b4c9b26e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'img/default_head.png', 'admin');
INSERT INTO `userinfo_sheet` VALUES ('e14d3b09-bb5a-4df1-bcaf-1339c729f697', 'admin2', 'lastly', 'c3270271f36e5e795e54c2721896102e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'img/default_head.png', 'admin2');

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
