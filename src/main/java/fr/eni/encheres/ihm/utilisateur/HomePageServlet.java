package fr.eni.encheres.ihm.utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.categories.CategorieManager;
import fr.eni.encheres.bll.categories.CategorieManagerSing;
import fr.eni.encheres.bll.gestionEncheres.EnchereManager;
import fr.eni.encheres.bll.gestionEncheres.EnchereManagerSing;
import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.Categorie;

public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager managerEnchere = EnchereManagerSing.getInstance();
	private CategorieManager managerCategorie = CategorieManagerSing.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("inscription".equals(action)) {
			inscription(request, response);
		} else if ("login".equals(action)) {
			login(request, response);
		} else if ("deconnexion".equals(action)) {
			deconnexion(request, response);
		} else {
			EnchereModel modelEnchere = new EnchereModel();
			List<Categorie> categorie = new ArrayList<>();
			try {
				categorie = managerCategorie.getAll();
				modelEnchere.setLstEnchere(managerEnchere.getAll());
			} catch (BLLException e) {
				e.printStackTrace();
				modelEnchere.setMessage("zut alors");
			}
			request.setAttribute("categorie", categorie);
			request.setAttribute("modelEnchere", modelEnchere);
			request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Categorie> categorie = new ArrayList<>();
		EnchereModel modelEnchere = new EnchereModel();

		if (request.getParameter("BT_SELECT_CATEGORIE") != null) {
			if (request.getParameter("categorie") != null && request.getParameter("nomArticle").equals("")) {

				try {
					categorie = managerCategorie.getAll();
					modelEnchere.setLstEnchere(
							managerEnchere.findByCategorie(Integer.parseInt(request.getParameter("categorie"))));
				} catch (BLLException e) {
					e.printStackTrace();
					modelEnchere.setMessage("zut alors");
				}

			} else if (!request.getParameter("nomArticle").equals("")) {

				try {
					categorie = managerCategorie.getAll();
					modelEnchere.setLstEnchere(managerEnchere.findByNomArticle(request.getParameter("nomArticle")));
				} catch (BLLException e) {
					e.printStackTrace();
					modelEnchere.setMessage("zut alors");
				}

			} else {

				try {
					categorie = managerCategorie.getAll();
					modelEnchere.setLstEnchere(managerEnchere.getAll());
				} catch (BLLException e) {
					e.printStackTrace();
					modelEnchere.setMessage("zut alors");
				}
			}

			request.setAttribute("categorie", categorie);
			request.setAttribute("modelEnchere", modelEnchere);
			request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
		}

	}

	private void inscription(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/InscriptionForm.jsp").forward(request, response);

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/LoginForm.jsp").forward(request, response);

	}

	private void deconnexion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
	}
}
