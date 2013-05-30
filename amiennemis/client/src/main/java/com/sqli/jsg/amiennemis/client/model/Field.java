package com.sqli.jsg.amiennemis.client.model;

import java.awt.Point;

public class Field {

	protected Sprite[][] field;
	
	public Sprite[][] getField() {
		return this.field;
	}
	
	public boolean isWall(Point position) {
		return field[position.x][position.y] == Sprite.MUR;
	}
	
	public void updateField(Personnage personnage) {
		field[personnage.getPosition().x][personnage.getPosition().y] = personnage.getSpriteType();
		field[personnage.getOldPosition().x][personnage.getOldPosition().y] = Sprite.SOL;
	}
	
	
}
