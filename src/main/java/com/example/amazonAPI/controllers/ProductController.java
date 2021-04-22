package com.example.amazonAPI.controllers;

import com.example.amazonAPI.CustomizedResponse;

import com.example.amazonAPI.Models.Product;
import com.example.amazonAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;

@CrossOrigin(origins ="https://serene-temple-92942.herokuapp.com")
@RestController
public class ProductController
{
    @Autowired
    private ProductService service;
    @GetMapping("/products")
    public ResponseEntity getProducts()
    {
        var customizedResponse = new CustomizedResponse("A List Of Products",service.getProducts());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    @GetMapping("/products/bestSeller")
    public ResponseEntity getBestSeller(@RequestParam(value="best")String best)
    {
        var customizedResponse = new CustomizedResponse("A List Of Products",service.getBestSeller(best));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity getAProducts(@PathVariable("id")String id)
    {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Product with id:"+id, Collections.singletonList(service.getAProduct(id)));
        } catch (Exception e) {
            customizedResponse=new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(customizedResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);

    }
    @RequestMapping(value = "/products/category/{Id}")
    public ResponseEntity getProductCategory(@PathVariable String Id){
        var customizedResponse = new CustomizedResponse("A List Of Products in Category "+Id,service.getProductCategory(Id));

        return new ResponseEntity(customizedResponse,HttpStatus.OK);
        // return service.getProductCategory(Id);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id")String id)
    {
service.deleteAProduct(id);
 return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping(value="/products",consumes={
            MediaType.APPLICATION_JSON_VALUE
    })
    public void addProduct(@RequestBody Product product)
    {
        service.insertIntoProducts(product);
    }

@PutMapping(value="/products/{id}",consumes={
        MediaType.APPLICATION_JSON_VALUE
})
    public ResponseEntity editProduct (@PathVariable("id") String id,@RequestBody Product newProduct)
{
    var customizedResponse = new CustomizedResponse("Product with ID:"+id+" was updated successfully.", Collections.singletonList(service.editProduct(id, newProduct)));

    return new ResponseEntity(customizedResponse, HttpStatus.OK);
}
}