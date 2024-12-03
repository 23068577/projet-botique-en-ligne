import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {

    private Connection connection;

    public CommandeDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Commande> getAllCommandes() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String query = "SELECT * FROM commandes";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                commandes.add(new Commande(rs.getInt("id"), new Client(rs.getInt("client_id"), "", "", ""),
                        rs.getDate("date"), null));  // Détails à charger plus tard
            }
        }
        return commandes;
    }

    // Méthodes pour ajouter, supprimer, et modifier les commandes...
}
window.onload = function() {
    displayProducts();
};
