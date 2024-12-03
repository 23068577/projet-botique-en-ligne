import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton viewProductsButton;
    private JButton viewOrdersButton;
    private JButton adminButton;

    public MainFrame() {
        setTitle("Boutique en ligne");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuration du layout
        setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("Bienvenue dans notre Boutique", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Boutons de navigation
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));

        viewProductsButton = new JButton("Voir les Produits");
        viewOrdersButton = new JButton("Mes Commandes");
        adminButton = new JButton("Administrateur");

        buttonPanel.add(viewProductsButton);
        buttonPanel.add(viewOrdersButton);
        buttonPanel.add(adminButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Action pour voir les produits
        viewProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre des produits
                new ProductsFrame().setVisible(true);
                dispose();
            }
        });

        // Action pour voir les commandes
        viewOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre des commandes
                new OrdersFrame().setVisible(true);
                dispose();
            }
        });

        // Action pour l'administration
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre d'administration
                new AdminFrame().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductsFrame extends JFrame {
    private JButton addToCartButton;
    private JButton backButton;

    public ProductsFrame() {
        setTitle("Produits Disponibles");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuration du layout
        setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("Liste des Produits", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Liste des produits (exemple statique pour l'illustration)
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 1)); // Dynamique en fonction du nombre de produits

        // Exemple de produit (à remplacer par une récupération dynamique depuis la base de données)
        List<Product> products = getProducts(); // Cette méthode simule la récupération des produits

        for (Product product : products) {
            JPanel productItem = new JPanel();
            productItem.setLayout(new BorderLayout());
            productItem.add(new JLabel(product.getName()), BorderLayout.NORTH);
            productItem.add(new JLabel("Prix: " + product.getPrice() + " €"), BorderLayout.CENTER);

            addToCartButton = new JButton("Ajouter au Panier");
            productItem.add(addToCartButton, BorderLayout.SOUTH);

            // Action pour ajouter au panier
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ajouter au panier (simuler l'ajout)
                    JOptionPane.showMessageDialog(null, "Produit ajouté au panier !");
                }
            });

            productPanel.add(productItem);
        }

        add(new JScrollPane(productPanel), BorderLayout.CENTER);

        // Bouton de retour
        backButton = new JButton("Retour");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retour à la fenêtre principale
                new MainFrame().setVisible(true);
                dispose();
            }
        });

        add(backButton, BorderLayout.SOUTH);
    }

    private List<Product> getProducts() {
        // Simule la récupération des produits depuis la base de données
        // Remplacer par une vraie logique d'accès aux données
        return List.of(
                new Product("Produit 1", 20.0),
                new Product("Produit 2", 30.0),
                new Product("Produit 3", 50.0)
        );
    }
}
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrdersFrame extends JFrame {
    private JButton backButton;

    public OrdersFrame() {
        setTitle("Mes Commandes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuration du layout
        setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("Mes Commandes", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Exemple de commandes (à remplacer par une récupération dynamique depuis la base de données)
        List<Order> orders = getOrders(); // Cette méthode simule la récupération des commandes

        String[] columnNames = {"ID Commande", "Date", "Statut"};
        Object[][] data = new Object[orders.size()][3];

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            data[i][0] = order.getId();
            data[i][1] = order.getDate();
            data[i][2] = order.getStatus();
        }

        JTable ordersTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(ordersTable);
        add(scrollPane, BorderLayout.CENTER);

        // Bouton de retour
        backButton = new JButton("Retour");
        backButton.addActionListener(e -> {
            new MainFrame().setVisible(true);
            dispose();
        });

        add(backButton, BorderLayout.SOUTH);
    }

    private List<Order> getOrders() {
        // Simule la récupération des commandes depuis la base de données
        // Remplacer par une vraie logique d'accès aux données
        return List.of(
                new Order(1, "2024-12-01", "Livré"),
                new Order(2, "2024-12-02", "En cours"),
                new Order(3, "2024-12-03", "En cours")
        );
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {
    private JButton addProductButton;
    private JButton manageProductsButton;
    private JButton backButton;

    public AdminFrame() {
        setTitle("Administration");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuration du layout
        setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("Administration", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Boutons de gestion
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));

        addProductButton = new JButton("Ajouter un Produit");
        manageProductsButton = new JButton("Gérer les Produits");
        backButton = new JButton("Retour");

        buttonPanel.add(addProductButton);
        buttonPanel.add(manageProductsButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Actions pour l'administration
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ajouter un produit (redirection vers un formulaire)
                JOptionPane.showMessageDialog(null, "Ajouter un produit");
            }
        });

        manageProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
