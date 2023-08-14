package org.example.browserFactory;

import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utilities.ConfigReader;

import java.nio.file.Paths;


public class Browsers {

    private static double slowMoTime;
    private static boolean isTraceEnabled;
    private ConfigReader prop;
    public static Logger logger = null;

    private static ThreadLocal<Playwright> threadLocal_Playwright = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> threadLocal_Context = new ThreadLocal<>();
    private static ThreadLocal<Page> threadLocal_Page = new ThreadLocal<>();
    private static ThreadLocal<Browser> threadLocal_Browser = new ThreadLocal<>();


    //setter
    public static void setPlaywright(Playwright playwright) {
        threadLocal_Playwright.set(playwright);
    }

    public static void setBrowser(Browser browser) {
        threadLocal_Browser.set(browser);
    }

    public static void setContext(BrowserContext context) {
        threadLocal_Context.set(context);
    }

    public static void setPage(Page page) {
        threadLocal_Page.set(page);
    }

    //getters
    public static Playwright getPlaywright() {
        return threadLocal_Playwright.get();
    }

    public static Browser getBrowser() {
        return threadLocal_Browser.get();
    }

    public static BrowserContext getContext() {
        return threadLocal_Context.get();
    }

    public static Page getPage() {
        return threadLocal_Page.get();
    }


    public Browser initBrowser(boolean isHeadless, boolean isSlowMo, boolean isTraceEnabled) throws Exception {

        ConfigReader prop = new ConfigReader();
        String browserName =  prop.get("browser");
        this.isTraceEnabled = isTraceEnabled;

        logger = LogManager.getLogger(getClass());

        if(isSlowMo)
           slowMoTime= 2000.0;
        else
           slowMoTime = 0;

        // Passing the playwright.create() to the thread using set
        Playwright pl = Playwright.create();
        try {
            setPlaywright(pl);
        } catch (Exception e) {
            new Exception("Exceptions is -> " + e);
        }

        switch (browserName.toLowerCase()) {
            case "simple":
                try {
                    setBrowser(getPlaywright().chromium().launch(new BrowserType.LaunchOptions()
                            .setHeadless(isHeadless)
                            .setSlowMo(slowMoTime)

                    ));
                } catch (Exception e) {
                    new Exception("Exceptions is -> " + e);
                }
                break;

            case "chromium":
                setBrowser(getPlaywright().chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(isHeadless)
                ));
                break;
            case "firefox":
                setBrowser(getPlaywright().firefox().launch(new BrowserType.LaunchOptions()
                        .setHeadless(isHeadless)
                ));
                break;
            case "safari":
                setBrowser(getPlaywright().webkit().launch(new BrowserType.LaunchOptions()
                        .setHeadless(isHeadless)
                ));
                break;
            case "chrome":
                setBrowser(getPlaywright().chromium().launch(new BrowserType.LaunchOptions()
                        .setChannel("chrome")
                        .setHeadless(isHeadless)
                ));
                break;
            case "edge":
                setBrowser(getPlaywright().chromium().launch(new BrowserType.LaunchOptions()
                        .setChannel("msedge")
                        .setHeadless(isHeadless)
                ));
                break;
            default:
                throw new Exception("Please use the correct Browser name");
        }
        return getBrowser();
    }

    public void traceSetUP(){
    // Start tracing before creating / navigating a page.
        if(this.isTraceEnabled) {
            getContext().tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
        }
    }

    public void traceCleanUP(){
        if(this.isTraceEnabled)
        getContext().tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("./target/trace.zip")));
        }
    }

