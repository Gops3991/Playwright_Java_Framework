package org.example.browserFactory;

import com.microsoft.playwright.*;
import org.example.listeners.Reporter;
import org.example.utilities.ConfigReader;
import org.testng.annotations.*;

import java.nio.file.Paths;

public class ProjectAnnotations extends Browsers {

    ConfigReader prop;
    private boolean isHeadless;
    private double slowMoTime;
    private static ThreadLocal<Playwright> threadLocal_Playwright = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> threadLocal_Context = new ThreadLocal<>();
    private static ThreadLocal<Page> threadLocal_Page = new ThreadLocal<>();
    private static ThreadLocal<Browser> threadLocal_Browser = new ThreadLocal<>();

    @BeforeSuite(alwaysRun = true)
    public void setupConfig() {
        System.out.println("Before..Suite");
        prop = new ConfigReader();
        prop.readConfigFile();
        prop.initConfig();
    }

    @BeforeTest(alwaysRun = true)
    public void testPrep() throws Exception {
        System.out.println("Before..test");
        //here lies the created page
    }

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        initBrowser(false,false,true);
        System.out.println("Before..class");
    }

    @BeforeMethod
    public void methodSetup() throws Exception {
        System.out.println("Before..method");
        setContext(getBrowser().newContext());
        traceSetUP();
        setPage(getContext().newPage());
        getPage().navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterMethod
    public void methodTeardown(){
        System.out.println("after..method");
        traceCleanUP();
        getPage().close();
        getContext().close();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        getPlaywright().close();
        System.out.println("after..class");
    }

    @AfterTest(alwaysRun = true)
    public void testCleanUp(){
        System.out.println("after..test");
    }

    @AfterSuite(alwaysRun = true)
    public void testDone(){
        System.out.println("after..suite");
    }

}
