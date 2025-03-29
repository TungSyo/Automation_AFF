package User.Search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search_Page {
	public WebDriver driver;

	// Link
	@FindBy(xpath = "//a[.='Trang chủ']")
	private WebElement linkHomePage;

	@FindBy(xpath = "//a[.='Sản phẩm']")
	private WebElement linkProduct;

	// Input
	@FindBy(xpath = "//input[@class='product__search-input ng-untouched ng-pristine ng-valid']")
	private WebElement txtSearch;

	// Button
	@FindBy(xpath = "//button[@class='product__search-submit btn']")
	private WebElement btnSearch;

	@FindBy(xpath = "///div[@class='product__search-category']")
	private WebElement btnCategory;

	public Search_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getTxtSearch() { return txtSearch; }
	public WebElement getBtnSearch() { return btnSearch; }
	public WebElement getBtnCategory() { return btnCategory; }
}
