package control;

import java.util.ArrayList;
import java.util.Iterator;

import database.AcquistoDAO;
import database.AlimentoDAO;
import database.ComposizioneDAO;
import database.MixCrudiDAO;
import database.SceltaDAO;
import database.SelezioneDAO;
import entity.EntityAcquisto;
import entity.EntityAlimento;
import entity.EntityClienteRegistrato;
import entity.EntityComposizione;
import entity.EntityMixCrudi;
import entity.EntityScelta;
import entity.EntitySelezione;
import exception.DAOException;
import exception.DBConnectionException;
import exception.InputException;
import exception.OperationException;

public class GestionePescheriaController {
	
	//IMPLEMENTO UN SINGLETON
	private static GestionePescheriaController gP = null;
	
	protected GestionePescheriaController() {}
	
	public static GestionePescheriaController getIstance()
	{
		if(gP == null)
		{
			gP = new GestionePescheriaController();
		}
		return gP;
	}
	//METODI PRIVATI
	private float verificaDisponibilitaAlimento(String codiceAlimento, float qta) throws DAOException, DBConnectionException, OperationException  {
		float quantita;
		try {
			quantita = AlimentoDAO.readQuantita(codiceAlimento);
			//System.out.println(quantita);
			if(qta<=quantita) //disponibile
			{
				return quantita;
			}
			else
			{
				throw new OperationException("La quantita' selezionata non e' disponibile");
			}
		} 	catch (DAOException e) {
			throw new DAOException("Impossibile accedere all' alimento nel database");
		} catch (DBConnectionException e) {
			throw new DBConnectionException("Impossibile connettersi al database");
		}	
	}
	
	private ArrayList<EntityComposizione> verificaDisponibilitaMix(String codice, int qtaSel) throws DAOException, DBConnectionException, OperationException 
	{
		float quantita;
		ArrayList<EntityComposizione> composizioneMix = new ArrayList<EntityComposizione>(); 
		try {
			composizioneMix = ComposizioneDAO.readComposizione(codice);
			for(Iterator<EntityComposizione> i = composizioneMix.iterator(); i.hasNext();)
			{
				EntityComposizione temp = i.next();
				quantita = AlimentoDAO.readQuantita(temp.getAlimento().getCodice());
				if(qtaSel*temp.getQuantita() > quantita) //se la quantita di mix richiesta moltiplicata la quantita di alimento presente nel mix> qta disp
				{
					throw new OperationException("La quantita' selezionata non è disponibile");	
				}
				
			}
			return composizioneMix;
			
		} catch (DAOException e) {
			throw new DAOException("Impossibile accedere agli alimenti nel database");
		} catch (DBConnectionException e) {
			throw new DBConnectionException("Impossibile connettersi al database");
		}	
		
		
	}
	
	
	//METODI PUBBLICI
	public EntityAcquisto createAcquisto(EntityClienteRegistrato cliente)
	{
		return new EntityAcquisto(cliente);
	}
	
