package fr.eni.encheres.bll.gestionEncheres;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
		
		System.out.println(getMontantMax(enchere.getArticleVendu()));
		System.out.println(enchere.getMontantEnchere());
		if(getMontantMax(enchere.getArticleVendu()) < enchere.getMontantEnchere() ) {
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
			Comparator<Enchere> dateComparator = Comparator.comparing(Enchere::getDateEnchere);
			
			// Trie de la liste d'enchères par date en ordre décroissant
			List<Enchere> enchereTrieParDate = daoEnchere.getAll().stream()
			    .sorted(dateComparator.reversed()) 
			    .collect(Collectors.toList());

			//Set pour stocker les numéros d'articles sans doublons
			Set<Integer> noArticlesUniques = new HashSet<>();
			List<Enchere> dernieresEncheresSansDoublon = new ArrayList<>();

			// Parcoure des enchères trié par date et ajout des enchères correspondant aux articles uniques
			for (Enchere enchere : enchereTrieParDate) {
			    int noArticle = enchere.getArticleVendu().getNoArticle();
			    if (!noArticlesUniques.contains(noArticle)) {
			        dernieresEncheresSansDoublon.add(enchere);
			        noArticlesUniques.add(noArticle);
			    }
			}
			return dernieresEncheresSansDoublon;
		}catch(DALException e) {
			e.printStackTrace();
			throw new BLLException("ms_getall");
		}
		
	}

	@Override
	public List<Enchere> findByCategorie(Integer noCategorie) throws BLLException {
		try{
			Comparator<Enchere> dateComparator = Comparator.comparing(Enchere::getDateEnchere);
			
			// Trie de la liste d'enchères par date en ordre décroissant
			List<Enchere> enchereTrieParDate = daoEnchere.findByCategorie(noCategorie).stream()
			    .sorted(dateComparator.reversed()) 
			    .collect(Collectors.toList());

			//Set pour stocker les numéros d'articles sans doublons
			Set<Integer> noArticlesUniques = new HashSet<>();
			List<Enchere> dernieresEncheresSansDoublon = new ArrayList<>();

			// Parcoure des enchères trié par date et ajout des enchères correspondant aux articles uniques
			for (Enchere enchere : enchereTrieParDate) {
			    int noArticle = enchere.getArticleVendu().getNoArticle();
			    if (!noArticlesUniques.contains(noArticle)) {
			        dernieresEncheresSansDoublon.add(enchere);
			        noArticlesUniques.add(noArticle);
			    }
			}
			return dernieresEncheresSansDoublon;
		}catch(DALException e) {
			e.printStackTrace();
			throw new BLLException("ms_getall");
		}
	}

	@Override
	public List<Enchere> findByNomArticle(String nomArticle) throws BLLException {
		try{
	Comparator<Enchere> dateComparator = Comparator.comparing(Enchere::getDateEnchere);
			
			// Trie de la liste d'enchères par date en ordre décroissant
			List<Enchere> enchereTrieParDate = daoEnchere.findByNomArticle(nomArticle).stream()
			    .sorted(dateComparator.reversed()) 
			    .collect(Collectors.toList());

			//Set pour stocker les numéros d'articles sans doublons
			Set<Integer> noArticlesUniques = new HashSet<>();
			List<Enchere> dernieresEncheresSansDoublon = new ArrayList<>();

			// Parcoure des enchères trié par date et ajout des enchères correspondant aux articles uniques
			for (Enchere enchere : enchereTrieParDate) {
			    int noArticle = enchere.getArticleVendu().getNoArticle();
			    if (!noArticlesUniques.contains(noArticle)) {
			        dernieresEncheresSansDoublon.add(enchere);
			        noArticlesUniques.add(noArticle);
			    }
			}
			return dernieresEncheresSansDoublon;
		}catch(DALException e) {
			e.printStackTrace();
			throw new BLLException("ms_getall");
		}
	}
	


	public List<Enchere> filtrer(List<Enchere> lst, boolean achatEnchereOuverte, boolean achatEnchereEnCours,
			boolean encheresParticipees, boolean encheresGagnees, boolean venteEnchereDebutes,
			boolean VenteEnchereTermines, Integer sessionUser) throws BLLException {
		Stream<Enchere> stream = lst.stream();
		
		if(achatEnchereOuverte) {
			stream = stream.filter(enchere -> enchere.getArticleVendu().getDateDebutEncheres().isBefore(LocalDate.now()));
		}
		if(achatEnchereEnCours) {
			stream = stream.filter(enchere -> enchere.getArticleVendu().getDateDebutEncheres().isBefore(LocalDate.now()) && enchere.getEnchereur().getNoUtilisateur().equals(sessionUser));
		}
		if(encheresParticipees) {
			stream = stream.filter(enchere -> enchere.getArticleVendu().getDateDebutEncheres().isAfter(LocalDate.now()) && enchere.getEnchereur().getNoUtilisateur().equals(sessionUser));
		}
		if(encheresGagnees) {
			stream = stream.filter(enchere -> enchere.getArticleVendu().getDateFinEncheres().isAfter(LocalDate.now()) && enchere.getArticleVendu().getUtilisateur().getNoUtilisateur().equals(sessionUser));
		}
		if(venteEnchereDebutes) {
			stream = stream.filter(enchere ->enchere.getArticleVendu().getDateDebutEncheres().isBefore(LocalDate.now()) && enchere.getArticleVendu().getUtilisateur().getNoUtilisateur().equals(sessionUser));
		}
		if(VenteEnchereTermines) {
			stream = stream.filter(enchere -> enchere.getArticleVendu().getDateFinEncheres().isBefore(LocalDate.now()) && enchere.getArticleVendu().getUtilisateur().getNoUtilisateur().equals(sessionUser));
		}

		return stream.toList();
		
		
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
