-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 04, 2025 at 10:19 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shop_quan_ao`
--

-- --------------------------------------------------------

--
-- Table structure for table `anh_sanpham`
--

CREATE TABLE `anh_sanpham` (
  `id` int(11) NOT NULL,
  `sanpham_id` int(11) NOT NULL,
  `mausac_id` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `tenanh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `anh_sanpham`
--

INSERT INTO `anh_sanpham` (`id`, `sanpham_id`, `mausac_id`, `url`, `tenanh`) VALUES
(7, 7, '#FFFFFF', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653764/shop_quan_ao/ppjmeqe9qafd4bzjtmbg.jpg', 'shop_quan_ao/ppjmeqe9qafd4bzjtmbg'),
(8, 7, '#000000', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653763/shop_quan_ao/zbx33d1f0ez7jkxe0w1e.jpg', 'shop_quan_ao/zbx33d1f0ez7jkxe0w1e'),
(9, 8, '#F5F5DC', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653762/shop_quan_ao/wxgisjtlwaidjhjolb9t.jpg', 'shop_quan_ao/wxgisjtlwaidjhjolb9t'),
(10, 8, '#808080', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653762/shop_quan_ao/sw3u65nmf9kyaer1u6ip.jpg', 'shop_quan_ao/sw3u65nmf9kyaer1u6ip'),
(11, 7, '#FFFFFF', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653763/shop_quan_ao/zzjr8jtrliosonjzalzd.jpg', 'shop_quan_ao/zzjr8jtrliosonjzalzd'),
(12, 7, '#000000', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653763/shop_quan_ao/dprrtpvgrwdqruxeu7y1.jpg', 'shop_quan_ao/dprrtpvgrwdqruxeu7y1'),
(13, 8, '#F5F5DC', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653762/shop_quan_ao/p0setus4qqdwzbxrea3v.jpg', 'shop_quan_ao/p0setus4qqdwzbxrea3v'),
(14, 8, '#808080', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653763/shop_quan_ao/yzcv2numpx1gkwlg6t2s.jpg', 'shop_quan_ao/yzcv2numpx1gkwlg6t2s'),
(15, 9, '#FFFFFF', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653761/shop_quan_ao/lx1vmie33lp5zeqv3uyc.jpg', 'shop_quan_ao/lx1vmie33lp5zeqv3uyc'),
(16, 9, '#FFFFFF', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653763/shop_quan_ao/w1qwdhkbxad5lz7vz95z.jpg', 'shop_quan_ao/w1qwdhkbxad5lz7vz95z'),
(17, 9, '#FFFFFF', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653762/shop_quan_ao/ndcdmacpmg8du6ehe8i9.jpg', 'shop_quan_ao/ndcdmacpmg8du6ehe8i9'),
(18, 9, '#808080', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653761/shop_quan_ao/s34ncsanyrb6ok9s6zdc.jpg', 'shop_quan_ao/s34ncsanyrb6ok9s6zdc'),
(19, 9, '#808080', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653762/shop_quan_ao/mvoitjjtl9pqzxdnfm4b.jpg', 'shop_quan_ao/mvoitjjtl9pqzxdnfm4b'),
(20, 9, '#808080', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653762/shop_quan_ao/i5jjlsz3sn6zq058jwyv.jpg', 'shop_quan_ao/i5jjlsz3sn6zq058jwyv'),
(21, 11, '#000000', 'http://res.cloudinary.com/dtgopjlto/image/upload/v1743743674/shop_quan_ao/cv0ealpstpwanrdzilhu.jpg', 'shop_quan_ao/cv0ealpstpwanrdzilhu'),
(22, 11, '#000000', 'http://res.cloudinary.com/dtgopjlto/image/upload/v1743743676/shop_quan_ao/oxm4gphwfopvnm0rxxew.jpg', 'shop_quan_ao/oxm4gphwfopvnm0rxxew'),
(23, 11, '#B0E0E6', 'http://res.cloudinary.com/dtgopjlto/image/upload/v1743761708/shop_quan_ao/erumhtnxumhxznl8vmzg.jpg', 'shop_quan_ao/erumhtnxumhxznl8vmzg'),
(25, 11, '#B0E0E6', 'http://res.cloudinary.com/dtgopjlto/image/upload/v1743776080/shop_quan_ao/kggcimykvfag5d7so2on.jpg', 'shop_quan_ao/kggcimykvfag5d7so2on');

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `machitietdonhang` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `loaisanpham` varchar(255) DEFAULT NULL,
  `soluong` int(11) NOT NULL,
  `giatien` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(255) NOT NULL,
  `anhsanpham` varchar(255) NOT NULL,
  `ngaythem` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`machitietdonhang`, `madonhang`, `loaisanpham`, `soluong`, `giatien`, `masanpham`, `tensanpham`, `anhsanpham`, `ngaythem`) VALUES
(34, 24, 'Sweatpant', 2, 480000, 16, 'ARTIE Casual Regular Pant - Xám - XL', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653761/shop_quan_ao/s34ncsanyrb6ok9s6zdc.jpg', '2025-04-03'),
(35, 24, 'T-shirt', 2, 460000, 10, 'FGSS Curved Stitch Jerey Tee - Đen - XL', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653763/shop_quan_ao/zbx33d1f0ez7jkxe0w1e.jpg', '2025-04-03'),
(36, 24, 'Sweater', 2, 480000, 4, 'ARTIE basic sweater - Xám - XL', 'https://res.cloudinary.com/dtgopjlto/image/upload/v1743653762/shop_quan_ao/sw3u65nmf9kyaer1u6ip.jpg', '2025-04-03');

--
-- Triggers `chitietdonhang`
--
DELIMITER $$
CREATE TRIGGER `chitietdonhang_after_insert` AFTER INSERT ON `chitietdonhang` FOR EACH ROW BEGIN
    -- Tăng cột luotthemgiohang trong bảng thongke
    UPDATE thongke
    SET luotthemgiohang = luotthemgiohang + 1,
        luottruycap = luottruycap + 1,
        luotxemsanpham = luotxemsanpham + 1
    WHERE mathongke = CONCAT(LPAD(MONTH(CURRENT_DATE), 2, '0'), '/', YEAR(CURRENT_DATE));
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `madonhang` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `tongtien` int(11) DEFAULT NULL,
  `diachi` varchar(255) DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT 'Không có ghi chú',
  `tinhtrang` varchar(255) DEFAULT NULL,
  `thanhtoan` varchar(255) DEFAULT NULL,
  `ngaythem` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`madonhang`, `username`, `tongtien`, `diachi`, `ghichu`, `tinhtrang`, `thanhtoan`, `ngaythem`) VALUES
(24, 'minhquan@email.com', 2840000, 'Hà Nội, Đống Đa, Kim Liên, Số 9', 'Không có', 'Đã nhận hàng', 'Đã thanh toán', '2025-04-03');

--
-- Triggers `donhang`
--
DELIMITER $$
CREATE TRIGGER `donhang_after_giao` AFTER UPDATE ON `donhang` FOR EACH ROW BEGIN
    -- Kiểm tra nếu tình trạng thay đổi từ 'Đã xác nhận' thành 'Đang vận chuyển'
    IF OLD.tinhtrang = 'Đã xác nhận' AND NEW.tinhtrang = 'Đang vận chuyển' THEN
        -- Tăng giá trị của cột donhangdanggiao trong bảng thongke
        UPDATE thongke
        SET donhangdanggiao = donhangdanggiao + 1,
        donhangdaxacnhan = donhangdaxacnhan - 1
        WHERE mathongke = CONCAT(LPAD(MONTH(CURRENT_DATE), 2, '0'), '/', YEAR(CURRENT_DATE));
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `donhang_after_huy` AFTER UPDATE ON `donhang` FOR EACH ROW BEGIN
    -- Kiểm tra nếu tình trạng mới là 'Đã hủy'
    IF NEW.tinhtrang = 'Đã hủy' THEN
        -- Tăng giá trị của cột donhangdahuy trong bảng thongke
        UPDATE thongke
        SET donhangdahuy = donhangdahuy + 1, donhang = donhang - 1
        WHERE mathongke = CONCAT(LPAD(MONTH(CURRENT_DATE), 2, '0'), '/', YEAR(CURRENT_DATE));

        -- Giảm giá trị của các cột tương ứng dựa vào tình trạng cũ
        IF OLD.tinhtrang = 'Chờ xác nhận' THEN
            UPDATE thongke
            SET donhangchoxacnhan = donhangchoxacnhan - 1
            WHERE mathongke = CONCAT(LPAD(MONTH(CURRENT_DATE), 2, '0'), '/', YEAR(CURRENT_DATE));
        ELSEIF OLD.tinhtrang = 'Đang vận chuyển' THEN
            UPDATE thongke
            SET donhangdanggiao = donhangdanggiao - 1
            WHERE mathongke = CONCAT(LPAD(MONTH(CURRENT_DATE), 2, '0'), '/', YEAR(CURRENT_DATE));
        END IF;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `donhang_after_insert` AFTER INSERT ON `donhang` FOR EACH ROW BEGIN
    -- Tăng giá trị của cột donhang trong bảng thongke
    UPDATE thongke
    SET donhang = donhang + 1, luotdathang = luotdathang + 1
    WHERE mathongke = CONCAT(LPAD(MONTH(CURRENT_DATE), 2, '0'), '/', YEAR(CURRENT_DATE));

    -- Kiểm tra nếu tinhtrang là 'Chờ xác nhận'
    IF NEW.tinhtrang = 'Chờ xác nhận' THEN
        -- Tăng cột donhangchoxacnhan trong bảng thongke
        UPDATE thongke
        SET donhangchoxacnhan = donhangchoxacnhan + 1
        WHERE mathongke = CONCAT(LPAD(MONTH(CURRENT_DATE), 2, '0'), '/', YEAR(CURRENT_DATE));
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `donhang_after_nhan` AFTER UPDATE ON `donhang` FOR EACH ROW BEGIN
    -- Kiểm tra nếu tình trạng thay đổi từ 'Đã xác nhận' thành 'Đang vận chuyển'
    IF OLD.tinhtrang = 'Đang vận chuyển' AND NEW.tinhtrang = 'Đã nhận hàng' THEN
        -- Tăng giá trị của cột donhangdanggiao trong bảng thongke
        UPDATE thongke
        SET donhangdanggiao = donhangdanggiao - 1,
        donhangdagiao = donhangdagiao + 1
        WHERE mathongke = CONCAT(LPAD(MONTH(CURRENT_DATE), 2, '0'), '/', YEAR(CURRENT_DATE));
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `donhang_after_xacnhan` AFTER UPDATE ON `donhang` FOR EACH ROW BEGIN
    -- Kiểm tra nếu tình trạng thay đổi từ 'Chờ xác nhận' thành 'Đã xác nhận'
    IF OLD.tinhtrang = 'Chờ xác nhận' AND NEW.tinhtrang = 'Đã xác nhận' THEN
        -- Giảm giá trị của cột donhangchoxacnhan trong bảng thongke
        UPDATE thongke
        SET donhangchoxacnhan = donhangchoxacnhan - 1,
        donhangdaxacnhan = donhangdaxacnhan + 1
        WHERE mathongke = CONCAT(LPAD(MONTH(CURRENT_DATE), 2, '0'), '/', YEAR(CURRENT_DATE));
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `kichthuoc`
--

CREATE TABLE `kichthuoc` (
  `kichthuoc_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kichthuoc`
--

INSERT INTO `kichthuoc` (`kichthuoc_id`) VALUES
('L'),
('M'),
('S'),
('XL'),
('XS'),
('XXL'),
('XXS'),
('XXXL');

-- --------------------------------------------------------

--
-- Table structure for table `lichsuhethong`
--

CREATE TABLE `lichsuhethong` (
  `malichsu` int(11) NOT NULL,
  `hanhdong` varchar(255) DEFAULT NULL,
  `thongtin` text DEFAULT NULL,
  `thoigian` varchar(255) DEFAULT NULL,
  `nguoithuchien` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lichsukho`
--

CREATE TABLE `lichsukho` (
  `malichsu` int(11) NOT NULL,
  `hanhdong` varchar(255) DEFAULT NULL,
  `thongtin` text DEFAULT NULL,
  `thoigian` varchar(255) DEFAULT NULL,
  `nguoithuchien` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `loai`
--

CREATE TABLE `loai` (
  `loai_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loai`
--

INSERT INTO `loai` (`loai_id`) VALUES
('Hoodie'),
('Jacket'),
('Long sleeves'),
('Polo'),
('Quần dài'),
('Quần short'),
('Sơ mi'),
('Sweater'),
('Sweatpant'),
('T-shirt'),
('Tank top');

-- --------------------------------------------------------

--
-- Table structure for table `mausac`
--

CREATE TABLE `mausac` (
  `mausac_id` varchar(255) NOT NULL,
  `ten` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mausac`
--

INSERT INTO `mausac` (`mausac_id`, `ten`) VALUES
('#F5F5DC', 'Be'),
('#FFA500', 'Cam'),
('#FFC0CB', 'Hồng'),
('#A52A2A', 'Nâu'),
('#D2691E', 'Nâu đất'),
('#800080', 'Tím'),
('#FFFFFF', 'Trắng'),
('#FFFF00', 'Vàng'),
('#FFD700', 'Vàng kim'),
('#808080', 'Xám'),
('#B0E0E6', 'Xanh bột'),
('#0000FF', 'Xanh dương'),
('#00008B', 'Xanh dương đậm'),
('#00FF00', 'Xanh lá'),
('#008000', 'Xanh lá đậm'),
('#00FFFF', 'Xanh lơ'),
('#4682B4', 'Xanh thép'),
('#000000', 'Đen'),
('#FF0000', 'Đỏ'),
('#FF6347', 'Đỏ cà chua'),
('#8B0000', 'Đỏ đậm');

-- --------------------------------------------------------

--
-- Table structure for table `otp`
--

CREATE TABLE `otp` (
  `id` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `payload` varchar(255) DEFAULT NULL,
  `expiry_date` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `loai` varchar(255) NOT NULL DEFAULT 'T-shirt',
  `thuonghieu` varchar(255) NOT NULL,
  `thongtin` text NOT NULL,
  `kichhoat` int(11) DEFAULT 1,
  `ngaythem` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`id`, `ten`, `loai`, `thuonghieu`, `thongtin`, `kichhoat`, `ngaythem`) VALUES
(7, 'FGSS Curved Stitch Jerey Tee', 'T-shirt', 'FGSS', 'Thành phần: 60% Cotton / 40% Polyester\nÁo form relaxed fit.\nÁo jersey form rộng, với hiệu ứng đường vắt sổ ngược ra ngoài độc đáo.', 1, '2025-04-04'),
(8, 'ARTIE basic sweater', 'Sweater', 'ARTIE', 'Chất liệu: Nỉ bông 350gsm cao cấp.\r\nMột item không thể thiếu trong tủ đồ mỗi khi mùa thu đông về.\r\nChất liệu được ARTIE cải tiến dày dặn hơn ,mặt ngoài trải kỹ không xù, mặt trong lót bông dạng vón cục ấm áp,đặc biệt không bám lông.\r\nSản phẩm có thể phối riêng lẻ , hoặc đi theo set.', 1, '2025-04-03'),
(9, 'ARTIE Casual Regular Pant', 'Sweatpant', 'ARTIE', 'Quần basic sweatpant ống rộng với 10 phối màu giúp bạn thỏa sức phối đồ theo ý muốn\r\nChất liệu: Nỉ bông 350gsm.', 1, '2025-04-03'),
(11, 'Ripped Skinny', 'Quần dài', 'Ripped', 'Quần skinny jean chất lượng tốt, có độ co dãn nhẹ\nSize: 28 / 30 / 32 / 34\nQuần rất phù hợp với anh em nào cao gầy nhé\nQuý khách vui lòng bấm chọn size cụ thể để kiểm tra tình trạng tồn kho của sản phẩm.', 1, '2025-04-04');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham_bienthe`
--

CREATE TABLE `sanpham_bienthe` (
  `id` int(11) NOT NULL,
  `sanpham_id` int(11) NOT NULL,
  `kichthuoc_id` varchar(255) NOT NULL,
  `mausac_id` varchar(255) NOT NULL,
  `soluong` int(11) DEFAULT 0,
  `soluongdatmua` int(11) DEFAULT 0,
  `giatien` int(11) NOT NULL,
  `kichhoat` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham_bienthe`
--

INSERT INTO `sanpham_bienthe` (`id`, `sanpham_id`, `kichthuoc_id`, `mausac_id`, `soluong`, `soluongdatmua`, `giatien`, `kichhoat`) VALUES
(1, 8, 'XXL', '#F5F5DC', 50, 0, 480000, 1),
(2, 8, 'XXL', '#808080', 50, 0, 480000, 1),
(3, 8, 'XL', '#F5F5DC', 50, 0, 480000, 1),
(4, 8, 'XL', '#808080', 48, 0, 480000, 1),
(5, 8, 'L', '#F5F5DC', 50, 0, 480000, 1),
(6, 8, 'L', '#808080', 50, 0, 480000, 1),
(7, 7, 'XXL', '#FFFFFF', 50, 0, 460000, 1),
(8, 7, 'XXL', '#000000', 50, 0, 460000, 1),
(9, 7, 'XL', '#FFFFFF', 50, 0, 460000, 1),
(10, 7, 'XL', '#000000', 48, 0, 460000, 1),
(11, 7, 'L', '#FFFFFF', 50, 0, 460000, 1),
(12, 7, 'L', '#000000', 50, 0, 460000, 1),
(13, 9, 'XXL', '#FFFFFF', 50, 0, 480000, 1),
(14, 9, 'XXL', '#808080', 50, 0, 480000, 1),
(15, 9, 'XL', '#FFFFFF', 50, 0, 480000, 1),
(16, 9, 'XL', '#808080', 48, 0, 480000, 1),
(17, 9, 'L', '#FFFFFF', 50, 0, 480000, 1),
(18, 9, 'L', '#808080', 50, 0, 480000, 1),
(19, 11, 'L', '#000000', 9, 0, 520000, 1),
(24, 11, 'XL', '#B0E0E6', 20, 0, 210000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sodienthoai` varchar(255) DEFAULT NULL,
  `diachi` varchar(255) DEFAULT 'Chưa có',
  `hoten` varchar(255) DEFAULT 'Chưa có',
  `loai_tai_khoan` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT 0,
  `ngaythem` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`username`, `password`, `sodienthoai`, `diachi`, `hoten`, `loai_tai_khoan`, `enabled`, `ngaythem`) VALUES
('lam@email.com', '{bcrypt}$2a$10$5aOPxSO8Q2xp8FoT8aByO.BOpIiOm54o9QzC8AEsouP2zRlJsS1sq', NULL, 'Hà Nội, Đống Đa, Kim Liên, Số 9', 'Nguyen Trong Lam', 'ROLE_KHACHHANG', 1, '2024-11-12'),
('minhquan@email.com', '{bcrypt}$2a$10$5aOPxSO8Q2xp8FoT8aByO.BOpIiOm54o9QzC8AEsouP2zRlJsS1sq', '0813385869', 'Hà Nội, Đống Đa, Kim Liên, Số 9', 'Vương Minh Quân', 'ROLE_QUANLY', 1, '2024-06-28'),
('quangdung@email.com', '{bcrypt}$2a$10$f9qTmzYqO0v.2UopPOHcSOIQFX7vvik6TBenz/rDcUJzajt8.6nY6', '08133859510', 'Hà Nội, Đống Đa, Kim Liên, Số 9', 'Vũ Quang Dũng', 'ROLE_KHACHHANG', 1, '2024-06-28'),
('test1@email.com', '{bcrypt}$2a$10$lf52t/fkdNkYLUszGluA5eYa8Kl/VNAQy78qV95N6Sy4EJ4h98e.i', '0813385856', 'Hà Nội, Đống Đa, Kim Liên, Số 9', 'Nguyen Van Test', 'ROLE_KHACHHANG', 1, '2024-10-15'),
('test2@email.com', '{bcrypt}$2a$10$GTCKzVpgn4yLw8MZp5bZrORlO.89QoITAh5COpqq7CPZ3Owq7GJx2', '0813385951', 'Hà Nội, Đống Đa, Kim Liên, Số 9', 'Nguyen Thi Test', 'ROLE_KHACHHANG', 1, '2024-10-16'),
('tuanminh@email.com', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', '0813385899', 'Hà Nội, Đống Đa, Kim Liên, Số 9', 'Nguyễn Tuấn Minh', 'ROLE_KHACHHANG', 1, '2024-06-28'),
('vanbo@email.com', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', '0813381234', 'Hà Nội, Đống Đa, Kim Liên, Số 9', 'Nguyễn Văn Bộ', 'ROLE_KHACHHANG', 1, '2024-06-28'),
('vanthi@email.com', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', '0813389999', 'Hà Nội, Đống Đa, Kim Liên, Số 9', 'Đinh Văn Thi', 'ROLE_KHACHHANG', 1, '2024-06-28'),
('vuongdepzai1262003@gmail.com', '{bcrypt}$2a$10$iU/OZvHLNYexkaE7N9fx6eY27a5S0dY/dsUzXkjGYxxAB6tABe7Ry', NULL, 'Hà Nội, Đống Đa, Kim Liên, Số 9', 'CodeNoob223', 'ROLE_KHACHHANG', 1, '2024-07-26'),
('vuongminhquan1262003@gmail.com', '{bcrypt}$2a$10$DKBrSGWbdMX.G9qWa2GZ5OioqA9.WPXHhq/mdT9hbkYpDaT4BrQ8G', '0813385857', 'Hà Nội, Đống Đa, Kim Liên, Số 9', 'Quan Minh', 'ROLE_KHACHHANG', 1, '2024-07-25');

--
-- Triggers `taikhoan`
--
DELIMITER $$
CREATE TRIGGER `delete_taikhoan_role_khachhang` AFTER DELETE ON `taikhoan` FOR EACH ROW BEGIN
    IF OLD.loai_tai_khoan = 'ROLE_KHACHHANG' THEN
        UPDATE thongke
        SET khachhang = khachhang - 1
        WHERE mathongke = CONCAT(LPAD(MONTH(CURRENT_DATE), 2, '0'), '/', YEAR(CURRENT_DATE)); -- Đảm bảo chỉ cập nhật vào dòng mathongke = 1, nếu có nhiều dòng thống kê
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `taikhoan_after_insert_khachhang` AFTER INSERT ON `taikhoan` FOR EACH ROW BEGIN
    -- Kiểm tra nếu loai_tai_khoan là 'ROLE_KHACHHANG'
    IF NEW.loai_tai_khoan = 'ROLE_KHACHHANG' THEN
        -- Tăng giá trị của các cột khachhang và luotdangkymoi trong bảng thongke
        UPDATE thongke
        SET khachhang = khachhang + 1,
            luotdangkymoi = luotdangkymoi + 1
        WHERE mathongke = CONCAT(LPAD(MONTH(CURRENT_DATE), 2, '0'), '/', YEAR(CURRENT_DATE));
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `thongke`
--

CREATE TABLE `thongke` (
  `mathongke` varchar(255) NOT NULL,
  `sanpham` bigint(20) DEFAULT NULL,
  `donhang` bigint(20) DEFAULT NULL,
  `donhangdahuy` bigint(20) DEFAULT NULL,
  `donhangdagiao` bigint(20) DEFAULT NULL,
  `khachhang` bigint(20) DEFAULT NULL,
  `luottruycap` bigint(20) DEFAULT NULL,
  `tilechuyendoi` float NOT NULL,
  `doanhthu` decimal(38,2) DEFAULT NULL,
  `von` decimal(38,2) DEFAULT NULL,
  `donhangchoxacnhan` bigint(20) DEFAULT NULL,
  `donhangdaxacnhan` bigint(20) DEFAULT NULL,
  `donhangdanggiao` bigint(20) DEFAULT NULL,
  `luotdangkymoi` bigint(20) DEFAULT NULL,
  `chiphi` decimal(38,2) DEFAULT NULL,
  `luotxemsanpham` bigint(20) DEFAULT NULL,
  `luotthemgiohang` bigint(20) DEFAULT NULL,
  `luotdathang` bigint(20) DEFAULT NULL,
  `luotthanhtoan` bigint(20) DEFAULT NULL,
  `luothoanthanhdon` bigint(20) DEFAULT NULL,
  `ngaythem` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thongke`
--

INSERT INTO `thongke` (`mathongke`, `sanpham`, `donhang`, `donhangdahuy`, `donhangdagiao`, `khachhang`, `luottruycap`, `tilechuyendoi`, `doanhthu`, `von`, `donhangchoxacnhan`, `donhangdaxacnhan`, `donhangdanggiao`, `luotdangkymoi`, `chiphi`, `luotxemsanpham`, `luotthemgiohang`, `luotdathang`, `luotthanhtoan`, `luothoanthanhdon`, `ngaythem`) VALUES
('04/2025', 18, 1, 0, 1, 9, 165, 0, 2840000.00, 0.00, -1, 0, 0, 9, 0.00, 162, 3, 1, 0, 0, '2025-04-03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anh_sanpham`
--
ALTER TABLE `anh_sanpham`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mabutky` (`sanpham_id`),
  ADD KEY `fk_anh_mausac` (`mausac_id`);

--
-- Indexes for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`machitietdonhang`),
  ADD KEY `madonhang` (`madonhang`),
  ADD KEY `fk_chitietdonhang_sanpham` (`masanpham`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`madonhang`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `kichthuoc`
--
ALTER TABLE `kichthuoc`
  ADD PRIMARY KEY (`kichthuoc_id`);

--
-- Indexes for table `lichsuhethong`
--
ALTER TABLE `lichsuhethong`
  ADD PRIMARY KEY (`malichsu`);

--
-- Indexes for table `lichsukho`
--
ALTER TABLE `lichsukho`
  ADD PRIMARY KEY (`malichsu`);

--
-- Indexes for table `loai`
--
ALTER TABLE `loai`
  ADD PRIMARY KEY (`loai_id`);

--
-- Indexes for table `mausac`
--
ALTER TABLE `mausac`
  ADD PRIMARY KEY (`mausac_id`),
  ADD UNIQUE KEY `ten` (`ten`);

--
-- Indexes for table `otp`
--
ALTER TABLE `otp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_loai` (`loai`);

--
-- Indexes for table `sanpham_bienthe`
--
ALTER TABLE `sanpham_bienthe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_sanpham` (`sanpham_id`),
  ADD KEY `fk_mausac` (`mausac_id`),
  ADD KEY `fk_kichthuoc` (`kichthuoc_id`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `sodienthoai_unique` (`sodienthoai`);

--
-- Indexes for table `thongke`
--
ALTER TABLE `thongke`
  ADD PRIMARY KEY (`mathongke`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anh_sanpham`
--
ALTER TABLE `anh_sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `machitietdonhang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
  MODIFY `madonhang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `lichsuhethong`
--
ALTER TABLE `lichsuhethong`
  MODIFY `malichsu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `lichsukho`
--
ALTER TABLE `lichsukho`
  MODIFY `malichsu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `otp`
--
ALTER TABLE `otp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `sanpham_bienthe`
--
ALTER TABLE `sanpham_bienthe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `anh_sanpham`
--
ALTER TABLE `anh_sanpham`
  ADD CONSTRAINT `fk_anh_mausac` FOREIGN KEY (`mausac_id`) REFERENCES `mausac` (`mausac_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_anh_sanpham` FOREIGN KEY (`sanpham_id`) REFERENCES `sanpham` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD CONSTRAINT `chitietdonhang_ibfk_1` FOREIGN KEY (`madonhang`) REFERENCES `donhang` (`madonhang`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_chitietdonhang_sanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham_bienthe` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `donhang_ibfk_1` FOREIGN KEY (`username`) REFERENCES `taikhoan` (`username`) ON DELETE CASCADE;

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `fk_loai` FOREIGN KEY (`loai`) REFERENCES `loai` (`loai_id`) ON DELETE CASCADE;

--
-- Constraints for table `sanpham_bienthe`
--
ALTER TABLE `sanpham_bienthe`
  ADD CONSTRAINT `fk_kichthuoc` FOREIGN KEY (`kichthuoc_id`) REFERENCES `kichthuoc` (`kichthuoc_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_mausac` FOREIGN KEY (`mausac_id`) REFERENCES `mausac` (`mausac_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_sanpham` FOREIGN KEY (`sanpham_id`) REFERENCES `sanpham` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
