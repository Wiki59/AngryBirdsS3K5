package angrybirds;

import javax.swing.JFrame;

/**
 * La fenetre du jeu
 */
public class GameFrame extends JFrame {
    /**
     * Le jeu sous panel
     */
    private AnimationOiseau jeu = new AnimationOiseau(0.0005, -0.5, 450);

    {
        setSize(Constante.fenetre.getSize());
        setDefaultCloseOperation(3);
        setContentPane(jeu);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     *
     * @param totalSeries
     */
    public GameFrame(int serieNum, int totalSeries) {
        setTitle("Angry Birds - Essai n° : " + serieNum + "/" + totalSeries);
        jeu.start();
    }
}