package com.techstore.ecommercemaven.repository;

import com.techstore.ecommercemaven.model.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductViewRepository
        extends JpaRepository<ProductView,Long> {

}