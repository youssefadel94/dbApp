import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSV {
	public static String[] columns;
	public static int rowsCount;
	public static String tableNameConst;
	public static String tableColumnsConst;
	public static PrintWriter pw;
	public static StringBuilder sb;
	public static int id;
	public static int pages;
	public static int pageRecords;
	public static int rowLengh;



	public static void createTable(String tableName, String tableColumns)
			throws FileNotFoundException {
		
		tableColumnsConst = tableColumns;//get table name
		columns = tableColumns.split(", ");//get coloumns 
		rowsCount = columns.length;//get count of rows
		pw = new PrintWriter(new File(tableName + ".csv"));//open a new witer
		sb = new StringBuilder();
		for (int i = 0; i < rowsCount; i++) {
			sb.append(columns[i]);sb.append(',');//add rows in the tbale head
		}
		sb.append('\n');
	}//test

	public static void insertRecord(String row) throws FileNotFoundException {
		
		checkIfReachedMaxRecords();//check if reached max record to create a new page
		//TODO Check if record is same as number of fields
		String[] recordColumns = row.split(", ");//get columns to be added
		int rowLengh = recordColumns.length;
		sb.append(id);sb.append(',');
		//if (rowLengh + 1 <= rowsCount) {//TODO take this check out in can add
			if(canAdd(row))
				System.out.print("hey");
		      for (int i = 0; i < rowLengh; i++) {
				sb.append(recordColumns[i]);sb.append(',');//add record
			}
			sb.append('\n');
			id++;//increment id of records
			pageRecords++;//increment page records counter
		}
	

	public static String readFromCSV(){
		//TODO Function read all csv files and generate a sting  
		return tableColumnsConst;
		//TODO brin index load and save for searching
	}
	//TODO brin library 
	public static void update(int id, String record){
		String records = readFromCSV();//see if lookup is by id or name
		//TODO Check if record exist then update it in the string and add it to the csv
	}
	public static boolean canAdd(String row){
		boolean f= false;
		//TODO Check if record can be add and dosn't exceed field numbers
		String[] recordColumns = row.split(",");//get columns to be added
		rowLengh = recordColumns.length;
		if (rowLengh <= rowsCount) {//TODO take this check out in can add
			f=true;
		}
		else{
			System.out.print("cant add a row");
		}
		return f; 
	}
	public static void delete(int id){
		String records = readFromCSV();//see if lookup is by id or name
		//TODO Check if record exist then update it in the string and add it to the csv
	}
	
	private static void checkIfReachedMaxRecords() throws FileNotFoundException {
		int limit = 2;//limit per page
		if (pageRecords == limit) {
			pw.write(sb.toString());
			pw.close();
			createTable(tableNameConst + " " + pages, tableColumnsConst);
			pages++;
			pageRecords = 0;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		id = 0;
		pages = 1;
		tableNameConst = "test";
		createTable(tableNameConst, "id, name, number, blabla");
		insertRecord("test name, ");
		insertRecord("test name, ");
		insertRecord("test name, ");
		insertRecord("test name, ");
		insertRecord("test name, ");
		insertRecord("test kl, ");
		insertRecord("test ll, ");
		insertRecord("test ll,3,hh, ff, h");
		writeToCSV();

	}

	private static void writeToCSV() {
		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");
	}
}