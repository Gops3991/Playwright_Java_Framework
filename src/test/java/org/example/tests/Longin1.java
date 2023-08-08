package org.example.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.example.browserFactory.ProjectAnnotations;
import org.testng.annotations.Test;


public class Longin1 extends ProjectAnnotations {
    Logger log;


    @Test ()
    public void Test1() throws Exception {
        log.info("This is 4th Login Test");
        Locator username = getPage().locator("//input[@name='username']");
        Locator password = getPage().locator("//input[@name='password']");
        Locator loginBtn = getPage().locator("//button[@type='submit']");
        username.fill("Admin");
        password.fill("admin123");
        loginBtn.click();
    }

    @Test ()
    public void Test2() throws Exception {
        log.info("This is 5th Login Test");
        Locator username = getPage().locator("//input[@name='username']");
        Locator password = getPage().locator("//input[@name='password']");
        Locator loginBtn = getPage().locator("//button[@type='submit']");
        username.fill("Admin1");
        password.fill("admin123");
        loginBtn.click();
    }

    @Test ()
    public void Test3() throws Exception {
        log.info("This is 6th Login Test");
        Locator username = getPage().locator("//input[@name='username']");
        Locator password = getPage().locator("//input[@name='password']");
        Locator loginBtn = getPage().locator("//button[@type='submit']");
        username.fill("Admin2");
        password.fill("admin123");
        loginBtn.click();
    }

//    @Test
//    public void Test2(){
//        System.out.println("This is 2nd Login Test");
//        username.fill("Admin1");
//        password.fill("admins123");
//        loginBtn.click();
//    }
//
//    @Test
//    public void Test3(){
//        System.out.println("This is 3rd Login Test");
//        username.fill("Admin2");
//        password.fill("admins123");
//        loginBtn.click();
//    }
}
