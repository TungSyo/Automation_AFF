package User.ForgotPass;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Base.*;
import Driver.Driver_Manager;
import User.ForgotPass.*;
import User.Login.*;
import Utils.ConfigUtil;
import Utils.Excel_Util;
import Utils.ScreenShotUtil;
import Report.Extend_Report;

@SuppressWarnings("unused")

public class Forgot_Pass_Test extends Base_Test {

    private static final String DATA_FILE = "src/test/resources/data/User_Data.xlsx";
    private static final String STEP_FILE = "src/test/resources/step/Step.xlsx";
    private static final String DATA_SHEET = "ForgotPass";
    private static final String STEP_SHEET = "Step";

    @DataProvider(name = "forgotpassData")
    public Object[][] getForgotPassData() throws IOException, InvalidFormatException {
        Excel_Util excel = new Excel_Util(DATA_FILE, DATA_SHEET);
        int rowCount = excel.getRowCount();
        int colCount = 9;

        Object[][] data = new Object[rowCount - 1][colCount];
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = excel.getCellData(i, "Email");
            data[i - 1][1] = excel.getCellData(i, "NewPass");
            data[i - 1][2] = excel.getCellData(i, "ComfirmPass");
            data[i - 1][3] = excel.getCellData(i, "Result");
            data[i - 1][4] = excel.getCellData(i, "Title");
            data[i - 1][5] = excel.getCellData(i, "Link");
            data[i - 1][6] = excel.getCellData(i, "Description");
            data[i - 1][7] = excel.getCellData(i, "TestType");
            data[i - 1][8] = excel.getCellData(i, "Pop3");
        }

        return data;
    }

    @Test(dataProvider = "forgotpassData", groups = { "Success", "Fail" })
    public void testForgotPass(String email, String newPass, String comfirmPass, String result, String title,
            String link,
            String description, String testType, String pop3)
            throws Exception {

        String category = testType.equalsIgnoreCase("Fail") ? "ForgotPass_Data_Fail" : "ForgotPass_Data_Pass";
        Extend_Report.startTest("Forgot_Pass Test - " + description, category);

        Base_Action baseAction = new Base_Action(Driver_Manager.getDriver());
        Forgot_Pass_Action forgotpassActions = new Forgot_Pass_Action(Driver_Manager.getDriver());

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
                        forgotpassActions.forgotPass(email, newPass, comfirmPass, pop3, description);
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
            String screenshotPath = ScreenShotUtil.captureScreenshot(Driver_Manager.getDriver(),
                    "testForgotPass_Exception", "ForgotPassTest");
            Extend_Report.attachScreenshot(screenshotPath);
            baseAction.handleTestException(e, description);
            throw e;
        } finally {
            Extend_Report.endTest();
        }
    }
}