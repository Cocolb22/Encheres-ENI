package fr.eni.encheres.ihm.utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import fr.eni.encheres.bll.gestionEncheres.EnchereException;
import fr.eni.encheres.bll.gestionEncheres.EnchereManager;
import fr.eni.encheres.bll.gestionEncheres.EnchereManagerSing;
import fr.eni.encheres.bo.model.Enchere;


public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager manager = EnchereManagerSing.getInstance();
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String action = request.getParameter("action");
        
        if ("inscription".equals(action)) {
            inscription(request, response);
        } else if ("login".equals(action)) {
            login(request, response);
        } else if ("deconnexion".equals(action)) {
            deconnexion(request, response);
        } else { // Si l'action n'est pas "inscription", rediriger vers Home.jsp
        	EnchereModel model = new EnchereModel();
            try {
            	model.setLstEnchere(manager.getAll());
            }catch(EnchereException e){
            	e.printStackTrace();
            	model.setMessage("zut alors");
            }
            request.setAttribute("model", model);
        	request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereModel model = (EnchereModel) request.getSession().getAttribute("model");
		try {
//			Enchere enchere = new Enchere();
//			model.setCurrentEnchere(enchere);
			model.setLstEnchere(manager.getAll());
		} catch (EnchereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
		
	}

	private void inscription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/InscriptionForm.jsp").forward(request, response);
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/LoginForm.jsp").forward(request, response);
		
	}
	
	private void deconnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getSession().invalidate();
    	request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
	}
}
