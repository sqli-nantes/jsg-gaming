package com.sqli.jsg.amiennemis.client.model;

public interface Game {
	Sprite[][] getGrille();
	void move(Move move);
}
