package User.Cart.CartAdd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Base.*;
import User.Cart.Cart.*;
import User.Search.Search_Page;

@SuppressWarnings("unused")
public class Cart_Add_Action {
    private WebDriver driver;
    private Base_Action baseAction;
    private Cart_Page scart_Page;
    private Search_Page search_Page;
    private Cart_Action scart_Action;

    public Cart_Add_Action(WebDriver driver) {
        this.driver = driver;
        this.scart_Page = new Cart_Page(driver);
        this.baseAction = new Base_Action(driver);
        this.search_Page = new Search_Page(driver);
        this.scart_Action = new Cart_Action(driver);
    }

    public void clickButton(WebElement element) {
        baseAction.clickElement(element);
    }

    public void enterText(WebElement element, String text) {
        baseAction.clearAndEnterText(element, text);
    }

    public void addToSCart(String product, String quantity, String price, String typecase) {
        switch (typecase) {
            case "One":
                enterText(search_Page.getTxtSearch(), product);
                clickButton(search_Page.getBtnSearch());
                scart_Action.addProductToCart(1);
                clickButton(scart_Page.getBtnCart());
                clickButton(scart_Page.getSelectAllCheckbox());
                scart_Action.checkProduct(quantity, price);
                break;
            case "Two":
                enterText(search_Page.getTxtSearch(), product);
                clickButton(search_Page.getBtnSearch());
                scart_Action.addProductToCart(1, 2, 3);
                clickButton(scart_Page.getBtnCart());
                clickButton(scart_Page.getSelectAllCheckbox());
                scart_Action.checkProduct(quantity, price);
                break;
            case "Three":
                enterText(search_Page.getTxtSearch(), product);
                clickButton(search_Page.getBtnSearch());
                int quantityValue = (int) Double.parseDouble(quantity);
                for (int i = 0; i < quantityValue; i++) {
                    scart_Action.clickAddToCart(1);
                    baseAction.sleep(800);
                }
                clickButton(scart_Page.getBtnCart());
                clickButton(scart_Page.getSelectAllCheckbox());
                scart_Action.checkProduct(quantity, price);
                break;
            default:
                System.out.println("Invalid typecase: " + typecase);
        }
    }
}
