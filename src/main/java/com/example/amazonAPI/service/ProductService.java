package com.example.amazonAPI.service;

import com.example.amazonAPI.Models.Product;
import com.example.amazonAPI.Models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Product> getProducts() {
        //validation

        //calculation
        return repository.findAll();
    }
    public List<Product> getBestSeller(String best)
    {
        Query query= new Query();
        query.addCriteria(Criteria.where("bestSeller").is(best));
        List<Product> products=mongoTemplate.find(query,Product.class);
        return products;
    }

    public void insertIntoProducts(Product product)
    {
        repository.insert(product);
    }

    public Product editProduct(String id,Product newProductData)
    {
        Optional<Product> product=repository.findById(id);

        product.get().setTitle(newProductData.getTitle());
        product.get().setDescription(newProductData.getDescription());
        product.get().setPrice(newProductData.getPrice());
        product.get().setPoster(newProductData.getPoster());
        product.get().setCategory(newProductData.getCategory());
        product.get().setBestSeller(newProductData.getBestSeller());

        Product updatedProduct=repository.save(product.get());
        return updatedProduct;
    }


    public Optional<Product> getAProduct(String id) throws Exception
    {

        Optional<Product>product = repository.findById(id);
        if(!product.isPresent())
        {
            throw new Exception ("Product with "+id+" not found");
        }
        return product;
    }
    

   public List<Product> getProductCategory(String Id)
   {

        return repository.findByCategory(Id);
   }

   public void deleteAProduct(String id)
    {
        repository.deleteById(id);
    }

}
