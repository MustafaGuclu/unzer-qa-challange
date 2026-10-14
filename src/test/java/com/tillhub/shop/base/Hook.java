package com.tillhub.shop.base;

import com.tillhub.shop.utilities.LogUtil;
import com.tillhub.shop.utilities.ScreenShot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.IOException;

public class Hook extends Base{

    private ScreenShot screenShot;

    @Before
    public void testPreparation(Scenario scenario) {

        LogUtil.testStartedLog(scenario.getName());
        String browser = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
        setWebDriver(browser);

        screenShot = new ScreenShot(driver);
    }

    @After
    public void takeScreenShotOfFailedTests(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            screenShot.takeScreenshotAtFailedTest(scenario);
            scenario.log("Scenario failed on url " + driver.getCurrentUrl());
        }
    }

    @After
    public void logTestResults(Scenario scenario) {
        LogUtil.testResult(scenario);
        LogUtil.testEndedLog(scenario.getName());
    }

    @After
    public void closeWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
