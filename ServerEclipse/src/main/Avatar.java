package main;

import processing.core.PApplet;

public class Avatar {

	
	private int x;
	private int y;
	private int color;
	private Main main;
	
	private boolean upMovement = false;
	private boolean downMovement = false;
	private boolean leftMovement = false;
	private boolean rightMovement = false;
	
	
	public Avatar(Main main, int x, int y, int color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.main = main;
	}
	
	public void pintar() {
		
		main.fill(this.color);
		main.ellipse(this.x, this.y, 50, 50);
		
		if (upMovement) {
			this.y-=2;
			
			if (y<= 25) {
				
				y += 2;
			}
		}
		
		if (downMovement) {
			this.y+=2;
			
			if (y>= 475) {
				
				y -= 2;
			}
		}
		
		if (leftMovement) {
			this.x-=2;
			
			if (x<= 25) {
				
				x += 2;
			}
		}
		
		if (rightMovement) {
			this.x+=2;
			if (x>= 475) {
				
				x -= 2;
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void activateUpMovement() {
		// TODO Auto-generated method stub
		upMovement=true;
	}
	
	public void desactivateUpMovement() {
		// TODO Auto-generated method stub
		upMovement=false;
	}

	public void activateDownMovement() {
		// TODO Auto-generated method stub
		downMovement=true;
	}

	public void desactivateDownMovement() {
		// TODO Auto-generated method stub
		
		downMovement=false;
	}

	public void activateRightMovement() {
		// TODO Auto-generated method stub
		rightMovement=true;
	}

	public void desactivateRightMovement() {
		// TODO Auto-generated method stub
		rightMovement=false;
	}

	public void activateLeftMovement() {
		// TODO Auto-generated method stub
		leftMovement=true;
	}

	public void desactivateLeftMovement() {
		// TODO Auto-generated method stub
		leftMovement=false;
	}
	
	
	
	
	
}
