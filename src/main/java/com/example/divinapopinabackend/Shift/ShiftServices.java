package com.example.divinapopinabackend.Shift;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class ShiftServices { @Autowired
ShiftRepository shiftRepository;

    public Shift getshiftById(long id){
        return shiftRepository.getReferenceById(id);
    }
    public List<Shift> getshiftByDate(Date date){
        return shiftRepository.findShiftByDate(date);
    }


    public void saveshift(Shift shift){
        shiftRepository.save(shift);
    }
    public void removeshift(long id){
        shiftRepository.deleteById(id);
    }
    public List<Shift> getshifts(){
        return shiftRepository.findAll();
    }
}
