package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap;

public class ConfigUtil {
    private static Map<String, Properties> configMap = new HashMap<>();
    private static final String CONFIG_DIR = "src/test/resources/";

    static {
        // Load sẵn các file properties thường dùng
        loadConfig("config");
        loadConfig("account");
        loadConfig("environment"); 
    }

    private static void loadConfig(String configName) {
        String filePath = CONFIG_DIR + configName + ".properties";
        File configFile = new File(filePath);
        if (!configFile.exists()) {
            System.err.println("⚠️ Warning: Configuration file not found: " + filePath);
            return;
        }
        try (FileInputStream fileIn = new FileInputStream(configFile)) {
            Properties props = new Properties();
            props.load(fileIn);
            configMap.put(configName, props);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to load configuration file: " + filePath);
        }
    }

    public static String getProperty(String configName, String key) {
        Properties props = configMap.get(configName);
        if (props == null) {
            loadConfig(configName);
            props = configMap.get(configName);
        }
        if (props != null) {
            return props.getProperty(key, "").trim();
        }
        return "";
    }

    public static void setProperty(String configName, String key, String value) {
        String filePath = CONFIG_DIR + configName + ".properties";
        Properties props = configMap.get(configName);
        if (props == null) {
            props = new Properties();
            configMap.put(configName, props);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            props.setProperty(key, value);
            props.store(fileOut, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to save configuration property: " + key);
        }
    }

    public static void reloadConfig(String configName) {
        loadConfig(configName);
    }

    public static void reloadAllConfigs() {
        configMap.clear();
        loadConfig("config");
        loadConfig("admin");
        loadConfig("information");
    }

    // Các phương thức tiện ích cho config mặc định
    public static String getLink() throws IOException {
        String link = getProperty("config", "url_2");
        if (link.isEmpty()) {
            throw new IOException("❌ URL is not found in the properties file.");
        }
        return link;
    }

    public static String getEmail() throws IOException {
        String email = getProperty("config", "username");
        if (email.isEmpty()) {
            throw new IOException("❌ Email is not found in the properties file.");
        }
        return email;
    }

    public static String getPassword() throws IOException {
        String password = getProperty("config", "password");
        if (password.isEmpty()) {
            throw new IOException("❌ Password is not found in the properties file.");
        }
        return password;
    }

    public static String getAppUrl() {
        return getProperty("config", "app.url");
    }
}
