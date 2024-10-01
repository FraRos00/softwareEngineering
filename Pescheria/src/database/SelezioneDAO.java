package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.EntitySelezione;
import exception.DAOException;
import exception.DBConnectionException;

public class SelezioneDAO {

		public static void create(EntitySelezione selezione, int id) throws DAOException, DBConnectionException {
		
			//segue sempre la creazione di un acquisto
			try {
				Connection conn = DBManager.getConnection();
				String query = "INSERT INTO SELEZIONI VALUES(?,?,?)";
				try {
					PreparedStatement stmt2 = conn.prepareStatement(query);
					stmt2.setFloat(1, selezione.getQuantita());
					stmt2.setInt(2,id);
					stmt2.setString(3,selezione.getAlimento().getCodice());
					stmt2.executeUpdate();
					
				} 
				catch (SQLException e){
					throw new DAOException("Errore creazione selezione");
				}
				
			} catch (SQLException e) {
				throw new DBConnectionException("Errore di connessione al database");		
			}
			
		}
	
}
