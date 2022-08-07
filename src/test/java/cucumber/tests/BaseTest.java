package cucumber.tests;

import managers.FileReaderManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utilities.DesiredCapabilitiesUtil;
import utilities.ThreadLocalDriver;

import java.io.IOException;
import java.net.URL;

public class BaseTest {
    private final DesiredCapabilitiesUtil desiredCapabilitiesUtil = new DesiredCapabilitiesUtil();
    private static final String USERNAME = FileReaderManager.getInstance().getConfigReader().getUsername();
    private static final String ACCESS_KEY = FileReaderManager.getInstance().getConfigReader().getAccessKey();
    private static final String BROWSERSTACK_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @BeforeMethod
    @Parameters({ "os", "osVersion","browser", "browserVersion" })
    public void setup(String os, String osVersion, String browser, String browserVersion) throws IOException {
        DesiredCapabilities capabilities = desiredCapabilitiesUtil.getDesiredCapabilities(os, osVersion, browser, browserVersion);
        ThreadLocalDriver.setTLDriver(new RemoteWebDriver(new URL(BROWSERSTACK_URL),capabilities));
    }

    @AfterMethod
    public synchronized void teardown() throws InterruptedException {
        Thread.sleep(4000);
        ThreadLocalDriver.getTLDriver().quit();
    }
}
