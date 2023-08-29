package fr.eni.encheres.bo.model;

import java.time.LocalDate;

public class ArticleVendu {
	
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private Integer prixInitial;
	private Integer prixVente;
	private Utilisateur utilisateur;
	private Integer noUtilisateur = utilisateur.getNoUtilisateur();
	private Categorie categorie;
	private Integer noCategorie = categorie.getnoCategorie();
	
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, Integer prixInitial, Integer prixVente,
			Integer noUtilisateur, Integer noCategorie) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}

	public ArticleVendu() {
		super();
	}

	public Integer getnoArticle() {
		return noArticle;
	}

	public void setnoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	public String getnomArticle() {
		return nomArticle;
	}

	public void setnomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getdateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setdateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getdateFinEncheres() {
		return dateFinEncheres;
	}

	public void setdateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public Integer getprixInitial() {
		return prixInitial;
	}

	public void setprixInitial(Integer prixInitial) {
		this.prixInitial = prixInitial;
	}

	public Integer getprixVente() {
		return prixVente;
	}

	public void setprixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}
	
	public Integer getnoUtilisateur() {
		return noUtilisateur;
	}

	public void setnoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public Integer getnoCategorie() {
		return noCategorie;
	}

	public void setnoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description="
				+ description + ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres="
				+ dateFinEncheres + ", prixInitial=" + prixInitial + ", prixVente=" + prixVente 
				+ ", noUtilisateur=" + noUtilisateur + ", noCategorie=" + noCategorie + "]";
	}
	
	

}
