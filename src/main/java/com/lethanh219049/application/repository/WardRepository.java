package com.lethanh219049.application.repository;

import com.lethanh219049.application.entity.District;
import com.lethanh219049.application.entity.Wards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Wards, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM wards where district_id= :districtId")
    List<Wards> findByDistrictWards(Long districtId);
}
