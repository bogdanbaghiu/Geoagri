/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bogdanbaghiu.manageips;

import com.bogdanbaghiu.manageips.model.IP;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Baghiu
 */
public class ManageIPs {

    private static final List<IP> ipList = new ArrayList<>();
    private static final String[] columns = {"IP", "COUNTRY", "REGION", "CITY"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        manageFile();
    }

    private static void manageFile() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            fileChooser.setFileFilter(filter);
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(fileChooser);
            if (result == JFileChooser.APPROVE_OPTION) {
                List<String> allLines = Files.readAllLines(Paths.get(fileChooser.getSelectedFile().getPath()));
                for (String line : allLines) {
                    ipList.add(makeCall(line));
                }
                generateXLS();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static IP makeCall(String ip) {
        try {
            OkHttpClient client = new OkHttpClient();
            String url = "https://freegeoip.app/json/" + ip;
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("content-type", "application/json")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String responseString = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    return jsonToIP(jsonObject);
                } catch (JSONException err) {
                    System.err.println(err.getMessage());
                    return null;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private static IP jsonToIP(JSONObject jsonObject) {
        IP ip1 = new IP(jsonObject.getString("ip"));
        ip1.setCity(jsonObject.getString("city"));
        ip1.setCountryCode(jsonObject.getString("country_code"));
        ip1.setMetroCode(jsonObject.getInt("metro_code"));
        ip1.setLatitude(jsonObject.getDouble("latitude"));
        ip1.setCountryName(jsonObject.getString("country_name"));
        ip1.setRegionName(jsonObject.getString("region_name"));
        ip1.setTimeZone(jsonObject.getString("time_zone"));
        ip1.setZipCode(jsonObject.getInt("zip_code"));
        ip1.setRegionCode(jsonObject.getString("region_code"));
        ip1.setLongitude(jsonObject.getDouble("longitude"));
        return ip1;
    }

    private static void generateXLS() {
        try {
            Workbook workbook = new XSSFWorkbook();
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Employee");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;
            for (IP ip : ipList) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0)
                        .setCellValue(ip.getIP());

                row.createCell(1)
                        .setCellValue(ip.getCountryName());

                row.createCell(2)
                        .setCellValue(ip.getRegionName());

                row.createCell(3)
                        .setCellValue(ip.getCity());
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            JFrame parentFrame = new JFrame();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            int userSelection = fileChooser.showSaveDialog(parentFrame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                FileOutputStream fileOut = new FileOutputStream(fileToSave.getAbsolutePath() + "ips.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}


