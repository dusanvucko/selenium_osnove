package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Zadatak2 {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://itbootcamp.rs/");
        driver.manage().window().maximize();

        List<WebElement> hoverableMenus = driver.findElements(By.cssSelector("li.dropdown>a"));
        List<WebElement> dropDownLists = driver.findElements(By.cssSelector("ul.dropdown-menu"));

        for (int i = 0; i < 3; i++) {
            new Actions(driver)
                    .moveToElement(hoverableMenus.get(i))
                    .perform();

            wait
                    .withMessage("Drop-down menu is not visible!")
                    .until(ExpectedConditions.visibilityOf(dropDownLists.get(i)));
            System.out.println(hoverableMenus.get(i).getText() + " menu is visible");

            Thread.sleep(1000);
        }
    }
}

