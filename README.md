https://github.com/TungSyo/Automation_AFF.git
# **🔹 1. Đảm bảo Selenium Hub đang chạy**
Trước khi thêm node, hãy kiểm tra Hub đã hoạt động chưa:

docker ps
// Container độc lập
docker run -d -p 4444:4444 --name chrome-v131 selenium/standalone-chrome:131.0.6778.264-20250222

-- Tạo mạng Grid
docker network create selenium-grid
```

## *1.3 Kiểm tra lại các node*
Sau khi chạy xong, kiểm tra bằng:
```bash
docker ps
D:\Application\Docker
cd "D:\Zalo\DATN\Automation_AFF"
--- Build Container bằng dockerfile
docker build -t windows-container .
move "C:\ProgramData\DockerDesktop" "D:\Application\Docker_Image"
mklink /J "C:\ProgramData\DockerDesktop" "D:\Application\Docker_Image"
mkdir D:\Application\Docker_Image

# Xây dựng Docker image
docker build -t my-angular-app .

# Chạy container từ image
docker run -p 4200:4200 my-angular-app

```

# **🔹 2. Triển khai CI/CD**

## *2.1 Sử dụng Docker để tự động hóa môi trường*

- **Dockerfile cho Selenium + Maven:**
  ```dockerfile
  FROM maven:3.8.6-openjdk-11
  WORKDIR /app
  COPY . .
  RUN mvn clean install
  CMD ["mvn", "test"]
  ```

# **🔹3. Cấu hình GitHub Actions - Phải set up thư mục nhận chrome đúng với thư mục Driver của CODE**

# **🔹4. Upload report lên artifact thay vì in log**
```yaml
- name: Upload Test Report
  uses: actions/upload-artifact@v3
  with:
    name: Test-Report
    path: test-output/ExtentReport.html
```

---
# **🔹5. Kết luần: Tối ưu CI/CD Selenium**

✔ ~~Chạy headless để tiết kiệm tài nguyên~~

✔ ~~Giới hạn thread-count trong TestNG để giảm tải CPU~~

✔ **Dùng Selenium Noid hoặc Docker để chạy test trên cloud - Cần tìm hiểu thêm**

✔ ~~Tối ưu Extent Report tránh leak bộ nhớ~~

✔ **Upload báo cáo test thay vì in log terminal - Cài đặt log báo cáo CI/CD**
---

## 6. **Tích hợp Selenoid với Automation Testing**

Sau khi thiết lập Selenoid trên Docker, bạn có thể chạy test automation với Selenium bằng cách cấu hình **Remote WebDriver**.

📌 **Ví dụ code Java (Selenium Test chạy trên Selenoid)**:

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class SelenoidTest {
    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");

        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        driver.get("https://www.google.com");
        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}
```
docker stop my-container
docker stop $(docker ps -q)
docker rm my-container
docker rm -f $(docker ps -aq)
