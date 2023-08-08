package org.example.pages;

import com.microsoft.playwright.Locator;
import org.example.listeners.Reporter;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.example.browserFactory.Browsers.getPage;

public class LoginPage {

    private Locator username = getPage().locator("//input[@name='username']");
    private Locator password = getPage().locator("//input[@name='password']");
    private Locator loginBtn = getPage().locator("//button[@type='submit']");


    public void login (String uname, String pword){
        Reporter.info("Logging in");
        username.fill(uname);
        password.fill(pword);
        loginBtn.click();
        assertThat(getPage().locator("//h6[normalize-space()='Dashboard']")).isVisible();
    }
}
