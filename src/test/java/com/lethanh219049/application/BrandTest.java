package com.lethanh219049.application;


import com.lethanh219049.application.entity.Brand;
import com.lethanh219049.application.repository.BrandRepository;
import com.lethanh219049.application.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BrandTest {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandService brandService;


    @Test
    void getListBrandsTest(){
        List<Brand> brands = brandService.getListBrand();
        brands.forEach(System.out::println);
    }
}
