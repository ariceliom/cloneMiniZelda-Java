package jogoZelda;

import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.Color;

public class Blocks extends Rectangle{

	public Blocks(int x, int y) {
		super(x,y,32,32);
	}
	
	public void render(Graphics g) {
		
		//g.drawImage(Spritesheet.tileWall, x, y, 32, 32, null);//definir imagem para borda
		
		//define cor do bloco
		g.setColor(Color.BLACK);
		//define borda máxima
		g.fillRect(x, y, width, height);
		//define cor da linha de borda
		//g.setColor(Color.black);
		//define a borda
		//g.drawRect(x, y, width, height);
	}
}