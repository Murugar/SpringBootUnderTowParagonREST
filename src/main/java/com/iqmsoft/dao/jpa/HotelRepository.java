package com.iqmsoft.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.iqmsoft.domain.Hotel;


public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findHotelByCity(String city);
    Page findAll(Pageable pageable);
}
