package com.iqmsoft.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.iqmsoft.dao.jpa.HotelRepository;
import com.iqmsoft.domain.Hotel;

@Service
public class HotelService {

    private static final Logger log = LoggerFactory.getLogger(HotelService.class);

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public HotelService() {
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel getHotel(long id) {
        return hotelRepository.findOne(id);
    }

    public void updateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.delete(id);
    }

  
    public Page<Hotel> getAllHotels(Integer page, Integer size) {
        Page pageOfHotels = hotelRepository.findAll(new PageRequest(page, size));
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("iqmsoft.HotelService.getAll.largePayload");
        }
        return pageOfHotels;
    }
}
