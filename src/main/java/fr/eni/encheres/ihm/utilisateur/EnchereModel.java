package fr.eni.encheres.ihm.utilisateur;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bo.model.Enchere;
import fr.eni.encheres.bo.model.Utilisateur;

public class EnchereModel {
	
	private Utilisateur utilisateur;
	private ArticleVendu article;
	private LocalDateTime dateFinEnchere;
	private Integer prix;
	

	private String message = "";
	private Enchere current = new Enchere(utilisateur, article, dateFinEnchere, prix);
	private List<Enchere> lstEnchere = new ArrayList<>();
	
	public EnchereModel() {
		
	}

	public EnchereModel(String message, Enchere current, List<Enchere> lstEnchere) {
		super();
		this.message = message;
		this.current = current;
		this.lstEnchere = lstEnchere;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Enchere getCurrent() {
		return current;
	}

	public void setCurrent(Enchere current) {
		this.current = current;
	}

	public List<Enchere> getLstEnchere() {
		return lstEnchere;
	}

	public void setLstEnchere(List<Enchere> lstEnchere) {
		this.lstEnchere = lstEnchere;
	}

	@Override
	public String toString() {
		return "EnchereModel [message=" + message + ", current=" + current + ", lstEnchere=" + lstEnchere + "]";
	}
	
	
}
