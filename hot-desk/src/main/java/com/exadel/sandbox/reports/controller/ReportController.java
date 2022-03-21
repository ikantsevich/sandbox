package com.exadel.sandbox.reports.controller;

import com.exadel.sandbox.reports.service.ReportService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("report")
@RequiredArgsConstructor
public class ReportController {
    @Autowired
    private ReportService service;

    @GetMapping("booking_by_office")
    public ResponseEntity<byte[]> getBookingByOfficeReport(@RequestParam(name = "office_id") Long officeId,
                                                           @RequestParam(name = "start_date") String startDate,
                                                           @RequestParam(name = "end_date") String endDate) throws IOException, JRException {
        byte[] data = service.getBookingByOfficeReport(officeId, LocalDate.parse(startDate), LocalDate.parse(endDate));
        return getResponse("booking_by_office.pdf", data);
    }

    @GetMapping("booking_by_city")
    public ResponseEntity<byte[]> getBookingByCityReport(@RequestParam(name = "city") String city,
                                                         @RequestParam(name = "start_date") String startDate,
                                                         @RequestParam(name = "end_date") String endDate) throws IOException, JRException {
        byte[] data = service.getBookingByCityReport(city, LocalDate.parse(startDate), LocalDate.parse(endDate));
        return getResponse("booking_by_city.pdf", data);
    }

    @GetMapping("booking_by_floor")
    public ResponseEntity<byte[]> getBookingByFloorReport(@RequestParam(name = "floor_id") Long floorId,
                                                          @RequestParam(name = "start_date") String startDate,
                                                          @RequestParam(name = "end_date") String endDate) throws IOException, JRException {
        byte[] data = service.getBookingByFloorReport(floorId, LocalDate.parse(startDate), LocalDate.parse(endDate));
        return getResponse("booking_by_floor.pdf", data);
    }

    @GetMapping("all_bookings")
    public ResponseEntity<byte[]> getAllBookingReport(@RequestParam(name = "start_date") String startDate,
                                                      @RequestParam(name = "end_date") String endDate) throws IOException, JRException {
        byte[] data = service.getAllBookingReport(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return getResponse("all_bookings.pdf", data);
    }

    @GetMapping("booking_by_employee")
    public ResponseEntity<byte[]> getBookingByEmployee(@RequestParam(name = "employee_id") Long employeeId,
                                                       @RequestParam(name = "start_date") String startDate,
                                                       @RequestParam(name = "end_date") String endDate) throws IOException, JRException {
        byte[] data = service.getBookingByEmployee(employeeId, LocalDate.parse(startDate), LocalDate.parse(endDate));
        return getResponse("booking_by_employee.pdf", data);
    }

    private ResponseEntity<byte[]> getResponse(String fileName, byte[] data) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; " + fileName);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }

}
