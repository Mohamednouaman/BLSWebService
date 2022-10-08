package org.bls.helper.services;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.bls.helper.bo.Client;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@Service
public class ClientServiceImpl implements IClientService {


    private static final String excelFilePath = "client-info.xls";
    private static final String sheetName = "sheet.xls";


    @Override
    public boolean addClient(Client client) {

        File file = new File(excelFilePath);

        if (file.exists()) return updateFile(excelFilePath, client);

        return createNewFile(excelFilePath, client);

    }

    @Override
    public void removeClient(int id) {


        Workbook wb = new Workbook();

        wb.loadFromFile(excelFilePath);

        Worksheet sheet = wb.getWorksheets().get(0);

        sheet.deleteRow(id);

        wb.save();

    }

    @Override
    public Client getClientById(int id) {
        return null;
    }

    @Override
    public Client getClientByLastName(String lastName) {

        Client client = readDataFromExcelFile(excelFilePath, lastName);

        return client;
    }

    @Override
    public List<Client> loadAll() {

        List<Client> clientList = new ArrayList<>();
        try {


            FileInputStream inputStream = new FileInputStream(excelFilePath);

            org.apache.poi.ss.usermodel.Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet firstSheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = firstSheet.iterator();
            iterator.hasNext();
            iterator.next();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                    Client client = new Client();
                    client.setId((int) nextRow.getCell(0).getNumericCellValue());
                    client.setEmail(nextRow.getCell(1).getStringCellValue());
                clientFactory(nextRow, client);
                clientList.add(client);

            }

            workbook.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return clientList;
    }

    private void clientFactory(Row nextRow, Client client) {
        client.setPassword(nextRow.getCell(2).getStringCellValue());
        client.setFirstName(nextRow.getCell(3).getStringCellValue());
        client.setLastName(nextRow.getCell(4).getStringCellValue());
        client.setBirthDate(nextRow.getCell(5).getStringCellValue());
        client.setPassportNumber(nextRow.getCell(6).getStringCellValue());
        client.setIssueDate(nextRow.getCell(7).getStringCellValue());
        client.setExpiryDate(nextRow.getCell(8).getStringCellValue());
        client.setPassportPlace(nextRow.getCell(9).getStringCellValue());
    }


    //Helper methods
    private boolean createNewFile(String excelFilePath, Client client) {

        try {

            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet newSheet = workbook.createSheet(sheetName);
            int rowCount = 0;
            Object[][] data = {
                    {"ID","EMAIL","Mot de passe", "PRENOM", "Nom de la famille", "Date de naissance", "Numéro de passeport", "Date d'émission", "Date d'expiration", "Lieu de passeport"},
                    {1, client.getEmail(),client.getPassword(),client.getFirstName(), client.getLastName(), client.getBirthDate(), client.getPassportNumber(), client.getIssueDate(), client.getExpiryDate(), client.getPassportPlace()}};
            insertData(newSheet, data, rowCount);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            System.out.println("File created successfully");

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;

    }

    private void insertData(Sheet newSheet, Object[][] bookComments, int rowCount) {


        for (Object[] aBook : bookComments) {
            Row row = newSheet.createRow(rowCount++);
            ;


            int columnCount = 0;
            Cell cell = row.createCell(columnCount);
            cell.setCellValue(rowCount);

            for (Object field : aBook) {
                cell = row.createCell(columnCount++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }

        }
    }

    private boolean updateFile(String excelFilePath, Client client) {
        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();
            Row row = sheet.getRow(rowCount);
            int idRow = (int) row.getCell(0).getNumericCellValue();
            Object[][] data = {
                    {idRow + 1,client.getEmail(),client.getPassword(), client.getFirstName(), client.getLastName(), client.getBirthDate(), client.getPassportNumber(), client.getIssueDate(), client.getExpiryDate(), client.getPassportPlace()}};

            insertData(sheet, data, rowCount + 1);

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            System.out.println("File updated successfully");

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private  Client readDataFromExcelFile(String excelFilePath, String emailClient) {

        Client client = null;
        try {


            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

            org.apache.poi.ss.usermodel.Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet firstSheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = firstSheet.iterator();
            iterator.hasNext();
            iterator.next();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Cell cell = nextRow.getCell(1);
                String email = cell.getStringCellValue();
                if (email.equalsIgnoreCase(emailClient)) {
                    client = new Client();
                    client.setId((int) nextRow.getCell(0).getNumericCellValue());
                    client.setEmail(email);
                    clientFactory(nextRow, client);
                    break;
                }

            }

            workbook.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return client;
    }


}


