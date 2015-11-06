package angrybirds;

import javax.swing.JFrame;

/**
 * La fenetre du jeu
 */
public class GameFrame extends JFrame {

    /**
     * Le jeu sous panel
     */
    private AnimationOiseau jeu;

    /**
     *
     * @param serieNum
     * @param totalSeries
     * @param c
     */
    public GameFrame(int serieNum, int totalSeries, Courbe c) {
        jeu = new AnimationOiseau(c);
        setDefaultCloseOperation(3);
        setResizable(false);
        setContentPane(jeu);
        setSize(Constante.fenetre.getSize());
        setLocationRelativeTo(null);
        setVisible(true);

        setTitle("Angry Birds - Essai n° : " + serieNum + "/" + totalSeries);
        jeu.start();
    }
}
