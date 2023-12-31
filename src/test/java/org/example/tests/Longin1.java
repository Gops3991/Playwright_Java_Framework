package org.example.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.example.annotationsFramework.TagGroups;
import org.example.browserFactory.ProjectAnnotations;
import org.example.listeners.Reporter;
import org.testng.annotations.Test;


public class Longin1 extends ProjectAnnotations {
    Logger log;

    @TagGroups(tags = {"sanity"})
    @Test ()
    public void Test4() throws Exception {
        Reporter.info("This is 4th Login Test");
        Locator username = getPage().locator("//input[@name='username']");
        Locator password = getPage().locator("//input[@name='password']");
        Locator loginBtn = getPage().locator("//button[@type='submit']");
        username.fill("Admin");
        password.fill("admin123");
        loginBtn.click();
    }

    @TagGroups(tags = {"regression"})
    @Test ()
    public void Test5() throws Exception {
        Reporter.info("This is 5th Login Test");
        Locator username = getPage().locator("//input[@name='username']");
        Locator password = getPage().locator("//input[@name='password']");
        Locator loginBtn = getPage().locator("//button[@type='submit']");
        username.fill("Admin1");
        password.fill("admin123");
        loginBtn.click();
    }

    @TagGroups(tags = {"sanity"})
    @Test ()
    public void Test6() throws Exception {
        Reporter.info("This is 6th Login Test");
        Locator username = getPage().locator("//input[@name='username']");
        Locator password = getPage().locator("//input[@name='password']");
        Locator loginBtn = getPage().locator("//button[@type='submit']");
        username.fill("Admin2");
        password.fill("admin123");
        loginBtn.click();
    }
}
