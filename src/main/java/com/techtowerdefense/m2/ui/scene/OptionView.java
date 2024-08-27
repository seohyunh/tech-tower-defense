package com.techtowerdefense.m2.ui.scene;

import com.almasb.fxgl.ui.UI;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class OptionView extends Parent {

    // public getter for playername and difficulty
    public String getPlayernamestring() {
        return playernamestring;
    }

    public String getDifficulty() {
        return difficulty.getValue().toString();
    }

    // public check null difficulty
    public Boolean isDifficultynull() {
        return difficulty.getValue() == null;
    }

    // public check null playernamestring
    public Boolean isplayernamestringnull() {
        return playernamestring == null || playernamestring.isEmpty();
    }

    // Create a String for playernamestring
    private String playernamestring;

    // Create a combo box for difficulty
    private String[] difficultylist = {"Easy", "Less easy", "Way less easy" };

    private ComboBox difficulty =
            new ComboBox(FXCollections.observableArrayList(difficultylist));

    // rendering options
    public OptionView() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Indicate difficulty
        Label playernameshow = new Label("Player Name");
        playernameshow.setTranslateX(60);
        grid.setConstraints(playernameshow, 0, 0);
        playernameshow.setStyle("-fx-font-size: 14.5");
        grid.getChildren().add(playernameshow);

        //Defining the Name text field
        final TextField playername = new TextField();
        playername.setPromptText("Enter player name.");
        playername.setPrefColumnCount(10);

        grid.setConstraints(playername, 0, 1);
        grid.getChildren().add(playername);

        //Defining the Submit button to set playername
        Button submit = new Button("Submit");

        grid.setConstraints(submit, 1, 1);
        grid.getChildren().add(submit);

        // set playername
        final Label label = new Label();
        grid.setConstraints(label, 0, 2);
        grid.getChildren().add(label);
        setPlayername(submit, label, playername);

        //Create Hbox for textfield and submit button
        var nameHbox = new HBox(2, playername, submit);



        // Indicate difficulty
        Label difficultytext = new Label("Difficulty");
        difficultytext.setTranslateX(60);
        difficultytext.setStyle("-fx-font-weight: bold; -fx-font-size: 14.5");

        // Label to display the selected difficulty
        Label selected = new Label("choose a difficulty");
        setDifficulty(selected);

        // Create a difficulty pane
        var difficultypane = new VBox(6, playernameshow, nameHbox, label, difficultytext, difficulty, selected);
        grid.setConstraints(difficultypane, 0, 3);
        grid.getChildren().add(difficultypane);
        difficultypane.setScaleY(1.5);
        difficultypane.setScaleX(1.5);
        difficultypane.setTranslateY(120);

        // add the optionview grid
        getChildren().add(grid);
    }

    public static Boolean isPlayerNameNull(TextField playername) {
        if (playername == null) {
            return true;
        }
        return isPlayerNameNull(playername.getText());
    }

    public static Boolean isPlayerNameNull(String playernamestring) {
        if (playernamestring == null) {
            return true;
        }
        return false;
    }
    
    public static Boolean checkOnlySpace(String playerName) {
        if (playerName.isBlank()) {
            return true;
        }
        return false;
    }

    private void setPlayername(Button submit, Label label, TextField playername) {
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                listenForPlayername(label, playername);
            }
        });
    }

    private void setDifficulty(Label selected) {
        difficulty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selected.setText(difficulty.getValue() + " selected");
            }
        });
    }

    private void listenForPlayername(Label label, TextField playername) {
        if (!isPlayerNameNull(playername)) {
            String tempstring = playername.getText();
            tempstring.trim();
            playernamestring = tempstring;
            label.setText(playernamestring + " "
                    + "is set to be your player name!");
        } else {
            playernamestring = "";
            label.setText("You have not left a player name.");
        }
    }


}
