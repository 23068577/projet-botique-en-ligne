import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreProduits extends JFrame {
    private JPanel panel;
    private JTable table;
    private JButton btnAjouterPanier;

    public FenetreProduits() {
        setTitle("Produits Disponibles");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new BorderLayout());
        table = new JTable(new ProduitTableModel()); // ProduitTableModel est un modèle de tableau
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        btnAjouterPanier = new JButton("Ajouter au Panier");
        btnAjouterPanier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ajouter au panier
            }
        });
        panel.add(btnAjouterPanier, BorderLayout.SOUTH);

        add(panel);
    }
}
public class FenetreAdminCommandes extends JFrame {
    private JPanel panel;
    private JTable tableCommandes;
    private JButton btnModifierCommande;

    public FenetreAdminCommandes() {
        setTitle("Gestion des Commandes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new BorderLayout());
        tableCommandes = new JTable(new CommandeTableModel()); // CommandeTableModel pour les données
        panel.add(new JScrollPane(tableCommandes), BorderLayout.CENTER);

        btnModifierCommande = new JButton("Modifier Commande");
        btnModifierCommande.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Modifier une commande
            }
        });
        panel.add(btnModifierCommande, BorderLayout.SOUTH);

        add(panel);
    }
}
