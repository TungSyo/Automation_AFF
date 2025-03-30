package Utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConfig {
    private static HikariDataSource dataSource;
    
    static {
        try {
            HikariConfig config = new HikariConfig();
            // Thay đổi host.docker.internal thành localhost nếu chạy trực tiếp trên máy
            config.setJdbcUrl("jdbc:sqlserver://localhost:1433;databaseName=AFF-UAT;encrypt=true;trustServerCertificate=true");
            config.setUsername("sa");
            config.setPassword("Tungtet2003");
            
            // Thêm các cấu hình timeout và retry
            config.setConnectionTimeout(30000); // 30 seconds
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(5);
            config.setIdleTimeout(300000); // 5 minutes
            config.setMaxLifetime(1200000); // 20 minutes
            
            // Thêm các thông số kết nối
            config.addDataSourceProperty("applicationName", "AFF-Automation");
            config.addDataSourceProperty("loginTimeout", "30");
            
            dataSource = new HikariDataSource(config);
            
            // Test kết nối ngay khi khởi tạo
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("Kết nối database thành công!");
            }
            
        } catch (Exception e) {
            System.err.println("Lỗi khởi tạo kết nối database: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Không thể khởi tạo kết nối database", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("DataSource chưa được khởi tạo");
        }
        return dataSource.getConnection();
    }
    
    public static void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
            dataSource = null;
        }
    }
} 