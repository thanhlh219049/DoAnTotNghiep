package com.lethanh219049.application.repository;

import com.lethanh219049.application.entity.District;
import com.lethanh219049.application.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findByProvince(Long provinceId);

    @Query(nativeQuery = true, value = "SELECT * FROM District where id= :id")
    District getDistrict(Long id);

}