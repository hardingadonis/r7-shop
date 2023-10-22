CREATE TABLE `user` (
  `id` int UNIQUE PRIMARY KEY AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng',
  `full_name` varchar(255) NOT NULL COMMENT 'Họ tên người dùng',
  `birth_year` int NOT NULL COMMENT 'Năm sinh',
  `gender` int NOT NULL COMMENT 'Giới tính. 0 là nam, 1 là nữ',
  `email` varchar(255) UNIQUE NOT NULL COMMENT 'Địa chỉ email',
  `hashed_password` varchar(64) NOT NULL COMMENT 'Mật khẩu sau khi hash bằng SHA256',
  `avatar_path` varchar(255) COMMENT 'Đường dẫn lưu ảnh đại diện',
  `address` longtext COMMENT 'Địa chỉ nhận hàng, lưu dạng json',
  `status` ENUM ('activate', 'deactivate') NOT NULL COMMENT 'Nếu set deactivate là bị ban',
  `create_at` datetime,
  `update_at` datetime
);

CREATE TABLE `category` (
  `id` int UNIQUE PRIMARY KEY AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng',
  `name` varchar(30) NOT NULL COMMENT 'Tên loại sản phẩm',
  `slug` varchar(30) NOT NULL COMMENT 'Đường dẫn trên web',
  `create_at` datetime,
  `update_at` datetime
);

CREATE TABLE `product` (
  `id` int UNIQUE PRIMARY KEY AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng',
  `brand` varchar(30) NOT NULL DEFAULT "MSI" COMMENT 'Tên hãng, mặc định là MSI',
  `model` varchar(100) NOT NULL COMMENT 'Tên sản phẩm',
  `category_id` int NOT NULL COMMENT 'ID loại sản phẩm',
  `cpu` varchar(50) NOT NULL COMMENT 'Tên CPU',
  `ram` varchar(50) NOT NULL COMMENT 'Dung lượng RAM',
  `vga` varchar(50) NOT NULL COMMENT 'Tên VGA',
  `screen_size` varchar(50) NOT NULL COMMENT 'Kích thước màn hình',
  `hard_disk` varchar(50) NOT NULL COMMENT 'Dung lượng ổ cứng',
  `os` varchar(50) NOT NULL DEFAULT "Windows 10" COMMENT 'Hệ điều hành được cài đặt sẵn',
  `price` bigint NOT NULL COMMENT 'Giá sản phẩm',
  `amount` int NOT NULL COMMENT 'Số lượng sẩn phẩm hiện có',
  `create_at` datetime,
  `update_at` datetime
);

CREATE TABLE `cart` (
  `user_id` int COMMENT 'Liên kết với id bảng user',
  `product_id` int COMMENT 'Liên kết với id bảng product',
  `amount` int NOT NULL DEFAULT 1 COMMENT 'Số lượng sẩn phẩm được chọn',
  PRIMARY KEY (`user_id`, `product_id`)
);

CREATE TABLE `order` (
  `id` int UNIQUE PRIMARY KEY AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng',
  `user_id` int NOT NULL COMMENT 'Liên kết với id bảng user',
  `total_price` bigint NOT NULL COMMENT 'Được tính dựa trên bảng order_data',
  `payment` ENUM ('cod', 'vnpay') NOT NULL COMMENT 'Lựa chọn phương thức thanh toán',
  `status` ENUM ('processing', 'shipping', 'done', 'canceled') NOT NULL COMMENT 'Tình trạng đơn hàng',
  `create_at` datetime,
  `update_at` datetime
);

CREATE TABLE `order_data` (
  `order_id` int COMMENT 'Liên kết với id bảng order',
  `product_id` int COMMENT 'Liên kết với id bảng product',
  `amount` int NOT NULL DEFAULT 1 COMMENT 'Số lượng sẩn phẩm được chọn',
  PRIMARY KEY (`order_id`, `product_id`)
);

CREATE TABLE `admin` (
  `id` int UNIQUE PRIMARY KEY AUTO_INCREMENT COMMENT 'ID dùng để quản lý, tự động tăng',
  `username` varchar(30) NOT NULL COMMENT 'Admin dùng username để đăng nhập',
  `hashed_password` varchar(64) NOT NULL COMMENT 'Mật khẩu sau khi hash bằng SHA256',
  `role` ENUM ('super_admin', 'admin') NOT NULL COMMENT 'Phân quyền, super_admin có thể tạo ra admin',
  `create_at` datetime,
  `update_at` datetime
);

ALTER TABLE `user` ADD FOREIGN KEY (`id`) REFERENCES `cart` (`user_id`);

ALTER TABLE `cart` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `product` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

ALTER TABLE `order` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `order_data` ADD FOREIGN KEY (`order_id`) REFERENCES `order` (`id`);

ALTER TABLE `order_data` ADD FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
