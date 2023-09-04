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
import fr.eni.encheres.bo.model.Enchere;

/**
 * Servlet implementation class DetailVenteServlet
 */
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EnchereManager manager = EnchereManagerSing.getInstance();
	
	 private List<Enchere> lstEnchere = new ArrayList<>();
	
	
    public DetailVenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
