package d22_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Zadatak1 {


        public static void main(String[] args) throws InterruptedException {

            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();

            ArrayList<String> url = new ArrayList<>();
            url.add("https://www.google.com/");
            url.add("https://www.facebook.com/");
            url.add("https://www.youtube.com/");
            url.add("https://www.ebay.com/");
            url.add("https://www.katalon.com/");

            int counter = 0;
            for (int i = 0; i < url.size(); i++) {

                driver.get(url.get(i));
                counter++;
                if (counter % 2 == 0) {
                    Thread.sleep(2000);
                }
            }

            driver.quit();




        }
    }

