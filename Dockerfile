# Sử dụng base image phù hợp (ví dụ: Ubuntu)
FROM ubuntu:22.04

# Chuyển sang user root để thực hiện cài đặt
USER root

# Tạo thư mục chứa Chrome & ChromeDriver
RUN mkdir -p /opt/google/chrome/

# Copy Chrome Portable (ZIP) & ChromeDriver vào container
COPY Driver/GoogleChromePortable.zip /opt/google/
COPY Driver/chromedriver.exe /usr/local/bin/chromedriver

# Cài đặt unzip, giải nén Chrome Portable và dọn dẹp cache
RUN apt-get update && \
    apt-get install -y unzip && \
    unzip /opt/google/GoogleChromePortable.zip -d /opt/google/chrome/ && \
    rm -rf /var/lib/apt/lists/* && \
    chmod +x /opt/google/chrome/GoogleChromePortable/App/Chrome-bin/chrome.exe && \
    chmod +x /usr/local/bin/chromedriver

# Tạo liên kết symbolic cho Google Chrome
RUN ln -sf /opt/google/chrome/GoogleChromePortable/App/Chrome-bin/chrome.exe /usr/bin/google-chrome

# Kiểm tra quyền và giải nén thành công
RUN test -f /usr/bin/google-chrome && \
    test -f /opt/google/chrome/GoogleChromePortable/App/Chrome-bin/chrome.exe && \
    echo "Chrome and Chromedriver setup successfully!"

# Chuyển sang user 1200
USER 1200

# Đặt working directory (tùy chọn)
WORKDIR /app

# Kết thúc
CMD ["echo", "Container is ready!"]