package com.exadel.sandbox.reports.service;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.reports.dto.SeatReportDto;
import net.sf.jasperreports.engine.JRException;
import org.apache.tomcat.jni.Local;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    public byte[] generateReport(String fileFormat) throws JRException, IOException;

    public byte[] generateBookingReportByOffice(String fileFormat) throws JRException, IOException;

    public List<Employee> findAllEmployees();

    List<SeatReportDto> seatReport(Long id, LocalDate startDate, LocalDate endDate);
}
