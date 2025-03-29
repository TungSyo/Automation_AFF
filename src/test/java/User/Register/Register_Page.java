package User.Register;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Register_Page {
	public static Object UserName;

	@SuppressWarnings("unused")

	private WebDriver driver;

	// Input
	@FindBy(xpath = "//input[@placeholder='Tên người dùng']")
	private WebElement txtName;

	@FindBy(xpath = "//input[@formcontrolname='citizenIdentification']")
	private WebElement txtCMND;

	@FindBy(xpath = "//input[@formcontrolname='phoneNumber']")
	private WebElement txtSdt;

	@FindBy(xpath = "//input[@formcontrolname='password']")
	private WebElement txtPass;

	@FindBy(xpath = "//input[@formcontrolname='email']")
	private WebElement txtEmail;

	@FindBy(xpath = "//input[@formcontrolname='referralCode']")
	private WebElement txtMgt;

	// Tỉnh - Huyện - Xã
	@FindBy(xpath = "//input[@formcontrolname='cityName']")
	private WebElement txtCity;

	@FindBy(xpath = "//input[@formcontrolname='districtName']")
	private WebElement txtDistrict;

	@FindBy(xpath = "//input[@formcontrolname='wardName']")
	private WebElement txtWard;

	@FindBy(xpath = "//input[@formcontrolname='address']")
	private WebElement txtLocation;

	// No validate
	@FindBy(xpath = "//input[@formcontrolname='personalTaxCode']")
	private WebElement txtMst;

	@FindBy(xpath = "//input[@formcontrolname='dateOfBirth']")
	private WebElement txtDate;

	@FindBy(xpath = "//input[@formcontrolname='bankName']")
	private WebElement txtBank;

	@FindBy(xpath = "//input[@formcontrolname='bankAccountNumber']")
	private WebElement txtStk;

	@FindBy(xpath = "//input[@formcontrolname='isActive']")
	private WebElement cbDongy;

	@FindBy(xpath = "//button[@class='button-submit']")
	private WebElement btnTaotk;

	@FindBy(xpath = "//div[@class='otp-inputs']/input[1]")
	private WebElement txtOtp;
	
	// Button
	@FindBy(xpath = "//a[@class='resend-link'][contains(.,'Gửi lại')]")
	private WebElement btnResend;

	@FindBy(xpath = "//button[contains(@class,'verify-button')]")
	private WebElement btnXacnhan;

	public Register_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getTxtName() { return txtName; }
	public WebElement getTxtCMND() { return txtCMND; }
	public WebElement getTxtSdt() { return txtSdt; }
	public WebElement getTxtPass() { return txtPass; }
	public WebElement getTxtEmail() { return txtEmail; }
	public WebElement getTxtMgt() { return txtMgt; }
	public WebElement getTxtCity() { return txtCity; }
	public WebElement getTxtDistrict() { return txtDistrict; }
	public WebElement getTxtWard() { return txtWard; }
	public WebElement getTxtLocation() { return txtLocation; }
	public WebElement getTxtMst() { return txtMst; }
	public WebElement getTxtDate() { return txtDate; }
	public WebElement getTxtBank() { return txtBank; }
	public WebElement getTxtStk() { return txtStk; }
	public WebElement getCbDongy() { return cbDongy; }
	public WebElement getBtnTaotk() { return btnTaotk; }
	public WebElement getTxtOtp() { return txtOtp; }
	public WebElement getBtnResend() { return btnResend; }
	public WebElement getBtnXacnhan() { return btnXacnhan; }
}
