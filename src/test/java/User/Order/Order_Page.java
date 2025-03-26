package User.Order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class Order_Page {
    public WebDriver driver;

    // Coupon
    @FindBy(xpath = "//input[contains(@placeholder,'Nhập mã ưu đãi')]")
    public WebElement inputCoupon;

    @FindBy(xpath = "//button[contains(@class,'coupon-search-btn')]")
    public WebElement btnCouponApply;

    // Validate
    @FindBy(xpath = "//input[contains(@placeholder,'Tên người dùng')]")
    public WebElement txtusername;

    @FindBy(xpath = "//input[contains(@placeholder,'Số điện thoại')]")
    public WebElement txtphone;

    @FindBy(xpath = "//input[contains(@placeholder,'Chọn ngân hàng')]")
    public WebElement selectbank;

    @FindBy(xpath = "(//li[@class='dropdown-item ng-star-inserted'])")
    public List<WebElement> BankList;

    @FindBy(xpath = "(//input[contains(@type,'radio')])[1]")
    public List<WebElement> btnMethodPayment;

    // No Validate
    @FindBy(xpath = "//textarea[contains(@formcontrolname,'note')]")
    public WebElement txtNote;

    @FindBy(xpath = "//input[contains(@placeholder,'Chọn tỉnh')]")
    public WebElement txtcity;
    
    @FindBy(xpath = "(//li[contains(@class,'dropdown-item ng-star-inserted')])")
    public WebElement listLocation;

    @FindBy(xpath = "//input[contains(@placeholder,'Chọn huyện')]")
    public WebElement txtdistrict;

    @FindBy(xpath = "//input[contains(@placeholder,'Chọn phường/xã')]")
    public WebElement txtward;

    @FindBy(xpath = "//input[contains(@placeholder,'Địa chỉ chi tiết')]")
    public WebElement txtaddress;
    
    // Button
    @FindBy(xpath = "//button[contains(@type,'submit')]")
    public WebElement btnOrder;

    // Total Price
    @FindBy (xpath = "/html[1]/body[1]/app-root[1]/app-client-layout[1]/div[1]/app-payment[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/table[1]/tfoot[1]/tr[1]/td[1]/span[1]/bdi[1]")
    public WebElement totalPrice;

    @FindBy(xpath = "/html[1]/body[1]/app-root[1]/app-client-layout[1]/div[1]/app-payment[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/table[1]/tfoot[1]/tr[2]/td[1]/span[1]/bdi[1]")
    public WebElement voucherDiscount;

    @FindBy (xpath = "//tr[@class='order-total']//bdi")
    public WebElement totalPriceAfterDiscount;

    public Order_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
