package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gson.Gson;

import processing.core.PApplet;

public class Main extends PApplet {

	int xBolita = -1000;
	int yBolita = -1000;

	private Avatar player1;
	private Avatar player2;

	private TCPConnectionP1 conexionJ1;
	private TCPConnectionP2 conexionJ2;
	
	private ArrayList<Bala> bala;
	private ArrayList<Bala> bala2;
	
	private boolean deadStatus=false;
	private int jugador;
	
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	
	public void settings() {
		size(500, 500);
	}

	
	public void setup() {
		conexionJ1 = new TCPConnectionP1();
		
		conexionJ1.setMain(this);
		conexionJ1.start();

		conexionJ2 = new TCPConnectionP2();
		
		conexionJ2.setMain(this);
		conexionJ2.start();

		player1 = new Avatar(this, 100, 100, color(255, 0, 0));
		player2 = new Avatar(this, 400, 400, color(0, 0, 255));
		
		bala = new ArrayList<>();
		bala2 = new ArrayList<>();
	}

	public void draw() {
		background(0);
		
		rectMode(CENTER);
		

		player1.pintar();
		player2.pintar();
		
		impactoPlayer1();
		impactoPlayer2();
		
		if (deadStatus) {
			endgame();
		}
	
		
		for (int i = 0; i < bala.size(); i++) {
			
			Bala b = bala.get(i);
			b.pintarBala();
			if (b.getPosX()<0) {
				bala.remove(i);
				break;
			}
		}
		
		for (int i = 0; i < bala2.size(); i++) {
			
			Bala b2 = bala2.get(i);
			b2.pintarBala();
			if (b2.getPosX()>500) {
				bala2.remove(i);
				break;
			}
		}
	}
	
	public void impactoPlayer1() {
		
		for (int i = 0; i < bala.size(); i++) {
			
			int posX1=bala.get(i).getPosX();
			int posY1=bala.get(i).getPosY();
			int posX2=player2.getX();
			int posY2=player2.getY();
			
			if (PApplet.dist(posX1, posY1, posX2, posY2) < 50) {
				
				bala.remove(i);
				jugador=1;
				deadStatus=true;
				System.out.println("Impacto al Jugador 2");
				
			}
		}
	}
	
	public void impactoPlayer2() {
		
		for (int i = 0; i < bala2.size(); i++) {
			
			int posX1=bala2.get(i).getPosX();
			int posY1=bala2.get(i).getPosY();
			int posX2=player1.getX();
			int posY2=player1.getY();
			
			if (PApplet.dist(posX1, posY1, posX2, posY2) < 50) {
				
				bala2.remove(i);
				jugador=2;
				deadStatus=true;
				System.out.println("Impacto al Jugador 1");
				
			}
		}
	}
	
	public void endgame() {
		
		String ganador = null;
		
		if (jugador==1) {
			
			ganador="Jugador1";
		}
		
		if (jugador==2) {
			
			ganador="Jugador2";
		}
		
			fill(255);
			rect(250, 250, 200, 150);
			
			fill(0);
			textAlign(CENTER);
			text(ganador+" es el ganador", 255, 250);
			text("Presione ESPACIO para jugar", 255, 250+20);
			
	}
	
	public void keyPressed() {
		
		if (deadStatus) {
			if (keyCode == ' ') {
				
				deadStatus=false;
				player1 = new Avatar(this, 100, 100, color(255, 0, 0));
				player2 = new Avatar(this, 400, 400, color(0, 0, 255));
				bala.clear();
				bala2.clear();
			}
		}
	}

	
	public void notificar(Coordenada c, Object obj) {

		
		if (obj instanceof TCPConnectionP1) {
			System.out.println("JUGADOR 1:" + c.getAccion());
			switch (c.getAccion()) {

			case "DOWNSTART":
				player1.activateDownMovement();
				break;
			case "DOWNSTOP":
				player1.desactivateDownMovement();
				break;
				
			case "UPSTART":
				player1.activateUpMovement();
				break;
			case "UPSTOP":
				player1.desactivateUpMovement();
				break;	
				
			case "RIGHTSTART":
				player1.activateRightMovement();
				break;
			case "RIGHTSTOP":
				player1.desactivateRightMovement();
				break;
				
			case "LEFTSTART":
				player1.activateLeftMovement();
				break;
			case "LEFTSTOP":
				player1.desactivateLeftMovement();
				break;
				
			case "FIRE":
				
				if (deadStatus==false) {
					Bala balaJ1 = new Bala(player1.getX()+50,player1.getY(), true, this);
					bala.add(balaJ1);
				}
				
				break;

			}
		}

		else if (obj instanceof TCPConnectionP2) {
			System.out.println("JUGADOR 2:" + c.getAccion());
			switch (c.getAccion()) {
			
			case "DOWNSTART":
				player2.activateDownMovement();
				break;
			case "DOWNSTOP":
				player2.desactivateDownMovement();
				break;
				
			case "UPSTART":
				player2.activateUpMovement();
				break;
			case "UPSTOP":
				player2.desactivateUpMovement();
				break;	
				
			case "RIGHTSTART":
				player2.activateRightMovement();
				break;
			case "RIGHTSTOP":
				player2.desactivateRightMovement();
				break;
				
			case "LEFTSTART":
				player2.activateLeftMovement();
				break;
			case "LEFTSTOP":
				player2.desactivateLeftMovement();
				break;
				
			case "FIRE":
				
				if (deadStatus==false) {
					
					Bala balaJ2 = new Bala(player2.getX()-50,player2.getY(), false, this);
					bala2.add(balaJ2);
				}
				
				break;

			}
		}

	}

}
