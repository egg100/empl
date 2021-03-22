package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InputDAO {
	static Connection conn = null;
	
	public static void insertBoard(InputBoardVO bo) {
		PreparedStatement pstmt = null;
		String sql = "insert into input_board values"
				+ "((select nvl(max(board_no),0)+1 from input_board),?,?,?,?,?)";
		conn = DBCon.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo.getTitle());
			pstmt.setString(2, bo.getPasswd());
			pstmt.setString(3, bo.getPublicity());
			pstmt.setString(4, bo.getExitDate());
			pstmt.setString(5, bo.getContents());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건 입력됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	} // end of insertBoard()
}
