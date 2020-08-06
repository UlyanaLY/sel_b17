package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

public class MySecondTest extends TestBase {

    @Test
    public void loginAsAdmin() {
        driver.navigate().to("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
