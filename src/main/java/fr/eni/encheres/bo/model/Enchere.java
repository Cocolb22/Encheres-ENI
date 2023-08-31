package fr.eni.encheres.bo.model;

import java.time.LocalDateTime;

public class Enchere {

	private Integer noEnchere;
	private Utilisateur enchereur;
	private ArticleVendu articleVendu;
	private LocalDateTime dateEnchere;
	private Integer montantEnchere;
	
	public Enchere() {
		
	}

	public Enchere(Utilisateur enchereur, ArticleVendu articleVendu, LocalDateTime dateEnchere,
			Integer montantEnchere) {
		this();
		this.enchereur = enchereur;
		this.articleVendu = articleVendu;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public Enchere(Integer noEnchere, Utilisateur enchereur, ArticleVendu articleVendu, LocalDateTime dateEnchere,
			Integer montantEnchere) {
		this();
		this.noEnchere = noEnchere;
		this.enchereur = enchereur;
		this.articleVendu = articleVendu;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}



	public Integer getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}

	public Utilisateur getEnchereur() {
		return enchereur;
	}

	public void setEnchereur(Utilisateur enchereur) {
		this.enchereur = enchereur;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	@Override
	public String toString() {
		return "Enchere [noEnchere=" + noEnchere + ", enchereur=" + enchereur + ", articleVendu=" + articleVendu
				+ ", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + "]";
	}
	
	


	
	
}
