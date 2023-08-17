-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 192.168.2.100    Database: ensoul_biz
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-17 17:27:50
