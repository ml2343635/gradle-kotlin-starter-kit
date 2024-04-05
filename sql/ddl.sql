SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ml_user
-- ----------------------------
DROP TABLE IF EXISTS `ml_user`;
CREATE TABLE `ml_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` bigint NOT NULL,
  `credential` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `role` enum('SUPER_ADMIN','UNKNOWN') COLLATE utf8mb4_bin NOT NULL,
  `update_at` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
