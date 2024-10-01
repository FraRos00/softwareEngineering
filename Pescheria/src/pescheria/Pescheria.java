package pescheria;

import java.util.Iterator;
import java.util.Scanner;

import boundary.BUtenteRegistrato;
import entity.*;

public class Pescheria {

	public static void main(String[] args) {
		//il caso d'uso è avviato da un cliente registrato che ha effettuato il login
		EntityClienteRegistrato clienteLoggato = new EntityClienteRegistrato("Test","Prova");
		System.out.println("E' stata selezionata la funzionalita' di acquisto prodotti");
		BUtenteRegistrato bur = new BUtenteRegistrato();
		bur.acquistaProdotti(clienteLoggato);

	}
}
