package User.ChangePass;

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
		clickButton(basePage.btnAccount_2);
		clickButton(changepassPage.linkChangPass);
		enterText(changepassPage.txtOldPass, oldpass);
		enterText(changepassPage.txtNewPass, newpass);
		clickButton(changepassPage.btnComfirm);
	}

}
