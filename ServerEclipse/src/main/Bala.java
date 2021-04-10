package main;

import processing.core.PApplet;

public class Bala {

	private PApplet app;
	private int posX, posY;
	private boolean moveRight;
	
	public Bala(int posX, int posY, boolean moveRight, PApplet app) {
		this.app=app;
		this.posX=posX;
		this.posY=posY;
		this.moveRight=moveRight;
	}
	
	public void pintarBala() {
		
		app.fill(255);
		app.ellipse(this.posX, this.posY, 10, 10);
		
		if(moveRight) {
			posX+=5;
		}else {
			posX-=5;
		}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isMoveRight() {
		return moveRight;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	
	
}
