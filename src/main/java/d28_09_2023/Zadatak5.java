package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak5 {

    public static void main(String[] args){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("http://seleniumdemo.com/?product=bdd-cucumber");

        driver.findElement(By.cssSelector("li.mobile-woocart__container > a")).click();
        wait.until(ExpectedConditions.attributeToBe(By.tagName("title"), "text", "Cart – Selenium Demo Page"));
        String text = driver.findElement(By.cssSelector("p.cart-empty")).getText();

        if (text.equals("Your cart is currently empty.")) {
            System.out.println("Element sadrzi tekst.");
        } else {
            System.out.println("Element ne sadrzi tekst.");
        }

        driver.quit();
    }
}

