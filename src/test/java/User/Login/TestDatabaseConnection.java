package User.Login;

import org.testng.annotations.Test;
import Utils.DatabaseUtils;
import java.sql.SQLException;
import java.util.List;

public class TestDatabaseConnection {
    
    @Test
    public void testDatabaseConnection() {
        try {
            // Test 1: Kiểm tra kết nối và thực thi query đơn giản
            System.out.println("Test 1: Kiểm tra kết nối database");
            DatabaseUtils.executeQuery("SELECT 1");
            System.out.println("Kết nối database thành công!");
            
            // Test 2: Lấy danh sách tên bảng trong database
            System.out.println("\nTest 2: Lấy danh sách tên bảng");
            List<String> tables = DatabaseUtils.getColumnValues(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE'",
                "TABLE_NAME"
            );
            System.out.println("Danh sách các bảng trong database:");
            tables.forEach(System.out::println);
            
            // Test 3: Lấy thông tin về cấu trúc của một bảng (ví dụ: Users)
            System.out.println("\nTest 3: Lấy thông tin cấu trúc bảng Users");
            List<String> columns = DatabaseUtils.getColumnValues(
                "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'Users'",
                "COLUMN_NAME"
            );
            System.out.println("Danh sách các cột trong bảng Users:");
            columns.forEach(System.out::println);
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi kết nối database: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Đóng kết nối sau khi hoàn thành
            DatabaseUtils.closeConnection();
        }
    }
    
    @Test
    public void testUserData() {
        try {
            // Test 4: Kiểm tra dữ liệu trong bảng Users
            System.out.println("\nTest 4: Kiểm tra dữ liệu trong bảng Users");
            List<String> users = DatabaseUtils.getColumnValues(
                "SELECT TOP 5 * FROM Users",
                "Username"
            );
            System.out.println("Danh sách 5 user đầu tiên:");
            users.forEach(System.out::println);
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeConnection();
        }
    }
} 