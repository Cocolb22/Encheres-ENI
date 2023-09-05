package fr.eni.encheres.bll.gestionEncheres;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bo.model.Enchere;


import fr.eni.encheres.dal.gestionEncheres.DAOFact;
import fr.eni.encheres.dal.gestionEncheres.EnchereDAO;
import fr.eni.encheres.dal.util.DALException;

public class EnchereManagerImpl implements EnchereManager {
	private EnchereDAO daoEnchere = DAOFact.getEnchereDAO();
	List<Integer> prixProposes = new ArrayList<Integer>();
	private EnchereDAO dao = DAOFact.getEnchereDAO();

	@Override
	public void addEnchere(Enchere enchere) throws BLLException {

		if(enchere.getDateEnchere().isBefore(LocalDateTime.now())) {
			throw new BLLException("ms_endenchere");
		}
		if(enchere.getMontantEnchere() > enchere.getMontantEnchere() ) {
			throw new BLLException("ms_mauvaiseoffre");
		}
		try{
			daoEnchere.insert(enchere);
		}catch(DALException e ) {
			throw new BLLException("ms_add");
		}
	}

	@Override
	public List<Enchere> getAll() throws BLLException {
		try{
			return daoEnchere.getAll();
		}catch(DALException e) {
			e.printStackTrace();
			throw new BLLException("ms_getall");
		}
		
	}

	@Override
	public List<Enchere> findByCategorie(Integer noCategorie) throws BLLException {
		try{
			return daoEnchere.findByCategorie(noCategorie);
		}catch(DALException e) {
			e.printStackTrace();
			throw new BLLException("ms_getall");
		}
	}

	@Override
	public List<Enchere> findByNomArticle(String nomArticle) throws BLLException {
		try{
			return daoEnchere.findByNomArticle(nomArticle);
		}catch(DALException e) {
			e.printStackTrace();
			throw new BLLException("ms_getall");
		}
	}

	public List<Enchere> filtrer(List<Enchere> lst, boolean achatEnchereOuverte, boolean achatEnchereEnCours,
			boolean encheresParticipees, boolean encheresGagnees, boolean venteEnchereDebutes,
			boolean VenteEnchereTermines, Object sessionUser) throws BLLException {
		Stream<Enchere> stream = lst.stream();
		
		if(achatEnchereOuverte) {
			stream = stream.filter(enchere -> enchere.getArticleVendu().getDateDebutEncheres().isBefore(LocalDate.now()));
		}
		if(achatEnchereEnCours) {
			stream = stream.filter(enchere -> enchere.getEnchereur().equals(sessionUser) && enchere.getEnchereur().equals(sessionUser));
		}
		if(encheresParticipees) {
			stream = stream.filter(enchere -> enchere.getArticleVendu().getDateDebutEncheres().isAfter(LocalDate.now()) && enchere.getEnchereur().equals(sessionUser));
		}
		if(encheresGagnees) {
			stream = stream.filter(enchere -> enchere.getArticleVendu().getDateFinEncheres().isAfter(LocalDate.now()) && enchere.getArticleVendu().getUtilisateur().getPseudo().equals(sessionUser));
		}
		if(venteEnchereDebutes) {
			stream = stream.filter(enchere ->enchere.getArticleVendu().getDateDebutEncheres().isBefore(LocalDate.now()) && enchere.getArticleVendu().getUtilisateur().getPseudo().equals(sessionUser));
		}
		if(VenteEnchereTermines) {
			stream = stream.filter(enchere -> enchere.getArticleVendu().getDateFinEncheres().isBefore(LocalDate.now()) && enchere.getArticleVendu().getUtilisateur().getPseudo().equals(sessionUser));
		}

		return stream.toList();
		
		
	}

	@Override
	public Integer meilleureOffre(Integer prixPropose) throws BLLException {
		prixProposes.add(prixPropose);
		Collections.sort(prixProposes, (p1, p2) -> p1.compareTo(p2));
		return prixProposes.get(0);
	}

	@Override
	public Integer getMontantMax(ArticleVendu article) throws BLLException {
		try {
			return dao.selectMontantMaxInteger(article);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException();
		}
	}
}
