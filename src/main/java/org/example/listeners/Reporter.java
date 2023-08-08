package org.example.listeners;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.layout.MarkerPatternSelector;
import org.example.browserFactory.Browsers;
public class Reporter extends Browsers {
    private static ExtentReports extentReports;
    private static ExtentSparkReporter spark;

      public static ExtentReports reporterInit() {
        extentReports = new ExtentReports();
        spark = new ExtentSparkReporter("./target/Spark.html");
        spark.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
        extentReports.attachReporter(spark);

        File extentConfig = new File(
                "./src/test/resources/config/extent-report-config.json");

        try {
            spark.loadJSONConfig(extentConfig);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        return extentReports;
    }

    /**
     * This method is for printing the info statement and log
     *
     * @param description
     * @return String
     */
    public static String info(String description) {
        ExtentReportListeners.getExtentTest().info(description);
        logger.info(description);
        return description;
    }

    /**
     * This method is for printing the pass statement and log
     *
     * @param description
     * @return String
     */
    public static String pass(String description) {
        ExtentReportListeners.getExtentTest().pass(description);
        logger.log(Level.getLevel("PASS"), description);
        //logger.info(description);
        return description;
    }

    /**
     * This method is for printing the fail statement and log
     *
     * @param description
     * @return String
     */
    public static String fail(String description) {
        ExtentReportListeners.getExtentTest().info(description);
        logger.log(Level.getLevel("FAIL"), description);
        //logger.error(description);
        return description;
    }

    /**
     * This method is for printing the skip statement and log
     *
     * @param description
     * @return String
     */
    public static String skip(String description) {
        ExtentReportListeners.getExtentTest().skip(description);
        logger.log(Level.getLevel("SKIP"), description);
        //logger.info(description);
        return description;
    }

    /**
     * This methods return the name of the report using date
     *
     * @return
     * @throws Exception
     */
    public String getReportName() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();
        String ReportFileName = "PlayWrightAutomationReport" + "_" + dateFormat.format(date) + ".html";
        return System.getProperty("user.dir") + "/Reports/" + ReportFileName;
    }

    
}
