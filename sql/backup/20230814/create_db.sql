CREATE DATABASE ensoul_cloud DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE ensoul_job DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE ensoul_nacos DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE ensoul_powerjob DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE ensoul_seata DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE ensoul_platform DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE ensoul_biz DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;


CREATE USER ensoul identified by 'ensoul';;
GRANT CREATE, CREATE VIEW, DELETE, EVENT, INSERT, LOCK TABLES, SELECT, TRIGGER, UPDATE ON ensoul_cloud.* TO ensoul;
GRANT CREATE, CREATE VIEW, DELETE, EVENT, INSERT, LOCK TABLES, SELECT, TRIGGER, UPDATE ON ensoul_job.* TO ensoul;
GRANT CREATE, CREATE VIEW, DELETE, EVENT, INSERT, LOCK TABLES, SELECT, TRIGGER, UPDATE ON ensoul_nacos.* TO ensoul;
GRANT CREATE, CREATE VIEW, DELETE, EVENT, INSERT, LOCK TABLES, SELECT, TRIGGER, UPDATE ON ensoul_powerjob.* TO ensoul;
GRANT CREATE, CREATE VIEW, DELETE, EVENT, INSERT, LOCK TABLES, SELECT, TRIGGER, UPDATE ON ensoul_seata.* TO ensoul;
GRANT CREATE, CREATE VIEW, DELETE, EVENT, INSERT, LOCK TABLES, SELECT, TRIGGER, UPDATE ON ensoul_platform.* TO ensoul;
GRANT CREATE, CREATE VIEW, DELETE, EVENT, INSERT, LOCK TABLES, SELECT, TRIGGER, UPDATE ON ensoul_biz.* TO ensoul;
