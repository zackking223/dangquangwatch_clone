- Refactor: Thêm Pageable vào tất cả các đối tượng
  1. Sửa Repository
  2. Sửa Service
  3. Sửa ServiceImpl
  4. Sửa Controller
  5. Sửa trang index.html: Thêm form tìm kiếm, thêm chỗ chọn trang
- Code Service cho ChiTietDonHang.java
- Code UI: 
  frontend: dongho, kinhmat, phukien, trang suc, cart, checkout
  backend: admin, khachhang, orders, thongke
- Code backend: Upload images, Payment processing

Để tải ảnh lên Cloudinary từ một form trong Thymeleaf và nhận lại đường dẫn công cộng của ảnh vừa đăng trong ứng dụng Spring Boot, bạn cần thực hiện các bước sau:

1. **Thiết lập Cloudinary**.
2. **Cấu hình Spring Boot để sử dụng Cloudinary**.
3. **Tạo một form Thymeleaf để tải ảnh**.
4. **Tạo controller để xử lý việc tải ảnh**.
5. **Nhận và xử lý kết quả từ Cloudinary**.

### Bước 1: Thiết lập Cloudinary

Đầu tiên, bạn cần tạo một tài khoản Cloudinary và nhận các thông tin cấu hình như `cloud_name`, `api_key`, và `api_secret`.

### Bước 2: Cấu hình Spring Boot để sử dụng Cloudinary

**Thêm dependencies vào `pom.xml`**:

```xml
<dependency>
    <groupId>com.cloudinary</groupId>
    <artifactId>cloudinary-http44</artifactId>
    <version>1.29.0</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

**Cấu hình Cloudinary trong application.properties**:

```properties
cloudinary.cloud_name=your_cloud_name
cloudinary.api_key=your_api_key
cloudinary.api_secret=your_api_secret
```

**Tạo một lớp cấu hình Cloudinary**:

```java
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }
}
```

### Bước 3: Tạo một form Thymeleaf để tải ảnh

**form.html**:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Upload Image</title>
</head>
<body>
    <h1>Upload Image</h1>
    <form action="#" th:action="@{/upload}" th:object="${imageForm}" method="post" enctype="multipart/form-data">
        <input type="file" th:field="*{file}" />
        <button type="submit">Upload</button>
    </form>

    <div th:if="${imageUrl != null}">
        <h2>Uploaded Image</h2>
        <img th:src="${imageUrl}" alt="Uploaded Image"/>
        <p>URL: <a th:href="${imageUrl}" th:text="${imageUrl}"></a></p>
    </div>
</body>
</html>
```

**Tạo một lớp ImageForm**:

```java
import org.springframework.web.multipart.MultipartFile;

public class ImageForm {
    private MultipartFile file;

    // getters and setters
}
```

### Bước 4: Tạo controller để xử lý việc tải ảnh

**ImageUploadController.java**:

```java
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class ImageUploadController {

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("imageForm", new ImageForm());
        return "form";
    }

    @PostMapping("/upload")
    public String uploadImage(@ModelAttribute ImageForm imageForm, Model model) {
        MultipartFile file = imageForm.getFile();
        if (file != null && !file.isEmpty()) {
            try {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String imageUrl = uploadResult.get("secure_url").toString();
                model.addAttribute("imageUrl", imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception accordingly
            }
        }
        return "form";
    }
}
```

### Bước 5: Chạy ứng dụng và kiểm tra

- Chạy ứng dụng Spring Boot của bạn.
- Mở trình duyệt và truy cập vào đường dẫn `/upload`.
- Tải lên một file ảnh và kiểm tra xem ảnh có được tải lên Cloudinary và hiển thị đường dẫn công cộng không.

Với các bước trên, bạn đã có thể tải ảnh lên Cloudinary từ một form trong Thymeleaf và nhận lại đường dẫn công cộng của ảnh vừa tải lên.