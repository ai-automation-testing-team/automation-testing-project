package org.endava.automation.testing.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    protected static Logger log = LoggerFactory.getLogger(Log.class.getName());
    private static final String LOG_MESSAGE_FORMAT = "{LogType}:{message}";

    public static String formatLogMessage(String logType, String message) {
        return LOG_MESSAGE_FORMAT
            .replace("{LogType}", logType)
            .replace("{message}", message);
    }

    public static void automationLogger(String message) {
        log.info(formatLogMessage("Test Automation", message));
    }


    public static void testCaseLogger(String message) {
        log.info(formatLogMessage("Test Case", message));
    }


    public static void stepLogger(String message) {
        log.info(formatLogMessage("Test Step", message));
    }


    public static void uiLogger(String message) {
        log.info(formatLogMessage("UI Interaction", message));
    }


    public static void elementLocatingLogger(String message) {
        log.error(formatLogMessage("Element Locating", message));
    }

    public static void errorLogger(String message) {
        log.error(formatLogMessage("Error", message));
    }


    public static void assertion(String message) {
        log.info(formatLogMessage("Assertion", message));
    }


    public static void warn(String message) {
        log.warn(formatLogMessage("Warn", message));
    }


    public static void navigationLogger(String message) {
        log.info(formatLogMessage("Navigation", message));
    }


    public static void dataExtractionLogger(String message) {
        log.info(formatLogMessage("Data Extraction", message));
    }

    public static void debug(String message) {
        log.debug(formatLogMessage("Debug", message));
    }
}
