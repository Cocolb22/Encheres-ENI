package fr.eni.encheres.bll.categories;

import java.util.List;

import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.Categorie;
import fr.eni.encheres.bundles.BusinessException;


public interface CategorieManager {
	
	public List<Categorie> getAll() throws  BLLException;

}
