package com.tillhub.shop.utilities;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
    private static final Logger log = LogManager.getLogger(LogUtil.class.getName());

    public static void testResult(Scenario scenario){
        String result;
        switch(scenario.getStatus()){
            case PASSED:
                result = "Passed";
                log.info(String.format("Test case: %s %s%s", scenario.getName(), result, System.lineSeparator()));
                System.out.println(String.format("Test case: %s %s%s", scenario.getName(), result, System.lineSeparator()));
                break;
            case FAILED:
                result = "Failed";
                log.error(String.format("Test case: %s %s%s", scenario.getName(), result, System.lineSeparator()));
                System.out.println(String.format("Test case: %s %s%s", scenario.getName(), result, System.lineSeparator()));
                break;
            case SKIPPED:
                result = "Skipped";
                log.warn(String.format("Test case: %s %s%s", scenario.getName(), result, System.lineSeparator()));
                System.out.println(String.format("Test case: %s %s%s", scenario.getName(), result, System.lineSeparator()));
                break;
        }
    }

    public static void testStartedLog(String scenarioName){
        log.info(scenarioName + " started");
    }

    public static void testEndedLog(String scenarioName){
        log.info(scenarioName + " ended");
        log.info("Closing browser at the end of the test");
    }
    public static void log(String text) {
        log.info(text);
    }

    public static void errorLog(String text) {
        log.error(text);
    }
}
