package com.sqli.jsg.amiennemis.client.model;

public enum GameImpl implements Game {

	INSTANCE;
	
	protected Field field;
	protected Personnage personnage;
	
	@Override
	public Sprite[][] getGrille() {
		return this.field.getField();
	}

	@Override
	public void move(Move move) {
		this.personnage.move(move, this.field);
		this.field.updateField(personnage);
	}
	
	

}
