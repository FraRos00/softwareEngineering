package boundary;

import java.util.ArrayList;
import java.util.Scanner;

import control.GestionePescheriaController;
import entity.EntityClienteRegistrato;
import entity.EntityScelta;
import entity.EntitySelezione;
import exception.DAOException;
import exception.DBConnectionException;
import exception.InputException;
import exception.OperationException;
import entity.EntityAcquisto;
import entity.EntityAlimento;

public class BUtenteRegistrato {
	
	//COSTRUTTORE
	public BUtenteRegistrato() {}
	
	public void acquistaProdotti(EntityClienteRegistrato cliente) 
	{
		boolean selezioneTerminata = false;
		String codiceProdotto;
		float quantita = 0;
		ArrayList<EntityAlimento> alimentiAcquistati = new ArrayList<EntityAlimento>();
		ArrayList<EntitySelezione> alimentiSelezionati = new ArrayList<EntitySelezione>();
		ArrayList<EntityScelta> mixScelti = new ArrayList<EntityScelta>();
		String confermaAcquisto;
		String carta;
		boolean pagato = false;
		
		GestionePescheriaController GPC = GestionePescheriaController.getIstance();
		EntityAcquisto acquisto = GPC.createAcquisto(cliente);
		Scanner scan;
		do
		{	
			scan = new Scanner(System.in);
			System.out.println("Immettere il codice del prodotto che si intende acquistare(digita exit per terminare la selezione): ");
			codiceProdotto = scan.nextLine();
			if(!codiceProdotto.equals("exit"))
			{	
				do
				{
					System.out.println("Immettere la quantita' desiderata: ");
					quantita = scan.nextFloat();
					if(quantita<0)
						System.out.println("La quantita' inserita non e' valida, riprovare");
				}while(quantita<0);
				//gestore.acquisto
				try {
				GPC.acquistaProdotto(codiceProdotto,quantita,alimentiAcquistati,acquisto,alimentiSelezionati,mixScelti);
				}
				catch(InputException e) 
				{
					System.out.println(e.getMessage());
				}
				catch (DAOException e) {
					System.out.println(e.getMessage());
				}
				catch (DBConnectionException e) 
				{
					System.out.println(e.getMessage());
				}
				catch (OperationException e)
				{
					System.out.println(e.getMessage());
				}
				
				
							
			}
			else
			{
				selezioneTerminata = true;
			}
			
		}while(!selezioneTerminata);
		
		if(acquisto.getPrezzoC()>0)
		{
			System.out.println("Prezzo totale: " + acquisto.getPrezzoC());
			do {
				System.out.println("Digitare 'Si' per confermare e 'No' per annullare: ");
				scan = new Scanner(System.in);
				confermaAcquisto = scan.nextLine();
				if(!confermaAcquisto.equals("Si") && !confermaAcquisto.equals("No"))
				{
					System.out.println("Input non valido.");
				}
			}while(!confermaAcquisto.equals("Si") && !confermaAcquisto.equals("No"));
			
			if(confermaAcquisto.equals("Si"))
			{
				System.out.println("Inserire carta di credito: ");
				scan = new Scanner(System.in);
				carta = scan.nextLine();
				
				if(pagato = effettuaPagamento(carta))
				{
					try {
						
						GPC.terminaAcquisto(alimentiSelezionati,mixScelti,acquisto);
						System.out.println("Acquisto effettuato");
					} catch (DAOException|DBConnectionException e) {
						System.out.println(e.getMessage());
						//SE LA REGISTRAZIONE DELL'ACQUISTO FALLISCE BISOGNA RIPRISTINARE LO STATO DEL DATABASE
						try {
							GPC.annullaAcquisto(alimentiAcquistati);
						} catch (DAOException ex) {
							System.out.println(ex.getMessage());
						} catch (DBConnectionException ex) {
							System.out.println( ex.getMessage());
						} 
						System.out.println("Acquisto annullato");
					} 
				}
			}
		
		//SE ANNULLO L'ACQUISTO O LA CARTA NON E VALIDA
			if(confermaAcquisto.equals("No") || pagato == false)
			{
				
				try {
					GPC.annullaAcquisto(alimentiAcquistati);
				} catch (DAOException e) {
					System.out.println(e.getMessage());
				} catch (DBConnectionException e) {
					System.out.println( e.getMessage());
				} 
				System.out.println("Acquisto annullato");
			}
		}
		//se non ho selezionato nessun alimento
		else {
			System.out.println("Acquisto terminato");
		}
	}

	private boolean effettuaPagamento(String carta) {		
		return true;
	}
	
}
