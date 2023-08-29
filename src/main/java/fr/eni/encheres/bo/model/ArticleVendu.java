package fr.eni.encheres.bo.model;

import java.util.Date;

public class ArticleVendu {
	
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private Integer prixInitial;
	private Integer prixVente;
	private Utilisateur utilisateur;
	private Integer noUtilisateur = utilisateur.getNoUtilisateur();
	private Categorie categorie;
	private Integer noCategorie = categorie.getnoCategorie();
	
	public ArticleVendu(Integer noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, Integer prixInitial, Integer prixVente,
			Integer noUtilisateur, Integer noCategorie) {
		super();
		this.noArticle = noArticle;
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

	public Date getdateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setdateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public Date getdateFinEncheres() {
		return dateFinEncheres;
	}

	public void setdateFinEncheres(Date dateFinEncheres) {
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
