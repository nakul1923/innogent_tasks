package com.innogent.ecommerce.mapper;

import com.innogent.ecommerce.dto.PromocodeDto;
import com.innogent.ecommerce.entity.Promocode;
import org.springframework.stereotype.Component;

@Component
public class PromocodeMapper {

    public PromocodeDto toDto(Promocode promocode){

        if(promocode==null)return null;

        return PromocodeDto.builder()
                .code(promocode.getCode())
                .active(promocode.getActive())
                .discountPercentage(promocode.getDiscountPercentage())
                .build();
    }
}
