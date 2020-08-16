package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.openqa.selenium.By.xpath;

public class MyFifthTest extends TestBase{
    @Test
    public void areCountriesAndZonesSorted(){
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countries = driver.findElements(xpath("//tr[@class='row']//td[5]//a"));
        int countriesSize =  countries.size();
        ArrayList<String> countriesNameSorted = new ArrayList<>();
        ArrayList<String> countriesNameAnSorted = new ArrayList<>();

        //Проверить, что страны отсортированы
        for (int i = 0; i < countriesSize; i++) {
            countriesNameSorted.add(countries.get(i).getText());
            countriesNameAnSorted.add(countries.get(i).getText());
        }
        Collections.sort(countriesNameSorted);
        Assert.assertEquals(countriesNameSorted, countriesNameAnSorted);

        //Проверить, что зоны отсортированы
        for (int j = 1; j < countries.size(); j++) {
            if (!driver.findElement(xpath("//tr[@class='row'][" + j + "]//td[6]")).getText().equals("0")){
                driver.findElement(xpath("//tr[@class='row'][" + j + "]//td[7]")).click();
                List<WebElement> zones = driver.findElements(xpath("//table[@class='dataTable']//tr//td[3]"));
                int zoneSize =  zones.size() - 1;
                ArrayList<String> zonesNameSorted = new ArrayList<>();
                ArrayList<String> zonesNameAnSorted = new ArrayList<>();
                for (int k = 0; k < zoneSize; k++) {
                    zonesNameSorted.add(zones.get(k).getText());
                    zonesNameAnSorted.add(zones.get(k).getText());
                }
                Collections.sort(zonesNameSorted);
                Assert.assertEquals(zonesNameSorted, zonesNameAnSorted);

                driver.findElement(xpath(("//ul[@id='box-apps-menu']//li[3]"))).click();
            }
        }
    }
}
