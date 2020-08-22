package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.util.UUID;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.By.xpath;

public class MySeventhTest extends TestBase {

    @Test
    public void createNewAccount() throws InterruptedException {
        driver.navigate().to("http://localhost/litecart/en/create_account");
        //Регистрация:
        driver.findElement(By.name("tax_id")).sendKeys("078-05-1120");
        driver.findElement(By.name("company")).sendKeys("SL");
        driver.findElement(By.name("firstname")).sendKeys("Ivan");
        driver.findElement(By.name("lastname")).sendKeys("Ivanov");
        driver.findElement(By.name("address1")).sendKeys("100 Winchester Circle Los Gatos, CA 95032");
        driver.findElement(By.name("address2")).sendKeys("101 Winchester Circle Los Gatos, CA 95033");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("Alabama");

        //Select drpCountry = new Select(driver.findElement(By.name("country_code")));
        //drpCountry.selectByValue("US");

        driver.findElement(xpath("//span[@role='presentation']")).click();
        driver.findElement(xpath("//input[@type='search']")).sendKeys("United States" + Keys.ENTER);

        Select zone = new Select(driver.findElement(xpath("//*[@id='create-account']/div/form/table/tbody/tr[5]/td[2]/select")));
        zone.selectByValue("AK");

        UUID uuid = UUID.randomUUID();
        String randomEmail = uuid.toString() + "@mail.ru";
        driver.findElement(By.name("email")).sendKeys(randomEmail);
        driver.findElement(By.name("phone")).sendKeys("+19780712356");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("confirmed_password")).sendKeys("password");
        driver.findElement(By.name("create_account")).click();
        sleep(5000);
        //Логаут:
        driver.findElement(xpath("//*[@id='box-account']/div/ul/li[4]/a")).click();
        //Логин:
        sleep(5000);
        driver.findElement(By.name("email")).sendKeys(randomEmail);
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("login")).click();

        //Логаут:
        driver.findElement(xpath("//*[@id='box-account']/div/ul/li[4]/a"));
    }
}
