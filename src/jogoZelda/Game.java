package jogoZelda;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	//definir tamanho da janela
	public static int WIDTH = 640, HEIGHT = 480;
	public static int SCALE = 3;
	public Player player;
	
	public World world;
	
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		new Spritesheet();
		player = new Player(32,32);//posição inicial do player
		world = new World();
	}
	
	//método da lógica do jogo
	public void tick() {
		player.tick();
	}
	
	//redenrização dos gráficos do jogo
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		player.render(g);
		
		world.render(g);
		
		bs.show();
	}
	
	//Método principal
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("Java Game");//titulo do jogo
		
		frame.pack();
		frame.setLocationRelativeTo(null);//deixa janela centralizada
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//metodo para finalizar java assim que fechar a janela
		
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	@Override
	public void run() {
		
		while(true){
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// lógica para movimentar ao precionar a tecla
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			player.shoot = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			player.down = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// lógica para parar movimento
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			player.down = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

}
