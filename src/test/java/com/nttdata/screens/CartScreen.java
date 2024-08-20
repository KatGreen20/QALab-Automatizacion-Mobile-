package com.nttdata.screens;

import java.time.Duration;
import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.exceptions.NoSuchElementException;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartScreen extends PageObject {

    // Localizador para el ícono del carrito
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Displays number of items in your cart\"]")
    private WebElement cartIcon;

    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Displays list of selected products\"]")
    private WebElement cartItemsContainer;

    public void navigateToCart() {
        cartIcon.click();  // Hace clic en el ícono del carrito
    }


    public boolean isProductInCart(String productName, int expectedTotalQuantity) {
        // Espera a que los elementos del carrito estén visibles
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        try {
            System.out.println("Esperando a que el contenedor de ítems del carrito esté visible...");
            wait.until(ExpectedConditions.visibilityOf(cartItemsContainer));
            System.out.println("El contenedor de ítems del carrito es visible.");
        } catch (TimeoutException e) {
            System.out.println("Timeout: El contenedor de ítems del carrito no se hizo visible.");
            throw e;
        }

        try {
            System.out.println("Buscando el producto en el carrito con el nombre: " + productName);
            // Busca el elemento que contiene el nombre del producto
            WebElement productElement = cartItemsContainer.findElement(By.xpath(".//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/titleTV' and @text='" + productName + "']"));
            System.out.println("Producto encontrado: " + productName);

            // Busca el total de ítems en el carrito
            System.out.println("Buscando el total de ítems en el carrito...");
            WebElement totalItemsElement = getDriver().findElement(By.xpath("//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/itemsTV']")); //android.widget.TextView[@resource-id="com.saucelabs.mydemoapp.android:id/itemsTV"]
            String totalItemsText = totalItemsElement.getText();
            System.out.println("Total de ítems encontrado: " + totalItemsText);

            // Extrae solo el número de ítems antes de la palabra "items"
            String totalItems = totalItemsText.split(" ")[0].trim();
            int totalItemsCount = Integer.parseInt(totalItems);

            System.out.println("Cantidad esperada: " + expectedTotalQuantity + ", Cantidad encontrada: " + totalItemsCount);
            return totalItemsCount == expectedTotalQuantity;

        } catch (NoSuchElementException e) {
            System.out.println("No se pudo localizar un elemento: " + e.getMessage());
            throw e;
        } catch (NumberFormatException e) {
            System.out.println("No se pudo convertir la cantidad de ítems a número: " + e.getMessage());
            throw e;
        }
    }


}
