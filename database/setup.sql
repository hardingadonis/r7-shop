-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2023 at 12:45 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `r7`
--
CREATE DATABASE IF NOT EXISTS `r7` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `r7`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL COMMENT 'ID dùng để quản lý, tự động tăng',
  `username` varchar(30) NOT NULL COMMENT 'Admin dùng username để đăng nhập',
  `hashed_password` varchar(64) NOT NULL COMMENT 'Mật khẩu sau khi hash bằng SHA256',
  `role` enum('super_admin','admin') NOT NULL COMMENT 'Phân quyền, super_admin có thể tạo ra admin',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `delete_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `hashed_password`, `role`, `create_at`, `update_at`, `delete_at`) VALUES
(1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'super_admin', '2023-10-23 10:47:51', NULL, NULL),
(2, 'adonis', '3fd725a89717d2175289be8011e2b983ab6428bc0064f788f1a9d6eb014c2253', 'admin', '2023-10-23 10:49:18', NULL, NULL),
(3, 'sinoda', '1c55e32786f8fd46880d95c4491f3b0ddc265ca349bd2c5ceb4e57e24b3d887b', 'admin', '2023-10-24 10:50:13', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `user_id` int(11) NOT NULL COMMENT 'Liên kết với id bảng user',
  `product_id` int(11) NOT NULL COMMENT 'Liên kết với id bảng product',
  `amount` int(11) NOT NULL DEFAULT 1 COMMENT 'Số lượng sẩn phẩm được chọn'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL COMMENT 'ID dùng để quản lý, tự động tăng',
  `name` varchar(30) NOT NULL COMMENT 'Tên loại sản phẩm',
  `slug` varchar(30) NOT NULL COMMENT 'Đường dẫn trên web',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `delete_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `slug`, `create_at`, `update_at`, `delete_at`) VALUES
