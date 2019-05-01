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

 Date: 01/05/2019 21:47:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for colliery_user
-- ----------------------------
DROP TABLE IF EXISTS `colliery_user`;
CREATE TABLE `colliery_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `role_id` int(20) DEFAULT NULL COMMENT '角色id',
  `depart_id` int(20) DEFAULT NULL COMMENT '部门id',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名称',
  `user_pwd` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改时间',
  `modify_date` timestamp NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
