package com.innogent.ecommerce.controller;

import com.innogent.ecommerce.dto.PromocodeDto;
import com.innogent.ecommerce.service.PromocodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promocode")
@RequiredArgsConstructor
@CrossOrigin
public class PromocodeController {

    private final PromocodeService promocodeService;

    @GetMapping("/")
    public ResponseEntity<List<PromocodeDto>> getAllActivePromocode(){

        return ResponseEntity.ok(promocodeService.getAllActivePromocode());
    }

    @GetMapping("/validate/{code}")
    public ResponseEntity<PromocodeDto> validatePromocode(@PathVariable String code){

        return ResponseEntity.ok(promocodeService.validatePromocode(code));
    }
}
