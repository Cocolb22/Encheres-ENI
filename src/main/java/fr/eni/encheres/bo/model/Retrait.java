package fr.eni.encheres.bo.model;

public class Retrait {

	private ArticleVendu article;
	private Integer noArticle = article.getnoArticle();
	private String rue;
	private String codePostal;
	private String ville;

	public Retrait(Integer noArticle, String rue, String codePostal, String ville) {
		super();
		this.noArticle = noArticle;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public Retrait() {
		super();
	}

	public Integer getnoArticle() {
		return noArticle;
	}

	public void setnoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getcodePostal() {
		return codePostal;
	}

	public void setcodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}
