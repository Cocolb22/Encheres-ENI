package fr.eni.encheres.dal.gestionEncheres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.model.Enchere;
import fr.eni.encheres.dal.util.ConnectionProvider;
import fr.eni.encheres.dal.util.DALException;

public class EnchereDAOImpl implements EnchereDAO {

    final String SELECT = "SELECT noEnchere, noUtilisateur, noArticle, dateEnchere, montantEnchere FROM Enchere";
    final String INSERT = "INSERT INTO Enchere (noUtilisateur, noArticle, dateEnchere, montantEnchere) VALUES (?, ?, ?, ?)";

    @Override
    public void insert(Enchere enchere) throws DALException {
        try (Connection con = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, enchere.getnoUtilisateur());
            stmt.setInt(2, enchere.getnoArticle());
            stmt.setTimestamp(3, Timestamp.valueOf(enchere.getdateEnchere()));
            stmt.setInt(4, enchere.getmontantEnchere());
            int nb = stmt.executeUpdate();
            if (nb > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    enchere.setnoEnchere(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DALException("Erreur lors de l'insertion de l'enchère.", e);
        }
    }

    @Override
    public List<Enchere> getAll() throws DALException {
        List<Enchere> result = new ArrayList<>();

        try (Connection con = ConnectionProvider.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(SELECT);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Enchere enchere = new Enchere(
                        rs.getInt("noUtilisateur"),
                        rs.getInt("noArticle"),
                        rs.getTimestamp("dateEnchere").toLocalDateTime(),
                        rs.getInt("montantEnchere"));
                enchere.setnoEnchere(rs.getInt("noEnchere"));
                result.add(enchere);
            }
        } catch (SQLException e) {
            throw new DALException("Erreur lors de la récupération des enchères.", e);
        }
        return result;
    }
}

