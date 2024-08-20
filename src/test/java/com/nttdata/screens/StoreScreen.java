package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class StoreScreen extends PageObject {

    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc='Scrollview manages views in given screen size']/android.view.ViewGroup")
    private List<WebElement> productList;

    public boolean isProductsDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        return !productList.isEmpty();
    }

    public void selectProduct(String productName) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));

        // Encuentra el contenedor del producto en el RecyclerView
        String containerXPath = "//androidx.recyclerview.widget.RecyclerView[@content-desc='Displays all products of catalog']/android.view.ViewGroup";
        List<WebElement> productContainers = getDriver().findElements(By.xpath(containerXPath));

        boolean productFound = false;

        for (WebElement productContainer : productContainers) {
            // Dentro de cada contenedor, busca el nombre del producto
            WebElement productNameElement = productContainer.findElement(By.xpath(".//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV' and @text='" + productName + "']"));

            if (productNameElement != null) {
                System.out.println("Product found: " + productNameElement.getText());

                // Encuentra la imagen del producto dentro del contenedor
                WebElement productImage = productContainer.findElement(By.xpath(".//android.widget.ImageView"));

                // Asegúrate de que la imagen del producto sea clickeable
                wait.until(ExpectedConditions.elementToBeClickable(productImage));
                productImage.click();

                productFound = true;
                break;
            }
        }

        if (!productFound) {
            System.out.println("Product not found: " + productName);
        }
    }




}

