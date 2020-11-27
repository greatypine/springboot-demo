/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 16/08/2020 10:57:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_demo
-- ----------------------------
DROP TABLE IF EXISTS `user_demo`;
CREATE TABLE `user_demo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(3) NULL DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_demo
-- ----------------------------
INSERT INTO `user_demo` VALUES (1, 'aaa', 'aaa', 22, 'aaa@126.com', '2020-08-14 10:55:21');
INSERT INTO `user_demo` VALUES (2, 'bbb', 'bbb', 33, 'bbb@126.com', '2020-08-15 10:55:29');
INSERT INTO `user_demo` VALUES (3, 'ccc', 'ccc', 44, 'ccc@126.com', '2020-08-16 10:55:33');

SET FOREIGN_KEY_CHECKS = 1;
