package fr.eni.bll.gestionEnchere;

import java.util.List;

import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.Enchere;

public interface EnchereManager {
	
	public void addEnchere(Enchere enchere) throws BLLException;
	
	public List<Enchere> getAll() throws BLLException;
	
	public List<Enchere> findByCategorie(Integer noCategorie) throws BLLException;
	
	public List<Enchere> findByNomArticle(String nomArticle) throws BLLException;

		
	

}
