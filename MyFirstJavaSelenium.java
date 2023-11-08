import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class MyFirstJavaSelenium {
    public static void main(String[] args) {
        // 1. Set the system property to specify the path to the GeckoDriver executable.
        System.setProperty("webdriver.gecko.driver", "/Users/lubaizhao/Documents/testproj/geckodriver");

        // 2. Create a new instance of the FirefoxDriver, which launches the Firefox browser.
        WebDriver driver = new FirefoxDriver();

        // 3. Initialize an Actions object to handle complex user interactions.
        Actions actions = new Actions(driver);

        // 4. Navigate to the Google website.
        driver.get("http://www.google.com");

        // 5. Maximize the browser window.
        driver.manage().window().maximize();

        // 6. Find an element on the web page with the name attribute "q" (usually the search input) and send the keys "SFBU."
        driver.findElement(By.name("q")).sendKeys("SFBU");

        // 7. Use the Actions class to send the Enter key to the search input element.
        actions.sendKeys(Keys.ENTER).build().perform();

        // 8. Navigate to the "https://sfbu.edu" website.
        driver.navigate().to("https://sfbu.edu");

        // 9. Get the title of the current web page and print it.
        String actualTitle = driver.getTitle();
        System.out.println("Current open web page title is " + actualTitle);

        // 10. Compare the actual web page title to the expected title and print the test result.
        String expectedTitle = "SFBU | San Francisco Bay University | SFBU";
        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        // 11. Close the browser window and exit the WebDriver session.
        driver.quit();
    }
}
