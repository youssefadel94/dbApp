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



	public static void createTable(String tableName, String tableColumns)
			throws FileNotFoundException {
		
		tableColumnsConst = tableColumns;
		columns = tableColumns.split(", ");
		rowsCount = columns.length;
		pw = new PrintWriter(new File(tableName + ".csv"));
		sb = new StringBuilder();
		for (int i = 0; i < rowsCount; i++) {
			sb.append(columns[i]);sb.append(',');
		}
		sb.append('\n');
	}

	public static void insertRecord(String row) throws FileNotFoundException {
		
		checkIfReachedMaxRecords();
//TODO Check if record is same as number of fields
		String[] recordColumns = row.split(", ");
		int rowLengh = recordColumns.length;
		sb.append(id);sb.append(',');
		if (rowLengh + 1 <= rowsCount) {
			for (int i = 0; i < rowLengh; i++) {
				sb.append(recordColumns[i]);sb.append(',');
			}
			sb.append('\n');
			id++;
			pageRecords++;
		}
	}

	public static String readFromCSV(){
		//TODO Function read all csv files and generate a sting  
		return tableColumnsConst;
		
	}
	//TODO brin library 
	public static void update(int id, String record){
		String records = readFromCSV();//see if lookup is by id or name
		//TODO Check if record exist then update it in the string and add it to the csv
	}
	public static void canAdd(String record){
		//TODO Check if record can be add and dosnt excede field numbers 
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
		writeToCSV();

	}

	private static void writeToCSV() {
		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");
	}
}