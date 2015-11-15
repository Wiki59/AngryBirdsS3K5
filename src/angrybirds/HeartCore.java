package angrybirds;

import static angrybirds.Constante.*;
import static angrybirds.menu.Menu.p;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

/**
 * Cette class a pour but de gerer l'avancement du projet grace aux thread comme
 * coeur qui bat.
 */
public class HeartCore implements Runnable, ActionListener {

    javax.swing.Timer t1 = new javax.swing.Timer(2000, this);
    java.util.Timer t2 = new java.util.Timer();

    /**
     * La distance parcourue entre le point(xDepart; yDepart) et la position du
     * pigeon
     */
    private double distanceParcourue;

    /**
     * Point de depart qui permet de calculer la vitesse du pigeon
     */
    private int xDepart, yDepart;

    /**
     * L'animation utilise
     */
    public AnimationOiseau anim;

    /**
     *
     * @param vitesse
     * @param animationOiseau
     */
    public HeartCore(int vitesse, AnimationOiseau animationOiseau) {
        anim = animationOiseau;
        Constante.vitesse = vitesse;
        t2.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("ok");
            }
        }, 0);
    }

    /**
     * Le run avance le x par "step", repaint et attends un peu
     */
    @Override
    public void run() {
        xDepart = bird.getX();
        yDepart = (int) anim.getCourbe().getYenX(bird.getX());
        while (!last) {
            distanceParcourue = anim.getCourbe().distanceEntreDeuxPoints(xDepart, bird.getX(), yDepart, anim.getCourbe().getYenX(bird.getX()));
            bird.setPosX(bird.getPosX() + 1);
            bird.setPosY((int) anim.getCourbe().getYenX(bird.getPosX()));
            bird.setA(anim.getCourbe().angleNextD(bird.getPosX()));
            if (distanceParcourue > vitesse) {
                refresh(false);
            }
        }
        refresh(true);
        t1.setRepeats(false);
        t1.start();
    }

    /**
     * Refresh l'animation du jeu
     */
    public void refresh(boolean impact) {
        /* Pour d'obscures raisons, quand l'oiseau n'a pas quitté les premiers
         pixels, le repaint bug */
        if (bird.getBirdCenterX() < 100) {
            anim.repaint();
        } else {
            int x1 = bird.getPosX() - 10 - vitesse;
            int x2 = bird.getBirdCenterX() + 10 + vitesse;
            int y1 = bird.getPosY() - 10 - vitesse;
            int y2 = bird.getBirdCenterY() + 10 + vitesse;
            x1 = (x1 > 0) ? x1 : 0;
            x2 = (x2 > 0) ? x2 : 0;
            y2 = (y2 > 0) ? y2 : 0;
            y1 = (y1 > 0) ? y1 : 0;
            anim.repaint(x1, y1, x2, y2);
            if (impact && entityHitty != 0) {
                x1 = obstacle.get(entityHitty).getX();
                x2 = obstacle.get(entityHitty).getX() + obstacle.get(entityHitty).getWeight();
                y1 = obstacle.get(entityHitty).getY();
                y2 = obstacle.get(entityHitty).getY() + obstacle.get(entityHitty).getHight();
                x1 = (x1 > 0) ? x1 : 0;
                x2 = (x2 > 0) ? x2 : 0;
                y2 = (y2 > 0) ? y2 : 0;
                y1 = (y1 > 0) ? y1 : 0;
                anim.repaint(x1, y1, x2, y2);
            }
        }
        xDepart = bird.getX();
        yDepart = (int) anim.getCourbe().getYenX(bird.getX());
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            System.out.println("Fred a un soucis de sommeil:(\n" + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == t1) {
            gf.dispose();
            p.run();
        }
    }
}
