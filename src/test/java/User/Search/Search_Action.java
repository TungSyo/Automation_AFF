package User.Search;

import Base.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@SuppressWarnings("unused")
public class Search_Action {
	private WebDriver driver;
	private Search_Page searchPage;
	private Base_Action baseAction;

	public Search_Action(WebDriver driver) {
		this.driver = driver;
		this.searchPage = new Search_Page(driver);
		this.baseAction = new Base_Action(driver);
	}

	public void enterText(WebElement element, String text) {
		baseAction.clearAndEnterText(element, text);
	}

	public void clickButton(WebElement element) {
		baseAction.clickElement(element);
	}

	public void searchProduct(String search) {
		enterText(searchPage.getTxtSearch(), search);
		clickButton(searchPage.getBtnSearch());
	}
}
