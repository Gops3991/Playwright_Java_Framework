package org.example.utilities;

import java.util.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;
    private static final Map<String, String> ConfigMap = new HashMap<>();

    public static String OSName, OSArchitecture, OSVersion, OSBit;

    public static String test;
    public static String totalTestCasesCount, passedTestCasesCount, failedTestCasesCount, skippedTestCasesCount;
    public static String vendorBrand;
    File src;

    public static String templateDir;
    public static String downloadDir;
    public static String exportsDir;
    public static String excelFileDir;

    /**
     * This method has implemented Singleton Pattern to read config file
     *
     */
    public void readConfigFile() {

            src = new File("./src/test/resources/config/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            prop = new Properties();
            prop.load(fis);
            for (Map.Entry<Object, Object> entry : prop.entrySet()) {
                ConfigMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
            }
        } catch (Exception e) {
            System.out.println("Exception is : " + e.getMessage());
        }
    }

    /**
     * This method returns the value from the configuration file by passing the name
     * of the key
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String get(String key) throws Exception {
        if (Objects.isNull(key) || Objects.isNull(ConfigMap.get(key))) {
            throw new Exception("property name " + key + " is not found. Please check config.properties file");
        }
        return ConfigMap.get(key).trim();
    }

    /**
     * Initializes Configuration Variables
     */
    public void initConfig() {
        OSName = System.getProperty("os.name");
        OSArchitecture = System.getProperty("os.arch");
        OSVersion = System.getProperty("os.version");
        OSBit = System.getProperty("sun.arch.data.model");

        templateDir = System.getProperty("user.dir") + File.separator + "Import_Export" + File.separator + "templates"
                + File.separator;
        downloadDir = System.getProperty("user.dir") + File.separator + "Import_Export" + File.separator + "downloads"
                + File.separator;
        exportsDir = System.getProperty("user.dir") + File.separator + "Import_Export" + File.separator + "exports"
                + File.separator;
        excelFileDir = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "Excel Files" + File.separator;
    }
}