(1, 'Modern 14 series', 'modern-14', '2023-10-23 12:11:06', NULL, NULL),
(2, 'Modern 15 series', 'modern-15', '2023-10-24 15:19:37', NULL, NULL),
(3, 'GF series', 'gf-series', '2023-10-24 11:14:38', NULL, NULL),
(4, 'GS series', 'gs-series', '2023-10-23 11:14:38', NULL, NULL),
(5, 'Bravo series', 'bravo-series', '2023-10-25 11:16:27', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` int(11) NOT NULL COMMENT 'ID dùng để quản lý, tự động tăng',
  `user_id` int(11) NOT NULL COMMENT 'Liên kết với id bảng user',
  `total_price` bigint(20) NOT NULL COMMENT 'Được tính dựa trên bảng order_data',
  `payment` enum('cod','vnpay') NOT NULL COMMENT 'Lựa chọn phương thức thanh toán',
  `status` enum('processing','shipping','done','canceled') NOT NULL COMMENT 'Tình trạng đơn hàng',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_data`
--

CREATE TABLE `order_data` (
  `order_id` int(11) NOT NULL COMMENT 'Liên kết với id bảng order',
  `product_id` int(11) NOT NULL COMMENT 'Liên kết với id bảng product',
  `amount` int(11) NOT NULL DEFAULT 1 COMMENT 'Số lượng sẩn phẩm được chọn'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL COMMENT 'ID dùng để quản lý, tự động tăng',
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
  `images` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`images`)),
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `delete_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `brand`, `model`, `category_id`, `cpu`, `ram`, `vga`, `screen_size`, `hard_disk`, `os`, `price`, `amount`, `images`, `create_at`, `update_at`, `delete_at`) VALUES
(1, 'MSI', 'MSI MODERN 14 C11M-011VN', 1, 'Intel® Core™ i3 1115G4', '8GB DDR4 3200Mhz Onboard', 'Intel® UHD', '14\" FHD (1920 x 1080)', '512GB NVMe PCIe SSD Gen 3', 'Windows 11 Home', 9999000, 153, '[\"imgs/products/567e06befb215cad1e2fb2e9223043cc8527ff5a6d15d2211044ec0f94aae6b7.jpg\",\"imgs/products/c9092069184ba305622b2438ba087820c48bb99c1cace4c39eba115a55dc703c.jpg\",\"imgs/products/9e2a5cd1bd53d5934fd89ee80a1c63cbedeb000326f234238c3f8850939ebd1f.jpg\"]', '2023-10-23 08:28:47', NULL, NULL),
(2, 'MSI', 'MSI MODERN 14 C7M-083VN', 1, 'AMD Ryzen™ 5 7530U', '8GB DDR4 3200Mhz', 'AMD Radeon™ Graphics', '14\" FHD (1920 x 1080)', '512GB NVMe PCIe SSD Gen 3', 'Windows 11 Home', 11799000, 198, '[\"imgs/products/1a719719f35797352e714bb5858b13c7a64073c6a628b32259d46e16863c39fc.jpg\",\"imgs/products/93ae7dfbaccdcdc7c5952b4594e35caf23bf5bf513ca30348c8e7a86a3b43ad5.jpg\",\"imgs/products/7aac66be1291be34944c74b4dfa2cd8535f95e2e2488f6275706328ce0660cb4.jpg\"]', '2023-10-23 11:23:38', NULL, NULL),
(3, 'MSI', 'MSI MODERN 14 C12MO-660VN', 1, 'Intel® Core™ i5 1235U', '16GB DDR4 3200Mhz Onboard', 'Intel® Iris® Xe Graphics', '14\" FHD (1920 x 1080) IPS', '512GB NVMe PCIe Gen 3 SSD', 'Windows 11 Home', 13299000, 166, '[\"imgs/products/f3b98832ea739cebb930db24f1d4382d971ac3a8d583082ce7ba0aa0eec599c2.jpg\",\"imgs/products/782f7cd824bb7e65d85d62b2105156c09545147511d192a4b7ccf7855a2d54d7.jpg\",\"imgs/products/25826a7b0c99df9d80382640804148d8818e807c3fd27d1043754b6d3c7788f1.jpg\"]', '2023-10-23 13:00:08', NULL, NULL),
(4, 'MSI', 'MSI MODERN 15 B12MO-628VN', 2, 'Intel® Core™ i5 1235U', '16GB DDR4 3200Mhz Onboard', 'Intel® Iris® Xe Graphics', '15.6\" FHD (1920 x 1080) IPS', '512GB NVMe PCIe Gen 3 SSD', 'Windows 11 Home', 13699000, 182, '[\"imgs/products/6d80dd18ce140b45a0669565dc53a7202e6d3399777fa411f340dd0a442a25ed.jpg\",\"imgs/products/2c85b449f77a8a1180894c1d462f6d04a484016481aef10497f54ff67989672b.jpg\",\"imgs/products/64da7e3d57e984ba75aae35b06b27d51136341c43ffffaee5a500bf27b355b1a.jpg\"]', '2023-10-23 13:04:49', NULL, NULL),
(5, 'MSI', 'MSI MODERN 15 B7M-231VN', 2, 'AMD Ryzen™ 5 7530U', '16GB DDR4 3200Mhz Onboard', 'AMD Radeon™ Graphics', '15.6\" FHD (1920 x 1080) IPS', '512GB NVMe PCIe Gen 3 SSD', 'Windows 10', 13099000, 282, '[\"imgs/products/812499797150a213076e55f95cb5a627b2b09eaf259be1e0ec849334dd88648c.jpg\",\"imgs/products/f1c708ba4ba8ae08ce855851347774067ce099be44f746739fac1c354f658c92.jpg\",\"imgs/products/4124fbe74ab5e2c07a51efae247cb0a7f8582a9725f2e74fdc6f1486ce282f81.jpg\"]', '2023-10-23 13:08:25', NULL, NULL),
(6, 'MSI', 'MSI MODERN 15 B7M-238VN', 2, 'AMD Ryzen™ 7 7730U', '16GB DDR4 3200Mhz Onboard', 'AMD Radeon™ Graphics', '15.6\" FHD (1920 x 1080) IPS', '512GB NVMe PCIe Gen 3 SSD', 'Windows 11 Home', 14999000, 243, '[\"imgs/products/38123853632c99ed5af717387162621f5d9e35b97b9e2f28c4303de094c567cf.jpg\",\"imgs/products/d5cbc98ab1df7dca7d238334ce74596b0673e41ed1ede7d323fbc31e9f34d312.jpg\",\"imgs/products/9ac44677067d96f7316da67c4483b6aca71f0cfdb3a267f81d23a063b62591fb.jpg\"]', '2023-10-23 13:10:38', NULL, NULL),
(7, 'MSI', 'MSI GF63 Thin 11SC 664VN', 3, 'Intel® Core™ i5 11400H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce GTX 1650 4GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 3 SSD', 'Windows 10 Home', 14990000, 178, '[\"imgs/products/2da436a0b434b5e25423bbb401a10f97b3ce957a5b53aea64e9b5f333a4089a1.jpg\",\"imgs/products/6c19bdf36712d66b936b625bdd235084e976b37bd26637d569ef983129e159e4.jpg\",\"imgs/products/f9b189ba124167722fd32a3cdcf6412970883fe70219f6285f5fab0b84094bab.jpg\"]', '2023-10-23 13:11:55', NULL, NULL),
(8, 'MSI', 'MSI GF63 12UCX 841VN', 3, 'Intel® Core™ i5 12450H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 2050 4GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 3 SSD', 'Windows 10 Home', 16490000, 198, '[\"imgs/products/34e5fdd8b50c8419a6015d2ecd5c5fb3643c0d3657c2d9d896186c9d47335b62.jpg\",\"imgs/products/85c1b0fef08f57a68ac4fbac52df7366f38e54e7db651863f5c7dfbc113e8e14.jpg\",\"imgs/products/c44488eac69cf94e6fc6e72eb1e349c2bd79c802e3b8bf02feec2b3e0d62c009.jpg\"]', '2023-10-23 13:17:13', NULL, NULL),
(9, 'MSI', 'MSI GF63 Thin 11UD 473VN', 3, 'Intel® Core™ i5 11400H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 3050 Ti 4GB GDDR6', '15.6\" FHD (1920 x 1080)', '512GB NVMe PCIe Gen 3 SSD', 'Windows 10 Home', 17490000, 251, '[\"imgs/products/035a7e758f8519e70bfbcb23f99f9b99dc8069e47c5f28709cb0f52d2a70885f.png\",\"imgs/products/9b5d14c0e70516c20ce1260601d67608b655f1ce7c80d490a04614fe9bafe133.png\",\"imgs/products/bf63adbcd61e84cc833262778827cb44c2b780267a2f4ed2e358ef8e242454f3.png\"]', '2023-10-23 13:19:24', NULL, NULL),
(10, 'MSI', 'MSI GF63 Thin 11UC 1228VN', 3, 'Intel® Core™ i7 11800H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 3050 4GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 3 SSD', 'Windows 10 Home', 18890000, 313, '[\"imgs/products/5010eca160a0524b3d4771452f0396b73f52b2ee4112b2c4265bafae7cb8a3e7.jpg\",\"imgs/products/945e460171530db87ae07f64b446f22df17f63be3c1ad8846120f1a1788b494e.jpg\",\"imgs/products/af4a88239afd82d06a28f38b5a9637921d8d7ff3e94201362cee10b02c3d5444.jpg\"]', '2023-10-23 13:21:56', NULL, NULL),
(11, 'MSI', 'MSI GF63 12UC 887VN', 3, 'Intel® Core™ i7 12650H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 3050 4GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 4 SSD', 'Windows 10 Home', 19890000, 312, '[\"imgs/products/ac304cc23531595383f64d2f5306521a35cbcf0d34432f7bf489cd19976e6426.jpg\",\"imgs/products/6d25658cb7fb996c6d77e85e2644d6f1895dd30b029cb03dbd2ef0f95631bb73.jpg\",\"imgs/products/8736fc0a06ab85894ba1781fa9e23e5163a10cd8d5303115a8a2a3c9687083d5.jpg\"]', '2023-10-23 13:24:15', NULL, NULL),
(12, 'MSI', 'MSI GF63 Thin 12VE 460VN', 3, 'Intel® Core™ i5 12450H', '8GB (8x1) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 4050 6GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 4 SSD', 'Windows 10 Home', 20890000, 129, '[\"imgs/products/71504617f0f0d4a87bce53b35d61b46c95ae4af54eb8ab9b14dcd381f0823fe5.jpg\",\"imgs/products/370436d81c328578a99b9196119e7d6bea9451082b5dcf310d68df857e8b6937.jpg\",\"imgs/products/dcb1ffcbe2487513de96eccc4e3356665ef475e223147d287097c548931218ef.jpg\"]', '2023-10-23 13:28:20', NULL, NULL),
(13, 'MSI', 'MSI GS66 Stealth 11UG 210VN', 4, 'Intel® Core™ i7 11800H', '32GB (16x2) DDR4 3200MHz (2 slots)', 'NVIDIA GeForce RTX 3070 Max-Q 8GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 300Hz', '2TB NVMe PCIe Gen 4 SSD', 'Windows 11 Home', 38990000, 34, '[\"imgs/products/063fddfdd4eb81df622b2bd96d5417e7949bda734ad9f8b98f2a571ebb795ca2.jpg\",\"imgs/products/607bdbe2a7553daa224ef58e413540733ea6400d046aa310ecf1606639604b34.jpg\",\"imgs/products/b4a89da0a1bb53c445e4a6fd40e7c4c75807ec2c13b80cc21838de12331363c5.jpg\"]', '2023-10-23 13:30:08', NULL, NULL),
(14, 'MSI', 'MSI GS77 12UH 075VN', 4, 'Intel® Core™ i9 12900H', '32GB (16x2) DDR5 4800MHz (2 slots)', 'NVIDIA GeForce RTX 3080 Max-Q 8GB GDDR6', '17.3\" QHD (2560 x 1440) IPS 240Hz', '2TB NVMe PCIe Gen 4 SSD', 'Windows 11 Home', 64490000, 26, '[\"imgs/products/35c08b029d91f28bffbab04f75bb647f68df2b9b2c30fafef8e5a0708ab24714.jpg\",\"imgs/products/754ec09d97cd31cd34592aa3279c549b50a70ac7f4f97801703f0307a8a32dde.jpg\",\"imgs/products/f061d101ea20e1c65bc35080eb534769f73cce45d2233ba7ce19f5db6d535917.jpg\"]', '2023-10-23 13:32:39', NULL, NULL),
(15, 'MSI', 'MSI BRAVO 15 B7ED-010VN', 5, 'AMD Ryzen™ 5 7535HS', '16GB (8x2) DDR5 4800MHz (2 slots)', 'AMD Radeon™ RX 6550M 4GB GDDR6', '15.6\" FHD (1920 x 1080) IPS 144Hz', '512GB NVMe PCIe Gen 4 SSD', 'Windows 10 Home', 17699000, 167, '[\"imgs/products/d191f56d03bd609961811dcf9cdc76bb27786ef38f001bed6423293637e60e7d.jpg\",\"imgs/products/2ec6542fca76d07f90ecc0f04cccbc09ebfa12b1cd44e07dc8c6583728fb805a.jpg\",\"imgs/products/7d66910a67ad7aa1d6820c72d5b0ccbe0fd9cb28ca22cea400395f3ed400b19f.jpg\"]', '2023-10-23 13:35:11', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT 'ID dùng để quản lý, tự động tăng',
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
  `delete_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `full_name`, `birth_year`, `gender`, `email`, `hashed_password`, `avatar_path`, `address`, `status`, `create_at`, `update_at`, `delete_at`) VALUES
