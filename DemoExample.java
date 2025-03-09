package demo;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.SocketException;
import java.time.Duration;

public class DemoExample {
    public static void main(String[] args) throws InterruptedException, NoSuchElementException, ElementNotInteractableException, TimeoutException, SocketException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\git\\SeleniumJavaFramework1\\SeleniumJavaFramework\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        driver.get("https://www.makemytrip.com/");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
      
        WebElement modalClose = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='commonModal__close']")));
        wait.until(ExpectedConditions.elementToBeClickable(modalClose)).click();
        wait.until(ExpectedConditions.invisibilityOf(modalClose)); 
        Thread.sleep(6000);
        boolean isDateSelected = false;
        int attempts = 0;
        final int maxAttempts = 10; 
        while (!isDateSelected && attempts < maxAttempts) {
            try {
            	driver.findElement(By.xpath("//label[@for='departure']")).click();
                WebElement dateCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Fri Mar 07 2025']//p[contains(text(),'7')]")));
                if (dateCell.isDisplayed() && dateCell.isEnabled()) {
                    dateCell.click(); // Changed from sendKeys to click
                    isDateSelected = true;
                    Thread.sleep(3000);
                }
            } catch (NoSuchElementException e) {
                attempts++;
                System.out.println("Attempt " + attempts + ": Date cell not found. Retrying...");
            } catch (ElementNotInteractableException e) {
                System.out.println("Date cell is not interactable. Retrying...");
                attempts++;
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                break; 
            }
        }
        
        if (!isDateSelected) {
            System.out.println("Date selection failed after " + maxAttempts + " attempts.");
        } 
        	try {
        	
        	    WebElement fromField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='From']")));
        	    
        	    fromField.click();
        	    fromField=driver.findElement(By.xpath("//li[@id='react-autowhatever-1-section-0-item-0']//p[@class='font12 greyText appendBottom3 lineHeight14'][normalize-space()='Chhatrapati Shivaji International Airport']"));
        	    fromField.click();
        	
        	} catch (Exception e) {
        	    System.out.println("Failed to interact with 'From' field: " + e.getMessage());
        	}      

        Thread.sleep(6000);
        driver.quit(); // Ensure the driver is closed after execution
    }
}
