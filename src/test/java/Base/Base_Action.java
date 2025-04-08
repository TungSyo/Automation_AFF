package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Report.Extend_Report;
import Utils.ConfigUtil;
@SuppressWarnings("unused")
public class Base_Action {
     protected WebDriver driver;

     public Base_Action(WebDriver driver) {
          this.driver = driver;
     }

     public void sleep(int milliseconds) {
          try {
               Thread.sleep(milliseconds);
          } catch (InterruptedException e) {
               e.printStackTrace();
          }
     }

     public void handleVerification(boolean condition, String type, String value) {
          if (condition) {
               Extend_Report.logPass("Kiểm tra " + type + " thành công cho: " + value);
          } else {
               Extend_Report.logFail("Kiểm tra " + type + " thất bại cho: " + value);
          }
     }

     public void handleTestException(Exception e, String description) throws IOException {
          Extend_Report.logFail("Kiểm tra thất bại: " + description + " với lỗi: " + e.getMessage());
     }

     public void navigate(String url) {
          url = convertLocalhostLink(url);
          driver.get(url);
     }

     public void clickElement(WebElement element) {
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
          wait.until(ExpectedConditions.elementToBeClickable(element));
          sleep(300);
          element.click();
          sleep(500);
     }

     public void clearAndEnterText(WebElement element, String text) {
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

          try {
               wait.until(ExpectedConditions.visibilityOf(element));
               wait.until(ExpectedConditions.elementToBeClickable(element));

               if (element.isDisplayed() && element.isEnabled()) {
                    sleep(300);
                    element.click();
                    sleep(200);

                    element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                    sleep(200);

                    element.sendKeys(Keys.BACK_SPACE);
                    sleep(300);

                    // for (char c : text.toCharArray()) {
                    //      element.sendKeys(String.valueOf(c));
                    //      sleep(100 + (int) (Math.random() * 200));
                    // }
                    element.sendKeys(text);
                    sleep(500);
               }
          } catch (Exception e) {
               System.out.println("Không thể nhập text vào phần tử: " + e.getMessage());
          }
     }

     public String convertLocalhostLink(String expectedLink) {
          String environment = ConfigUtil.getProperty("environment", "TEST_ENV");

          if ((environment.equalsIgnoreCase("DOCKER") || environment.equalsIgnoreCase("LINUX"))
                    && expectedLink.contains("localhost")) {
               return expectedLink.replace("localhost", "host.docker.internal");
          }
          return expectedLink;
     }

     public boolean verifyNotion(String expectedText) {
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
          
          try {
               WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[normalize-space(text())='" + expectedText + "']")));
               if (element != null && element.isDisplayed()) {
                    return true;
               }
          } catch (Exception e) {
               try {
                    List<WebElement> allElements = wait.until(ExpectedConditions
                         .presenceOfAllElementsLocatedBy(By.xpath("//*[contains(text(), '" + expectedText + "')]")));
                    for (WebElement element : allElements) {
                         String elementText = element.getText().trim();
                         if (!elementText.isEmpty() && elementText.contains(expectedText)) {
                              return true;
                         }
                    }
               } catch (Exception ex) {
                    return false;
               }
          }
          return false;
     }

     public boolean verifyLink(String expectedLink) {
          String currentUrl = driver.getCurrentUrl();

          if (expectedLink.contains("host.docker.internal")) {
               expectedLink = expectedLink.replace("host.docker.internal", "localhost");
          }
          if (currentUrl.contains("host.docker.internal")) {
               currentUrl = currentUrl.replace("host.docker.internal", "localhost");
          }

          String decodedExpected = URLDecoder.decode(expectedLink.trim(), StandardCharsets.UTF_8);
          String decodedActual = URLDecoder.decode(currentUrl.trim(), StandardCharsets.UTF_8);

          System.out.println("[DEBUG] Expected URL: " + decodedExpected);
          System.out.println("[DEBUG] Actual URL: " + decodedActual);

          return decodedActual.equalsIgnoreCase(decodedExpected);
     }

     public boolean verifyTitle(String expectedTitle) {
          String actualTitle = driver.getTitle();
          System.out.println("[DEBUG] Expected Title: " + expectedTitle);
          System.out.println("[DEBUG] Actual Title: " + actualTitle);
          return actualTitle.equals(expectedTitle);
     }
}
