package com.qa.api.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	public static Workbook book;
	public static Sheet sheet;

	public static String TESTDATA_SHEET_PATH="./src/main/java/com/qa/api/gorest/testdata/GoRestTestData.xlsx";
	
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream ip;
		
		try {
			ip = new FileInputStream(TESTDATA_SHEET_PATH);			
			book=	WorkbookFactory.create(ip);
			sheet=book.getSheet(sheetName);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		Object data[][]=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
//		
//		for(int i=0;i<sheet.getLastRowNum();i++) {
//			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++) {
//				data[i][k]=sheet.getRow(i+1).getCell(k).toString();
//			}
//		}
		
		Object[][] data=new Object[sheet.getLastRowNum()][1];		
		Hashtable<String, String> table=null;		
		for(int i=0;i<sheet.getLastRowNum();i++) {
			table=new Hashtable<String,String>();
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				table.put(sheet.getRow(0).getCell(j).toString(),sheet.getRow(i+1).getCell(j).toString());
				data[i][0]=table;
			}
		}
		
		return data;
	}
	
}
