package com.lethanh219049.application.service.impl;

import com.lethanh219049.application.entity.District;
import com.lethanh219049.application.entity.Province;
import com.lethanh219049.application.entity.Wards;
import com.lethanh219049.application.repository.DistrictRepository;
import com.lethanh219049.application.repository.ProvinceRepository;
import com.lethanh219049.application.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private WardRepository wardRepository;

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public List<District> getDistrictsByProvince(Long provinceId) {
        return districtRepository.findByProvince(provinceId);
    }

    public List<Wards> getWardsByDistrict(Long districtId) {
        return wardRepository.findByDistrict(districtId);
    }

    public Province getProvinceById(Long provinceId) {
        return provinceRepository.getProvince(provinceId);
    }

    public District getDistrictById(Long districtId) {
        return districtRepository.getDistrict(districtId);
    }
}

