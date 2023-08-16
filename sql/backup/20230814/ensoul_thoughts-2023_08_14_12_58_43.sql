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
-- Table structure for table `thot_album`
--

DROP TABLE IF EXISTS `thot_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thot_album` (
  `album_id` bigint NOT NULL COMMENT '思集ID',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App',
  `album_title` varchar(64) NOT NULL COMMENT '思绪编号',
  `description` varchar(256) DEFAULT NULL COMMENT '思绪描述',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '活动状态',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`album_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='思集信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thot_album`
--

LOCK TABLES `thot_album` WRITE;
/*!40000 ALTER TABLE `thot_album` DISABLE KEYS */;
INSERT INTO `thot_album` VALUES (1,'000000',10001,'444444','222222',0,NULL,1,'2023-07-04 22:24:24',1,'2023-07-16 10:59:09',1,NULL),(2,'000000',10000,'666666','222222',0,NULL,1,'2023-07-04 22:24:24',1,'2023-07-16 11:12:15',1,NULL),(3,'000000',10001,'111111','222222',0,'2023-07-14 13:26:02',1,'2023-07-04 22:24:24',1,'2023-07-16 11:10:50',1,NULL),(4,'000000',10001,'222222','222222',0,NULL,1,'2023-07-04 22:24:24',1,'2023-07-04 22:24:24',1,NULL),(5,'000000',10001,'555555','222222',0,'2023-07-14 10:08:48',1,'2023-07-04 22:24:24',1,'2023-07-04 22:24:24',1,NULL),(6,'000000',10001,'333333','222222',0,NULL,1,'2023-07-04 22:24:24',1,'2023-07-16 10:56:29',1,NULL);
/*!40000 ALTER TABLE `thot_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thot_album_thought`
--

DROP TABLE IF EXISTS `thot_album_thought`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thot_album_thought` (
  `id` bigint NOT NULL,
  `album_id` bigint NOT NULL COMMENT '思集ID',
  `thought_id` bigint NOT NULL COMMENT '思绪ID',
  `is_cover` char(1) NOT NULL DEFAULT 'N' COMMENT '是否为封面',
  `create_time` datetime DEFAULT (now()) COMMENT '加入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='思集信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thot_album_thought`
--

LOCK TABLES `thot_album_thought` WRITE;
/*!40000 ALTER TABLE `thot_album_thought` DISABLE KEYS */;
INSERT INTO `thot_album_thought` VALUES (1680411702907723777,1,1676807724089245617,'N',NULL),(1680411702995804161,1,1676807724089245697,'N',NULL),(1680414659795501057,3,1676807724089245617,'N',NULL),(1680414659808083969,3,1676807724089245697,'N',NULL),(1680415016093237250,2,1676807724019245697,'Y',NULL),(1680415016093237251,2,1676807724089245690,'Y',NULL);
/*!40000 ALTER TABLE `thot_album_thought` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thot_campaign`
--

DROP TABLE IF EXISTS `thot_campaign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thot_campaign` (
  `campaign_id` bigint NOT NULL COMMENT '活动ID',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` varchar(32) NOT NULL COMMENT '接入App',
  `campaign_code` varchar(32) NOT NULL COMMENT '活动编号',
  `campaign_name` varchar(32) NOT NULL COMMENT '活动名称',
  `campaign_banner_img` varchar(256) DEFAULT NULL COMMENT '活动主图',
  `campaign_content` text COMMENT '活动内容',
  `description` varchar(512) DEFAULT NULL COMMENT '活动说明',
  `type_code` varchar(32) NOT NULL COMMENT '活动类型',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '活动状态',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`campaign_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thot_campaign`
--

LOCK TABLES `thot_campaign` WRITE;
/*!40000 ALTER TABLE `thot_campaign` DISABLE KEYS */;
INSERT INTO `thot_campaign` VALUES (1676173709606977537,'000000','10000','1懂adsa','dsadas 懂a','1683067448908443649','<p> 大赛adas <img src=\"data:image/gif;base64,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\" alt=\"\" data-href=\"\" style=\"width: 30%;\"/><img src=\"http://en-thoughts.s3.oss-cn-hangzhou.aliyuncs.com/thoughts/2023/07/23/196d928ac6314576913fdea1e711a6f9.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230723T121519Z&X-Amz-SignedHeaders=host&X-Amz-Expires=119&X-Amz-Credential=LTAI5tCX9BCEkkXs8X6YVmyq%2F20230723%2F%2Fs3%2Faws4_request&X-Amz-Signature=b22aa5e1976dea71876f1a9dbcd1252b78ec9fe59cf3716ec3972dcca237ac72\" alt=\"1639742634590.jpg\" data-href=\"http://en-thoughts.s3.oss-cn-hangzhou.aliyuncs.com/thoughts/2023/07/23/196d928ac6314576913fdea1e711a6f9.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230723T121519Z&X-Amz-SignedHeaders=host&X-Amz-Expires=119&X-Amz-Credential=LTAI5tCX9BCEkkXs8X6YVmyq/20230723//s3/aws4_request&X-Amz-Signature=b22aa5e1976dea71876f1a9dbcd1252b78ec9fe59cf3716ec3972dcca237ac72\" style=\"width: 50%;\"/></p>',' 大赛adas 大赛adas 大赛adas 大赛adas 大赛adas 大赛adas 大赛adas 大赛adas 大赛adas 大赛adas',' 大赛adas 大赛adas 大赛adas 大赛adas',0,'2023-07-07 21:40:00','2023-07-07 21:40:30',1,'2023-07-04 18:18:49',1,'2023-07-23 20:15:25',1,NULL),(1677303844896845825,'000000','10000','3432432','432432432','1677679784667709442,1677679916977029121,1677679935801065473','<p>懂adsadsa<img src=\"http://en-thoughts.s3.oss-cn-hangzhou.aliyuncs.com/thoughts/2023/07/23/d31cc6b5fc3a41109599c02e2b490788.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230723T123033Z&X-Amz-SignedHeaders=host&X-Amz-Expires=119&X-Amz-Credential=LTAI5tCX9BCEkkXs8X6YVmyq%2F20230723%2F%2Fs3%2Faws4_request&X-Amz-Signature=64ab6e3beb5143f7c5132268b53b1e2b342a16457ac701ef24ce1a8177c78b84\" alt=\"v2-d95c992bf4f25b29a0e1c43e5114161a_r.jpg\" data-href=\"http://en-thoughts.s3.oss-cn-hangzhou.aliyuncs.com/thoughts/2023/07/23/d31cc6b5fc3a41109599c02e2b490788.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230723T123033Z&X-Amz-SignedHeaders=host&X-Amz-Expires=119&X-Amz-Credential=LTAI5tCX9BCEkkXs8X6YVmyq/20230723//s3/aws4_request&X-Amz-Signature=64ab6e3beb5143f7c5132268b53b1e2b342a16457ac701ef24ce1a8177c78b84\" style=\"width: 30%;\"/></p>','恶趣味','432432',0,'2023-07-07 21:09:30','2023-07-07 21:55:50',1,'2023-07-07 21:09:34',1,'2023-07-23 20:30:41',1,NULL);
/*!40000 ALTER TABLE `thot_campaign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thot_channel`
--

DROP TABLE IF EXISTS `thot_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thot_channel` (
  `channel_id` bigint NOT NULL COMMENT '主键id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App标识',
  `channel_code` varchar(32) NOT NULL COMMENT '频道编码',
  `channel_name` varchar(32) NOT NULL COMMENT '频道名称',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `description` varchar(256) DEFAULT NULL COMMENT '频道描述',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='频道信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thot_channel`
--

LOCK TABLES `thot_channel` WRITE;
/*!40000 ALTER TABLE `thot_channel` DISABLE KEYS */;
INSERT INTO `thot_channel` VALUES (1675421763799429121,'000000',10000,'231321312','懂adsa','Y','321321321312',1,'2023-07-02 16:30:51',1,'2023-07-02 16:34:48',1,NULL);
/*!40000 ALTER TABLE `thot_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thot_channel_thought`
--

DROP TABLE IF EXISTS `thot_channel_thought`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thot_channel_thought` (
  `id` bigint NOT NULL,
  `channel_id` bigint NOT NULL COMMENT '频道id',
  `thought_id` bigint NOT NULL COMMENT '思绪ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='思绪专题关联信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thot_channel_thought`
--

LOCK TABLES `thot_channel_thought` WRITE;
/*!40000 ALTER TABLE `thot_channel_thought` DISABLE KEYS */;
/*!40000 ALTER TABLE `thot_channel_thought` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thot_style`
--

DROP TABLE IF EXISTS `thot_style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thot_style` (
  `style_id` bigint NOT NULL COMMENT '样式id',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App',
  `style_code` varchar(32) NOT NULL COMMENT '样式编码',
  `style_name` varchar(64) NOT NULL COMMENT '样式名称',
  `style_content` varchar(256) DEFAULT NULL COMMENT '样式',
  `status` char(1) NOT NULL DEFAULT 'Y' COMMENT '状态',
  `description` varchar(256) DEFAULT NULL COMMENT '样式描述',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`style_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='样式信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thot_style`
--

LOCK TABLES `thot_style` WRITE;
/*!40000 ALTER TABLE `thot_style` DISABLE KEYS */;
INSERT INTO `thot_style` VALUES (3,'000000',10001,'TESSS','赛事','{ 懂打动懂adsadsa \n}','Y','懂adsadsa ',1,'2023-07-02 16:12:24',1,'2023-07-02 16:12:24',1,NULL),(1675417120801345138,'000000',10000,'TESSS','BBB','{ 懂打动懂adsadsa \n}','Y','懂adsadsa ',1,'2023-07-02 16:12:24',1,'2023-07-02 16:12:24',1,NULL),(1675417120801345238,'000000',10001,'TESSS','筛法赛','{ 懂打动懂adsadsa \n}','Y','懂adsadsa ',1,'2023-07-02 16:12:24',1,'2023-07-02 16:12:24',1,NULL),(1675417120801345538,'000000',10000,'TESSS','AAA','{ 懂打动懂adsadsa \n}','Y','懂adsadsa ',1,'2023-07-02 16:12:24',1,'2023-07-02 16:12:24',1,NULL);
/*!40000 ALTER TABLE `thot_style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thot_thought`
--

DROP TABLE IF EXISTS `thot_thought`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thot_thought` (
  `thought_id` bigint NOT NULL COMMENT '思绪ID',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` bigint NOT NULL COMMENT '接入App',
  `code` varchar(32) NOT NULL COMMENT '思绪编号',
  `main_img` varchar(256) NOT NULL COMMENT '主图',
  `banner_img` varchar(256) DEFAULT NULL COMMENT 'banner图',
  `title` varchar(64) DEFAULT NULL COMMENT '思绪标题',
  `title_style` bigint NOT NULL COMMENT '标题样式',
  `content` text NOT NULL COMMENT '思绪内容',
  `centent_style` bigint DEFAULT NULL COMMENT '内容样式',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '活动状态',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`thought_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='思绪信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thot_thought`
--

LOCK TABLES `thot_thought` WRITE;
/*!40000 ALTER TABLE `thot_thought` DISABLE KEYS */;
INSERT INTO `thot_thought` VALUES (1676807724019245697,'000000',10000,'3213214','1679079049956696066','1679079090997960705','11111',1675417120801345138,'432432432',1675417120801345138,0,'2023-07-12 20:49:11',1,'2023-07-06 12:18:10',1,'2023-07-23 18:36:09',1,NULL),(1676807724089215697,'000000',10000,'3213214','','','22222',1675417120801345538,'432432432',1675417120801345538,0,'2023-07-07 21:53:48',1,'2023-07-06 12:18:10',1,'2023-07-07 22:25:19',1,NULL),(1676807724089245617,'000000',10001,'3213214','','','33333',432432,'432432432',432432,0,'2023-07-07 21:53:50',1,'2023-07-06 12:18:10',1,'2023-07-06 12:18:10',1,NULL),(1676807724089245690,'000000',10000,'3213214','','','44444',1675417120801345538,'432432432',1675417120801345538,0,'2023-07-07 18:51:00',1,'2023-07-06 12:18:10',1,'2023-07-07 22:25:38',1,NULL),(1676807724089245697,'000000',10001,'3213214','','','55555',432432,'432432432',432432,2,'2023-07-07 18:50:50',1,'2023-07-06 12:18:10',1,'2023-07-06 12:18:10',1,NULL),(1683057449847013378,'000000',10000,'111111','1683057070694592514','1683057162872811521','1111111',1675417120801345138,'11111111 &nbsp;',1675417120801345538,0,NULL,1,'2023-07-23 18:12:20',1,'2023-07-23 18:25:32',1,NULL);
/*!40000 ALTER TABLE `thot_thought` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thot_topic`
--

DROP TABLE IF EXISTS `thot_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thot_topic` (
  `topic_id` bigint NOT NULL COMMENT '话题ID',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `app_id` varchar(32) NOT NULL COMMENT '接入App',
  `topic_code` varchar(32) NOT NULL COMMENT '话题编码',
  `topic_name` varchar(32) NOT NULL COMMENT '话题名称',
  `topic_banner_img` varchar(256) DEFAULT NULL COMMENT '话题主图',
  `topic_description` varchar(512) NOT NULL COMMENT '话题说明',
  `topic_content` text COMMENT '话题内容',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '话题状态',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话题信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thot_topic`
--

LOCK TABLES `thot_topic` WRITE;
/*!40000 ALTER TABLE `thot_topic` DISABLE KEYS */;
INSERT INTO `thot_topic` VALUES (1676165740056059906,'000000','10000','11111','22222','1677526625999724545,1677537008907001857',' style=\"width: 215px;\"',' style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\" style=\"width: 215px;\"',0,'2023-07-12 00:01:00','2023-07-28 00:00:00',1,'2023-07-04 17:47:09',1,'2023-07-12 18:07:49',1,NULL);
/*!40000 ALTER TABLE `thot_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thot_topic_attend`
--

DROP TABLE IF EXISTS `thot_topic_attend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thot_topic_attend` (
  `id` bigint NOT NULL,
  `attend_id` bigint NOT NULL COMMENT '评论id',
  `topic_id` bigint NOT NULL COMMENT '话题ID',
  `album_id` bigint DEFAULT NULL COMMENT '参与思绪集',
  `thought_id` bigint DEFAULT NULL COMMENT '思绪ID',
  `img` varchar(256) DEFAULT NULL COMMENT '回复图片地址',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `content` varchar(256) DEFAULT NULL COMMENT '话题评论',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话题参与表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thot_topic_attend`
--

LOCK TABLES `thot_topic_attend` WRITE;
/*!40000 ALTER TABLE `thot_topic_attend` DISABLE KEYS */;
/*!40000 ALTER TABLE `thot_topic_attend` ENABLE KEYS */;
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
