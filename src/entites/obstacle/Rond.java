package entites.obstacle;

import angrybirds.Courbe;
import entites.Skin;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Objet permettant de faire des obstacles en forme de rond
 */
public class Rond extends Obstacle {

    /**
     * Dessine un rond
     *
     * @param hight
     * @param widht
     * @param x
     * @param y
     * @param c
     */
    public Rond(int x, int y, int hight, int widht, Courbe crb, Color c) {
        super(x, y, hight, widht, crb, c);
    }

    @Override
    public Skin draw(Skin s) {
        Graphics g = s.getG();
        g.setColor(c);
        g.fillOval(x, y, widht, hight);
        s.setG(g);
        return s;
    }
}
