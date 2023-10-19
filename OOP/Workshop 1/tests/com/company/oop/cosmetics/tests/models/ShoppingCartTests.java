package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.models.Product;
import com.company.oop.cosmetics.models.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTests {

    private ShoppingCart cart;

    @BeforeEach
    public void before() {
        cart = new ShoppingCart();
    }

    @Test
    public void add_Should_AddProduct_When_ProductIsValid() {
        // Arrange, Act
        Product productToAdd = ProductTests.initializeTestProduct();

        // Act
        cart.addProduct(productToAdd);

        //Assert
        assertEquals(1, cart.getProducts().size());
    }

    @Test
    public void construct_Should_InitializeNewListOfProducts() {
        // Arrange, Act, Assert
        Assertions.assertNotNull(cart.getProducts());
    }

    @Test
    public void getProducts_Should_ReturnCopyOfTheCollection() {
        // Arrange
        Product productToAdd = ProductTests.initializeTestProduct();

        // Act
        cart.getProducts().add(productToAdd);

        // Assert
        assertEquals(0, cart.getProducts().size());
    }

    @Test
    public void contains_Should_ReturnTrue_When_ProductIsFound() {
        // Arrange, Act
        Product productToAdd = ProductTests.initializeTestProduct();
        cart.addProduct(productToAdd);

        // Act
        boolean isFound = cart.containsProduct(ProductTests.initializeTestProduct());

        //Assert
        Assertions.assertTrue(isFound);
    }

    @Test
    public void contains_Should_ReturnFalse_When_ProductNotFound() {
        // Arrange, Act
        boolean isFound = cart.containsProduct(ProductTests.initializeTestProduct());

        //Assert
        Assertions.assertFalse(isFound);
    }

    @Test
    public void remove_Should_RemoveProduct_When_ProductInCart() {
        // Arrange
        Product product1 = ProductTests.initializeTestProduct();
        Product product2 = ProductTests.initializeTestProduct();
        cart.addProduct(product1);
        cart.addProduct(product2);

        // Act
        cart.removeProduct(product1);

        //Assert
        assertEquals(1, cart.getProducts().size());
    }

    @Test
    public void remove_Should_ThrowException_When_ProductNotInCart() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> cart.removeProduct(ProductTests.initializeTestProduct()));
    }
}
