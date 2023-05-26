package member.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {   //  싱글톤 패턴 
	private static Connection con;
	//1. 외부에서 접근할 수 없는 기본생성자 작성
	
	private DBConn() {}   // -> new DBConn 할 수 없다. 객체 생성 안된다.
	
	//2. Connection 객체가 null인 경우에만 객체를 생성하고 반환하는 공유 메서드
	//   getConnection()작성	(접근 제한 X)
	
	public static Connection getConnection()  {    // Static 한가지를 만들고 계속 공유해서 쓰려고
		String driver = "oracle.jdbc.OracleDriver";
		String url  = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "dEv ";
		String password = "1111";

		if(con == null) {
				try {
					Class.forName(driver); 
					con = DriverManager.getConnection(url, username, password);
				} catch (SQLException e) {	
					e.printStackTrace();
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
		}
          return con;
	} // getconnection end
	
	//3. Statement,connection를 매개변수로 받아서 닫는 메서드 close() 작성 (접근 제한 X)
	public static void close(Statement stmt)   {
		
			// stmt 및 con 객체가 널이 아닌 경우에만 각각 닫기 처리
					try {
						if(stmt != null) stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			
	} // close end
	
	//4. preparedStatement 객체를 매개변수로 받아서 닫는 메서드 close() 작성 (접근 제한 X)
	public static void close (PreparedStatement psmt)  {
	
			// stmt 및 con 객체가 널이 아닌 경우에만 각각 닫기 처리
					try {
						if(psmt != null) psmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
		}	 // close end										// 메서드를 실행하기 위한 특정 데이터를 받기 위해
	 public static void close (ResultSet rs,PreparedStatement psmt) { //매개변수로 ResultSet 과 PreparedStatement 를 받아서
		 try {
				if(rs != null) rs.close();  // 실행한 순서의 역순으로 닫아야 한다.
				if(psmt != null)psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 }
	 public static void close() {   // 매개변수 없이 
		 try {
				if(con != null) con.close();   //  Connection이 null일때 닫는다.
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 }
	 

} // class end
