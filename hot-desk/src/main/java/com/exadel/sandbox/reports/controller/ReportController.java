package com.exadel.sandbox.reports.controller;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.reports.dto.SeatReportDto;
import com.exadel.sandbox.reports.service.ReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("report")
@RequiredArgsConstructor
public class ReportController {
    @Autowired
    private ReportService service;

    @GetMapping("list")
    public List<Employee> findAllEmployees() {
        return service.findAllEmployees();
    }

    @GetMapping("/{fileFormat}")
    public ResponseEntity<byte[]> generateReport(@PathVariable String fileFormat) throws IOException, JRException {
        //return service.generateReport(fileFormat);
        byte [] data = service.generateReport(fileFormat);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employees.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }

    @GetMapping("office/{id}")
    public List<SeatReportDto> seatReport(@PathVariable Long id, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return service.seatReport(id, startDate, endDate);
    }
}
