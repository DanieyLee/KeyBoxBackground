/*
 Navicat Premium Data Transfer

 Source Server         : 云返票务火车票和酒店
 Source Server Type    : MySQL
 Source Server Version : 50630
 Source Host           : 120.55.166.235:3306
 Source Schema         : keybox

 Target Server Type    : MySQL
 Target Server Version : 50630
 File Encoding         : 65001

 Date: 03/04/2019 17:45:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `databasechangelog`;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_croatian_ci NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_croatian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- ----------------------------
-- Records of databasechangelog
-- ----------------------------
BEGIN;
INSERT INTO `databasechangelog` VALUES ('00000000000001', 'jhipster', 'config/liquibase/changelog/00000000000000_initial_schema.xml', '2019-02-15 19:54:22', 1, 'EXECUTED', '7:df4a1dbcfdaf29d68b0d4c4a350b9535', 'createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...', '', NULL, '3.5.4', NULL, NULL, '0231662233');
INSERT INTO `databasechangelog` VALUES ('20190213032327-1', 'jhipster', 'config/liquibase/changelog/20190213032327_added_entity_Usertoken.xml', '2019-02-15 19:54:22', 2, 'EXECUTED', '7:ba3bdfae47348c3e2261ecc3686d4b80', 'createTable tableName=usertoken', '', NULL, '3.5.4', NULL, NULL, '0231662233');
INSERT INTO `databasechangelog` VALUES ('20190213032328-1', 'jhipster', 'config/liquibase/changelog/20190213032328_added_entity_Keyboxes.xml', '2019-02-15 19:54:22', 3, 'EXECUTED', '7:8b6a78ed4165d7c272f1738ce8b9949f', 'createTable tableName=keyboxes', '', NULL, '3.5.4', NULL, NULL, '0231662233');
INSERT INTO `databasechangelog` VALUES ('20190213032328-2', 'jhipster', 'config/liquibase/changelog/20190213032328_added_entity_constraints_Keyboxes.xml', '2019-02-15 19:54:23', 4, 'EXECUTED', '7:a7e3f61daa6f1b8318fc7d7af8e68f70', 'addForeignKeyConstraint baseTableName=keyboxes, constraintName=fk_keyboxes_usertoken_id, referencedTableName=usertoken', '', NULL, '3.5.4', NULL, NULL, '0231662233');
COMMIT;

-- ----------------------------
-- Table structure for databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `databasechangeloglock`;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- ----------------------------
-- Records of databasechangeloglock
-- ----------------------------
BEGIN;
INSERT INTO `databasechangeloglock` VALUES (1, b'0', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for jhi_authority
-- ----------------------------
DROP TABLE IF EXISTS `jhi_authority`;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) COLLATE utf8mb4_croatian_ci NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- ----------------------------
-- Records of jhi_authority
-- ----------------------------
BEGIN;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN');
INSERT INTO `jhi_authority` VALUES ('ROLE_USER');
COMMIT;

-- ----------------------------
-- Table structure for jhi_persistent_audit_event
-- ----------------------------
DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) COLLATE utf8mb4_croatian_ci NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- ----------------------------
-- Records of jhi_persistent_audit_event
-- ----------------------------
BEGIN;
INSERT INTO `jhi_persistent_audit_event` VALUES (1, 'admin', '2019-02-15 11:59:06', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (2, 'admin', '2019-02-18 01:19:34', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (3, 'admin', '2019-02-19 01:22:40', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (4, 'admin', '2019-02-19 02:56:13', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (5, 'admin', '2019-02-20 01:13:01', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (6, 'admin', '2019-02-20 01:15:51', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (7, '13000000000', '2019-02-21 01:12:51', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (8, '1300000000', '2019-02-21 01:22:57', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (9, '13000000000', '2019-02-21 01:23:08', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (10, '13000000000', '2019-02-21 01:23:50', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (11, '13000000000', '2019-02-21 01:25:13', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (12, '13000000000', '2019-02-21 01:25:35', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (13, '13000000000', '2019-02-21 02:20:35', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (14, '13123456789', '2019-02-21 02:38:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (15, '13123456789', '2019-02-21 02:46:48', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (16, '13000000000', '2019-02-21 03:32:26', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (17, '13000000000', '2019-02-21 03:54:42', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (18, '13000000000', '2019-02-21 03:54:49', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (19, '13000000000', '2019-02-21 03:55:10', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (20, '13000000000', '2019-02-21 04:16:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (21, '13000000000', '2019-02-21 06:34:07', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (22, '13000000000', '2019-02-21 06:53:29', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (23, '13000000000', '2019-02-21 06:53:56', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (24, '13000000000', '2019-02-21 06:54:30', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (25, '13000000000', '2019-02-21 07:39:04', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (26, '13000000000', '2019-02-21 08:04:56', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (27, '13000000000', '2019-02-21 09:57:36', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (28, '18667048803', '2019-02-22 05:53:13', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (29, '18559283192', '2019-02-22 06:58:14', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (30, '15382397775', '2019-02-22 07:13:26', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (31, '15999980733', '2019-02-22 07:47:41', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (32, '13000000000', '2019-02-22 07:48:14', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (33, '13000000000', '2019-02-22 07:58:37', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (34, '13564241325', '2019-02-22 08:18:37', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (35, 'admin', '2019-02-22 08:19:09', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (36, '13000000000', '2019-02-22 09:58:50', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (37, '13000000000', '2019-02-22 09:59:14', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (38, '13000000000', '2019-02-22 10:00:09', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (39, '13000000000', '2019-02-22 10:00:18', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (40, '18905012143', '2019-02-23 00:31:22', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (41, '15669911952', '2019-02-23 06:25:16', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (42, '15669911952', '2019-02-23 06:26:32', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (43, '15669911952', '2019-02-23 06:26:45', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (44, '15669911952', '2019-02-23 06:26:51', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (45, '15669911952', '2019-02-23 06:27:11', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (46, '13000000000', '2019-02-25 01:50:32', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (47, '13000000000', '2019-02-25 02:16:12', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (48, '13000000000', '2019-02-25 03:00:48', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (49, '13000000000', '2019-02-25 03:10:52', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (50, '13000000000', '2019-02-25 03:35:45', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (51, '13000000000', '2019-02-25 03:37:25', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (52, '13000000000', '2019-02-25 03:54:06', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (53, '13000000000', '2019-02-25 05:37:18', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (54, '13000000000', '2019-02-25 05:38:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (55, '13000000000', '2019-02-25 05:40:51', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (56, '13000000000', '2019-02-25 05:55:44', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (57, '13000000000', '2019-02-25 06:07:05', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (58, '13000000000', '2019-02-25 06:41:53', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (59, '13000000000', '2019-02-25 06:42:00', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (60, '13000000000', '2019-02-25 07:09:57', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (61, '13123456788', '2019-02-25 07:18:08', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (62, '13123456788', '2019-02-25 07:18:28', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (63, '13123456788', '2019-02-25 07:18:33', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (64, '13123456789', '2019-02-25 07:19:17', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (65, '13123456789', '2019-02-25 07:20:24', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (66, '13000000000', '2019-02-26 00:59:58', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (67, '13000000000', '2019-02-26 01:00:33', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (68, '13000000000', '2019-02-26 01:03:34', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (69, '13000000000', '2019-02-26 01:06:29', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (70, '13000000000', '2019-02-26 01:08:36', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (71, '13000000000', '2019-02-26 03:48:41', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (72, '13000000000', '2019-02-27 00:51:18', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (73, 'admin', '2019-02-27 07:26:24', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (74, '15999980733', '2019-02-27 07:30:24', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (75, '15999980733', '2019-02-27 07:30:59', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (76, '15999980733', '2019-02-27 07:32:51', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (77, '15999980733', '2019-02-27 07:32:51', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (78, '13000000000', '2019-02-27 07:45:09', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (79, '13000000000', '2019-02-27 07:45:11', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (80, '13000000000', '2019-02-27 07:45:17', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (81, '13000000000', '2019-02-27 07:45:31', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (82, '13000000000', '2019-02-27 07:45:34', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (83, '13000000000', '2019-02-27 07:46:16', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (84, '13000000000', '2019-02-27 07:46:49', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (85, '13000000000', '2019-02-27 07:46:55', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (86, '213213', '2019-02-27 17:10:50', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (87, '213213', '2019-02-27 17:11:51', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (88, '13123456789', '2019-02-28 02:35:32', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (89, '13123456789', '2019-02-28 03:19:34', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (90, '13123456789', '2019-02-28 03:19:55', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (91, '13123456789', '2019-02-28 03:26:27', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (92, 'admin', '2019-02-28 03:27:47', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (93, '13000000000', '2019-02-28 03:41:21', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (94, '13000000000', '2019-02-28 03:46:14', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (95, '213213', '2019-02-28 13:05:23', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (96, '213213', '2019-02-28 13:06:20', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (97, '13000000000', '2019-03-04 05:48:25', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (98, '13000000000', '2019-03-04 06:14:33', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (99, 'admin', '2019-03-07 01:52:52', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (100, '13000000000', '2019-03-24 13:45:50', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (101, '13000000000', '2019-03-28 01:44:51', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (102, '13987025525', '2019-03-28 07:56:45', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (103, '13987025525', '2019-03-28 08:52:46', 'AUTHENTICATION_SUCCESS');
INSERT INTO `jhi_persistent_audit_event` VALUES (104, '15999980733', '2019-04-01 07:14:25', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (105, '15999980733', '2019-04-01 07:14:34', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (106, '15999980733', '2019-04-01 07:14:48', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (107, '15999980733', '2019-04-01 07:15:01', 'AUTHENTICATION_FAILURE');
INSERT INTO `jhi_persistent_audit_event` VALUES (108, '15999980733', '2019-04-01 07:15:05', 'AUTHENTICATION_SUCCESS');
COMMIT;

-- ----------------------------
-- Table structure for jhi_persistent_audit_evt_data
-- ----------------------------
DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) COLLATE utf8mb4_croatian_ci NOT NULL,
  `value` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- ----------------------------
-- Records of jhi_persistent_audit_evt_data
-- ----------------------------
BEGIN;
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (8, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (8, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (17, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (17, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (18, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (18, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (28, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (28, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (42, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (42, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (43, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (43, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (44, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (44, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (58, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (58, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (61, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (61, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (62, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (62, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (63, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (63, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (78, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (78, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (79, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (79, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (80, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (80, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (81, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (81, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (82, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (82, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (83, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (83, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (84, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (84, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (86, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (86, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (87, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (87, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (93, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (93, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (95, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (95, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (96, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (96, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (104, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (104, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (105, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (105, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (106, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (106, 'type', 'org.springframework.security.authentication.BadCredentialsException');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (107, 'message', 'Bad credentials');
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (107, 'type', 'org.springframework.security.authentication.BadCredentialsException');
COMMIT;

-- ----------------------------
-- Table structure for jhi_user
-- ----------------------------
DROP TABLE IF EXISTS `jhi_user`;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE utf8mb4_croatian_ci NOT NULL,
  `password_hash` varchar(60) COLLATE utf8mb4_croatian_ci NOT NULL,
  `first_name` varchar(50) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `email` varchar(191) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `image_url` varchar(256) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(6) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `activation_key` varchar(20) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `reset_key` varchar(20) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_croatian_ci NOT NULL,
  `created_date` timestamp NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- ----------------------------
-- Records of jhi_user
-- ----------------------------
BEGIN;
INSERT INTO `jhi_user` VALUES (1, 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System', 'system@localhost', '', b'1', 'zh-cn', NULL, NULL, 'system', NULL, NULL, 'system', NULL);
INSERT INTO `jhi_user` VALUES (2, 'anonymoususer', '$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'User', 'anonymous@localhost', '', b'1', 'zh-cn', NULL, NULL, 'system', NULL, NULL, 'system', NULL);
INSERT INTO `jhi_user` VALUES (3, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', 'admin@localhost', '', b'1', 'zh-cn', NULL, NULL, 'system', NULL, NULL, 'system', NULL);
INSERT INTO `jhi_user` VALUES (4, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost', '', b'1', 'zh-cn', NULL, NULL, 'system', NULL, NULL, 'system', NULL);
INSERT INTO `jhi_user` VALUES (9, '15999980733', '$2a$10$qt1zF0uGDFtR1I3GWfCs6eQr2MCxUIOjZ81R8V7.nakfTzSc9Lm4e', '15999980733', '15999980733', '15999980733@localhost', '', b'1', 'zh-cn', NULL, NULL, 'anonymousUser', '2019-02-22 07:47:22', NULL, 'anonymousUser', '2019-02-22 07:47:22');
INSERT INTO `jhi_user` VALUES (13, '13000000000', '$2a$10$bSoEiiG7rHUWA1q7xjvWMOsXa0hvYIFyeLcBs8O717nNzeH.yW7Oi', '13000000000', '13000000000', '13000000000@localhost', '', b'1', 'zh-cn', NULL, NULL, '13000000000', '2019-02-28 03:42:01', NULL, '13000000000', '2019-02-28 03:42:01');
INSERT INTO `jhi_user` VALUES (14, '13987025525', '$2a$10$RSWtMobAbauIx017sqRu7e4Ym2GQIpg4CVWF4vE6PdqY/vrSsOe52', '13987025525', '13987025525', '13987025525@localhost', '', b'1', 'zh-cn', NULL, NULL, 'anonymousUser', '2019-03-28 07:56:35', NULL, 'anonymousUser', '2019-03-28 07:56:35');
COMMIT;

-- ----------------------------
-- Table structure for jhi_user_authority
-- ----------------------------
DROP TABLE IF EXISTS `jhi_user_authority`;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) COLLATE utf8mb4_croatian_ci NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- ----------------------------
-- Records of jhi_user_authority
-- ----------------------------
BEGIN;
INSERT INTO `jhi_user_authority` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `jhi_user_authority` VALUES (3, 'ROLE_ADMIN');
INSERT INTO `jhi_user_authority` VALUES (1, 'ROLE_USER');
INSERT INTO `jhi_user_authority` VALUES (3, 'ROLE_USER');
INSERT INTO `jhi_user_authority` VALUES (4, 'ROLE_USER');
INSERT INTO `jhi_user_authority` VALUES (9, 'ROLE_USER');
INSERT INTO `jhi_user_authority` VALUES (13, 'ROLE_USER');
INSERT INTO `jhi_user_authority` VALUES (14, 'ROLE_USER');
COMMIT;

-- ----------------------------
-- Table structure for keyboxes
-- ----------------------------
DROP TABLE IF EXISTS `keyboxes`;
CREATE TABLE `keyboxes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `login` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `passwordtext` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `levelpasswordtext` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `other` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `usertoken_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_keyboxes_usertoken_id` (`usertoken_id`),
  CONSTRAINT `fk_keyboxes_usertoken_id` FOREIGN KEY (`usertoken_id`) REFERENCES `usertoken` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- ----------------------------
-- Records of keyboxes
-- ----------------------------
BEGIN;
INSERT INTO `keyboxes` VALUES (23, 'Steam', 'au104745', '4835192760', '', '', 1550821834256, '', 5);
INSERT INTO `keyboxes` VALUES (24, '本科', '010618204560', '104745', '', '', 1550821889690, '华南师范大学508，专业代码B020309', 5);
INSERT INTO `keyboxes` VALUES (25, '国开', '1844101486599', '06050818', '', NULL, 1550821928366, NULL, 5);
INSERT INTO `keyboxes` VALUES (27, '百度云', '尐丨兮忆丶', '4835192760m,', '', '', 1550823909085, '', 5);
INSERT INTO `keyboxes` VALUES (28, 'Quip', 'lixin520gj@163.com', '4835192760mM', '', '', 1550823982341, '', 5);
INSERT INTO `keyboxes` VALUES (29, '尚德机构', '15999980733', '4835192760m', NULL, NULL, 1550824070370, NULL, 5);
INSERT INTO `keyboxes` VALUES (30, '云闪付', '15999980733', '4835192760m,', NULL, NULL, 1550824151538, NULL, 5);
INSERT INTO `keyboxes` VALUES (31, '奕特智联企业号', 'fnf50652968r', 'it89285549it', '', '', 1550824229789, '', 5);
INSERT INTO `keyboxes` VALUES (32, '奕特阿里云', 'gztanjia', 'Yite12345!@#$%', '', '', 1550824301308, '', 5);
INSERT INTO `keyboxes` VALUES (33, '联想账号', '15999980733', '4835192760m,', '', '', 1550824356022, '', 5);
INSERT INTO `keyboxes` VALUES (34, 'Adobe', 'lixin520gj@163.com', '767843089N1ne', NULL, NULL, 1550824478685, NULL, 5);
INSERT INTO `keyboxes` VALUES (35, 'Lastpass', 'lixin520gj@163.com', '4835192760m,', NULL, NULL, 1550824549617, NULL, 5);
INSERT INTO `keyboxes` VALUES (36, 'Google', 'lixin520gj@163.com', '767843089N1ne', NULL, NULL, 1550824610643, NULL, 5);
INSERT INTO `keyboxes` VALUES (37, '163邮箱', 'lixin520gj@163.com', '4835192760m,', NULL, NULL, 1550824660749, NULL, 5);
INSERT INTO `keyboxes` VALUES (38, 'Facebook', '15999980733', '4835192760', NULL, NULL, 1550824711181, NULL, 5);
INSERT INTO `keyboxes` VALUES (39, '微软账号', 'lixin520gj@163.com', '4835192769m,.', NULL, NULL, 1550824763405, NULL, 5);
INSERT INTO `keyboxes` VALUES (40, 'Daybreak', 'au104710', '4835192760m', NULL, NULL, 1550824867666, NULL, 5);
INSERT INTO `keyboxes` VALUES (41, 'Apple', 'lixin520gj@163.com', '767843089N1ne', NULL, NULL, 1550824980194, NULL, 5);
INSERT INTO `keyboxes` VALUES (42, '交管12123', '130629198806050818', '4835192760m,', NULL, NULL, 1550825042069, NULL, 5);
INSERT INTO `keyboxes` VALUES (43, 'Dockercloud', 'au104745', '4835192760m,', '', NULL, 1550825088385, NULL, 5);
INSERT INTO `keyboxes` VALUES (44, '中国银行', '15999980733', '4835192760m,', NULL, NULL, 1550825129976, NULL, 5);
INSERT INTO `keyboxes` VALUES (45, 'Xvideo', '541091705@qq.com', '4835192760m,', NULL, NULL, 1550825177172, NULL, 5);
INSERT INTO `keyboxes` VALUES (46, '世纪天成', 'au104710', '4835192760m', NULL, NULL, 1550825218684, NULL, 5);
INSERT INTO `keyboxes` VALUES (47, 'QQ天涯明月刀', '3263220591', '4835192760', NULL, NULL, 1550825282634, NULL, 5);
INSERT INTO `keyboxes` VALUES (48, '奕特微信公众号', '3457194256@qq.com', 'Liulin19921012', NULL, NULL, 1550825333602, NULL, 5);
INSERT INTO `keyboxes` VALUES (49, '奕特微信公众号工程师端', 'liulin@gzyiit.com', 'liulin19921012', NULL, NULL, 1550825379688, NULL, 5);
INSERT INTO `keyboxes` VALUES (50, 'TPlink', '15032222823', '4835192769m,.', NULL, NULL, 1550825452460, NULL, 5);
INSERT INTO `keyboxes` VALUES (51, 'TeamViewer', '541091705@qq.com', '4835192760m,', NULL, NULL, 1550825493396, NULL, 5);
INSERT INTO `keyboxes` VALUES (52, '奕特阿里云服务器', 'root', 'KEY=it12345!@#$%', NULL, '120.77.243.2', 1550825591684, NULL, 5);
INSERT INTO `keyboxes` VALUES (53, '广州公积金', '130629198806050818', '104745', '', '', 1550825648793, '身份证登陆', 5);
INSERT INTO `keyboxes` VALUES (56, '迅雷', '5509079', '4835192760', '', NULL, 1550900654268, NULL, 5);
INSERT INTO `keyboxes` VALUES (71, 'Xnxx', '541091705@qq.com', '4835192760m,', '', '', 1551173334976, '', 5);
INSERT INTO `keyboxes` VALUES (81, '我加', '在家', '123', '你', '我', 1551664722769, 'Hahjaj', 9);
INSERT INTO `keyboxes` VALUES (83, '1234', '456', NULL, NULL, NULL, 1551664781049, NULL, 9);
INSERT INTO `keyboxes` VALUES (86, 'Hush’s', 'Shush', NULL, NULL, NULL, 1551678348402, NULL, 9);
INSERT INTO `keyboxes` VALUES (87, '个人所得税', '15999980733', '4835192760m,', NULL, NULL, 1552582839758, NULL, 5);
INSERT INTO `keyboxes` VALUES (88, '阿里云', '541091705@qq.com', '4835192760m,', NULL, NULL, 1552802591845, NULL, 5);
INSERT INTO `keyboxes` VALUES (89, '网络版财务管理软件', '301504zhangyh', 'dw136966', NULL, NULL, 1553763433809, NULL, 10);
COMMIT;

-- ----------------------------
-- Table structure for usertoken
-- ----------------------------
DROP TABLE IF EXISTS `usertoken`;
CREATE TABLE `usertoken` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `state` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `end_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- ----------------------------
-- Records of usertoken
-- ----------------------------
BEGIN;
INSERT INTO `usertoken` VALUES (5, '9', '1', 0);
INSERT INTO `usertoken` VALUES (9, '13', '1', 0);
INSERT INTO `usertoken` VALUES (10, '14', '1', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
