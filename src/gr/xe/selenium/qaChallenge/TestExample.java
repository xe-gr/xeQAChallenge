package gr.xe.selenium.qaChallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestExample {

    /*
    * This is our test. It will just visit xe.gr and click on the jobs tab.
    * */
    @Test(priority = 0, description = "Visit xe.gr and click on the jobs tab")
    public void visitXeClickJobsTab() {
        //We get the current working directory - the project directory
        String path = System.getProperty("user.dir");
        //Under that directory we get the web driver executable path
        //For this example chrome 93.0.4577.63 for windows has been used
        //If you have a later version you have to replace the chrome executable with the latest one
        //Under this directory there also is the executable for linux environments
        //Feel free to add and use any executable for any browser or environment that suits you
        String chromeDriverPath = path + "/src/resources/chromedriver.exe";
        //After we have built the web driver path we load it to the corresponding property
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //We start the chromedriver
        WebDriver driver = new ChromeDriver();
        //We visit xe.gr
        driver.navigate().to("https://www.xe.gr/");
        //We click on the tab we want
        driver.findElement(By.cssSelector("#jobs-tab")).click();
        //We close the driver
        driver.quit();
    }
}
