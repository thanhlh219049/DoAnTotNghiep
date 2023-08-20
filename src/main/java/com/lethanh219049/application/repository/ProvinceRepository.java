package com.lethanh219049.application.repository;

import com.lethanh219049.application.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM province where id= :id")
    Province getProvince(Long id);

}