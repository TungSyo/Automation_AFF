package User.Order;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.*;
import Driver.Driver_Manager;
import User.Login.User_Login_Action;
import Utils.ConfigUtil;
import Utils.Excel_Util;
import Utils.ScreenShotUtil;
import Report.Extend_Report;

public class Order_Test extends Base_Test {
    private Base_Action baseAction;
    private Order_Action orderAction;
    private User_Login_Action loginAction;

    private static final String DATA_SHEET = "Order";
    private static final String STEP_SHEET = "Step";

    @DataProvider(name = "orderData")
    public Object[][] getOrderData() throws IOException, InvalidFormatException {
        Excel_Util excel = new Excel_Util(Base_Constant.USER_DATA_FILE, DATA_SHEET);
        int rowCount = excel.getRowCount();
        
        Object[][] data = new Object[rowCount - 1][1];
        for (int i = 1; i < rowCount; i++) {
            data[i-1][0] = new Order_Data(
               excel.getCellData(i, "Name"),
               excel.getCellData(i, "Phone"),
               excel.getCellData(i, "City"),
               excel.getCellData(i, "District"),
               excel.getCellData(i, "Ward"),
               excel.getCellData(i, "Location"),
               excel.getCellData(i, "Bank"),
               excel.getCellData(i, "Note"),
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

    @Test(dataProvider = "orderData", groups = { "Success", "Fail" })
    public void testOrder(Order_Data data) {
        baseAction = new Base_Action(Driver_Manager.getDriver());
        orderAction = new Order_Action(Driver_Manager.getDriver());
        loginAction = new User_Login_Action(Driver_Manager.getDriver()); 

        try {
            String category = data.getTestType().equalsIgnoreCase("Fail") ? "Order_Data_Fail" : "Order_Data_Pass";
            Extend_Report.startTest("Order Test - " + data.getDescription(), category);

            Excel_Util excelSteps = new Excel_Util(Base_Constant.STEP_FILE, STEP_SHEET);
            executeTestSteps(excelSteps, data);

        } catch (Exception e) {
            handleTestException(e, data);
        } finally {
            Extend_Report.endTest();
        }
    }

    private void executeTestSteps(Excel_Util excelSteps, Order_Data data) throws Exception {
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
                    String name = ConfigUtil.getProperty("account", "username_admin");
                    String password = ConfigUtil.getProperty("account", "password_admin");
                    loginAction.login(name, password);
                    orderAction.Order(data.getName(), data.getPhone(), data.getCity(), data.getDistrict(), data.getWard(), data.getLocation(), data.getBank(), data.getNote(), data.getDescription(), data.getTypeCase());
                    break;

                case "verifynotion":
                    baseAction.handleVerification(baseAction.verifyNotion(data.getResult()),  "thông báo", data.getResult());
                    break;

                case "verifytitle":
                    baseAction.handleVerification(baseAction.verifyTitle(data.getTitle()), "tiêu đề", data.getTitle());
                    break;

                case "verifylink":
                    baseAction.handleVerification(baseAction.verifyLink(data.getLink()), "link", data.getLink());
                    break;

                case "close":
                    Extend_Report.logInfo("Đóng trình duyệt...");
                    break;

                default:
                    throw new IllegalArgumentException("Hành động chưa xác định: " + action);
            }
        }
    }

    private void handleTestException(Exception e, Order_Data data) {
        try {
            String screenshotPath = ScreenShotUtil.captureScreenshot(Driver_Manager.getDriver(), "testOrder_Exception", "OrderTest");
            Extend_Report.attachScreenshot(screenshotPath);
            baseAction.handleTestException(e, data.getDescription());
        } catch (Exception ex) {
            System.out.println("Lỗi khi xử lý exception: " + ex.getMessage());
        }
    }
} 