package com.exadel.sandbox.reports.service;

import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.entity.BookingDates;
import com.exadel.sandbox.booking.repository.BookingRepository;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import com.exadel.sandbox.exception.exceptions.EntityNotFoundException;
import com.exadel.sandbox.reports.dto.BookingReportDto;
import com.exadel.sandbox.reports.dto.EmployeeReportDto;
import com.exadel.sandbox.reports.dto.SeatReportDto;
import com.exadel.sandbox.seat.entity.Seat;
import com.exadel.sandbox.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;


@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ModelMapper mapper;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public byte[] getBookingByOfficeReport(Long officeId, LocalDate startDate, LocalDate endDate) throws FileNotFoundException, JRException {
        List<Booking> bookings = bookingRepository.findBookingsByOfficeId(officeId);
        List<Seat> totalSeats = seatRepository.findSeatsByOfficeId(officeId);
        List<BookingReportDto> reportBookings = getReportBookings(bookings, startDate, endDate);
        Map<String, Object> parameters = new HashMap<>();
        Float bookingRatio = (float) reportBookings.size() / (totalSeats.size() * (startDate.until(endDate, DAYS) + 1)) * 100;
        parameters.put("pBookingRatio", bookingRatio);
        return getReportFile("classpath:Reports/booking_by_office.jrxml", reportBookings, parameters);
    }

    @Override
    public byte[] getBookingByCityReport(String city, LocalDate startDate, LocalDate endDate) throws FileNotFoundException, JRException {
        List<Booking> bookings = bookingRepository.findBookingsByCity(city);
        List<Seat> totalSeats = seatRepository.findSeatsByCity(city);
        List<BookingReportDto> reportBookings = getReportBookings(bookings, startDate, endDate);
        Map<String, Object> parameters = new HashMap<>();
        Float bookingRatio = (float) reportBookings.size() / (totalSeats.size() * (startDate.until(endDate, DAYS) + 1)) * 100;
        parameters.put("pBookingRatio", bookingRatio);
        parameters.put("pCityName", city);
        return getReportFile("classpath:Reports/booking_by_city.jrxml", reportBookings, parameters);
    }

    @Override
    public byte[] getBookingByFloorReport(Long floorId, LocalDate startDate, LocalDate endDate) throws FileNotFoundException, JRException {
        List<Booking> bookings = bookingRepository.findBookingsByFloorId(floorId);
        List<Seat> totalSeats = seatRepository.findSeatsByFloorId(floorId);
        List<BookingReportDto> reportBookings = getReportBookings(bookings, startDate, endDate);
        Map<String, Object> parameters = new HashMap<>();
        Float bookingRatio = (float) reportBookings.size() / (totalSeats.size() * (startDate.until(endDate, DAYS) + 1)) * 100;
        parameters.put("pBookingRatio", bookingRatio);
        return getReportFile("classpath:Reports/booking_by_floor.jrxml", reportBookings, parameters);
    }

    @Override
    public byte[] getAllBookingReport(LocalDate startDate, LocalDate endDate) throws FileNotFoundException, JRException {
        List<Booking> bookings = bookingRepository.findAll();
        List<Seat> totalSeats = seatRepository.findAll();
        List<BookingReportDto> reportBookings = getReportBookings(bookings, startDate, endDate);
        Map<String, Object> parameters = new HashMap<>();
        Float bookingRatio = (float) reportBookings.size() / (totalSeats.size() * (startDate.until(endDate, DAYS) + 1)) * 100;
        parameters.put("pBookingRatio", bookingRatio);
        return getReportFile("classpath:Reports/all_bookings.jrxml", reportBookings, parameters);
    }

    @Override
    public byte[] getBookingByEmployee(Long employeeId, LocalDate startDate, LocalDate endDate) throws FileNotFoundException, JRException {
        List<Booking> bookings = bookingRepository.findBookingsByEmployeeId(employeeId);
        EmployeeReportDto employeeReportDto = mapper.map(employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException("Employee not found!"))
                , EmployeeReportDto.class);
        List<BookingReportDto> reportBookings = getReportBookings(bookings, startDate, endDate);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pFirstName", employeeReportDto.getFirstname());
        parameters.put("pLastName", employeeReportDto.getLastname());
        parameters.put("pEmail", employeeReportDto.getEmail());
        parameters.put("pPosition", employeeReportDto.getPosition());
        return getReportFile("classpath:Reports/booking_by_employee.jrxml", reportBookings, parameters);
    }

    private byte[] getReportFile(String filePath, List<BookingReportDto> reportBookings, Map<String, Object> parameters) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile(filePath);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportBookings);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        byte[] report = null;
        report = JasperExportManager.exportReportToPdf(jasperPrint);
        return report;
    }

    private List<BookingReportDto> getReportBookings(List<Booking> bookings, LocalDate startDate, LocalDate endDate) {
        List<BookingReportDto> reportBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            for (BookingDates date : booking.getDates()) {
                if ((startDate.isEqual(date.getDate()) || startDate.isBefore(date.getDate())) &&
                        (endDate.isEqual(date.getDate()) || endDate.isAfter(date.getDate()))) {
                    BookingReportDto reportBooking = new BookingReportDto();
                    SeatReportDto seatReportDto = mapper.map(booking.getSeat(), SeatReportDto.class);
                    reportBooking.setId(booking.getId());
                    reportBooking.setSeatReportDto(seatReportDto);
                    reportBooking.setDate(date.getDate());
                    reportBookings.add(reportBooking);
                }
            }
        }
        return reportBookings;
    }

}