package entity;

public class EntitySelezione {

	//COSTRUTTORE
	public EntitySelezione(float qta, EntityAcquisto acq, EntityAlimento alim)
	{
		quantita = qta;
		acquisto = acq;
		alimento = alim;
	}
	
	//GETTER
	public float getQuantita()
	{
		return quantita;
	}
	public EntityAcquisto getAcquisto()
	{
		return acquisto;
	}
	public EntityAlimento getAlimento()
	{
		return alimento;
	}
	
	//SETTER
	public void setQuantita(float quantita)
	{
		this.quantita = quantita;
	}
	
	public void setAlimento(EntityAlimento al)
	{
		alimento = al;
	}
	public void setAcquisto(EntityAcquisto acq)
	{
		acquisto = acq;
	}
	
	private float quantita;
	private EntityAcquisto acquisto;
	private EntityAlimento alimento;
}
