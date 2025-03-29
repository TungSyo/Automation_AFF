package Base;

import Utils.ConfigUtil;

public class Base_Constant {
    // Đường dẫn file Excel từ config
    public static final String USER_DATA_FILE = getConfigValue("data_file", "src/test/resources/data/User_Data.xlsx");
    public static final String ADMIN_DATA_FILE = getConfigValue("data_file", "src/test/resources/data/Admin_Data.xlsx");
    public static final String STEP_FILE = getConfigValue("step_file", "src/test/resources/step/Step.xlsx");

    private static String getConfigValue(String key, String defaultValue) {
        String value = ConfigUtil.getProperty("environment", key);
        return value.isEmpty() ? defaultValue : value;
    }

    public enum ConfigType {
        USER("user.properties"),
        ADMIN("admin.properties"),
        INFORMATION("information.properties");
        
        private String fileName;
        
        ConfigType(String fileName) {
            this.fileName = fileName;
        }
        
        public String getFileName() {
            return fileName;
        }
    }
} 