-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 伺服器版本:                        10.7.3-MariaDB - mariadb.org binary distribution
-- 伺服器作業系統:                      Win64
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 正在傾印表格  the_one_project.sys_user 的資料：~2 rows (近似值)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
REPLACE INTO `sys_user` (`id`, `account`, `active`, `password`, `user_name`, `sys_user_roles_id`) VALUES
	(1, '21053064', b'1', '0000', 'Oscar', 1),
	(2, '21053060', b'1', '0000', 'John', 2);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 正在傾印表格  the_one_project.sys_user_roles 的資料：~2 rows (近似值)
/*!40000 ALTER TABLE `sys_user_roles` DISABLE KEYS */;
REPLACE INTO `sys_user_roles` (`id`, `functions`, `rank`, `roles`) VALUES
	(1, 'ADMIN', 'ADMIN', 'ROLE_ADMIN'),
	(2, 'USER', 'USER', 'ROLE_USER');
/*!40000 ALTER TABLE `sys_user_roles` ENABLE KEYS */;

-- 正在傾印表格  the_one_project.users 的資料：~7 rows (近似值)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
REPLACE INTO `users` (`id`, `email`, `encrpte_password`, `user_id`, `user_name`) VALUES
	(1, 'test@test.com', 'test', 'd2900473-82f8-4c43-a8d0-8429a2f6af6c', 'Oscar'),
	(5, 'test1@test.com', 'test', 'b3977d1c-5504-4fca-a938-23fe92614cc2', 'John'),
	(8, 'test2@test.com', 'test', '7421a580-a566-4b6d-8d10-23d655cfdf4a', 'Vaida'),
	(9, 'test3@test.com', 'test', '7d46c028-ae94-4aac-9636-70608e828c2f', 'Strange'),
	(10, 'test4@test.com', '$2a$10$fFkgHtTHhhQIzMAnl1Sfw.m2UdkWOsGLlwgY5GN4zW34vsVnqL7zq', '6f7a110d-44fb-4774-8a08-938abe1b59e3', 'Ben'),
	(11, 'test5@test.com', '$2a$10$SU9yrwk7rELuSjbEI7NSeeXvqk.BgGCTpqP.ZPkl2w/0Vy5g4hqGi', '2495734d-9984-44e6-9da1-94a1d5166043', 'Hank'),
	(12, 'test6@test.com', '$2a$10$jcLusRpIWBnqbupSJz47lOeKb4Aaj9jIOFZCtw4CTxdImBJd7y82u', '93e8dba7-b211-461c-a841-0463e1b43292', 'Ikea'),
	(16, 'test7@test.com', '$2a$10$QfasSFY3ZhQ8NWE2PSo8ROMMbItdLmo3i4Olgofp5hNcJmBgSWm2.', 'e21a8aa4-5e27-4575-b6fa-22fc5f21599d', 'Luk');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
