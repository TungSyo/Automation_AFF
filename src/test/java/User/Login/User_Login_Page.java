package User.Login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class User_Login_Page {
	public WebDriver driver;
	// Account Userr
	@FindBy(xpath = "//div[@class='field-group user_name']//input[@id='user']")
	private WebElement txtUUser;

	@FindBy(xpath = "//div[@class='field-group']//input[@id='user']")
	private WebElement txtUPass;

	@FindBy(xpath = "//button[@class='button-submit']")
	private WebElement btnULogin;

	public User_Login_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getTxtUUser() { return txtUUser; }
	public WebElement getTxtUPass() { return txtUPass; }
	public WebElement getBtnULogin() { return btnULogin; }
}
