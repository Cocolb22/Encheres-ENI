package fr.eni.encheres.bll.categories;

import java.util.List;

import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.Categorie;

import fr.eni.encheres.dal.gestionEncheres.CategorieDAO;
import fr.eni.encheres.dal.gestionEncheres.DAOFact;
import fr.eni.encheres.dal.util.DALException;

public class CategorieManagerImpl implements CategorieManager {
	
	private CategorieDAO dao = DAOFact.getCategorieDAO();

	@Override
	public List<Categorie> getAll() throws BLLException {
		try {
			return dao.getAll();
		}catch(DALException e) {
			e.printStackTrace();
			throw new BLLException("ms_getall");
		}
	}

}
