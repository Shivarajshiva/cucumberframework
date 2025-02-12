package dataDriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

public class DataDrivenTest {

//	"C:\Users\Shivaraj\Desktop\testdata.xlsx"

//Identify testcase column by scanning the entire 1st row
//	one column is identified then scan the entire testcase column to identify the cart testcase row
//	after u grab the cart row= pull all the data of that row and feed into test

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\Shivaraj\\Desktop\\testdata.xlsx");
		XSSFWorkbookFactory workbook = new XSSFWorkbookFactory();
		XSSFWorkbook book = workbook.create(fis);
		int sheets = book.getNumberOfSheets();
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < sheets; i++) {
			if (book.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = book.getSheetAt(i);

				// Identify testcase column by scanning the entire 1st row
				Iterator<Row> row = sheet.rowIterator();
				Row firstrow = row.next();
				Iterator<Cell> cell = firstrow.cellIterator();
				int k = 0;
				int column = 0;
				while (cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equalsIgnoreCase("testcase")) {
						column = k;
					}
					k++;
				}
				System.out.println(column);
//				one column is identified then scan the entire testcase column to identify the cart testcase row
				while (row.hasNext()) {
					Row r = row.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase("cart")) {
						Iterator<Cell> cellvalue = r.cellIterator();
						while (cellvalue.hasNext()) {
							Cell ce = cellvalue.next();
							if (ce.getCellType() == CellType.STRING) {
								 String stringvalue = ce.getStringCellValue();
								System.out.println(stringvalue);
								list.add(stringvalue);
							}else {
								double numvalue = ce.getNumericCellValue();
								System.out.println(numvalue);
								String stringValue = NumberToTextConverter.toText(numvalue);
								System.out.println(stringValue);
								list.add(stringValue);
							}
							
						}
					}
				}

			}
		}
	}
}
