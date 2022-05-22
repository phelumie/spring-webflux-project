CREATE TABLE `sales` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` varchar(100) NOT NULL ,
  `product_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

