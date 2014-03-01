import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main  {
    public static void main(String[] args) {
        // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        String PROTOCOL = "https";
        String URL_BASE = "smast14t.saas.hp.com";
        String WEBAPP_NAME = "sm";
        // And now use this to visit Google

        driver.get(String.format("%s://%s/%s/index.do", PROTOCOL, URL_BASE, WEBAPP_NAME));

        try {
            System.out.println("Trying to login (sm 9)");
            int frameId = 0;
            while (!driver.getPageSource().contains("Password:")) {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(frameId);
                frameId++;
            }
        } catch (Exception e) {
            System.out.println("Failed to switch frame, continuing as sm 9");
        }


        login(driver);
        //List<WebElement> element = driver.findElements(By.tagName("input"));
        //driver.get(String.format("%s://%s/%s/detail.do", PROTOCOL, URL_BASE, WEBAPP_NAME));

        //System.out.println(driver.getPageSource());
        // Find the text input element by its name

        //WebElement element = driver.findElement(By.name("var/user.id"));

        // Enter something to search for
        //element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        //element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        driver.quit();
    }

    private static void login(ChromeDriver driver) {
        WebElement usernameField = driver.findElement(By.xpath("//input[@name='user.id' or @name='var/user.id']"));
        usernameField.sendKeys("saas_bpm_mon");

        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys("123saas456");

        WebElement loginButton = driver.findElement(By.xpath("//input[(@type='button' and @value='Login') or (@type='submit' and @id='loginBtn')]"));
        loginButton.click();
    }
}