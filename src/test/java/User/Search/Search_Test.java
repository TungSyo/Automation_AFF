package User.Search;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import Base.Base_Action;
import Base.Base_Test;
import Driver.Driver_Manager;
import User.Login.User_Login_Action;
import Utils.ConfigUtil;
import Utils.Excel_Util;
import Utils.ScreenShotUtil;
import Report.Extend_Report;

@SuppressWarnings("unused")

public class Search_Test extends Base_Test {

    private static final String DATA_FILE = "src/test/resources/data/User_Data.xlsx";
    private static final String DATA_SHEET = "Search";
    private static final String STEP_SHEET = "Step";

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() throws IOException, InvalidFormatException {
        Excel_Util excel = new Excel_Util(DATA_FILE, DATA_SHEET);
        
        int rowCount = excel.getRowCount();
        int colCount = 7;

        Object[][] data = new Object[rowCount - 1][colCount];
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = excel.getCellData(i, "Search");
            data[i - 1][1] = excel.getCellData(i, "Result");
            data[i - 1][3] = excel.getCellData(i, "Title");
            data[i - 1][4] = excel.getCellData(i, "Link");
            data[i - 1][5] = excel.getCellData(i, "Description");
            data[i - 1][6] = excel.getCellData(i, "TestType");
        }
        return data;
    }

    @Test(dataProvider = "searchData", groups = { "Success", "Fail" })
    public void testSearch(String search, String result, String title, String link, String description,
            String testType)
            throws Exception {

        String category = testType.equalsIgnoreCase("Fail") ? "Search_Data_Fail" : "Search_Data_Pass";
        Extend_Report.startTest("Search Test - " + description, category);
        Base_Action baseAction = new Base_Action(Driver_Manager.getDriver());
        Search_Action searchActions = new Search_Action(Driver_Manager.getDriver());

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
                        searchActions.searchProduct(search);
                        Extend_Report.logInfo("Thực hiện test case: " + description);
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
            String screenshotPath = ScreenShotUtil.captureScreenshot(Driver_Manager.getDriver(), "testSearch_Exception",
                    "SearchTest");
            Extend_Report.attachScreenshot(screenshotPath);
            baseAction.handleTestException(e, description);
            throw e;
        } finally {
            Extend_Report.endTest();
        }
    }
}