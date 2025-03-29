package User.Information;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Information_Page {
	public WebDriver driver;

	// Link
	@FindBy(xpath = "(//a[@class='nav-link'][contains(.,'Thông tin người dùng')])[1]")
	private WebElement linkInfor;

	// Button
	@FindBy(xpath = "//button[@class='btn-update-user'][contains(.,'Cập nhật')]")
	private WebElement btnUpdate_1;

	@FindBy(xpath = "//button[@type='submit'][contains(.,'Cập nhật')]")
	private WebElement btnUpdate_2;
	
	// Input
	@FindBy(xpath = "//input[contains(@placeholder,'Tên người dùng')]")
	private WebElement txtName;

	@FindBy(xpath = "//input[contains(@placeholder,'CCCD/CMND')]")
	private WebElement txtCMND;

	@FindBy(xpath = "//input[contains(@placeholder,'Chọn tỉnh')]")
	private WebElement txtCity;

	@FindBy(xpath = "//input[contains(@placeholder,'Chọn huyện')]")
	private WebElement txtDistrict;

	@FindBy(xpath = "//input[contains(@placeholder,'Chọn xã')]")
	private WebElement txtWard;

	@FindBy(xpath = "//input[contains(@placeholder,'Địa chỉ chi tiết')]")
	private WebElement txtLocation;

	@FindBy(xpath = "//input[contains(@placeholder,'Mã số thuế cá nhân')]")
	private WebElement txtMst;

	@FindBy(xpath = "//input[contains(@type,'date')]")
	private WebElement txtDate;

	@FindBy(xpath = "//input[contains(@placeholder,'Tên ngân hàng')]")
	private WebElement txtBank;

	@FindBy(xpath = "//input[contains(@placeholder,'Số tài khoản')]")
	private WebElement txtStk;

	public Information_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getLinkInfor() { return linkInfor; }
	public WebElement getTxtName() { return txtName; }
    public WebElement getTxtCmnd() { return txtCMND; }
    public WebElement getTxtCity() { return txtCity; }
    public WebElement getTxtDistrict() { return txtDistrict; }
    public WebElement getTxtWard() { return txtWard; }
    public WebElement getTxtLocation() { return txtLocation; }
    public WebElement getTxtMst() { return txtMst; }
    public WebElement getTxtDate() { return txtDate; }
    public WebElement getTxtBank() { return txtBank; }
    public WebElement getTxtStk() { return txtStk; }
    public WebElement getBtnUpdate_1() { return btnUpdate_1; }
	public WebElement getBtnUpdate_2() { return btnUpdate_2; }
}
 