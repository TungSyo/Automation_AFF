package User.Order;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import Base.*;
import User.Cart.Cart.Cart_Action;
import User.WithDraw.WithDraw_Action;
import User.Information.Information_Page;
import User.Cart.Cart.Cart_Page;
@SuppressWarnings("unused")
public class Order_Action {
    private WebDriver driver;
    private Base_Action baseAction;
    private Order_Page orderPage;
    private Cart_Action cartAction;
    private WithDraw_Action withdrawAction;
    private Base_Page basePage;
    private Information_Page inforPage;
    private Cart_Page scart_Page;


    public Order_Action(WebDriver driver) {
        this.driver = driver;
        this.orderPage = new Order_Page(driver);
        this.cartAction = new Cart_Action(driver);
        this.baseAction = new Base_Action(driver);
        this.withdrawAction = new WithDraw_Action(driver);
        this.basePage = new Base_Page(driver);
        this.inforPage = new Information_Page(driver);
        this.scart_Page = new Cart_Page(driver);
    }

    public void clickButton(WebElement element) {
        baseAction.clickElement(element);
    }

    public void enterText(WebElement element, String text) {
        baseAction.clearAndEnterText(element, text);
    }

    public void enterInformationOrder(String name, String phone, String city, String district, String ward,
            String location, String note) {
        enterText(orderPage.getTxtusername(), name);
        enterText(orderPage.getTxtphone(), phone);
        enterText(orderPage.getTxtcity(), city);
        enterText(orderPage.getTxtdistrict(), district);
        enterText(orderPage.getTxtward(), ward);
        enterText(orderPage.getTxtaddress(), location);
        enterText(orderPage.getTxtNote(), note);
        clickButton(orderPage.getBtnOrder());
    }

    public void selectBank(String bank) {
        clickButton(orderPage.getSelectbank());
        if (bank != null && !bank.trim().isEmpty()) {
            try {
                int bankIndex = (int) Double.parseDouble(bank);
                clickButton(orderPage.getBankList().get(bankIndex));
            } catch (Exception e) {
                System.out.println("Không thể chọn ngân hàng: " + bank);
            }
        }
    }

    public void selectPaymentMethod(String description,String bank) {
        if (description != null && !description.trim().isEmpty()) {
            String lowerDesc = description.toLowerCase();
            if (lowerDesc.contains("ngân hàng")) {
                clickButton(orderPage.getBtnMethodPayment().get(0));
                selectBank(bank);
            } else if (lowerDesc.contains("điểm trực tiếp")) {
                clickButton(orderPage.getBtnMethodPayment().get(1));
            } else if (lowerDesc.contains("điểm gián tiếp")) {
                clickButton(orderPage.getBtnMethodPayment().get(2));
            } else {
                System.out.println("Không tìm thấy phương thức thanh toán phù hợp với mô tả: " + description);
            }
        } else {
            System.out.println("Mô tả phương thức thanh toán không được để trống");
        }
    }

    public void Order(String name, String phone, String city, String district, String ward, String location,
            String bank, String note, String description, String typeCase) {
        switch (typeCase) {
            case "One":
                cartAction.SCartToOrder("Three");
                selectPaymentMethod("ngân hàng",bank);
                enterInformationOrder(name, phone, city, district, ward, location, note);
                break;
            case "Two":
                cartAction.SCartToOrder("Three");
                selectPaymentMethod("điểm trực tiếp",bank);
                enterInformationOrder(name, phone, city, district, ward, location, note);
                break;
            case "Three":
                cartAction.SCartToOrder("Three");
                selectPaymentMethod("điểm gián tiếp",bank);    
                enterInformationOrder(name, phone, city, district, ward, location, note);
                break;
            case "Four":
                caseFour(description,bank,name, phone, city, district, ward, location, note);
                break;
            default:
                System.out.println("Invalid typecase: " + typeCase);
        }
    }

    private void caseFour(String description,String bank,String name, String phone, String city, String district, String ward, String location, String note) {
		clickButton(basePage.getBtnAccount_2());
		clickButton(inforPage.getLinkInfor());
        String selectedVoucher = withdrawAction.checkVoucherDiscount("1000000");
        cartAction.addProductsAndGoToCart();
		clickButton(scart_Page.getSelectAllCheckbox());
		clickButton(scart_Page.getSelectCheckboxDongy());
		clickButton(scart_Page.getBtnToThanhToan());    
        withdrawAction.pasteVoucher();
        clickButton(orderPage.getBtnCouponApply());
        selectPaymentMethod(description,bank);
        enterInformationOrder(name, phone, city, district, ward, location, note);
	}
}

