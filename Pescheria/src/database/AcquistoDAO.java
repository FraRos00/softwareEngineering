package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityAcquisto;
import exception.DAOException;
import exception.DBConnectionException;

public class AcquistoDAO {

	public static void create(EntityAcquisto acquisto) throws DAOException, DBConnectionException {
		int id;
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT MAX(ID) FROM ACQUISTI";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet res = stmt.executeQuery();
				res.next();
				id = res.getInt(1);			
				query = "INSERT INTO ACQUISTI VALUES(?,?,?,?)";
				PreparedStatement stmt2 = conn.prepareStatement(query);
				if(res.wasNull())
				{					
					
					stmt2.setInt(1, 1);
				}
				else
				{				
					
					stmt2.setInt(1,res.getInt(1)+1);
				}
				stmt2.setFloat(2,acquisto.getPrezzoC());
				java.sql.Date sqlDate = new java.sql.Date(acquisto.getData().getTime());
				stmt2.setDate(3, sqlDate);			
				stmt2.setString(4, acquisto.getCliente().getNomeUtente());			
				stmt2.executeUpdate();
				
			} 
			catch (SQLException e){
				throw new DAOException("Errore creazione acquisto");
			}
			
		} catch (SQLException e) {
			throw new DBConnectionException("Errore di connessione al database");		
		}
		
	}
	
	public static int readLastID() throws DBConnectionException, DAOException
	{
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT MAX(ID) FROM ACQUISTI ";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);				
				ResultSet res = stmt.executeQuery();
				res.next();
				return res.getInt(1);				
			} 
			catch (SQLException e){
				throw new DAOException("Errore creazione scelta");
			}		
		} catch (SQLException e) {
			throw new DBConnectionException("Errore di connessione al database");
		}
	}

}
