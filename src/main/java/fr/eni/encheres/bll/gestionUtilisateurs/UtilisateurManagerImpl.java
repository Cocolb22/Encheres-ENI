package fr.eni.encheres.bll.gestionUtilisateurs;

import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.dal.gestionUtilisateur.DAOFactUtilisateur;
import fr.eni.encheres.dal.gestionUtilisateur.UtilisateurDAO;
import fr.eni.encheres.dal.util.DALException;

public class UtilisateurManagerImpl implements UtilisateurManager {
	private UtilisateurDAO dao = DAOFactUtilisateur.getUtilisateurDAO();

	@Override
	public void addUtilisateur(Utilisateur utilisateur) throws BLLException {
		try {
			dao.insert(utilisateur);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur showUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur connectUtilisateur(String pseudo, String motDePasse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur disconnectUtilisateur() {
		// TODO Auto-generated method stub
		return null;
	}

}
