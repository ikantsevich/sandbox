package com.exadel.sandbox.employee.repository;

import com.exadel.sandbox.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByTgInfoChatId(String id);
    Optional<Employee> findEmployeeByEmail(String email);

    @Query(value = "select d.date from Booking b join b.employee e join b.dates d where e.id = :id and d.date >= :startDate and d.date <= :finishDate")
    List<LocalDate> findEmployeeBookedDates(Long id, LocalDate startDate, LocalDate finishDate);

    @Query(value = "select d.date from Booking b join b.employee e join b.dates d where e.id = :id")
    List<LocalDate> findEmployeeBookedDates(Long id);
}
