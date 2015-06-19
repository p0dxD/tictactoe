
package tictactoe;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The game with computer player (Random) and player 
 * @author Jose Rodriguez
 */
public class TicTacToe extends Application {

    private boolean isPlayerTurn = true;
    private boolean isComputerTurn = false;
    private Box[][] box = new Box[3][3];
    private GridPane root = new GridPane();
    private double width = 500;
    private double height = 540;

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(new File("Style.css").toURI().toString());
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() {
        root.setAlignment(Pos.CENTER);
        root.setHgap(5.5);
        root.setVgap(5.5);
        for (int row = 0; row < box.length; row++) {
            for (int column = 0; column < box[row].length; column++) {
                root.add(box[row][column] = new Box(), row, column);
                System.out.print(box[row][column].getName());
                System.out.print(box[row][column].isEmpty());
            }
            System.out.println();
        }
        root.setPrefSize(width, height);
    }

    public void processComputerTurn() {
        System.out.println("Is my turn");
        for (Box[] row : box) {
            for (Box boxy : row) {
                if (boxy.getName() != 1 && boxy.getName() != 0 && isComputerTurn) {
                    boxy.setName(0);
                    boxy.setStyle("-fx-border-color: red");
                    isComputerTurn = false;
                    isPlayerTurn = true;
                    checkIfWon(0);
                }
                System.out.print(boxy.getName());

            }
            System.out.println();
        }
    }

    public void checkIfWon(int name) {
        for (int i = 0; i < 3; i++) {
            if (box[i][0].getName() == name && box[i][1].getName() == name && box[i][2].getName() == name) {
                won(name);
            }
            if (box[0][i].getName() == name && box[1][i].getName() == name && box[2][i].getName() == name) {
                won(name);
            }
        }
        if (box[0][0].getName() == name && box[1][1].getName() == name && box[2][2].getName() == name) {
            won(name);
        }
        if (box[0][2].getName() == name && box[1][1].getName() == name && box[2][0].getName() == name) {
            won(name);
        }
    }

    public void won(int name) {
        if (name == 1) {
            System.out.println("You Won");
        } else {
            System.out.println("Computer Won");

        }
        isPlayerTurn = false;
        isComputerTurn = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    class Box extends Pane {

        private boolean isEmpty = true;
        private int name = -1;

        public Box() {
            this.setPrefSize(width / 3, height / 3);
            this.getStyleClass().add("my-rect");
            this.setOnMouseClicked(e -> {
                if (isEmpty() && isPlayerTurn) {
                    setIsEmpty(false);
                    this.setStyle("-fx-border-color: blue");
                    isPlayerTurn = false;
                    isComputerTurn = true;
                    setName(1);
                    checkIfWon(1);
                    processComputerTurn();
                }
            });
        }

        public void setName(int name) {
            this.name = name;
        }

        public int getName() {
            return name;
        }

        public void setIsEmpty(boolean status) {
            this.isEmpty = false;
        }

        public boolean isEmpty() {
            return this.isEmpty;
        }
    }
}
