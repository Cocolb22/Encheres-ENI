package fr.eni.encheres.bo.model;

import java.time.LocalDateTime;

public class Enchere {

	private Integer noEnchere;
	private Utilisateur utilisateur;
	private Integer noUtilisateur;
	private ArticleVendu article;
	private Integer noArticle;
	private LocalDateTime dateEnchere;
	private Integer montantEnchere;
	
	
	
	public Enchere(Integer noEnchere, Utilisateur utilisateur, ArticleVendu article, LocalDateTime dateEnchere, Integer montantEnchere) {
		super();
		this.noEnchere = noEnchere;
		this.setNoUtilisateur(utilisateur.getNoUtilisateur());
		this.setNoArticle(article.getnoArticle());
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	public Enchere(Utilisateur utilisateur, ArticleVendu article, LocalDateTime dateEnchere, Integer montantEnchere) {
		super();
		this.setNoUtilisateur(utilisateur.getNoUtilisateur());
		this.setNoArticle(article.getnoArticle());
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public Integer getnoEnchere() {
		return noEnchere;
	}

	public void setnoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public ArticleVendu getArticle() {
		return article;
	}


	public void setArticle(ArticleVendu article) {
		this.article = article;
	}


	public LocalDateTime getdateEnchere() {
		return dateEnchere;
	}

	public void setdateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getmontantEnchere() {
		return montantEnchere;
	}

	public void setmontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}


	@Override
	public String toString() {
		return "Enchere [noEnchere=" + noEnchere + ", utilisateur=" + utilisateur + ", article=" + article
				+ ", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + "]";
	}


	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}


	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}


	public Integer getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}
	
	
}
