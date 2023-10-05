package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

public class Zadatak1 {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");

        driver.findElement(By.className("edit-image")).click();
        wait.withMessage("Delete button not visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("image-option-remove")));

        driver.findElement(By.id("image-option-remove")).click();

        ArrayList<File> imgFile = new ArrayList<>();
        imgFile.add(new File("test_data/front.jpg"));
        imgFile.add(new File("test_data/right.jpg"));
        imgFile.add(new File("test_data/left.jpg"));
        imgFile.add(new File("test_data/back.jpg"));

        {
            int i = 0;
            while (i < 4) {
                driver.findElement(By.className("edit-image")).click();
                wait.withMessage("Upload an image dialog not shown.")
                        .until(ExpectedConditions.presenceOfElementLocated(By.id("imageUpload")));

                driver.findElement(By.id("imageUpload")).sendKeys(imgFile.get(i).getAbsolutePath());
                wait.withMessage("Uploaded photo not added to the list.")
                        .until(ExpectedConditions.numberOfElementsToBe(By.xpath("//img[contains(@id, 'image-option-')]"), i + 1));

                driver.findElement(By.id("image-option-0")).click();
                wait.withMessage("Image crop done button is not visible.")
                        .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#image-crop-done-button > button")));
                driver.findElement(By.cssSelector("#image-crop-done-button > button")).click();

                Thread.sleep(2000);
                i++;
            }
        }

        wait.withMessage("Next button is not clickable.")
                .until(ExpectedConditions.elementToBeClickable(By.id("next-button")));
        driver.findElement(By.id("next-button")).click();

        driver.findElement(By.id("textareaID")).click();
        driver.findElement(By.id("textareaID")).sendKeys("Low poly images box");

        driver.findElement(By.id("next-button")).click();
        wait.withMessage("Confetti selection dialog is not visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'confetti-')]")));

        driver.findElement(By.id("confetti-4")).click();

        driver.findElement(By.id("next-button")).click();

        WebElement box360View = driver.findElement(By.id("input-container"));

        int i = 0;
        while (i < 4) {
            new Actions(driver)
                    .clickAndHold(box360View)
                    .moveByOffset(100, 0)
                    .release()
                    .perform();
            Thread.sleep(500);
            i++;
        }

        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.className("close")).click();
        driver.findElement(By.id("next-button")).click();

        Thread.sleep(5000);


        driver.quit();
    }
}


