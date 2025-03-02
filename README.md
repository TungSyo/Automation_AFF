https://github.com/TungSyo/Automation_AFF.git
# **🔹 1. Đảm bảo Selenium Hub đang chạy**
Trước khi thêm node, hãy kiểm tra Hub đã hoạt động chưa:

docker ps
docker rm -f selenium-hub
docker run -d -p 4444:4444 --network selenium-grid --name selenium-hub selenium/hub

## *1.1 Nếu chưa có chạy lại*
docker network create selenium-grid
docker run -d -p 4444:4444 --network selenium-grid --name selenium-hub selenium/hub

## *1.2 Chạy 3 node Chrome*
Sau khi Hub đã chạy, chạy các lệnh sau để tạo 3 node Chrome:
```bash
docker run -d --network selenium-grid --name chrome-1 \
  -e HUB_HOST=selenium-hub \
  -e SE_NODE_MAX_SESSIONS=3 \
  -e SE_NODE_OVERRIDE_MAX_SESSIONS=true \
  -e SE_OPTIONS="--headless" \
  selenium/node-chrome

docker run -d --network selenium-grid --name chrome-2 \
  -e HUB_HOST=selenium-hub \
  -e SE_NODE_MAX_SESSIONS=3 \
  -e SE_NODE_OVERRIDE_MAX_SESSIONS=true \
  -e SE_OPTIONS="--headless" \
  selenium/node-chrome

docker run -d --network selenium-grid --name chrome-3 \
  -e HUB_HOST=selenium-hub \
  -e SE_NODE_MAX_SESSIONS=3 \
  -e SE_NODE_OVERRIDE_MAX_SESSIONS=true \
  -e SE_OPTIONS="--headless" \
  selenium/node-chrome
```
## *1.3 Kiểm tra lại các node*
Sau khi chạy xong, kiểm tra bằng:
```bash
docker ps

cd "D:\Application\Docker Image"
--- Build Container bằng dockerfile

docker build -t custom-chrome-node .
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
