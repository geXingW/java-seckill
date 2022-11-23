/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.100.101
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : 192.168.100.101:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 23/11/2022 14:42:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint(0) NOT NULL,
  `p_id` bigint(0) NOT NULL,
  `p_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `p_quantity` int(0) NOT NULL DEFAULT 0,
  `status` tinyint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `stock` int(0) NOT NULL DEFAULT 0,
  `sale_cnt` int(0) NOT NULL DEFAULT 0,
  `version` bigint(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
