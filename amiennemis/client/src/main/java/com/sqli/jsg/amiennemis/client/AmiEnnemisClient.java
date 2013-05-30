package com.sqli.jsg.amiennemis.client;

import static com.sqli.jsg.amiennemis.client.model.Move.BAS;
import static com.sqli.jsg.amiennemis.client.model.Move.DROITE;
import static com.sqli.jsg.amiennemis.client.model.Move.GAUCHE;
import static com.sqli.jsg.amiennemis.client.model.Move.HAUT;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.sqli.jsg.amiennemis.client.model.Game;
import com.sqli.jsg.amiennemis.client.model.GameImpl;
import com.sqli.jsg.amiennemis.client.model.Sprite;

/**
 * Hello world!
 * 
 */
public class AmiEnnemisClient extends Application {

	final private int NB_CASES_H = 25;
	final private int NB_CASES_V = 25;
	final private int CELLSPACE = 0;

	final ImageView[] tile = new ImageView[NB_CASES_H * NB_CASES_V];
	final StringProperty info = new SimpleStringProperty("starting the game");
	final StringProperty turnInfo = new SimpleStringProperty("");
	private Game game;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		game = GameImpl.INSTANCE;

		LocalEndpoint.client = this;

		final BorderPane root = new BorderPane();
		root.setStyle("-fx-padding: 20");
//		root.setCenter(rows);

		Timeline fiveSecondsWonder = new Timeline(new KeyFrame(
				Duration.seconds(1), new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						
						 final Sprite[][] grille = game.getGrille();
						// *
//						final Sprite[][] grille = new Sprite[][] {
//								{ Sprite.MUR, Sprite.SOL, Sprite.FANTOME },
//								{ Sprite.MUR, Sprite.SOL, Sprite.FANTOME },
//								{ Sprite.MUR, Sprite.SOL, Sprite.FANTOME } };
						// */
						if (grille.length == 0) {
							System.out.println("Grille vide");
						} else {
							System.out.println("Grille de taille : ("
									+ grille.length + " x " + grille[0].length
									+ ")");
						}
						VBox rows = prepareBoard(grille);
						
						root.setCenter(rows);
						
					}
				}));
		fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
		fiveSecondsWonder.play();

		
		Scene scene = new Scene(root);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out
						.println("AmiEnnemisClient.start(...).new EventHandler() {...}.handle()"
								+ event.getCode());
				doMove(event);
			}
		});
		stage.setScene(scene);
		stage.show();
	}

	private VBox prepareBoard(final Sprite[][] grille) {
		// *
		for (int i = 0; i < grille.length; i++) {

			for (int j = 0; j < grille[i].length; j++) {
				switch (grille[i][j]) {
				case FANTOME:
					tile[NB_CASES_V * i + j] = new ImageView(new Image(
							"/joueur_bleu.png", 20, 20, true, true));
					break;
				case MUR:
					tile[NB_CASES_V * i + j] = new ImageView(new Image(
							"/mur_ovale_horizontal.png", 20, 20, true, true));
					break;
				case POURSUIVANT:
					tile[NB_CASES_V * i + j] = new ImageView(new Image(
							"/pacman.png", 20, 20, true, true));
					break;
				case SOL:
					tile[NB_CASES_V * i + j] = new ImageView(new Image(
							"/sol.png", 20, 20, true, true));
					break;
				}
			}
		}// */

		for (int i = 0; i < tile.length; i++) {
			// tile[i] = new ImageView(new Image("/p.png", 20, 20, true, true));
			// tile[i].setOnKeyPressed(arg0)
		}

		VBox rows = new VBox();
		rows.setStyle("-fx-background-color: black;");
		rows.setSpacing(CELLSPACE);
		for (int i = 0; i < grille.length; i++) {
			HBox cols = new HBox();
			cols.setSpacing(CELLSPACE);
			List<ImageView> listImageEnLigne = new ArrayList<ImageView>();
			for (int j = 0; j < grille[i].length; j++) {
				System.out.println("i = " + i + " ; j =" + j + " ; coord = "
						+ (NB_CASES_V * i + j));

				listImageEnLigne.add(tile[NB_CASES_V * i + j]);
			}
			System.out.println("LA");
			cols.getChildren().addAll(listImageEnLigne);
			rows.getChildren().add(cols);
		}
		return rows;
	}

	public void setInfo(String txt) {
		System.out.println("change infotext from " + info.get() + " to " + txt);
		info.set(txt);
	}

	public void doMove(KeyEvent event) {
		// appel
		switch (event.getCode()) {
		case KP_DOWN:
			game.move(BAS);
			break;
		case KP_LEFT:
			game.move(GAUCHE);
			break;
		case KP_RIGHT:
			game.move(DROITE);
			break;
		case KP_UP:
			game.move(HAUT);
			break;
		default:
		}
	}

}
