package simpleDevelopment.development2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil1 {
	private String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";

	private String HOST_NAME;
	private String PORT_NUM;
	private String DATABASE_NAME;
	private String USER_NAME;
	private String PASSWORD;
	private String DATABASE_URL = null;
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private int columnsNumber;
	private ArrayList<String> columnNames = null;
	private ArrayList<String> row = null;
	private List<ArrayList<String>> myData = null;

	public DBUtil1(String HOST_NAME, String PORT_NUM, String DATABASE_NAME, String USER_NAME, String PASSWORD) {
		this.HOST_NAME = HOST_NAME;
		this.PORT_NUM = PORT_NUM;
		this.DATABASE_NAME = DATABASE_NAME;
		this.USER_NAME = USER_NAME;
		this.PASSWORD = PASSWORD;
		DATABASE_URL = "jdbc:mysql://" + this.HOST_NAME + ":" + this.PORT_NUM + "/" + this.DATABASE_NAME;
	}

	public List<ArrayList<String>> ececuteQuarry(String qurry) {
		try {
			Class.forName(DATABASE_DRIVER);
			conn = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(qurry);
			rsmd = rs.getMetaData();
			columnsNumber = rsmd.getColumnCount();

			columnNames = new ArrayList<String>();
			myData = new ArrayList<ArrayList<String>>();

			for (int i = 1; i <= columnsNumber; i++) {
				if (i >= 1)
					columnNames.add(rsmd.getColumnName(i));
			}
			myData.add(columnNames);

			while (rs.next()) {
				row = new ArrayList<String>();
				for (int i = 1; i <= columnsNumber; i++) {
					if (i >= 1)
						row.add(rs.getString(i));
				}
				myData.add(row);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myData;
	}

	public void printResults(List<ArrayList<String>> results) {
		for (ArrayList<String> eachRow : results) {
			int changeRow = 0;
			for (String str : eachRow) {
				changeRow++;
				System.out.print(str + "  ||  ");
				if (eachRow.size() == changeRow) {
					System.out.println();
				}
			}

		}

	}
	
	public void getColumnData(List<ArrayList<String>> results, String clm_name) {
		int clmNumber =0;
		ArrayList<String> clmList=   results.get(0);
		for(int i=0;i<=clmList.size();i++) {
			
			if(clmList.get(i).trim().equalsIgnoreCase(clm_name.trim())){
				clmNumber = i;
				break;
			}
		}
		
		for(ArrayList<String> eachRow : results) {
			for (int i=1;i<eachRow.size();i++) {
				System.out.println("||   "+eachRow.get(clmNumber)+"    ||");
				break;
			}
		}
		
		

}
}
