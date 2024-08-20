package com.nttdata.steps;

import com.nttdata.screens.CartScreen;
import com.nttdata.screens.ProductScreen;
import com.nttdata.screens.StoreScreen;
import org.junit.Assert;

public class StoreSteps {

    StoreScreen storeScreen;
    ProductScreen productScreen;
    CartScreen cartScreen;

    public void visibleProducts(){
        Assert.assertTrue(storeScreen.isProductsDisplayed());
    }

    public void addProductToCart(int quantity, String productName){
        storeScreen.selectProduct(productName);
        productScreen.addProductToCart(quantity);
    }

    public void validateCart(String productName, int expectedTotalQuantity) {
        cartScreen.navigateToCart();
        boolean isProductInCart = cartScreen.isProductInCart(productName, expectedTotalQuantity);
        Assert.assertTrue("Se esperaba que el producto '" + productName + "' estuviera en el carrito con una cantidad total de " + expectedTotalQuantity + ", pero no se encontró o la cantidad es incorrecta.", isProductInCart);
    }


}
