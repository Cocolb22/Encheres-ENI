package fr.eni.encheres.ihm.encheres;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.eni.encheres.bll.gestionEncheres.EnchereManager;
import fr.eni.encheres.bll.gestionEncheres.EnchereManagerSing;
import fr.eni.encheres.bll.gestionEncheres.VenteArticleManager;
import fr.eni.encheres.bll.gestionEncheres.VenteArticleManagerSing;
import fr.eni.encheres.bll.gestionUtilisateurs.UtilisateurManager;
import fr.eni.encheres.bll.gestionUtilisateurs.UtilisateurManagerSing;
import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bo.model.Enchere;
import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.bundles.BusinessException;

/**
 * Servlet implementation class DetailVenteServlet
 */
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VenteArticleManager manager = VenteArticleManagerSing.getInstance();
	private EnchereManager enchereManager = EnchereManagerSing.getInstance();
	private UtilisateurManager userManager = UtilisateurManagerSing.getInstance();
	
	 private List<Enchere> lstEnchere = new ArrayList<>();
	 private Integer noArticle;
	 private ArticleVendu articleVendu;
	
	
    public DetailVenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		noArticle = Integer.parseInt(request.getParameter("noArticle"));
		
		try {
			lstEnchere = enchereManager.getAll();
			System.out.println("id:" +" " + noArticle + " "  + lstEnchere);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				articleVendu = manager.getArticleById(noArticle);
				List<Enchere> encheresArticle;
				Enchere enchere=null;
				try {
					encheresArticle = enchereManager.findByNomArticle(articleVendu.getNomArticle());
					Collections.sort(encheresArticle, (e1, e2) -> e1.getMontantEnchere().compareTo(e2.getMontantEnchere()));
					enchere = encheresArticle.get(0);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(enchere);
				System.out.println("articleVendu:" +" " + articleVendu);
				request.setAttribute("articleVendu", articleVendu);
				request.setAttribute("enchere", enchere);
				System.out.println(enchere.getEnchereur().getPseudo());
				request.setAttribute("meilleurEncherisseur", enchere.getEnchereur().getPseudo());
				request.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getAttribute("articleVendu");
		if(request.getParameter("encherir") != null) {
			encherir(request, response, articleVendu);
		}
	}
	
	private void encherir(HttpServletRequest request, HttpServletResponse response, ArticleVendu article) throws ServletException, IOException {
		Integer proposition = Integer.parseInt(request.getParameter("propositionPrix"));
		HttpSession session = request.getSession();
		Utilisateur utilisateurInscrit = (Utilisateur) session.getAttribute("utilisateurInscrit");

		try {
			Enchere enchere = new Enchere(utilisateurInscrit, article, LocalDateTime.now(), proposition);
			enchereManager.addEnchere(enchere);
			
			Integer meilleureOffre = enchereManager.getMontantMax(article);
			enchere.setMontantEnchere(meilleureOffre);
			
			utilisateurInscrit.setCredit(utilisateurInscrit.getCredit()-proposition);
			userManager.updateUtilisateur(utilisateurInscrit, utilisateurInscrit.getMotDePasse());
			
			List<Enchere> encheresArticle = enchereManager.findByNomArticle(enchere.getArticleVendu().getNomArticle());
			
			if(encheresArticle.size() > 1) {
				Collections.sort(encheresArticle, (e1, e2) -> e1.getMontantEnchere().compareTo(e2.getMontantEnchere()));
				Enchere encherePrecedente = encheresArticle.get(1);
				Utilisateur enchereur = encherePrecedente.getEnchereur();
				enchereur.setCredit(enchereur.getCredit() + enchere.getMontantEnchere());
				try {
					userManager.updateUtilisateur(enchereur, enchereur.getMotDePasse());
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			request.setAttribute("enchere", enchere);
			request.setAttribute("meilleurEncherisseur", enchere.getEnchereur().getPseudo());
			request.setAttribute("articleVendu", article);
			request.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);
		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("erreurMessage", e.getMessage());
			doGet(request, response);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

};
