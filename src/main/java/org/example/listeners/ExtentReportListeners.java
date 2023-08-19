package org.example.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.example.common.Commons;
import org.testng.*;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.ITestAnnotation;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;

import static org.example.browserFactory.Browsers.logger;
import static org.example.listeners.Reporter.reporterInit;

public class ExtentReportListeners implements ITestListener, IAnnotationTransformer, IRetryAnalyzer {
    private static ExtentReports extentReports;
    private static ExtentSparkReporter spark;

    int counter = 0;
    int retryLimit = 1;

    public static ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<ExtentTest>();

    public static ExtentTest getExtentTest() {
        return threadLocalTest.get();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " started!");
        //create Test

        ExtentTest extentTest = extentReports.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        threadLocalTest.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        Reporter.pass(result.getMethod().getMethodName() + " Succeeded!");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        Commons commons = new Commons();
        getExtentTest().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(commons.takeScreenshot(), result.getMethod().getMethodName()).build());
        Reporter.fail(result.getMethod().getMethodName() + " failed");
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        getExtentTest().skip(result.getMethod().getMethodName() + " has been skipped");
        logger.info(result.getMethod().getMethodName() + " Skipped!");
        extentReports.removeTest(getExtentTest());
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info("Test Success with Percentage!");
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("On..Start");
        extentReports = reporterInit();
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
//logger.info(("Test Suite is ending!"));
        extentReports.flush();

        // Open Report and trace after the run is complete
        try {
            Desktop.getDesktop().browse(new File("target", "Spark.html").toURI());
            Desktop.getDesktop().browse(new URI("https://trace.playwright.dev"));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean retry(ITestResult result) {
        //Reporter.info(result.getMethod().getMethodName() + " is retrying");
        if (counter < retryLimit) {
            counter++;
            return true;
        }
        return false;
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(ExtentReportListeners.class);
    }
}
