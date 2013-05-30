package com.sqli.jsg.amiennemis.client.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public enum GameImpl implements Game {

	INSTANCE;
	
	
	protected Field field;
	protected Personnage personnage;
	protected List<Personnage> others = new ArrayList<Personnage>();
	
	private GameImpl() {
		this.personnage = new Poursuivant(Integer.parseInt(System.getProperty("id")), new Point(Integer.parseInt(System.getProperty("x")), Integer.parseInt(System.getProperty("y"))));
	}
	
	@Override
	public Sprite[][] getGrille() {
		return this.field.getField();
	}

	@Override
	public void move(Move move) {
		this.personnage.move(move, this.field);
		this.field.updateField(personnage);
	}

	@Override
	public void updatePersonnage(int id, int x, int y) {
		for (Personnage perso : others) {
			if(perso.id == id) {
				perso.setPosition(new Point(x, y));
				this.field.updateField(perso);
				break;
			}
		}
		
	}
	
	

}
