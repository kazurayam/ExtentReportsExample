package utils.listeners;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static utils.extentreports.ExtentTestManager.getTest;

public class Retry implements IRetryAnalyzer {

    private int count = 0;

    //Run the test once, when failed retry maxRetry times
    private static final int maxRetry = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {                     //Check if test not succeed
            if (count < maxRetry) {                         //Check if maxRetry count is reached
                count++;                                    //Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE); //Mark test as failed and take base64Screenshot
                extentReportsFailOperations(iTestResult);   //ExtentReports fail operations
                return true;                                //Tells TestNG to re-run the test
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);     //If test passes, TestNG marks it as passed
        }
        return false;
    }

    public void extentReportsFailOperations(ITestResult iTestResult) {
        WebDriver webDriver = TestListener.findWebDriver(iTestResult);
        String base64Screenshot = "data:image/png;base64," +
                ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot)
                        .getModel().getMedia().get(0));
    }
}
