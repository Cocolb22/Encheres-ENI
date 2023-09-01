package fr.eni.encheres.bll.gestionEncheres;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bundles.BusinessException;
import fr.eni.encheres.dal.gestionEncheres.ArticleVenduDAO;
import fr.eni.encheres.dal.gestionEncheres.DAOFact;

public class VenteArticleManagerImpl implements VenteArticleManager {

	private ArticleVenduDAO dao = DAOFact.getArticleVenduDAO();
	
	@Override
	public void addArticle(ArticleVendu article) throws BusinessException {
		BusinessException be = new BusinessException();
		
		if(be.hasErreurs()) {
			System.out.println(be);
			throw be;
		} else {
			dao.insert(article);
		}
	}

	@Override
	public List<ArticleVendu> getAllArticles() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void validerArticleVendu(ArticleVendu article, BusinessException be) throws BusinessException{
		validerNomArticle(article.getNomArticle(), be);
		validerDescriptionArticle(article.getDescription(), be);
		validerDateDebutEnchere(article.getDateDebutEncheres(), be);
		validerDateFinEnchere(article.getDateFinEncheres(), article.getDateDebutEncheres(), be);
		validerPrixInitial(article.getPrixInitial(), be);
		validerPrixVente(article.getPrixVente(), article.getPrixVente(), be);
		validerRue(article.getPointRetrait().getRue(), be);
		validerCodePostal(article.getPointRetrait().getCodePostal(), be);
		validerVille(article.getPointRetrait().getVille(), be);
	}
	
	private void validerNoArticle(Integer noArticle, BusinessException be) {
		if(noArticle == null || noArticle < 1) {
			be = new BusinessException(30000);
		}
	}
	
	private void validerNomArticle(String nom, BusinessException be) {
		if(nom == null || nom.isBlank() || nom.length() >= 30) {
			be = new BusinessException(30001);
		};
	}
	
	private void validerDescriptionArticle(String description, BusinessException be) {
		if(description == null || description.isBlank() || description.length() >= 300) {
			be = new BusinessException(30002);
		};
	}
	
	private void validerDateDebutEnchere(LocalDate dateDebutEnchere, BusinessException be) {
		if(dateDebutEnchere == null || dateDebutEnchere.isBefore(LocalDate.now())) {
			be = new BusinessException(30003);
		}
	}

	private void validerDateFinEnchere(LocalDate dateFinEnchere, LocalDate dateDebutEnchere, BusinessException be) {
		if(dateFinEnchere == null || dateFinEnchere.isBefore(dateDebutEnchere.plusDays(1))) {
			be = new BusinessException(30004);
		}
	}
	
	private void validerPrixInitial(Integer prix, BusinessException be) {
		if(prix == null || prix < 1) {
			be = new BusinessException(30005);
		}
	}
	
	private void validerPrixVente(Integer prix, Integer prixInitial, BusinessException be) {
		if(prix < prixInitial) {
			be = new BusinessException(30006);
		}
	}
	
	private void validerRue(String rue, BusinessException be) {
		if(rue == null || rue.isBlank() || rue.length() >= 30) {
			be = new BusinessException(30007);
		};
	}
	
	private void validerCodePostal(String codePostal, BusinessException be) {
		if(codePostal == null || codePostal.isBlank() || codePostal.length() >= 10 || !codePostal.matches("\\d+")) {
			be = new BusinessException(30008);
		};
	}
	
	private void validerVille(String ville, BusinessException be){
		if(ville == null || ville.isBlank() || ville.length() >= 30) {
			be = new BusinessException(30009);
		};
	}
}
