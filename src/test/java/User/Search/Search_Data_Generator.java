package User.Search;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Search_Data_Generator {
    private static final Faker faker = new Faker(new Locale("vi"));

    // Danh sách các sản phẩm có thật trong hệ thống
    private static final List<String> VALID_PRODUCTS = Arrays.asList(
        "Sữa hạt khỏe",
        "Sữa hạt tươi"
    );

    // Sinh kết quả mong đợi dựa trên từ khóa tìm kiếm
    private static String[] generateExpectedResults(String searchTerm) {
        String result, title, link;
        
        // Xử lý null hoặc chuỗi rỗng
        if (searchTerm == null || searchTerm.equals("")) {
            result = "Tất cả sản phẩm";
            title = "Honivy";
            link = "http://localhost:4200/productsearch";
            return new String[]{result, title, link};
        }
        
        String trimmedSearch = searchTerm.trim();
        // Xử lý các trường hợp còn lại
        if (VALID_PRODUCTS.stream().anyMatch(p -> p.toLowerCase().contains(trimmedSearch.toLowerCase()))) {
            result = trimmedSearch;  
            title = "Honivy";
        } else {
            result = "Tất cả sản phẩm";
            title = "Honivy";
        }

        try {
            String encodedSearch = URLEncoder.encode(trimmedSearch, StandardCharsets.UTF_8.toString());
            link = "http://localhost:4200/productsearch?KeyWord=" + encodedSearch;
        } catch (Exception e) {
            link = "http://localhost:4200/productsearch?KeyWord=" + trimmedSearch.replace(" ", "%20");
        }
        
        return new String[]{result, title, link};
    }

    public static Object[][] generateSearchData() {
        List<Object[]> testData = new ArrayList<>();
        
        // Case 1: Tìm kiếm với tên đầy đủ sản phẩm
        String fullNameSearch = VALID_PRODUCTS.get(0);
        addTestCase(testData, fullNameSearch, true);
        
        // Case 2: Tìm kiếm với một phần tên sản phẩm
        String partialNameSearch = VALID_PRODUCTS.get(0).split(" ")[0];
        addTestCase(testData, partialNameSearch, true);
        
        // Case 3: Tìm kiếm với khoảng trắng thừa
        String whiteSpaceSearch = "  " + VALID_PRODUCTS.get(0) + "  ";
        addTestCase(testData, whiteSpaceSearch, true);
        
        // Case 4: Tìm kiếm với chuỗi ngẫu nhiên
        addTestCase(testData, faker.lorem().word(), false);
        
        // Case 5: Tìm kiếm với số
        addTestCase(testData, String.valueOf(faker.number().numberBetween(1000, 999999)), false);
        
        // Case 6: Tìm kiếm với ký tự đặc biệt
        addTestCase(testData, "@#$%^&*()", false);
        
        // Case 7: Tìm kiếm với kết hợp text và ký tự đặc biệt
        addTestCase(testData, faker.lorem().word() + "@#$" + faker.number().digit(), false);
        
        // Case 8: Tìm kiếm với chuỗi rỗng
        addTestCase(testData, "     ", true);
        
        // Case 9: Tìm kiếm với null
        addTestCase(testData, "", true);
        
        return testData.toArray(new Object[0][]);
    }
    
    private static void addTestCase(List<Object[]> testData, String searchTerm, boolean isValid) {
        String testType = isValid ? "Success" : "Fail";
        String description = isValid ? 
            "Tìm kiếm hợp lệ: " + searchTerm :
            "Tìm kiếm không hợp lệ: " + searchTerm;
            
        String[] expectedResults = generateExpectedResults(searchTerm);
        
        testData.add(new Object[]{
            searchTerm,
            expectedResults[0], 
            expectedResults[1],
            expectedResults[2], 
            description,
            testType
        });
    }
}