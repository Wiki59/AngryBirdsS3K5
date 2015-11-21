package angrybirds;

import java.awt.Graphics;
import static angrybirds.Constante.*;
import static angrybirds.HeartCore.t;
import entites.Collision;
import javax.swing.JPanel;

/**
 * La classe principale du programme la ou se consentre aussi le visualisateur
 * du jeu, le moteur de collision, le thread principal du jeu et la courbe
 * engage par l'oiseau
 */
public class AnimationJeu extends JPanel {

    /**
     * Le visualisateur
     */
    private Visualisateur visu;

    /**
     * Gestionnaire de collision
     */
    private Collision stun;

    /**
     * Thread de l'animation
     */
    private Thread core;

    /**
     * Le constructeur est en prive pour ne pas pouvoir l'appeler
     */
    public AnimationJeu() {
    }

    {
        setDoubleBuffered(true); // Un bel affichage en HD
        visu = new Visualisateur(); // Gestionnaire d'affichage
        stun = new Collision(this); // Gestionnaire de collision
        core = new HeartCore(5, this); // Gestionnaire d'evenement
    }

    /**
     * Demmarrage de l'animation
     */
    public final void start() {
        core.start();
    }

    /**
     * Le painteur du jeu, simplifie grace au visu'
     *
     * @param g Le graphics sur le quelle dessiner
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        addFootstepCoord();
        g = visu.drawAllNeed(g);
        g = visu.drawAllHitBox(g);
        stun.verif();
    }

    /**
     * Fonction qui ajoute les coordonnes actuel a une liste
     */
    private void addFootstepCoord() {
        footstepX.add(bird.getPosX());
        footstepY.add(bird.getPosY() + bird.getR());
        footstepA.add(bird.getCourbe().angleAenT(t));
    }
    
    /**
     * Retourne le thread du jeu
     *
     * @return Le thread du jeu
     */
    public Thread getCore() {
        return core;
    }
}