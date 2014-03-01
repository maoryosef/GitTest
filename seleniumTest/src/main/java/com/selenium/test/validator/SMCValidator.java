package com.selenium.test.validator;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


/**
 * Created with IntelliJ IDEA.
 * User: Maor
 * Date: 01/03/14
 * Time: 19:49
 */
public class SMCValidator implements PageValidator{
    ChromeDriver m_WebDriver;
    static Logger logger = Logger.getLogger(SMCValidator.class);

    public Boolean runValidation(String i_Url) {
        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");

        Boolean passed = true;
        m_WebDriver = new ChromeDriver();
        logger.info("Opening " + i_Url);
        m_WebDriver.get(i_Url);

        try {
            logger.info("Searching for the correct frame...");
            searchTextInFrames("Password:");
        } catch (Exception e) {
            logger.error("Failed to load home page", e);
            passed = false;
        }


        logger.info("Trying to login");
        try {
            WebElement usernameField = m_WebDriver.findElement(By.xpath("//input[@name='user.id' or @name='var/user.id']"));
            usernameField.sendKeys("");

            WebElement passwordField = m_WebDriver.findElement(By.xpath("//input[@type='password']"));
            passwordField.sendKeys("");

            WebElement loginButton = m_WebDriver.findElement(By.xpath("//input[(@type='button' and @value='Login') or (@type='submit' and @id='loginBtn')]"));
            loginButton.click();

            searchTextInFrames("To Do Queue:");
        } catch (Exception e) {
            logger.error("Failed to login", e);
            passed = false;
        }

        m_WebDriver.quit();

        return passed;
    }

    private void searchTextInFrames(String i_Text) {
        int frameId = 0;
        while (!m_WebDriver.getPageSource().contains(i_Text)) {
            m_WebDriver.switchTo().defaultContent();
            m_WebDriver.switchTo().frame(frameId);
            frameId++;
        }
    }
}
