package org.example.tests;

import com.microsoft.playwright.Locator;
import org.example.browserFactory.ProjectAnnotations;
import org.example.listeners.Reporter;
import org.example.pages.LoginPage;
import org.testng.annotations.*;

public class LoginTests extends ProjectAnnotations {

    @Test ()
    public void Test1() throws Exception {

        Reporter.info("This is 1st Login Test");
        LoginPage loginPage = new LoginPage();
        loginPage.login("Admin","admin123");
    }

    @Test ()
    public void Test2() throws Exception {
        Reporter.info("This is 2nd Login Test");
        LoginPage loginPage = new LoginPage();
        loginPage.login("Admin","admin123");
    }

    @Test ()
    public void Test3() throws Exception {
        Reporter.info("This is 3rd Login Test");
        LoginPage loginPage = new LoginPage();
        loginPage.login("Admin","admin123");
    }
}
