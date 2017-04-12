import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class denya {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void denya() {
        wd.get("http://biggame.com.ua/");
        wd.findElement(By.id("exampleInputName1")).click();
        wd.findElement(By.id("exampleInputName1")).clear();
        wd.findElement(By.id("exampleInputName1")).sendKeys("Денис");
        wd.findElement(By.id("exampleInputName2")).click();
        wd.findElement(By.id("exampleInputName2")).clear();
        wd.findElement(By.id("exampleInputName2")).sendKeys("Звєрєв");
        wd.findElement(By.id("citi")).click();
        wd.findElement(By.id("citi")).clear();
        wd.findElement(By.id("citi")).sendKeys("Миколаїв");
        wd.findElement(By.id("exampleInputEmail1")).click();
        wd.findElement(By.id("exampleInputEmail1")).clear();
        wd.findElement(By.id("exampleInputEmail1")).sendKeys("dzdenya@gmail.com");
        wd.findElement(By.id("tel")).click();
        wd.findElement(By.id("tel")).clear();
        wd.findElement(By.id("tel")).sendKeys("+38 (063) 635-78-51");
        if (!wd.findElement(By.xpath("//form[@id='form']/div[6]/div/select//option[2]")).isSelected()) {
            wd.findElement(By.xpath("//form[@id='form']/div[6]/div/select//option[2]")).click();
        }
        wd.findElement(By.cssSelector("button.btn.btn-primary ")).click();
    }
    
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
