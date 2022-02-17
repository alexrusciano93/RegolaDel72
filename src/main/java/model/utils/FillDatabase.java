package model.utils;

import model.calciatore.Calciatore;
import model.calciatore.CalciatoreDAO;
import model.voto.Voto;
import model.voto.VotoDAO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;

public class FillDatabase {
    public FillDatabase(){ }

    public void generateVoti(int i) throws IOException, SQLException {
        VotoDAO votoDAO=new VotoDAO();
        CalciatoreDAO calDAO=new CalciatoreDAO();
        File file=new File("C:\\Users\\arusc\\OneDrive\\Desktop\\DATASET TESI\\voti anno 21-22\\VotiGiornata"+i+".xlsx");
        FileInputStream fileStream=new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        int riga = 0, colonna = 0;
        while (rowIterator.hasNext()) {
            riga++;
            colonna = 0;
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Calciatore c = new Calciatore();
            Voto v=new Voto();
            v.setnGiornata(i);
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (colonna ==0 && cell.getCellType()==0) {
                    c.setCod((int) cell.getNumericCellValue());
                    c=calDAO.doRetrieveByCod(c.getCod());
                    if (c!=null)
                        v.setCalciatore(c);
                }
                if (colonna ==15 && cell.getCellType()==2) {
                    CellValue x=evaluator.evaluate(cell);
                    v.setVoto(x.getNumberValue());
                }
                colonna++;
            }
            if (c.getQuotazione()>0)
                votoDAO.addVoto(c,v);
        }
        fileStream.close();
    }

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
            c.setMedia(0.0);
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
