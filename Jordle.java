package com.example.jordle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.robot.Robot;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.application.Platform;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;


public class  Jordle extends Application{
    Button reset;
    Button close;
    Button instr;
    Button Restartbtn;
    Stage dark;
    Button darkbtn;
    int sum = 0;

    boolean bb;
    Text t1;

    TextField tb00, tb10, tb20, tb30, tb40,
            tb01, tb11, tb21, tb31, tb41,
            tb02, tb12, tb22, tb32, tb42,
            tb03, tb13, tb23, tb33, tb43,
            tb04, tb14, tb24, tb34, tb44,
            tb05, tb15, tb25, tb35, tb45;

    TextField[] textFields = {tb00, tb10, tb20, tb30, tb40,
            tb01, tb11, tb21, tb31, tb41,
            tb02, tb12, tb22, tb32, tb42,
            tb03, tb13, tb23, tb33, tb43,
            tb04, tb14, tb24, tb34, tb44,
            tb05, tb15, tb25, tb35, tb45};

    String s00, s10, s20, s30, s40,
            s01, s11, s21, s31, s41,
            s02, s12, s22, s32, s42,
            s03, s13, s23, s33, s43,
            s04, s14, s24, s34, s44,
            s05, s15, s25, s35, s45;

    Label l1;

    Stage window;
    Window w1;
    Stage instruction;
    Scene game;
    Scene WScreen;
    Scene instructions;
    Scene darkscn;

    int ans[] = new int[5];

    char[] full = new char[30];

    char c00;

    String correct;

    BorderPane bPain;
    StackPane sPain;
    GridPane gPain;

    int count = 0;
    int abc = 1;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        window = mainStage;
        mainStage.setTitle("Jordle");

        bPain = new BorderPane();
        //sPain = new StackPane();

        gPain = new GridPane();
        //sPain = new StackPane(bPain, gPain);
        //sPain = new StackPane(bPain, gPain);
        gPain.setPadding(new Insets(50,50,50,50));
        gPain.setVgap(5);
        gPain.setHgap(5);

        //game = new Scene(bPain, 500, 500);
        //instructions = new Scene(bPain, 300, 300);


        Restartbtn = new Button("Restart");
        Restartbtn.setOnAction(e->{
           for (int rows = 0; rows < 30; rows++) {
               textFields[rows].setText("");
               Paint color5 = Paint.valueOf("FFFFFF");
               textFields[rows].setStyle("-fx-control-inner-background: #"+color5.toString().substring(2));
               textFields[rows].setFont(Font.font("Georgia", 16));
               textFields[rows].setAlignment(Pos.CENTER);
               textFields[rows].setMaxSize(40, 40);
               textFields[rows].setMinSize(40,40);
               //gPain.getChildren().add(textFields[rows]);
           }
            Random rand1 = new Random();
            int randWord1 = rand1.nextInt(Words.list.size());
            System.out.println(randWord1);
            correct = Words.list.get(randWord1);
            correct = correct.toUpperCase();
            System.out.println(correct);
//            BorderPane.setAlignment(gPain, Pos.CENTER);
//            BorderPane.setMargin(gPain, new Insets(20,20,50,90)); // optional
//            bPain.setCenter(gPain);
        });



        darkbtn = new Button("Dark Mode");
        darkbtn.setOnAction(e -> {
            t1.setStyle(String.valueOf(Color.RED));
                Background background = new Background(new BackgroundFill(Paint.valueOf("3a3a3c"), null, null));
            //Background background1 = new Background(new BackgroundFill(Paint.valueOf("3a3a3c"), null, null));

            bPain.setBackground(background);
            //gPain.setBackground(background1);


        });


        t1 = new Text("Jordle");
        t1.setFont(Font.font("Georgia", 36));
        BorderPane.setAlignment(t1, Pos.TOP_CENTER);

        bPain.setTop(t1);

