package entity;

public class EntityComposizione {
	
	//COSTRUTTORE
	public EntityComposizione(float qta, EntityAlimento alim)
	{
		quantita = qta;
		alimento = alim;
	}
	public EntityComposizione() {};
	
	//GETTER
	public EntityAlimento getAlimento()
	{
		return alimento;
	}
	
	public float getQuantita()
	{
		return quantita;
	}
	//SETTER
	public void setAlimento(EntityAlimento al)
	{
		alimento = al;
	}
	public void setQuantita(float qta)
	{
		quantita = qta;
	}

	
	private EntityAlimento alimento;
	private float quantita;
}
