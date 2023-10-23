SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `r7` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `r7`;

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng',
  `username` varchar(30) NOT NULL COMMENT 'Admin dùng username để đăng nhập',
  `hashed_password` varchar(64) NOT NULL COMMENT 'Mật khẩu sau khi hash bằng SHA256',
  `role` enum('super_admin','admin') NOT NULL COMMENT 'Phân quyền, super_admin có thể tạo ra admin',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `admin` (`id`, `username`, `hashed_password`, `role`, `create_at`, `update_at`) VALUES
(1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'super_admin', '2023-10-23 10:47:51', NULL),
(2, 'adonis', '3fd725a89717d2175289be8011e2b983ab6428bc0064f788f1a9d6eb014c2253', 'admin', '2023-10-23 10:49:18', NULL),
(3, 'sinoda', '1c55e32786f8fd46880d95c4491f3b0ddc265ca349bd2c5ceb4e57e24b3d887b', 'admin', '2023-10-24 10:50:13', NULL);

CREATE TABLE IF NOT EXISTS `cart` (
  `user_id` int(11) NOT NULL COMMENT 'Liên kết với id bảng user',
  `product_id` int(11) NOT NULL COMMENT 'Liên kết với id bảng product',
  `amount` int(11) NOT NULL DEFAULT 1 COMMENT 'Số lượng sẩn phẩm được chọn',
  PRIMARY KEY (`user_id`,`product_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng',
  `name` varchar(30) NOT NULL COMMENT 'Tên loại sản phẩm',
  `slug` varchar(30) NOT NULL COMMENT 'Đường dẫn trên web',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `category` (`id`, `name`, `slug`, `create_at`, `update_at`) VALUES
(1, 'Modern 14 series', 'modern-14', '2023-10-23 12:11:06', NULL),
(2, 'Modern 15 series', 'modern-15', '2023-10-24 15:19:37', NULL),
(3, 'GF series', 'gf-series', '2023-10-24 11:14:38', NULL),
(4, 'GS series', 'gs-series', '2023-10-23 11:14:38', NULL),
(5, 'Bravo series', 'bravo-series', '2023-10-25 11:16:27', NULL);

CREATE TABLE IF NOT EXISTS `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng',
  `user_id` int(11) NOT NULL COMMENT 'Liên kết với id bảng user',
  `total_price` bigint(20) NOT NULL COMMENT 'Được tính dựa trên bảng order_data',
  `payment` enum('cod','vnpay') NOT NULL COMMENT 'Lựa chọn phương thức thanh toán',
  `status` enum('processing','shipping','done','canceled') NOT NULL COMMENT 'Tình trạng đơn hàng',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `order_data` (
  `order_id` int(11) NOT NULL COMMENT 'Liên kết với id bảng order',
  `product_id` int(11) NOT NULL COMMENT 'Liên kết với id bảng product',
  `amount` int(11) NOT NULL DEFAULT 1 COMMENT 'Số lượng sẩn phẩm được chọn',
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng',
  `brand` varchar(30) NOT NULL DEFAULT 'MSI' COMMENT 'Tên hãng, mặc định là MSI',
  `model` varchar(100) NOT NULL COMMENT 'Tên sản phẩm',
  `category_id` int(11) NOT NULL COMMENT 'ID loại sản phẩm',
  `cpu` varchar(50) NOT NULL COMMENT 'Tên CPU',
  `ram` varchar(50) NOT NULL COMMENT 'Dung lượng RAM',
  `vga` varchar(50) NOT NULL COMMENT 'Tên VGA',
  `screen_size` varchar(50) NOT NULL COMMENT 'Kích thước màn hình',
  `hard_disk` varchar(50) NOT NULL COMMENT 'Dung lượng ổ cứng',
  `os` varchar(50) NOT NULL DEFAULT 'Windows 10' COMMENT 'Hệ điều hành được cài đặt sẵn',
  `price` bigint(20) NOT NULL COMMENT 'Giá sản phẩm',
  `amount` int(11) NOT NULL COMMENT 'Số lượng sẩn phẩm hiện có',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `product` (`id`, `brand`, `model`, `category_id`, `cpu`, `ram`, `vga`, `screen_size`, `hard_disk`, `os`, `price`, `amount`, `create_at`, `update_at`) VALUES
(1, 'MSI', 'MSI MODERN 14 C11M-011VN', 1, 'Intel® Core™ i3 1115G4', '8GB DDR4 3200Mhz Onboard', 'Intel® UHD', '14\" FHD (1920 x 1080)', '512GB NVMe PCIe SSD Gen 3', 'Windows 11 Home', 9999000, 153, '2023-10-23 08:28:47', NULL),
(2, 'MSI', 'MSI MODERN 14 C7M-083VN', 1, 'AMD Ryzen™ 5 7530U', '8GB DDR4 3200Mhz', 'AMD Radeon™ Graphics', '14\" FHD (1920 x 1080)', '512GB NVMe PCIe SSD Gen 3', 'Windows 11 Home', 11799000, 198, '2023-10-23 11:23:38', NULL),
(3, 'MSI', 'MSI MODERN 14 C12MO-660VN', 1, 'Intel® Core™ i5 1235U', '16GB DDR4 3200Mhz Onboard', 'Intel® Iris® Xe Graphics', '14\" FHD (1920 x 1080) IPS', '512GB NVMe PCIe Gen 3 SSD', 'Windows 11 Home', 13299000, 166, '2023-10-23 13:00:08', NULL),
(4, 'MSI', 'MSI MODERN 15 B12MO-628VN', 2, 'Intel® Core™ i5 1235U', '16GB DDR4 3200Mhz Onboard', 'Intel® Iris® Xe Graphics', '15.6\" FHD (1920 x 1080) IPS', '512GB NVMe PCIe Gen 3 SSD', 'Windows 11 Home', 13699000, 182, '2023-10-23 13:04:49', NULL),
(5, 'MSI', 'MSI MODERN 15 B7M-231VN', 2, 'AMD Ryzen™ 5 7530U', '16GB DDR4 3200Mhz Onboard', 'AMD Radeon™ Graphics', '15.6\" FHD (1920 x 1080) IPS', '512GB NVMe PCIe Gen 3 SSD', 'Windows 10', 13099000, 282, '2023-10-23 13:08:25', NULL),
(6, 'MSI', 'MSI MODERN 15 B7M-238VN', 2, 'AMD Ryzen™ 7 7730U', '16GB DDR4 3200Mhz Onboard', 'AMD Radeon™ Graphics', '15.6\" FHD (1920 x 1080) IPS', '512GB NVMe PCIe Gen 3 SSD', 'Windows 11 Home', 14999000, 243, '2023-10-23 13:10:38', NULL),
(7, 'MSI', 'MSI GF63 Thin 11SC 664VN', 3, 'Intel® Core™ i5 11400H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce GTX 1650 4GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 3 SSD', 'Windows 10 Home', 14990000, 178, '2023-10-23 13:11:55', NULL),
(8, 'MSI', 'MSI GF63 12UCX 841VN', 3, 'Intel® Core™ i5 12450H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 2050 4GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 3 SSD', 'Windows 10 Home', 16490000, 198, '2023-10-23 13:17:13', NULL),
(9, 'MSI', 'MSI GF63 Thin 11UD 473VN', 3, 'Intel® Core™ i5 11400H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 3050 Ti 4GB GDDR6', '15.6\" FHD (1920 x 1080)', '512GB NVMe PCIe Gen 3 SSD', 'Windows 10 Home', 17490000, 251, '2023-10-23 13:19:24', NULL),
(10, 'MSI', 'MSI GF63 Thin 11UC 1228VN', 3, 'Intel® Core™ i7 11800H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 3050 4GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 3 SSD', 'Windows 10 Home', 18890000, 313, '2023-10-23 13:21:56', NULL),
(11, 'MSI', 'MSI GF63 12UC 887VN', 3, 'Intel® Core™ i7 12650H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 3050 4GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 4 SSD', 'Windows 10 Home', 19890000, 312, '2023-10-23 13:24:15', NULL),
(12, 'MSI', 'MSI GF63 Thin 12VE 460VN', 3, 'Intel® Core™ i5 12450H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 4050 6GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 4 SSD', 'Windows 10 Home', 20890000, 129, '2023-10-23 13:28:20', NULL),
(13, 'MSI', 'MSI GS66 Stealth 11UG 210VN', 4, 'Intel® Core™ i7 11800H', '32GB (16x2) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 3070 Max-Q 8GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 300Hz', '2TB NVMe PCIe Gen 4 SSD', 'Windows 11 Home', 38990000, 34, '2023-10-23 13:30:08', NULL),
(14, 'MSI', 'MSI GS77 12UH 075VN', 4, 'Intel® Core™ i9 12900H', '32GB (16x2) DDR5 4800MHz (2 slots)', 'NVIDIA GeForce RTX 3080 Max-Q 8GB GDDR6', '17.3\" QHD (2560 x 1440) IPS 240Hz', '2TB NVMe PCIe Gen 4 SSD', 'Windows 11 Home', 64490000, 26, '2023-10-23 13:32:39', NULL),
(15, 'MSI', 'MSI BRAVO 15 B7ED-010VN', 5, 'AMD Ryzen™ 5 7535HS', '16GB (8x2) DDR5 4800MHz (2 slots)', 'AMD Radeon™ RX 6550M 4GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 4 SSD', 'Windows 10 Home', 17699000, 167, '2023-10-23 13:35:11', NULL);

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng',
  `full_name` varchar(255) NOT NULL COMMENT 'Họ tên người dùng',
  `birth_year` int(11) NOT NULL COMMENT 'Năm sinh',
  `gender` enum('male','female') NOT NULL COMMENT 'Giới tính',
  `email` varchar(255) NOT NULL COMMENT 'Địa chỉ email',
  `hashed_password` varchar(64) NOT NULL COMMENT 'Mật khẩu sau khi hash bằng SHA256',
  `avatar_path` varchar(255) DEFAULT NULL COMMENT 'Đường dẫn lưu ảnh đại diện',
  `address` longtext DEFAULT NULL COMMENT 'Địa chỉ nhận hàng, lưu dạng json',
  `status` enum('activate','deactivate') NOT NULL COMMENT 'Nếu set deactivate là bị ban',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `user` (`id`, `full_name`, `birth_year`, `gender`, `email`, `hashed_password`, `avatar_path`, `address`, `status`, `create_at`, `update_at`) VALUES
(1, 'Nguyễn Thị Lan', 1990, 'female', 'lan.nguyen90@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL, NULL, 'activate', '2023-10-26 14:13:27', NULL),
(2, 'Trần Văn Nam', 1985, 'male', 'nam.tran85@yahoo.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL, NULL, 'activate', '2023-10-31 07:17:50', NULL),
(3, 'Phạm Đức Anh', 2004, 'male', 'anh.pham04@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL, NULL, 'activate', '2023-10-26 17:28:26', NULL),
(4, '陈芳华', 2003, 'female', 'chenfanghua2003@126.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL, NULL, 'activate', '2023-10-25 11:09:26', NULL),
(5, '王小红', 2000, 'female', 'wangxiaohong2000@163.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL, NULL, 'activate', '2023-10-26 11:08:47', NULL),
(6, 'Camille Lefebvre', 1995, 'female', 'camille.lefebvre95@outlook.fr', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', NULL, NULL, 'activate', '2023-10-28 14:10:16', NULL);


ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `order_data`
  ADD CONSTRAINT `order_data_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  ADD CONSTRAINT `order_data_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
