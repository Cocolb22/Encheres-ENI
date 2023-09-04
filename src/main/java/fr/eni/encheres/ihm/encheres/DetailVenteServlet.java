package fr.eni.encheres.ihm.encheres;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.gestionEncheres.EnchereManager;
import fr.eni.encheres.bll.gestionEncheres.EnchereManagerSing;
import fr.eni.encheres.bll.gestionEncheres.VenteArticleManager;
import fr.eni.encheres.bll.gestionEncheres.VenteArticleManagerSing;
import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.ArticleVendu;
import fr.eni.encheres.bo.model.Enchere;
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}