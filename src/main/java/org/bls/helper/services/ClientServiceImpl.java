package org.bls.helper.services;


import org.bls.helper.dao.ClientRepository;
import org.bls.helper.entities.Client;
import org.bls.helper.entities.BLSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service
@Transactional
public class ClientServiceImpl implements IClientService {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private IUserService userService;
    @Override
    public Client addClient(Client client) {


      Client clientSaved=  clientRepository.save(client);

        return clientSaved;

    }

    @Override
    public void removeClient(Long id) {

          clientRepository.deleteById(id);

    }

    @Override
    public List<Client> getClientByUserId(Long id) {

        BLSUser user=userService.getUserById(id);
        if(user==null) return null;

       List<Client> clients= clientRepository.getByUser(user);
        if(CollectionUtils.isEmpty(clients)) return null;

        return clients;
    }

    @Override
    public List<Client> getClientByUserEmail(String email) {

        BLSUser user=userService.getUserByEmail(email);
        if(user==null) return null;

        List<Client> clients= clientRepository.getByUser(user);
        if(CollectionUtils.isEmpty(clients)) return null;

        return clients;
    }

    @Override
    public Client getClientByLastName(String lastName) {
        return null;
    }


    @Override
    public Client getClientByEmail(String email) {

        Client client = clientRepository.getByEmail(email);

        return client;
    }

    @Override
    public List<Client> loadAll() {

        List<Client> clientList = clientRepository.findAll();

        if(CollectionUtils.isEmpty(clientList)) return null;

        return clientList;
    }




    //Helper methods
//    private boolean createNewFile(String excelFilePath, Client client) {
//
//        try {
//
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            Sheet newSheet = workbook.createSheet(sheetName);
//            int rowCount = 0;
//            Object[][] data = {
//                    {"ID","EMAIL","Mot de passe", "PRENOM", "Nom de la famille", "Date de naissance", "Numéro de passeport", "Date d'émission", "Date d'expiration", "Lieu de passeport"},
//                    {1, client.getEmail(),client.getPassword(),client.getFirstName(), client.getLastName(), client.getBirthDate(), client.getPassportNumber(), client.getIssueDate(), client.getExpiryDate(), client.getPassportPlace()}};
//            insertData(newSheet, data, rowCount);
//
//            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//            workbook.write(outputStream);
//            workbook.close();
//            outputStream.close();
//            System.out.println("File created successfully");
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return false;
//        }
//        return true;
//
//    }

//    private void insertData(Sheet newSheet, Object[][] bookComments, int rowCount) {
//
//
//        for (Object[] aBook : bookComments) {
//            Row row = newSheet.createRow(rowCount++);
//            ;
//
//
//            int columnCount = 0;
//            Cell cell = row.createCell(columnCount);
//            cell.setCellValue(rowCount);
//
//            for (Object field : aBook) {
//                cell = row.createCell(columnCount++);
//                if (field instanceof String) {
//                    cell.setCellValue((String) field);
//                } else if (field instanceof Integer) {
//                    cell.setCellValue((Integer) field);
//                }
//            }
//
//        }
//    }

//    private boolean updateFile(String excelFilePath, Client client) {
//        try {
//            FileInputStream inputStream = new FileInputStream(excelFilePath);
//            org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(inputStream);
//
//            Sheet sheet = workbook.getSheetAt(0);
//
//            int rowCount = sheet.getLastRowNum();
//            Row row = sheet.getRow(rowCount);
//            int idRow = (int) row.getCell(0).getNumericCellValue();
//            Object[][] data = {
//                    {idRow + 1,client.getEmail(),client.getPassword(), client.getFirstName(), client.getLastName(), client.getBirthDate(), client.getPassportNumber(), client.getIssueDate(), client.getExpiryDate(), client.getPassportPlace()}};
//
//            insertData(sheet, data, rowCount + 1);
//
//            inputStream.close();
//
//            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//            workbook.write(outputStream);
//            workbook.close();
//            outputStream.close();
//            System.out.println("File updated successfully");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
//        }
//        return true;
//    }

//    private  Client readDataFromExcelFile(String excelFilePath, String emailClient) {
//
//        Client client = null;
//        try {
//
//
//            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
//
//            org.apache.poi.ss.usermodel.Workbook workbook = new XSSFWorkbook(inputStream);
//
//            Sheet firstSheet = workbook.getSheetAt(0);
//
//            Iterator<Row> iterator = firstSheet.iterator();
//            iterator.hasNext();
//            iterator.next();
//
//            while (iterator.hasNext()) {
//                Row nextRow = iterator.next();
//                Cell cell = nextRow.getCell(1);
//                String email = cell.getStringCellValue();
//                if (email.equalsIgnoreCase(emailClient)) {
//                    client = new Client();
//                    client.setId((int) nextRow.getCell(0).getNumericCellValue());
//                    client.setEmail(email);
//                    clientFactory(nextRow, client);
//                    break;
//                }
//
//            }
//
//            workbook.close();
//            inputStream.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return client;
//    }


}


