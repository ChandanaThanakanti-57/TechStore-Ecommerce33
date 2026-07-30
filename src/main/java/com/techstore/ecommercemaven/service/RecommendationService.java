package com.techstore.ecommercemaven.service;


import com.techstore.ecommercemaven.model.Product;
import com.techstore.ecommercemaven.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecommendationService {


    private final ProductRepository productRepository;


    public RecommendationService(
            ProductRepository productRepository){

        this.productRepository =
                productRepository;
    }



    public List<Product> getRecommendations(){

        return productRepository
                .findAll()
                .stream()
                .limit(4)
                .toList();

    }

}