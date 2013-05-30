package com.sqli.jsg.amiennemis.client.model;

import java.awt.Point;

public class Field {

	protected Sprite[][] field;
	protected int x = 25;
	protected int y = 25;
	
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

	public void generateField() {
		for(int i=0; i<x; i++) {
			for(int j=0; i<y; j++) {
				this.field[i][j] = Sprite.SOL;
				if(j == 0 || i == 0 || j == x || i == y) {
					this.field[i][j] = Sprite.MUR;
				}
			}
		}
	}
}