	public void acquistaProdotto(String codiceProdotto,float quantitaSelezionata,ArrayList<EntityAlimento> alimentiAcquistati,EntityAcquisto acquisto, ArrayList<EntitySelezione> alimentiSelezionati, ArrayList<EntityScelta> mixScelti) throws InputException, DAOException, DBConnectionException, OperationException
	{
		float quantita;
		int quantitaMix;
		float prezzo;
		ArrayList<EntityComposizione> composizioneMix = new ArrayList<EntityComposizione>();
		
		if(EntityAlimento.checkCodice(codiceProdotto))
		{
			//Se il prodotto è un alimento verifica la sua disponibilità
			try {
				quantita = verificaDisponibilitaAlimento(codiceProdotto,quantitaSelezionata);
			} catch (DAOException e) {
				throw new DAOException(e.getMessage());
			} catch (DBConnectionException e) {
				throw new DBConnectionException(e.getMessage());
			} 
					
			//aggiorna quantita disponibile nel database
			try {
				AlimentoDAO.updateQuantita(codiceProdotto,quantita-quantitaSelezionata);
			} catch (DAOException e) {
				throw new DAOException(e.getMessage());
			} catch (DBConnectionException e) {
				throw new DBConnectionException(e.getMessage());
			}
			
			
			//aggiungi prezzo all'acquisto
			try {
				prezzo = AlimentoDAO.readPrezzo(codiceProdotto);
				acquisto.addToPrezzoC(prezzo*quantitaSelezionata);
			} catch (DAOException e) {
				throw new DAOException("Impossibile accedere agli alimenti nel database");
			} catch (DBConnectionException e) {
				throw new DBConnectionException("Impossibile connettersi al database");
			}
			//aggiungi l'alimento alla lista degli alimenti acquistati (per eventuale ripristino db)
			EntityAlimento al = new EntityAlimento(codiceProdotto,quantitaSelezionata,prezzo);
			alimentiAcquistati.add(al);
			//aggiungi l'alimento alla lista degli alimenti selezionati per registrare l'acquisto al termine dell'operazione (per SelezioneDAO.create)
			EntitySelezione sel = new EntitySelezione(quantitaSelezionata,acquisto,al);
			alimentiSelezionati.add(sel);
		}
		else
		{
			if(EntityMixCrudi.checkCodice(codiceProdotto))
			{
				//se il prodotto è un mix
				//verifica la disponibilita del mix e ottieni gli alimenti che compongono il mix e le loro quantita
				try {
					composizioneMix = verificaDisponibilitaMix(codiceProdotto,(int)quantitaSelezionata);
					
				} catch (DAOException e) {
					throw new DAOException(e.getMessage());
				} catch (DBConnectionException e) {
					throw new DBConnectionException(e.getMessage());
				} catch(OperationException e)
				{
					throw new OperationException(e.getMessage());
				}
				//aggiorna le quantita disponibili nel database
				quantitaMix = (int)quantitaSelezionata; //si possono richiedere solo interi di mix, es no 0.5 mix
				for(Iterator<EntityComposizione> i = composizioneMix.iterator(); i.hasNext();)
				{
					EntityComposizione temp = i.next();
					String codiceAlimentoMix = new String(temp.getAlimento().getCodice());
					float quantitaComposizione = temp.getQuantita();
					try {
						quantita = AlimentoDAO.readQuantita(codiceAlimentoMix);
						AlimentoDAO.updateQuantita(codiceAlimentoMix, quantita-(quantitaMix*quantitaComposizione ));
					} catch (DAOException e) {
						throw new DAOException(e.getMessage());
					} catch (DBConnectionException e) {
						throw new DBConnectionException(e.getMessage());
					}
					
					//aggiungi l'alimento alla lista degli alimenti acquistati (per eventuale ripristino db)
					EntityAlimento al = new EntityAlimento(codiceAlimentoMix,quantitaMix*quantitaComposizione );
					alimentiAcquistati.add(al);
					
				}
				
				
				//aggiungi prezzo all'acquisto
				try {
					prezzo = MixCrudiDAO.readPrezzo(codiceProdotto);
					acquisto.addToPrezzoC(prezzo*quantitaMix);
				} catch (DAOException e) {
					throw new DAOException("Impossibile al mix nel database");
				} catch (DBConnectionException e) {
					throw new DBConnectionException("Impossibile connettersi al database");
				}
				//aggiungi il mix alla lista dei mix scelti per registrare l'acquisto al termine dell'operazione (per SceltaDAO.create)
				EntityMixCrudi mixTemp = new EntityMixCrudi(prezzo,codiceProdotto,composizioneMix);
				EntityScelta scel = new EntityScelta((int)quantitaSelezionata,acquisto,mixTemp);
				mixScelti.add(scel);			
			}
			
			else
			{
				throw new InputException("Codice prodotto non valido");
			}
		}
		
	}

	public void annullaAcquisto(ArrayList<EntityAlimento> alimentiAcquistati) throws DAOException, DBConnectionException {
		EntityAlimento temp;
		float quantita;
		for(Iterator<EntityAlimento> i = alimentiAcquistati.iterator(); i.hasNext();)
		{		
			temp = new EntityAlimento();
			temp = i.next();
			try {
				quantita = AlimentoDAO.readQuantita(temp.getCodice());
				AlimentoDAO.updateQuantita(temp.getCodice(), quantita+temp.getQuantita());
			} catch (DAOException e) {
				throw new DAOException("Impossibile annullare l'acquisto");			
			} catch (DBConnectionException e) {
				throw new DBConnectionException("Impossibile connettersi al database e annullare l'acquisto");				
			} 
				
		}
		
	}

	public void terminaAcquisto(ArrayList<EntitySelezione> alimentiSelezionati, ArrayList<EntityScelta> mixScelti, EntityAcquisto acquisto) throws DAOException, DBConnectionException {
		acquisto.setListaSceltaMix(mixScelti);
		acquisto.setListaSelezioneAlimenti(alimentiSelezionati);
		int id;
		try {
			AcquistoDAO.create(acquisto);
			id = AcquistoDAO.readLastID();
			for(Iterator<EntitySelezione> i = alimentiSelezionati.iterator();i.hasNext();)
			{
				EntitySelezione sel = i.next();
				SelezioneDAO.create(sel,id);
			}
			for(Iterator<EntityScelta> i = mixScelti.iterator();i.hasNext();)
			{
				EntityScelta scel = i.next();
				SceltaDAO.create(scel,id);
			}
		} catch (DAOException e) {
			throw new DAOException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new DBConnectionException(e.getMessage());
		}
		
	}
}
