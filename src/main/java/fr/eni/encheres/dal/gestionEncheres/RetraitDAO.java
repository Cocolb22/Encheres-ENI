package fr.eni.encheres.dal.gestionEncheres;

import java.util.List;

import fr.eni.encheres.bo.model.Retrait;
import fr.eni.encheres.dal.util.DALException;

public interface RetraitDAO {
	
	public void insert(Retrait retrait) throws DALException;
	public List<Retrait> findByNoArticle(Integer noArticle) throws DALException;

}
