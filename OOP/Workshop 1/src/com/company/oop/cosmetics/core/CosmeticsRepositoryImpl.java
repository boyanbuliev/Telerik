package com.company.oop.cosmetics.core;

import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.Category;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.Product;
import com.company.oop.cosmetics.models.ShoppingCart;

import java.util.ArrayList;

public class CosmeticsRepositoryImpl implements CosmeticsRepository {

    private final ArrayList<Product> products;
    private final ArrayList<Category> categories;
    private final ShoppingCart shoppingCart;
    public static final String DOES_NOT_EXIST = "%s %s does not exist!";

    public CosmeticsRepositoryImpl() {
        products = new ArrayList<>();
        categories = new ArrayList<>();
        shoppingCart = new ShoppingCart();
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public ArrayList<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    @Override
    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public Product findProductByName(String productName) {
        /**
         * Hint: You have to go through every product and see if one has name equal to productName.
         *       If not, "throw new IllegalArgumentException("Product %s does not exist!");"
         */
        return products.stream().filter(p -> productName.equals(p.getName())).findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(DOES_NOT_EXIST, "Product", productName)));

        /** eto i sku4niq variant**/
//        for (Product product : products) {
//            if (product.getName().equals(productName)) {
//                return product;
//            }
//        }
//        throw new IllegalArgumentException(String.format("Product %s does not exist!", productName));
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        /**
         * Hint: You have to go through every category and see if one has name equal to categoryName.
         *       If not, "throw new IllegalArgumentException("Category %s does not exist!");"
         */
        return categories.stream().filter(c -> categoryName.equals(c.getName()))
                .findAny().orElseThrow(() -> new IllegalArgumentException(String.format(DOES_NOT_EXIST, "Category", categoryName)));
//        for (Category category : categories) {
//            if (category.getName().equals(categoryName)) {
//                return category;
//            }
//        }
//        throw new IllegalArgumentException(String.format("Category %s does not exist!", categoryName));
    }

    @Override
    public void createCategory(String categoryName) {
        categories.add(new Category(categoryName));
    }

    @Override
    public void createProduct(String name, String brand, double price, GenderType gender) {
        products.add(new Product(name, brand, price, gender));
    }

    @Override
    public boolean categoryExist(String categoryName) {
        /**
         * Hint: You have to go through every category and see if one has name equal to categoryName.
         *       If there is one, return true. If not, return false.
         */
        try {
            findCategoryByName(categoryName);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean productExist(String productName) {
        /**
         * Hint: You have to go through every product and see if one has name equal to productName.
         *       If there is one, return true. If not, return false.
         */
        try {
            findProductByName(productName);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
