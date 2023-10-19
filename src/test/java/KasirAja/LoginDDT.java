package KasirAja;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginDDT {
    //Login menggunakan fitur Data Drive Test(DDT)
    @Test
    public void login_ddt(){
        WebDriver driver;
        String baseurl = "https://kasirdemo.belajarqa.com/";

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        //path CSV
        String fileDir = System.getProperty("user.dir")+"/src/test/data/test-data.csv";

        //baca file csv
        try(CSVReader reader = new CSVReader(new FileReader(fileDir))){
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null){
                String email = nextLine[0];
                String password = nextLine[1];
                String status = nextLine[2];

                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                driver.get(baseurl);

                driver.findElement(By.id("email")).sendKeys(email);
                //input password
                driver.findElement(By.id("password")).sendKeys(password);
                //click login
                driver.findElement(By.xpath("//button[@type='submit']")).click();

                if (status.equals("success")){//assert success login
                    driver.findElement(By.xpath("//div[contains(text(), 'dashboard')]"));
                    String username = driver.findElement(By.xpath("//dd[contains(text(), 'hai')]/preceding-sibling::dt")).getText();
                    Assert.assertEquals(username, "tdd-selenium");
                } else { //assert failed login
                    String ErrorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
                    Assert.assertEquals(ErrorLogin, "Kredensial yang Anda berikan salah");
                }
            }
        }catch (CsvValidationException| IOException e){
            throw new RuntimeException(e);
        }
    }
}
