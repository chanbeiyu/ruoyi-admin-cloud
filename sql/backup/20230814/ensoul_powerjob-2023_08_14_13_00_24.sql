-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ensoul_powerjob
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
-- Table structure for table `app_info`
--

DROP TABLE IF EXISTS `app_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '应用ID',
  `app_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用名称',
  `current_server` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Server地址,用于负责调度应用的ActorSystem地址',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx01_app_info` (`app_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='应用表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_info`
--

LOCK TABLES `app_info` WRITE;
/*!40000 ALTER TABLE `app_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `app_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `container_info`
--

DROP TABLE IF EXISTS `container_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `container_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '容器ID',
  `app_id` bigint NOT NULL COMMENT '应用ID',
  `container_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '容器名称',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `last_deploy_time` datetime DEFAULT NULL COMMENT '上次部署时间',
  `source_info` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源信息,内容取决于source_type\n1、FatJar -> String\n2、Git -> JSON，{"repo”:””仓库,”branch”:”分支”,”username”:”账号,”password”:”密码”}',
  `source_type` int NOT NULL COMMENT '资源类型,1:FatJar/2:Git',
  `status` int NOT NULL COMMENT '状态,1:正常ENABLE/2:已禁用DISABLE/99:已删除DELETED',
  `version` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx01_container_info` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='容器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `container_info`
--

LOCK TABLES `container_info` WRITE;
/*!40000 ALTER TABLE `container_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `container_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instance_info`
--

DROP TABLE IF EXISTS `instance_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instance_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务实例ID',
  `app_id` bigint NOT NULL COMMENT '应用ID',
  `instance_id` bigint NOT NULL COMMENT '任务实例ID',
  `type` int NOT NULL COMMENT '任务实例类型,1:普通NORMAL/2:工作流WORKFLOW',
  `job_id` bigint NOT NULL COMMENT '任务ID',
  `instance_params` longtext COLLATE utf8mb4_general_ci COMMENT '任务动态参数',
  `job_params` longtext COLLATE utf8mb4_general_ci COMMENT '任务静态参数',
  `actual_trigger_time` bigint DEFAULT NULL COMMENT '实际触发时间',
  `expected_trigger_time` bigint DEFAULT NULL COMMENT '计划触发时间',
  `finished_time` bigint DEFAULT NULL COMMENT '执行结束时间',
  `last_report_time` bigint DEFAULT NULL COMMENT '最后上报时间',
  `result` longtext COLLATE utf8mb4_general_ci COMMENT '执行结果',
  `running_times` bigint DEFAULT NULL COMMENT '总执行次数,用于重试判断',
  `status` int NOT NULL COMMENT '任务状态,1:等待派发WAITING_DISPATCH/2:等待Worker接收WAITING_WORKER_RECEIVE/3:运行中RUNNING/4:失败FAILED/5:成功SUCCEED/9:取消CANCELED/10:手动停止STOPPED',
  `task_tracker_address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'TaskTracker地址',
  `wf_instance_id` bigint DEFAULT NULL COMMENT '工作流实例ID',
  `additional_data` longtext COLLATE utf8mb4_general_ci COMMENT '附加信息 (JSON)',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx01_instance_info` (`job_id`,`status`),
  KEY `idx02_instance_info` (`app_id`,`status`),
  KEY `idx03_instance_info` (`instance_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='任务实例表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instance_info`
--

LOCK TABLES `instance_info` WRITE;
/*!40000 ALTER TABLE `instance_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `instance_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_info`
--

DROP TABLE IF EXISTS `job_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app_id` bigint DEFAULT NULL COMMENT '应用ID',
  `job_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '任务名称',
  `job_description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '任务描述',
  `job_params` text COLLATE utf8mb4_general_ci COMMENT '任务默认参数',
  `concurrency` int DEFAULT NULL COMMENT '并发度,同时执行某个任务的最大线程数量',
  `designated_workers` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '运行节点,空:不限(多值逗号分割)',
  `dispatch_strategy` int DEFAULT NULL COMMENT '投递策略,1:健康优先/2:随机',
  `execute_type` int NOT NULL COMMENT '执行类型,1:单机STANDALONE/2:广播BROADCAST/3:MAP_REDUCE/4:MAP',
  `instance_retry_num` int NOT NULL DEFAULT '0' COMMENT 'Instance重试次数',
  `instance_time_limit` bigint NOT NULL DEFAULT '0' COMMENT '任务整体超时时间',
  `lifecycle` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生命周期',
  `max_instance_num` int NOT NULL DEFAULT '1' COMMENT '最大同时运行任务数,默认 1',
  `max_worker_count` int NOT NULL DEFAULT '0' COMMENT '最大运行节点数量',
  `min_cpu_cores` double NOT NULL DEFAULT '0' COMMENT '最低CPU核心数量,0:不限',
  `min_disk_space` double NOT NULL DEFAULT '0' COMMENT '最低磁盘空间(GB),0:不限',
  `min_memory_space` double NOT NULL DEFAULT '0' COMMENT '最低内存空间(GB),0:不限',
  `next_trigger_time` bigint DEFAULT NULL COMMENT '下一次调度时间',
  `notify_user_ids` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '报警用户(多值逗号分割)',
  `processor_info` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '执行器信息',
  `processor_type` int NOT NULL COMMENT '执行器类型,1:内建处理器BUILT_IN/2:SHELL/3:PYTHON/4:外部处理器（动态加载）EXTERNAL',
  `status` int NOT NULL COMMENT '状态,1:正常ENABLE/2:已禁用DISABLE/99:已删除DELETED',
  `task_retry_num` int NOT NULL DEFAULT '0' COMMENT 'Task重试次数',
  `time_expression` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '时间表达式,内容取决于time_expression_type,1:CRON/2:NULL/3:LONG/4:LONG',
  `time_expression_type` int NOT NULL COMMENT '时间表达式类型,1:CRON/2:API/3:FIX_RATE/4:FIX_DELAY,5:WORKFLOW\n）',
  `tag` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'TAG',
  `log_config` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志配置',
  `extra` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '扩展字段',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx01_job_info` (`app_id`,`status`,`time_expression_type`,`next_trigger_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_info`
--

LOCK TABLES `job_info` WRITE;
/*!40000 ALTER TABLE `job_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_lock`
--

DROP TABLE IF EXISTS `oms_lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oms_lock` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '序号ID',
  `lock_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `max_lock_time` bigint DEFAULT NULL COMMENT '最长持锁时间',
  `ownerip` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '拥有者IP',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx01_oms_lock` (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据库锁';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_lock`
--

LOCK TABLES `oms_lock` WRITE;
/*!40000 ALTER TABLE `oms_lock` DISABLE KEYS */;
/*!40000 ALTER TABLE `oms_lock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pj_app_info`
--

DROP TABLE IF EXISTS `pj_app_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pj_app_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app_name` varchar(255) DEFAULT NULL,
  `current_server` varchar(255) DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx01_app_info` (`app_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pj_app_info`
--

LOCK TABLES `pj_app_info` WRITE;
/*!40000 ALTER TABLE `pj_app_info` DISABLE KEYS */;
INSERT INTO `pj_app_info` VALUES (1,'ruoyi-worker',NULL,'2023-06-19 11:24:14.865000','2023-06-19 11:24:14.866000','123456'),(2,'ensoul-alkaid-admin',NULL,'2023-06-19 13:54:45.469000','2023-06-19 13:54:45.469000','123456');
/*!40000 ALTER TABLE `pj_app_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pj_container_info`
--

DROP TABLE IF EXISTS `pj_container_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pj_container_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app_id` bigint DEFAULT NULL,
  `container_name` varchar(255) DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  `last_deploy_time` datetime(6) DEFAULT NULL,
  `source_info` varchar(255) DEFAULT NULL,
  `source_type` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx01_container_info` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pj_container_info`
--

LOCK TABLES `pj_container_info` WRITE;
/*!40000 ALTER TABLE `pj_container_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `pj_container_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pj_instance_info`
--

DROP TABLE IF EXISTS `pj_instance_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pj_instance_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `actual_trigger_time` bigint DEFAULT NULL,
  `app_id` bigint DEFAULT NULL,
  `expected_trigger_time` bigint DEFAULT NULL,
  `finished_time` bigint DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  `instance_id` bigint DEFAULT NULL,
  `instance_params` longtext,
  `job_id` bigint DEFAULT NULL,
  `job_params` longtext,
  `last_report_time` bigint DEFAULT NULL,
  `result` longtext,
  `running_times` bigint DEFAULT NULL,
  `status` int DEFAULT NULL,
  `task_tracker_address` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `wf_instance_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx01_instance_info` (`job_id`,`status`),
  KEY `idx02_instance_info` (`app_id`,`status`),
  KEY `idx03_instance_info` (`instance_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pj_instance_info`
--

LOCK TABLES `pj_instance_info` WRITE;
/*!40000 ALTER TABLE `pj_instance_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `pj_instance_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pj_job_info`
--

DROP TABLE IF EXISTS `pj_job_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pj_job_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `alarm_config` varchar(255) DEFAULT NULL,
  `app_id` bigint DEFAULT NULL,
  `concurrency` int DEFAULT NULL,
  `designated_workers` varchar(255) DEFAULT NULL,
  `dispatch_strategy` int DEFAULT NULL,
  `execute_type` int DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  `instance_retry_num` int DEFAULT NULL,
  `instance_time_limit` bigint DEFAULT NULL,
  `job_description` varchar(255) DEFAULT NULL,
  `job_name` varchar(255) DEFAULT NULL,
  `job_params` longtext,
  `lifecycle` varchar(255) DEFAULT NULL,
  `log_config` varchar(255) DEFAULT NULL,
  `max_instance_num` int DEFAULT NULL,
  `max_worker_count` int DEFAULT NULL,
  `min_cpu_cores` double NOT NULL,
  `min_disk_space` double NOT NULL,
  `min_memory_space` double NOT NULL,
  `next_trigger_time` bigint DEFAULT NULL,
  `notify_user_ids` varchar(255) DEFAULT NULL,
  `processor_info` varchar(255) DEFAULT NULL,
  `processor_type` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `task_retry_num` int DEFAULT NULL,
  `time_expression` varchar(255) DEFAULT NULL,
  `time_expression_type` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx01_job_info` (`app_id`,`status`,`time_expression_type`,`next_trigger_time`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pj_job_info`
--

LOCK TABLES `pj_job_info` WRITE;
/*!40000 ALTER TABLE `pj_job_info` DISABLE KEYS */;
INSERT INTO `pj_job_info` VALUES (1,'{\"alertThreshold\":0,\"silenceWindowLen\":0,\"statisticWindowLen\":0}',1,5,'',2,1,NULL,'2023-06-02 15:01:27.717000','2023-06-02 16:03:19.462000',1,0,'','单机处理器执行测试',NULL,'{}','{\"type\":1}',0,0,0,0,0,NULL,NULL,'org.dromara.job.processors.StandaloneProcessorDemo',1,1,NULL,1,'30000',3),(2,'{\"alertThreshold\":0,\"silenceWindowLen\":0,\"statisticWindowLen\":0}',1,5,'',1,2,NULL,'2023-06-02 15:04:45.342000','2023-06-02 16:04:09.736000',0,0,NULL,'广播处理器测试',NULL,'{}','{\"type\":1}',0,0,0,0,0,NULL,NULL,'org.dromara.job.processors.BroadcastProcessorDemo',1,1,NULL,1,'30000',3),(3,'{\"alertThreshold\":0,\"silenceWindowLen\":0,\"statisticWindowLen\":0}',1,5,'',1,4,NULL,'2023-06-02 15:13:23.519000','2023-06-02 16:03:22.421000',0,0,NULL,'Map处理器测试',NULL,'{}','{\"type\":1}',0,0,0,0,0,NULL,NULL,'org.dromara.job.processors.MapProcessorDemo',1,2,NULL,1,'1000',3),(4,'{\"alertThreshold\":0,\"silenceWindowLen\":0,\"statisticWindowLen\":0}',1,5,'',1,3,NULL,'2023-06-02 15:45:25.896000','2023-06-02 16:03:23.125000',0,0,NULL,'MapReduce处理器测试',NULL,'{}','{\"type\":1}',0,0,0,0,0,NULL,NULL,'org.dromara.job.processors.MapReduceProcessorDemo',1,2,NULL,1,'1000',3);
/*!40000 ALTER TABLE `pj_job_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pj_oms_lock`
--

DROP TABLE IF EXISTS `pj_oms_lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pj_oms_lock` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  `lock_name` varchar(255) DEFAULT NULL,
  `max_lock_time` bigint DEFAULT NULL,
  `ownerip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx01_oms_lock` (`lock_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pj_oms_lock`
--

LOCK TABLES `pj_oms_lock` WRITE;
/*!40000 ALTER TABLE `pj_oms_lock` DISABLE KEYS */;
/*!40000 ALTER TABLE `pj_oms_lock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pj_server_info`
--

DROP TABLE IF EXISTS `pj_server_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pj_server_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx01_server_info` (`ip`),
  KEY `idx01_server_info` (`gmt_modified`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pj_server_info`
--

LOCK TABLES `pj_server_info` WRITE;
/*!40000 ALTER TABLE `pj_server_info` DISABLE KEYS */;
INSERT INTO `pj_server_info` VALUES (1,'2023-06-19 10:04:21.307000','2023-06-21 18:56:50.101000','192.168.31.37');
/*!40000 ALTER TABLE `pj_server_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pj_user_info`
--

DROP TABLE IF EXISTS `pj_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pj_user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `web_hook` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uidx01_user_info` (`username`),
  KEY `uidx02_user_info` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pj_user_info`
--

LOCK TABLES `pj_user_info` WRITE;
/*!40000 ALTER TABLE `pj_user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `pj_user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pj_workflow_info`
--

DROP TABLE IF EXISTS `pj_workflow_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pj_workflow_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app_id` bigint DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  `lifecycle` varchar(255) DEFAULT NULL,
  `max_wf_instance_num` int DEFAULT NULL,
  `next_trigger_time` bigint DEFAULT NULL,
  `notify_user_ids` varchar(255) DEFAULT NULL,
  `pedag` longtext,
  `status` int DEFAULT NULL,
  `time_expression` varchar(255) DEFAULT NULL,
  `time_expression_type` int DEFAULT NULL,
  `wf_description` varchar(255) DEFAULT NULL,
  `wf_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx01_workflow_info` (`app_id`,`status`,`time_expression_type`,`next_trigger_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pj_workflow_info`
--

LOCK TABLES `pj_workflow_info` WRITE;
/*!40000 ALTER TABLE `pj_workflow_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `pj_workflow_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pj_workflow_instance_info`
--

DROP TABLE IF EXISTS `pj_workflow_instance_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pj_workflow_instance_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `actual_trigger_time` bigint DEFAULT NULL,
  `app_id` bigint DEFAULT NULL,
  `dag` longtext,
  `expected_trigger_time` bigint DEFAULT NULL,
  `finished_time` bigint DEFAULT NULL,
  `gmt_create` datetime(6) DEFAULT NULL,
  `gmt_modified` datetime(6) DEFAULT NULL,
  `parent_wf_instance_id` bigint DEFAULT NULL,
  `result` longtext,
  `status` int DEFAULT NULL,
  `wf_context` longtext,
  `wf_init_params` longtext,
  `wf_instance_id` bigint DEFAULT NULL,
  `workflow_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx01_wf_instance` (`wf_instance_id`),
  KEY `idx01_wf_instance` (`workflow_id`,`status`,`app_id`,`expected_trigger_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pj_workflow_instance_info`
--

LOCK TABLES `pj_workflow_instance_info` WRITE;
/*!40000 ALTER TABLE `pj_workflow_instance_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `pj_workflow_instance_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pj_workflow_node_info`
--

DROP TABLE IF EXISTS `pj_workflow_node_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pj_workflow_node_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app_id` bigint NOT NULL,
  `enable` bit(1) NOT NULL,
  `extra` longtext,
  `gmt_create` datetime(6) NOT NULL,
  `gmt_modified` datetime(6) NOT NULL,
  `job_id` bigint DEFAULT NULL,
  `node_name` varchar(255) DEFAULT NULL,
  `node_params` longtext,
  `skip_when_failed` bit(1) NOT NULL,
  `type` int DEFAULT NULL,
  `workflow_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx01_workflow_node_info` (`workflow_id`,`gmt_create`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pj_workflow_node_info`
--

LOCK TABLES `pj_workflow_node_info` WRITE;
/*!40000 ALTER TABLE `pj_workflow_node_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `pj_workflow_node_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `server_info`
--

DROP TABLE IF EXISTS `server_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `server_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '服务器ID',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `ip` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务器IP地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx01_server_info` (`ip`),
  KEY `idx01_server_info` (`gmt_modified`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='服务器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `server_info`
--

LOCK TABLES `server_info` WRITE;
/*!40000 ALTER TABLE `server_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `extra` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '扩展字段',
  `web_hook` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'webhook地址',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx01_user_info` (`username`),
  UNIQUE KEY `uidx02_user_info` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflow_info`
--

DROP TABLE IF EXISTS `workflow_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflow_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '工作流ID',
  `app_id` bigint NOT NULL COMMENT '应用ID',
  `wf_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作流名称',
  `wf_description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流描述',
  `extra` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '扩展字段',
  `lifecycle` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生命周期',
  `max_wf_instance_num` int NOT NULL DEFAULT '1' COMMENT '最大运行工作流数量,默认 1',
  `next_trigger_time` bigint DEFAULT NULL COMMENT '下次调度时间',
  `notify_user_ids` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '报警用户(多值逗号分割)',
  `pedag` text COLLATE utf8mb4_general_ci COMMENT 'DAG信息(JSON)',
  `status` int NOT NULL COMMENT '状态,1:正常ENABLE/2:已禁用DISABLE/99:已删除DELETED',
  `time_expression` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '时间表达式,内容取决于time_expression_type,1:CRON/2:NULL/3:LONG/4:LONG',
  `time_expression_type` int NOT NULL COMMENT '时间表达式类型,1:CRON/2:API/3:FIX_RATE/4:FIX_DELAY,5:WORKFLOW\n）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx01_workflow_info` (`app_id`,`status`,`time_expression_type`,`next_trigger_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='工作流表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow_info`
--

LOCK TABLES `workflow_info` WRITE;
/*!40000 ALTER TABLE `workflow_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflow_instance_info`
--

DROP TABLE IF EXISTS `workflow_instance_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflow_instance_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '工作流实例ID',
  `wf_instance_id` bigint DEFAULT NULL COMMENT '工作流实例ID',
  `workflow_id` bigint DEFAULT NULL COMMENT '工作流ID',
  `actual_trigger_time` bigint DEFAULT NULL COMMENT '实际触发时间',
  `app_id` bigint DEFAULT NULL COMMENT '应用ID',
  `dag` text COLLATE utf8mb4_general_ci COMMENT 'DAG信息(JSON)',
  `expected_trigger_time` bigint DEFAULT NULL COMMENT '计划触发时间',
  `finished_time` bigint DEFAULT NULL COMMENT '执行结束时间',
  `result` text COLLATE utf8mb4_general_ci COMMENT '执行结果',
  `status` int DEFAULT NULL COMMENT '工作流实例状态,1:等待调度WAITING/2:运行中RUNNING/3:失败FAILED/4:成功SUCCEED/10:手动停止STOPPED',
  `wf_context` text COLLATE utf8mb4_general_ci COMMENT '工作流上下文',
  `wf_init_params` text COLLATE utf8mb4_general_ci COMMENT '工作流启动参数',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx01_wf_instance` (`wf_instance_id`),
  KEY `idx01_wf_instance` (`workflow_id`,`status`),
  KEY `idx02_wf_instance` (`app_id`,`status`,`expected_trigger_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='工作流实例表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow_instance_info`
--

LOCK TABLES `workflow_instance_info` WRITE;
/*!40000 ALTER TABLE `workflow_instance_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow_instance_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflow_node_info`
--

DROP TABLE IF EXISTS `workflow_node_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflow_node_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '节点ID',
  `app_id` bigint NOT NULL COMMENT '应用ID',
  `enable` bit(1) NOT NULL COMMENT '是否启动,0:否/1:是',
  `extra` text COLLATE utf8mb4_general_ci COMMENT '扩展字段',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `job_id` bigint DEFAULT NULL COMMENT '任务ID',
  `node_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '节点名称',
  `node_params` text COLLATE utf8mb4_general_ci COMMENT '节点参数',
  `skip_when_failed` bit(1) NOT NULL COMMENT '是否允许失败跳过,0:否/1:是',
  `type` int DEFAULT NULL COMMENT '节点类型,1:任务JOB',
  `workflow_id` bigint DEFAULT NULL COMMENT '工作流ID',
  PRIMARY KEY (`id`),
  KEY `idx01_workflow_node_info` (`workflow_id`,`gmt_create`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='工作流节点表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow_node_info`
--

LOCK TABLES `workflow_node_info` WRITE;
/*!40000 ALTER TABLE `workflow_node_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflow_node_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-14 13:00:25
