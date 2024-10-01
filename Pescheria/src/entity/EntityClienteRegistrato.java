package entity;

public class EntityClienteRegistrato {
	
	//COSTRUTTORE
	public EntityClienteRegistrato(String nu, String psw)
	{
		nomeUtente = nu;
		password = psw;
	}
	
	//GETTER
	public String getNomeUtente()
	{
		return new String(nomeUtente);
	}
	
	public String getPassword()
	{
		return new String(password);
	}
	
	//SETTER
	public void setNomeUtente(String nu)
	{
		nomeUtente = nu;
	}
	
	public void setPassword(String psw)
	{
		password = psw;
	}
	
	private String nomeUtente;
	private String password;
}
