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
import java.util.List;

import fr.eni.encheres.bll.gestionEncheres.EnchereManager;
import fr.eni.encheres.bll.gestionEncheres.EnchereManagerSing;
import fr.eni.encheres.bll.gestionEncheres.VenteArticleManager;
import fr.eni.encheres.bll.gestionEncheres.VenteArticleManagerSing;
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
				System.out.println("articleVendu:" +" " + articleVendu);
				request.setAttribute("articleVendu", articleVendu);
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
			request.setAttribute("enchere", enchere);
			request.setAttribute("articleVendu", article);
			request.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

};
