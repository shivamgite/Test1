package simpleDevelopment.development2;

import org.testng.annotations.Test;
public class test3 {
	
	@Test
	public void dbMethod() {
		DBUtil1 util = new DBUtil1("localhost","3306","sql_store","root","123");
		util.printResults(util.ececuteQuarry("select * from customers;"));
		util.getColumnData(util.ececuteQuarry("select * from customers;"), "first_name");
		
	}

}
