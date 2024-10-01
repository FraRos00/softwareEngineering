package entity;

import java.util.ArrayList;


public class EntityMixCrudi {
	
	//METODI PRIVATI
	public static boolean checkCodice(String c)
	{
		String substr = c.substring(1);
		return c.length() == 5 && c.substring(0,1).equals("M") && substr.matches("-?\\d+");	
		//rifletti dove usarlo
	}
	
	//COSTRUTTORE
	public EntityMixCrudi(float prz, String cd, ArrayList<EntityComposizione> lq)
	{
		prezzo = prz;
		codice = cd;
		lista_quantita = (ArrayList<EntityComposizione>) lq.clone();
	}
	public EntityMixCrudi(String cd, float prz)
	{
		codice = cd;
		prezzo = prz;
		lista_quantita = null;
	}
	//METODI GETTER
	public float getPrezzo()
	{
		return prezzo;
	}
	public String getCodice()
	{
		return new String(codice);
	}
	
	public ArrayList<EntityComposizione> getListaQuantita()
	{
		return (ArrayList<EntityComposizione>) lista_quantita.clone();
	}
	//METODI SETTER
	public void setPrezzo(float p)
	{
		prezzo = p;
	}
	public void setCodice(String c)
	{
		codice = c;
	}
	public void setListaQuantita(ArrayList<EntityComposizione> lq)
	{
		lista_quantita = (ArrayList<EntityComposizione>) lq.clone();
	}
	//METODI PUBBLICI
	
	
	private float prezzo;
	private String codice;
	private ArrayList<EntityComposizione> lista_quantita;
}
