package User.WithDraw;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

import Base.Base_Action;
import User.Order.Order_Page;
import User.Information.Information_Page;
import Base.Base_Page;

@SuppressWarnings("unused")
public class WithDraw_Action {
    public WebDriver driver;
    private WithDraw_Page withdrawPage;
    private Base_Action baseAction;
    private Order_Page orderPage;
    private Information_Page inforPage;
    private Base_Page basePage;

    public WithDraw_Action(WebDriver driver) {
        this.driver = driver;
        this.withdrawPage = new WithDraw_Page(driver);
        this.baseAction = new Base_Action(driver);
        this.orderPage = new Order_Page(driver);
        this.inforPage = new Information_Page(driver);
        this.basePage = new Base_Page(driver);
    }

    public void clickButton(WebElement element) {
        baseAction.clickElement(element);
    }

    public void enterText(WebElement element, String text) {
        baseAction.clearAndEnterText(element, text);
    }


    public void pasteVoucher() {
        clickButton(orderPage.getInputCoupon());
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
    }

    public String checkVoucherDiscount(String point) {
        List<WebElement> vouchers = withdrawPage.getCoppyVoucher();
        String selectedVoucher = null;
    
        if (vouchers != null && !vouchers.isEmpty()) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(vouchers.size());
            WebElement selectedElement = vouchers.get(randomIndex);
            selectedVoucher = selectedElement.getText(); 
            clickButton(selectedElement);
            System.out.println("Có voucher giảm giá, sử dụng voucher");
        } else {
            System.out.println("Không có voucher, tạo mới...");
            directWithdraw(point);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            vouchers = withdrawPage.getCoppyVoucher();
            
            if (vouchers != null && !vouchers.isEmpty()) {
                WebElement newVoucher = vouchers.get(vouchers.size() - 1);
                selectedVoucher = newVoucher.getText();
                clickButton(newVoucher);
                System.out.println("Đã tạo và copy voucher mới");
            } else {
                System.out.println("Lỗi: Không thể tạo voucher mới.");
            }
        }   
    
        return selectedVoucher;
    }
    
    
    public void directWithdraw(String point) {
        clickButton(withdrawPage.getBtnWithdraw());
        clickButton(withdrawPage.getDirectWithdraw());
        enterText(withdrawPage.getInputWithdraw(), point);
        clickButton(withdrawPage.getBtnCreateVoucher());
    }

    // public void indirectWithdraw(String point) {
    // clickButton(withdrawPage.getBtnWithdraw());
    // enterText(withdrawPage.getInputWithdraw(), point);
    // clickButton(withdrawPage.getIndirectWithdraw());
    // clickButton(withdrawPage.getBtnWithdrawConfirm());
    // }
}
