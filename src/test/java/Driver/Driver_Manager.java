package Driver;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("unused")
public class Driver_Manager {
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> threadLocalWait = new ThreadLocal<>();

    public static void initDriver(Browser_Type browser) {
        WebDriver driver;
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().driverVersion("131.0.6778.86").setup();
                // System.setProperty("webdriver.chrome.driver", "C:\\Users\\dactu\\.cache\\selenium\\chromedriver\\win64\\131.0.6778.86\\chromedriver.exe");

                ChromeOptions chromeOptions = new ChromeOptions();

                chromeOptions.setBinary("D:\\Tài xuống\\GoogleChromePortable\\App\\Chrome-bin\\chrome.exe");

                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--disable-extenstions");
                chromeOptions.addArguments("--disable-build-check");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOptions);
                break;
    
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();

                edgeOptions.addArguments("--headless=new");
                edgeOptions.addArguments("--disable-gpu");
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--disable-dev-shm-usage");
                driver = new EdgeDriver(edgeOptions);
                break;
    
            case FIREFOX:
                WebDriverManager.firefoxdriver().clearDriverCache();
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
    
            default:
                throw new IllegalArgumentException("Trình duyệt không được hỗ trợ: " + browser);
        }
    
        driver.manage().window().maximize();  // Tự động tối đa hóa cửa sổ khi khởi tạo
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    
        threadLocalDriver.set(driver);
        threadLocalWait.set(new WebDriverWait(driver, Duration.ofSeconds(10))); // Khởi tạo WebDriverWait

    }
    

    public static WebDriver getDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver chưa được khởi tạo. Gọi initDriver() trước.");
        }
        return driver;
    }


    public static WebDriverWait getWait() {
        WebDriverWait wait = threadLocalWait.get();
        if (wait == null) {
            throw new IllegalStateException("WebDriverWait chưa được khởi tạo. Gọi initDriver() trước.");
        }
        return wait;
    }
    
    public static void quitDriver() {
        WebDriver driverInstance = threadLocalDriver.get();
        if (driverInstance != null) {
            driverInstance.quit();
            threadLocalDriver.remove();
            System.out.println("✅ Đã đóng trình duyệt.");
        } else {
            System.out.println("🚫 Trình duyệt không tồn tại hoặc đã được đóng.");
        }
    }
    

    public static void quitAllDrivers() {
        WebDriver driver = threadLocalDriver.get();
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("✅ Đã đóng tất cả trình duyệt.");
        }
    }

}