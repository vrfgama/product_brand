package main;

import java.sql.Connection;

import connection.ConnectionDB;
import control.Control;

public class Main {
	
	public static void main(String[] args) {
		Control.run();
		
		testaConexaoBD();
	}
	
	
	// SXSSFWorkbook
	
	public static void testaConexaoBD() {
		ConnectionDB.startConnection();
		Connection connection= ConnectionDB.getConnection();
		System.out.println("obj connection "+connection);
		
		System.out.println("status "+ConnectionDB.statusConnection());
		ConnectionDB.restartConnection();
		System.out.println("status após reiniciar "+ConnectionDB.statusConnection());
		ConnectionDB.closeConnection();
		System.out.println("status após fechar "+ConnectionDB.statusConnection());
		System.out.println("status "+ConnectionDB.statusConnection());
	}
}