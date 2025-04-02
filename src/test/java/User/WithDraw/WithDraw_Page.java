package User.WithDraw;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WithDraw_Page {
    public WebDriver driver;
    @FindBy(xpath = "//button[contains(.,'Đổi điểm thưởng')]")
    private WebElement btnWithdraw;
    @FindBy(xpath = "//input[contains(@id,'minmaxfraction')]")
    private WebElement inputWithdraw;
    @FindBy(xpath = "(//input[contains(@type,'radio')])[1]")
    private WebElement DirectWithdraw;
    @FindBy(xpath = "(//input[contains(@type,'radio')])[2]")
    private WebElement IndirectWithdraw;
    @FindBy(xpath = "//button[@type='submit'][contains(.,'Tạo mã giảm')]")
    private WebElement btnCreateVoucher;
    @FindBy(xpath = "//button[@type='submit'][contains(.,'Tạo mã giảm')]")
    private WebElement btnWithdrawConfirm;
    @FindBy(xpath = "/html[1]/body[1]/app-root[1]/app-client-layout[1]/div[1]/app-user-infomation[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/ul[1]/li/span[2]/*")
    private List<WebElement> CoppyVoucher;

    public WithDraw_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getBtnWithdraw() {
        return btnWithdraw;
    }

    public WebElement getInputWithdraw() {
        return inputWithdraw;
    }

    public WebElement getDirectWithdraw() {
        return DirectWithdraw;
    }

    public WebElement getIndirectWithdraw() {
        return IndirectWithdraw;
    }

    public WebElement getBtnWithdrawConfirm() {
        return btnWithdrawConfirm;
    }

    public List<WebElement> getCoppyVoucher() {
        return CoppyVoucher;
    }

    public WebElement getBtnCreateVoucher() {
        return btnCreateVoucher;
    }
}
