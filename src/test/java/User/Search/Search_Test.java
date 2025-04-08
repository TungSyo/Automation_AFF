package User.Search;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Base.*;
import Driver.Driver_Manager;
import Utils.ConfigUtil;
import Utils.Excel_Util;
import Utils.ScreenShotUtil;
import Report.Extend_Report;

public class Search_Test extends Base_Test {
    private static final String STEP_SHEET = "Step";

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() {
        return Search_Data_Generator.generateSearchData();
    }

    @Test(dataProvider = "searchData", groups = { "Success", "Fail" })
    public void testSearch(String search, String result, String title, String link, String description,
            String testType) throws Exception {

        String category = testType.equalsIgnoreCase("Fail") ? "Search_Data_Fail" : "Search_Data_Pass";
        Extend_Report.startTest("Search Test - " + description, category);
        Base_Action baseAction = new Base_Action(Driver_Manager.getDriver());
        Search_Action searchActions = new Search_Action(Driver_Manager.getDriver());

        try {
            // Đọc các bước test từ file Excel
            Excel_Util excelSteps = new Excel_Util(Base_Constant.STEP_FILE, STEP_SHEET);
            int rowCount = excelSteps.getRowCount();

            // Thực hiện từng bước test
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
                        searchActions.searchProduct(search);
                        Extend_Report.logInfo("Thực hiện tìm kiếm với từ khóa: " + search);
                        break;

                    case "verifynotion":
                        if (!search.trim().isEmpty()) {
                            baseAction.handleVerification(baseAction.verifyNotion(result), "thông báo", result);
                        }
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
                        throw new IllegalArgumentException("Hành động không xác định: " + action);
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