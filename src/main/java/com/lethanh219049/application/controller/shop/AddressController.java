package com.lethanh219049.application.controller.shop;

import com.lethanh219049.application.entity.District;
import com.lethanh219049.application.entity.Product;
import com.lethanh219049.application.entity.Province;
import com.lethanh219049.application.entity.Wards;
import com.lethanh219049.application.exception.NotFoundException;
import com.lethanh219049.application.model.dto.DetailProductInfoDTO;
import com.lethanh219049.application.service.ProductService;
import com.lethanh219049.application.service.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ProductService productService;

    @GetMapping("/provinces")
    public String getProvinces(Model model, @RequestParam String id) {
        // Lấy chi tiết sản phẩm
        DetailProductInfoDTO product;
        try {
            product = productService.getDetailProductById(id);
        } catch (NotFoundException ex) {
            return "error/404";
        } catch (Exception ex) {
            return "error/500";
        }
        model.addAttribute("product", product);
        List<Province> provinces = addressService.getAllProvinces();
        model.addAttribute("provinces", provinces);
        return "shop/payment"; // Tên của template Thymeleaf
    }

    @GetMapping("/districts/{selectedCityId}")
    public String getDistricts(@PathVariable Long selectedCityId, Model model, @RequestParam String id) {
        // Lấy chi tiết sản phẩm
        DetailProductInfoDTO product;
        try {
            product = productService.getDetailProductById(id);
        } catch (NotFoundException ex) {
            return "error/404";
        } catch (Exception ex) {
            return "error/500";
        }
        model.addAttribute("product", product);
        List<District> districts = addressService.getDistrictsByProvince(selectedCityId);
        model.addAttribute("districts", districts);
        return "shop/payment"; // Tên của template Thymeleaf
    }

    @GetMapping("/wards/{selectedDistrictId}")
    public String getWards(@PathVariable Long selectedDistrictId, Model model, @RequestParam String id) {
        // Lấy chi tiết sản phẩm
        DetailProductInfoDTO product;
        try {
            product = productService.getDetailProductById(id);
        } catch (NotFoundException ex) {
            return "error/404";
        } catch (Exception ex) {
            return "error/500";
        }
        model.addAttribute("product", product);
        List<Wards> wards = addressService.getWardsByDistrict(selectedDistrictId);
        model.addAttribute("wards", wards);
        return "shop/payment"; // Tên của template Thymeleaf
    }
}
