package listener;

import com.epam.tasks.task2.browser.Browser;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class YandexListener implements ITestListener {
    private Browser browser = new Browser();
    private String filePath = "files/fail_screenshots";

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            browser.getScreenshot(result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
