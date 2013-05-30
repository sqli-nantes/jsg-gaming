package com.sqli.jsg.amiennemis.client.model;

import java.awt.Point;

public class Poursuivant extends Personnage {
	
	public Poursuivant(int id, Point position) {
		super(id, position);
		this.spriteType = Sprite.POURSUIVANT;
	}
}
