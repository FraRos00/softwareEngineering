package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.EntityAlimento;
import entity.EntityComposizione;
import exception.DAOException;
import exception.DBConnectionException;

public class ComposizioneDAO {

	public static ArrayList<EntityComposizione> readComposizione(String codice) throws DAOException, DBConnectionException {
		ArrayList<EntityComposizione> composizioneMix = new ArrayList<EntityComposizione>();
		EntityComposizione compTemp;
		EntityAlimento alTemp;
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT * FROM COMPOSIZIONI WHERE MIXCRUDI = ? ;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, codice);
				ResultSet res = stmt.executeQuery();
				
				while(res.next())
				{			
					compTemp = new EntityComposizione();
					compTemp.setQuantita(res.getFloat(1));
					alTemp= new EntityAlimento();
					alTemp.setCodice(res.getString(2));
					compTemp.setAlimento(alTemp);
					composizioneMix.add(compTemp);
				}
			} 
			catch (SQLException e){
				throw new DAOException("Errore Alimento, read alimento");
			}
			
		} catch (SQLException e) {
			throw new DBConnectionException("Errore di connessione al database");		
		}
		
		return composizioneMix;
	}

}
