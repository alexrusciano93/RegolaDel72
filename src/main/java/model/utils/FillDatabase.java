package model.utils;

import model.calciatore.Calciatore;
import model.calciatore.CalciatoreDAO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;

public class FillDatabase {
    public FillDatabase(){ }

    public void generateCalciatori(int scheda) throws IOException, SQLException {
        CalciatoreDAO calDAO=new CalciatoreDAO();
        File file=new File("C:\\Users\\arusc\\OneDrive\\Desktop\\RegolaDel72\\src\\main\\resources\\Quotazioni_Fantacalcio.xlsx");
        FileInputStream fileStream=new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
        XSSFSheet sheet = workbook.getSheetAt(scheda);
        Iterator<Row> rowIterator = sheet.iterator();
        int riga = 0, colonna = 0;
        while (rowIterator.hasNext()) {
            riga++; colonna = 0;
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Calciatore c = new Calciatore();
            c.setScelto(false);
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (riga > 2 && colonna <= 4) {
                    switch (colonna) {
                        case 0:
                            c.setCod((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            c.setRuolo(cell.getStringCellValue());
                            break;
                        case 2:
                            c.setNome(cell.getStringCellValue());
                            break;
                        case 3:
                            c.setSquadra(cell.getStringCellValue());
                            break;
                        case 4:
                            c.setQuotazione((int) cell.getNumericCellValue());
                            break;
                    }
                }
                colonna++;
            }
            if (c.getNome()!=null) {
                calDAO.addCalciatore(c);
            }
        }
        fileStream.close();
    }
}
