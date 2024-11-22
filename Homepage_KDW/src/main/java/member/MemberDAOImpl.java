package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

public class MemberDAOImpl implements MemberDAO, DBConn {
	private static MemberDAOImpl instance = null;
	
	public MemberDAOImpl() {}
	
	public static MemberDAOImpl getInstance() {
		if(instance == null) {
			instance = new MemberDAOImpl();
		}
		return instance;
	}

	@Override
	public int insert(MemberVO vo) {
	    int result = 0;
	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

	        pstmt.setString(1, vo.getUserid());
	        pstmt.setString(2, vo.getPassword());
	        pstmt.setString(3, vo.getEmail());
	        pstmt.setString(4, vo.getEmailAgree());
	        pstmt.setString(5, String.join(", ", vo.getInterestJoin()));  // 배열을 결합
	        pstmt.setString(6, vo.getPhone());
	        pstmt.setString(7, vo.getIntroduce());

	        result = pstmt.executeUpdate();
	        System.out.println(result + "행 등록");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result; 
	}

	
	@Override
    public MemberVO selectByUserId(String userid) {
        MemberVO member = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection(DBConn.URL, DBConn.USER, DBConn.PASSWORD);
            pstmt = conn.prepareStatement(DBConn.SQL_SELECT_BY_USERID);
            pstmt.setString(1, userid);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                member = new MemberVO();
                member.setUserid(rs.getString(DBConn.COL_USERID));
                member.setPassword(rs.getString(DBConn.COL_PASSWORD));
                member.setEmail(rs.getString(DBConn.COL_EMAIL));
                member.setEmailAgree(rs.getString(DBConn.COL_EMAIL_AGREE));
                member.setInterestJoin(rs.getString(DBConn.COL_INTEREST));
                member.setPhone(rs.getString(DBConn.COL_PHONE));
                member.setIntroduce(rs.getString(DBConn.COL_INTRODUCE));
                member.setStatus(rs.getString(DBConn.COL_STATUS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return member;
    }

    @Override
    public MemberVO selectUserInfo(String userid) {
        return selectByUserId(userid);
    }

    @Override
    public boolean deleteAccount(String userid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
        	conn = DriverManager.getConnection(DBConn.URL, DBConn.USER, DBConn.PASSWORD);
        	pstmt = conn.prepareStatement(DBConn.SQL_DEACTIVATE_USER);
        	pstmt.setString(1, userid);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
				try {
		            if (pstmt != null) pstmt.close();
		            if (conn != null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        }

        return result;
    }
    
    @Override
    public int updateAccount(MemberVO vo) {
        int result = 0;

        try (Connection conn = DriverManager.getConnection(DBConn.URL, DBConn.USER, DBConn.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(DBConn.SQL_UPDATE)) {

            pstmt.setString(1, vo.getPassword());
            pstmt.setString(2, vo.getEmail());
            pstmt.setString(3, vo.getEmailAgree());
            pstmt.setString(4, vo.getPhone());
            pstmt.setString(5, vo.getIntroduce());     
            pstmt.setString(6, vo.getInterestJoin()); 
            pstmt.setString(7, vo.getUserid());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
