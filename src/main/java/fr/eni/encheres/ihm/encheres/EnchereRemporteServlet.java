package fr.eni.encheres.ihm.encheres;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
 * Servlet implementation class EnchereRemporteServlet
 */
public class EnchereRemporteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private VenteArticleManager manager = VenteArticleManagerSing.getInstance();
	private EnchereManager enchereManager = EnchereManagerSing.getInstance();
	
	 private List<Enchere> lstEnchere = new ArrayList<>();
	 private Integer noArticle;
	 private ArticleVendu articleVendu;

    public EnchereRemporteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
			
			System.out.println("articleVendu:" +" " + articleVendu);
			request.setAttribute("articleVendu", articleVendu);
			request.setAttribute("enchere", enchere);
			request.getRequestDispatcher("/WEB-INF/EnchereRemporte.jsp").forward(request, response);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		request.getRequestDispatcher("/WEB-INF/EnchereRemporte.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getAttribute("back");
		request.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);
	}

}
