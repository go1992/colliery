/*
 Navicat Premium Data Transfer

 Source Server         : 47.103.35.95
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 47.103.35.95:3306
 Source Schema         : coal_mine

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 01/05/2019 21:46:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for colliery_auth
-- ----------------------------
DROP TABLE IF EXISTS `colliery_auth`;
CREATE TABLE `colliery_auth` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id自增',
  `auth_name` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `auth_level` int(5) DEFAULT NULL COMMENT '权限级别',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户',
  `modify_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
