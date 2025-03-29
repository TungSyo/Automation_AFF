package User.Cart;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.*;
import Driver.Driver_Manager;
import User.Cart.*;
import User.Login.*;
import Utils.ConfigUtil;
import Utils.Excel_Util;
import Utils.ScreenShotUtil;
import Report.Extend_Report;

@SuppressWarnings("unused")

public class Cart_Delete_Test extends Base_Test {

    private static final String DATA_SHEET = "SCartDel";
    private static final String STEP_SHEET = "Step";

    @DataProvider(name = "scartdeleteData")
    public Object[][] getSCartDeleteData() throws IOException, InvalidFormatException {
        Excel_Util excel = new Excel_Util(Base_Constant.USER_DATA_FILE, DATA_SHEET);
        int rowCount = excel.getRowCount();
        int colCount = 6;
        
        Object[][] data = new Object[rowCount - 1][colCount];
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = excel.getCellData(i, "Result");
            data[i - 1][1] = excel.getCellData(i, "Title");
            data[i - 1][2] = excel.getCellData(i, "Link");
            data[i - 1][3] = excel.getCellData(i, "Description");
            data[i - 1][4] = excel.getCellData(i, "TestType");
            data[i - 1][5] = excel.getCellData(i, "TypeCase");
        }

        return data;
    }

    @Test(dataProvider = "scartdeleteData", groups = { "Success", "Fail" })
    public void testSCartDelete(String result, String title, String link, String description, String testType,
            String typeCase)
            throws Exception {

        String category = testType.equalsIgnoreCase("Fail") ? "SCart_Data_Fail" : "SCart_Data_Pass";

        Extend_Report.startTest("SCartAdd Test - " + description, category);

        Base_Action baseAction = new Base_Action(Driver_Manager.getDriver());
        Cart_Delete_Action cartDeleteActions = new Cart_Delete_Action(Driver_Manager.getDriver());
        User_Login_Action loginActions = new User_Login_Action(Driver_Manager.getDriver());

        try {
            Excel_Util excelSteps = new Excel_Util(Base_Constant.STEP_FILE, STEP_SHEET);

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
                        cartDeleteActions.deleteProduct(typeCase);
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
            String screenshotPath = ScreenShotUtil.captureScreenshot(Driver_Manager.getDriver(), "testSCartDelete_Exception", "CartDeleteTest");
            Extend_Report.attachScreenshot(screenshotPath);
            baseAction.handleTestException(e, description);
            throw e;
        } finally {
            Extend_Report.endTest();
        }
    }
}