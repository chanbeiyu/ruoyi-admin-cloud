-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ensoul_thoughts
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_advice`
--

DROP TABLE IF EXISTS `app_advice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_advice` (
  `advice_id` bigint NOT NULL COMMENT '留言id',
  `app_id` bigint NOT NULL COMMENT 'AppId',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `member_id` bigint DEFAULT NULL COMMENT '成员id',
  `contact` varchar(64) DEFAULT NULL COMMENT '联系方式',
  `content` varchar(1000) DEFAULT NULL COMMENT '反馈内容',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`advice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='意见反馈信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_advice`
--

LOCK TABLES `app_advice` WRITE;
/*!40000 ALTER TABLE `app_advice` DISABLE KEYS */;
INSERT INTO `app_advice` VALUES (10000,10000,'000000',11122222,'111111','111111',1,'2023-07-01 19:12:26',1,'2023-07-01 19:12:33',1,NULL);
/*!40000 ALTER TABLE `app_advice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_extend`
--

DROP TABLE IF EXISTS `app_extend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_extend` (
  `extend_id` bigint NOT NULL COMMENT '组件id',
  `app_id` bigint NOT NULL COMMENT 'AppId',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `key` varchar(64) NOT NULL COMMENT '数据key',
  `label` varchar(32) NOT NULL COMMENT '数据标签',
  `value` longtext COMMENT '扩展数据',
  `version` varchar(16) DEFAULT '1.0.0' COMMENT '扩展数据版本',
  `description` varchar(256) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`extend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用扩展信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_extend`
--

LOCK TABLES `app_extend` WRITE;
/*!40000 ALTER TABLE `app_extend` DISABLE KEYS */;
INSERT INTO `app_extend` VALUES (10001,10000,'000000','service_agreement','服务协议','<p></p>','1.0.0','','',1,'2023-07-16 12:31:27',1,'2023-07-16 12:31:27',1),(10002,10000,'000000','privacy_policy','隐私条款','<p></p>','1.0.0','','',1,'2023-07-16 12:59:03',1,'2023-07-16 12:59:03',1),(10003,10000,'000000','behaviour_norm','行为规范','<p></p>','1.0.0','','',1,'2023-07-16 12:59:36',1,'2023-07-16 12:59:36',1),(10004,10000,'000000','personal_Info_checklist','个人信息收集清单','<p></p>','1.0.0','','',1,'2023-07-16 12:59:59',1,'2023-07-16 12:59:59',1),(10005,10000,'000000','personal_Info_sharelist','个人信息共享清单','<p></p>','1.0.0','','',1,'2023-07-16 13:02:00',1,'2023-07-16 13:02:00',1);
/*!40000 ALTER TABLE `app_extend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_info`
--

DROP TABLE IF EXISTS `app_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_info` (
  `app_id` bigint NOT NULL COMMENT 'AppId',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编码',
  `app_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `access_key_id` varchar(32) DEFAULT NULL COMMENT 'AccessKeyId',
  `secret_access_key` varchar(512) DEFAULT NULL COMMENT 'SecretAccessKey',
  `salt` varchar(32) DEFAULT NULL COMMENT '签名加盐值',
  `domains` varchar(512) DEFAULT NULL COMMENT '允许的域,支持模糊匹配',
  `is_internal` char(1) NOT NULL DEFAULT 'Y' COMMENT '是否为内部应用',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `extend` text COMMENT '扩展信息',
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_info`
--

LOCK TABLES `app_info` WRITE;
/*!40000 ALTER TABLE `app_info` DISABLE KEYS */;
INSERT INTO `app_info` VALUES (10000,'000000','APP_THOUGHTS','思绪','','','','','Y','N','[{\"key\":\"contact_wechat\",\"value\":\"wechat\",\"label\":\"微信\"},{\"key\":\"contact_phone\",\"value\":\"15888888888\",\"label\":\"手机号\"},{\"key\":\"contact_email\",\"value\":\"chpeng@outlook.com\",\"label\":\"邮箱\"}]','',103,'2023-06-28 21:04:05',1,'2023-07-29 11:28:28',1,NULL),(10001,'000000','TEST_APP1','测试APP2','','','','','Y','Y','[{\"key\": \"contact_wechat\", \"value\": \"wechat\", \"label\":\"微信\"},{\"key\": \"contact_phone\", \"value\": \"15888888888\", \"label\":\"手机号\"},{\"key\": \"contact_email\", \"value\": \"chpeng@outlook.com\", \"label\":\"邮箱\"}]','1321321321321321312321312',103,'2023-06-28 21:04:05',1,'2023-07-03 18:32:57',1,NULL),(1678341295127969794,'000000','sassasasa','sasasas',NULL,NULL,NULL,'saasasasassa','Y','Y',NULL,'asasaassaas',1,'2023-07-10 17:52:01',1,'2023-07-10 17:52:01',1,NULL),(1685130043005923329,'000000','aaaaaaaaa','aaaaaaaaa',NULL,NULL,NULL,NULL,'Y','Y',NULL,NULL,1,'2023-07-29 11:28:05',1,'2023-07-29 11:28:05',1,NULL),(1685130064891801602,'000000','aaaaaaaaa','aaaaaaaaa',NULL,NULL,NULL,'aaaaaaaaa','Y','Y',NULL,'aaaaaaaaa',1,'2023-07-29 11:28:10',1,'2023-07-29 11:28:10',1,NULL);
/*!40000 ALTER TABLE `app_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_version`
--

DROP TABLE IF EXISTS `app_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_version` (
  `version_id` bigint NOT NULL COMMENT '版本id',
  `app_id` bigint NOT NULL COMMENT 'AppId',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `version` varchar(32) NOT NULL COMMENT '版本名称',
  `build_version` varchar(32) NOT NULL COMMENT '内部版本号',
  `forced` char(1) NOT NULL DEFAULT 'Y' COMMENT '强制升级',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`version_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用版本信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_version`
--

LOCK TABLES `app_version` WRITE;
/*!40000 ALTER TABLE `app_version` DISABLE KEYS */;
INSERT INTO `app_version` VALUES (10000,10000,'000000','1111','111','N','2023-07-29 00:02:00',1,'2023-07-01 18:28:41',1,'2023-07-08 09:28:13',1,NULL);
/*!40000 ALTER TABLE `app_version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_action`
--

DROP TABLE IF EXISTS `member_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_action` (
  `action_id` bigint NOT NULL COMMENT '积分id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `member_id` bigint NOT NULL COMMENT '会员id',
  `member_type_id` bigint NOT NULL COMMENT '会员类别',
  `action_code` varchar(32) NOT NULL COMMENT '动作编号',
  `action_name` varchar(32) NOT NULL COMMENT '动作名称',
  `points` int DEFAULT '0' COMMENT '积分点',
  `conis` int NOT NULL DEFAULT '0' COMMENT '代币点',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态',
  `description` varchar(256) DEFAULT NULL COMMENT '说明',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`action_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会员积分';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_action`
--

LOCK TABLES `member_action` WRITE;
/*!40000 ALTER TABLE `member_action` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_coins`
--

DROP TABLE IF EXISTS `member_coins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_coins` (
  `id` bigint NOT NULL COMMENT '主键id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `member_id` bigint NOT NULL COMMENT '成员id',
  `coins_type` tinyint NOT NULL DEFAULT '0' COMMENT '代币信息0点数1时常2天数',
  `last_coins` int NOT NULL COMMENT '剩余点数/时常/天数',
  `expired_date` datetime NOT NULL COMMENT '过期时间',
  `description` varchar(256) DEFAULT NULL COMMENT '级别说明',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代币信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_coins`
--

LOCK TABLES `member_coins` WRITE;
/*!40000 ALTER TABLE `member_coins` DISABLE KEYS */;
INSERT INTO `member_coins` VALUES (1687294511878017025,'000000',10001,1,1,1234,'2023-08-04 10:48:52','11111111',1,1,'2023-08-04 10:48:55',1,'2023-08-08 12:38:18',1,NULL);
/*!40000 ALTER TABLE `member_coins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_coins_record`
--

DROP TABLE IF EXISTS `member_coins_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_coins_record` (
  `record_id` bigint NOT NULL COMMENT '级别id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `member_id` bigint NOT NULL COMMENT '成员id',
  `coins_type` tinyint NOT NULL DEFAULT '0' COMMENT '代币类型0点数1时常2天数',
  `action_code` varchar(32) NOT NULL COMMENT '动作类型',
  `befor_coins` int NOT NULL COMMENT '先前代币',
  `befor_expired_date` datetime NOT NULL COMMENT '先前过期时间',
  `change_coins` int NOT NULL COMMENT '代币变化',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单号',
  `status` tinyint DEFAULT '0' COMMENT '状态',
  `description` varchar(256) DEFAULT NULL COMMENT '说明',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代币记录信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_coins_record`
--

LOCK TABLES `member_coins_record` WRITE;
/*!40000 ALTER TABLE `member_coins_record` DISABLE KEYS */;
INSERT INTO `member_coins_record` VALUES (1,'000000',10000,1,1,'1',1000,'2023-08-08 13:09:53',19,'1234568895',1,'11111111',3,'2023-08-08 13:10:19',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `member_coins_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_info`
--

DROP TABLE IF EXISTS `member_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_info` (
  `member_id` bigint NOT NULL COMMENT '主键id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `union_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关联用户标识',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `type_id` bigint NOT NULL COMMENT '成员类型',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `avatar_img` varchar(256) DEFAULT NULL COMMENT '头像',
  `banner_img` varchar(256) DEFAULT NULL COMMENT '顶部背景图',
  `whatsup` varchar(256) DEFAULT NULL COMMENT '个性签名',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='成员信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_info`
--

LOCK TABLES `member_info` WRITE;
/*!40000 ALTER TABLE `member_info` DISABLE KEYS */;
INSERT INTO `member_info` VALUES (1,'000000','1111',10000,1688449866607652866,'张麻醉','324324','32432432','432432432','Y',1,'2023-08-03 12:26:30',1,'2023-08-03 12:26:30',1,NULL);
/*!40000 ALTER TABLE `member_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_level`
--

DROP TABLE IF EXISTS `member_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_level` (
  `level_id` bigint NOT NULL COMMENT '级别id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `member_type_id` bigint NOT NULL COMMENT '会员类别',
  `level_code` varchar(32) NOT NULL COMMENT '级别编码',
  `level_name` varchar(32) NOT NULL COMMENT '级别名称',
  `least_points` int NOT NULL COMMENT '最小积分',
  `biggest_points` int NOT NULL COMMENT '最大积分',
  `status` char(1) NOT NULL DEFAULT 'N' COMMENT '状态',
  `description` varchar(256) DEFAULT NULL COMMENT '级别说明',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会员级别信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_level`
--

LOCK TABLES `member_level` WRITE;
/*!40000 ALTER TABLE `member_level` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_points`
--

DROP TABLE IF EXISTS `member_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_points` (
  `id` bigint NOT NULL COMMENT '积分id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `member_id` bigint NOT NULL COMMENT '成员id',
  `member_type_id` bigint NOT NULL COMMENT '会员类别',
  `total_points` int NOT NULL DEFAULT '0' COMMENT '总积分',
  `last_level` int NOT NULL DEFAULT '0' COMMENT '最终会员等级',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态',
  `description` varchar(256) DEFAULT NULL COMMENT '获取积分说明',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会员积分';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_points`
--

LOCK TABLES `member_points` WRITE;
/*!40000 ALTER TABLE `member_points` DISABLE KEYS */;
INSERT INTO `member_points` VALUES (1687277013212389377,'000000',10001,1,1688450310939635714,1011,81,1,'111111111',1,'2023-08-04 09:39:23',1,'2023-08-04 11:44:18',1,NULL);
/*!40000 ALTER TABLE `member_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_points_record`
--

DROP TABLE IF EXISTS `member_points_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_points_record` (
  `record_id` bigint NOT NULL COMMENT '积分id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `member_id` bigint NOT NULL COMMENT '成员id',
  `member_type_id` bigint NOT NULL COMMENT '会员类别',
  `action_code` varchar(32) NOT NULL COMMENT '操作类型',
  `befor_points` int NOT NULL DEFAULT '0' COMMENT '上次积分',
  `points` int NOT NULL DEFAULT '0' COMMENT '获取积分',
  `expired_date` datetime NOT NULL COMMENT '过期时间',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态',
  `description` varchar(256) DEFAULT NULL COMMENT '获取积分说明',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会员积分记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_points_record`
--

LOCK TABLES `member_points_record` WRITE;
/*!40000 ALTER TABLE `member_points_record` DISABLE KEYS */;
INSERT INTO `member_points_record` VALUES (1688776634271092737,'000000',10000,1,1688449866607652866,'1',100,25,'2023-08-08 12:58:18',0,'111',1,'2023-08-08 12:58:20',1,'2023-08-08 13:07:54',1,NULL);
/*!40000 ALTER TABLE `member_points_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_type`
--

DROP TABLE IF EXISTS `member_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_type` (
  `type_id` bigint NOT NULL COMMENT '级别id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `type_code` varchar(32) NOT NULL COMMENT '会员类型编码',
  `type_name` varchar(32) NOT NULL COMMENT '会员类型名称',
  `max_points` int NOT NULL DEFAULT '0' COMMENT '最大积分',
  `max_level` tinyint NOT NULL DEFAULT '0' COMMENT '最大级别',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `description` varchar(256) DEFAULT NULL COMMENT '会员类型说明',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会员类型信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_type`
--

LOCK TABLES `member_type` WRITE;
/*!40000 ALTER TABLE `member_type` DISABLE KEYS */;
INSERT INTO `member_type` VALUES (1688449866607652866,'000000',10000,'MTYPE001','类型001',1000,10,'Y','类型001',1,'2023-08-07 15:19:53',1,'2023-08-07 15:19:53',1,NULL),(1688450310939635714,'000000',10000,'MTYPE002','类型002',1000,10,'Y','100',1,'2023-08-07 15:21:39',1,'2023-08-08 11:18:38',1,NULL);
/*!40000 ALTER TABLE `member_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_type_related`
--

DROP TABLE IF EXISTS `member_type_related`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_type_related` (
  `id` bigint NOT NULL COMMENT 'id',
  `member_id` bigint NOT NULL COMMENT '会员id',
  `member_type_id` bigint NOT NULL COMMENT '会员类别',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会员类型关联信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_type_related`
--

LOCK TABLES `member_type_related` WRITE;
/*!40000 ALTER TABLE `member_type_related` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_type_related` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_comment`
--

DROP TABLE IF EXISTS `social_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_comment` (
  `comment_id` bigint NOT NULL COMMENT '评论id',
  `comment_pid` bigint NOT NULL DEFAULT '0' COMMENT '父评论(回复)id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `member_id` bigint NOT NULL COMMENT '评论用户id',
  `to_member_id` bigint NOT NULL COMMENT '被评论用户id',
  `subject_id` bigint NOT NULL COMMENT '对应主题',
  `target_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '目标id',
  `target_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '目标内容',
  `comment_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '评论标题',
  `comment_contnet` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '评论内容',
  `like_num` int DEFAULT '0' COMMENT '点赞数',
  `allow_comment` tinyint(1) DEFAULT '0' COMMENT '允许评论0允许1关注用户2不需要',
  `status` tinyint DEFAULT '0' COMMENT '状态:0发布1删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`comment_id`),
  KEY `comment_pid` (`comment_pid`),
  KEY `create_time` (`create_time`),
  KEY `album_id_id` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_comment`
--

LOCK TABLES `social_comment` WRITE;
/*!40000 ALTER TABLE `social_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_favorite`
--

DROP TABLE IF EXISTS `social_favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_favorite` (
  `favorite_id` bigint NOT NULL COMMENT '主键id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `member_id` bigint NOT NULL COMMENT '收藏用户id',
  `to_member_id` bigint NOT NULL COMMENT '被收藏用户id',
  `subject_id` bigint NOT NULL COMMENT '对应主题',
  `target_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '目标id',
  `target_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '目标内容',
  `describe` varchar(1024) DEFAULT NULL COMMENT '描述',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`favorite_id`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收藏信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_favorite`
--

LOCK TABLES `social_favorite` WRITE;
/*!40000 ALTER TABLE `social_favorite` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_follow`
--

DROP TABLE IF EXISTS `social_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_follow` (
  `follow_id` bigint NOT NULL COMMENT '主键id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `member_id` bigint NOT NULL COMMENT '用户id',
  `to_member_id` bigint NOT NULL COMMENT '关注用户id',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '关注状态0关注1互相关注2取消关注',
  `unfollow_time` datetime DEFAULT NULL COMMENT '取消关注时间',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`follow_id`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='关注信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_follow`
--

LOCK TABLES `social_follow` WRITE;
/*!40000 ALTER TABLE `social_follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_like`
--

DROP TABLE IF EXISTS `social_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_like` (
  `like_id` bigint NOT NULL COMMENT '主键id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `member_id` bigint NOT NULL COMMENT '点赞用户id',
  `to_member_id` bigint NOT NULL COMMENT '被点赞用户id',
  `subject_id` bigint NOT NULL COMMENT '对应主题',
  `target_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '目标id',
  `target_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '目标内容',
  `describe` varchar(1024) DEFAULT NULL COMMENT '描述',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`like_id`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='点赞信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_like`
--

LOCK TABLES `social_like` WRITE;
/*!40000 ALTER TABLE `social_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_notice`
--

DROP TABLE IF EXISTS `social_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_notice` (
  `notice_id` bigint NOT NULL COMMENT '消息ID',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `member_id` bigint NOT NULL COMMENT '用户id',
  `trigger_member_id` bigint NOT NULL COMMENT '触发通知用户id',
  `trigger_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '触发通知内容id',
  `trigger_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '通知内容',
  `type_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知类型',
  `status` tinyint(1) DEFAULT '0' COMMENT '通知状态（0发送失败1已发送2已阅读3删除）',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='信息通知';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_notice`
--

LOCK TABLES `social_notice` WRITE;
/*!40000 ALTER TABLE `social_notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_notice_type`
--

DROP TABLE IF EXISTS `social_notice_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_notice_type` (
  `notice_type_id` bigint NOT NULL COMMENT '通知类型id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `notice_type_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知类型编码',
  `notice_type_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知类型名称',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `description` varchar(256) DEFAULT NULL COMMENT '通知类型描述',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='信息通知类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_notice_type`
--

LOCK TABLES `social_notice_type` WRITE;
/*!40000 ALTER TABLE `social_notice_type` DISABLE KEYS */;
INSERT INTO `social_notice_type` VALUES (10000,'000000',10000,'sys_notice','公告','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{contnet}'),(10001,'000000',10000,'user_favor','收藏','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{content}'),(10002,'000000',10000,'user_follow','关注','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{content}'),(10003,'000000',10000,'user_like','点赞','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{content}'),(10004,'000000',10000,'user_share','转发','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{contnet}'),(10005,'000000',10000,'user_@','@','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{content}'),(10006,'000000',10000,'user_msg','私信','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{contnet}'),(10007,'000000',10000,'user_comment','评论','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{content}'),(10008,'000000',10000,'user_unfollow','取消关注','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{contnet}'),(10009,'000000',10000,'user_block','拉黑','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{contnet}'),(10010,'000000',10000,'gc_msg','群聊消息','N',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{contnet}'),(10011,'000000',10000,'gc_@','群聊内@','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{contnet}'),(10012,'000000',10000,'gc_ko','移出群','Y',NULL,NULL,'2023-06-28 17:55:59',NULL,NULL,NULL,'{contnet}');
/*!40000 ALTER TABLE `social_notice_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_subject`
--

DROP TABLE IF EXISTS `social_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_subject` (
  `subject_id` bigint NOT NULL COMMENT '主键id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `subject_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主题编码',
  `subject_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主题名称',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '主题描述',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='内容主题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_subject`
--

LOCK TABLES `social_subject` WRITE;
/*!40000 ALTER TABLE `social_subject` DISABLE KEYS */;
INSERT INTO `social_subject` VALUES (10001,'000000',10000,'thot_thought','思绪','Y','思绪-思绪',1,'2023-06-28 21:37:51',1,'2023-07-03 14:24:32',1,NULL),(10002,'000000',10000,'thot_album','思集','Y','思绪-思集',1,'2023-06-28 21:37:51',1,'2023-07-03 14:24:37',1,NULL),(10003,'000000',10000,'thot_campaign','活动','Y','思绪-活动',1,'2023-06-30 15:53:54',1,'2023-07-03 14:24:48',1,NULL),(10004,'000000',10000,'thot_channel','频道','Y','思绪-频道',1,'2023-07-03 14:22:16',1,'2023-07-03 14:24:56',1,NULL),(10005,'000000',10000,'thot_topic','话题','Y','思绪-话题',1,'2023-07-03 14:22:52',1,'2023-07-03 14:25:04',1,NULL),(10006,'000000',10000,'thot_style','样式','Y','思绪-样式',1,'2023-07-03 14:24:03',1,'2023-07-03 14:25:09',1,NULL),(100011,'000000',10001,'32132132','321321','Y','32132132',1,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `social_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_tag`
--

DROP TABLE IF EXISTS `social_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_tag` (
  `tag_id` bigint NOT NULL COMMENT '标签id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `subject_id` bigint NOT NULL COMMENT '所属主题',
  `tag_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签编码',
  `tag_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签名称',
  `tag_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签类型',
  `status` char(1) DEFAULT 'Y' COMMENT '状态',
  `description` varchar(256) DEFAULT NULL COMMENT '标签描述',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_tag`
--

LOCK TABLES `social_tag` WRITE;
/*!40000 ALTER TABLE `social_tag` DISABLE KEYS */;
INSERT INTO `social_tag` VALUES (1675861963663056897,'000000',10001,100011,'432432','432432','Y','Y','432432',1,'2023-07-03 21:40:03',1,'2023-07-06 10:05:58',1,NULL);
/*!40000 ALTER TABLE `social_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_order`
--

DROP TABLE IF EXISTS `trade_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_order` (
  `order_id` bigint NOT NULL COMMENT '订单id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '订单来源应用',
  `member_id` bigint NOT NULL COMMENT '订单用户id',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `order_type` int DEFAULT '0' COMMENT '订单类型：0->虚拟订单；1->正常订单',
  `order_note` varchar(512) DEFAULT NULL COMMENT '订单备注',
  `payment_type` int DEFAULT NULL COMMENT '支付方式',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `payment_no` varchar(32) DEFAULT NULL COMMENT '交易编号',
  `payment_amount` int DEFAULT NULL COMMENT '支付金额(实付)',
  `total_amount` int DEFAULT NULL COMMENT '订单总金额',
  `freight_amount` int DEFAULT '0' COMMENT '运费金额',
  `promotion_amount` int DEFAULT '0' COMMENT '促销优化金额',
  `integration_amount` int DEFAULT '0' COMMENT '积分抵扣金额',
  `discount_amount` int DEFAULT '0' COMMENT '后台折扣金额',
  `coupon_amount` int DEFAULT '0' COMMENT '优惠券抵扣金额',
  `delivery_id` bigint DEFAULT NULL COMMENT '配送方式',
  `delivery_name` varchar(32) DEFAULT NULL COMMENT '配送公司',
  `delivery_time` datetime DEFAULT NULL COMMENT '配送时间',
  `delivery_no` varchar(32) DEFAULT NULL COMMENT '物流单号',
  `receiver_username` varchar(32) DEFAULT NULL COMMENT '收货人',
  `receiver_phone` varchar(32) DEFAULT NULL COMMENT '收货人手机号',
  `receiver_post_code` varchar(32) DEFAULT NULL COMMENT '收货人邮编',
  `receiver_address` varchar(512) DEFAULT NULL COMMENT '收货地址',
  `receiver_time` datetime DEFAULT NULL COMMENT '收货时间',
  `status` int DEFAULT NULL COMMENT '订单状态',
  `extend` text COMMENT '扩展信息',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_order`
--

LOCK TABLES `trade_order` WRITE;
/*!40000 ALTER TABLE `trade_order` DISABLE KEYS */;
INSERT INTO `trade_order` VALUES (1686660841307697153,'000000',10000,10000,'10000',0,'10000',1,'2023-08-03 00:00:00','10000',10000,10000,10000,10000,10000,123,1000,5435,'公司','2023-08-31 00:00:00','物流单号','收货人','收货人手机号','收货人邮编','收货地址\n','2023-08-31 00:00:00',1,'100001000010000','100001000010000100001000010000',1,'2023-08-02 16:50:56',1,'2023-08-02 16:50:56',1,NULL);
/*!40000 ALTER TABLE `trade_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_order_invoice`
--

DROP TABLE IF EXISTS `trade_order_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_order_invoice` (
  `invoice_id` bigint NOT NULL COMMENT '开票id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '订单来源应用',
  `member_id` bigint NOT NULL COMMENT '订单用户id',
  `order_id` bigint DEFAULT NULL COMMENT '订单id',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `invoice_type` int NOT NULL DEFAULT '0' COMMENT '发票类型：0->不开发票；1->电子发票；2->纸质发票',
  `invoice_header` varchar(200) DEFAULT NULL COMMENT '发票抬头',
  `invoice_content` varchar(200) DEFAULT NULL COMMENT '发票内容',
  `invoice_url` varchar(256) DEFAULT NULL COMMENT '收票下载地址',
  `receiver_phone` varchar(32) DEFAULT NULL COMMENT '收票人电话',
  `receiver_email` varchar(64) DEFAULT NULL COMMENT '收票人邮箱',
  `status` int DEFAULT NULL COMMENT '发票状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `build_time` datetime DEFAULT NULL COMMENT '开票时间',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`invoice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_order_invoice`
--

LOCK TABLES `trade_order_invoice` WRITE;
/*!40000 ALTER TABLE `trade_order_invoice` DISABLE KEYS */;
INSERT INTO `trade_order_invoice` VALUES (1000,'000000',10000,1,1234567890,'1234567890',1,'xxx有限公司','xxx有限公司','https://prod-streaming-video-msn-com.akamaized.net/aa5cb260-7dae-44d3-acad-3c7053983ffe/1b790558-39a2-4d2a-bcd7-61f075e87fdd.mp4','18888888888','123@456.com',1,'2023-01-01 00:00:00','2024-01-01 00:00:00',NULL);
/*!40000 ALTER TABLE `trade_order_invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_order_item`
--

DROP TABLE IF EXISTS `trade_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_order_item` (
  `item_id` bigint NOT NULL COMMENT '订单商品id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `order_id` bigint DEFAULT NULL COMMENT '订单id',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `product_name` varchar(56) NOT NULL COMMENT '产品名称',
  `product_brand` varchar(56) DEFAULT NULL COMMENT '产品品牌',
  `product_sku_id` bigint NOT NULL COMMENT '商品sku编号',
  `product_sku_code` varchar(56) DEFAULT NULL COMMENT '商品sku条码',
  `product_pic_url` varchar(256) DEFAULT NULL COMMENT '产品图片地址',
  `product_attr` varchar(512) DEFAULT NULL COMMENT '商品销售属性',
  `product_price` int NOT NULL COMMENT '销售价格',
  `product_quantity` int NOT NULL DEFAULT '1' COMMENT '购买数量',
  `product_category_id` bigint DEFAULT '0' COMMENT '商品分类id',
  `promotion_name` varchar(128) DEFAULT '0' COMMENT '商品促销名称',
  `promotion_amount` int DEFAULT '0' COMMENT '商品促销分解金额',
  `coupon_amount` int DEFAULT '0' COMMENT '优惠券优惠分解金额',
  `integration_amount` int DEFAULT '0' COMMENT '积分优惠分解金额',
  `payment_amount` int DEFAULT NULL COMMENT '实际金额',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_order_item`
--

LOCK TABLES `trade_order_item` WRITE;
/*!40000 ALTER TABLE `trade_order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_order_operate`
--

DROP TABLE IF EXISTS `trade_order_operate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_order_operate` (
  `operate_id` bigint NOT NULL COMMENT '订单操作id',
  `order_id` bigint DEFAULT NULL COMMENT '订单id',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单号',
  `befor_status` int DEFAULT NULL COMMENT '操作前订单状态',
  `after_status` int DEFAULT NULL COMMENT '操作后订单状态',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`operate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单操作历史记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_order_operate`
--

LOCK TABLES `trade_order_operate` WRITE;
/*!40000 ALTER TABLE `trade_order_operate` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade_order_operate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-14 12:58:44
