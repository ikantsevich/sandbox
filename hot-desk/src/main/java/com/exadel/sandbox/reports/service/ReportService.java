package com.exadel.sandbox.reports.service;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public interface ReportService {

    byte[] getBookingByOfficeReport(Long officeId, LocalDate startDate, LocalDate endDate) throws JRException, IOException;

    byte[] getBookingByCityReport(String city, LocalDate startDate, LocalDate endDate) throws JRException, IOException;

    byte[] getBookingByFloorReport(Long floorId, LocalDate startDate, LocalDate endDate) throws FileNotFoundException, JRException;

    byte[] getAllBookingReport(LocalDate startDate, LocalDate endDate) throws FileNotFoundException, JRException;

    byte[] getBookingByEmployee(Long employeeId, LocalDate startDate, LocalDate endDate) throws FileNotFoundException, JRException;
}
