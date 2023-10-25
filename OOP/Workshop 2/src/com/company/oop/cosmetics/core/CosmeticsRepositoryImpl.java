package com.company.oop.cosmetics.core;

import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.CategoryImpl;
import com.company.oop.cosmetics.models.ShampooImpl;
import com.company.oop.cosmetics.models.ShoppingCartImpl;
import com.company.oop.cosmetics.models.ToothpasteImpl;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.contracts.ShoppingCart;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;

import java.util.ArrayList;
import java.util.List;

public class CosmeticsRepositoryImpl implements CosmeticsRepository {

    private static final String PRODUCT_DOES_NOT_EXIST = "Product %s does not exist!";
    private static final String CATEGORY_DOES_NOT_EXIST = "Category %s does not exist!";

    private final List<Product> products;
    private final List<Category> categories;
    private final ShoppingCart shoppingCart;

    public CosmeticsRepositoryImpl() {
        this.products = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.shoppingCart = new ShoppingCartImpl();
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public Product findProductByName(String productName) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        for (Category category : getCategories()) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }

        throw new IllegalArgumentException(String.format(CATEGORY_DOES_NOT_EXIST, categoryName));
    }

    @Override
    public Category createCategory(String categoryName) {
        Category category = new CategoryImpl(categoryName);
        this.categories.add(category);
        return category;
    }

    @Override
    public ShampooImpl createShampoo(String name, String brandName, double price, GenderType genderType,
                                     int millilitres, UsageType usageType) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public ToothpasteImpl createToothpaste(String name, String brandName, double price, GenderType genderType, List<String> ingredients) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public boolean categoryExist(String categoryName) {
        boolean exists = false;

        for (Category category : getCategories()) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    @Override
    public boolean productExist(String productName) {
        boolean exists = false;

        for (Product product : getProducts()) {
            if (product.getName().equalsIgnoreCase(productName)) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    @Override
    public void addProductToShoppingCart(Product product) {
        shoppingCart.addProduct(product);
    }

    @Override
    public void removeProductFromCart(Product product) {
        shoppingCart.removeProduct(product);
    }

}