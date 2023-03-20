package com.example.divinapopinabackend.EmployeeTerm;

import com.example.divinapopinabackend.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTermRepository extends JpaRepository<EmployeeTerm, Long> {
}
