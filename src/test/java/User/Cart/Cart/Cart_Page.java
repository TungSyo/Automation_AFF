package User.Cart.Cart;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Cart_Page {
     public WebDriver driver;

     @FindBy(xpath = "//div[@class='cart-product']//tr//input[@class='ng-untouched ng-pristine']")
     private List<WebElement> productQuantity;

     @FindBy(xpath = "//tr[@class='order-total']/td")
     private List<WebElement> productPrice;

     @FindBy(xpath = "//div[@class='cart-product']//a")
     private List<WebElement> productName;
     
     @FindBy(xpath = "//div[@class='cart-product']//tr//button[contains(@class,'btn-minus')]")
     private List<WebElement> minusButtons;

     @FindBy(xpath = "//div[@class='cart-product']//tr//button[contains(@class,'btn-plus')]")
     private List<WebElement> plusButtons;
     
     @FindBy(xpath = "//button[contains(.,'Thêm vào giỏ')]")
     private List<WebElement> addToCartButtons;

     @FindBy(xpath = "//input[@type='checkbox']")
     private List<WebElement> addToCartCheckboxes;

     @FindBy(xpath = "//span[@class='product-trash']")
     private List<WebElement> deleteButtons;

     @FindBy(xpath = "(//input[@class='select-all-checkbox'])[1]")
     private WebElement selectAllCheckbox;
     
     @FindBy(xpath = "(//input[contains(@class,'select-all-checkbox')])[3]")
     private WebElement selectCheckboxDongy;
     
     @FindBy(xpath = "//button[contains(.,'TIỀN HÀNG THANH TOÁN')]")
     private WebElement btnToThanhToan;
     
     @FindBy(xpath = "//li[contains(@class,'header__shop-icon-item header__shop-icon-item-cart')]")
     private WebElement btnCart;

     public Cart_Page(WebDriver driver){
          this.driver = driver;
          PageFactory.initElements(driver, this);
     }

     public List<WebElement> getProductQuantity() { return productQuantity; }
     public List<WebElement> getProductPrice() { return productPrice; }
     public List<WebElement> getProductName() { return productName; }
     public List<WebElement> getMinusButtons() { return minusButtons; }
     public List<WebElement> getPlusButtons() { return plusButtons; }
     public List<WebElement> getAddToCartButtons() { return addToCartButtons; }
     public List<WebElement> getAddToCartCheckboxes() { return addToCartCheckboxes; }
     public List<WebElement> getDeleteButtons() { return deleteButtons; }
     public WebElement getSelectAllCheckbox() { return selectAllCheckbox; }
     public WebElement getSelectCheckboxDongy() { return selectCheckboxDongy; }
     public WebElement getBtnToThanhToan() { return btnToThanhToan; }
     public WebElement getBtnCart() { return btnCart; }
}
