package com.lethanh219049.application.repository;


import com.lethanh219049.application.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

    Setting findByCode(String code);


}
