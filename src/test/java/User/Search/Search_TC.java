// package User.Search;

// import java.io.IOException;
// import java.net.URISyntaxException;
// import java.time.Duration;
// import java.util.List;
// import java.util.Random;
// import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import org.testng.annotations.Test;
// import Report.Extend_Report;
// import Base.Base_Page;
// import Base.Base_Test;
// import Driver.Driver_Manager;
// import Utils.ConfigUtil;

// public class User_Product_Test extends Base_Test {
//     private Base_Page basePage;
//     private WebDriverWait wait;
//     private WebDriver driver;

//     @Test(groups = "Success")
//     public void testLogin() throws URISyntaxException, IOException {
//         try {
//             driver = Driver_Manager.getDriver();
//             wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//             basePage = new Base_Page(driver);

//             Extend_Report.startTest("Kiểm tra chi tiết sản phẩm", "Search_Product_Data_Pass");

//             String url_user = ConfigUtil.getProperty("url_user");
//             if (url_user == null || url_user.trim().isEmpty()) {
//                 throw new IllegalArgumentException("Cấu hình 'url_user' không được tìm thấy trong file config.properties");
//             }

//             Extend_Report.logInfo("Truy cập vào trang web: " + url_user);
//             driver.get(url_user);
//             driver.manage().window().maximize();

//             JavascriptExecutor js = (JavascriptExecutor) driver;
//             js.executeScript("document.body.style.zoom='80%'");

//             Extend_Report.logInfo("Nhấn vào liên kết sản phẩm");
//             basePage.linkProduct.click();

//             checkProductList(driver, wait);
//             checkProductDetails(driver, wait);

//             Extend_Report.logPass("Kiểm tra chi tiết sản phẩm thành công.");
//         } catch (Exception e) {
//             Extend_Report.logFail("Lỗi trong quá trình kiểm tra: " + e.getMessage());
//             throw e; 
//         } finally {
//             Extend_Report.endReport();
//             Extend_Report.endTest();
//         }
//     }

//     private void checkProductList(WebDriver driver, WebDriverWait wait) {
//         try {
//             List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//                     By.xpath("//a[contains(@href, '/productdetail')]/div[@class='position-relative']")));

//             if (products.isEmpty()) {
//                 throw new Exception("Danh sách sản phẩm rỗng.");
//             }

//             Random random = new Random();
//             WebElement randomProduct = products.get(random.nextInt(products.size()));
//             String productName = randomProduct.getText().replaceAll("\\d.*", "").trim();

//             Extend_Report.logInfo("Nhấn vào sản phẩm: " + productName);
//             randomProduct.click();
//             Extend_Report.logPass("Đã click vào sản phẩm: " + productName);
//         } catch (Exception e) {
//             Extend_Report.logFail("Không tìm thấy sản phẩm: " + e.getMessage());
//         }
//     }

//     private void checkProductDetails(WebDriver driver, WebDriverWait wait) {
//         try {
//             String productLink = driver.getCurrentUrl();
//             Extend_Report.logInfo("Link sản phẩm chi tiết: " + productLink);

//             WebElement productDescriptionElement = wait.until(ExpectedConditions.presenceOfElementLocated(
//                     By.xpath("//h3[.='Mô tả chi tiết']")));
//             String productDescription = productDescriptionElement != null && !productDescriptionElement.getText().isEmpty()
//                     ? productDescriptionElement.getText()
//                     : "Mô tả không có hoặc không đầy đủ.";

//             Extend_Report.logInfo("Mô tả sản phẩm: " + productDescription);
//             System.out.println("Mô tả sản phẩm: " + productDescription);
//         } catch (Exception e) {
//             Extend_Report.logFail("Không tìm thấy mô tả chi tiết sản phẩm: " + e.getMessage());
//         }
//     }
// }