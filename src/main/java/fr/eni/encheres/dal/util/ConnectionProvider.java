package fr.eni.encheres.dal.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.eni.encheres.dal.util.ConnectionProvider;

public class ConnectionProvider {
	private static DataSource dataSource;

	static {
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException {
		return ConnectionProvider.dataSource.getConnection();

	}
}
