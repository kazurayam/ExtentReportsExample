package utils.logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.extentreports.ExtentTestManager;
import com.aventstack.extentreports.Status;

public class Log {
    //Initialize Log4j instance
    private static final Logger Log = LogManager.getLogger(Log.class);

    //Info Level Logs
    public static void info(String message) {
        Log.info(message);
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.INFO, message);
        }
    }

    //Warn Level Logs
    public static void warn(String message) {
        Log.warn(message);
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().log(Status.WARNING, message);
        }
    }

    //Error Level Logs
    public static void error(String message) {
        Log.error(message);
        if (ExtentTestManager.getTest() != null) {
            //ExtentTestManager.getTest().log(Status.ERROR, message);
            // somehow Status.ERROR does not resolve by the compiler
        }
    }

    //Fatal Level Logs
    public static void fatal(String message) {
        Log.fatal(message);
        if (ExtentTestManager.getTest() != null) {
            //ExtentTestManager.getTest().log(Status.FATAL, message);
        }
    }

    //Debug Level Logs
    public static void debug(String message) {
        Log.debug(message);
        if (ExtentTestManager.getTest() != null) {
            //ExtentTestManager.getTest().log(Status.DEBUG, message);
        }
    }
}
