package com.exadel.sandbox.reports.controller;

import com.exadel.sandbox.reports.dto.BookingReportDto;
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

    @GetMapping("booking_by_office")
    public ResponseEntity<byte[]> getBookingByOfficeReport(@RequestParam(name = "file_format") String fileFormat,
                                                           @RequestParam(name = "office_id") Long officeId,
                                                           @RequestParam(name = "start_date") String startDate,
                                                           @RequestParam(name = "end_date") String endDate) throws IOException, JRException {
        //return service.generateReport(fileFormat);
        byte[] data = service.getBookingByOfficeReport(fileFormat, officeId, LocalDate.parse(startDate), LocalDate.parse(endDate));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employees.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }

}
