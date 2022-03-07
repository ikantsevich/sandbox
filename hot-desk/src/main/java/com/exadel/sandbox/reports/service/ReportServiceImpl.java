package com.exadel.sandbox.reports.service;

import com.exadel.sandbox.reports.service.ReportService;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.apache.http.client.methods.HttpHead;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final EmployeeRepository employeeRepository;

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
}