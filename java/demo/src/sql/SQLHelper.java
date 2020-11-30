package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLHelper {

	private static SQLHelper instance;

	public static SQLHelper getInstance() {
		if (instance == null) {
			instance = new SQLHelper();
		}
		return instance;
	}

	private Connection connection;

	private Statement statement;

	private ResultSet resultSet;

	private String dbPath = "../../tool.db";

	private SQLHelper() {

	}

	private Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("连接数据库失败！ type:"+ e.getClass().getName() + ", error:" + e.getMessage());
			}
		}
		return connection;
	}

	public ResultSet execQuery(String sql) {
		try {
			resultSet = getStatement().executeQuery(sql);
			return resultSet;
		} catch (Exception e) {
			System.out.println("ִ执行sql失败！sql:" + sql + ",type:" + e.getClass().getName() + ", error:" + e.getMessage());
			return null;
		}
	}

	private Statement getStatement() throws SQLException, ClassNotFoundException {
		if (null == statement) {
			statement = getConnection().createStatement();
		}
		return statement;
	}

	/**
	 * 数据库资源关闭和释放
	 */
	public void destroyed() {
		try {
			if (null != statement) {
				statement.close();
				statement = null;
			}

			if (null != resultSet) {
				resultSet.close();
				resultSet = null;
			}

			if (null != connection) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getIntValue(ResultSet resultSet, String column) throws NumberFormatException, SQLException {
		String str = resultSet.getString(column);
		return Integer.parseInt(str);
	}

	public static String getStringValue(ResultSet resultSet, String column) throws SQLException {
		return resultSet.getString(column);
	}
	
	public static boolean getBooleanValue(ResultSet resultSet, String column) throws SQLException {
		return resultSet.getString(column).equals("1");
	}
}
