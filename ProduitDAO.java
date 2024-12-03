import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {

    private Connection connection;

    public ProduitDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Produit> getAllProduits() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM produits";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                produits.add(new Produit(rs.getInt("id"), rs.getString("nom"), rs.getDouble("prix"), rs.getInt("stock")));
            }
        }
        return produits;
    }

    // Méthodes pour ajouter, supprimer, modifier les produits...
}
