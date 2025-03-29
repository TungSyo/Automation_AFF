package User.Login;

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

public class User_Login_Test extends Base_Test {
    private Base_Action baseAction;
    private User_Login_Action loginAction;
    private static final String DATA_SHEET = "Login";
    private static final String STEP_SHEET = "Step";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException, InvalidFormatException {
        Excel_Util excel = new Excel_Util(Base_Constant.USER_DATA_FILE, DATA_SHEET);
        int rowCount = excel.getRowCount();
        
        Object[][] data = new Object[rowCount - 1][1];
        for (int i = 1; i < rowCount; i++) {
            data[i-1][0] = new User_Login_Data(
                excel.getCellData(i, "Email"),
                excel.getCellData(i, "Password"),
                excel.getCellData(i, "Result"),
                excel.getCellData(i, "Title"),
                excel.getCellData(i, "Link"),
                excel.getCellData(i, "Description"),
                excel.getCellData(i, "TestType")
            );
        }
        return data;
    }

    @Test(dataProvider = "loginData", groups = { "Success", "Fail" })
    public void testLogin(User_Login_Data data) {
        baseAction = new Base_Action(Driver_Manager.getDriver());
        loginAction = new User_Login_Action(Driver_Manager.getDriver());

        try {
            String category = data.getTestType().equalsIgnoreCase("Fail") ? "Login_Data_Fail" : "Login_Data_Pass";
            Extend_Report.startTest("Login Test - " + data.getDescription(), category);

            Excel_Util excelSteps = new Excel_Util(Base_Constant.STEP_FILE, STEP_SHEET);
            executeTestSteps(excelSteps, data);

        } catch (Exception e) {
            handleTestException(e, data);
        } finally {
            Extend_Report.endTest();
        }
    }

    private void executeTestSteps(Excel_Util excelSteps, User_Login_Data data) throws Exception {
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
                    loginAction.login(data.getEmail(), data.getPassword());
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

    private void handleTestException(Exception e, User_Login_Data data) {
        try {
            String screenshotPath = ScreenShotUtil.captureScreenshot(Driver_Manager.getDriver(), "testLogin_Exception", "LoginTest");
            Extend_Report.attachScreenshot(screenshotPath);
            baseAction.handleTestException(e, data.getDescription());
        } catch (Exception ex) {
            System.out.println("Lỗi khi xử lý exception: " + ex.getMessage());
        }
    }
} 