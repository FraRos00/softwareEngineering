package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.DAOException;
import exception.DBConnectionException;

public class MixCrudiDAO {

	public static float readPrezzo(String codice) throws DAOException, DBConnectionException {
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT prezzo FROM MIXCRUDI WHERE CODICE = ? ;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, codice);
				ResultSet res = stmt.executeQuery();
				res.next();
				return res.getFloat(1);		
			} 
			catch (SQLException e){
				throw new DAOException("Errore read MixCrudi");
			}
			
		} catch (SQLException e) {
			throw new DBConnectionException("Errore di connessione al database");	
		}
	}

}
