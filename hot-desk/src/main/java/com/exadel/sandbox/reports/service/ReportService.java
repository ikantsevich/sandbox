package com.exadel.sandbox.reports.service;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.entity.Employee;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    public byte[] generateReport(String fileFormat) throws JRException, IOException;

    public List<Employee> findAllEmployees();

}
