package com.example.Customers.Controller;

import java.util.Optional;

import com.example.Customers.Model.Product;

import com.example.Customers.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // create new customer
    @PostMapping("/add")
    public Product addNewProduct(@RequestBody Product newProduct){
        Product product = new Product();
        product.setName(newProduct.getName());
        product.setQuantity(newProduct.getQuantity());
        product.setPrice(newProduct.getPrice());
        productRepository.save(product);
        return product; 
    }

    // view all customers
    @GetMapping("view/all")
    public @ResponseBody Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }

    // view specific customer
    @GetMapping("view/{id}")
    public Optional<Product> getProduct(@PathVariable Integer id) {
        return productRepository.findById(id);
    }

    // update an existing customer
    @PutMapping("/edit/{id}")
    public String update( @RequestBody Product updateProduct, @PathVariable Integer id) {
        return productRepository.findById(id)
                 .map(product -> {
                       product.setName(updateProduct.getName());
                       product.setQuantity(updateProduct.getQuantity());
                       product.setPrice(updateProduct.getPrice());
                       productRepository.save(product);
                       return "Product details have been successfully updated!";
                 }).orElseGet(() -> {
                       return "This product doesn't exist";
                 });
    }

    // delete customer
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id")Integer id) {
    	productRepository.deleteById(id); 
        return "Product has been successfully deleted!";
    }
}
