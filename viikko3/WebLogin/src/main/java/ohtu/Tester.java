package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "F:\\Lataukset\\chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Random r = new Random();

        driver.get("http://localhost:4567");
        
        sleep(2);
        /*
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        */
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka"+r.nextInt(10000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana1");
        element = driver.findElement(By.name("signup"));
        sleep(2);
        element.submit();
        
        sleep(2);
        
        clickLinkWithText("continue to application mainpage", driver);
        //driver.findElement(By.linkText("continue to application mainpage"));
        //element.click();
        sleep(2);
        clickLinkWithText("logout", driver);
        //driver.findElement(By.linkText("logout"));
       // element.click();
        /* 2
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekkaf");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        sleep(2);
        element.submit();
           */
        
        /* 1
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkepf");
        element = driver.findElement(By.name("login"));
        sleep(2);
        element.submit();
        */
        /*
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
        */
        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
    
    private static void clickLinkWithText(String text, WebDriver driver) {
        int trials = 0;
        while( trials++<5 ) {
            try{
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;           
            } catch(Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}
