<?php
// config.php - Connexion à la base de données
$host = 'localhost'; // Adresse du serveur MySQL
$dbname = 'boutique'; // Nom de la base de données
$username = 'root'; // Utilisateur MySQL
$password = ''; // Mot de passe MySQL

try {
    $pdo = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Erreur de connexion : " . $e->getMessage());
}
?>
<?php
// index.php - Affichage des produits
include('config.php');

// Récupérer les produits depuis la base de données
$query = "SELECT * FROM produits";
$stmt = $pdo->prepare($query);
$stmt->execute();
$produits = $stmt->fetchAll(PDO::FETCH_ASSOC);
?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produits Disponibles</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Bienvenue dans notre Boutique</h1>
        <nav>
            <a href="panier.php">Panier</a>
            <a href="commandes.php">Mes Commandes</a>
        </nav>
    </header>

    <main>
        <h2>Produits Disponibles</h2>
        <div class="produits">
            <?php foreach ($produits as $produit): ?>
            <div class="produit">
                <img src="images/<?php echo $produit['id']; ?>.jpg" alt="<?php echo $produit['nom']; ?>">
                <h3><?php echo $produit['nom']; ?></h3>
                <p>Prix: <?php echo number_format($produit['prix'], 2, ',', ' '); ?> €</p>
                <p>Stock: <?php echo $produit['stock']; ?></p>
                <form action="panier.php" method="post">
                    <input type="hidden" name="produit_id" value="<?php echo $produit['id']; ?>">
                    <button type="submit">Ajouter au Panier</button>
                </form>
            </div>
            <?php endforeach; ?>
        </div>
    </main>

    <footer>
        <p>&copy; 2024 Boutique en ligne</p>
    </footer>
</body>
</html>
<?php
// panier.php - Gestion du panier
session_start();
include('config.php');

// Vérifier si un produit a été ajouté au panier
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $produit_id = $_POST['produit_id'];

    // Récupérer les détails du produit
    $query = "SELECT * FROM produits WHERE id = :id";
    $stmt = $pdo->prepare($query);
    $stmt->execute(['id' => $produit_id]);
    $produit = $stmt->fetch(PDO::FETCH_ASSOC);

    // Ajouter le produit au panier
    if ($produit) {
        $_SESSION['panier'][] = $produit;
    }
}

// Afficher le panier
$panier = isset($_SESSION['panier']) ? $_SESSION['panier'] : [];

?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Panier</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Mon Panier</h1>
        <nav>
            <a href="index.php">Retour aux Produits</a>
            <a href="commandes.php">Mes Commandes</a>
        </nav>
    </header>

    <main>
        <h2>Articles dans votre Panier</h2>
        <?php if (empty($panier)): ?>
            <p>Votre panier est vide.</p>
        <?php else: ?>
            <ul>
                <?php foreach ($panier as $produit): ?>
                    <li>
                        <?php echo $produit['nom']; ?> - <?php echo number_format($produit['prix'], 2, ',', ' '); ?> €
                    </li>
                <?php endforeach; ?>
            </ul>
            <form action="validation_commande.php" method="post">
                <button type="submit">Valider la Commande</button>
            </form>
        <?php endif; ?>
    </main>

    <footer>
        <p>&copy; 2024 Boutique en ligne</p>
    </footer>
</body>
</html>
<?php
// validation_commande.php - Validation de la commande
session_start();
include('config.php');

// Vérifier si le panier est vide
if (empty($_SESSION['panier'])) {
    die("Votre panier est vide.");
}

// Vérifier si l'utilisateur est connecté (ici, un exemple sans gestion des sessions utilisateur, à personnaliser)
if (!isset($_SESSION['client_id'])) {
    die("Veuillez vous connecter pour passer une commande.");
}

// Enregistrer la commande dans la base de données
$query = "INSERT INTO commandes (client_id) VALUES (:client_id)";
$stmt = $pdo->prepare($query);
$stmt->execute(['client_id' => $_SESSION['client_id']]);

// Récupérer l'ID de la commande
$commande_id = $pdo->lastInsertId();

// Enregistrer les détails de la commande
foreach ($_SESSION['panier'] as $produit) {
    $query = "INSERT INTO details_commande (commande_id, produit_id, quantite, prix) VALUES (:commande_id, :produit_id, 1, :prix)";
    $stmt = $pdo->prepare($query);
    $stmt->execute([
        'commande_id' => $commande_id,
        'produit_id' => $produit['id'],
        'prix' => $produit['prix']
    ]);
}

// Vider le panier après la commande
unset($_SESSION['panier']);

echo "Commande passée avec succès!";
?>
<?php
// commandes.php - Consultation des commandes
session_start();
include('config.php');

// Vérifier si l'utilisateur est connecté
if (!isset($_SESSION['client_id'])) {
    die("Veuillez vous connecter pour consulter vos commandes.");
}

// Récupérer les commandes de l'utilisateur
$query = "SELECT * FROM commandes WHERE client_id = :client_id";
$stmt = $pdo->prepare($query);
$stmt->execute(['client_id' => $_SESSION['client_id']]);
$commandes = $stmt->fetchAll(PDO::FETCH_ASSOC);
?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta
