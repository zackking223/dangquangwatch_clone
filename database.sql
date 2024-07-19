-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 19, 2024 at 06:11 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dongho_dangquang`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateThongKeOnInsert` (IN `productType` VARCHAR(255), IN `productPrice` DECIMAL(10,2), IN `productQuantity` INT)  BEGIN
    DECLARE currentCost INT;

    -- Lấy giá trị hiện tại của chiphi
    SELECT chiphi INTO currentCost FROM thongke WHERE mathongke = 1;

    -- Tính toán chi phí mới
    SET currentCost = currentCost + CEIL(productPrice / 10) * productQuantity;

    -- Tăng số lượng sản phẩm tương ứng và cập nhật chi phí trong bảng thongke
    IF productType = 'dongho' THEN
        UPDATE thongke
        SET dongho = dongho + 1,
            chiphi = currentCost
        WHERE mathongke = 1;
    ELSEIF productType = 'kinhmat' THEN
        UPDATE thongke
        SET kinhmat = kinhmat + 1,
            chiphi = currentCost
        WHERE mathongke = 1;
    ELSEIF productType = 'phukien' THEN
        UPDATE thongke
        SET phukien = phukien + 1,
            chiphi = currentCost
        WHERE mathongke = 1;
    ELSEIF productType = 'trangsuc' THEN
        UPDATE thongke
        SET trangsuc = trangsuc + 1,
            chiphi = currentCost
        WHERE mathongke = 1;
    ELSEIF productType = 'butky' THEN
        UPDATE thongke
        SET butky = butky + 1,
            chiphi = currentCost
        WHERE mathongke = 1;
    END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `anh_butky`
--

