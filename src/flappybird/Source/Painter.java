package flappybird.Source;
/************************************************************
 *                                                          *
 *   Jacob Abbott                8728                       *
 *                                                          *
 *   Project 4A           AbbottJacobP4A                    *
 *                                                          *
 *   Starting Date: 9/16/13     Due Date: 9//13             *
 *                                                          *
 *   AP Computer Science Java Period 4                      *
 *                                                          *
 *                                                          *
 ************************************************************/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Painter extends JFrame implements KeyListener{

	JFrame gui = new JFrame();

	Pipes[] pipeob = new Pipes[5000];
	
	Thread[] pipethread = new Thread[5000];
	
	Bird bird = new Bird();
	Thread birdthread = new Thread(bird);
	
	Boolean firstrun = true;
	Boolean replay = true;
	
	int currentthreads = 0;
	int height = 200;
	int score = -2;
	
	Font fotn= new Font("Comic Sans", Font.BOLD, 20);
	
	Image Backgroundbottom = new ImageIcon("Resources/BackgroundBottom.png").getImage();
	Image Backgroundtop = new ImageIcon("Resources/BackgroundTop.png").getImage();
	Image Bird= new ImageIcon("Resources/Bird.png").getImage();
	Image Pipes = new ImageIcon("Resources/fullofdempipes.png").getImage();
	Image gameover = new ImageIcon("Resources/gameover.png").getImage();


	public Painter(){
		init(gui);
		this.setSize(450, 800);
		this.setVisible(true);
		this.addKeyListener(this);


		for (int i=0;i<5000;i++){
			pipeob[i]= new Pipes();  // create each actual shot
			pipethread[i] = new Thread(pipeob[i]);
		}
		repaint();
	}

	public void init(JFrame gui){
		JPanel pane1 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		pane1.setBackground(Color.white);
		pane1.setDoubleBuffered(true);  
		this.add(pane1);
		pipethread[currentthreads].start();
		birdthread.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(fotn);
		Random generator1 = new Random();
		height = bird.getBirdy();

		g.drawImage(Backgroundtop, 0, 0, 450, 644, null); 
		g.drawImage(Bird, 100, height, 40, 30, null); 
		for(int index = 0; index <= currentthreads; index++){
			g.drawImage(Pipes, pipeob[index].getpipex(), pipeob[index].getpipey(), null);	
		}
		g.drawImage(Backgroundbottom, 0, 644, 450, 156, null); 

		g.setColor (Color.black);
		if (score >=0)
			g.drawString("Score: " +score+"", 50, 50);
		else 
			g.drawString("Score: 0", 50, 50);

		if(pipeob[currentthreads].getpipex() <= 550){
			currentthreads ++;
			pipethread[currentthreads].start();
			score ++;
		}

		for(int index =0; index <currentthreads; index ++){
			if(((height < pipeob[index].getpipey()+730)||(height > pipeob[index].getpipey()+870 ))&&((pipeob[index].getpipex() < 100)&&(pipeob[index].getpipex() + 72 > 100))||
					((height < pipeob[index].getpipey()+730)||(height > pipeob[index].getpipey()+870 ))&&((pipeob[index].getpipex() < 140)&&(pipeob[index].getpipex() + 72 > 140))||
					((height+30 < pipeob[index].getpipey()+730)||(height+30 > pipeob[index].getpipey()+870 ))&&((pipeob[index].getpipex() < 100)&&(pipeob[index].getpipex() + 72 > 100))||
					((height+30 < pipeob[index].getpipey()+730)||(height+30 > pipeob[index].getpipey()+870 ))&&((pipeob[index].getpipex() < 140)&&(pipeob[index].getpipex() + 72 > 140))||
					((height+30>=644))
					){
				g.drawImage(gameover, 125, 200, null);
				JOptionPane.showMessageDialog(null, "You Dead. \n you scored " + score, "Oh no!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}
		repaint(); 
	}

	public void keyTyped(KeyEvent ke){}
	
	public void keyPressed(KeyEvent ke){
		if(ke.getKeyCode() == KeyEvent.VK_SPACE){
			bird.setTime(0, height);
		}
	}

	public void keyReleased(KeyEvent ke){}
}