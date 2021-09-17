package gr.xe.selenium.qaChallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SelectLandForSaleApplyPlotFilterSaveSearch {

    WebDriver driver;
    WebDriverWait wait;

    String username = "FILL IN YOUR USERNAME HERE";
    String password = "FILL IN YOUR PASSWORD HERE";

    @BeforeClass
    public void initialize(){
        //Set the web driver binary path to the corresponding property
        String path = System.getProperty("user.dir");
        String chromeDriverPath = path + "/src/main/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @Test(priority = 0, description = "Visit gr.xe.gr, select land for sale from the categories and perform a search")
    public void visitXeSelectLandForSaleFromCategoriesPerformSearch() {
        //We start the chromedriver
        driver = new ChromeDriver();
        //We define the implicti wait for this driver
        wait = new WebDriverWait(driver, 15);
        //We visit gr.xe.gr
        driver.navigate().to("https://www.xe.gr/");
        //Select land for sale
        driver.findElement(By.cssSelector("a[href*='poliseis-gis']")).click();
        //Perform the search
        driver.findElement(By.cssSelector(".buttonSave")).click();
    }

    @Test(priority = 1, description = "Expand the filters and apply plot")
    public void expandFiltersApplyPlot() {
        //The cookies banner obstructs our test here so we have to close it
        closeCookiesBannerIfPresent();
        //Expand the filters tab
        driver.findElement(By.cssSelector("div[data-toggle='expand_filters']")).click();
        //Select the plot option
        driver.findElement(By.cssSelector(".checkbox-filter-container label[id*='plot-checkbox']")).click();
        //Apply the filter
        driver.findElement(By.cssSelector(".buttons-container .submit-button")).click();
    }

    @Test(priority = 2, description = "Save the previous search")
    public void saveSearch() {
        //Click to save the search
        driver.findElement(By.cssSelector("[data-testid='save-search-btn']")).click();
        //Wait for username to be visible and fill it in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
        driver.findElement(By.cssSelector("input#email")).sendKeys(username);
        //Fill in password
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        //Click to connect
        driver.findElement(By.cssSelector(".login_button span")).click();
        //Hint: If your search is already saved you are redirected to your saved searches instead of being able to save your search.
        //Manually delete your search if you already saved it once and  want to save it again.
        //Click to save the search again
        driver.findElement(By.cssSelector("[data-testid='save-search-btn']")).click();
        //Deal with the save search pop-up
        saveSearchDoNothingIfAlreadySaved();
    }

    @AfterClass
    public void close(){
        //We close the driver
        driver.quit();
    }

    /*
    * Closes the cookies banner if it's present on the page
    */
    public void closeCookiesBannerIfPresent(){
        if(driver.findElements(By.cssSelector(".btn-disclaimer-ok")).size()>0)
            driver.findElement(By.cssSelector(".btn-disclaimer-ok")).click();
    }

    /*
     * Clicks to save search in the save search pop up and then closes it.
     * If no pop-up is present then it does nothing.
     */
    public void saveSearchDoNothingIfAlreadySaved(){
        if(driver.findElements(By.cssSelector("input.button-property")).size()>0) {
            //Save the search
            driver.findElement(By.cssSelector("input.button-property")).click();
            //Close the success modal
            driver.findElement(By.cssSelector(".xe-modal-close .xe.xe-close")).click();
        }
    }

}