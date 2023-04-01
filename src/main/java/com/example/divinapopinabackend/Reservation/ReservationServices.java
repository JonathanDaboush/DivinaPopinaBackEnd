package com.example.divinapopinabackend.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class ReservationServices {
    @Autowired
    ReservationRepository reservationRepository;

    public Reservation getreservationById(long id){
        return reservationRepository.getReferenceById(id);
    }
    public List<String> getReservationNames(){
        return reservationRepository.findReservationNames();
    }

    public List<Reservation> getReservationByDate(Date date){
        return reservationRepository.findReservationByDate(date);
    }
    public List<Reservation> getReservationByName(String name){
        return reservationRepository.findReservationByName(name);
    }
    public void saveReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }
    public void removeReservation(long id){
        reservationRepository.deleteById(id);
    }
    public List<Reservation> getReservations(){
        return reservationRepository.findAll();
    }
}
