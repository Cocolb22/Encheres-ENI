package fr.eni.encheres.bo.model;

public class Categorie {
	
	private Integer noCategorie;
	private String libelle;
	
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}
	

	public Categorie() {
		super();
	}

	public Integer getnoCategorie() {
		return noCategorie;
	}

	public void setnoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	

}
