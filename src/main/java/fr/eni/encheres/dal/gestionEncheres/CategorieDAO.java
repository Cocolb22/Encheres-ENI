package fr.eni.encheres.dal.gestionEncheres;

import java.util.List;

import fr.eni.encheres.bo.model.Categorie;
import fr.eni.encheres.dal.util.DALException;

public interface CategorieDAO {
	
	public List<Categorie> getAll() throws DALException;

}
