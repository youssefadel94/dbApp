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
	/*	PrintWriter pw = new PrintWriter(new File("test.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("id");
		sb.append(',');
		sb.append("Name");
		sb.append('\n');
		sb.append("1");
		sb.append(',');
		sb.append("Prashant Ghimire");
		sb.append('\n');
		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");*/

	}

	private static void writeToCSV() {
		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");
	}
}