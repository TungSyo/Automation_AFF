package User.Cart.CartAdd;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.*;
import Driver.Driver_Manager;
import Utils.ConfigUtil;
import Utils.Excel_Util;
import Utils.ScreenShotUtil;
import Report.Extend_Report;
import User.Login.User_Login_Action;
@SuppressWarnings("unused")
public class Cart_Add_Test extends Base_Test {
    private Base_Action baseAction;
    private Cart_Add_Action cartAddAction;
    private User_Login_Action loginActions;
    private static final String DATA_SHEET = "CartAdd";
    private static final String STEP_SHEET = "Step";

    @DataProvider(name = "cartAddData")
    public Object[][] getCartAddData() throws IOException, InvalidFormatException {
        Excel_Util excel = new Excel_Util(Base_Constant.USER_DATA_FILE, DATA_SHEET);
        int rowCount = excel.getRowCount();
        
        Object[][] data = new Object[rowCount - 1][1];
        for (int i = 1; i < rowCount; i++) {
            data[i-1][0] = new Cart_Add_Data(
                excel.getCellData(i, "Product"),
                excel.getCellData(i, "Quanlity"),
                excel.getCellData(i, "Price"),
                excel.getCellData(i, "Result"),
                excel.getCellData(i, "Title"),
                excel.getCellData(i, "Link"),
                excel.getCellData(i, "Description"),
                excel.getCellData(i, "TestType"),
                excel.getCellData(i, "TypeCase")
            );
        }
        return data;
    }

    @Test(dataProvider = "cartAddData", groups = { "Success", "Fail" })
    public void testCartAdd(Cart_Add_Data data) throws IOException {
        baseAction = new Base_Action(Driver_Manager.getDriver());
        cartAddAction = new Cart_Add_Action(Driver_Manager.getDriver());
        loginActions = new User_Login_Action(Driver_Manager.getDriver());
        
        try {
            String category = data.getTestType().equalsIgnoreCase("Fail") ? "CartAdd_Data_Fail" : "CartAdd_Data_Pass";
            Extend_Report.startTest("Cart Add Test - " + data.getDescription(), category);

            Excel_Util excelSteps = new Excel_Util(Base_Constant.STEP_FILE, STEP_SHEET);
            executeTestSteps(excelSteps, data);

        } catch (Exception e) {
            handleTestException(e, data);
        } finally {
            Extend_Report.endTest();
        }
    }

    private void executeTestSteps(Excel_Util excelSteps, Cart_Add_Data data) throws Exception {
        int rowCount = excelSteps.getRowCount();

        for (int i = 1; i < rowCount; i++) {
            String action = excelSteps.getCellData(i, "Action Keyword");

            switch (action.toLowerCase()) {
                case "open":
                    Extend_Report.logInfo("Mở trình duyệt...");
                    break;

                case "navigate":
                    String url_user = ConfigUtil.getProperty("environment", "url_user");
                    baseAction.navigate(url_user);
                    Extend_Report.logInfo("Điều hướng đến " + url_user);
                    break;

                case "action":
                    Extend_Report.logInfo("Thực hiện test case: " + data.getDescription());
                    cartAddAction.addToSCart(data.getProduct(), data.getQuanlity(), data.getPrice(), data.getTypeCase());
                    break;

                case "verifynotion":
                    baseAction.handleVerification(baseAction.verifyNotion(data.getResult()),
                            "thông báo", data.getResult());
                    break;

                case "verifytitle":
                    baseAction.handleVerification(baseAction.verifyTitle(data.getTitle()),
                            "tiêu đề", data.getTitle());
                    break;

                case "verifylink":
                    baseAction.handleVerification(baseAction.verifyLink(data.getLink()),
                            "link", data.getLink());
                    break;

                case "close":
                    Extend_Report.logInfo("Đóng trình duyệt...");
                    break;

                default:
                    throw new IllegalArgumentException("Hành động chưa xác định: " + action);
            }
        }
    }

    private void handleTestException(Exception e, Cart_Add_Data data) throws IOException {
        String screenshotPath = ScreenShotUtil.captureScreenshot(Driver_Manager.getDriver(), "testCartAdd_Exception", "CartAddTest");
        Extend_Report.attachScreenshot(screenshotPath);
        Extend_Report.logFail("Kiểm tra thêm vào giỏ hàng thất bại cho: " + data.getDescription() + " với lỗi: " + e.getMessage());
        System.out.println("Ảnh chụp màn hình đã được lưu tại: " + screenshotPath);
    }
}