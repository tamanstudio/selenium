package KasirAja.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseurl = "https://kasirdemo.belajarqa.com/";

    @Given("User launch the web app")
    public void halaman_login_kasirAja(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseurl);


        //Assertion Login
        String loginPageAssert = driver.findElement(By.xpath("//h2[contains(text(), 'hai, kasirAja')]")).getText();
        Assert.assertEquals(loginPageAssert, "hai, kasirAja");
    }

    @When("User enter the valid credentials")
    public void userEnterTheValidCredentials() {
        //input Email
        driver.findElement(By.id("email")).sendKeys("tdd-selenium@gmail.com");
        //input password
        driver.findElement(By.id("password")).sendKeys("tdd-selenium");
    }

    @And("Click on Login")
    public void clickOnLogin() {
        //click login
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("Home Page should be displayed")
    public void homePageShouldBeDisplayed() {
        driver.findElement(By.xpath("//div[contains(text(), 'dashboard')]"));
        String username = driver.findElement(By.xpath("//dd[contains(text(), 'hai']/preceding-sibling::dt")).getText();
        Assert.assertEquals(username,"tdd-selenium");
    }

    @When("User enter the inValid credentials")
    public void userEnterTheInValidCredentials() {
        //input Email
        driver.findElement(By.id("email")).sendKeys("tdd-selenium@gmail.com");
        //input password
        driver.findElement(By.id("password")).sendKeys("invalidpassword");
    }

    @Then("Error Message Should be displayed")
    public void errorMessageShouldBeDisplayed() {
        String ErrorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        Assert.assertEquals(ErrorLogin, "Kredensial yang Anda berikan salah");
    }
}
