package com.exadel.sandbox.reports.service;

import com.exadel.sandbox.exception.exceptions.DateOutOfBoundException;
import com.exadel.sandbox.reports.dto.SeatReportDto;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.repository.EmployeeRepository;

import com.exadel.sandbox.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;


@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final EmployeeRepository employeeRepository;
    private final SeatRepository seatRepository;

    @Override
    public byte[] generateReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\user\\Desktop\\Report";
        List<Employee> employees = employeeRepository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:Reports/employees.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Nika");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        byte[] report = null;
        if (reportFormat.equalsIgnoreCase("pdf")) {
            //JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
            report = JasperExportManager.exportReportToPdf(jasperPrint);
        }
        /*
        if(reportFormat.equalsIgnoreCase("csv")){
            JRCsvExporter exporter = new JRCsvExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleWriterExporterOutput(path + "\\employees.pdf"));
            SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
            configuration.setWriteBOM(Boolean.TRUE);
            configuration.setRecordDelimiter("\r\n");
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }
        */

        return report;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<SeatReportDto> seatReport(Long id, LocalDate startDate, LocalDate endDate) {
        long between = DAYS.between(startDate, endDate) + 1;

        if (between < 0)
            throw new DateOutOfBoundException();
        List<SeatReportDto> reportOfSeatsById = seatRepository.findReportOfSeatsById(id, startDate, endDate);
        reportOfSeatsById.forEach(seatReportDto -> seatReportDto.setFreeDates(between - seatReportDto.getBookedDates()));

        return reportOfSeatsById;
    }
}