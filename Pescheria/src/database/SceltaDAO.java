package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityAcquisto;
import entity.EntityScelta;
import exception.DAOException;
import exception.DBConnectionException;

public class SceltaDAO {

	public static void create(EntityScelta scelta, int id) throws DAOException, DBConnectionException {
		
		//segue sempre la creazione di un acquisto
		try {
			Connection conn = DBManager.getConnection();
			String query = "INSERT INTO SCELTE VALUES(?,?,?)";
			try {
				PreparedStatement stmt2 = conn.prepareStatement(query);
				stmt2.setInt(1, scelta.getQuantita());
				stmt2.setInt(2,id);
				stmt2.setString(3,scelta.getMix().getCodice());
				stmt2.executeUpdate();
				
			} 
			catch (SQLException e){
				throw new DAOException("Errore creazione scelta");
			}
			
		} catch (SQLException e) {
			throw new DBConnectionException("Errore di connessione al database");		
		}
		
	}
	
}
