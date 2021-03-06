package angrybirds.option;

import static angrybirds.Constante.footstep;
import static angrybirds.Constante.indexPFAGUtilise;
import angrybirds.menu.FenetrePrincipale;
import entites.bird.Footstep;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import static ressource.PFAGReader.listePFAG;

public class Option extends GridPane implements EventHandler<ActionEvent> {

    FenetrePrincipale root;
    VBox vb = new VBox();
    VBox vb2 = new VBox();
    Text option = new Text("Option");
    Text choiseMap = new Text("Choisissez votre map");
    ChoiceBox<String> cb1 = new ChoiceBox<>(FXCollections.observableArrayList(listePFAG()));
    Text choiseFootstep = new Text("Choisissez votre empreinte");
    ChoiceBox<String> cb2 = new ChoiceBox<>(FXCollections.observableArrayList(Footstep.getListFootstep()));

    GridPane hb1 = new GridPane();
    GridPane hb2 = new GridPane();
    BoutonOption back = new BoutonOption("Retour au menu");
    BoutonOption apply = new BoutonOption("Appliquer");

    SeparatorVDeux s1 = new SeparatorVDeux("Diver");
    Separator s2 = new Separator();

    public Option(FenetrePrincipale root) {
        
        s1.setOnAction(this);

        this.root = root;
        setAlignment(Pos.CENTER);
        option.getStyleClass().add("textOption1");
        choiseMap.getStyleClass().add("textOption2");
        choiseFootstep.getStyleClass().add("textOption2");

        vb.setMinSize(800, 666);
        vb.setAlignment(Pos.TOP_CENTER);
        vb2.setAlignment(Pos.TOP_CENTER);
        vb.setBackground(new Background(new BackgroundImage(new Image("ressource/papyrus.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        back.setOnAction(this);
        apply.setOnAction(this);
        hb1.setAlignment(Pos.CENTER_LEFT);
        hb1.add(back, 0, 0);
        hb2.setAlignment(Pos.CENTER);
        hb2.add(apply, 0, 0);

        cb1.setValue(listePFAG().get(indexPFAGUtilise));
        String fsUse = footstep.toString();
        int indexFsUse = Footstep.getListFootstep().indexOf(fsUse);
        cb2.setValue(Footstep.getListFootstep().get(indexFsUse));

        vb2.getChildren().addAll(choiseMap, cb1, choiseFootstep, cb2);
        vb.getChildren().addAll(option, s1, vb2, s2, hb2, hb1);
        add(vb, 1, 1);

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == apply) {
            indexPFAGUtilise = listePFAG().indexOf(cb1.getValue());
            footstep = Footstep.valueOf(cb2.getValue());
        }
        if (event.getSource() == back) {
            root.changeScene(1);
        }
        if (event.getSource() == s1.b) {
            if (vb.getChildren().contains(vb2)) {
                s1.indexHided = vb.getChildren().indexOf(vb2);
                vb.getChildren().remove(s1.indexHided);
            } else {
                vb.getChildren().add(s1.indexHided, vb2);
            }
        }
    }

}
