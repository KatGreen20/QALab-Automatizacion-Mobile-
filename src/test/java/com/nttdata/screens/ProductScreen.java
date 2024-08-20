package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductScreen extends PageObject {

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Tap to add product to cart']")
    private WebElement addToCartButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/noTV']")
    private WebElement quantityField;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    private WebElement increaseQuantityButton;

    public void addProductToCart(int quantity) {
        // Asegúrate de que la cantidad inicial sea 1 antes de aumentar
        int currentQuantity = Integer.parseInt(quantityField.getText());

        while (currentQuantity < quantity) {
            increaseQuantityButton.click();
            currentQuantity++;
            // Espera a que la cantidad en pantalla se actualice
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.textToBePresentInElement(quantityField, String.valueOf(currentQuantity)));
        }

        // Espera explícita para asegurarse de que el botón "Agregar al carrito" esté listo para ser clicado
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }

}

