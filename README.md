# Springboot - Đăng Quang Watch clone
### Tech:
- HTML/CSS/JS.
- Springboot v3.3.1.
  - Spring JPA.
  - Spring Web.
  - Spring Security.
- [Live reload with Tailwindcss](https://www.wimdeblauwe.com/blog/2022/08/27/thymeleaf-live-reload-with-spring-boot-and-tailwind-css/).
- [Cloudinary](https://console.cloudinary.com/).

### Database:
- Server type: MariaDB.
- Server version: 10.4.22-MariaDB - mariadb.org.
- Server charset: UTF-8 Unicode (utf8mb4).
- > .\database.sql

### Features:
- User interface:
  - Login & Regsiter.
  - View products.
  - Search products.
  - Place order.
  - Track order.
- Admin panel:
  - Crud operations.
    - Watch.
    - Jewellery.
    - Accessory.
    - Order.
    - Glass.
    - Pen.
  - Track orders.
  - Analytics.
- Database triggers & procedures.

### Run & Build:

1. Start the Spring Boot application with the local profile. `mvn spring-boot:run`
2. Run `npm run build && npm run watch` from the command line.
3. As a final change to the pom.xml, we can add a profile that calls our production NPM scripts. At release time, be sure to enable this Maven profile. `mvn spring-boot:run -P release`