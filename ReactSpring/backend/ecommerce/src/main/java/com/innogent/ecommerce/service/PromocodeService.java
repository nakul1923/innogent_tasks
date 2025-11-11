package com.innogent.ecommerce.service;

import com.innogent.ecommerce.dto.PromocodeDto;
import com.innogent.ecommerce.entity.Promocode;
import com.innogent.ecommerce.mapper.PromocodeMapper;
import com.innogent.ecommerce.repository.PromocodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PromocodeService {

    private final PromocodeMapper promocodeMapper;
    private final PromocodeRepository promocodeRepository;
    public List<PromocodeDto> getAllActivePromocode(){

        return  promocodeRepository.findAll().stream().filter((pr)->pr.getActive()).map(promocodeMapper::toDto).toList();
    }

    public PromocodeDto validatePromocode(String code){

        LocalDateTime now = LocalDateTime.now();
        Promocode promocode = promocodeRepository.findByCodeAndActiveTrueAndValidTillAfter(code,now);

        return promocodeMapper.toDto(promocode);
    }
}
