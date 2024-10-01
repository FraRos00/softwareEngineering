package entity;

import java.util.Date;
import java.util.ArrayList;

public class EntityAcquisto {
	//COSTRUTTORI
	public EntityAcquisto(EntityClienteRegistrato cliente)
	{
		prezzoC=0;
		data = new Date();
		this.cliente = cliente;
	}
	
	public EntityAcquisto(Date data, float przC)
	{
		this.data = data;
		prezzoC = przC;
	}
	
	//METODI PUBBLICI
	public void addToPrezzoC(float aggiunta)
	{
		prezzoC+=aggiunta;
	}
	//GETTER
	public Date getData()
	{
		return data;
	}
	
	public float getPrezzoC()
	{
		return prezzoC;
	}
	public ArrayList<EntitySelezione> getListaSelezioneAlimenti()
	{
		return (ArrayList<EntitySelezione>)l_selezione_alimenti.clone();
	}
	public ArrayList<EntityScelta> getListaSceltaMix()
	{
		return (ArrayList<EntityScelta>)l_scelta_mix.clone();
	}
	public EntityClienteRegistrato getCliente()
	{
		return cliente;
	}
	//SETTER
	public void setData(Date data)
	{
		this.data=data;
	}
	public void setPrezzoC(float pc)
	{
		prezzoC = pc;
	}
	public void setListaSelezioneAlimenti(ArrayList<EntitySelezione> la)
	{
		l_selezione_alimenti = (ArrayList<EntitySelezione>)la.clone();
	}
	public void setListaSceltaMix(ArrayList<EntityScelta> lm)
	{
		l_scelta_mix = (ArrayList<EntityScelta>)lm.clone();
	}
	public void setClienteRegistrato(EntityClienteRegistrato cr)
	{
		cliente = cr;
	}
	
	private Date data;
	private float prezzoC;
	private ArrayList<EntitySelezione> l_selezione_alimenti;
	private ArrayList<EntityScelta> l_scelta_mix; 
	private EntityClienteRegistrato cliente;
}
