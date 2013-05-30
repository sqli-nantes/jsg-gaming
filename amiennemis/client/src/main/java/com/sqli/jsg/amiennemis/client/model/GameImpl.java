package com.sqli.jsg.amiennemis.client.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.sqli.jsg.amiennemis.client.LocalEndpoint;

public enum GameImpl implements Game {

	INSTANCE;
	
	
	protected Field field;
	protected Personnage personnage;
	protected List<Personnage> others = new ArrayList<Personnage>();
	
	private GameImpl() {
		this.personnage = new Poursuivant(Integer.parseInt(System.getProperty("id")), new Point(Integer.parseInt(System.getProperty("x")), Integer.parseInt(System.getProperty("y"))));
		LocalEndpoint.getInstance().setGame(this);
	}
	
	@Override
	public Sprite[][] getGrille() {
		return this.field.getField();
	}

	@Override
	public void move(Move move) {
		this.personnage.move(move, this.field);
		this.field.updateField(personnage);
		LocalEndpoint.getInstance().broadcastPosition(personnage);
	}

	@Override
	public void updatePersonnage(int id, int x, int y) {
		boolean persoExists = false;
		for (Personnage perso : others) {
			if(perso.id == id) {
				perso.setPosition(new Point(x, y));
				this.field.updateField(perso);
				persoExists = true;
			}
		}
		if(!persoExists) {
			Poursuivant nouveau = new Poursuivant(id, new Point(x, y));
			this.field.updateField(nouveau);
			others.add(nouveau);
		}
	}
	
	

}
