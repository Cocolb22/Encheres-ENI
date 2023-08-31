package fr.eni.bll.gestionEnchere;


import java.time.LocalDateTime;
import java.util.List;

import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.Enchere;
import fr.eni.encheres.dal.gestionEncheres.DAOFact;
import fr.eni.encheres.dal.gestionEncheres.EnchereDAO;
import fr.eni.encheres.dal.util.DALException;

public class EnchereManagerImpl implements EnchereManager {
	
	private EnchereDAO dao = DAOFact.getEnchereDAO();
	

	@Override
	public void addEnchere(Enchere enchere) throws BLLException {

		if(enchere.getdateEnchere().isBefore(LocalDateTime.now())) {
			throw new BLLException("ms_endenchere");
		}
		if(enchere.getmontantEnchere() > enchere.getmontantEnchere() ) {
			throw new BLLException("ms_mauvaiseoffre");
		}
		try{
			dao.insert(enchere);
		}catch(DALException e ) {
			throw new BLLException("ms_add");
		}
	}

	@Override
	public List<Enchere> getAll() throws BLLException {
		try{
			return dao.getAll();
		}catch(DALException e) {
			throw new BLLException("ms_getall");
		}
		
	}

	@Override
	public List<Enchere> findByCategorie(Integer noCategorie) throws BLLException {
		try{
			return dao.findByCategorie(noCategorie);
		}catch(DALException e) {
			throw new BLLException("ms_getall");
		}
	}

	@Override
	public List<Enchere> findByNomArticle(String nomArticle) throws BLLException {
		try{
			return dao.findByNomArticle(nomArticle);
		}catch(DALException e) {
			throw new BLLException("ms_getall");
		}
	}


	
	

}
