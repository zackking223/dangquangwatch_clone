Để xóa hết các stored procedure và trigger trong MySQL, bạn có thể thực hiện các bước sau:

### 1. Xóa Stored Procedure

Để xóa tất cả các stored procedure, bạn cần sử dụng câu lệnh `DROP PROCEDURE`. Bạn có thể liệt kê các stored procedure hiện có bằng cách truy vấn `information_schema` và sau đó sử dụng các câu lệnh `DROP PROCEDURE` để xóa từng procedure.

### 2. Xóa Trigger

Tương tự như stored procedure, bạn có thể liệt kê các trigger hiện có bằng cách truy vấn `information_schema` và sau đó sử dụng câu lệnh `DROP TRIGGER` để xóa từng trigger.

Dưới đây là các bước chi tiết để xóa tất cả các stored procedure và trigger.

### Xóa Stored Procedure

1. **Liệt kê tất cả các stored procedure**:
   ```sql
   SELECT ROUTINE_NAME 
   FROM INFORMATION_SCHEMA.ROUTINES 
   WHERE ROUTINE_TYPE='PROCEDURE' AND ROUTINE_SCHEMA='your_database_name';
   ```

2. **Xóa tất cả các stored procedure**:
   Giả sử bạn đã có danh sách tên các stored procedure từ truy vấn trên, bạn có thể tạo và thực thi các câu lệnh `DROP PROCEDURE` cho mỗi procedure:
   ```sql
   DROP PROCEDURE IF EXISTS UpdateThongKeOnInsert;
   -- Thêm các stored procedure khác vào đây
   ```

### Xóa Trigger

1. **Liệt kê tất cả các trigger**:
   ```sql
   SELECT TRIGGER_NAME 
   FROM INFORMATION_SCHEMA.TRIGGERS 
   WHERE TRIGGER_SCHEMA='your_database_name';
   ```

2. **Xóa tất cả các trigger**:
   Giả sử bạn đã có danh sách tên các trigger từ truy vấn trên, bạn có thể tạo và thực thi các câu lệnh `DROP TRIGGER` cho mỗi trigger:
   ```sql
   DROP TRIGGER IF EXISTS dongho_after_insert;
   DROP TRIGGER IF EXISTS kinhmat_after_insert;
   DROP TRIGGER IF EXISTS phukien_after_insert;
   DROP TRIGGER IF EXISTS trangsuc_after_insert;
   DROP TRIGGER IF EXISTS butky_after_insert;
   DROP TRIGGER IF EXISTS donhang_after_xacnhan;
   DROP TRIGGER IF EXISTS donhang_after_nhan;
   DROP TRIGGER IF EXISTS donhang_after_giao;
   DROP TRIGGER IF EXISTS donhang_after_huy;
   -- Thêm các trigger khác vào đây
   ```

### Ví dụ cụ thể

#### Liệt kê và xóa stored procedure
```sql
-- Liệt kê các stored procedure trong cơ sở dữ liệu
SELECT CONCAT('DROP PROCEDURE IF EXISTS ', ROUTINE_NAME, ';') 
FROM INFORMATION_SCHEMA.ROUTINES 
WHERE ROUTINE_TYPE='PROCEDURE' AND ROUTINE_SCHEMA='dongho_dangquang';
```

Sau khi bạn có kết quả từ truy vấn trên, bạn có thể sao chép và dán kết quả để thực thi chúng và xóa các stored procedure.

#### Liệt kê và xóa trigger
```sql
-- Liệt kê các trigger trong cơ sở dữ liệu
SELECT CONCAT('DROP TRIGGER IF EXISTS ', TRIGGER_NAME, ';') 
FROM INFORMATION_SCHEMA.TRIGGERS 
WHERE TRIGGER_SCHEMA='dongho_dangquang';
```

Tương tự, sau khi có kết quả từ truy vấn trên, bạn sao chép và dán kết quả để thực thi chúng và xóa các trigger.

### Kết luận

Sử dụng các câu lệnh SQL trên, bạn có thể liệt kê và xóa tất cả các stored procedure và trigger trong cơ sở dữ liệu MySQL của mình. Đảm bảo thay thế `'dongho_dangquang'` bằng tên cơ sở dữ liệu của bạn.