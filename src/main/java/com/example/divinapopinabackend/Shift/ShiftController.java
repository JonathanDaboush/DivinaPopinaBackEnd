package com.example.divinapopinabackend.Shift;

import com.example.divinapopinabackend.Employee.Employee;
import com.example.divinapopinabackend.Employee.EmployeeServices;
import com.example.divinapopinabackend.Reservation.Reservation;
import com.example.divinapopinabackend.Sercurity.Util.JasyptConfig;
import com.example.divinapopinabackend.Shift.Shift;
import com.example.divinapopinabackend.Shift.ShiftServices;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/shift")
@CrossOrigin
public class ShiftController  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired ShiftServices shiftServices;
    @Autowired
    EmployeeServices employeeServices;
    @Autowired JasyptConfig jasyptConfig;

    @GetMapping
    public List<Shift> getshifts() {


        List<Shift>  shifts=shiftServices.getshifts();

        return  shifts;
    }

    @GetMapping("/{id}")
    public Shift getShift(@PathVariable Long id) throws MessagingException, IOException {
        Shift shift=shiftServices.getshiftById(id);
        return shift;
    }
    @GetMapping("/byName/{date}")
    public List<Shift>  getshiftByDate(@PathVariable Date date) throws MessagingException, IOException {
       return shiftServices.getshiftByDate(date);
    }
    @PostMapping
    public ResponseEntity createShift(@RequestParam Time startTime, @RequestParam  Time endTime, @RequestParam Date dateOfShift,@RequestParam long id) throws URISyntaxException {
        Employee employee=employeeServices.getemployeeById(id);
        Shift shift=new Shift(startTime,endTime,dateOfShift,employee);
        shiftServices.saveshift(shift);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createShift(@RequestParam  long id,@RequestParam Time startTime, @RequestParam  Time endTime, @RequestParam Date dateOfShift,@RequestParam long employeeId) throws URISyntaxException {
        Shift shift=shiftServices.getshiftById(id);
        shift.setStartTime(startTime);
        shift.setEndTime(endTime);
        shift.setDateOfShift(dateOfShift);
        shift.setEmployee(employeeServices.getemployeeById(employeeId));
        shiftServices.saveshift(shift);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteshift(@PathVariable Long id) {
        shiftServices.removeshift(id);
        return ResponseEntity.ok().build();
    }


}
