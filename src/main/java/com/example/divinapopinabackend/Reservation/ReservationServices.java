package com.example.divinapopinabackend.Reservation;

import com.example.divinapopinabackend.Reservation.Reservation;
import com.example.divinapopinabackend.Reservation.ReservationRepository;
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

    public Reservation getReservationByName(String name){
        return reservationRepository.findReservationByName(name).get(0);
    }
    public Reservation getReservationByDate(Date date){
        return reservationRepository.findReservationByDate(date).get(0);
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
