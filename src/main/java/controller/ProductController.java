package controller;

import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IProductService;

import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public ModelAndView showProducts() {
        ModelAndView modelAndView = new ModelAndView("view");
        List<Product> products = productService.findAll();
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteProduct(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("view");
        productService.delete(productService.findOne(id));
        List<Product> products = productService.findAll();
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewDetail(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.findOne(id);
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editProduct(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        List<Category> categories = productService.getAll();
        Product product = productService.findOne(id);
        modelAndView.addObject("categories",categories);
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView saveProduct(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("view");
        productService.save(product);
        List<Product> products = productService.findAll();
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createProduct(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView("edit");
        List<Category> categories = productService.getAll();
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }
}
