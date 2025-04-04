package edu.it10.vuquangdung.spring.helper;
import java.util.HashMap;
import java.util.Map;

public class ParameterNameMapper {

    private static final Map<String, String> PARAMETER_NAME_MAP = new HashMap<>();

    static {
        PARAMETER_NAME_MAP.put("soluong", "Số lượng");
        PARAMETER_NAME_MAP.put("masanpham", "Mã sản phẩm");
        PARAMETER_NAME_MAP.put("id", "Mã sản phẩm");
        PARAMETER_NAME_MAP.put("giatien", "Giá tiền");
        PARAMETER_NAME_MAP.put("soluongdatmua", "Số lượng đặt mua");

        PARAMETER_NAME_MAP.put("ten", "Tên");
        PARAMETER_NAME_MAP.put("thuonghieu", "Thương hiệu");
        PARAMETER_NAME_MAP.put("bienthe", "Biến thể");
        PARAMETER_NAME_MAP.put("tendongho", "Tên đồng hồ");
        PARAMETER_NAME_MAP.put("tenkinhmat", "Tên kính mắt");
        PARAMETER_NAME_MAP.put("tentrangsuc", "Tên trang sức");
        PARAMETER_NAME_MAP.put("tenphukien", "Tên phụ kiện");

        PARAMETER_NAME_MAP.put("madonhang", "Mã đơn hàng");
        PARAMETER_NAME_MAP.put("madongho", "Mã đồng hồ");
        PARAMETER_NAME_MAP.put("makinhmat", "Mã kính mắt");
        PARAMETER_NAME_MAP.put("matrangsuc", "Mã trang sức");
        PARAMETER_NAME_MAP.put("maphukien", "Mã phụ kiện");

        PARAMETER_NAME_MAP.put("username", "Email");
        PARAMETER_NAME_MAP.put("hoten", "Họ tên");
        PARAMETER_NAME_MAP.put("sodienthoai", "Số điện thoại");
        PARAMETER_NAME_MAP.put("loai_tai_khoan", "Loại tài khoản");
        PARAMETER_NAME_MAP.put("loaitaikhoan", "Loại tài khoản");
        PARAMETER_NAME_MAP.put("diachi", "Địa chỉ");

        PARAMETER_NAME_MAP.put("kichhoat", "Kích hoạt");
        PARAMETER_NAME_MAP.put("tragop", "Trả góp");
        PARAMETER_NAME_MAP.put("duongkinh", "Đường kính");
        PARAMETER_NAME_MAP.put("chongnuoc", "Chống nước");
        PARAMETER_NAME_MAP.put("bomay", "Bộ máy");
        PARAMETER_NAME_MAP.put("ngaythem", "Ngày thêm");
        PARAMETER_NAME_MAP.put("thongtin", "Thông tin");
        PARAMETER_NAME_MAP.put("thongtinnhap", "Thông tin nhập");
        PARAMETER_NAME_MAP.put("thongtinxuat", "Thông tin xuất");
        PARAMETER_NAME_MAP.put("gioitinh", "Giới tính");
        PARAMETER_NAME_MAP.put("chatlieu", "Chất liệu");

        PARAMETER_NAME_MAP.put("password", "Mật khẩu");
        PARAMETER_NAME_MAP.put("newpassword", "Mật khẩu mới");
        PARAMETER_NAME_MAP.put("hanhdong", "Hành động");
        PARAMETER_NAME_MAP.put("thoigian", "Thời gian");
        PARAMETER_NAME_MAP.put("nguoithuchien", "Người thực hiện");
        PARAMETER_NAME_MAP.put("chiphi", "Chi phí");
    }

    public static String getFriendlyName(String parameterName) {
        return PARAMETER_NAME_MAP.getOrDefault(parameterName.toLowerCase(), "Các trường"); // Mặc định trả về tên gốc nếu không có ánh xạ
    }
}
