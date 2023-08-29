package fr.eni.encheres.dal.gestionEncheres;

import java.util.List;

import fr.eni.encheres.bo.model.Enchere;
import fr.eni.encheres.dal.util.DALException;

public interface EnchereDAO {
	public void insert(Enchere enchere) throws DALException;
	public List<Enchere> getAll() throws DALException;

}