(1, 'Nguyễn Thị Lan', 1990, 'female', 'lan.nguyen90@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'imgs/avatars/9c9bd33a4fcaa3ad8d7738d8f1abef10f18651a13d7007cc252245ca0362a322.jpg', '[{\"province\":\"52\",\"district\":\"540\",\"ward\":\"21550\",\"specific\":\"Đại học FPT Quy Nhơn\"},{\"province\":\"52\",\"district\":\"547\",\"ward\":\"21808\",\"specific\":\"123 Trần Quang Diệu\"}]', 'activate', '2023-10-26 14:13:27', NULL, NULL),
(2, 'Trần Văn Nam', 1985, 'male', 'nam.tran85@yahoo.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'imgs/avatars/667cb3f09e9d11f6eecf502d99c826f681fdd1f09623f7aca23f340ec1d98d5e.jpg', '[{\"province\":\"52\",\"district\":\"540\",\"ward\":\"21550\",\"specific\":\"Đại học FPT Quy Nhơn\"},{\"province\":\"52\",\"district\":\"547\",\"ward\":\"21808\",\"specific\":\"123 Trần Quang Diệu\"}]', 'activate', '2023-10-31 07:17:50', NULL, NULL),
(3, 'Phạm Đức Anh', 2004, 'male', 'anh.pham04@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'imgs/avatars/8767436af5c289afa14119cf3653ea685e2b546706c971f37d2bcaca1bc6244c.jpg', '[{\"province\":\"52\",\"district\":\"540\",\"ward\":\"21550\",\"specific\":\"Đại học FPT Quy Nhơn\"},{\"province\":\"52\",\"district\":\"547\",\"ward\":\"21808\",\"specific\":\"123 Trần Quang Diệu\"}]', 'activate', '2023-10-26 17:28:26', NULL, NULL),
(4, '陈芳华', 2003, 'female', 'chenfanghua2003@126.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'imgs/avatars/0180030124919e17d3634f7d32e57f99b00285797a1ecc4b1216d880c17518c1.jpg', '[{\"province\":\"52\",\"district\":\"540\",\"ward\":\"21550\",\"specific\":\"Đại học FPT Quy Nhơn\"},{\"province\":\"52\",\"district\":\"547\",\"ward\":\"21808\",\"specific\":\"123 Trần Quang Diệu\"}]', 'activate', '2023-10-25 11:09:26', NULL, NULL),
(5, '王小红', 2000, 'female', 'wangxiaohong2000@163.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'imgs/avatars/a1c35cd81eb7e348caa743c39a9ec7286a680bebd2553c88a0833da28d9182cd.jpg', '[{\"province\":\"52\",\"district\":\"540\",\"ward\":\"21550\",\"specific\":\"Đại học FPT Quy Nhơn\"},{\"province\":\"52\",\"district\":\"547\",\"ward\":\"21808\",\"specific\":\"123 Trần Quang Diệu\"}]', 'activate', '2023-10-26 11:08:47', NULL, NULL),
(6, 'Camille Lefebvre', 1995, 'female', 'camille.lefebvre95@outlook.fr', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'imgs/avatars/e0816c3d35be0e6a39b68edb14d6116ab8fbd0135e5b05f0927816938b4905d2.jpg', '[{\"province\":\"52\",\"district\":\"540\",\"ward\":\"21550\",\"specific\":\"Đại học FPT Quy Nhơn\"},{\"province\":\"52\",\"district\":\"547\",\"ward\":\"21808\",\"specific\":\"123 Trần Quang Diệu\"}]', 'activate', '2023-10-28 14:10:16', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`user_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `order_data`
--
ALTER TABLE `order_data`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng', AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng', AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng';

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng', AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng', AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `order_data`
--
ALTER TABLE `order_data`
  ADD CONSTRAINT `order_data_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  ADD CONSTRAINT `order_data_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
