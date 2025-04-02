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
    private WebElement inputCoupon;

    @FindBy(xpath = "//button[contains(@class,'coupon-search-btn')]")
    private WebElement btnCouponApply;

    // Validate
    @FindBy(xpath = "//input[contains(@placeholder,'Tên người dùng')]")
    private WebElement txtusername;

    @FindBy(xpath = "//input[contains(@placeholder,'Số điện thoại')]")
    private WebElement txtphone;

    @FindBy(xpath = "//input[contains(@placeholder,'Chọn ngân hàng')]")
    private WebElement selectbank;

    @FindBy(xpath = "(//li[@class='dropdown-item ng-star-inserted'])")
    private List<WebElement> BankList;

    @FindBy(xpath = "(//input[contains(@type,'radio')])")
    private List<WebElement> btnMethodPayment;

    // No Validate
    @FindBy(xpath = "//textarea[contains(@formcontrolname,'note')]")
    private WebElement txtNote;

    @FindBy(xpath = "//input[contains(@placeholder,'Chọn tỉnh')]")
    private WebElement txtcity;
    
    @FindBy(xpath = "(//li[contains(@class,'dropdown-item ng-star-inserted')])")
    private WebElement listLocation;

    @FindBy(xpath = "//input[@placeholder='Chọn huyện']")
    private WebElement txtdistrict;

    @FindBy(xpath = "//input[@placeholder='Chọn xã']")
    private WebElement txtward;

    @FindBy(xpath = "//input[contains(@placeholder,'Địa chỉ chi tiết')]")
    private WebElement txtaddress;
    
    // Button
    @FindBy(xpath = "//button[contains(@type,'submit')]")
    private WebElement btnOrder;

    // Total Price
    @FindBy (xpath = "/html[1]/body[1]/app-root[1]/app-client-layout[1]/div[1]/app-payment[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/table[1]/tfoot[1]/tr[1]/td[1]/span[1]/bdi[1]")
    private WebElement totalPrice;

    @FindBy(xpath = "/html[1]/body[1]/app-root[1]/app-client-layout[1]/div[1]/app-payment[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/table[1]/tfoot[1]/tr[2]/td[1]/span[1]/bdi[1]")
    private WebElement voucherDiscount;

    @FindBy (xpath = "//tr[@class='order-total']//bdi")
    private WebElement totalPriceAfterDiscount;

    public Order_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public WebElement getInputCoupon() { return inputCoupon; }
    public WebElement getBtnCouponApply() { return btnCouponApply; }
    public WebElement getBtnOrder() { return btnOrder; }
    public WebElement getTotalPrice() { return totalPrice; }
    public WebElement getVoucherDiscount() { return voucherDiscount; }
    public WebElement getTotalPriceAfterDiscount() { return totalPriceAfterDiscount; }

    public WebElement getTxtusername() { return txtusername; }
    public WebElement getTxtphone() { return txtphone; }
    public WebElement getSelectbank() { return selectbank; }
    public List<WebElement> getBankList() { return BankList; }
    public List<WebElement> getBtnMethodPayment() { return btnMethodPayment; }
    public WebElement getTxtNote() { return txtNote; }
    public WebElement getTxtcity() { return txtcity; }
    public WebElement getListLocation() { return listLocation; }
    public WebElement getTxtdistrict() { return txtdistrict; }
    public WebElement getTxtward() { return txtward; }
    public WebElement getTxtaddress() { return txtaddress; }    
    
    
}
