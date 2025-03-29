// package Admin.Login;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.net.URLDecoder;
// import java.nio.charset.StandardCharsets;
// import java.time.Duration;
// import java.util.ArrayList;
// import java.util.List;
 
// import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.ss.usermodel.Sheet;
// import org.apache.poi.ss.usermodel.Workbook;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

// import Base.Base_Page;
// import Report.Extend_Report;

// public class Admin_Login_Action {
// 	public WebDriver driver;
// 	public Base_Page basePage;

// 	public Admin_Login_Action(WebDriver driver) {
// 		this.driver = driver;
// 		this.basePage = new Base_Page(driver);
// 	}

// 	public void enterUsername(String email) {
// 		basePage.txtAUser.clear();
// 		basePage.txtAUser.sendKeys(email);
//         Extend_Report.logInfo("Nhập email: " + email);
// 	}

// 	public void enterPassword(String password) {
// 		basePage.txtAPass.clear();
// 		basePage.txtAPass.sendKeys(password);
//         Extend_Report.logInfo("Nhập password: " + password);
// 	}

// 	public void clickLogin() {
// 		basePage.btnALogin.click();
//         Extend_Report.logInfo("Nhấn đăng nhập");
// 	}

// 	public void login(String email, String password) {
// 		enterUsername(email);
// 		enterPassword(password);
// 		clickLogin();
// 	}

// }
