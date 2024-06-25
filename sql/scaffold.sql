/*
 Navicat Premium Dump SQL

 Source Server         : 192.168.156.24_3306
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37-0ubuntu0.24.04.1)
 Source Host           : 192.168.156.24:3306
 Source Schema         : scaffold

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37-0ubuntu0.24.04.1)
 File Encoding         : 65001

 Date: 26/06/2024 00:51:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log` VALUES ('FFDEB6F2CA4748FDBD82A4DF1AC10D3D', '', '/api/scaffold/hello', 'GET', 'test', 'username=nihao username: nihao', 9, '198.18.0.1', '0:0:0:0:0:0:0:1', 1, NULL, 1, '2024-06-26 00:48:32');

SET FOREIGN_KEY_CHECKS = 1;
