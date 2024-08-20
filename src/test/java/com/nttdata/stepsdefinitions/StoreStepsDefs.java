package com.nttdata.stepsdefinitions;

import com.nttdata.steps.StoreSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class StoreStepsDefs {

    @Steps
    StoreSteps storeSteps;


    @Given("estoy en la aplicación de SauceLabs")
    public void estoyEnLaAplicaciónDeSauceLabs() {

    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void validoQueCarguenCorrectamenteLosProductosEnLaGaleria() {
        storeSteps.visibleProducts();
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agregoUNIDADESDelSiguienteProducto(int unidades, String producto) {
        storeSteps.addProductToCart(unidades, producto);
    }

    @Then("valido que el carrito de compra actualice correctamente {string} con {int} unidades")
    public void validoElCarritoDeCompraActualiceCorrectamente(String productName, int expectedQuantity) {
        storeSteps.validateCart(productName, expectedQuantity);
    }

}
