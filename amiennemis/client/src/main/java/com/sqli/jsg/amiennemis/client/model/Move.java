package com.sqli.jsg.amiennemis.client.model;

import java.awt.Point;

public enum Move {
	HAUT {
		@Override
		public boolean isAuthorized(Move move) {
			return move != BAS;
		}

		@Override
		public Point getNewPosition(Point position) {
			Point newPosition = new Point(position);
			newPosition.translate(0, -1);
			return newPosition;
		}
	}, BAS {
		@Override
		public boolean isAuthorized(Move move) {
			return move != HAUT;
		}

		@Override
		public Point getNewPosition(Point position) {
			Point newPosition = new Point(position);
			newPosition.translate(0, 1);
			return newPosition;
		}
	}, GAUCHE {
		@Override
		public boolean isAuthorized(Move move) {
			return move != DROITE;
		}

		@Override
		public Point getNewPosition(Point position) {
			Point newPosition = new Point(position);
			newPosition.translate(-1, 0);
			return newPosition;
		}
	}, DROITE {
		@Override
		public boolean isAuthorized(Move move) {
			return move != GAUCHE;
		}

		@Override
		public Point getNewPosition(Point position) {
			Point newPosition = new Point(position);
			newPosition.translate(1, 0);
			return newPosition;
		}
	};
	
	public abstract boolean isAuthorized(Move move);
	public abstract Point getNewPosition(Point position);
}
