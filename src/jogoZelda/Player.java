package jogoZelda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle{
	
	//velocidade do player
	public int spd = 4;
	
	//direções do player
	public boolean right,up,down,left;
	
	//animação
	public int curAnimation = 0;
	
	//velovidade da animação
	public int curFrames = 0, targetFrames = 15;
	
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	
	public boolean shoot = false;

	public Player(int x, int y) {
		super(x,y,32,32);
	}
	
	public void tick() {
		boolean moved = false;
		if(right && World.isFree(x+spd, y)) {
			x+=spd;
			moved = true;
		}else if(left && World.isFree(x-spd, y)) {
			x-=spd;
			moved = true;
		}
		
		if(up && World.isFree(x, y-spd)) {
			y-=spd;
			moved = true;
		}else if(down && World.isFree(x, y+spd)) {
			y+=spd;
			moved = true;
		}
		
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if(curAnimation == Spritesheet.player_front.length) {
					curAnimation = 0;
				}
			}	
		}
		
		if(shoot) {
			shoot = false;
			bullets.add(new Bullet(x,y,1));
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.blue);
		//g.fillRect(x, y, width, height);
		g.drawImage(Spritesheet.player_front[curAnimation], x, y , 32, 32, null);
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
}