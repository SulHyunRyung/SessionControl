package member;

public interface DBConn {
	public static final String URL = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";
	
	public static final String TABLE_NAME = "TEST_MEMBER";
	public static final String COL_USERID = "USERID";
	public static final String COL_PASSWORD = "PASSWORD";
	public static final String COL_EMAIL = "EMAIL";
	public static final String COL_EMAIL_AGREE = "EMAIL_AGREE";
	public static final String COL_INTEREST = "INTEREST";
	public static final String COL_PHONE = "PHONE";
	public static final String COL_INTRODUCE = "INTRODUCE";
	public static final String COL_STATUS = "STATUS";
	
	public static final String SQL_INSERT = "INSERT INTO " + TABLE_NAME +
            " (" + COL_USERID + ", " + COL_PASSWORD + ", " + COL_EMAIL + ", " + COL_EMAIL_AGREE + ", " + COL_INTEREST + ", " + COL_PHONE + ", " + COL_INTRODUCE + ", " + COL_STATUS + ") " +
            " VALUES (?, ?, ?, ?, ?, ?, ?, 'active')";
	
	public static final String SQL_SELECT_BY_USERID = "SELECT * FROM " + TABLE_NAME +
            " WHERE " + COL_USERID + " = ?";
	
	public static final String SQL_DEACTIVATE_USER = "UPDATE " + TABLE_NAME + 
            " SET " + COL_STATUS + " = 'inactive' WHERE " + COL_USERID + " = ?";
    
	public static final String SQL_UPDATE = "UPDATE " + TABLE_NAME + " SET " +
		    COL_PASSWORD + " = ?, " +
		    COL_EMAIL + " = ?, " +
		    COL_EMAIL_AGREE + " = ?, " +
		    COL_PHONE + " = ?, " +
		    COL_INTRODUCE + " = ?, " +
		    COL_INTEREST + " = ? " +
		    "WHERE " + COL_USERID + " = ?";
    
}

