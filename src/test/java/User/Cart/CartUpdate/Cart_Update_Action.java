package User.Cart.CartUpdate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Base.*;
import Report.Extend_Report;
import User.Cart.Cart.Cart_Action;
import User.Cart.Cart.Cart_Page;

@SuppressWarnings("unused")
public class Cart_Update_Action {
    private WebDriver driver;
    private Base_Page basePage;
    private Base_Action baseAction;
    private Cart_Page scart_Page;
    private Cart_Action scart_Action;

    public Cart_Update_Action(WebDriver driver) {
        this.driver = driver;
        this.basePage = new Base_Page(driver);
        this.scart_Page = new Cart_Page(driver);
        this.baseAction = new Base_Action(driver);
        this.scart_Action = new Cart_Action(driver);
    }

    public void clickButton(WebElement element) {
        baseAction.clickElement(element);
    }

    public void enterText(WebElement element, String text) {
        baseAction.clearAndEnterText(element, text);
    }

    public void updateProductQuantity(String Quanlity) {
        try {
            if (!scart_Page.getProductQuantity().isEmpty()) {
                String currentQuantity = scart_Page.getProductQuantity().get(0).getDomAttribute("value");
                int currentQty = (int) Math.round(Double.parseDouble(currentQuantity));
                int targetQty = (int) Math.round(Double.parseDouble(Quanlity));

                int timesToChange = targetQty - currentQty;

                if (timesToChange > 0) {
                    for (int i = 0; i < timesToChange; i++) {
                        clickButton(scart_Page.getPlusButtons().get(0));
                        baseAction.sleep(500);
                    }
                    Extend_Report.logInfo("Đã tăng số lượng sản phẩm từ " + currentQty + " lên " + targetQty);
                } else if (timesToChange < 0) {
                    for (int i = 0; i < Math.abs(timesToChange); i++) {
                        clickButton(scart_Page.getMinusButtons().get(0));
                        baseAction.sleep(500);
                    }
                    Extend_Report.logInfo("Đã giảm số lượng sản phẩm từ " + currentQty + " xuống " + targetQty);
                } else {
                    Extend_Report.logInfo("Số lượng sản phẩm đã đạt yêu cầu: " + targetQty);
                }
            }
        } catch (Exception e) {
            Extend_Report.logFail("Lỗi khi cập nhật số lượng sản phẩm: " + e.getMessage());
        }
    }

    public void UpdateSCart(String typecase, String productQuantity, String productPrice) {
        switch (typecase) {
            case "One":
                clickButton(basePage.getLinkProduct());
                scart_Action.addProductToCart(1);
                clickButton(scart_Page.getBtnCart());
                updateProductQuantity( productQuantity);
                clickButton(scart_Page.getSelectAllCheckbox());
                scart_Action.checkProduct(productQuantity, productPrice);
                break;
            case "Two":
                clickButton(basePage.getLinkProduct());
                int quantity = 5;
                for (int i = 0; i < quantity; i++) {
                    scart_Action.clickAddToCart(1);
                    baseAction.sleep(800);
                }
                clickButton(scart_Page.getBtnCart());
                updateProductQuantity( productQuantity);
                clickButton(scart_Page.getSelectAllCheckbox());
                scart_Action.checkProduct( productQuantity, productPrice);
                break;
            case "Three":
                clickButton(basePage.getLinkProduct());
                scart_Action.addProductToCart(1);
                clickButton(scart_Page.getBtnCart());
                updateProductQuantity(productQuantity);
                clickButton(scart_Page.getSelectAllCheckbox());
                scart_Action.checkProduct( productQuantity, productPrice);
                break;
            default:
                System.out.println("Invalid typecase: " + typecase);
        }
    }
}
