package entity;

public class EntityAlimento {
	
	//METODI PRIVATI
	public static boolean checkCodice(String c)
	{
		String substr = c.substring(1);
		return c.length() == 5 && c.substring(0,1).equals("A") && substr.matches("-?\\d+");	
		//rifletti dove usarlo
	}
	
	//COSTRUTTORI
	public EntityAlimento(String cd, String type, float qta, float prz, int cdp, String ta)
	{
		codice = cd;
		tipologia = type;
		quantita = qta;
		prezzo = prz;
		codProvenienza = cdp;
		tipoAllevamento = ta;
	}
	public EntityAlimento(String cd, String type, float qta, float prz, int cp)
	{
		codice = cd;
		tipologia = type;
		quantita = qta;
		prezzo = prz;
		codProvenienza = cp;
		tipoAllevamento = "Nessuno";
	}
	public EntityAlimento(String cd, float qta,float prz)
	{
		codice = cd;
		quantita = qta;
		prezzo = prz;
	}
	public EntityAlimento(String cd, float qta)
	{
		codice = cd;
		quantita = qta;
	}
	
	public EntityAlimento() {
		
	}

	//METODI GETTER
	public String getTipoAllevamento()
	{
		return new String(tipoAllevamento);
	}
	public String getCodice()
	{
		return new String(codice);
	}
	
	public float getQuantita()
	{
		return quantita;
	}
	
	public float getPrezzo()
	{
		return prezzo;
	}
	
	public int getCodProvenienza()
	{
		return codProvenienza;
	}
	
	public String getTipologia()
	{
		return new String(tipologia);
	}
	//METODI SETTER
	public void setCodice(String c)
	{
		codice = c;
	}
	public void setTipologia(String t)
	{
		tipologia = t;
	}
	public void setQuantita(float q)
	{
		quantita = q;
	}
	public void setPrezzo(float p)
	{
		prezzo = p;
	}
	public void setCodProvenienza(int cp)
	{
		codProvenienza = cp;
	}
	public void setTipoAllevamento(String ta)
	{
		tipoAllevamento = ta;
	}
	
	
	private String codice;
	private String tipologia;
	private float quantita;
	private float prezzo;
	private int codProvenienza;
	private String tipoAllevamento;
}
