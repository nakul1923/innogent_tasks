package com.innogent.ecommerce.repository;


import com.innogent.ecommerce.entity.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;


public interface PromocodeRepository extends JpaRepository<Promocode,Long> {

    Promocode findByCodeAndActiveTrueAndValidTillAfter(String code, LocalDateTime now);
}
