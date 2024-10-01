package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class AlimentoDAO {
	public static float readQuantita(String codice) throws DAOException,DBConnectionException
	{
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT quantita FROM ALIMENTI WHERE CODICE = ? ;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, codice);
				ResultSet res = stmt.executeQuery();
				res.next();
				return res.getFloat(1);
			} 
			catch (SQLException e){
				throw new DAOException("Errore Alimento, read alimento");
			}
			
		} catch (SQLException e) {
			throw new DBConnectionException("Errore di connessione al database");		
		}
	}
	
	public static float readPrezzo(String codice) throws DAOException, DBConnectionException
	{
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT prezzo FROM ALIMENTI WHERE CODICE = ? ;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, codice);
				ResultSet res = stmt.executeQuery();
				res.next();
				return res.getFloat(1);
			} 
			catch (SQLException e){
				throw new DAOException("Errore Alimento, read alimento");
			}
			
		} catch (SQLException e) {
			throw new DBConnectionException("Errore di connessione al database");	
		}
	}

	public static void updateQuantita(String codiceProdotto, float newQ) throws DBConnectionException, DAOException {
		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE ALIMENTI SET QUANTITA = ? WHERE CODICE = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(2, codiceProdotto);
				stmt.setFloat(1, newQ);				
				int res = stmt.executeUpdate();
				if(res <1)
				{
					throw new DAOException("Erroreeee update alimento");
				}
				
			} 
			catch (SQLException e){
				throw new DAOException("Errore update alimento");
			}
			
		} catch (SQLException e) {
			throw new DBConnectionException("Errore di connessione al database");
		}
		
		
	}
}
