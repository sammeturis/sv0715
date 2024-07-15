package com.acme.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.rental.entity.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Holiday findByName(String name);

    List<Holiday> findByIsRecurring(boolean isRecurring);

    List<Holiday> findByDate(String date);

    List<Holiday> findByDescription(String description);
}
