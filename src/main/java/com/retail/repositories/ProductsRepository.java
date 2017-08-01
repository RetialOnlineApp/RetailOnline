package com.retail.repositories;


import com.retail.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Product, Integer>{
}
