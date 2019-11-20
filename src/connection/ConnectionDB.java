package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionDB {

	static Connection connection = null;

	
	public static void startConnection() {

		try {

			// loading JDBC Driver
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);

			// sets up connection with database
			String serverName = "localhost"; // caminho do servidor do BD
			String mydatabase = "supermercado_now"; // nome do seu banco de dados
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String username = "root"; // nome de um usuário de seu BD
			String password = ""; // sua senha de acesso

			connection = DriverManager.getConnection(url, username, password);


		} catch (ClassNotFoundException e) { 

			JOptionPane.showMessageDialog(null, "O driver expecificado nao foi encontrado.");

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados.");

		}

	}
	
	// return connection
	public static Connection getConnection() {
		return connection;
	}


	// return status connection
	public static boolean statusConnection() {
		if (getConnection() != null) return true;
		else return false;
	}

	// close connection 
	public static boolean closeConnection() {

		try {
			//getConnection().close();
			connection.close();
			connection= null;
			return true;

		} catch (SQLException e) {
			return false;
		}

	}


	// restart connection
	public static void restartConnection() {
		
		closeConnection();
		startConnection();
	}

}
