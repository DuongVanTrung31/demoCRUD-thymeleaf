package service;


import model.Category;
import model.Product;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface IProductService {
    List<Product> findAll();

    Product findOne(Integer id);

    Product save(Product product);

    void delete(Integer id);

    void delete(Product product);

    void deleteAll();

    List<Category> getAll();

    Category findCategory(int id);
}
