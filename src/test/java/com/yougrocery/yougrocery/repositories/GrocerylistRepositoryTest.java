package com.yougrocery.yougrocery.repositories;

import com.yougrocery.yougrocery.models.Grocerylist;
import com.yougrocery.yougrocery.models.Product;
import com.yougrocery.yougrocery.models.ProductOnGrocerylist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class GrocerylistRepositoryTest {

    @Autowired
    private GrocerylistRepository grocerylistRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ProductOnGrocerylistRepository productOnGrocerylistRepo;

    @Test
    void createTwoGroceryListsWithSameName_works() {
        var grocerylist1 = new Grocerylist("Test name 1");
        var grocerylist2 = new Grocerylist("Test name 1");

        saveGrocerylist(grocerylist1);
        saveGrocerylist(grocerylist2);

        assertThat(findAllGrocerylists())
                .hasSize(2)
                .extracting(Grocerylist::getName)
                .containsExactly(
                        "Test name 1",
                        "Test name 1");
    }

    @Test
    void groceryListWithTwoProducts_deleteGrocerylist_fkConstraintFailure() {
        //Arrange
        var grocerylist1 = new Grocerylist("Test name 1");
        var product1 = new Product("Product name 1");
        var product2 = new Product("Product name 2");
        var productOnGrocerylist = new ProductOnGrocerylist(product1, grocerylist1, 1);
        var productOnGrocerylist2 = new ProductOnGrocerylist(product2, grocerylist1, 1);

        saveGrocerylist(grocerylist1);
        saveAllProducts(List.of(product1, product2));
        saveAllProductsOnGrocerylist(List.of(productOnGrocerylist, productOnGrocerylist2));

        assertThat(findAllGrocerylists()).hasSize(1);
        assertThat(findAllProducts()).hasSize(2);
        assertThat(findAllProductsOnGrocerylists()).hasSize(2);

        //Act
        assertThrows(
                DataIntegrityViolationException.class,
                () -> deleteGrocerylistAndFlush(grocerylist1));
    }

    private Grocerylist saveGrocerylist(Grocerylist grocerylist1) {
        return grocerylistRepo.save(grocerylist1);
    }

    private List<Product> saveAllProducts(List<Product> products) {
        return productRepo.saveAll(products);
    }

    private void saveAllProductsOnGrocerylist(List<ProductOnGrocerylist> productsOnGrocerylist) {
        productOnGrocerylistRepo.saveAll(productsOnGrocerylist);
    }

    private List<Grocerylist> findAllGrocerylists() {
        return grocerylistRepo.findAll();
    }

    private List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    private List<ProductOnGrocerylist> findAllProductsOnGrocerylists() {
        return productOnGrocerylistRepo.findAll();
    }

    private void deleteGrocerylistAndFlush(Grocerylist grocerylist1) {
        grocerylistRepo.delete(grocerylist1);
        grocerylistRepo.flush();
    }
}