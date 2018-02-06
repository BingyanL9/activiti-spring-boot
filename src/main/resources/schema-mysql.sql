DROP TABLE IF EXISTS `studentinfo`;
DROP TABLE IF EXISTS `teacher`;
DROP TABLE IF EXISTS `club`;
DROP TABLE IF EXISTS `project`;
DROP TABLE IF EXISTS `activity`;
DROP TABLE IF EXISTS `dedicated_budget`;
DROP TABLE IF EXISTS `voucher`;
DROP TABLE IF EXISTS `application`;
DROP TABLE IF EXISTS `project_respon`;
DROP TABLE IF EXISTS `approval`;
DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `studentinfo` (
  `sno` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `student_name` varchar(100) NOT NULL,
  `password` varchar(16) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `teacher` (
  `tno` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `teacher_name` varchar(100) NOT NULL,
  `password` varchar(16) NOT NULL,
   `email` varchar(50) NOT NULL,
  `college` varchar(50) NOT NULL,
  `title` varchar(32),
  `role` varchar(10) NOT NULL,
  `leader_tno` bigint(20),
   CONSTRAINT `LEADER_TNO_FK` FOREIGN KEY (`leader_tno`) REFERENCES `teacher` (`tno`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `club` (
  `cno` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `club_name` varchar(100) NOT NULL,
  `password` varchar(16) NOT NULL,
  `college` varchar(50) NOT NULL,
  `leader_cno` bigint(20),
  CONSTRAINT `LEADER_CNO_FK` FOREIGN KEY (`leader_cno`) REFERENCES `club` (`cno`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `project` (
  `pno` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `project_name` varchar(100) NOT NULL,
  `budget` int(11),
  `starting_time` date,
  `end_time` date
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `activity` (
  `ano` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `activity_name` varchar(100) NOT NULL,
  `budget` int(11),
  `starting_time` date,
  `end_time` date,
  `charge_club` bigint(20),
   CONSTRAINT `CHARGE_CLUB_FK` FOREIGN KEY (`charge_club`) REFERENCES `club` (`cno`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dedicated_budget` (
  `tno` bigint(20) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `budget` int(11) NOT NULL,
  CONSTRAINT CONSTRAINT_C3 PRIMARY KEY (`tno`,`item_name`),
  CONSTRAINT `TNO_FK` FOREIGN KEY (`tno`) REFERENCES `teacher` (`tno`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `voucher` (
  `vno` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `invoice_code` varchar(32),
  `invoice_no` varchar(32),
  `voucher_type` varchar(100) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `item_money` int(11) NOT NULL,
  `enclosure` blob NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `application` (
  `apno` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `application_type` varchar(100) NOT NULL,
  `application_sno` bigint(20),
  `application_tno` bigint(20),
  `pno` bigint(20),
  `ano` bigint(20),
  `vno` bigint(20) NOT NULL,
  CONSTRAINT `APPLICATION_SNO_FK` FOREIGN KEY (`application_sno`) REFERENCES `studentinfo` (`sno`) ON DELETE CASCADE,
  CONSTRAINT `APPLICATION_TNO_FK` FOREIGN KEY (`application_tno`) REFERENCES `teacher` (`tno`) ON DELETE CASCADE,
  CONSTRAINT `PNO_FK` FOREIGN KEY (`pno`) REFERENCES `project` (`pno`) ON DELETE CASCADE,
  CONSTRAINT `ANO_FK` FOREIGN KEY (`ano`) REFERENCES `activity` (`ano`) ON DELETE CASCADE,
  CONSTRAINT `VNO_FK` FOREIGN KEY (`vno`) REFERENCES `voucher` (`vno`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `project_respon` (
  `pno` bigint(20) NOT NULL,
  `charge` bigint(20) NOT NULL,
  `level` int(8) NOT NULL,
  CONSTRAINT CONSTRAINT_C4 PRIMARY KEY (`pno`,`charge`,`level`),
  CONSTRAINT `PNO_FK2` FOREIGN KEY (`pno`) REFERENCES `project` (`pno`) ON DELETE CASCADE,
  CONSTRAINT `CHARGE_FK` FOREIGN KEY (`charge`) REFERENCES `teacher` (`tno`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `approval` (
  `apno` bigint(20) NOT NULL,
  `approval_person` bigint(20) NOT NULL default 0,
  `approval_club` bigint(20) NOT NULL default 0,
  `approval_time` date NOT NULL,
  `approval_statu` varchar(32) NOT NULL,
  `disapproval_reason` varchar(200),
  CONSTRAINT CONSTRAINT_C5 PRIMARY KEY (`apno`,`approval_person`,`approval_club`),
  CONSTRAINT `APNO_FK2` FOREIGN KEY (`apno`) REFERENCES `application` (`apno`) ON DELETE CASCADE,
  CONSTRAINT `APPLICATION_PERSON_FK` FOREIGN KEY (`approval_person`) REFERENCES `teacher` (`tno`),
  CONSTRAINT `APPLICATION_CLUB_FK` FOREIGN KEY (`approval_club`) REFERENCES `club` (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `feedback` (
  `fno` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `feedback_time` date NOT NULL,
  `iscorrect` bit(1) NOT NULL,
  `suggest` varchar(1000),
  `apno` bigint(20) NOT NULL,
  CONSTRAINT `APNO_FK3` FOREIGN KEY (`apno`) REFERENCES `application` (`apno`) ON DELETE CASCADE,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;