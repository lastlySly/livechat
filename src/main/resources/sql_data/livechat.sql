/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 47.106.202.5:3306
 Source Schema         : livechat

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 01/08/2018 10:09:38
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
-- Table structure for friend_application_sheet
-- ----------------------------
DROP TABLE IF EXISTS `friend_application_sheet`;
CREATE TABLE `friend_application_sheet`  (
  `friend_application_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '好友申请表ID',
  `friend_application_from` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
  `friend_application_to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送位置',
  `friend_application_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请成功后的备注',
  `friend_application_group` int(11) NULL DEFAULT NULL COMMENT '分组',
  `friend_application_time` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `friend_application_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请时的消息',
  `friend_application_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请状态(通过，拒绝，未处理)',
  `friend_application_refuse_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被拒绝的原因',
  PRIMARY KEY (`friend_application_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for friendgroups_sheet
-- ----------------------------
DROP TABLE IF EXISTS `friendgroups_sheet`;
CREATE TABLE `friendgroups_sheet`  (
  `friendgroups_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '好友分组id',
  `friendgroups_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `friendgroups_user_loginid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登陆ID，分组所属（外键）',
  `friendgroups_grade` int(255) NULL DEFAULT NULL COMMENT '分组等级（1为系统级别（不可删除），2为为用户级别（可删除））',
  PRIMARY KEY (`friendgroups_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for friends_sheet
-- ----------------------------
DROP TABLE IF EXISTS `friends_sheet`;
CREATE TABLE `friends_sheet`  (
  `friends_id` int(11) NOT NULL AUTO_INCREMENT,
  `friends_friend_loginid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '朋友的ID(外键)',
  `friends_user_loginid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自己的ID（外键）',
  `friends_remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注昵称',
  `friends_friendgroupsid` int(11) NULL DEFAULT NULL COMMENT '所属分组（外键）',
  PRIMARY KEY (`friends_id`) USING BTREE
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
  `messages_from_loginid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送者登陆ID（指向用户表user_sheet，外键）',
  `messages_to_loginid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收者登陆ID（指向用户表user_sheet，外键）',
  PRIMARY KEY (`messages_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for messagestype_sheet
-- ----------------------------
DROP TABLE IF EXISTS `messagestype_sheet`;
CREATE TABLE `messagestype_sheet`  (
  `messagestype_id` int(11) NOT NULL COMMENT '消息类型ID',
  `mesagestype_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型名称(1系统消息,2好友请求,3普通消息,4系统消息上下线通知,5上线,6下线)',
  PRIMARY KEY (`messagestype_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messagestype_sheet
-- ----------------------------
INSERT INTO `messagestype_sheet` VALUES (1, '系统消息');
INSERT INTO `messagestype_sheet` VALUES (2, '好友请求');
INSERT INTO `messagestype_sheet` VALUES (3, '普通消息');
INSERT INTO `messagestype_sheet` VALUES (4, '系统消息上下线通知');
INSERT INTO `messagestype_sheet` VALUES (5, '上线');
INSERT INTO `messagestype_sheet` VALUES (6, '下线');

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
-- Table structure for roles_sheet
-- ----------------------------
DROP TABLE IF EXISTS `roles_sheet`;
CREATE TABLE `roles_sheet`  (
  `roles_id` int(11) NOT NULL AUTO_INCREMENT,
  `roles_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roles_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roles_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  UNIQUE INDEX `user_login_id_unique`(`user_login_id`) USING BTREE COMMENT '用户登录账号唯一'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
