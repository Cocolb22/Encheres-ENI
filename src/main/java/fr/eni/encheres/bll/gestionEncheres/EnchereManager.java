package fr.eni.encheres.bll.gestionEncheres;

import java.util.List;

import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bo.model.Enchere;


public interface EnchereManager {
	
	public void addEnchere(Enchere enchere) throws BLLException;
	
	public List<Enchere> getAll() throws  BLLException;
	
	public List<Enchere> findByCategorie(Integer noCategorie) throws BLLException;
	
	public List<Enchere> findByNomArticle(String nomArticle) throws BLLException;
	
	public List<Enchere> filtrer(List<Enchere> lst,
			boolean achatEnchereOuverte,
			boolean achatEnchereEnCours,
			boolean encheresParticipees,
			boolean encheresGagnees,
			boolean venteEnchereDebutes,
			boolean VenteEnchereTermines,
<<<<<<< HEAD

			Integer sessionUser) throws BLLException;


=======
			Integer sessionUser) throws BLLException;
>>>>>>> refs/heads/develop

	public Integer getMontantMax(ArticleVendu article) throws BLLException;
}
