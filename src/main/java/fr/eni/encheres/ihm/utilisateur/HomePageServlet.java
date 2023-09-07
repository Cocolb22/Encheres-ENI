package fr.eni.encheres.ihm.utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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
import fr.eni.encheres.bll.gestionUtilisateurs.UtilisateurManager;
import fr.eni.encheres.bll.gestionUtilisateurs.UtilisateurManagerSing;
import fr.eni.encheres.bll.util.BLLException;
import fr.eni.encheres.bo.model.Categorie;
import fr.eni.encheres.bo.model.Enchere;
import fr.eni.encheres.bo.model.Utilisateur;
import fr.eni.encheres.bundles.BusinessException;

public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager managerEnchere = EnchereManagerSing.getInstance();
	private CategorieManager managerCategorie = CategorieManagerSing.getInstance();
	private UtilisateurManager utilisateurManager = UtilisateurManagerSing.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String action = request.getParameter("action");
       
        if ("inscription".equals(action)) {
            inscription(request, response);
        } else if ("login".equals(action)) {
            login(request, response);
        } else if ("deconnexion".equals(action)) {
            deconnexion(request, response);
        } else {
        	
        	Cookie[] cookies = request.getCookies();
            
        	if(cookies != null) {
        	   for(Cookie cookie : cookies) {
        	        if(cookie.getName().equals("rememberMe")) {
        	            String rememberMeValue = cookie.getValue();
        	            Utilisateur utilisateur;
						try {
							utilisateur = utilisateurManager.checkIdUser(Integer.parseInt(rememberMeValue));
							request.getSession().setAttribute("user", utilisateur);
						} catch (NumberFormatException | BusinessException e) {
							e.printStackTrace();
						}
        	        }
        	    }
        	 }
        	
        	EnchereModel modelEnchere = new EnchereModel();
        	List<Categorie> categorie = new ArrayList<>();
        	try {
        		categorie = managerCategorie.getAll();;
        		modelEnchere.setLstEnchere(managerEnchere.getAll());
            }catch(BLLException e){
            	e.printStackTrace();
            	modelEnchere.setMessage("zut alors");
            }
        	request.setAttribute("categorie", categorie);
            request.setAttribute("modelEnchere", modelEnchere);
        	request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("categorie"));
		
    	List<Categorie> categories = new ArrayList<>();
    	EnchereModel modelEnchere = new EnchereModel();
    	Utilisateur sessionUser = ((Utilisateur) request.getSession().getAttribute("utilisateurInscrit"));
    	Integer categorieSelectionnee = 0;
    	if(request.getParameter("BT_SELECT_CATEGORIE") != null) {
    		if(request.getParameter("nomArticle").equals("") && !request.getParameter("categorie").equals("0")) {
	            try {
	            	categories = managerCategorie.getAll();	            
	        		categorieSelectionnee = request.getParameter("categorie") != null ? Integer.parseInt(request.getParameter("categorie")): 0;
	            	if(request.getSession().getAttribute("utilisateurInscrit") != null) {
	            	
	            	modelEnchere.setLstEnchere(managerEnchere.filtrer(managerEnchere.findByCategorie(Integer.parseInt(request.getParameter("categorie"))),
	            			Boolean.parseBoolean(request.getParameter("achatEnchereOuverte")),
	            			Boolean.parseBoolean(request.getParameter("achatEnchereEnCours")),
	            			Boolean.parseBoolean(request.getParameter("achatEnchereRemportées")),
	            			Boolean.parseBoolean(request.getParameter("venteEnchereEnCours")),
	            			Boolean.parseBoolean(request.getParameter("venteEnchereDebutes")),
	            			Boolean.parseBoolean(request.getParameter("VenteEnchereTermines")),
	            			sessionUser.getNoUtilisateur()));
	            	}else {
	            		modelEnchere.setLstEnchere(managerEnchere.findByCategorie(Integer.parseInt(request.getParameter("categorie"))));
	            	}
	            	
	            }catch(BLLException e){
	            	e.printStackTrace();
	            	modelEnchere.setMessage("zut alors");
	            }
	            
    		}else if(!request.getParameter("nomArticle").equals("") ) {
    		
	    		try {
	            	categories = managerCategorie.getAll();
	            	
	            	if(request.getSession().getAttribute("utilisateurInscrit") != null) {
	            	modelEnchere.setLstEnchere(managerEnchere.filtrer(managerEnchere.findByNomArticle(request.getParameter("nomArticle")),
	            			Boolean.parseBoolean(request.getParameter("achatEnchereOuverte")),
	            			Boolean.parseBoolean(request.getParameter("achatEnchereEnCours")),
	            			Boolean.parseBoolean(request.getParameter("achatEnchereRemportées")),
	            			Boolean.parseBoolean(request.getParameter("venteEnchereEnCours")),
	            			Boolean.parseBoolean(request.getParameter("venteEnchereDebutes")),
	            			Boolean.parseBoolean(request.getParameter("VenteEnchereTermines")),
	            			sessionUser.getNoUtilisateur()));
	            	}else {
	            		modelEnchere.setLstEnchere(managerEnchere.findByNomArticle(request.getParameter("nomArticle")));
	            	}
	            }catch(BLLException e){
	            	e.printStackTrace();
	            	modelEnchere.setMessage("zut alors");
	            }
    		
    		}else if(request.getParameter("categorie").equals("0") && request.getParameter("nomArticle").equals("")){
	    		try {
		            	categories = managerCategorie.getAll();
		        		categorieSelectionnee = request.getParameter("categorie") != null ? Integer.parseInt(request.getParameter("categorie")): 0;
		            	if(request.getSession().getAttribute("utilisateurInscrit") != null) {
		            	modelEnchere.setLstEnchere(managerEnchere.filtrer(managerEnchere.getAll(),
		            			Boolean.parseBoolean(request.getParameter("achatEnchereOuverte")),
		            			Boolean.parseBoolean(request.getParameter("achatEnchereEnCours")),
		            			Boolean.parseBoolean(request.getParameter("achatEnchereRemportées")),
		            			Boolean.parseBoolean(request.getParameter("venteEnchereEnCours")),
		            			Boolean.parseBoolean(request.getParameter("venteEnchereDebutes")),
		            			Boolean.parseBoolean(request.getParameter("VenteEnchereTermines")),
		            			sessionUser.getNoUtilisateur()));
		            	}else {
		            		modelEnchere.setLstEnchere(managerEnchere.getAll());
		            	}
	            	
	            }catch(BLLException e){
	            	e.printStackTrace();
	            	modelEnchere.setMessage("zut alors");
	            }
    		}
    		categorieSelectionnee = request.getParameter("categorie") != null ? Integer.parseInt(request.getParameter("categorie")): 0;
    		request.setAttribute("categorieSelectionnee", categorieSelectionnee);
	        request.setAttribute("categorie", categories);
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

	private void deconnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("rememberMe")) {
					cookie.setMaxAge(0);
				}
			}
		}
		request.getSession().invalidate();
		
		List<Categorie> categorie = new ArrayList<>();
		EnchereModel modelEnchere = new EnchereModel();
		try {
		    categorie = managerCategorie.getAll();
		    modelEnchere.setLstEnchere(managerEnchere.getAll());
		} catch (BLLException e) {
		    e.printStackTrace();
		    modelEnchere.setMessage("Erreur lors de la récupération des enchères.");
		}
		request.setAttribute("categorie", categorie);
		request.setAttribute("modelEnchere", modelEnchere);
		request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);

	}
}
