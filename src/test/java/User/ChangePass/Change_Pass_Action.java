package User.ChangePass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Base.*;

@SuppressWarnings("unused")
public class Change_Pass_Action {
	private WebDriver driver;
	private Base_Page basePage;
	private Base_Action baseAction;
	private Change_Pass_Page changepassPage;

	public Change_Pass_Action(WebDriver driver) {
		this.driver = driver;
		this.basePage = new Base_Page(driver);
		this.changepassPage = new Change_Pass_Page(driver);
		this.baseAction = new Base_Action(driver);
	}

	public void clickButton(WebElement element) {
		baseAction.clickElement(element);
	}

	public void enterText(WebElement element, String text) {
		baseAction.clearAndEnterText(element, text);
	}

	public void changePass(String oldpass, String newpass) {
		clickButton(basePage.getBtnAccount_2());
		clickButton(changepassPage.getLinkChangPass());
		enterText(changepassPage.getTxtOldPass(), oldpass);
		enterText(changepassPage.getTxtNewPass(), newpass);
		clickButton(changepassPage.getBtnComfirm());
	}

}
