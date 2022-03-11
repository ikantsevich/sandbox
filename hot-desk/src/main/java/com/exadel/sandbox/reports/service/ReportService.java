package com.exadel.sandbox.reports.service;

import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.time.LocalDate;

public interface ReportService {

    byte[] getBookingByOfficeReport(String reportFormat, Long officeId, LocalDate startDate, LocalDate endDate) throws JRException, IOException;

}
