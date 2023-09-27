package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak4 {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://artplayer.org/");
        Thread.sleep(4000);

        driver.findElement(By.cssSelector("div.art-control.art-control-playAndPause")).click();
        driver.findElement(By.cssSelector("div.art-control.art-control-volume")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("div.art-control.art-control-screenshot.hint--rounded.hint--top")).click();
        driver.findElement(By.cssSelector("div.art-control.art-control-pip.hint--rounded.hint--top")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div.art-control.art-control-pip.hint--rounded.hint--top")).click();
        driver.findElement(By.cssSelector("div.art-control.art-control-fullscreenWeb.hint--rounded.hint--top")).click();
        driver.findElement(By.cssSelector("div.art-control.art-control-fullscreen.hint--rounded.hint--top")).click();
        Thread.sleep(5000);


        driver.quit();

    }
    }

