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
public class Cart_Action {
	private WebDriver driver;
	private Base_Page basePage;
	private Base_Action baseAction;
	private Cart_Page scart_Page;
	private Search_Page search_Page;

	public Cart_Action(WebDriver driver) {
		this.driver = driver;
		this.basePage = new Base_Page(driver);
		this.scart_Page = new Cart_Page(driver);
		this.baseAction = new Base_Action(driver);
		this.search_Page = new Search_Page(driver);
	}

	public void clickButton(WebElement element) {
		baseAction.clickElement(element);
	}

	public void enterText(WebElement element, String text) {
		baseAction.clearAndEnterText(element, text);
	}

	public void clickAddToCart(int index) {
		if (index > 0 && index <= scart_Page.getAddToCartButtons().size()) {
			scart_Page.getAddToCartButtons().get(index - 1).click();
		} else {
			throw new IndexOutOfBoundsException("Không tìm thấy sản phẩm ở vị trí: " + index);
		}
	}

	public void addProductToCart(int... indexes) {
		for (int index : indexes) {
			clickAddToCart(index);
		}
	}

	public void clickRemoveFromCart(int productIndex) {
		if (productIndex > 0 && productIndex <= scart_Page.getDeleteButtons().size()) {
			scart_Page.getDeleteButtons().get(productIndex - 1).click();
		} else {
			throw new IndexOutOfBoundsException("Product at index " + productIndex + " does not exist");
		}
	}

	public void clickRemoveAllFromCart() {
		while (!scart_Page.getDeleteButtons().isEmpty()) {
			scart_Page.getDeleteButtons().get(0).click();
		}
	}

	public boolean checkProduct(String productQuantity, String productPrice) {
		double unitPrice = 1_000_000;
		double totalWebPrice = 0;
	
		for (int i = 0; i < scart_Page.getProductPrice().size(); i++) {
			String priceText = scart_Page.getProductPrice().get(i).getText().replaceAll("[^0-9]", "");
			double webPrice = Double.parseDouble(priceText);
			totalWebPrice += webPrice;
		}
	
		String excelPriceText = String.valueOf(productPrice).replaceAll("[^0-9]", "");
		double excelPrice = Double.parseDouble(excelPriceText);
	
		double actualPrice = unitPrice * Double.parseDouble(productQuantity);
		return actualPrice == totalWebPrice && actualPrice == excelPrice;
	}
	

	public void SCartToOrder(String typecase) {
		switch (typecase) {
			case "One":
				clickButton(basePage.getLinkProduct());
				addProductToCart(1, 2, 3);
				clickButton(scart_Page.getBtnCart());
				clickButton(scart_Page.getSelectCheckboxDongy());
				clickButton(scart_Page.getBtnToThanhToan());
				break;
			case "Two":
				clickButton(basePage.getLinkProduct());
				addProductToCart(1, 2, 3);
				clickButton(scart_Page.getBtnCart());
				clickButton(scart_Page.getSelectAllCheckbox());
				clickButton(scart_Page.getBtnToThanhToan());
				break;
			case "Three":
				clickButton(basePage.getLinkProduct());
				addProductToCart(1, 2, 3);
				clickButton(scart_Page.getBtnCart());
				clickButton(scart_Page.getSelectAllCheckbox());
				clickButton(scart_Page.getSelectCheckboxDongy());
				clickButton(scart_Page.getBtnToThanhToan());
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
}
