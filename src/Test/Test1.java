package Test;


import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.util.Set;

public class Test1 {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ranga\\Desktop\\seli\\Flipkart1\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Scanner sc = new Scanner(System.in);

        driver.manage().window().maximize(); // Maximize window size
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to the website
            driver.get("https://www.flipkart.com/");

            // Search for "shoes" on Flipkart
            WebElement search = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='q']")));
            assert search != null : "Search input field not found";
            search.sendKeys("shoes\n"); 


            // Search for the brand "nike"
            WebElement searchBrand = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search Brand']")));
            assert searchBrand != null : "Brand search input field not found";
            searchBrand.sendKeys("puma"); 
            // Click on the first brand search result
            WebElement clickBrand = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='_24_Dny'])[1]"))); 
            assert clickBrand != null : "Brand search result not found";
            clickBrand.click();


            // Locate and select the minimum price in the dropdown
            WebElement minDropdown = driver.findElement(By.xpath("//div[@class='_1YAKP4']//select[@class='_2YxCDZ']"));
            assert minDropdown != null : "Minimum price dropdown not found";
            Select minSelect = new Select(minDropdown);
            minSelect.selectByIndex(2); // Select min price according to index
            Thread.sleep(1000);

            // Locate and select the maximum price in the dropdown
            WebElement maxDropdown = driver.findElement(By.xpath("//div[@class='_3uDYxP']//select[@class='_2YxCDZ']"));
            assert maxDropdown != null : "Maximum price dropdown not found";
            Select maxSelect = new Select(maxDropdown);
            maxSelect.selectByIndex(3); // Select max price according to index
            Thread.sleep(1000);

            
            // Click on the first result shoe after filtering
            WebElement firstShoes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='_3ywSr_']//div[@class='_312yBx SFzpgZ']//img)[1]"))); 
            assert firstShoes != null : "First shoe product image not found";
            firstShoes.click();


            // Get handles of all open tabs and switch to the new tab
            Set<String> handles = driver.getWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(driver.getWindowHandle())) {
                    driver.switchTo().window(handle);
                    break;
                }
            }


            // Locate and click on the shoe size 
            // WebElement shoeSize = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='swatch-3-size']//a[@class='_1fGeJ5 _2UVyXR _31hAvz']")));  //need to change id to swatch-0-size to swatch-6-size for 6,7,8,9,10,11,12 shoe size. Also it will click when show is available for user

            WebElement shoeSize = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='swatch-3-size']//a[contains(@class, '_1fGeJ5')]")));  //click even shoe size is not available
            assert shoeSize != null : "Shoe size is not available";
            shoeSize.click();

            // Click on the "Buy Now" button
            WebElement buyNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[2]/form/button"))); 
            assert buyNow != null : "Buy Now button not found";
            buyNow.click();

            System.out.println("Testing Done. Press any key to Exit :- ");
            sc.nextLine();
            
        } catch (Exception e) {

            System.out.println("Testing Done. Press any key to Exit :- ");
            sc.nextLine();
        	throw new RuntimeException("Error occurred during test execution", e);
        } finally {
            // Quit the WebDriver instance
            driver.quit();
        }
    }
}