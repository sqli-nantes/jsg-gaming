package com.sqli.jsg.amiennemis.client.model;

import java.awt.Point;

public abstract class Personnage {
	protected Point position;
	protected Point oldPosition;
	protected Sprite spriteType;
	protected int id;
	
	public Personnage(int id, Point position) {
		this.id = id;
		this.position = position;
		this.oldPosition = position;
	}

	public Point getOldPosition() {
		return oldPosition;
	}

	protected Move currentDirection;
	
	public void move(Move moveRequest, Field field) {
		oldPosition = position;
		Point requestPosition = moveRequest.getNewPosition(position);
		if(currentDirection.isAuthorized(moveRequest)) {
			position = requestPosition;
			currentDirection = moveRequest;
		}
		else {
			position = currentDirection.getNewPosition(position);
		}
	}
	
	public Point getPosition() {
		return position;
	}

	public Sprite getSpriteType() {
		return this.spriteType;
	}
	
}
