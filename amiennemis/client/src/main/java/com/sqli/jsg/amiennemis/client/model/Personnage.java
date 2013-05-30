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
		if(requestPosition.x > field.x-1) {
			requestPosition.x = field.x;
		}
		if(requestPosition.y > field.y-1) {
			requestPosition.y = field.y;
		}
		if(requestPosition.x < 1) {
			requestPosition.x = 1;
		}
		if(requestPosition.y < 1) {
			requestPosition.y = 1;
		}
		
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
