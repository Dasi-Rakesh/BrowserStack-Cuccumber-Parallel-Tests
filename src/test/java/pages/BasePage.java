package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor javascriptExecutor;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor)driver;
        wait = new WebDriverWait(driver, 15);
    }

    protected void waitAndClick(By by) { wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click(); }

    protected void shortSleep(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void click(By by) {
        driver.findElement(by).click();
    }

    protected void hideKeyboard() { driver.navigate().back(); }

    protected List<WebElement> waitAndFindElements(By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    protected WebElement waitAndFindElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected String getText(By by) {
        return waitAndFindElement(by).getText();
    }

    protected void sendKey(By by, String text) {
        waitAndFindElement(by).sendKeys(text);
    }

    public List<WebElement> getElements(By elementLocator) {
        try {
            List<WebElement> elements = driver.findElements(elementLocator);
            return elements;
        } catch (WebDriverException exception) {
            throw new WebDriverException(
                    "Element with locator : " + elementLocator + " was not displayed and unable to get the count",
                    exception);
        }
    }
}
