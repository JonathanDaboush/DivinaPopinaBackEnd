package com.example.divinapopinabackend.Reservation;

import com.example.divinapopinabackend.Reservation.Reservation;
import com.example.divinapopinabackend.Reservation.ReservationServices;
import com.example.divinapopinabackend.Sercurity.Util.JasyptConfig;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@CrossOrigin
public class ReservationController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired ReservationServices reservationServices;
    @Autowired
    JasyptConfig jasyptConfig;



    public ReservationController(ReservationServices reservationServices, JasyptConfig jasyptConfig) {
        this.reservationServices = reservationServices;
        this.jasyptConfig = jasyptConfig;
    }

    @GetMapping
    public List<Reservation> getreservations() {
        return reservationServices.getReservations();
    }

    @GetMapping("/{id}")
    public Reservation getreservation(@PathVariable Long id) throws MessagingException, IOException {
        Reservation reservation=reservationServices.getreservationById(id);
        return reservation;
    }
    @GetMapping("/byName/{name}")
    public Reservation getreservationByName(@PathVariable String name) throws MessagingException, IOException {
        Reservation reservation=reservationServices.getReservationByName(name);
        return reservation;
    }
    @GetMapping("byDate/{date}")
    public Reservation getreservationByName(@PathVariable Date date) throws MessagingException, IOException {
        Reservation reservation=reservationServices.getReservationByDate(date);
        return reservation;
    }
    @PostMapping
    public ResponseEntity createreservation(@RequestParam  String name, @RequestParam  float cost, @RequestParam  String note, @RequestParam Date dateOfEvent) throws URISyntaxException {
        Reservation reservation=new Reservation(name,cost,note,dateOfEvent);
        reservationServices.saveReservation(reservation);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createreservation(@RequestParam  long id,@RequestParam  String name,@RequestParam  float cost,@RequestParam  String note,@RequestParam  Date dateOfEvent) throws URISyntaxException {
        Reservation reservation=reservationServices.getreservationById(id);
        reservation.setName(name);
        reservation.setCost(cost);
        reservation.setNote(note);
        reservation.setDateOfEvent(dateOfEvent);
        reservationServices.saveReservation(reservation);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation(@PathVariable Long id) {
        reservationServices.removeReservation(id);
        return ResponseEntity.ok().build();
    }
}

