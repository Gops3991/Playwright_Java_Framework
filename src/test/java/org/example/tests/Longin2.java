package org.example.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.example.browserFactory.ProjectAnnotations;
import org.example.listeners.Reporter;
import org.testng.annotations.Test;


public class Longin2 extends ProjectAnnotations {
    Logger log;

    @Test ()
    public void Test1() throws Exception {
        Reporter.info("This is 7th Login Test");
        Locator username = getPage().locator("//input[@name='username']");
        Locator password = getPage().locator("//input[@name='password']");
        Locator loginBtn = getPage().locator("//button[@type='submit']");
        username.fill("Admin");
        password.fill("admin123");
        loginBtn.click();
    }

    @Test ()
    public void Test2() throws Exception {
        Reporter.info("This is 8th Login Test");
        Locator username = getPage().locator("//input[@name='username']");
        Locator password = getPage().locator("//input[@name='password']");
        Locator loginBtn = getPage().locator("//button[@type='submit']");
        username.fill("Admin1");
        password.fill("admin123");
        loginBtn.click();
    }

    @Test ()
    public void Test3() throws Exception {
        Reporter.info("This is 9th Login Test");
        Locator username = getPage().locator("//input[@name='username']");
        Locator password = getPage().locator("//input[@name='password']");
        Locator loginBtn = getPage().locator("//button[@type='submit']");
        username.fill("Admin2");
        password.fill("admin123");
        loginBtn.click();
    }
}
