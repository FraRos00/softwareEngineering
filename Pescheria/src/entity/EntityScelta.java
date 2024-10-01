package entity;

public class EntityScelta {
	
	//COSTRUTTORI
	public EntityScelta(int quantita, EntityAcquisto acquisto, EntityMixCrudi mix)
	{
		this.quantita = quantita;
		this.acquisto = acquisto;
		this.mix = mix;
	}
	//GETTER
	public int getQuantita()
	{
		return quantita;
	}
	public EntityMixCrudi getMix()
	{
		return mix;
	}
	public EntityAcquisto getAcquisto()
	{
		return acquisto;
	}
	//SETTER
	public void setQuantita(int qta)
	{
		quantita = qta;
	}
	public void setMix(EntityMixCrudi mx)
	{
		mix = mx;
	}
	public void setAcquisto(EntityAcquisto aq)
	{
		acquisto = aq;
	}
	
	private int quantita;
	private EntityMixCrudi mix;
	private EntityAcquisto acquisto;

}
