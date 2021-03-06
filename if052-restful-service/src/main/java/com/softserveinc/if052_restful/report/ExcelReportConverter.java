package com.softserveinc.if052_restful.report;

import com.softserveinc.if052_core.domain.*;
import com.softserveinc.if052_restful.service.UserService;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Danylo Tiahun on 09.04.2015.
 */

@Component
public class ExcelReportConverter extends ReportConverter {

    @Autowired
    private UserService userService;

    private final String[] UAPhrases = {"Звітність за період", "для адреси", "Поточний тариф", "Дата", "Показник", "Тариф", "Вартість", "Статус оплати", "Оплачено", "Неоплачено"};
    private final String[] ENPhrases = {"Statements for the period", "for address", "Current tariff", "Date", "Indicator", "Tariff", "Cost", "Payment status", "Paid", "Not Paid"};
    private String[] phrases = {};

    private XSSFWorkbook workbook = new XSSFWorkbook();
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private int cellId;
    private int rowId;
    private final int CELL_STEP = 3;
    private final int CELL_RANGE = 14;

    private DateFormat df;
    private int previousValue = 0;

    private static Logger LOGGER = Logger.getLogger(ExcelReportConverter.class.getName());

    @Override
    public byte[] convert(Report report) {
        setLocale(report);
        User user = userService.getReportUserByLogin(report.getUsers().get(0).getLogin());
        LOGGER.info("Creating new Excel workbook for user with login " + report.getUsers().get(0).getLogin());
        this.workbook = new XSSFWorkbook();
        for (Address a : user.getAddresses()) {
            rowId = 0;
            cellId = 0;
            sheet = workbook.createSheet(a.getCity() + ", " + a.getStreet() + " "
                    + a.getBuilding() + " " + a.getApartment());
            writeTitleRow(a, report.getStartDate(), report.getEndDate());
            for (WaterMeter wm : a.getWaterMeters()) {
                previousValue = 0;
                writeMeterData(wm);
                writeMeterColumnNames();
                for (Indicator i : wm.getIndicators()) {
                    if (i.getDate().compareTo(report.getStartDate()) >= 0
                            && i.getDate().compareTo(report.getEndDate()) <= 0) {
                        writeIndicatorData(i);
                    }
                }
                merge(rowId);
                rowId++;
            }
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);
            byte[] outputByte = out.toByteArray();
            out.flush();
            out.close();
            workbook.close();
            LOGGER.info("Workbook was written successfully.");
            return outputByte;
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }

        return new byte[]{};
    }

    private void writeTitleRow(Address a, Date startDate, Date endDate) {
        sheet.addMergedRegion(new CellRangeAddress(rowId, rowId, cellId, CELL_RANGE));
        row = sheet.createRow(rowId++);
        row.setHeight((short) 700);
        cell = row.createCell(cellId++);
        cell.setCellValue(phrases[0] + " " + df.format(startDate) + " - " + df.format(endDate)
                + " " + phrases[1] + " " + a.getCity() + ", " + a.getStreet() + " " + a.getBuilding() + "/" + a.getApartment());
        cell.setCellStyle(titleRowStyle());
    }

    private void writeMeterData(WaterMeter wm) {
        sheet.addMergedRegion(new CellRangeAddress(rowId, rowId, 0, CELL_RANGE));
        rowId++;
        sheet.addMergedRegion(new CellRangeAddress(rowId, rowId, 0, CELL_RANGE));
        row = sheet.createRow(rowId++);
        row.setHeight((short) 500);
        cellId = 0;
        cell = row.createCell(cellId++);
        cell.setCellValue(wm.getMeterType().getType() + " (" + wm.getName() + "). " + phrases[2] + " = " + wm.getTariff());
        cell.setCellStyle(meterDataStyle());
    }

    private void writeMeterColumnNames() {
        merge(rowId);
        row = sheet.createRow(rowId++);
        Map<Integer, String> columnNames = new HashMap<Integer, String>();
        String[] colNames = {phrases[3], phrases[4], phrases[5], phrases[6], phrases[7]};
        for (int i = 0; i < colNames.length; i++) {
            columnNames.put(i * CELL_STEP, colNames[i]);
        }
        for (Map.Entry<Integer, String> me : columnNames.entrySet()) {
            cell = row.createCell(me.getKey());
            cell.setCellValue(me.getValue());
            cell.setCellStyle(meterColumnNamesStyle());
        }
    }

    private void writeIndicatorData(Indicator i) {
        merge(rowId);
        row = sheet.createRow(rowId++);
        cellId = 0;
        cell = row.createCell(cellId);
        cell.setCellValue(df.format(i.getDate()));
        cell.setCellStyle(indicatorDataStyle());
        cell = row.createCell(cellId += CELL_STEP);
        cell.setCellValue(i.getValue());
        cell = row.createCell(cellId += CELL_STEP);
        cell.setCellValue(i.getTariffPerDate());
        cell = row.createCell(cellId += CELL_STEP);
        cell.setCellValue((i.getValue() - previousValue) * i.getTariffPerDate());
        cell = row.createCell(cellId += CELL_STEP);
        cell.setCellValue(i.isPaid() ? phrases[8] : phrases[9]);
        cell.setCellStyle(indicatorDataStyle());
        previousValue = i.getValue();
    }

    private XSSFCellStyle titleRowStyle() {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(32, 255, 181)));
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style.setFont(titleRowFont());
        return style;
    }

    private XSSFCellStyle meterDataStyle() {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(145, 248, 250)));
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style.setFont(titleRowFont());
        return style;
    }

    private XSSFCellStyle meterColumnNamesStyle() {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(230, 224, 142)));
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setFont(titleRowFont());
        return style;
    }

    private XSSFCellStyle indicatorDataStyle() {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        return style;
    }

    private Font titleRowFont() {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setFontName("Arial");
        font.setBold(true);
        return font;
    }

    private void merge(int rowId) {
        for (int j = 0; j <= CELL_RANGE; j += CELL_STEP) {
            sheet.addMergedRegion(new CellRangeAddress(rowId, rowId, j, j + CELL_STEP - 1));
        }
    }

    private void setLocale(Report report) {
        if (report.getLocale().equals("uk")) {
            phrases = UAPhrases;
        } else {
            phrases = ENPhrases;
        }
        df = new SimpleDateFormat(report.getDateFormat());
    }

}
