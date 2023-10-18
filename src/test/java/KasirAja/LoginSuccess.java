package KasirAja;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginSuccess {
    @Test
    public void successlogin() {
        WebDriver driver;
        String baseurl = "https://kasirdemo.belajarqa.com/";

        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        //Membuka halaman login
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseurl);

        //assertion halaman login
        //String loginPageAssert = driver.findElement(By.xpath("//h2[contains(test(), 'hai, kasirAja')]")).getText();
        //Assert.assertEquals(loginPageAssert, "hai, kasirAja");

        //input Email
        driver.findElement(By.id("email")).sendKeys("tdd-selenium@gmail.com");
        //input password
        driver.findElement(By.id("password")).sendKeys("tdd-selenium");
        //click login
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert nama toko
     //   String namaToko = driver.findElement(By.xpath())
        //quit
        //driver.close();
    }

    @Test
    public void failedlogin(){
        WebDriver driver;
        String baseurl = "https://kasirdemo.belajarqa.com/";

        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        //Membuka halaman login
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseurl);

        //assertion halaman login
        //String loginPageAssert = driver.findElement(By.xpath("//h2[contains(test(), 'hai, kasirAja')]")).getText();
        //Assert.assertEquals(loginPageAssert, "hai, kasirAja");

        //input Email
        driver.findElement(By.id("email")).sendKeys("tdd-selenium@gmail.com");
        //input password yang salah
        driver.findElement(By.id("password")).sendKeys("passdsalah");
        //click login
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String ErrorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        Assert.assertEquals(ErrorLogin, "Kredensial yang Anda berikan salah");
    }
}
