package User.Information;

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
public class Information_Action {
	private WebDriver driver;
	private Base_Page basePage;
	private Base_Action baseAction;
	private Information_Page inforPage;

	public Information_Action(WebDriver driver) {
		this.driver = driver;
		this.basePage = new Base_Page(driver);
		this.inforPage = new Information_Page(driver);
		this.baseAction = new Base_Action(driver);
	}

	public void enterText(WebElement element, String text) {
		baseAction.clearAndEnterText(element, text);
	}

	public void clickButton(WebElement element) {
		baseAction.clickElement(element);
	}

	public void updateInformation(String name, String cmnd, String city, String district, String ward, String location,
			String mst, String date, String bank, String stk) {
		clickButton(basePage.getBtnAccount_2());
		clickButton(inforPage.getLinkInfor());
		clickButton(inforPage.getBtnUpdate_1());
		enterText(inforPage.getTxtName(), name);
		enterText(inforPage.getTxtCmnd(), cmnd);
		enterText(inforPage.getTxtCity(), city);
		enterText(inforPage.getTxtDistrict(), district);
		enterText(inforPage.getTxtWard(), ward);
		enterText(inforPage.getTxtLocation(), location);
		enterText(inforPage.getTxtMst(), mst);
		enterText(inforPage.getTxtDate(), date);
		enterText(inforPage.getTxtBank(), bank);
		enterText(inforPage.getTxtStk(), stk);
		clickButton(inforPage.getBtnUpdate_2());
	}

}
