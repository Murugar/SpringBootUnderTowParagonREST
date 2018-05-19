package com.iqmsoft.service;

import org.springframework.context.ApplicationEvent;

public class HotelServiceEvent extends ApplicationEvent {

   	private static final long serialVersionUID = 1L;

	public HotelServiceEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "My HotelService Event";
    }
}