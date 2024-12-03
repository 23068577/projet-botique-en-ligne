public class Produit {
    private int id;
    private String nom;
    private double prix;
    private int stock;

    // Constructeur, getters, setters
    public Produit(int id, String nom, double prix, int stock) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
import java.util.Date;
import java.util.List;

public class Commande {
    private int id;
    private Client client;
    private Date date;
    private List<DetailCommande> detailCommandes;

    // Constructeur, getters, setters
    public Commande(int id, Client client, Date date, List<DetailCommande> detailCommandes) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.detailCommandes = detailCommandes;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public List<DetailCommande> getDetailCommandes() { return detailCommandes; }
    public void setDetailCommandes(List<DetailCommande> detailCommandes) { this.detailCommandes = detailCommandes; }
}
public class Client {
    private int id;
    private String nom;
    private String email;
    private String adresse;

    // Constructeur, getters, setters
    public Client(int id, String nom, String email, String adresse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
}
public class DetailCommande {
    private Produit produit;
    private int quantite;

    // Constructeur, getters, setters
    public DetailCommande(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    // Getters et setters
    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}