        instr = new Button("Instructions");
        instr.setOnAction(e->{
            instruction = new Stage();

            instruction.initModality(Modality.APPLICATION_MODAL);
            instruction.setTitle("Instructions");
            instruction.setMinWidth(250);
            instruction.setMinHeight(250);

            Label label = new Label();
            label.setText("Play the game dumdum.");

            VBox lay = new VBox(10);
            lay.getChildren().addAll(label, close);
            lay.setAlignment(Pos.CENTER);

            instructions = new Scene(lay);
            instruction.setScene(instructions);
            instruction.showAndWait();


        });

        close = new Button("I Understand");
        close.setOnAction(e -> instruction.close());


        bPain.setAlignment(instr, Pos.BOTTOM_CENTER);

        tb00 = new TextField();
        tb00.setFont(Font.font("Georgia", 16));
        tb00.setAlignment(Pos.CENTER);
        tb00.setMaxSize(40, 40);
        tb00.setMinSize(40,40);
        //bPain.setCenter(tb00);
        tb00.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb00.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb00.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb00.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[0] = s.charAt(0);
                }
        }});

        textFields[0] = tb00;

        tb01 = new TextField();
        tb01.setFont(Font.font("Georgia", 16));
        tb01.setAlignment(Pos.CENTER);
        tb01.setMaxSize(40, 40);
        tb01.setMinSize(40,40);
        tb01.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb01.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb01.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb01.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[5] = s.charAt(0);
                }
            }});

        //s01 = tb01.getCharacters().toString();


        tb02 = new TextField();
        tb02.setFont(Font.font("Georgia", 16));
        tb02.setAlignment(Pos.CENTER);
        tb02.setMaxSize(40, 40);
        tb02.setMinSize(40,40);
        tb02.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb02.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb02.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb02.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[10] = s.charAt(0);
                }
            }});


        tb03 = new TextField();
        tb03.setFont(Font.font("Georgia", 16));
        tb03.setAlignment(Pos.CENTER);
        tb03.setMaxSize(40, 40);
        tb03.setMinSize(40,40);
        tb03.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb03.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb03.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb03.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[15] = s.charAt(0);
                }
            }});


        tb04 = new TextField();
        tb04.setFont(Font.font("Georgia", 16));
        tb04.setAlignment(Pos.CENTER);
        tb04.setMaxSize(40, 40);
        tb04.setMinSize(40,40);
        tb04.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb04.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb04.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb04.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[20] = s.charAt(0);
                }
            }});


        tb05 = new TextField();
        tb05.setFont(Font.font("Georgia", 16));
        tb05.setAlignment(Pos.CENTER);
        tb05.setMaxSize(40, 40);
        tb05.setMinSize(40,40);
        tb05.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb05.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb05.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb05.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[25] = s.charAt(0);
                }
            }});

        s05 = tb05.getCharacters().toString();

        tb10 = new TextField();
        tb10.setFont(Font.font("Georgia", 16));
        tb10.setAlignment(Pos.CENTER);
        tb10.setMaxSize(40, 40);
        tb10.setMinSize(40,40);
        tb10.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb10.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb10.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb10.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[1] = s.charAt(0);
                }
            }});

        s10 = tb10.getCharacters().toString();

        tb20 = new TextField();
        tb20.setFont(Font.font("Georgia", 16));
        tb20.setAlignment(Pos.CENTER);
        tb20.setMaxSize(40, 40);
        tb20.setMinSize(40,40);
        tb20.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb20.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb20.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb20.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[2] = s.charAt(0);
                }
            }});

        s20 = tb20.getCharacters().toString();

        tb30 = new TextField();
        tb30.setFont(Font.font("Georgia", 16));
        tb30.setAlignment(Pos.CENTER);
        tb30.setMaxSize(40, 40);
        tb30.setMinSize(40,40);
        tb30.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb30.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb30.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb30.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[3] = s.charAt(0);
                }
            }});

        s30 = tb30.getCharacters().toString();

        tb40 = new TextField();
        tb40.setFont(Font.font("Georgia", 16));
        tb40.setAlignment(Pos.CENTER);
        tb40.setMaxSize(40, 40);
        tb40.setMinSize(40,40);
        tb40.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb40.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb40.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb40.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[4] = s.charAt(0);
                }
            }});

        s40 = tb40.getCharacters().toString();

        tb41 = new TextField();
        tb41.setFont(Font.font("Georgia", 16));
        tb41.setAlignment(Pos.CENTER);
        tb41.setMaxSize(40, 40);
        tb41.setMinSize(40,40);
        tb41.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb41.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb41.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb41.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[9] = s.charAt(0);
                }
            }});

        s41 = tb41.getCharacters().toString();

        tb42 = new TextField();
        tb42.setFont(Font.font("Georgia", 16));
        tb42.setAlignment(Pos.CENTER);
        tb42.setMaxSize(40, 40);
        tb42.setMinSize(40,40);
        tb42.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb42.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb42.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb42.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[14] = s.charAt(0);
                }
            }});

        s42 = tb42.getCharacters().toString();

        tb43 = new TextField();
        tb43.setFont(Font.font("Georgia", 16));
        tb43.setAlignment(Pos.CENTER);
        tb43.setMaxSize(40, 40);
        tb43.setMinSize(40,40);
        tb43.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb43.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb43.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb43.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[19] = s.charAt(0);
                }
            }});

        s43 = tb43.getCharacters().toString();

        tb44 = new TextField();
        tb44.setFont(Font.font("Georgia", 16));
        tb44.setAlignment(Pos.CENTER);
        tb44.setMaxSize(40, 40);
        tb44.setMinSize(40,40);
        tb44.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb44.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb44.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb44.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[24] = s.charAt(0);
                }
            }});

        s44 = tb44.getCharacters().toString();

        tb45 = new TextField();
        tb45.setFont(Font.font("Georgia", 16));
        tb45.setAlignment(Pos.CENTER);
        tb45.setMaxSize(40, 40);
        tb45.setMinSize(40,40);
        tb45.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb45.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb45.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb45.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[29] = s.charAt(0);
                }
            }});

        s45 = tb45.getCharacters().toString();

        tb11 = new TextField();
        tb11.setFont(Font.font("Georgia", 16));
        tb11.setAlignment(Pos.CENTER);
        tb11.setMaxSize(40, 40);
        tb11.setMinSize(40,40);
        tb11.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb11.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb11.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb11.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[6] = s.charAt(0);
                }
            }});

        s11 = tb11.getCharacters().toString();

        tb12 = new TextField();
        tb12.setFont(Font.font("Georgia", 16));
        tb12.setAlignment(Pos.CENTER);
        tb12.setMaxSize(40, 40);
        tb12.setMinSize(40,40);
        tb12.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb12.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb12.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb12.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[11] = s.charAt(0);
                }
            }});

        s12 = tb12.getCharacters().toString();

        tb13 = new TextField();
        tb13.setFont(Font.font("Georgia", 16));
        tb13.setAlignment(Pos.CENTER);
        tb13.setMaxSize(40, 40);
        tb13.setMinSize(40,40);
        tb13.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb13.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb13.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb13.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[16] = s.charAt(0);
                }
            }});

        s13 = tb13.getCharacters().toString();

        tb14 = new TextField();
        tb14.setFont(Font.font("Georgia", 16));
        tb14.setAlignment(Pos.CENTER);
        tb14.setMaxSize(40, 40);
        tb14.setMinSize(40,40);
        tb14.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb14.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb14.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb14.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[21] = s.charAt(0);
                }
            }});

        s14 = tb14.getCharacters().toString();

        tb15 = new TextField();
        tb15.setFont(Font.font("Georgia", 16));
        tb15.setAlignment(Pos.CENTER);
        tb15.setMaxSize(40, 40);
        tb15.setMinSize(40,40);
        tb15.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb15.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb15.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb15.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[26] = s.charAt(0);
                }
            }});

        s15 = tb15.getCharacters().toString();

        tb25 = new TextField();
        tb25.setFont(Font.font("Georgia", 16));
        tb25.setAlignment(Pos.CENTER);
        tb25.setMaxSize(40, 40);
        tb25.setMinSize(40,40);
        tb25.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb25.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb25.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb25.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[27] = s.charAt(0);
                }
            }});

        s25 = tb25.getCharacters().toString();

        tb24 = new TextField();
        tb24.setFont(Font.font("Georgia", 16));
        tb24.setAlignment(Pos.CENTER);
        tb24.setMaxSize(40, 40);
        tb24.setMinSize(40,40);
        tb24.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb24.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb24.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb24.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[22] = s.charAt(0);
                }
            }});

        s24 = tb24.getCharacters().toString();

        tb23 = new TextField();
        tb23.setFont(Font.font("Georgia", 16));
        tb23.setAlignment(Pos.CENTER);
        tb23.setMaxSize(40, 40);
        tb23.setMinSize(40,40);
        tb23.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb23.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb23.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb23.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[17] = s.charAt(0);
                }
            }});

        s23 = tb23.getCharacters().toString();

        tb22 = new TextField();
        tb22.setFont(Font.font("Georgia", 16));
        tb22.setAlignment(Pos.CENTER);
        tb22.setMaxSize(40, 40);
        tb22.setMinSize(40,40);
        tb22.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb22.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb22.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb22.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[12] = s.charAt(0);
                }
            }});

        s22 = tb22.getCharacters().toString();

        tb21 = new TextField();
        tb21.setFont(Font.font("Georgia", 16));
        tb21.setAlignment(Pos.CENTER);
        tb21.setMaxSize(40, 40);
        tb21.setMinSize(40,40);
        tb21.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb21.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb21.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb21.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[7] = s.charAt(0);
                }
            }});

        s21 = tb21.getCharacters().toString();

        tb31 = new TextField();
        tb31.setFont(Font.font("Georgia", 16));
        tb31.setAlignment(Pos.CENTER);
        tb31.setMaxSize(40, 40);
        tb31.setMinSize(40,40);
        tb31.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb31.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb31.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb31.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[8] = s.charAt(0);
                }
            }});

        s31 = tb31.getCharacters().toString();

        tb32 = new TextField();
        tb32.setFont(Font.font("Georgia", 16));
        tb32.setAlignment(Pos.CENTER);
        tb32.setMaxSize(40, 40);
        tb32.setMinSize(40,40);
        tb32.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb32.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb32.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb32.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[13] = s.charAt(0);
                }
            }});

        s32 = tb32.getCharacters().toString();

        tb33 = new TextField();
        tb33.setFont(Font.font("Georgia", 16));
        tb33.setAlignment(Pos.CENTER);
        tb33.setMaxSize(40, 40);
        tb33.setMinSize(40,40);
        tb33.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb33.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb33.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb33.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[18] = s.charAt(0);
                }
            }});

        s33 = tb33.getCharacters().toString();

        tb34 = new TextField();
        tb34.setFont(Font.font("Georgia", 16));
        tb34.setAlignment(Pos.CENTER);
        tb34.setMaxSize(40, 40);
        tb34.setMinSize(40,40);
        tb34.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb34.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb34.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb34.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[23] = s.charAt(0);
                }
            }});

        s34 = tb34.getCharacters().toString();

        tb35 = new TextField();
        tb35.setFont(Font.font("Georgia", 16));
        tb35.setAlignment(Pos.CENTER);
        tb35.setMaxSize(40, 40);
        tb35.setMinSize(40,40);
        tb35.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tb35.getText().length() >= 1 && !newValue.matches("\\sA-Z*")) {
                    String s = tb35.getText().substring(0, 1);
                    s = s.replace(s, s.toUpperCase());
                    //tb00.setText(s);
                    tb35.setText(s.replaceAll("[^\\sA-Z]", ""));
                    full[28] = s.charAt(0);
                }
            }});

        s35 = tb35.getCharacters().toString();

        textFields[1] = tb10;
        textFields[2] = tb20;
        textFields[3] = tb30;
        textFields[4] = tb40;

        textFields[5] = tb01;
        textFields[6] = tb11;
        textFields[7] = tb21;
        textFields[8] = tb31;
        textFields[9] = tb41;

        textFields[10] = tb02;
        textFields[11] = tb12;
        textFields[12] = tb22;
        textFields[13] = tb32;
        textFields[14] = tb42;

        textFields[15] = tb03;
        textFields[16] = tb13;
        textFields[17] = tb23;
        textFields[18] = tb33;
        textFields[19] = tb43;

        textFields[20] = tb04;
        textFields[21] = tb14;
        textFields[22] = tb24;
        textFields[23] = tb34;
        textFields[24] = tb44;

        textFields[25] = tb05;
        textFields[26] = tb15;
        textFields[27] = tb25;
        textFields[28] = tb35;
        textFields[29] = tb45;

        //Column , Row
        GridPane.setConstraints(tb00, 0, 0);
        GridPane.setConstraints(tb01, 0, 1);
        //GridPane.setConstraints(tb01, 0, 1);
        GridPane.setConstraints(tb02, 0, 2);
        GridPane.setConstraints(tb03, 0, 3);
        GridPane.setConstraints(tb04, 0, 4);
        GridPane.setConstraints(tb05, 0, 5);
        GridPane.setConstraints(tb10, 1, 0);
        GridPane.setConstraints(tb20, 2, 0);
        GridPane.setConstraints(tb30, 3, 0);
        GridPane.setConstraints(tb40, 4, 0);

        GridPane.setConstraints(tb11, 1, 1);
        GridPane.setConstraints(tb12, 1, 2);
        GridPane.setConstraints(tb13, 1, 3);
        GridPane.setConstraints(tb14, 1, 4);
        GridPane.setConstraints(tb15, 1, 5);

        GridPane.setConstraints(tb25, 2, 5);
        GridPane.setConstraints(tb24, 2, 4);
        GridPane.setConstraints(tb23, 2, 3);
        GridPane.setConstraints(tb22, 2, 2);
        GridPane.setConstraints(tb21, 2, 1);

        GridPane.setConstraints(tb31, 3, 1);
        GridPane.setConstraints(tb32, 3, 2);
        GridPane.setConstraints(tb33, 3, 3);
        GridPane.setConstraints(tb34, 3, 4);
        GridPane.setConstraints(tb35, 3, 5);


        GridPane.setConstraints(tb45, 4, 5);
        GridPane.setConstraints(tb44, 4, 4);
        GridPane.setConstraints(tb43, 4, 3);
        GridPane.setConstraints(tb42, 4, 2);
        GridPane.setConstraints(tb41, 4, 1);


        gPain.getChildren().addAll(tb00, tb10, tb20, tb30, tb40,
                                    tb01, tb11, tb21, tb31, tb41,
                                    tb02, tb12, tb22, tb32, tb42,
                                    tb03, tb13, tb23, tb33, tb43,
                                    tb04, tb14, tb24, tb34, tb44,
                                    tb05, tb15, tb25, tb35, tb45);





        BorderPane.setAlignment(gPain, Pos.CENTER);
        BorderPane.setMargin(gPain, new Insets(20,20,50,90)); // optional
        bPain.setCenter(gPain);
        //sPain.getChildren().addAll(gPain, bPain);

        HBox bottomMenu = new HBox(10);
        bottomMenu.getChildren().addAll(instr, Restartbtn, darkbtn);

        bPain.setAlignment(bottomMenu, Pos.CENTER);
        bPain.setBottom(bottomMenu);
        game = new Scene(bPain, 500, 500);




        Random rand = new Random();
        int randWord = rand.nextInt(Words.list.size());
        System.out.println(randWord);
        correct = Words.list.get(randWord);
        correct = correct.toUpperCase();
        System.out.println(correct);

        int[] semi_correct = new int[5];

        game.setOnKeyPressed(e -> {
                if(count >= 6) {
                    System.out.println("Game Over!");
                    Stage alert = new Stage();
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.setTitle("Error 404");
                    alert.setMinHeight(250);
                    alert.setMinWidth(250);

                    Label lerr = new Label();
                    lerr.setText("YOU LOSE. TRY TO PLAY AGAIN LATER.");
                    Button closen = new Button("Close");
                    closen.setOnAction(ex -> alert.close());

                    VBox layerr = new VBox(10);
                    layerr.getChildren().addAll(lerr, closen);
                    layerr.setAlignment(Pos.CENTER);

                    Scene scn = new Scene(layerr);
                    alert.setScene(scn);
                    alert.showAndWait();
                } else {


                    for (int i = 0; i < 5 * abc; i++) {
                        if (textFields[i].getText() == null || textFields[i].getText().trim().isEmpty()) {
                            Stage alert = new Stage();
                            alert.initModality(Modality.APPLICATION_MODAL);
                            alert.setTitle("Error 404");
                            alert.setMinHeight(250);
                            alert.setMinWidth(250);

                            Label lerr = new Label();
                            lerr.setText("ERROR! - Enter 5 Characters to proceed.");
                            Button closen = new Button("Close");
                            closen.setOnAction(ex -> alert.close());

                            VBox layerr = new VBox(10);
                            layerr.getChildren().addAll(lerr, closen);
                            layerr.setAlignment(Pos.CENTER);

                            Scene scn = new Scene(layerr);
                            alert.setScene(scn);
                            alert.showAndWait();
                            bb = false;
                            break;

                        } else {
                            bb = true;
                        }
                    }
                    //System.out.println("A key was pressed");
                    int key = 1;

                    if (bb) {

                        if (count < 6) {
                            abc++;
                            for (int i = 0; i <= 4; i++) {
                                //System.out.println("full index array: " + full[i+1]);
                                //System.out.println("========");
                                //System.out.println("Correct answer: " + correct.charAt(i));
                                //
                                if (full[i + count * 5] == correct.charAt(i)) {
                                    ans[i] = 1;
                                    //Green
                                    Paint color0 = Paint.valueOf("6AAA64");
                                    // tb45.setStyle("-fx-control-inner-background: #"+color0.toString().substring(2));
                                    textFields[i + count * 5].setStyle("-fx-control-inner-background: #" + color0.toString().substring(2));

                                } else {
                                    for (int j = 0; j <= 4; j++) {
                                        if (full[i + count * 5] == correct.charAt(j)) {
                                            //ORANGE
                                            Paint color1 = Paint.valueOf("C9b458");
                                            semi_correct[j] = 1;
                                            textFields[i + count * 5].setStyle("-fx-control-inner-background: #" + color1.toString().substring(2));
                                            break;

                                        } else {
                                            //GREY
                                            Paint color2 = Paint.valueOf("86888A");
                                            textFields[i + count * 5].setStyle("-fx-control-inner-background: #" + color2.toString().substring(2));
                                        }
                                    }
                                }
                            }
                            count++;
                        }
                    }

                    sum = ans[0] + ans[1] + ans[2] + ans[3] + ans[4];
                    if (sum == 5) {
                        Stage winner = new Stage();
                        winner.initModality(Modality.APPLICATION_MODAL);
                        winner.setTitle("HOORAYY!!!");
                        winner.setMinHeight(450);
                        winner.setMinWidth(450);

                        Label lwin = new Label();
                        lwin.setFont(Font.font("Georgia", 18));
                        lwin.setText("YOU HAVE CONQUER WORDLE");
                        Button closen = new Button("Close");
                        closen.setOnAction(ex -> winner.close());

                        VBox laywin = new VBox(10);
                        laywin.getChildren().addAll(lwin, closen);
                        laywin.setAlignment(Pos.CENTER);

                        Scene scn = new Scene(laywin);
                        winner.setScene(scn);
                        winner.showAndWait();
                    }


                }

        });



        shifting(tb00,tb10);
        shifting(tb10,tb20);
        shifting(tb20,tb30);
        shifting(tb30,tb40);

        backspace(tb40, tb30);
        backspace(tb30, tb20);
        backspace(tb20, tb10);
        backspace(tb10, tb00);

        shifting(tb01,tb11);
        shifting(tb11,tb21);
        shifting(tb21,tb31);
        shifting(tb31,tb41);

        backspace(tb41, tb31);
        backspace(tb31, tb21);
        backspace(tb21, tb11);
        backspace(tb11, tb01);


        //shifting(tb32,tb42);

        backspace(tb42, tb32);
        backspace(tb32, tb22);
        backspace(tb22, tb12);
        backspace(tb12, tb02);

        shifting(tb03,tb13);
        shifting(tb13,tb23);
        shifting(tb23,tb33);
        shifting(tb33,tb43);

        backspace(tb43, tb33);
        backspace(tb33, tb23);
        backspace(tb23, tb13);
        backspace(tb13, tb03);

        shifting(tb04,tb14);
        shifting(tb14,tb24);
        shifting(tb24,tb34);
        shifting(tb34,tb44);

        backspace(tb44, tb34);
        backspace(tb34, tb24);
        backspace(tb24, tb14);
        backspace(tb14, tb04);

        shifting(tb05,tb15);
        shifting(tb15,tb25);
        shifting(tb25,tb35);
        shifting(tb35,tb45);

        backspace(tb45, tb35);
        backspace(tb35, tb25);
        backspace(tb25, tb15);
        backspace(tb15, tb05);







        window.setScene(game);
        window.show();

    }
    private void backspace(TextField tf1, TextField tf2) {
        tf1.textProperty().addListener((obs, oldText, newText) -> {
            if (oldText.length() == 1 && newText.length() < 1) {
                tf2.requestFocus();
            }
        });
    }

    private void shifting(TextField tf1, TextField tf2) {
        tf1.textProperty().addListener((obs, oldText, newText) -> {
            if (oldText.length() < 1 && newText.length() >= 1) {
                tf2.requestFocus();
            }
        });
    }




public int[] check_word(char[] guess, String correct) {
        int[] answer = new int[5];
        int[] semi = new int[5];
        for (int i = 0; i <= 4; i++) {
            //System.out.println("full index array: " + full[i+1]);
            //System.out.println("========");
            //System.out.println("Correct answer: " + correct.charAt(i));
            if (guess[i] == correct.charAt(i)) {
                answer[i] = 1;

            } else {
                for (int j = 0; j <= 4; j++) {
                    if (guess[i] == correct.charAt(j)) {
                        semi[j] = 1;
                    }
                }
                return semi;
            }
        }
        return answer;
    }

    private boolean isClr2() {
        Boolean clr = false;

        Alert atr = new Alert(Alert.AlertType.CONFIRMATION);
        atr.setContentText("No ideas are bad ideas.");
        atr.setTitle("Clear All Fields!");
        atr.setHeaderText("Are you sure you want to clear all fields? (All unsaved data may be lost)");

        // ButtonType cancel = new ButtonType("Cancel",
        // ButtonBar.ButtonData.CANCEL_CLOSE);
        // atr.getDialogPane().getButtonTypes().add(cancel);

        Optional<ButtonType> result = atr.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            clr = true;
        }
        return clr;
    }

}