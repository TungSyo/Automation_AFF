package User.Register;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import User.Register.Register_Page;
import Base.*;
import User.Login.User_Login_Page;
import Utils.Email_Reader;

@SuppressWarnings("unused")
public class Register_Action {
    private WebDriver driver;
    private Register_Page registerPage;
    private Base_Page basePage;
    private Base_Test baseTest;
    private Base_Action baseAction;
    private User_Login_Page loginPage;
    public boolean shouldLoginGoogle = false;

    public Register_Action(WebDriver driver) {
        this.driver = driver;
        this.registerPage = new Register_Page(driver);
        this.basePage = new Base_Page(driver);
        this.baseTest = new Base_Test();
        this.loginPage = new User_Login_Page(driver);
    }

    public void setShouldLoginGoogle(boolean value) {
        this.shouldLoginGoogle = value;
    }

    public boolean getShouldLoginGoogle() {
        return shouldLoginGoogle;
    }

    public void clickTaotk() {
        clickButton(registerPage.getBtnTaotk());
        if (isTextPresent("XÁC THỰC OTP")) {
            setShouldLoginGoogle(true);
        } else {
            System.out.println("🚀 Không cần đăng nhập Google. Tiếp tục bước tiếp theo.");
        }
    }

    public void clickAccount() {
        clickButton(basePage.getBtnAccount());
        baseAction.sleep(500);

        try {
            if (basePage.getBtnLogout().isDisplayed()) {
                clickButton(basePage.getBtnLogout());
                baseAction.sleep(5000);
            }
        } catch (Exception e) {
        }
    }

    public void enterOTP(String otp) {
        clickButton(registerPage.getTxtOtp());

        List<WebElement> otpInputs = driver.findElements(By.xpath("//div[@class='otp-inputs']/input"));
        int length = Math.min(otp.length(), otpInputs.size());

        for (int i = 0; i < length; i++) {
            otpInputs.get(i).sendKeys(String.valueOf(otp.charAt(i)));
        }
    }

    public void enterText(WebElement element, String text) {
        baseAction.clearAndEnterText(element, text);
    }

    public void clickButton(WebElement element) {
        baseAction.clickElement(element);
    }

    public void register(String name, String sdt, String email, String cmnd, String pass, String mgt,
            String city, String district, String ward, String location, String mst,
            String date, String bank, String stk, String result, String pop3) {
        clickAccount();
        clickButton(basePage.getLinkRegister());
        baseAction.sleep(1000);
        enterText(registerPage.getTxtName(), name);
        enterText(registerPage.getTxtSdt(), sdt);
        enterText(registerPage.getTxtEmail(), email);
        enterText(registerPage.getTxtCMND(), cmnd);
        enterText(registerPage.getTxtPass(), pass);
        enterText(registerPage.getTxtMgt(), mgt);
        enterText(registerPage.getTxtCity(), city);
        enterText(registerPage.getTxtDistrict(), district);
        enterText(registerPage.getTxtWard(), ward);
        enterText(registerPage.getTxtLocation(), location);
        enterText(registerPage.getTxtMst(), mst);
        enterText(registerPage.getTxtDate(), date);
        enterText(registerPage.getTxtBank(), bank);
        enterText(registerPage.getTxtStk(), stk);
        clickButton(registerPage.getCbDongy());

        clickTaotk();
        if (shouldLoginGoogle) {
            baseAction.sleep(3000);

            String host = "pop.gmail.com";
            String username = email;
            String password = pop3;
            System.err.println("Email: " + email + ", POP3 password: " + pop3);

            try {
                System.out.println("📨 Đang kiểm tra hộp thư...");

                String otp = Email_Reader.readLatestEmail(host, username, password);
                if (otp != null && !otp.isEmpty()) {
                    System.out.println("✅ OTP tìm thấy: " + otp);
                    enterOTP(otp);
                    clickButton(registerPage.getBtnXacnhan());
                } else {
                    System.err.println("⚠️ Không lấy được OTP!");
                }

            } catch (Exception e) {
                System.err.println("❌ Lỗi khi đọc email qua POP: " + e.getMessage());
            }
        }

    }

    public boolean isTextPresent(String expectedText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Chờ tối đa 3 giây
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(text(),'" + expectedText + "')]")));
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println("⚠️ Không tìm thấy phần tử chứa: " + expectedText + ". Tiếp tục bước tiếp theo...");
            return false;
        }
    }
}
