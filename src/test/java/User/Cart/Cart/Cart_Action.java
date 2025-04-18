package User.Cart.Cart;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.*;
import User.Search.Search_Page;
import User.Information.Information_Page;
import User.WithDraw.WithDraw_Action;
@SuppressWarnings("unused")
public class Cart_Action {
	private WebDriver driver;
	private Base_Page basePage;
	private Base_Action baseAction;
	private Cart_Page scart_Page;
	private Information_Page inforPage;
	private WithDraw_Action withdrawAction;

	public Cart_Action(WebDriver driver) {
		this.driver = driver;
		this.basePage = new Base_Page(driver);
		this.scart_Page = new Cart_Page(driver);
		this.baseAction = new Base_Action(driver);
		this.inforPage = new Information_Page(driver);
		this.withdrawAction = new WithDraw_Action(driver);
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
	
	public void addProductsAndGoToCart() {
		clickButton(basePage.getLinkProduct());
		addProductToCart(1, 2, 3);
		clickButton(scart_Page.getBtnCart());
	}


	private void caseOne() {
		addProductsAndGoToCart();
		clickButton(scart_Page.getSelectCheckboxDongy());
		clickButton(scart_Page.getBtnToThanhToan());

	}

	private void caseTwo() {
		addProductsAndGoToCart();
		clickButton(scart_Page.getSelectAllCheckbox());
		clickButton(scart_Page.getBtnToThanhToan());

	}

	private void caseThree() {
		addProductsAndGoToCart();
		clickButton(scart_Page.getSelectAllCheckbox());
		clickButton(scart_Page.getSelectCheckboxDongy());
		clickButton(scart_Page.getBtnToThanhToan());
	}

	
	public void SCartToOrder(String typecase) {
		switch (typecase) {
			case "One":
				caseOne();
				break;
			case "Two":
				caseTwo();
				break;
			case "Three":
				caseThree();
				break;
			default:
				System.out.println("Invalid typecase: " + typecase);
		}
	}
}
