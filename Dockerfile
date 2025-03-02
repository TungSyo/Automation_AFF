# Sử dụng base image Ubuntu phiên bản mới hơn
FROM ubuntu:24.04

# Set biến môi trường
ENV CHROME_PATH="/opt/google/chrome/GoogleChromePortable/App/Chrome-bin/chrome.exe"
ENV CHROMEDRIVER_PATH="/usr/local/bin/chromedriver"

# Cài đặt phụ thuộc và thiết lập môi trường
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    unzip \
    libx11-6 \
    libxcb1 \
    libxcomposite1 \
    libxcursor1 \
    libxdamage1 \
    libxext6 \
    libxfixes3 \
    libxi6 \
    libxrandr2 \
    libxrender1 \
    libxss1 \
    libxtst6 \
    ca-certificates \
    fonts-liberation \
    libappindicator1 \
    libnspr4 \
    libnss3 \
    xvfb \
    && rm -rf /var/lib/apt/lists/*

# Copy và xử lý file
COPY Driver/GoogleChromePortable.zip /opt/google/
COPY Driver/chromedriver.exe ${CHROMEDRIVER_PATH}

# Giải nén và thiết lập quyền
RUN unzip /opt/google/GoogleChromePortable.zip -d /opt/google/chrome/ && \
    chmod -R 755 /opt/google/chrome && \
    chmod +x ${CHROME_PATH} && \
    chmod +x ${CHROMEDRIVER_PATH} && \
    ln -sf ${CHROME_PATH} /usr/bin/google-chrome

# Tạo user chuyên dụng thay vì dùng UID trực tiếp
RUN groupadd -r chromeuser && \
    useradd -r -g chromeuser -u 1200 chromeuser && \
    mkdir -p /app && \
    chown -R chromeuser:chromeuser /opt/google /app

USER chromeuser
WORKDIR /app

# Health check
HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
    CMD [ -x "${CHROME_PATH}" ] && [ -x "${CHROMEDRIVER_PATH}" ] || exit 1

# Cleanup
RUN rm -f /opt/google/GoogleChromePortable.zip

CMD ["echo", "Container is ready!"]
