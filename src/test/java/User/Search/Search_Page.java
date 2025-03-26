package User.Search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search_Page {
	public WebDriver driver;

	// Link
	@FindBy(xpath = "//a[.='Trang chủ']")
	public WebElement linkHomePage;

	@FindBy(xpath = "//a[.='Sản phẩm']")
	public WebElement linkProduct;

	// Input
	@FindBy(xpath = "//input[@class='product__search-input ng-untouched ng-pristine ng-valid']")
	public WebElement txtSearch;

	// Button
	@FindBy(xpath = "//button[@class='product__search-submit btn']")
	public WebElement btnSearch;

	@FindBy(xpath = "///div[@class='product__search-category']")
	public WebElement btnCategory;

	public Search_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
