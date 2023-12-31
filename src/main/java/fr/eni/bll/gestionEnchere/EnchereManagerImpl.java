package fr.eni.bll.gestionEnchere;


import java.time.LocalDateTime;
import java.util.List;
import fr.eni.encheres.bo.model.Enchere;
import fr.eni.encheres.dal.gestionEncheres.DAOFact;
import fr.eni.encheres.dal.gestionEncheres.EnchereDAO;
import fr.eni.encheres.dal.util.DALException;

public class EnchereManagerImpl implements EnchereManager {
	
	private EnchereDAO dao = DAOFact.getEnchereDAO();
	

	@Override
	public void addEnchere(Enchere enchere) throws EnchereException {

		if(enchere.getdateEnchere().isBefore(LocalDateTime.now())) {
			throw new EnchereException("ms_endenchere");
		}
		if(enchere.getmontantEnchere() > enchere.getmontantEnchere() ) {
			throw new EnchereException("ms_mauvaiseoffre");
		}
		try{
			dao.insert(enchere);
		}catch(DALException e ) {
			throw new EnchereException("ms_add");
		}
	}

	@Override
	public List<Enchere> getAll() throws EnchereException {
		try{
			return dao.getAll();
		}catch(DALException e) {
			throw new EnchereException("ms_getall");
		}
		
	}


	
	

}
