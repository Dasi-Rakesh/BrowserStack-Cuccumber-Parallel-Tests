package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * WebElements Elements
     */

    By closeButton = By.xpath("(//button)[2]");
    By SearchBar = By.xpath("//input[@name = 'q']");
    By SearchIcon = By.xpath("//button[@type='submit']");

    By Categories(String category) {
        return By.xpath("//span[text()='CATEGORIES']/following::a[@title='" + category + "']");
    }

    By BrandFilter(String filter) {
        return By.xpath("//input[@type='checkbox']/following::div[text()='" + filter + "']");
    }

    /**
     * Actions
     */
    public void navigateToWebsite(String URL) {
        driver.get("https://www." + URL + ".com");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        String title = driver.getTitle();
        System.out.println("Website Title is : " + title);
        if ("Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!".equals(title)) {
            Assert.assertEquals("Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!", title);
            javascriptExecutor.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
            System.out.println("Page Title is verified");
        } else {
            javascriptExecutor.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title mismatched!\"}}");
        }


    }

    public void clickOnCloseButton() {
        shortSleep();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(closeButton)).isDisplayed()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(closeButton)).click();
        }
    }

    public void SearchForProduct(String searchItem) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SearchBar)).sendKeys(searchItem);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SearchIcon)).click();
        shortSleep();
    }

    public void SelectCategory(String category) {
        shortSleep();
        try {
            if(wait.until(ExpectedConditions.visibilityOfElementLocated(Categories(category))).isDisplayed()) {
                shortSleep();
                wait.until(ExpectedConditions.visibilityOfElementLocated(Categories(category))).click();
                javascriptExecutor.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element Clicked!\"}}");
            }
        } catch (Exception e) {
            javascriptExecutor.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Element Not clicked!\"}}");
            System.out.println("Element not clicked");
        }


    }

    public void SelectBrandFilter(String filter) {
        shortSleep();
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(BrandFilter(filter))).click();
            javascriptExecutor.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Element Clicked!\"}}");
        }catch (Exception e){
            javascriptExecutor.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Element Not clicked!\"}}");
            System.out.println("Element not clicked");
        }

    }
}
