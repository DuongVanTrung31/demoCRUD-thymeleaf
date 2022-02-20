package service.impl;

import model.Category;
import model.Product;


import service.IProductService;

import java.util.ArrayList;
import java.util.List;


public class ProductServiceImpl implements IProductService {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Category> categories = new ArrayList<>();
    private static Integer autoIncrementId = 0;

    static {
        products.add(new Product(++autoIncrementId,"Iphone 11 Pro Max",1000, "Good","Điện thoại"));
        products.add(new Product(++autoIncrementId,"Tablet Samsung 3",1200, "Good","Máy tính bảng"));
        products.add(new Product(++autoIncrementId,"Iphone 13 Pro Max",1600, "Best","Điện thoại"));
        products.add(new Product(++autoIncrementId,"Laptop Dell",1500, "Not bab","Laptop"));
        products.add(new Product(++autoIncrementId,"Laptop Acer",1100, "OK","Laptop"));
        categories.add(new Category(1,"Điện thoại"));
        categories.add(new Category(1,"Máy tính bảng"));
        categories.add(new Category(1,"Laptop"));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findOne(Integer id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Product save(Product product) {
        return product.getId() == null ? persist(product) : merge(product);
    }

    private Product merge(Product product){
        Product original = findOne(product.getId());
        original.setName(product.getName());
        original.setPrice(product.getPrice());
        original.setDescription(product.getDescription());
        original.setCategory(product.getCategory());
        return original;
    }

    private Product persist(Product product) {
        Product clone = product.clone();
        clone.setId(++autoIncrementId);
        products.add(clone);
        return clone;
    }


    @Override
    public void delete(Integer id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public void delete(Product product) {
        delete(product.getId());
    }

    @Override
    public void deleteAll() {
        products = new ArrayList<>();
    }

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public Category findCategory(int id) {
        return categories.stream().filter(category -> category.getId() == id).findFirst().orElse(null);
    }
}
