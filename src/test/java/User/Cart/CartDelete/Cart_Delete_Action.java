package User.Cart.CartDelete;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Base.Base_Action;
import Base.Base_Page;
import User.Cart.Cart.Cart_Action;
import User.Cart.Cart.Cart_Page;

@SuppressWarnings("unused")
public class Cart_Delete_Action {
	private WebDriver driver;
	private Base_Page basePage;
	private Base_Action baseAction;
	private Cart_Page scart_Page;
	private Cart_Action cartActions;

	public Cart_Delete_Action(WebDriver driver) {
		this.driver = driver;
		this.basePage = new Base_Page(driver);
		this.scart_Page = new Cart_Page(driver);
		this.baseAction = new Base_Action(driver);
		this.cartActions = new Cart_Action(driver);
	}

	public void clickButton(WebElement element) {
		baseAction.clickElement(element);
	}

	public void enterText(WebElement element, String text) {
		baseAction.clearAndEnterText(element, text);
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

	public void deleteProduct(String typeCase) {
		switch (typeCase) {
			case "One":
				clickButton(basePage.getLinkProduct());
				cartActions.addProductToCart(1, 2, 3);
				clickButton(scart_Page.getBtnCart());
				int initialProductCount = scart_Page.getProductName().size();
				System.out.println("Số lượng sản phẩm ban đầu: " + initialProductCount);
				clickRemoveFromCart(1);
				int finalProductCount = scart_Page.getProductName().size();
				System.out.println("Số lượng sản phẩm sau khi xóa: " + finalProductCount);
				if (finalProductCount != initialProductCount - 1) {
					throw new AssertionError("Số lượng sản phẩm không giảm đúng sau khi xóa");
				}
				break;
			case "Two":
				clickButton(basePage.getLinkProduct());
				cartActions.addProductToCart(1, 2, 3);
				int initialCount = scart_Page.getProductName().size();
				System.out.println("Số lượng sản phẩm ban đầu: " + initialCount);
				clickButton(scart_Page.getBtnCart());
				clickRemoveAllFromCart();
				int finalCount = scart_Page.getProductName().size();
				System.out.println("Số lượng sản phẩm sau khi xóa: " + finalCount);
				break;
		}
	}
}
