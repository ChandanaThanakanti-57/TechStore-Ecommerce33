package com.techstore.ecommercemaven.service;


import com.techstore.ecommercemaven.model.Product;
import com.techstore.ecommercemaven.repository.ProductRepository;

import org.springframework.stereotype.Service;


@Service
public class AnalyticsService {


    private final ProductRepository productRepository;


    public AnalyticsService(
            ProductRepository productRepository){

        this.productRepository =
                productRepository;

    }



    public Product bestSellingProduct(){


        return productRepository
                .findAll()
                .stream()
                .findFirst()
                .orElse(null);


    }



    public long totalProducts(){

        return productRepository.count();

    }


}