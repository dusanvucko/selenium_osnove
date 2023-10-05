package d03_10_2023;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class Zadatak1 {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "https://s.bootsnipp.com/iframe/K5yrx";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.navigate().to(baseUrl);
        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
                "Title of the page is not Table with Edit and Update Data - Bootsnipp.com");
    }

    @Test
    public void testEditRow() {
        String firstName = "Dusan";
        String lastName = "Vuckovic";
        String middleName = "/";

        driver.findElement(By.cssSelector("#d1 .update")).click();
        wait
                .withMessage("Edit modal is not visible")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#edit  .modal-dialog")));

        WebElement firstNameInput = driver.findElement(By.id("fn"));
        WebElement lastNameInput = driver.findElement(By.id("ln"));
        WebElement middleNameInput = driver.findElement(By.id("mn"));

        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        middleNameInput.clear();
        middleNameInput.sendKeys(middleName);

        driver.findElement(By.xpath("//button[text()='Update']")).click();

        wait
                .withMessage("Edit modal is still visible")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#edit  .modal-dialog")));

        Assert.assertEquals(driver.findElement(By.id("f1")).getText(), firstName, "First name should be " + firstName);
        Assert.assertEquals(driver.findElement(By.id("l1")).getText(), lastName, "Last name should be " + lastName);
        Assert.assertEquals(driver.findElement(By.id("m1")).getText(), middleName, "Middle name should be " + middleName);
    }

    @Test
    public void testDeleteRow() {
        int cellsPerRow = driver.findElements(By.cssSelector(".table>tbody>tr:first-child>td")).size();
        int cellsPerTable = driver.findElements(By.cssSelector(".table>tbody>tr>td")).size();

        driver.findElement(By.cssSelector("#d1 .delete")).click();

        wait
                .withMessage("Edit modal is not visible")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#delete .modal-dialog")));
        driver.findElement(By.id("del")).click();

        wait
                .withMessage("Delete modal is still visible")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#delete .modal-dialog")));

        Assert.assertEquals(driver.findElements(By.cssSelector(".table>tbody>tr>td")).size(),
                cellsPerTable - cellsPerRow, "Row is still visible after deletion");
    }

    @Test
    public void testTakeAScreenshot() throws IOException {
        Object Helper = null;
        Helper.(driver, "screenshots/slike.png");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

