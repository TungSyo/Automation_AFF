package User.Login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Base.*;

@SuppressWarnings("unused")
public class User_Login_Action {
	private WebDriver driver;
	private Base_Page basePage;
	private Base_Action baseAction;
	private User_Login_Page userLoginPage;

	public User_Login_Action(WebDriver driver) {
		this.driver = driver;
		this.basePage = new Base_Page(driver);
		this.baseAction = new Base_Action(driver);
		this.userLoginPage = new User_Login_Page(driver);
	}

	public void enterText(WebElement element, String text) {
		baseAction.clearAndEnterText(element, text);
	}

	public void clickButton(WebElement element) {
		baseAction.clickElement(element);
	}

	public void login(String email, String password) {
		clickButton(basePage.getBtnAccount());
		enterText(userLoginPage.getTxtUUser(), email);
		enterText(userLoginPage.getTxtUPass(), password);
		clickButton(userLoginPage.getBtnULogin());
	}
}