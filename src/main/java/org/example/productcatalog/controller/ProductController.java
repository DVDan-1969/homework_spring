package org.example.productcatalog.controller;

import org.example.productcatalog.model.Product;
import org.example.productcatalog.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {

        this.service = service;
    }


@GetMapping("/")
public String viewHome(Model model) {

    var products = service.getAll();

    System.out.println("Produse gasite: " + products);

    model.addAttribute("products", products);

    return "products";
}

    @GetMapping("/new")
    public String showForm(Model model) {

        model.addAttribute("product", new Product());

        return "add-product";
    }

    @PostMapping("/save")
    public String saveProduct(Product product) {

        service.save(product);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        service.deleteById(id);

        return "redirect:/";
    }

}
