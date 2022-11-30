import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

public class Hangman extends Application {

    private Scene HANGMAN_SCENE;
    private String check;
    private char[] key;
    private char[] line;
    private int lives = 6;
    
    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        Circle head = new Circle(367 ,92, 30);
        head.setFill(Color.WHITE);
        Line body = new Line(367, 217, 367, 123);
        Line leftarm = new Line(316, 134, 367.5, 166);
        Line rightarm = new Line(425, 131, 368, 165);
        Line leftleg = new Line(334, 257, 367.5, 217.5);
        Line rightleg = new Line(397, 256, 367.5, 218.5);
        Line base = new Line(456, 296, 540, 296);
        Line tree = new Line(499, 37, 498, 295.5);
        Line branch = new Line(367.5, 36.5, 499,37);
        Line noose = new Line(367, 59, 367,37);
        Button play = new Button("Play");
        PasswordField answer = new PasswordField();
        TextField guess = new TextField();
        Label prompt = new Label("Please enter the word to be guessed.");
        Label score = new Label("");
        score.setFont(new Font(Font.getDefault().getName(), 25));
        answer.setVisible(false);
        prompt.setVisible(false);
        score.setVisible(false);
        guess.setVisible(false);
        play.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                score.setText("");
                lives = 6;
                prompt.setFont(new Font(Font.getDefault().getName(), Font.getDefault().getSize()));
                prompt.setText("Please enter the word to be guessed");
                AnchorPane.setRightAnchor(prompt, 200.0);
                AnchorPane.setTopAnchor(prompt, 275.0);
                play.setVisible(false);
                answer.setVisible(true);
                prompt.setVisible(true);
                head.setVisible(false);
                body.setVisible(false);
                leftarm.setVisible(false);
                rightarm.setVisible(false);
                leftleg.setVisible(false);
                rightleg.setVisible(false);
            }
        });
        answer.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!(answer.getText().trim().length() == 0)) {
                    check = answer.getText().trim();
                    key = new char[check.length()];
                    line = new char[check.length()];
                    for (int i = 0; i < line.length; i++) {
                        line[i] = '_';
                    }
                    for (int i = 0; i < key.length; i++) {
                        key[i] = check.charAt(i);
                        score.setText(score.getText() + line[i]);
                    }
                    answer.setVisible(false);
                    prompt.setText("Please guess a letter. You have 6 guesses left.");
                    AnchorPane.setRightAnchor(prompt, 175.0);
                    score.setVisible(true);
                    guess.setVisible(true);
                }
            }
        });
        guess.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    score.setText("");
                    int temp = 0;
                    for (int i = 0; i < key.length; i++) {
                        if (Character.toLowerCase(key[i]) == (Character.toLowerCase(guess.getText().charAt(0)))) {
                            line[i] = guess.getText().charAt(0);
                            prompt.setText(String.format("Nice Guess. You have %d guesses left.", lives));
                            temp++;
                        }
                    }
                    if (temp == 0) {
                        lives--;
                        prompt.setText(String.format("Too Bad. You have %d guesses left.", lives));
                        if (lives == 5) {
                            head.setVisible(true);
                        }
                        if (lives == 4) {
                            body.setVisible(true);
                        }
                        if (lives == 3) {
                            leftarm.setVisible(true);
                        }
                        if (lives == 2) {
                            rightarm.setVisible(true);
                        }
                        if (lives == 1) {
                            leftleg.setVisible(true);
                        }
                        if (lives == 0) {
                            rightleg.setVisible(true);
                        }
                    }
                    for (int i = 0; i < line.length; i++) {
                        score.setText(score.getText() + line[i]);
                    }
                    guess.setText("");
                    if (score.getText().equalsIgnoreCase(check)) {
                        guess.setVisible(false);
                        play.setText("Play again?");
                        play.setVisible(true);
                        prompt.setText("You Win");
                        prompt.setFont(new Font(Font.getDefault().getName(), 40));
                        AnchorPane.setRightAnchor(prompt, 225.0);
                        AnchorPane.setTopAnchor(prompt, 125.0);
                    }
                    if (lives <= 0) {
                        guess.setVisible(false);
                        play.setText("Play again?");
                        play.setVisible(true);
                        prompt.setText("You Died");
                        prompt.setFont(new Font(Font.getDefault().getName(), 40));
                        AnchorPane.setRightAnchor(prompt, 225.0);
                        AnchorPane.setTopAnchor(prompt, 125.0);
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    prompt.setText("You have to guess something!");
                    for (int i = 0; i < line.length; i++) {
                        score.setText(score.getText() + line[i]);
                    }
                }
            }
        });
        anchorPane.setBackground(new Background(new BackgroundFill(
                                                                Color.DARKGREY,
                                                                null,
                                                                null)));
        primaryStage.setScene(HANGMAN_SCENE);
        AnchorPane.setRightAnchor(head, 153.0);
        AnchorPane.setRightAnchor(body, 183.0);
        AnchorPane.setRightAnchor(leftarm, 183.0);
        AnchorPane.setRightAnchor(rightarm, 123.0);
        AnchorPane.setRightAnchor(leftleg, 183.0);
        AnchorPane.setRightAnchor(rightleg, 153.0);
        AnchorPane.setRightAnchor(noose, 183.0);
        AnchorPane.setRightAnchor(branch, 51.0);
        AnchorPane.setRightAnchor(tree, 51.0);
        AnchorPane.setRightAnchor(base, 11.0);
        AnchorPane.setRightAnchor(play, 275.0);
        AnchorPane.setTopAnchor(play, 175.0);
        AnchorPane.setRightAnchor(answer, 225.0);
        AnchorPane.setTopAnchor(answer, 250.0);
        AnchorPane.setRightAnchor(prompt, 200.0);
        AnchorPane.setTopAnchor(prompt, 275.0);
        AnchorPane.setRightAnchor(score, 0.0);
        AnchorPane.setLeftAnchor(score, 0.0);
        score.setAlignment(Pos.CENTER);
        AnchorPane.setRightAnchor(guess, 225.0);
        AnchorPane.setTopAnchor(guess, 250.0);
        anchorPane.getChildren().addAll(head, body, leftarm, rightarm, leftleg, rightleg,
            base, tree, branch, noose, play, answer, prompt, guess, score);
        HANGMAN_SCENE = new Scene(anchorPane, 550, 350);
        primaryStage.setTitle("Hangman Game");
        primaryStage.setScene(HANGMAN_SCENE);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}