CREATE TABLE `anh_butky` (
  `maanh` int(11) NOT NULL,
  `mabutky` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `tenanh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `anh_butky`
--

INSERT INTO `anh_butky` (`maanh`, `mabutky`, `url`, `tenanh`) VALUES
(1, 1, 'https://dangquangwatch.vn/upload/product_small/but-ky-cao-cap-BUTPA-911-1567023679.jpg', 'BÚT KÝ PHILIPPE AUGUSTE BUTPA-911/B'),
(2, 2, 'https://dangquangwatch.vn/upload/product_small/247238172_but-ky-cao-cap12.jpg', 'BÚT KÝ PHILIPPE AUGUSTE BUTPA-911/R'),
(3, 3, 'https://www.dangquangwatch.vn/upload/product/1312194159_but-ky-cao-cap13.jpg', 'BÚT KÝ PHILIPPE AUGUSTE BUTPA-911/BL'),
(4, 4, 'https://www.dangquangwatch.vn/upload/product/463369489_but-ky-cao-cap10.jpg', 'BÚT KÝ PHILIPPE AUGUSTE BUTPA-910/BL'),
(5, 5, 'https://dangquangwatch.vn/upload/product_small/1737762568_but-ky-cao-cap14.jpg', 'BÚT KÝ PHILIPPE AUGUSTE BUTPA-923/R');

-- --------------------------------------------------------

--
-- Table structure for table `anh_dongho`
--

CREATE TABLE `anh_dongho` (
  `maanh` int(11) NOT NULL,
  `madongho` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `tenanh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `anh_dongho`
--

INSERT INTO `anh_dongho` (`maanh`, `madongho`, `url`, `tenanh`) VALUES
(1, 1, 'https://www.dangquangwatch.vn/upload/img_big/713622497_dong-ho-chinh-hang-phien-ban-gioi-han.jpg6.jpg', 'Đồng hồ Jacques Lemans JL-11-1654.2ZD Limited'),
(2, 2, 'https://www.dangquangwatch.vn/upload/product/dong-ho-chinh-hang-TB8208A-01-829446536.jpg', 'Đồng hồ Tsar Bomba TB8208A-01'),
(3, 3, 'https://www.dangquangwatch.vn/upload/img_big/1133644626_dong-ho-chihh-hang-1.jpg', 'Đồng hồ Diamond D DM64205IG-R'),
(4, 4, 'https://www.dangquangwatch.vn/upload/product/1702677619_donghothoitrang4.jpg', 'Đồng hồ Diamond D DM36285IG-W'),
(5, 5, 'https://www.dangquangwatch.vn/upload/product/dong-ho-nu-thoi-trang200-1700677024.jpg', 'Đồng hồ Diamond D DM61165W'),
(6, 6, 'https://www.dangquangwatch.vn/upload/img_big/1117849065_dong-ho-thoi-trang74.jpg', 'Đồng hồ Diamond D DM1004S'),
(7, 7, 'https://www.dangquangwatch.vn/upload/product/dong-ho-nu23-1884947005.jpg', 'Đồng hồ Diamond D DM38445IG'),
(8, 8, 'https://www.dangquangwatch.vn/upload/img_big/174620343_dong-ho-co-chinh-hang2.jpg', 'Đồng hồ Tsar Bomba TB8208CF-09 Carbon'),
(9, 9, 'https://www.dangquangwatch.vn/upload/product/Dong-ho-nam-dinh-da-1427516571.jpg', 'Đồng hồ Tsar Bomba TB8208D-06'),
(10, 10, 'https://www.dangquangwatch.vn/upload/img_big/609224999_dong-ho-chinh-hang5.jpg', 'Đồng hồ Tsar Bomba TB8208CF-08 Carbon'),
(11, 11, 'https://www.dangquangwatch.vn/upload/product/dong-ho-chinh-hang-TB8204Q-04-2124560748.jpg', 'Đồng hồ Tsar Bomba TB8204Q-04'),
(12, 12, 'https://www.dangquangwatch.vn/upload/product/1755774722_dong-ho-nu18.jpg', 'Đồng hồ Jacques Lemans JL-1-2001D'),
(13, 13, 'https://www.dangquangwatch.vn/upload/product/dong-ho-chinh-hang-phien-ban-gioi-han1-1896924675.jpg', 'Đồng hồ Jacques Lemans JL-11-1654.2ZH Limited'),
(14, 14, 'https://www.dangquangwatch.vn/upload/product/1680920657_194864047_985534601_donghonhapkhau36.jpg', 'Đồng hồ Jacques Lemans JL-1-1654.2ZB'),
(15, 15, 'https://www.dangquangwatch.vn/upload/product/1496514498_dong-ho-nu-nhap-khau30.jpg', 'Đồng hồ Jacques Lemans JL-1-1807.1E'),
(16, 16, 'https://www.dangquangwatch.vn/upload/product/235611185_dong-ho-nu-thoi-trang.jpg', 'Đồng hồ Aries Gold AG-L58010L RG-MP'),
(17, 17, 'https://www.dangquangwatch.vn/upload/product/1637158092_241571475_1083070878_donghocochinhang65.jpg', 'Đồng hồ Aries Gold AG-G9005A G-BK'),
(18, 18, 'https://www.dangquangwatch.vn/upload/product/1754702713_donghonu2-2072641046.jpg', 'Đồng hồ Aries Gold AG-L5038 G-W'),
(19, 19, 'https://www.dangquangwatch.vn/upload/product/dong-ho-chinh-hang8-772626658.jpg', 'Đồng hồ Aries Gold AG-G1009 G-RW'),
(20, 20, 'https://www.dangquangwatch.vn/upload/product/402191911_donghonhapkhau54-1780145683.jpg', 'Đồng hồ Aries Gold AG-G1013Z S-BK'),
(21, 21, 'https://www.dangquangwatch.vn/upload/product/dong-ho-co-chinh-hang-214916002.jpg', 'Đồng hồ Philippe Auguste PA5008B'),
(22, 22, 'https://www.dangquangwatch.vn/upload/product/dong-ho-ho20-1810769538.jpg', 'Đồng hồ Philippe Auguste PA2022'),
(23, 23, 'https://www.dangquangwatch.vn/upload/product/dong-ho-co-chinh-hang5-1500758213.jpg', 'Đồng hồ Philippe Auguste PA999RG'),
(24, 24, 'https://www.dangquangwatch.vn/upload/product/dong-ho-co-chinh-hang9-1294094934.jpg', 'Đồng hồ Philippe Auguste PA999BK'),
(25, 25, 'https://www.dangquangwatch.vn/upload/img_big/2082073730_DONG-HO-CHINH-HANG-27.jpg', 'Đồng hồ Philippe Auguste PA5005L'),
(26, 26, 'https://www.dangquangwatch.vn/upload/product/Dong-ho-Thuy-Sy-vang-nguyen-khoi2-1956922418.jpg', 'Đồng hồ Atlantic Swiss AT-96741.65.31 vàng nguyên khối phiên bản giới hạn'),
(27, 27, 'https://www.dangquangwatch.vn/upload/product/dong-ho-thuy-sy-vang-nguyen-khoi_1-355655564.jpg', 'Đồng hồ Atlantic Swiss AT-95743.65.21 vàng nguyên khối'),
(28, 28, 'https://www.dangquangwatch.vn/upload/product/dong-ho-thuy-sy-phien-ban-gioi-han1-949951940.jpg', 'Đồng hồ Atlantic Swiss AT-55858.41.29 Limited'),
(29, 29, 'https://www.dangquangwatch.vn/upload/img_big/1902308758_donghothuysy23.jpg', 'Đồng hồ Atlantic Swiss AT-52780.41.51 Limited'),
(30, 30, 'https://www.dangquangwatch.vn/upload/product/1119291573_donghothuysy56.jpg', 'Đồng hồ Atlantic Swiss AT-56555.45.21'),
(31, 31, 'https://dangquangwatch.vn/upload/product_small/468082655_dong-ho-nhat8.jpg', 'citizen CT-BM7'),
(32, 32, 'https://www.dangquangwatch.vn/upload/img_big/1267939770_%C4%91%E1%BB%93ng-h%E1%BB%93-%C4%91%C4%83ng-quang29.jpg', 'Citizen CT-NH8363-14A'),
(33, 33, 'https://dangquangwatch.vn/upload/product_small/dong-ho-chinh-hang1-794559389.jpg', 'Citizen'),
(34, 34, 'https://dangquangwatch.vn/upload/product_small/1664776886_1198992371_114954084_%C4%91%E1%BB%93ng-h%E1%BB%93-%C4%91%C4%83ng-quang32.jpg', 'Citizen'),
(35, 35, 'https://dangquangwatch.vn/upload/product_small/221232752_%C4%91%E1%BB%93ng-h%E1%BB%93-nh%E1%BA%ADt4.jpg', 'Citizen'),
(36, 36, 'https://dangquangwatch.vn/upload/product_small/332755139_donghonhat7.jpg', 'Citizen'),
(37, 37, 'https://dangquangwatch.vn/upload/product_small/846713905_dong-ho-chinh-hang-14.jpg', 'Citizen'),
(38, 38, 'https://www.dangquangwatch.vn/upload/product/dong-ho-thuy-sy-cao-cap-2090822342.jpg', 'Đồng hồ Epos Swiss E-3435.313.22.18.25 Limited'),
(39, 39, 'https://www.dangquangwatch.vn/upload/img_big/1383335732_dong-ho-nu-thuy-sy3.jpg', 'Đồng hồ Epos Swiss E-8000.700.22.88.32 Diamond'),
(40, 40, 'https://www.dangquangwatch.vn/upload/img_big/573057248_dong-ho-nu-thuy-sy73-227803529.jpg', 'Đồng hồ Epos Swiss E-4314.133.20.89.10 Diamond'),
(41, 41, 'https://www.dangquangwatch.vn/upload/img_big/1122699237_dong-ho-thuy-sy67.jpg', 'Đồng hồ Epos Swiss E-3439.322.24.26.25'),
(42, 42, 'https://www.dangquangwatch.vn/upload/product/1627452799_dong-ho-thuy-sy75.jpg', 'Đồng hồ Epos Swiss E-3439.322.24.26.34'),
(43, 43, 'https://www.dangquangwatch.vn/upload/img_big/1264727169_dong%20ho%20thuy%20sy%20cao%20cap%20(5).jpg', 'Đồng hồ Epos Swiss E-3439.322.24.18.25'),
(44, 44, 'https://www.dangquangwatch.vn/upload/product/1374030555_donghothuysy61.jpg', 'Đồng hồ Epos Swiss E-3391.832.22.20.25'),
(45, 45, 'https://www.dangquangwatch.vn/upload/img_big/1612812808_dong-ho-co-thuy-sy-phien-ban-gioi-han7.jpg', 'Đồng Hồ EPOS SWISS E-3500.169.24.25.25 Limited'),
(46, 46, 'https://www.dangquangwatch.vn/upload/img_big/868427465_dong-ho-thuy-sy30.jpg', '46'),
(47, 47, 'https://www.dangquangwatch.vn/upload/img_big/1952031059_dong%20ho%20thuy%20sy%20E-3439.322.20.16%20(5).jpg', 'Đẹp vãi '),
(48, 1, 'http://res.cloudinary.com/dtgopjlto/image/upload/v1719422130/mjyddhovibxm2ruduiia.png', 'mjyddhovibxm2ruduiia'),
(50, 2, 'http://res.cloudinary.com/dtgopjlto/image/upload/v1719423705/decs9cptpz9qtycvtitx.png', 'decs9cptpz9qtycvtitx'),
(51, 48, 'http://res.cloudinary.com/dtgopjlto/image/upload/v1719711500/qeudhecaodkpriszrprf.png', 'qeudhecaodkpriszrprf');

-- --------------------------------------------------------

--
-- Table structure for table `anh_kinhmat`
--

CREATE TABLE `anh_kinhmat` (
  `maanh` int(11) NOT NULL,
  `makinhmat` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `tenanh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `anh_kinhmat`
--

INSERT INTO `anh_kinhmat` (`maanh`, `makinhmat`, `url`, `tenanh`) VALUES
(1, 1, 'https://www.dangquangwatch.vn/upload/product/1405923694_kinh-mat-chinh-hang68.jpg', 'Kính mát PHILIPPE AUGUSTE PA555-008-G'),
(2, 2, 'https://www.dangquangwatch.vn/upload/product/109007553_kinh-mat-chinh-hang15-1246338084.jpg', 'Kính mát PHILIPPE AUGUSTE PA555-007-F'),
(3, 3, 'https://www.dangquangwatch.vn/upload/img_big/1755103197_k%C3%ADnh-m%C3%A1t-ch%C3%ADnh-h%C3%A3ng13.jpg', 'Kính mát PHILIPPE AUGUSTE PA555-007-B'),
(4, 4, 'https://www.dangquangwatch.vn/upload/product/1567326006_kinh-mat-chinh-hang26-688474142.jpg', 'Kính mắt PHILIPPE AUGUSTE PA555-007-D'),
(5, 5, 'https://www.dangquangwatch.vn/upload/img_big/1958772232_k%C3%ADnh-m%C3%A1t-ch%C3%ADnh-h%C3%A3ng14.jpg', 'Kính mát PHILIPPE AUGUSTE PA555-002-E');

-- --------------------------------------------------------

--
-- Table structure for table `anh_phukien`
--

CREATE TABLE `anh_phukien` (
  `maanh` int(11) NOT NULL,
  `maphukien` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `tenanh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `anh_phukien`
--

INSERT INTO `anh_phukien` (`maanh`, `maphukien`, `url`, `tenanh`) VALUES
(1, 1, 'https://www.dangquangwatch.vn/upload/product/1905887238_D%20DM%20W%2012.jpg', 'Dây da Diamond D D DM W 12'),
(2, 2, 'https://www.dangquangwatch.vn/upload/product/606156816_day_da_hong_ho_086A9320.JPG', 'Dây da đồng hồ 71C-443SFM-30/22 - Size 22 mm - Màu đen'),
(3, 3, 'https://www.dangquangwatch.vn/upload/product/Day-cao-su-dong-ho-Tsar-Bomba-xanh3-412194286.jpg', 'Dây cao su đồng hồ Tsar Bomba D TB8218F-LIGHT BLUE'),
(4, 4, 'https://www.dangquangwatch.vn/upload/product/1115029854_d%C3%A2y-jl4.jpg', 'Dây da Jacques D JL-1-1846C'),
(5, 5, 'https://www.dangquangwatch.vn/upload/product/Day-da-chinh-hang-Dang-Quang-Watch22-932689191.jpg', 'Dây da Đăng Quang D-A-273NKS.H19.NERO-19');

-- --------------------------------------------------------

--
-- Table structure for table `anh_trangsuc`
--

CREATE TABLE `anh_trangsuc` (
  `maanh` int(11) NOT NULL,
  `matrangsuc` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `tenanh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `anh_trangsuc`
--

INSERT INTO `anh_trangsuc` (`maanh`, `matrangsuc`, `url`, `tenanh`) VALUES
(1, 1, 'https://www.dangquangwatch.vn/upload/product/vong-deo-tay-Diamond-Djpg5-1510694459.jpg', 'Vòng tay cao cấp Diamond D DM-BR001IG'),
(2, 2, 'https://www.dangquangwatch.vn/upload/img_big/1528845531_vong-deo-tay-Diamond-D.jpg12.jpg', 'Vòng tay cao cấp Diamond D DM-BR001IG'),
(3, 3, 'https://www.dangquangwatch.vn/upload/product/vong-deo-tay-Diamond-Djpg6-111570942.jpg', 'Vòng tay cao cấp Diamond D DM-BR001W');

-- --------------------------------------------------------

--
-- Table structure for table `butky`
--

CREATE TABLE `butky` (
  `mabutky` int(11) NOT NULL,
  `tenbutky` varchar(255) NOT NULL,
  `giatien` int(11) NOT NULL,
  `tragop` int(100) NOT NULL DEFAULT 0,
  `thongtin` text NOT NULL,
  `soluong` int(11) NOT NULL,
  `NGAYTHEM` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `butky`
--

INSERT INTO `butky` (`mabutky`, `tenbutky`, `giatien`, `tragop`, `thongtin`, `soluong`, `NGAYTHEM`) VALUES
(1, 'BÚT KÝ PHILIPPE AUGUSTE BUTPA-911/B', 550000, 0, 'Chất liệu vỏ: Vỏ bút làm bằng ĐỒNG.\nPhần mạ: Mạ màu công nghệ cao.\nChất liệu ruột bút: Nhôm\nMực: Mực nước màu xanh và đen, chảy mực đều, nét bút rất êm. Chiếc bút ký sang trọng này là hiện thân của sự tinh tế và đẳng cấp, xứng đáng trở thành biểu tượng của phong cách và sự chuyên nghiệp. Được chế tác từ kim loại cao cấp với lớp phủ mạ vàng hoặc bạc, bút không chỉ bền bỉ mà còn tỏa sáng với vẻ đẹp lấp lánh và sang trọng. Thiết kế thanh lịch với các đường nét mềm mại và cân đối, cầm nắm thoải mái, mang lại cảm giác viết trơn tru và mượt mà.\n\nNgòi bút được làm từ hợp kim đặc biệt, cho phép mực chảy đều và ổn định, giúp chữ viết của bạn trở nên sắc nét và đẹp mắt. Nắp bút được thiết kế chắc chắn, dễ dàng đóng mở, bảo vệ ngòi bút khỏi bụi bẩn và khô mực. Mỗi chi tiết nhỏ, từ logo thương hiệu khắc tinh tế đến các đường viền trang trí, đều thể hiện sự tỉ mỉ và đẳng cấp.\n\nChiếc bút ký này không chỉ là công cụ viết mà còn là phụ kiện thời trang, biểu tượng của sự thành đạt và phong cách. Đây là món quà hoàn hảo dành cho doanh nhân, luật sư, nhà văn, hoặc bất kỳ ai trân trọng giá trị của chữ viết và sự tinh tế. Hãy để bút ký sang trọng này đồng hành cùng bạn trong mọi cuộc họp, ký kết quan trọng, khẳng định vị thế và gu thẩm mỹ đẳng cấp của bạn.', 10, '2024-06-21'),
(2, 'BÚT KÝ PHILIPPE AUGUSTE BUTPA-911/R', 550000, 0, 'Chất liệu vỏ: Vỏ bút làm bằng VÀNG.\nPhần mạ: Mạ vàng 24k.\nChất liệu ruột bút: Nhôm\nMực: Mực nước màu xanh và đen, chảy mực đều, nét bút rất êm. Chiếc bút ký sang trọng này là hiện thân của sự tinh tế và đẳng cấp, xứng đáng trở thành biểu tượng của phong cách và sự chuyên nghiệp. Được chế tác từ kim loại cao cấp với lớp phủ mạ vàng hoặc bạc, bút không chỉ bền bỉ mà còn tỏa sáng với vẻ đẹp lấp lánh và sang trọng. Thiết kế thanh lịch với các đường nét mềm mại và cân đối, cầm nắm thoải mái, mang lại cảm giác viết trơn tru và mượt mà.\n\nNgòi bút được làm từ hợp kim đặc biệt, cho phép mực chảy đều và ổn định, giúp chữ viết của bạn trở nên sắc nét và đẹp mắt. Nắp bút được thiết kế chắc chắn, dễ dàng đóng mở, bảo vệ ngòi bút khỏi bụi bẩn và khô mực. Mỗi chi tiết nhỏ, từ logo thương hiệu khắc tinh tế đến các đường viền trang trí, đều thể hiện sự tỉ mỉ và đẳng cấp.\n\nChiếc bút ký này không chỉ là công cụ viết mà còn là phụ kiện thời trang, biểu tượng của sự thành đạt và phong cách. Đây là món quà hoàn hảo dành cho doanh nhân, luật sư, nhà văn, hoặc bất kỳ ai trân trọng giá trị của chữ viết và sự tinh tế. Hãy để bút ký sang trọng này đồng hành cùng bạn trong mọi cuộc họp, ký kết quan trọng, khẳng định vị thế và gu thẩm mỹ đẳng cấp của bạn.', 11, '2024-06-21'),
(3, 'BÚT KÝ PHILIPPE AUGUSTE BUTPA-911/BL', 550000, 0, 'Chất liệu vỏ: Vỏ bút làm bằng BẠC.\nPhần mạ: Mạ màu công nghệ cao.\nChất liệu ruột bút: Nhôm\nMực: Mực nước màu xanh và đen, chảy mực đều, nét bút rất êm. Chiếc bút ký sang trọng này là hiện thân của sự tinh tế và đẳng cấp, xứng đáng trở thành biểu tượng của phong cách và sự chuyên nghiệp. Được chế tác từ kim loại cao cấp với lớp phủ mạ vàng hoặc bạc, bút không chỉ bền bỉ mà còn tỏa sáng với vẻ đẹp lấp lánh và sang trọng. Thiết kế thanh lịch với các đường nét mềm mại và cân đối, cầm nắm thoải mái, mang lại cảm giác viết trơn tru và mượt mà.\n\nNgòi bút được làm từ hợp kim đặc biệt, cho phép mực chảy đều và ổn định, giúp chữ viết của bạn trở nên sắc nét và đẹp mắt. Nắp bút được thiết kế chắc chắn, dễ dàng đóng mở, bảo vệ ngòi bút khỏi bụi bẩn và khô mực. Mỗi chi tiết nhỏ, từ logo thương hiệu khắc tinh tế đến các đường viền trang trí, đều thể hiện sự tỉ mỉ và đẳng cấp.\n\nChiếc bút ký này không chỉ là công cụ viết mà còn là phụ kiện thời trang, biểu tượng của sự thành đạt và phong cách. Đây là món quà hoàn hảo dành cho doanh nhân, luật sư, nhà văn, hoặc bất kỳ ai trân trọng giá trị của chữ viết và sự tinh tế. Hãy để bút ký sang trọng này đồng hành cùng bạn trong mọi cuộc họp, ký kết quan trọng, khẳng định vị thế và gu thẩm mỹ đẳng cấp của bạn.', 10, '2024-06-21'),
(4, 'BÚT KÝ PHILIPPE AUGUSTE BUTPA-910/BL', 550000, 0, 'Chất liệu vỏ: Vỏ bút làm bằng Titan.\nPhần mạ: Mạ màu công nghệ cao.\nChất liệu ruột bút: Nhôm\nMực: Mực nước màu xanh và đen, chảy mực đều, nét bút rất êm. Chiếc bút ký sang trọng này là hiện thân của sự tinh tế và đẳng cấp, xứng đáng trở thành biểu tượng của phong cách và sự chuyên nghiệp. Được chế tác từ kim loại cao cấp với lớp phủ mạ vàng hoặc bạc, bút không chỉ bền bỉ mà còn tỏa sáng với vẻ đẹp lấp lánh và sang trọng. Thiết kế thanh lịch với các đường nét mềm mại và cân đối, cầm nắm thoải mái, mang lại cảm giác viết trơn tru và mượt mà.\n\nNgòi bút được làm từ hợp kim đặc biệt, cho phép mực chảy đều và ổn định, giúp chữ viết của bạn trở nên sắc nét và đẹp mắt. Nắp bút được thiết kế chắc chắn, dễ dàng đóng mở, bảo vệ ngòi bút khỏi bụi bẩn và khô mực. Mỗi chi tiết nhỏ, từ logo thương hiệu khắc tinh tế đến các đường viền trang trí, đều thể hiện sự tỉ mỉ và đẳng cấp.\n\nChiếc bút ký này không chỉ là công cụ viết mà còn là phụ kiện thời trang, biểu tượng của sự thành đạt và phong cách. Đây là món quà hoàn hảo dành cho doanh nhân, luật sư, nhà văn, hoặc bất kỳ ai trân trọng giá trị của chữ viết và sự tinh tế. Hãy để bút ký sang trọng này đồng hành cùng bạn trong mọi cuộc họp, ký kết quan trọng, khẳng định vị thế và gu thẩm mỹ đẳng cấp của bạn.', 22, '2024-06-21'),
(5, 'BÚT KÝ PHILIPPE AUGUSTE BUTPA-923/R', 550000, 0, 'Chất liệu vỏ: Vỏ bút làm bằng Uranium.\nPhần mạ: Mạ màu công nghệ cao.\nChất liệu ruột bút: Nhôm\nMực: Mực nước màu xanh và đen, chảy mực đều, nét bút rất êm. Chiếc bút ký sang trọng này là hiện thân của sự tinh tế và đẳng cấp, xứng đáng trở thành biểu tượng của phong cách và sự chuyên nghiệp. Được chế tác từ kim loại cao cấp với lớp phủ mạ vàng hoặc bạc, bút không chỉ bền bỉ mà còn tỏa sáng với vẻ đẹp lấp lánh và sang trọng. Thiết kế thanh lịch với các đường nét mềm mại và cân đối, cầm nắm thoải mái, mang lại cảm giác viết trơn tru và mượt mà.\n\nNgòi bút được làm từ hợp kim đặc biệt, cho phép mực chảy đều và ổn định, giúp chữ viết của bạn trở nên sắc nét và đẹp mắt. Nắp bút được thiết kế chắc chắn, dễ dàng đóng mở, bảo vệ ngòi bút khỏi bụi bẩn và khô mực. Mỗi chi tiết nhỏ, từ logo thương hiệu khắc tinh tế đến các đường viền trang trí, đều thể hiện sự tỉ mỉ và đẳng cấp.\n\nChiếc bút ký này không chỉ là công cụ viết mà còn là phụ kiện thời trang, biểu tượng của sự thành đạt và phong cách. Đây là món quà hoàn hảo dành cho doanh nhân, luật sư, nhà văn, hoặc bất kỳ ai trân trọng giá trị của chữ viết và sự tinh tế. Hãy để bút ký sang trọng này đồng hành cùng bạn trong mọi cuộc họp, ký kết quan trọng, khẳng định vị thế và gu thẩm mỹ đẳng cấp của bạn.', 55, '2024-06-21');

--
-- Triggers `butky`
--
DELIMITER $$
CREATE TRIGGER `butky_after_insert` AFTER INSERT ON `butky` FOR EACH ROW BEGIN
    CALL UpdateThongKeOnInsert('butky', NEW.giatien, NEW.soluong);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `delete_butky` AFTER DELETE ON `butky` FOR EACH ROW BEGIN
    UPDATE thongke
    SET butky = butky - 1
    WHERE mathongke = 1;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `machitietdonhang` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `loaisanpham` enum('dongho','phukien','kinhmat','butky','trangsuc') NOT NULL,
  `soluong` int(11) NOT NULL,
  `giatien` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(255) NOT NULL,
  `anhsanpham` varchar(255) NOT NULL,
  `NGAYTHEM` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`machitietdonhang`, `madonhang`, `loaisanpham`, `soluong`, `giatien`, `masanpham`, `tensanpham`, `anhsanpham`, `NGAYTHEM`) VALUES
(1, 1, 'dongho', 1, 6600000, 1, 'Đồng hồ Jacques Lemans JL-11-1654.2ZD Limited', 'https://www.dangquangwatch.vn/upload/img_big/713622497_dong-ho-chinh-hang-phien-ban-gioi-han.jpg6.jpg', '2024-06-26'),
(2, 1, 'dongho', 1, 10500000, 2, 'Đồng hồ Tsar Bomba TB8208A-01', 'https://www.dangquangwatch.vn/upload/product/dong-ho-chinh-hang-TB8208A-01-829446536.jpg', '2024-06-26'),
(3, 2, 'dongho', 1, 5890000, 3, 'Đồng hồ Diamond D DM64205IG-R', 'https://www.dangquangwatch.vn/upload/img_big/1133644626_dong-ho-chihh-hang-1.jpg', '2024-06-26'),
(4, 2, 'dongho', 1, 4550000, 4, 'Đồng hồ Diamond D DM36285IG-W', 'https://www.dangquangwatch.vn/upload/product/1702677619_donghothoitrang4.jpg', '2024-06-26'),
(5, 4, 'dongho', 1, 4550000, 4, 'Đồng hồ Diamond D DM36285IG-W', 'https://www.dangquangwatch.vn/upload/product/1702677619_donghothoitrang4.jpg', '2024-06-28'),
(6, 4, 'dongho', 1, 3620000, 5, 'Đồng hồ Diamond D DM61165W', 'https://www.dangquangwatch.vn/upload/product/dong-ho-nu-thoi-trang200-1700677024.jpg', '2024-06-28'),
(7, 4, 'trangsuc', 2, 1200000, 1, 'Vòng tay cao cấp Diamond D DM-BR001IG', 'https://www.dangquangwatch.vn/upload/product/vong-deo-tay-Diamond-Djpg5-1510694459.jpg', '2024-06-28'),
(8, 6, 'dongho', 3, 3620000, 5, 'Đồng hồ Diamond D DM61165W', 'https://www.dangquangwatch.vn/upload/product/dong-ho-nu-thoi-trang200-1700677024.jpg', '2024-06-28'),
(9, 7, 'dongho', 3, 3620000, 5, 'Đồng hồ Diamond D DM61165W', 'https://www.dangquangwatch.vn/upload/product/dong-ho-nu-thoi-trang200-1700677024.jpg', '2024-06-28'),
(10, 7, 'kinhmat', 1, 3090000, 3, 'Kính mát PHILIPPE AUGUSTE PA555-007-B', 'https://www.dangquangwatch.vn/upload/img_big/1755103197_k%C3%ADnh-m%C3%A1t-ch%C3%ADnh-h%C3%A3ng13.jpg', '2024-06-28'),
(11, 7, 'kinhmat', 1, 3090000, 4, 'Kính mắt PHILIPPE AUGUSTE PA555-007-D', 'https://www.dangquangwatch.vn/upload/product/1567326006_kinh-mat-chinh-hang26-688474142.jpg', '2024-06-28'),
(12, 7, 'kinhmat', 1, 3090000, 5, 'Kính mát PHILIPPE AUGUSTE PA555-002-E', 'https://www.dangquangwatch.vn/upload/img_big/1958772232_k%C3%ADnh-m%C3%A1t-ch%C3%ADnh-h%C3%A3ng14.jpg', '2024-06-28');

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
    WHERE mathongke = 1;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `dongho`
--

CREATE TABLE `dongho` (
  `madongho` int(11) NOT NULL,
  `tendongho` varchar(255) NOT NULL,
  `giatien` int(11) NOT NULL,
  `soluong` int(11) NOT NULL DEFAULT 20,
  `tragop` int(100) NOT NULL DEFAULT 0,
  `duongkinh` tinyint(255) NOT NULL DEFAULT 40,
  `chongnuoc` tinyint(100) NOT NULL DEFAULT 10,
  `bomay` varchar(255) NOT NULL DEFAULT 'Cơ tự động',
  `NGAYTHEM` date NOT NULL DEFAULT current_timestamp(),
  `thongtin` text NOT NULL DEFAULT 'Đẹp lắm, mua đi.',
  `gioitinh` varchar(255) NOT NULL DEFAULT 'Nam',
  `chatlieu` varchar(255) NOT NULL DEFAULT 'Kính sapphire'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dongho`
--

INSERT INTO `dongho` (`madongho`, `tendongho`, `giatien`, `soluong`, `tragop`, `duongkinh`, `chongnuoc`, `bomay`, `NGAYTHEM`, `thongtin`, `gioitinh`, `chatlieu`) VALUES
(1, 'Đồng hồ Jacques Lemans JL-11-1654.2ZD Limited', 6600000, 10, 0, 40, 10, 'Quartz/Pin', '2024-06-26', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Krysterna crystal kính cứng'),
(2, 'Đồng hồ Tsar Bomba TB8208A-01', 10500000, 10, 0, 42, 5, 'Automatic', '2024-06-26', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nữ', 'Kính sapphire'),
(3, 'Đồng hồ Diamond D DM64205IG-R', 5890000, 29, 0, 30, 10, 'Quartz/Pin', '2024-06-21', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(4, 'Đồng hồ Diamond D DM36285IG-W', 4550000, 38, 0, 32, 3, 'Quartz', '2024-06-21', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nữ', 'Kính sapphire'),
(5, 'Đồng hồ Diamond D DM61165W', 3620000, 54, 0, 36, 3, 'Quartz/Pin', '2024-06-21', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(6, 'Đồng hồ Diamond D DM1004S', 5625000, 33, 0, 37, 5, 'Quartz/Pin', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nữ', 'Kính cứng chịu lực'),
(7, 'Đồng hồ Diamond D DM38445IG', 4940000, 55, 0, 27, 3, 'Quartz/Pin', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nữ', 'Kính sapphire'),
(8, 'Đồng hồ Tsar Bomba TB8208CF-09 Carbon', 14000000, 12, 0, 42, 5, 'Automatic', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(9, 'Đồng hồ Tsar Bomba TB8208D-06', 15750000, 16, 0, 42, 5, 'Automatic', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(10, 'Đồng hồ Tsar Bomba TB8208CF-08 Carbon', 14000000, 13, 0, 42, 5, 'Automatic', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(11, 'Đồng hồ Tsar Bomba TB8204Q-04', 7000000, 21, 0, 42, 5, 'Quartz/Pin', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(12, 'Đồng hồ Jacques Lemans JL-1-2001D', 4030000, 6, 0, 36, 5, 'Quartz (Điện tử)', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nữ', 'Hardened crystex crystal (Kính cứng )'),
(13, 'Đồng hồ Jacques Lemans JL-11-1654.2ZH Limited', 770000, 13, 0, 40, 10, 'Quartz/Pin', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nữ', 'Krysterna crystal ( kính cứng )'),
(14, 'Đồng hồ Jacques Lemans JL-1-1654.2ZB', 5280000, 19, 0, 40, 10, 'Quartz/Pin', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Krysterna crystal ( kính cứng )'),
(15, 'Đồng hồ Jacques Lemans JL-1-1807.1E', 4610000, 24, 0, 24, 5, 'Quartz (Điện tử)', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nữ', 'Hardened crystex crystal (Kính cứng )'),
(16, 'Đồng hồ Aries Gold AG-L58010L RG-MP', 9950000, 22, 0, 35, 5, 'Quartz/Pin', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nữ', 'Kính sapphire'),
(17, 'Đồng hồ Aries Gold AG-G9005A G-BK', 12670000, 7, 0, 42, 5, 'Automatic ( cơ tự động )', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(18, 'Đồng hồ Aries Gold AG-L5038 G-W', 4680000, 33, 0, 29, 5, 'Quartz/Pin', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nữ', 'Kính sapphire'),
(19, 'Đồng hồ Aries Gold AG-G1009 G-RW', 5200000, 36, 0, 36, 5, 'Quartz/Pin', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(20, 'Đồng hồ Aries Gold AG-G1013Z S-BK', 2475000, 52, 0, 40, 3, 'Quartz/Pin', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Mineral ( Kính cứng )'),
(21, 'Đồng hồ Philippe Auguste PA5008B', 11808000, 16, 0, 40, 5, 'Automatic ( cơ tự động )', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire');
INSERT INTO `dongho` (`madongho`, `tendongho`, `giatien`, `soluong`, `tragop`, `duongkinh`, `chongnuoc`, `bomay`, `NGAYTHEM`, `thongtin`, `gioitinh`, `chatlieu`) VALUES
(22, 'Đồng hồ Philippe Auguste PA2022', 11900000, 23, 0, 40, 5, 'Quartz/Pin', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Tráng Sapphire'),
(23, 'Đồng hồ Philippe Auguste PA999RG', 12800000, 3, 0, 42, 3, 'Automatic', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(24, 'Đồng hồ Philippe Auguste PA999BK', 12800000, 6, 0, 42, 3, 'Automatic', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(25, 'Đồng hồ Philippe Auguste PA5005L', 6488000, 24, 0, 39, 4, 'Quartz/Pin', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Tráng sapphire'),
(26, 'Đồng hồ Atlantic Swiss AT-96741.65.31 vàng nguyên khối phiên bản giới hạn', 209000000, 3, 0, 40, 0, 'Automatic', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(27, 'Đồng hồ Atlantic Swiss AT-95743.65.21 vàng nguyên khối', 135000000, 7, 0, 38, 3, 'Automatic', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Sapphire tinh thể'),
(28, 'Đồng hồ Atlantic Swiss AT-55858.41.29 Limited', 80000000, 9, 0, 42, 5, 'Automatic', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(29, 'Đồng hồ Atlantic Swiss AT-52780.41.51 Limited', 35940000, 11, 0, 42, 5, 'Automatic (Cơ tự động)', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(30, 'Đồng hồ Atlantic Swiss AT-56555.45.21', 18980000, 14, 0, 41, 5, 'Quartz (Điện tử)', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(31, 'Đồng hồ Citizen CT-BM7466-81H', 7985000, 20, 0, 40, 10, 'Eco-Drive', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính khoáng'),
(32, 'Đồng hồ Citizen CT-NH8363-14A', 6485000, 20, 0, 41, 10, 'Cơ tự động', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính Mineral chịu lực'),
(33, 'Đồng hồ Citizen CT-NH8363-14H\r\n', 6485000, 20, 0, 41, 10, 'Cơ tự động', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính Mineral chịu lực'),
(34, 'Đồng hồ Citizen CT-NH8350-08A\r\n', 6085000, 20, 0, 40, 10, 'Cơ tự động', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính Mineral chịu lực'),
(35, 'Đồng hồ Citizen CT-BI1050-56A\r\n', 3285000, 20, 0, 40, 10, 'Quartz', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Mineral Crystal'),
(36, 'Đồng hồ Citizen CT-NH8360-80J\r\n', 6985000, 20, 0, 40, 10, 'Cơ tự động', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính Mineral chịu lực'),
(37, 'Đồng hồ Citizen CT-NH8366-83A\r\n', 6300000, 20, 0, 40, 10, 'Cơ tự động', '2024-06-22', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính Mineral chịu lực'),
(38, 'Đồng hồ Epos Swiss E-3435.313.22.18.25 Limited', 62800000, 20, 0, 44, 5, 'Máy cơ lên cót bằng tay', '2024-06-23', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(39, 'Đồng hồ Epos Swiss E-8000.700.22.88.32 Diamond', 23900000, 20, 0, 40, 10, 'Quartz (Điện tử)', '2024-06-23', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nữ', 'Kính sapphire'),
(40, 'Đồng hồ Epos Swiss E-4314.133.20.89.10 Diamond', 35600000, 20, 0, 42, 5, 'Cơ tự động', '2024-06-23', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nữ', 'Kính sapphire'),
(41, 'Đồng hồ Epos Swiss E-3439.322.24.26.25', 70300000, 20, 0, 40, 5, 'Cơ tự động', '2024-06-23', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(42, 'Đồng hồ Epos Swiss E-3439.322.24.26.34', 76300000, 20, 0, 42, 10, 'Cơ tự động', '2024-06-23', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire');
INSERT INTO `dongho` (`madongho`, `tendongho`, `giatien`, `soluong`, `tragop`, `duongkinh`, `chongnuoc`, `bomay`, `NGAYTHEM`, `thongtin`, `gioitinh`, `chatlieu`) VALUES
(43, 'Đồng hồ Epos Swiss E-3439.322.24.18.25', 70300000, 20, 0, 42, 5, 'Cơ tự động', '2024-06-23', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(44, 'Đồng hồ Epos Swiss E-3391.832.22.20.25', 80000000, 20, 0, 45, 5, 'Cơ tự động', '2024-06-23', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(45, 'Đồng Hồ Epos Swiss E-3500.169.24.25.25 Limited', 57100000, 20, 0, 41, 5, 'Cơ tự động', '2024-06-23', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(46, 'Đồng hồ Epos Swiss E-3390.152.22.11.25', 100000000, 20, 0, 40, 5, 'Cơ tự động', '2024-06-23', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(47, 'Đồng Hồ Epos Swiss E-3439.322.20.16.25', 61000000, 20, 0, 40, 10, 'Cơ tự động', '2024-06-23', 'Với thiết kế đồng hồ thể thao điển hình dành cho phái mạnh luôn thuộc top những mẫu đồng hồ bán chạy nhất tại Đăng Quang Watch. Chiếc đồng hồ này không chỉ là một công cụ đo thời gian, mà còn là một biểu tượng của sự sang trọng và đẳng cấp. Được chế tác từ những vật liệu cao cấp nhất, vỏ đồng hồ được làm từ thép không gỉ sáng bóng, chống trầy xước và bền bỉ với thời gian. Mặt kính sapphire chống lóa mang đến sự rõ nét hoàn hảo, bảo vệ chiếc đồng hồ khỏi những va chạm hàng ngày.\r\n\r\nMặt số của đồng hồ được thiết kế tinh tế với những chi tiết tinh xảo, từ các vạch số đến kim chỉ giờ, phút và giây. Các vạch số được phủ lớp dạ quang, giúp bạn dễ dàng xem giờ trong điều kiện thiếu ánh sáng. Kim giây chuyển động mượt mà, tạo cảm giác như thời gian đang trôi chậm lại, cho bạn những giây phút yên bình giữa cuộc sống hối hả.\r\n\r\nDây đeo đồng hồ làm từ da thật cao cấp, mang lại cảm giác êm ái và thoải mái khi đeo. Màu sắc của dây đeo được lựa chọn cẩn thận để hoàn thiện vẻ đẹp tổng thể của chiếc đồng hồ, tạo nên một phong cách thời trang thanh lịch và trang nhã. Khóa cài chắc chắn, dễ dàng điều chỉnh độ rộng, phù hợp với mọi kích cỡ cổ tay.\r\n\r\nChiếc đồng hồ này không chỉ là phụ kiện thời trang mà còn là người bạn đồng hành đáng tin cậy trong mọi hoạt động hàng ngày. Với khả năng chống nước lên đến 50 mét, bạn có thể yên tâm sử dụng khi rửa tay, đi mưa hay thậm chí khi bơi lội. Bộ máy đồng hồ chính xác, bền bỉ, được sản xuất bởi những nghệ nhân tài ba, đảm bảo độ chính xác cao và tuổi thọ lâu dài.\r\n\r\nMỗi chi tiết của chiếc đồng hồ này đều được chế tác tỉ mỉ, từ logo thương hiệu được khắc nổi trên núm vặn đến nắp lưng trong suốt, cho phép bạn chiêm ngưỡng bộ máy bên trong hoạt động. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật và công nghệ, giữa truyền thống và hiện đại.\r\n\r\nChiếc đồng hồ này không chỉ giúp bạn quản lý thời gian một cách hiệu quả mà còn thể hiện phong cách sống tinh tế và đẳng cấp của bạn. Đây là món quà hoàn hảo dành cho những người đàn ông và phụ nữ thành đạt, những người luôn trân trọng giá trị của thời gian và biết cách tận hưởng cuộc sống. Hãy để chiếc đồng hồ này cùng bạn viết nên câu chuyện thành công của riêng mình, với mỗi giây phút đều tràn đầy ý nghĩa và giá trị. ', 'Nam', 'Kính sapphire'),
(48, 'Đồng hồ Diamond D DM64205IG-R', 100, 1, 0, 40, 10, 'Automatic', '2024-06-30', 'DOng ho xin', 'Nam', 'Sapphire');

--
-- Triggers `dongho`
--
DELIMITER $$
CREATE TRIGGER `delete_dongho` AFTER DELETE ON `dongho` FOR EACH ROW BEGIN
        UPDATE thongke
        SET dongho = dongho - 1
        WHERE mathongke = 1;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `dongho_after_insert` AFTER INSERT ON `dongho` FOR EACH ROW BEGIN
    CALL UpdateThongKeOnInsert('dongho', NEW.giatien, NEW.soluong);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `madonhang` int(11) NOT NULL,
  `username` varchar(11) NOT NULL,
  `tongtien` bigint(20) NOT NULL,
  `diachi` varchar(255) DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT 'Không có ghi chú',
  `tinhtrang` enum('Chờ xác nhận','Đã xác nhận','Đang vận chuyển','Đã nhận hàng','Đã hủy') NOT NULL,
  `thanhtoan` enum('Đã hủy','Đã thanh toán','Khi nhận hàng','Chưa thanh toán') NOT NULL,
  `NGAYTHEM` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`madonhang`, `username`, `tongtien`, `diachi`, `ghichu`, `tinhtrang`, `thanhtoan`, `NGAYTHEM`) VALUES
(1, 'tuanminh', 6600000, 'Phú Thọ', 'Không có ghi chú', 'Đã hủy', 'Khi nhận hàng', '2024-06-26'),
(2, 'vanthi', 10440000, 'Nam Định', 'Không có ghi chú', 'Đã nhận hàng', 'Khi nhận hàng', '2024-06-26'),
(4, 'minhquan', 6020000, 'Dong Da', 'Không có', 'Đã nhận hàng', 'Đã thanh toán', '2024-06-28'),
(6, 'minhquan', 10860000, 'Dong Da', 'Không có', 'Chờ xác nhận', 'Khi nhận hàng', '2024-06-28'),
(7, 'minhquan', 20130000, 'Vĩnh Phúc', 'Lái xe về Vĩnh Phúc hỏi có ai tên Bộ không', 'Chờ xác nhận', 'Khi nhận hàng', '2024-06-28');

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
        WHERE mathongke = 1;
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
        WHERE mathongke = 1;

        -- Giảm giá trị của các cột tương ứng dựa vào tình trạng cũ
        IF OLD.tinhtrang = 'Chờ xác nhận' THEN
            UPDATE thongke
            SET donhangchoxacnhan = donhangchoxacnhan - 1
            WHERE mathongke = 1;
        ELSEIF OLD.tinhtrang = 'Đang vận chuyển' THEN
            UPDATE thongke
            SET donhangdanggiao = donhangdanggiao - 1
            WHERE mathongke = 1;
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
    WHERE mathongke = 1;

    -- Kiểm tra nếu tinhtrang là 'Chờ xác nhận'
    IF NEW.tinhtrang = 'Chờ xác nhận' THEN
        -- Tăng cột donhangchoxacnhan trong bảng thongke
        UPDATE thongke
        SET donhangchoxacnhan = donhangchoxacnhan + 1
        WHERE mathongke = 1;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `donhang_after_nhan` AFTER UPDATE ON `donhang` FOR EACH ROW BEGIN
    DECLARE v_done INT DEFAULT FALSE;
    DECLARE v_machitiet INT;
    DECLARE v_madon INT;
    DECLARE v_loaisanpham VARCHAR(20);
    DECLARE v_masanpham INT;
    DECLARE v_soluong INT;
    DECLARE v_giatien INT;
    DECLARE v_tinhtrang_cu VARCHAR(255);
    
    -- Declare cursor
    DECLARE cur CURSOR FOR 
    SELECT machitietdonhang, madonhang, loaisanpham, masanpham, soluong, giatien 
    FROM ChiTietDonHang
    WHERE madonhang = OLD.madonhang;
    
    -- Declare handler
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_done = TRUE;

    -- Check if status changed from 'Đang vận chuyển' to 'Đã nhận hàng'
    IF OLD.tinhtrang = 'Đang vận chuyển' AND NEW.tinhtrang = 'Đã nhận hàng' THEN
        -- Update thongke table
        UPDATE thongke
        SET donhangdanggiao = donhangdanggiao - 1,
            donhangdagiao = donhangdagiao + 1,
            luotthanhtoan = luotthanhtoan + 1,
            luothoanthanhdon = luothoanthanhdon + 1,
            chiphi = chiphi + 50000,
            doanhthu = doanhthu + NEW.tongtien
        WHERE mathongke = 1;

        -- Open cursor
        OPEN cur;

        -- Loop through chitietdonhang records
        read_loop: LOOP
            FETCH cur INTO v_machitiet, v_madon, v_loaisanpham, v_masanpham, v_soluong, v_giatien;
            IF v_done THEN
                LEAVE read_loop;
            END IF;

            -- Decrease the corresponding product quantity based on loaisanpham
            CASE v_loaisanpham
                WHEN 'dongho' THEN
                    UPDATE dongho
                    SET dongho.soluong = dongho.soluong - v_soluong
                    WHERE dongho.madongho = v_masanpham;
                WHEN 'butky' THEN
                    UPDATE butky
                    SET butky.soluong = butky.soluong - v_soluong
                    WHERE butky.mabutky = v_masanpham;
                WHEN 'kinhmat' THEN
                    UPDATE kinhmat
                    SET kinhmat.soluong = kinhmat.soluong - v_soluong
                    WHERE kinhmat.makinhmat = v_masanpham;
                WHEN 'phukien' THEN
                    UPDATE phukien
                    SET phukien.soluong = phukien.soluong - v_soluong
                    WHERE phukien.maphukien = v_masanpham;
                WHEN 'trangsuc' THEN
                    UPDATE trangsuc
                    SET trangsuc.soluong = trangsuc.soluong - v_soluong
                    WHERE trangsuc.matrangsuc = v_masanpham;
                ELSE
                    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = v_loaisanpham;
            END CASE;
        END LOOP;
        
        -- Close cursor
        CLOSE cur;
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
        WHERE mathongke = 1;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `kinhmat`
--

CREATE TABLE `kinhmat` (
  `makinhmat` int(11) NOT NULL,
  `tensanpham` varchar(255) NOT NULL,
  `giatien` int(11) NOT NULL,
  `rongmat` int(100) NOT NULL,
  `songmui` int(100) NOT NULL,
  `daigong` int(11) NOT NULL,
  `tinhnang` varchar(255) NOT NULL,
  `tragop` int(100) NOT NULL,
  `soluong` int(11) NOT NULL,
  `NGAYTHEM` date NOT NULL DEFAULT current_timestamp(),
  `thongtin` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kinhmat`
--

INSERT INTO `kinhmat` (`makinhmat`, `tensanpham`, `giatien`, `rongmat`, `songmui`, `daigong`, `tinhnang`, `tragop`, `soluong`, `NGAYTHEM`, `thongtin`) VALUES
(1, 'Kính mát PHILIPPE AUGUSTE PA555-008-G', 3280000, 53, 20, 135, 'Chống chói lóa, tia UV', 0, 15, '2024-06-21', 'Kính mắt sang trọng này không chỉ là một phụ kiện thời trang, mà còn là biểu tượng của sự thanh lịch và phong cách đỉnh cao. Được chế tác tỉ mỉ từ những vật liệu cao cấp nhất, gọng kính được làm từ hợp kim titanium nhẹ nhưng vô cùng bền bỉ, mang đến sự thoải mái tối đa khi đeo suốt cả ngày dài. Màu sắc của gọng kính được hoàn thiện với lớp phủ chống ăn mòn, giữ cho vẻ ngoài luôn mới mẻ và rạng rỡ.\r\n\r\nMắt kính được làm từ chất liệu polycarbonate chống trầy xước, không chỉ bền bỉ mà còn mang đến khả năng chống tia UV hoàn hảo, bảo vệ đôi mắt của bạn khỏi tác hại của ánh nắng mặt trời. Lớp phủ chống lóa trên bề mặt mắt kính giúp giảm thiểu hiện tượng chói mắt, mang lại tầm nhìn rõ ràng và sắc nét ngay cả trong điều kiện ánh sáng mạnh.\r\n\r\nThiết kế của kính mắt này mang phong cách hiện đại với các đường nét tinh tế và cân đối, phù hợp với nhiều kiểu dáng khuôn mặt khác nhau. Phần đệm mũi được làm từ silicon mềm mại, có thể điều chỉnh dễ dàng để ôm sát vừa vặn, không gây cảm giác khó chịu hay để lại vết hằn trên da. Bản lề kính được thiết kế chắc chắn, đảm bảo sự linh hoạt và độ bền cao, cho phép bạn mở và gập kính một cách mượt mà và dễ dàng.\r\n\r\nMỗi chi tiết nhỏ trên chiếc kính mắt này đều được chăm chút tỉ mỉ, từ logo thương hiệu được khắc nổi tinh tế trên gọng kính, đến các đường nét hoàn thiện tinh xảo trên mắt kính. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật chế tác và công nghệ hiện đại, mang đến cho bạn một sản phẩm không chỉ đẹp mà còn vô cùng tiện ích.\r\n\r\nKính mắt sang trọng này không chỉ giúp bảo vệ đôi mắt của bạn mà còn tôn lên vẻ đẹp, phong cách và đẳng cấp của bạn. Dù bạn đang đi làm, đi dự tiệc hay dạo phố, chiếc kính này sẽ là người bạn đồng hành lý tưởng, giúp bạn tự tin và nổi bật trong mọi tình huống. Đây là món quà hoàn hảo dành cho những người yêu thích thời trang và luôn tìm kiếm những sản phẩm chất lượng cao.\r\n\r\nHãy để kính mắt sang trọng này trở thành điểm nhấn trong phong cách của bạn, mang đến cho bạn không chỉ sự bảo vệ tuyệt vời mà còn là cảm giác tự tin và phong cách trong từng khoảnh khắc. Với mỗi chi tiết được chế tác công phu, kính mắt này thực sự là một kiệt tác, là sự lựa chọn hoàn hảo cho những ai yêu thích sự hoàn hảo và đẳng cấp.'),
(2, 'Kính mát PHILIPPE AUGUSTE PA555-007-F', 3090000, 51, 20, 145, 'Chống chói lóa, tia UV', 0, 50, '2024-06-21', 'Kính mắt sang trọng này không chỉ là một phụ kiện thời trang, mà còn là biểu tượng của sự thanh lịch và phong cách đỉnh cao. Được chế tác tỉ mỉ từ những vật liệu cao cấp nhất, gọng kính được làm từ hợp kim titanium nhẹ nhưng vô cùng bền bỉ, mang đến sự thoải mái tối đa khi đeo suốt cả ngày dài. Màu sắc của gọng kính được hoàn thiện với lớp phủ chống ăn mòn, giữ cho vẻ ngoài luôn mới mẻ và rạng rỡ.\r\n\r\nMắt kính được làm từ chất liệu polycarbonate chống trầy xước, không chỉ bền bỉ mà còn mang đến khả năng chống tia UV hoàn hảo, bảo vệ đôi mắt của bạn khỏi tác hại của ánh nắng mặt trời. Lớp phủ chống lóa trên bề mặt mắt kính giúp giảm thiểu hiện tượng chói mắt, mang lại tầm nhìn rõ ràng và sắc nét ngay cả trong điều kiện ánh sáng mạnh.\r\n\r\nThiết kế của kính mắt này mang phong cách hiện đại với các đường nét tinh tế và cân đối, phù hợp với nhiều kiểu dáng khuôn mặt khác nhau. Phần đệm mũi được làm từ silicon mềm mại, có thể điều chỉnh dễ dàng để ôm sát vừa vặn, không gây cảm giác khó chịu hay để lại vết hằn trên da. Bản lề kính được thiết kế chắc chắn, đảm bảo sự linh hoạt và độ bền cao, cho phép bạn mở và gập kính một cách mượt mà và dễ dàng.\r\n\r\nMỗi chi tiết nhỏ trên chiếc kính mắt này đều được chăm chút tỉ mỉ, từ logo thương hiệu được khắc nổi tinh tế trên gọng kính, đến các đường nét hoàn thiện tinh xảo trên mắt kính. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật chế tác và công nghệ hiện đại, mang đến cho bạn một sản phẩm không chỉ đẹp mà còn vô cùng tiện ích.\r\n\r\nKính mắt sang trọng này không chỉ giúp bảo vệ đôi mắt của bạn mà còn tôn lên vẻ đẹp, phong cách và đẳng cấp của bạn. Dù bạn đang đi làm, đi dự tiệc hay dạo phố, chiếc kính này sẽ là người bạn đồng hành lý tưởng, giúp bạn tự tin và nổi bật trong mọi tình huống. Đây là món quà hoàn hảo dành cho những người yêu thích thời trang và luôn tìm kiếm những sản phẩm chất lượng cao.\r\n\r\nHãy để kính mắt sang trọng này trở thành điểm nhấn trong phong cách của bạn, mang đến cho bạn không chỉ sự bảo vệ tuyệt vời mà còn là cảm giác tự tin và phong cách trong từng khoảnh khắc. Với mỗi chi tiết được chế tác công phu, kính mắt này thực sự là một kiệt tác, là sự lựa chọn hoàn hảo cho những ai yêu thích sự hoàn hảo và đẳng cấp.'),
(3, 'Kính mát PHILIPPE AUGUSTE PA555-007-B', 3090000, 51, 20, 145, 'Chống chói lóa, tia UV', 0, 44, '2024-06-21', 'Kính mắt sang trọng này không chỉ là một phụ kiện thời trang, mà còn là biểu tượng của sự thanh lịch và phong cách đỉnh cao. Được chế tác tỉ mỉ từ những vật liệu cao cấp nhất, gọng kính được làm từ hợp kim titanium nhẹ nhưng vô cùng bền bỉ, mang đến sự thoải mái tối đa khi đeo suốt cả ngày dài. Màu sắc của gọng kính được hoàn thiện với lớp phủ chống ăn mòn, giữ cho vẻ ngoài luôn mới mẻ và rạng rỡ.\r\n\r\nMắt kính được làm từ chất liệu polycarbonate chống trầy xước, không chỉ bền bỉ mà còn mang đến khả năng chống tia UV hoàn hảo, bảo vệ đôi mắt của bạn khỏi tác hại của ánh nắng mặt trời. Lớp phủ chống lóa trên bề mặt mắt kính giúp giảm thiểu hiện tượng chói mắt, mang lại tầm nhìn rõ ràng và sắc nét ngay cả trong điều kiện ánh sáng mạnh.\r\n\r\nThiết kế của kính mắt này mang phong cách hiện đại với các đường nét tinh tế và cân đối, phù hợp với nhiều kiểu dáng khuôn mặt khác nhau. Phần đệm mũi được làm từ silicon mềm mại, có thể điều chỉnh dễ dàng để ôm sát vừa vặn, không gây cảm giác khó chịu hay để lại vết hằn trên da. Bản lề kính được thiết kế chắc chắn, đảm bảo sự linh hoạt và độ bền cao, cho phép bạn mở và gập kính một cách mượt mà và dễ dàng.\r\n\r\nMỗi chi tiết nhỏ trên chiếc kính mắt này đều được chăm chút tỉ mỉ, từ logo thương hiệu được khắc nổi tinh tế trên gọng kính, đến các đường nét hoàn thiện tinh xảo trên mắt kính. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật chế tác và công nghệ hiện đại, mang đến cho bạn một sản phẩm không chỉ đẹp mà còn vô cùng tiện ích.\r\n\r\nKính mắt sang trọng này không chỉ giúp bảo vệ đôi mắt của bạn mà còn tôn lên vẻ đẹp, phong cách và đẳng cấp của bạn. Dù bạn đang đi làm, đi dự tiệc hay dạo phố, chiếc kính này sẽ là người bạn đồng hành lý tưởng, giúp bạn tự tin và nổi bật trong mọi tình huống. Đây là món quà hoàn hảo dành cho những người yêu thích thời trang và luôn tìm kiếm những sản phẩm chất lượng cao.\r\n\r\nHãy để kính mắt sang trọng này trở thành điểm nhấn trong phong cách của bạn, mang đến cho bạn không chỉ sự bảo vệ tuyệt vời mà còn là cảm giác tự tin và phong cách trong từng khoảnh khắc. Với mỗi chi tiết được chế tác công phu, kính mắt này thực sự là một kiệt tác, là sự lựa chọn hoàn hảo cho những ai yêu thích sự hoàn hảo và đẳng cấp.'),
(4, 'Kính mắt PHILIPPE AUGUSTE PA555-007-D', 3090000, 51, 20, 145, 'Chống chói lóa, tia UV', 0, 55, '2024-06-21', 'Kính mắt sang trọng này không chỉ là một phụ kiện thời trang, mà còn là biểu tượng của sự thanh lịch và phong cách đỉnh cao. Được chế tác tỉ mỉ từ những vật liệu cao cấp nhất, gọng kính được làm từ hợp kim titanium nhẹ nhưng vô cùng bền bỉ, mang đến sự thoải mái tối đa khi đeo suốt cả ngày dài. Màu sắc của gọng kính được hoàn thiện với lớp phủ chống ăn mòn, giữ cho vẻ ngoài luôn mới mẻ và rạng rỡ.\r\n\r\nMắt kính được làm từ chất liệu polycarbonate chống trầy xước, không chỉ bền bỉ mà còn mang đến khả năng chống tia UV hoàn hảo, bảo vệ đôi mắt của bạn khỏi tác hại của ánh nắng mặt trời. Lớp phủ chống lóa trên bề mặt mắt kính giúp giảm thiểu hiện tượng chói mắt, mang lại tầm nhìn rõ ràng và sắc nét ngay cả trong điều kiện ánh sáng mạnh.\r\n\r\nThiết kế của kính mắt này mang phong cách hiện đại với các đường nét tinh tế và cân đối, phù hợp với nhiều kiểu dáng khuôn mặt khác nhau. Phần đệm mũi được làm từ silicon mềm mại, có thể điều chỉnh dễ dàng để ôm sát vừa vặn, không gây cảm giác khó chịu hay để lại vết hằn trên da. Bản lề kính được thiết kế chắc chắn, đảm bảo sự linh hoạt và độ bền cao, cho phép bạn mở và gập kính một cách mượt mà và dễ dàng.\r\n\r\nMỗi chi tiết nhỏ trên chiếc kính mắt này đều được chăm chút tỉ mỉ, từ logo thương hiệu được khắc nổi tinh tế trên gọng kính, đến các đường nét hoàn thiện tinh xảo trên mắt kính. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật chế tác và công nghệ hiện đại, mang đến cho bạn một sản phẩm không chỉ đẹp mà còn vô cùng tiện ích.\r\n\r\nKính mắt sang trọng này không chỉ giúp bảo vệ đôi mắt của bạn mà còn tôn lên vẻ đẹp, phong cách và đẳng cấp của bạn. Dù bạn đang đi làm, đi dự tiệc hay dạo phố, chiếc kính này sẽ là người bạn đồng hành lý tưởng, giúp bạn tự tin và nổi bật trong mọi tình huống. Đây là món quà hoàn hảo dành cho những người yêu thích thời trang và luôn tìm kiếm những sản phẩm chất lượng cao.\r\n\r\nHãy để kính mắt sang trọng này trở thành điểm nhấn trong phong cách của bạn, mang đến cho bạn không chỉ sự bảo vệ tuyệt vời mà còn là cảm giác tự tin và phong cách trong từng khoảnh khắc. Với mỗi chi tiết được chế tác công phu, kính mắt này thực sự là một kiệt tác, là sự lựa chọn hoàn hảo cho những ai yêu thích sự hoàn hảo và đẳng cấp.'),
(5, 'Kính mát PHILIPPE AUGUSTE PA555-002-E', 3090000, 57, 16, 135, 'Chống chói lóa, tia UV', 0, 11, '2024-06-21', 'Kính mắt sang trọng này không chỉ là một phụ kiện thời trang, mà còn là biểu tượng của sự thanh lịch và phong cách đỉnh cao. Được chế tác tỉ mỉ từ những vật liệu cao cấp nhất, gọng kính được làm từ hợp kim titanium nhẹ nhưng vô cùng bền bỉ, mang đến sự thoải mái tối đa khi đeo suốt cả ngày dài. Màu sắc của gọng kính được hoàn thiện với lớp phủ chống ăn mòn, giữ cho vẻ ngoài luôn mới mẻ và rạng rỡ.\r\n\r\nMắt kính được làm từ chất liệu polycarbonate chống trầy xước, không chỉ bền bỉ mà còn mang đến khả năng chống tia UV hoàn hảo, bảo vệ đôi mắt của bạn khỏi tác hại của ánh nắng mặt trời. Lớp phủ chống lóa trên bề mặt mắt kính giúp giảm thiểu hiện tượng chói mắt, mang lại tầm nhìn rõ ràng và sắc nét ngay cả trong điều kiện ánh sáng mạnh.\r\n\r\nThiết kế của kính mắt này mang phong cách hiện đại với các đường nét tinh tế và cân đối, phù hợp với nhiều kiểu dáng khuôn mặt khác nhau. Phần đệm mũi được làm từ silicon mềm mại, có thể điều chỉnh dễ dàng để ôm sát vừa vặn, không gây cảm giác khó chịu hay để lại vết hằn trên da. Bản lề kính được thiết kế chắc chắn, đảm bảo sự linh hoạt và độ bền cao, cho phép bạn mở và gập kính một cách mượt mà và dễ dàng.\r\n\r\nMỗi chi tiết nhỏ trên chiếc kính mắt này đều được chăm chút tỉ mỉ, từ logo thương hiệu được khắc nổi tinh tế trên gọng kính, đến các đường nét hoàn thiện tinh xảo trên mắt kính. Đây chính là sự kết hợp hoàn hảo giữa nghệ thuật chế tác và công nghệ hiện đại, mang đến cho bạn một sản phẩm không chỉ đẹp mà còn vô cùng tiện ích.\r\n\r\nKính mắt sang trọng này không chỉ giúp bảo vệ đôi mắt của bạn mà còn tôn lên vẻ đẹp, phong cách và đẳng cấp của bạn. Dù bạn đang đi làm, đi dự tiệc hay dạo phố, chiếc kính này sẽ là người bạn đồng hành lý tưởng, giúp bạn tự tin và nổi bật trong mọi tình huống. Đây là món quà hoàn hảo dành cho những người yêu thích thời trang và luôn tìm kiếm những sản phẩm chất lượng cao.\r\n\r\nHãy để kính mắt sang trọng này trở thành điểm nhấn trong phong cách của bạn, mang đến cho bạn không chỉ sự bảo vệ tuyệt vời mà còn là cảm giác tự tin và phong cách trong từng khoảnh khắc. Với mỗi chi tiết được chế tác công phu, kính mắt này thực sự là một kiệt tác, là sự lựa chọn hoàn hảo cho những ai yêu thích sự hoàn hảo và đẳng cấp.');

--
-- Triggers `kinhmat`
--
DELIMITER $$
CREATE TRIGGER `delete_kinhmat` AFTER DELETE ON `kinhmat` FOR EACH ROW BEGIN
    UPDATE thongke
    SET kinhmat = kinhmat - 1
    WHERE mathongke = 1;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `kinhmat_after_insert` AFTER INSERT ON `kinhmat` FOR EACH ROW BEGIN
    CALL UpdateThongKeOnInsert('kinhmat', NEW.giatien, NEW.soluong);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `phukien`
--

CREATE TABLE `phukien` (
  `maphukien` int(11) NOT NULL,
  `tenphukien` varchar(255) NOT NULL,
  `giatien` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `thongtin` text NOT NULL,
  `tragop` int(100) NOT NULL DEFAULT 0,
  `NGAYTHEM` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phukien`
--

INSERT INTO `phukien` (`maphukien`, `tenphukien`, `giatien`, `soluong`, `thongtin`, `tragop`, `NGAYTHEM`) VALUES
(1, 'Dây da Diamond D D DM W 12', 300000, 10, 'Hàng ngàn mẫu Dây Đeo Đồng hồ chính hãng: Dây da, Dây Cao Su, Dây Kim Loại, Dây Đeo Đồng Hồ Vải,... Phụ kiện dây da đồng hồ sang trọng này là sự kết hợp hoàn hảo giữa chất liệu cao cấp và thiết kế tinh tế. Được chế tác từ da thuộc tự nhiên, dây đeo không chỉ mềm mại và êm ái khi đeo mà còn mang lại độ bền vượt trội, đảm bảo sử dụng lâu dài mà không bị nứt hay bong tróc. Màu sắc của dây da được nhuộm đều và tinh tế, mang lại vẻ đẹp tự nhiên và sang trọng, phù hợp với nhiều loại đồng hồ khác nhau.\n\nCác đường chỉ khâu trên dây đeo được thực hiện tỉ mỉ và chắc chắn, tạo điểm nhấn tinh tế và thêm phần bền bỉ cho sản phẩm. Khóa cài làm từ thép không gỉ, được đánh bóng kỹ lưỡng, giúp dễ dàng điều chỉnh độ rộng và giữ cho dây đeo luôn cố định, không bị lỏng lẻo trong suốt quá trình sử dụng.\n\nVới phụ kiện dây da này, chiếc đồng hồ của bạn sẽ được nâng tầm đẳng cấp, mang lại phong cách thanh lịch và quý phái cho người đeo. Đây chính là lựa chọn hoàn hảo cho những ai yêu thích sự hoàn mỹ và tinh tế trong từng chi tiết.', 0, '2024-06-21'),
(2, 'Dây da đồng hồ 71C-443SFM-30/22 - Size 22 mm - Màu đen', 199000, 100, 'Hàng ngàn mẫu Dây Đeo Đồng hồ chính hãng: Dây da, Dây Cao Su, Dây Kim Loại, Dây Đeo Đồng Hồ Vải,... Phụ kiện dây da đồng hồ sang trọng này là sự kết hợp hoàn hảo giữa chất liệu cao cấp và thiết kế tinh tế. Được chế tác từ da thuộc tự nhiên, dây đeo không chỉ mềm mại và êm ái khi đeo mà còn mang lại độ bền vượt trội, đảm bảo sử dụng lâu dài mà không bị nứt hay bong tróc. Màu sắc của dây da được nhuộm đều và tinh tế, mang lại vẻ đẹp tự nhiên và sang trọng, phù hợp với nhiều loại đồng hồ khác nhau.\n\nCác đường chỉ khâu trên dây đeo được thực hiện tỉ mỉ và chắc chắn, tạo điểm nhấn tinh tế và thêm phần bền bỉ cho sản phẩm. Khóa cài làm từ thép không gỉ, được đánh bóng kỹ lưỡng, giúp dễ dàng điều chỉnh độ rộng và giữ cho dây đeo luôn cố định, không bị lỏng lẻo trong suốt quá trình sử dụng.\n\nVới phụ kiện dây da này, chiếc đồng hồ của bạn sẽ được nâng tầm đẳng cấp, mang lại phong cách thanh lịch và quý phái cho người đeo. Đây chính là lựa chọn hoàn hảo cho những ai yêu thích sự hoàn mỹ và tinh tế trong từng chi tiết.', 0, '2024-06-21'),
(3, 'Dây cao su đồng hồ Tsar Bomba D TB8218F-LIGHT BLUE', 3500000, 3, 'Dây cao su  D TB8218F-LIGHT BLUE sử dụng cho các mã đồng hồ Tsar Bomba TB-8214 & TB-8218. Phụ kiện dây da đồng hồ sang trọng này là sự kết hợp hoàn hảo giữa chất liệu cao cấp và thiết kế tinh tế. Được chế tác từ da thuộc tự nhiên, dây đeo không chỉ mềm mại và êm ái khi đeo mà còn mang lại độ bền vượt trội, đảm bảo sử dụng lâu dài mà không bị nứt hay bong tróc. Màu sắc của dây da được nhuộm đều và tinh tế, mang lại vẻ đẹp tự nhiên và sang trọng, phù hợp với nhiều loại đồng hồ khác nhau.\n\nCác đường chỉ khâu trên dây đeo được thực hiện tỉ mỉ và chắc chắn, tạo điểm nhấn tinh tế và thêm phần bền bỉ cho sản phẩm. Khóa cài làm từ thép không gỉ, được đánh bóng kỹ lưỡng, giúp dễ dàng điều chỉnh độ rộng và giữ cho dây đeo luôn cố định, không bị lỏng lẻo trong suốt quá trình sử dụng.\n\nVới phụ kiện dây da này, chiếc đồng hồ của bạn sẽ được nâng tầm đẳng cấp, mang lại phong cách thanh lịch và quý phái cho người đeo. Đây chính là lựa chọn hoàn hảo cho những ai yêu thích sự hoàn mỹ và tinh tế trong từng chi tiết.', 0, '2024-06-21'),
(4, 'Dây da Jacques D JL-1-1846C', 650000, 20, 'Hàng ngàn mẫu Dây Đeo Đồng hồ chính hãng: Dây da, Dây Cao Su, Dây Kim Loại, Dây Đeo Đồng Hồ Vải,... Phụ kiện dây da đồng hồ sang trọng này là sự kết hợp hoàn hảo giữa chất liệu cao cấp và thiết kế tinh tế. Được chế tác từ da thuộc tự nhiên, dây đeo không chỉ mềm mại và êm ái khi đeo mà còn mang lại độ bền vượt trội, đảm bảo sử dụng lâu dài mà không bị nứt hay bong tróc. Màu sắc của dây da được nhuộm đều và tinh tế, mang lại vẻ đẹp tự nhiên và sang trọng, phù hợp với nhiều loại đồng hồ khác nhau.\n\nCác đường chỉ khâu trên dây đeo được thực hiện tỉ mỉ và chắc chắn, tạo điểm nhấn tinh tế và thêm phần bền bỉ cho sản phẩm. Khóa cài làm từ thép không gỉ, được đánh bóng kỹ lưỡng, giúp dễ dàng điều chỉnh độ rộng và giữ cho dây đeo luôn cố định, không bị lỏng lẻo trong suốt quá trình sử dụng.\n\nVới phụ kiện dây da này, chiếc đồng hồ của bạn sẽ được nâng tầm đẳng cấp, mang lại phong cách thanh lịch và quý phái cho người đeo. Đây chính là lựa chọn hoàn hảo cho những ai yêu thích sự hoàn mỹ và tinh tế trong từng chi tiết.', 0, '2024-06-21'),
(5, 'Dây da Đăng Quang D-A-273NKS.H19.NERO-19', 450000, 10, 'Hàng ngàn mẫu Dây Đeo Đồng hồ chính hãng: Dây da, Dây Cao Su, Dây Kim Loại, Dây Đeo Đồng Hồ Vải,... Phụ kiện dây da đồng hồ sang trọng này là sự kết hợp hoàn hảo giữa chất liệu cao cấp và thiết kế tinh tế. Được chế tác từ da thuộc tự nhiên, dây đeo không chỉ mềm mại và êm ái khi đeo mà còn mang lại độ bền vượt trội, đảm bảo sử dụng lâu dài mà không bị nứt hay bong tróc. Màu sắc của dây da được nhuộm đều và tinh tế, mang lại vẻ đẹp tự nhiên và sang trọng, phù hợp với nhiều loại đồng hồ khác nhau.\n\nCác đường chỉ khâu trên dây đeo được thực hiện tỉ mỉ và chắc chắn, tạo điểm nhấn tinh tế và thêm phần bền bỉ cho sản phẩm. Khóa cài làm từ thép không gỉ, được đánh bóng kỹ lưỡng, giúp dễ dàng điều chỉnh độ rộng và giữ cho dây đeo luôn cố định, không bị lỏng lẻo trong suốt quá trình sử dụng.\n\nVới phụ kiện dây da này, chiếc đồng hồ của bạn sẽ được nâng tầm đẳng cấp, mang lại phong cách thanh lịch và quý phái cho người đeo. Đây chính là lựa chọn hoàn hảo cho những ai yêu thích sự hoàn mỹ và tinh tế trong từng chi tiết.', 0, '2024-06-21');

--
-- Triggers `phukien`
--
DELIMITER $$
CREATE TRIGGER `delete_phukien` AFTER DELETE ON `phukien` FOR EACH ROW BEGIN
    UPDATE thongke
    SET phukien = phukien - 1
    WHERE mathongke = 1;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `phukien_after_insert` AFTER INSERT ON `phukien` FOR EACH ROW BEGIN
    CALL UpdateThongKeOnInsert('phukien', NEW.giatien, NEW.soluong);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sodienthoai` varchar(15) DEFAULT NULL,
  `diachi` varchar(255) NOT NULL,
  `hoten` varchar(255) DEFAULT NULL,
  `loai_tai_khoan` varchar(255) NOT NULL DEFAULT 'ROLE_KHACHHANG',
  `enabled` tinyint(10) NOT NULL DEFAULT 1,
  `NGAYTHEM` date DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`username`, `password`, `sodienthoai`, `diachi`, `hoten`, `loai_tai_khoan`, `enabled`, `NGAYTHEM`) VALUES
('minhquan', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', '0813385858', 'Dong Da', 'Vương Minh Quân', 'ROLE_QUANTRI', 1, '2024-06-28'),
('quangdung', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', '0813385959', 'Hai Duong', 'Vũ Quang Dũng', 'ROLE_KHACHHANG', 1, '2024-06-28'),
('tuanminh', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', '0813385899', 'Phú Thọ', 'Nguyễn Tuấn Minh', 'ROLE_KHACHHANG', 1, '2024-06-28'),
('vanbo', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', '0813381234', 'Vinh Phuc', 'Nguyễn Văn Bộ', 'ROLE_KHACHHANG', 1, '2024-06-28'),
('vanthi', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', '0813389999', 'Nam Định', 'Đinh Văn Thi', 'ROLE_KHACHHANG', 1, '2024-06-28');

--
-- Triggers `taikhoan`
--
DELIMITER $$
CREATE TRIGGER `delete_taikhoan_role_khachhang` AFTER DELETE ON `taikhoan` FOR EACH ROW BEGIN
    IF OLD.loai_tai_khoan = 'ROLE_KHACHHANG' THEN
        UPDATE thongke
        SET khachhang = khachhang - 1
        WHERE mathongke = 1; -- Đảm bảo chỉ cập nhật vào dòng mathongke = 1, nếu có nhiều dòng thống kê
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
        WHERE mathongke = 1;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `thongke`
--

CREATE TABLE `thongke` (
  `mathongke` int(11) NOT NULL,
  `dongho` int(11) NOT NULL,
  `butky` int(11) NOT NULL,
  `phukien` int(11) NOT NULL,
  `trangsuc` int(11) NOT NULL,
  `kinhmat` int(11) NOT NULL,
  `donhang` int(11) NOT NULL DEFAULT 0,
  `donhangdahuy` int(11) NOT NULL,
  `donhangdagiao` int(11) NOT NULL,
  `khachhang` int(11) NOT NULL,
  `luottruycap` int(11) NOT NULL,
  `tilechuyendoi` float NOT NULL,
  `doanhthu` bigint(20) NOT NULL,
  `von` bigint(20) NOT NULL,
  `donhangchoxacnhan` int(11) NOT NULL DEFAULT 0,
  `donhangdaxacnhan` int(11) NOT NULL DEFAULT 0,
  `donhangdanggiao` int(11) NOT NULL DEFAULT 0,
  `luotdangkymoi` int(11) NOT NULL DEFAULT 0,
  `chiphi` int(11) NOT NULL DEFAULT 0,
  `luotxemsanpham` int(11) NOT NULL DEFAULT 0,
  `luotthemgiohang` int(11) NOT NULL DEFAULT 0,
  `luotdathang` int(11) NOT NULL DEFAULT 0,
  `luotthanhtoan` int(11) NOT NULL DEFAULT 0,
  `luothoanthanhdon` int(11) NOT NULL DEFAULT 0,
  `NGAYTHEM` date DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `thongke`
--

INSERT INTO `thongke` (`mathongke`, `dongho`, `butky`, `phukien`, `trangsuc`, `kinhmat`, `donhang`, `donhangdahuy`, `donhangdagiao`, `khachhang`, `luottruycap`, `tilechuyendoi`, `doanhthu`, `von`, `donhangchoxacnhan`, `donhangdaxacnhan`, `donhangdanggiao`, `luotdangkymoi`, `chiphi`, `luotxemsanpham`, `luotthemgiohang`, `luotdathang`, `luotthanhtoan`, `luothoanthanhdon`, `NGAYTHEM`) VALUES
(1, 48, 5, 5, 5, 5, 5, 1, 2, 4, 250, 0.809717, 16470000, 1000000, 2, 0, 0, 4, 2024439510, 135, 12, 5, 2, 2, '2024-06-28');

-- --------------------------------------------------------

--
-- Table structure for table `trangsuc`
--

CREATE TABLE `trangsuc` (
  `matrangsuc` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `gia` int(11) NOT NULL,
  `tragop` int(100) NOT NULL DEFAULT 0,
  `thongtin` text NOT NULL,
  `NGAYTHEM` date NOT NULL DEFAULT current_timestamp(),
  `tentrangsuc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trangsuc`
--

INSERT INTO `trangsuc` (`matrangsuc`, `soluong`, `gia`, `tragop`, `thongtin`, `NGAYTHEM`, `tentrangsuc`) VALUES
(1, 10, 1200000, 0, 'Thép 316L là một chất liệu có độ cứng cao, siêu bền và khả năng chống ăn mòn tốt. Vòng tay kim cương cao cấp này là đỉnh cao của sự xa hoa và tinh tế, mang đến vẻ đẹp rực rỡ và đẳng cấp cho người đeo. Được chế tác tỉ mỉ từ vàng trắng 18k hoặc bạch kim, vòng tay lấp lánh với những viên kim cương được cắt gọt hoàn hảo, đính kết tinh xảo dọc theo thân vòng. Mỗi viên kim cương toát lên sự lấp lánh tuyệt đẹp, bắt sáng từ mọi góc độ, tạo nên một dải ánh sáng rực rỡ và mê hoặc.\n\nThiết kế của vòng tay kết hợp hoàn hảo giữa sự thanh lịch và hiện đại, với các chi tiết chạm khắc tinh tế và cân đối. Các móc khóa chắc chắn nhưng dễ dàng thao tác, đảm bảo vòng tay luôn ôm sát cổ tay một cách hoàn hảo và an toàn. Vòng tay này không chỉ là một món trang sức mà còn là một tác phẩm nghệ thuật, thể hiện đẳng cấp và gu thẩm mỹ của người đeo.\n\nVới vòng tay kim cương cao cấp này, bạn sẽ luôn tỏa sáng và nổi bật trong mọi dịp, từ những bữa tiệc xa hoa đến các sự kiện quan trọng. Đây là món quà tuyệt vời dành cho những người phụ nữ yêu thích sự hoàn hảo và muốn khẳng định phong cách riêng biệt của mình. Hãy để vòng tay kim cương cao cấp này tô điểm cho vẻ đẹp của bạn, mang đến sự tự tin và cuốn hút không thể cưỡng lại.', '2024-06-21', 'Vòng tay cao cấp Diamond D DM-BR001IG'),
(2, 15, 1200000, 0, 'Thép 316L là một chất liệu có độ cứng cao, siêu bền và khả năng chống ăn mòn tốt. Vòng tay kim cương cao cấp này là đỉnh cao của sự xa hoa và tinh tế, mang đến vẻ đẹp rực rỡ và đẳng cấp cho người đeo. Được chế tác tỉ mỉ từ vàng trắng 18k hoặc bạch kim, vòng tay lấp lánh với những viên kim cương được cắt gọt hoàn hảo, đính kết tinh xảo dọc theo thân vòng. Mỗi viên kim cương toát lên sự lấp lánh tuyệt đẹp, bắt sáng từ mọi góc độ, tạo nên một dải ánh sáng rực rỡ và mê hoặc.\n\nThiết kế của vòng tay kết hợp hoàn hảo giữa sự thanh lịch và hiện đại, với các chi tiết chạm khắc tinh tế và cân đối. Các móc khóa chắc chắn nhưng dễ dàng thao tác, đảm bảo vòng tay luôn ôm sát cổ tay một cách hoàn hảo và an toàn. Vòng tay này không chỉ là một món trang sức mà còn là một tác phẩm nghệ thuật, thể hiện đẳng cấp và gu thẩm mỹ của người đeo.\n\nVới vòng tay kim cương cao cấp này, bạn sẽ luôn tỏa sáng và nổi bật trong mọi dịp, từ những bữa tiệc xa hoa đến các sự kiện quan trọng. Đây là món quà tuyệt vời dành cho những người phụ nữ yêu thích sự hoàn hảo và muốn khẳng định phong cách riêng biệt của mình. Hãy để vòng tay kim cương cao cấp này tô điểm cho vẻ đẹp của bạn, mang đến sự tự tin và cuốn hút không thể cưỡng lại.', '2024-06-21', 'Vòng tay cao cấp Diamond D DM-BR001RG'),
(3, 10, 1200000, 0, 'Thép 316L là một chất liệu có độ cứng cao, siêu bền và khả năng chống ăn mòn tốt. Vòng tay kim cương cao cấp này là đỉnh cao của sự xa hoa và tinh tế, mang đến vẻ đẹp rực rỡ và đẳng cấp cho người đeo. Được chế tác tỉ mỉ từ vàng trắng 18k hoặc bạch kim, vòng tay lấp lánh với những viên kim cương được cắt gọt hoàn hảo, đính kết tinh xảo dọc theo thân vòng. Mỗi viên kim cương toát lên sự lấp lánh tuyệt đẹp, bắt sáng từ mọi góc độ, tạo nên một dải ánh sáng rực rỡ và mê hoặc.\n\nThiết kế của vòng tay kết hợp hoàn hảo giữa sự thanh lịch và hiện đại, với các chi tiết chạm khắc tinh tế và cân đối. Các móc khóa chắc chắn nhưng dễ dàng thao tác, đảm bảo vòng tay luôn ôm sát cổ tay một cách hoàn hảo và an toàn. Vòng tay này không chỉ là một món trang sức mà còn là một tác phẩm nghệ thuật, thể hiện đẳng cấp và gu thẩm mỹ của người đeo.\n\nVới vòng tay kim cương cao cấp này, bạn sẽ luôn tỏa sáng và nổi bật trong mọi dịp, từ những bữa tiệc xa hoa đến các sự kiện quan trọng. Đây là món quà tuyệt vời dành cho những người phụ nữ yêu thích sự hoàn hảo và muốn khẳng định phong cách riêng biệt của mình. Hãy để vòng tay kim cương cao cấp này tô điểm cho vẻ đẹp của bạn, mang đến sự tự tin và cuốn hút không thể cưỡng lại.', '2024-06-21', 'Vòng tay cao cấp Diamond D DM-BR001W');

--
-- Triggers `trangsuc`
--
DELIMITER $$
CREATE TRIGGER `delete_trangsuc` AFTER DELETE ON `trangsuc` FOR EACH ROW BEGIN
    UPDATE thongke
    SET trangsuc = trangsuc - 1
    WHERE mathongke = 1;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trangsuc_after_insert` AFTER INSERT ON `trangsuc` FOR EACH ROW BEGIN
    CALL UpdateThongKeOnInsert('trangsuc', NEW.gia, NEW.soluong);
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anh_butky`
--
ALTER TABLE `anh_butky`
  ADD PRIMARY KEY (`maanh`),
  ADD KEY `mabutky` (`mabutky`);

--
-- Indexes for table `anh_dongho`
--
ALTER TABLE `anh_dongho`
  ADD PRIMARY KEY (`maanh`),
  ADD KEY `Fk_madongho` (`madongho`);

--
-- Indexes for table `anh_kinhmat`
--
ALTER TABLE `anh_kinhmat`
  ADD PRIMARY KEY (`maanh`),
  ADD KEY `makinhmat` (`makinhmat`);

--
-- Indexes for table `anh_phukien`
--
ALTER TABLE `anh_phukien`
  ADD PRIMARY KEY (`maanh`),
  ADD KEY `maphukien` (`maphukien`);

--
-- Indexes for table `anh_trangsuc`
--
ALTER TABLE `anh_trangsuc`
  ADD PRIMARY KEY (`maanh`),
  ADD KEY `matrangsuc` (`matrangsuc`);

--
-- Indexes for table `butky`
--
ALTER TABLE `butky`
  ADD PRIMARY KEY (`mabutky`);

--
-- Indexes for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`machitietdonhang`),
  ADD KEY `madonhang` (`madonhang`);

--
-- Indexes for table `dongho`
--
ALTER TABLE `dongho`
  ADD PRIMARY KEY (`madongho`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`madonhang`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `kinhmat`
--
ALTER TABLE `kinhmat`
  ADD PRIMARY KEY (`makinhmat`);

--
-- Indexes for table `phukien`
--
ALTER TABLE `phukien`
  ADD PRIMARY KEY (`maphukien`);

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
-- Indexes for table `trangsuc`
--
ALTER TABLE `trangsuc`
  ADD PRIMARY KEY (`matrangsuc`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anh_dongho`
--
ALTER TABLE `anh_dongho`
  MODIFY `maanh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `butky`
--
ALTER TABLE `butky`
  MODIFY `mabutky` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `machitietdonhang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `dongho`
--
ALTER TABLE `dongho`
  MODIFY `madongho` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
  MODIFY `madonhang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `kinhmat`
--
ALTER TABLE `kinhmat`
  MODIFY `makinhmat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `phukien`
--
ALTER TABLE `phukien`
  MODIFY `maphukien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `thongke`
--
ALTER TABLE `thongke`
  MODIFY `mathongke` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `trangsuc`
--
ALTER TABLE `trangsuc`
  MODIFY `matrangsuc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `anh_butky`
--
ALTER TABLE `anh_butky`
  ADD CONSTRAINT `anh_butky_ibfk_1` FOREIGN KEY (`mabutky`) REFERENCES `butky` (`mabutky`),
  ADD CONSTRAINT `fk_anh_butky_butky` FOREIGN KEY (`mabutky`) REFERENCES `butky` (`mabutky`) ON DELETE CASCADE;

--
-- Constraints for table `anh_dongho`
--
ALTER TABLE `anh_dongho`
  ADD CONSTRAINT `Fk_madongho` FOREIGN KEY (`madongho`) REFERENCES `dongho` (`madongho`),
  ADD CONSTRAINT `fk_anh_dongho_dongho` FOREIGN KEY (`madongho`) REFERENCES `dongho` (`madongho`) ON DELETE CASCADE;

--
-- Constraints for table `anh_kinhmat`
--
ALTER TABLE `anh_kinhmat`
  ADD CONSTRAINT `anh_kinhmat_ibfk_1` FOREIGN KEY (`makinhmat`) REFERENCES `kinhmat` (`makinhmat`),
  ADD CONSTRAINT `fk_anh_kinhmat_kinhmat` FOREIGN KEY (`makinhmat`) REFERENCES `kinhmat` (`makinhmat`) ON DELETE CASCADE;

--
-- Constraints for table `anh_phukien`
--
ALTER TABLE `anh_phukien`
  ADD CONSTRAINT `anh_phukien_ibfk_1` FOREIGN KEY (`maphukien`) REFERENCES `phukien` (`maphukien`),
  ADD CONSTRAINT `fk_anh_phukien_phukien` FOREIGN KEY (`maphukien`) REFERENCES `phukien` (`maphukien`) ON DELETE CASCADE;

--
-- Constraints for table `anh_trangsuc`
--
ALTER TABLE `anh_trangsuc`
  ADD CONSTRAINT `anh_trangsuc_ibfk_1` FOREIGN KEY (`matrangsuc`) REFERENCES `trangsuc` (`matrangsuc`),
  ADD CONSTRAINT `fk_anh_trangsuc_trangsuc` FOREIGN KEY (`matrangsuc`) REFERENCES `trangsuc` (`matrangsuc`) ON DELETE CASCADE;

--
-- Constraints for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD CONSTRAINT `chitietdonhang_ibfk_1` FOREIGN KEY (`madonhang`) REFERENCES `donhang` (`madonhang`);

--
-- Constraints for table `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `donhang_ibfk_1` FOREIGN KEY (`username`) REFERENCES `taikhoan` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
