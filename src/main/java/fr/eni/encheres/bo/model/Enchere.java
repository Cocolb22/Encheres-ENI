package fr.eni.encheres.bo.model;

import java.time.LocalDateTime;

public class Enchere {

	private Integer noEnchere;
	private Utilisateur utilisateur;
	private Integer noUtilisateur = utilisateur.getnoUtilisateur();
	private ArticleVendu article;
	private Integer noArticle = article.getnoArticle();
	private LocalDateTime dateEnchere;
	private Integer montantEnchere;
	
	public Enchere(Integer noEnchere, Integer noUtilisateur, Integer noArticle, LocalDateTime dateEnchere, Integer montantEnchere) {
		super();
		this.noEnchere = noEnchere;
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public Enchere() {
		super();
	}

	public Integer getnoEnchere() {
		return noEnchere;
	}

	public void setnoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}

	public Integer getnoUtilisateur() {
		return noUtilisateur;
	}

	public void setnoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public Integer getnoArticle() {
		return noArticle;
	}

	public void setnoArticle(Integer noArticle) {
		this.noArticle = noArticle;
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
	
	
}
