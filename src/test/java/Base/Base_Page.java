package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Base_Page {
	public WebDriver driver;
	// --------Menu User--------
	@FindBy(xpath = "//a[.='Trang chủ']")
	private WebElement linkHomePage;
	public WebElement getLinkHomePage() { return linkHomePage; }

	@FindBy(xpath = "//a[.='Sản phẩm']")
	private WebElement linkProduct;
	public WebElement getLinkProduct() { return linkProduct; }

	@FindBy(xpath = "//input[@class='product__search-input ng-untouched ng-pristine ng-valid']")
	private WebElement txtSearch;
	public WebElement getTxtSearch() { return txtSearch; }

	@FindBy(xpath = "//button[@class='product__search-submit btn']")
	private WebElement btnSearch;
	public WebElement getBtnSearch() { return btnSearch; }

	// @FindBy(xpath = "//li[@class='header__shop-icon-item header__shop-icon-item-cart']")
	// private WebElement btnSCart;
	// public WebElement getBtnSCart() { return btnSCart; }

	@FindBy(xpath = "//app-header/div[2]/div[1]/div[3]/div[3]/ul[1]/li[2]")
	private WebElement btnAccount;
	public WebElement getBtnAccount() { return btnAccount; }
	
	@FindBy(xpath = "/html[1]/body[1]/app-root[1]/app-client-layout[1]/app-header[1]/div[2]/div[1]/div[3]/div[3]/ul[1]/li[2]")
	private WebElement btnAccount_2;
	public WebElement getBtnAccount_2() { return btnAccount_2; }

	@FindBy(xpath = "//span[.='Đăng ký']")
	private WebElement linkRegister;
	public WebElement getLinkRegister() { return linkRegister; }

	@FindBy(xpath = "//span[.='Quên mật khẩu']")
	private WebElement LinkForgotPassword;
	public WebElement getLinkForgotPassword() { return LinkForgotPassword; }

	// --------Menu Admin--------
	@FindBy(xpath = "//span[@class='layout-menuitem-text ng-tns-c183498709-7']")
	private WebElement menuOverview;
	public WebElement getMenuOverview() { return menuOverview; }

	// --------Menu Product--------
	@FindBy(xpath = "//a[.='Sản phẩm']")
	private WebElement menuProduct;
	public WebElement getMenuProduct() { return menuProduct; }

	@FindBy(xpath = "//a[.='Danh sách sản phẩm']")
	private WebElement menuListProduct;
	public WebElement getMenuListProduct() { return menuListProduct; }

	// @FindBy(xpath = "//a[.='Danh mục sản phẩm']")
	// public WebElement menuListCategory;

	@FindBy(xpath = "//a[.='Thương hiệu']")
	private WebElement menuListBrand;
	public WebElement getMenuListBrand() { return menuListBrand; }

	// --------Menu Quản trị hệ thống-------
	@FindBy(xpath = "//a[.='Quản trị hệ thống']")
	private WebElement menuAdminSystem;
	public WebElement getMenuAdminSystem() { return menuAdminSystem; }

	@FindBy(xpath = "//a[.='Nhóm quyền']")
	private WebElement menuAuthor;
	public WebElement getMenuAuthor() { return menuAuthor; }

	@FindBy(xpath = "//a[.='Quản lý tài khoản']")
	private WebElement menuAccount;
	public WebElement getMenuAccount() { return menuAccount; }

	@FindBy(xpath = "//a[.='Tài khoản người dùng']")
	private WebElement menuInfoAccount;
	public WebElement getMenuInfoAccount() { return menuInfoAccount; }

	// --------Menu Hệ thống--------
	@FindBy(xpath = "//a[.='Hệ thống']")
	private WebElement menuSystem;	
	public WebElement getMenuSystem() { return menuSystem; }

	@FindBy(xpath = "//a[@href='/admin/pages/tree-binary/show']")
	private WebElement menuLevelAccount;
	public WebElement getMenuLevelAccount() { return menuLevelAccount; }

	/*
	 * @FindBy(xpath = "//a[.='Rút điểm thưởng']")
	 * public WebElement menuWithdraw;
	 * 
	 * @FindBy(xpath = "//a[.='Danh sách rút điểm thưởng']")
	 * public WebElement menuListPoint;
	 * 
	 * @FindBy(xpath = "//a[.='Hoa hồng điểm thưởng']")
	 * public WebElement menuCommission;
	 */

	/*
	 * @FindBy(xpath = "//a[.='Đơn hàng']")
	 * public WebElement menuOrder;
	 * 
	 * @FindBy(xpath = "//a[.='Tạo đơn hàng']")
	 * public WebElement menuCreatOrder;
	 * 
	 * @FindBy(xpath = "//a[.='Lịch sử đặt hàng']")
	 * public WebElement menuHistoryOrder;
	 * 
	 * @FindBy(xpath = "//a[.='Danh sách đơn hàng']")
	 * public WebElement menuListOrder;
	 */

	/*
	 * @FindBy(xpath = "//a[.='Quản trị nội dung']")
	 * public WebElement menuContent;
	 * 
	 * @FindBy(xpath = "//a[.='Quản lý tin tức']")
	 * public WebElement menuNews;
	 */

	// Google
	@FindBy(xpath = "//input[contains(@type,'email')]")
	private WebElement txtEmailGG;
	public WebElement getTxtEmailGG() { return txtEmailGG; }

	@FindBy(xpath = "//input[contains(@type,'password')]")
	private WebElement txtPassGG;
	public WebElement getTxtPassGG() { return txtPassGG; }

	@FindBy(xpath = "//span[@jsname='V67aGc'][contains(.,'Next')]")
	private WebElement btnNext;
	public WebElement getBtnNext() { return btnNext; }

	@FindBy(xpath = "(//i[contains(@class,'fa fa-power-off')])[1]")
	private WebElement btnLogout;
	public WebElement getBtnLogout() { return btnLogout; }

	@FindBy(xpath = "//div[@class='AsY17b'][contains(.,'Sử dụng một tài khoản khác')]")
	private WebElement btnNewAccount;
	public WebElement getBtnNewAccount() { return btnNewAccount; }

	@FindBy(xpath = "//div[@class='TN bzz aHS-aHO'][contains(.,'Tất cả thư')]")
	private WebElement btnAllEmail;
	public WebElement getBtnAllEmail() { return btnAllEmail; }

	@FindBy(xpath = "(//span[@class='ait'])[1]")
	private WebElement btnAddView;
	public WebElement getBtnAddView() { return btnAddView; }

	@FindBy(xpath = "//div[@class='D E G-atb PY']//div[@class='G-tF']/div[4]")
	private WebElement btnReload;
	public WebElement getBtnReload() { return btnReload; }

	@FindBy(xpath = "(//div[contains(@class,'asa')])[12]")
	private WebElement btnDeleteMail;
	public WebElement getBtnDeleteMail() { return btnDeleteMail; }

	@FindBy(xpath = "(//span[contains(@class,'button__label')])[5]")
	private WebElement btnLoginEmail;
	public WebElement getBtnLoginEmail() { return btnLoginEmail; }

	public Base_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
