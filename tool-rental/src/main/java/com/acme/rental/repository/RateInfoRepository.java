package com.acme.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.rental.entity.RateInfo;

@Repository
public interface RateInfoRepository extends JpaRepository<RateInfo, Long> {
    RateInfo findByToolType(String toolType);
}
