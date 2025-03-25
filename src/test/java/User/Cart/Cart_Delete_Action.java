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
public class Cart_Delete_Action {
	private WebDriver driver;
	private Base_Page basePage;
	private Base_Action baseAction;
	private Cart_Page scart_Page;
	private Search_Page search_Page;
	private Cart_Action cartActions;

	public Cart_Delete_Action(WebDriver driver) {
		this.driver = driver;
		this.basePage = new Base_Page(driver);
		this.scart_Page = new Cart_Page(driver);
		this.baseAction = new Base_Action(driver);
		this.search_Page = new Search_Page(driver);
		this.cartActions = new Cart_Action(driver);
	}

	public void clickButton(WebElement element) {
		baseAction.clickElement(element);
	}

	public void enterText(WebElement element, String text) {
		baseAction.clearAndEnterText(element, text);
	}

	public void clickRemoveFromCart(int productIndex) {
		if (productIndex > 0 && productIndex <= scart_Page.deleteButtons.size()) {
			scart_Page.deleteButtons.get(productIndex - 1).click();
		} else {
			throw new IndexOutOfBoundsException("Product at index " + productIndex + " does not exist");
		}
	}

	public void clickRemoveAllFromCart() {
		while (!scart_Page.deleteButtons.isEmpty()) {
			scart_Page.deleteButtons.get(0).click();
		}
	}
	public void deleteProduct(String typeCase) {
		switch (typeCase) {
			case "One":
				clickButton(basePage.linkProduct);
				cartActions.addProductToCart(1,2,3);
				clickButton(scart_Page.btnCart);
				clickRemoveFromCart(1);
				break;
			case "Two":
				clickButton(basePage.linkProduct);
				cartActions.addProductToCart(1,2,3);
				clickButton(scart_Page.btnCart);
				clickRemoveAllFromCart();
				break;
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

			Sheet sheet = workbook.getSheet("SCartDel");
			if (sheet == null) {
				System.err.println("[ERROR] Sheet 'SCartDelete' not found in the Excel file.");
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
