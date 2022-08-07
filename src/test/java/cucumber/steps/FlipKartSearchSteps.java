package cucumber.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ThreadLocalDriver;

import java.util.List;
import java.util.Map;

public class FlipKartSearchSteps extends BaseSteps {

    @Before
    public void setupLoginSteps() {
        setupScreens(ThreadLocalDriver.getTLDriver());
    }

    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        homePage.navigateToWebsite(url);
        homePage.clickOnCloseButton();
    }

    @When("I search for {string} and select {string} in Categories")
    public void iSearchForAndSelectInCategories(String product, String category) {
        homePage.SearchForProduct(product);
        homePage.SelectCategory(category);

    }

    @And("^I set the following filters and select Flipkart Assured$")
    public void iSetTheFollowingFiltersAndSelectFlipkartAssured(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> values = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> value : values) {
            homePage.SelectBrandFilter(value.get("Brand"));
        }
        searchPage.SelectFlipkartAssuredCheckbox();
        Thread.sleep(6000);
    }

    @And("^I sort the Price from High to Low$")
    public void iSortThePriceFromHighToLow() {
        searchPage.SelectPriceHighToLow();
    }

    @Then("^I capture all the results on the First Page$")
    public void iCaptureAllTheResultsOnTheFirstPage() {
        searchPage.GetProductResults();
    }


}
