package User.ForgotPass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Forgot_Pass_Page {
	public WebDriver driver;

	// Input
	@FindBy(xpath = "//input[contains(@id,'user')]")
	private WebElement txtEmailForgot;

	@FindBy(xpath = "//input[contains(@placeholder,'Mật khẩu mới')]")
	private WebElement txtNewPass;

	@FindBy(xpath = "//input[contains(@id,'confirmPassword')]")
	private WebElement txtConfirmPass;

	@FindBy(xpath = "//input[contains(@id,'otp')]")
	private WebElement txtOTP;

	// Link
	@FindBy(xpath = "//a[contains(.,'Quay lại đăng nhập')]")
	private WebElement linkBackLogin;

	@FindBy(xpath = "//a[@href='/resend-email-otp']")
	private WebElement linkResendOTP;

	// Button
	@FindBy(xpath = "//span[contains(.,'Lấy mã OTP')]")
	private WebElement btnGetOTP;

	@FindBy(xpath = "//span[contains(.,'Đến trang đặt lại mật khẩu')]")
	private WebElement btnToResetPass;

	@FindBy(xpath = "//span[@class='p-button-label'][contains(.,'XÁC NHẬN')]")
	private WebElement btnConfirm;

	public Forgot_Pass_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getTxtEmailForgot() { return txtEmailForgot; }
	public WebElement getTxtNewPass() { return txtNewPass; }
	public WebElement getTxtConfirmPass() { return txtConfirmPass; }
	public WebElement getTxtOTP() { return txtOTP; }
	public WebElement getLinkBackLogin() { return linkBackLogin; }
	public WebElement getLinkResendOTP() { return linkResendOTP; }
	public WebElement getBtnGetOTP() { return btnGetOTP; }
	public WebElement getBtnToResetPass() { return btnToResetPass; }
	public WebElement getBtnConfirm() { return btnConfirm; }
}
