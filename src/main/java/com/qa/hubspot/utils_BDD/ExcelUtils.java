package com.qa.hubspot.utils_BDD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	private static Workbook workbook;
	private static Sheet sheet;
	
	//External Workbook File directory path
	
	public static String TEST_DATA_WORKBOOK_PATH=".\\src\\main\\java\\com\\qa\\hubspot\\TestData\\Demo_Framework_Test_Data.xls";
	// "."----represents the Root Directory
	
	//Utility Method 1
	
	//Utility-----Provide any Sheet Name of the Workbook as an input parameter and get the corresponding test data of that particular sheet.
	
	//e.g---Get Contacts Test Data from Sheet Name "Contacts" of Workbook mentioned in the path.
	
	//There can be multiple sheets within a workbook
	
	public static Object[][] getTestData(String SheetName)
	{
		Object data[][]=null; //2-D array declared and initialized with default value
		
		try 
		{
			FileInputStream FIP=new FileInputStream(TEST_DATA_WORKBOOK_PATH);
			
			try 
			{
				workbook=WorkbookFactory.create(FIP);   
				
				//Now Workbook object is created
				
				sheet=workbook.getSheet(SheetName);
				
	//Workbook contains various sheets and have to provide the particular sheet name from where we want to get the data
				
				
				data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; 
				
				//2-D Array Object Created
				
				
				//Now read and store the values from the sheet to the 2-D Array
				
				//Hence 2-D Array is the final place where the data is stored from external Workbook file
				
				for(int row=0;row<sheet.getLastRowNum();row++)
				{
					for(int column=0;column<sheet.getRow(0).getLastCellNum();column++)
					{
						data[row][column]=sheet.getRow(row+1).getCell(column).toString();
					}
				}
			} 
			catch (EncryptedDocumentException | IOException e) 
			{
				
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
		}
		
		return data;
		
		
		
		
	}
	
	
	
	
	

}
