package fr.eni.encheres.bll.gestionUtilisateurs;

import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.bundles.BusinessException;
import fr.eni.encheres.dal.gestionUtilisateurs.DAOFactUtilisateur;
import fr.eni.encheres.dal.gestionUtilisateurs.UtilisateurDAO;
import fr.eni.encheres.dal.util.CodeResultDAL;

public class UtilisateurManagerImpl implements UtilisateurManager {
	private UtilisateurDAO dao = DAOFactUtilisateur.getUtilisateurDAO();

	@Override
	public void addUtilisateur(Utilisateur utilisateur, String confirmationMDP) throws BusinessException {

		BusinessException  be = new BusinessException();
		
		if(be.hasErreurs()) {
			System.out.println(be);
			throw be;
		} else {
			dao.insert(utilisateur);
		}
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur, String confirmationMdp) throws BusinessException {
		BusinessException be = new BusinessException();
//		utilisateur = dao.findByLoginAndPassword(utilisateur.getPseudo(), utilisateur.getMotDePasse());
		validerUtilisateur(utilisateur, confirmationMdp, be);
		validerNoUtilisateur(utilisateur.getNoUtilisateur(), be);
		
		System.out.println(utilisateur);
		
		if(be.hasErreurs()) {
			System.out.println(be);
			throw be;
		} else {
			dao.update(utilisateur, utilisateur.getNoUtilisateur());
		}
		
	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur)throws BusinessException {
		BusinessException be = new BusinessException();
		validerNoUtilisateur(utilisateur.getNoUtilisateur(), be);
		
		if(be.hasErreurs()) {
			System.out.println(be);
			throw be;
		} else {
			dao.delete(utilisateur.getNoUtilisateur());
		}
		
	}

	@Override
	public Utilisateur connectUtilisateur(String pseudo, String motDePasse) throws BusinessException {
		BusinessException  be = new BusinessException();
		Utilisateur utilisateur = dao.findByLoginAndPassword(pseudo, motDePasse);
		if(utilisateur != null) {
			return utilisateur;
		} else {
			be.ajouterErreur(CodeResultDAL.CHECK_CONNECTION_ECHEC);
			throw be;
		}
	}

	private void validerPseudo(String pseudo, BusinessException be) throws BusinessException {
		if(pseudo == null || pseudo.isBlank() || pseudo.length() >= 30) {
			be.ajouterErreur(CodeResultDAL.INSERT_PSEUDO_ECHEC);
		};
	}
	
	private void validerNom(String nom, BusinessException be) throws BusinessException {
		if(nom == null || nom.isBlank() || nom.length() >= 30) {
			be.ajouterErreur(CodeResultDAL.INSERT_NOM_ECHEC);
		};
	}
	
	private void validerPrenom(String prenom, BusinessException be) throws BusinessException {
		if(prenom == null || prenom.isBlank() || prenom.length() >= 30) {
			be.ajouterErreur(CodeResultDAL.INSERT_PRENOM_ECHEC);
		};
	}
	
	private void validerEmail(String email, BusinessException be) throws BusinessException {
		if(email == null || email.isBlank() || email.length() >= 100 || !email.contains("@")) {
			be.ajouterErreur(CodeResultDAL.INSERT_EMAIL_ECHEC);
		};
	}

	private void validerTelephone(String telephone, BusinessException be) throws BusinessException {
		if(telephone.length() >= 15 || !telephone.matches("\\d+")) {
			be.ajouterErreur(CodeResultDAL.INSERT_TELEPHONE_ECHEC);
		};
	}
	
	private void validerRue(String rue, BusinessException be) throws BusinessException {
		if(rue == null || rue.isBlank() || rue.length() >= 30) {
			be.ajouterErreur(CodeResultDAL.INSERT_RUE_ECHEC);
		};
	}
	
	private void validerCodePostal(String codePostal, BusinessException be) throws BusinessException {
		if(codePostal == null || codePostal.isBlank() || codePostal.length() >= 10 || !codePostal.matches("\\d+")) {
			be.ajouterErreur(CodeResultDAL.INSERT_CODE_POSTAL_ECHEC);
		};
	}
	
	private void validerVille(String ville, BusinessException be) throws BusinessException {
		if(ville == null || ville.isBlank() || ville.length() >= 30) {
			be.ajouterErreur(CodeResultDAL.INSERT_VILLE_ECHEC);
		};
	}
	
	private void validerMotDePasse(String motDePasse, BusinessException be) throws BusinessException {
		if(motDePasse == null || motDePasse.isBlank() || motDePasse.length() >= 30) {
			be.ajouterErreur(CodeResultDAL.INSERT_MOT_DE_PASSE_ECHEC);
		};
	}
	
	private void validerConfirmationMDP(String motDePasseConfirmation, String motDePasse, BusinessException be) throws BusinessException {
		if(!motDePasseConfirmation.equals(motDePasse)) {
			be.ajouterErreur(CodeResultDAL.INSERT_CONFIRMATION_MDP_ECHEC);
		};
	}
	
	private void validerNoUtilisateur(Integer noUtilisateur, BusinessException be) throws BusinessException {
		if(noUtilisateur == null || noUtilisateur < 1) {
			be.ajouterErreur(CodeResultDAL.INSERT_NO_UTILISATEUR_ECHEC);
		}
	}
	
	private void validerUtilisateur(Utilisateur utilisateur, String confirmationMDP, BusinessException be) throws BusinessException {
		
		validerPseudo(utilisateur.getPseudo(), be);
		validerNom(utilisateur.getNom(), be);
		validerPrenom(utilisateur.getPrenom(), be);
		validerEmail(utilisateur.getEmail(), be);
		validerTelephone(utilisateur.getTelephone(), be);
		validerRue(utilisateur.getRue(), be);
		validerCodePostal(utilisateur.getCodePostal(), be);
		validerVille(utilisateur.getVille(), be);
		validerMotDePasse(utilisateur.getMotDePasse(), be);
		validerConfirmationMDP(confirmationMDP, utilisateur.getMotDePasse(), be);
	}
}
