package com.example.divinapopinabackend.Shift;

import com.example.divinapopinabackend.Employee.Employee;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shift")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "start_time",nullable = false)
    private Time startTime;
    @Column(name = "end_time",nullable = false)
    private Time endTime;
    @Column(name = "date_of_shift",nullable = false)
    private Date dateOfShift;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Shift(Time startTime, Time endTime, Date dateOfShift, Employee employee) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateOfShift = dateOfShift;
        this.employee = employee;
    }
}
