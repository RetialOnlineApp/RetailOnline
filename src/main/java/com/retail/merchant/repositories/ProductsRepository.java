package com.retail.merchant.repositories;


import com.retail.merchant.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Product, Integer>{
}
