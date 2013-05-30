package com.sqli.jsg.amiennemis.client.model;

import java.awt.Point;

public abstract class Personnage {
	protected Point position;
	

	protected Point oldPosition;
	protected Sprite spriteType;
	protected int id;
	protected Move currentDirection;
	

	public Personnage(int id, Point position) {
		this.id = id;
		this.position = position;
		this.oldPosition = position;
	}


	
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
	
	public int getId() {
		return id;
	}
	
	public void setPosition(Point position) {
		this.oldPosition = this.position;
		this.position = position;
	}
	
	public Point getOldPosition() {
		return oldPosition;
	}
}
