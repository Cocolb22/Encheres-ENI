package fr.eni.encheres.bll.gestionEncheres;

import java.util.List;

import fr.eni.encheres.bll.util.BLLException;
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
			Object sessionUser) throws BLLException;
	

		
	

}
