-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.21 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for toratax_db
CREATE DATABASE IF NOT EXISTS `toratax_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `toratax_db`;

-- Dumping structure for table toratax_db.document_type
CREATE TABLE IF NOT EXISTS `document_type` (
  `id` int NOT NULL,
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ref_table_name` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table toratax_db.permanent_documents
CREATE TABLE IF NOT EXISTS `permanent_documents` (
  `id` int NOT NULL,
  `user_permanent_data_id` int NOT NULL,
  `document_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `document_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `uploaded_date` date DEFAULT NULL,
  `document_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_parmanent_documents_user_permanent_data` (`user_permanent_data_id`),
  CONSTRAINT `FK_parmanent_documents_user_permanent_data` FOREIGN KEY (`user_permanent_data_id`) REFERENCES `user_permanent_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table toratax_db.permanent_documents_seq
CREATE TABLE IF NOT EXISTS `permanent_documents_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table toratax_db.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL DEFAULT '0',
  `role_description` varchar(200) NOT NULL DEFAULT '0',
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table toratax_db.toratax_user
CREATE TABLE IF NOT EXISTS `toratax_user` (
  `id` bigint NOT NULL,
  `first_name` varchar(100) NOT NULL DEFAULT '0',
  `password` varchar(500) NOT NULL DEFAULT '0',
  `last_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `role_code` varchar(20) NOT NULL DEFAULT '0',
  `email_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `start_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_date` date DEFAULT NULL,
  `phone_no` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table toratax_db.toratax_user_seq
CREATE TABLE IF NOT EXISTS `toratax_user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table toratax_db.user_permanent_data
CREATE TABLE IF NOT EXISTS `user_permanent_data` (
  `id` int NOT NULL,
  `aadhar_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pan_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `passport_no` varchar(100) DEFAULT NULL,
  `driving_license` varchar(100) DEFAULT NULL,
  `voter_id` varchar(100) DEFAULT NULL,
  `folder_path` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_folder_path` (`user_id`,`folder_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table toratax_db.user_permanent_data_seq
CREATE TABLE IF NOT EXISTS `user_permanent_data_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table toratax_db.user_yearly_information
CREATE TABLE IF NOT EXISTS `user_yearly_information` (
  `id` int NOT NULL,
  `itr_year` int NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `transaction_id` varchar(100) DEFAULT '',
  `payment_status` varchar(100) DEFAULT '',
  `salaried` int NOT NULL,
  `yearly_folder_path` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `itr_year_yearly_folder_path` (`itr_year`,`yearly_folder_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table toratax_db.user_yearly_information_seq
CREATE TABLE IF NOT EXISTS `user_yearly_information_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table toratax_db.yearly_documents
CREATE TABLE IF NOT EXISTS `yearly_documents` (
  `id` int NOT NULL,
  `yearly_information_id` int NOT NULL,
  `document_name` varchar(100) DEFAULT '',
  `document_path` varchar(100) DEFAULT '',
  `uploaded_date` date DEFAULT NULL,
  `document_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__user_yearly_information` (`yearly_information_id`),
  CONSTRAINT `FK__user_yearly_information` FOREIGN KEY (`yearly_information_id`) REFERENCES `user_yearly_information` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table toratax_db.yearly_documents_seq
CREATE TABLE IF NOT EXISTS `yearly_documents_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
