package flappybird.Source;

import java.util.Random;


public class Pipes implements Runnable{
	int pipex = 800;
	int pipey = 0;
	double slope = 0;
	Random generator = new Random();



	public void run(){

		Random generator = new Random();
		int var = generator.nextInt(400)+ 1;
		pipey = -630 + (var);
		//slope = generator.nextInt(3)-1;

		while(pipex >= -80){
			pipex -= 3;

			try {
				Thread.sleep(25);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			//gdog.repaint();  

		}//end while

	}//end run

	public int getpipex(){
		return pipex;	
	}	

	public int getpipey(){
		return pipey;	
	}	

}//end shotsfired