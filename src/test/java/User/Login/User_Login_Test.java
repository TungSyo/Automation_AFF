package User.Login;

import java.io.IOException;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.Base_Action;
import Base.Base_Test;

import Driver.Driver_Manager;
import User.Login.User_Login_Action;
import Utils.ConfigUtil;
import Utils.Excel_Util;
import Utils.ScreenShotUtil;
import Report.Extend_Report;

@SuppressWarnings("unused")

public class User_Login_Test extends Base_Test {

    private static final String DATA_FILE = "src/test/resources/data/User_Data.xlsx";
    private static final String DATA_SHEET = "Login";
    private static final String STEP_SHEET = "Step";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException, InvalidFormatException {
        Excel_Util excelUtil = new Excel_Util(DATA_FILE, DATA_SHEET);
        int rowCount = excelUtil.getRowCount();
        int colCount = 8;

        Object[][] data = new Object[rowCount - 1][colCount];
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = excelUtil.getCellData(i, "Email");
            data[i - 1][1] = excelUtil.getCellData(i, "Password");
            data[i - 1][2] = excelUtil.getCellData(i, "Result");
            data[i - 1][3] = excelUtil.getCellData(i, "Title");
            data[i - 1][4] = excelUtil.getCellData(i, "Link");
            data[i - 1][5] = excelUtil.getCellData(i, "Description");
            data[i - 1][6] = excelUtil.getCellData(i, "TestType");
        }
        return data;
    }

    @Test(dataProvider = "loginData", groups = { "Success", "Fail" })
    public void testLogin(String email, String password, String result, String title, String link, String description,
            String testType)
            throws Exception {

        String category = testType.equalsIgnoreCase("Fail") ? "Login_Data_Fail" : "Login_Data_Pass";
        Extend_Report.startTest("Login Test - " + description, category);
        Base_Action baseAction = new Base_Action(Driver_Manager.getDriver());
        User_Login_Action loginActions = new User_Login_Action(Driver_Manager.getDriver());

        try {
            Excel_Util excelSteps = new Excel_Util("src/test/resources/step/Step.xlsx", STEP_SHEET);
            int rowCount = excelSteps.getRowCount();

            for (int i = 1; i < rowCount; i++) {
                String action = excelSteps.getCellData(i, "Action Keyword");

                switch (action.toLowerCase()) {
                    case "open":
                        Extend_Report.logInfo("Mở trình duyệt...");
                        break;

                    case "navigate":
                        String url_user = ConfigUtil.getProperty("url_user");
                        baseAction.navigate(url_user);
                        Extend_Report.logInfo("Điều hướng đến " + url_user);
                        break;

                    case "action":
                        Extend_Report.logInfo("Thực hiện test case: " + description);
                        loginActions.login(email, password);
                        break;

                    case "verifynotion":
                        baseAction.handleVerification(baseAction.verifyNotion(result), "thông báo", result);
                        break;

                    case "verifytitle":
                        baseAction.handleVerification(baseAction.verifyTitle(title), "tiêu đề", title);
                        break;

                    case "verifylink":
                        baseAction.handleVerification(baseAction.verifyLink(link), "link", link);
                        break;

                    case "close":
                        Extend_Report.logInfo("Đóng trình duyệt...");
                        break;
                    default:
                        throw new IllegalArgumentException("Hành động chưa xác định: " + action);
                }
            }
        } catch (Exception e) {
            String screenshotPath = ScreenShotUtil.captureScreenshot(Driver_Manager.getDriver(), "testLogin_Exception",
                    "LoginTest");
            Extend_Report.attachScreenshot(screenshotPath);
            baseAction.handleTestException(e, description);
            throw e;
        } finally {
            Extend_Report.endTest();
        }
    }
}