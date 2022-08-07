package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Mobile Elements
     */
    By FilpKartAssuredCheckbox = By.xpath("(//input[@type='checkbox']/following::div/img[contains(@src,'flixcart')])[1]");

    By PriceHighToLow = By.xpath("//div[text()='Price -- High to Low']");

    By ProductResults = By.xpath("//div[@class='_4rR01T']");

    By ProductPrices = By.xpath("//div[@class='_30jeq3 _1_WHN1']");

    /**
     * Actions
     */
    public void SelectFlipkartAssuredCheckbox() {
        shortSleep();
        wait.until(ExpectedConditions.visibilityOfElementLocated(FilpKartAssuredCheckbox)).click();
    }

    public void SelectPriceHighToLow() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PriceHighToLow)).click();
    }

    public void GetProductResults() {
        shortSleep();
        List<WebElement> products = getElements(ProductResults);
        List<WebElement> productPrices = getElements(ProductPrices);
        for (int i = 0; i < products.size(); i++) {
            WebElement productName = products.get(i);
            WebElement productPrice = productPrices.get(i);
            System.out.println("Product Name  --> |" + productName.getText() + "| Product Price --> |" + productPrice.getText() + "| ");
        }
    }
}
