package User.Cart;

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

import Base.Base_Action;
import Base.Base_Page;
import Base.Base_Test;
import Report.Extend_Report;
import User.Search.Search_Page;

@SuppressWarnings("unused")
public class Cart_Add_Action {
    private WebDriver driver;
    private Base_Page basePage;
    private Base_Action baseAction;
    private Cart_Page scart_Page;
    private Search_Page search_Page;
    private Cart_Action scart_Action;

    public Cart_Add_Action(WebDriver driver) {
        this.driver = driver;
        this.basePage = new Base_Page(driver);
        this.scart_Page = new Cart_Page(driver);
        this.scart_Action = new Cart_Action(driver);
        this.baseAction = new Base_Action(driver);
        this.search_Page = new Search_Page(driver);
    }

    public void clickButton(WebElement element) {
        baseAction.clickElement(element);
    }

    public void enterText(WebElement element, String text) {
        baseAction.clearAndEnterText(element, text);
    }

    public void addToSCart(String typecase, String productName, String productQuantity, String productPrice) {
        switch (typecase) {
            case "One":
                enterText(search_Page.txtSearch, productName);
                clickButton(search_Page.btnSearch);
                scart_Action.addProductToCart(1);
                clickButton(scart_Page.btnCart);
                clickButton(scart_Page.selectAllCheckbox);
                scart_Action.checkProduct(productQuantity, productPrice);
                break;
            case "Two":
                enterText(search_Page.txtSearch, productName);

                clickButton(search_Page.btnSearch);

                scart_Action.addProductToCart(1, 2, 3);

                clickButton(scart_Page.btnCart);

                clickButton(scart_Page.selectAllCheckbox);

                scart_Action.checkProduct(productQuantity, productPrice);
                break;
            case "Three":
                enterText(search_Page.txtSearch, productName);

                clickButton(search_Page.btnSearch);

                int quantity = (int) Double.parseDouble(productQuantity);
                for (int i = 0; i < quantity; i++) {
                    scart_Action.clickAddToCart(1);
                    baseAction.sleep(800);
                }
                clickButton(scart_Page.btnCart);

                clickButton(scart_Page.selectAllCheckbox);
                scart_Action.checkProduct(productQuantity, productPrice);
                break;
            default:
                System.out.println("Invalid typecase: " + typecase);
        }
    }

    public boolean verifyNotion(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            List<WebElement> allElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//*[normalize-space(text())='" + expectedText + "']")));
            return !allElements.isEmpty();
        } catch (Exception e) {
            return false;
        }
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

    public List<Object[]> getSearchTestData() throws IOException {
        List<Object[]> testData = new ArrayList<>();
        String filePath = "src/test/resources/data/AFF_U_Data.xlsx";
        File file = new File(filePath);

        if (!file.exists()) {
            System.err.println("[ERROR] File not found: " + filePath);
            return testData;
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
                Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheet("SCartAdd");
            if (sheet == null) {
                System.err.println("[ERROR] Sheet 'SCartAdd' not found in the Excel file.");
                return testData;
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || row.getCell(1) == null || row.getCell(2) == null)
                    continue;
                String expectedTitle = row.getCell(1).getStringCellValue();
                String expectedLink = row.getCell(2).getStringCellValue().trim();
                testData.add(new Object[] { expectedTitle, expectedLink });
            }
        }

        return testData;
    }

}
