package com.exadel.sandbox.reports.service;

import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.entity.BookingDates;
import com.exadel.sandbox.booking.repository.BookingRepository;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorResponseDto;
import com.exadel.sandbox.officeFloor.entities.Floor;
import com.exadel.sandbox.reports.entity.SeatBooking;
import com.exadel.sandbox.reports.service.ReportService;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.repository.EmployeeRepository;

import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.sandbox.seat.entity.Seat;
import com.exadel.sandbox.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.apache.http.client.methods.HttpHead;
import org.apache.tomcat.jni.Local;
import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.graph.RootGraph;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.stat.SessionStatistics;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;


@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ModelMapper mapper;
    private final EmployeeRepository employeeRepository;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;

    @Override
    public byte[] generateReport(String reportFormat) throws FileNotFoundException, JRException {
        List<Booking> bookings = bookingRepository.findAll();
        
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:Reports/employees.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bookings);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Nika");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        byte[] report = null;
        if (reportFormat.equalsIgnoreCase("pdf")) {
            //JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
            report = JasperExportManager.exportReportToPdf(jasperPrint);
        }

        return report;
    }

    @Override
    public byte[] generateBookingReportByOffice(String reportFormat) throws FileNotFoundException, JRException {

        List<Seat> seats = seatRepository.findAll();
        File file = ResourceUtils.getFile("classpath:Reports/booking_by_office.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(seats);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Nika");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        byte[] report = null;
        if (reportFormat.equalsIgnoreCase("pdf")) {
            //JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
            report = JasperExportManager.exportReportToPdf(jasperPrint);
        }
        return null;
    }


    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

}