/*
 Navicat Premium Dump SQL

 Source Server         : 192.168.73.100_3306
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37-0ubuntu0.22.04.3)
 Source Host           : 192.168.73.100:3306
 Source Schema         : scaffold

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37-0ubuntu0.22.04.3)
 File Encoding         : 65001

 Date: 26/06/2024 17:07:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for machine
-- ----------------------------
DROP TABLE IF EXISTS `machine`;
CREATE TABLE `machine`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机器名',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机器IP',
  `port` smallint UNSIGNED NOT NULL DEFAULT 22 COMMENT '用于SSH链接的端口',
  `operating_system` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主机操作系统',
  `virtual` tinyint UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否为虚拟机（0：不是；1：是）',
  `enabled` tinyint UNSIGNED NOT NULL COMMENT '是否可用（0：不可用；1：可用）',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注信息',
  `created_user` bigint UNSIGNED NULL DEFAULT NULL COMMENT '创建用户',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `latest_time` datetime NULL DEFAULT NULL COMMENT '最近登录时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `updated_user` bigint NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '主机表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for resource_auth
-- ----------------------------
DROP TABLE IF EXISTS `resource_auth`;
CREATE TABLE `resource_auth`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `privileged` tinyint(1) NOT NULL COMMENT '是否为特权账号',
  `enabled` tinyint(1) NOT NULL COMMENT '是否可用',
  `machine_id` bigint UNSIGNED NOT NULL COMMENT '所属机器ID',
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注信息',
  `latest_conn_time` datetime NULL DEFAULT NULL COMMENT '上次连接时间',
  `effective_end_time` datetime NOT NULL DEFAULT ((now() + interval 1 year)) COMMENT '有效结束时间',
  `created_time` datetime NULL DEFAULT (now()) COMMENT '创建时间',
  `created_user` bigint NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '主机账号' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '物理主键',
  `operation` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作描述',
  `uri` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求URI',
  `req_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法',
  `method_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '操作方法名',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '参数内容',
  `resp_time` bigint NULL DEFAULT NULL COMMENT '耗时',
  `client_addr` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端IP',
  `remote_addr` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '中转IP',
  `result` tinyint NULL DEFAULT NULL COMMENT '请求结果',
  `reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '失败原因',
  `operator` bigint NULL DEFAULT NULL COMMENT '操作人',
  `operated_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户操作日志表' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
