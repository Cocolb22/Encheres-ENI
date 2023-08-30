package fr.eni.bll.gestionEnchere;

import java.util.List;

import fr.eni.encheres.bo.model.Enchere;

public interface EnchereManager {
	
	public void addEnchere(Enchere enchere) throws EnchereException;
	
	public List<Enchere> getAll() throws EnchereException;
	

		
	

}
