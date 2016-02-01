package flappybird.Source;

public class Bird implements Runnable{
	int birdy = 200;
	int velocity = 0;
	int time = 0;
	int acc = 9;
	int counter = 0;
	int height = 200;

	public void run(){

		while(true){
			birdy = (int)(((velocity * time) + (.5 * acc * time * time))/20 + height);
			time++;
			counter++;
			velocity = -140;
			try {
				Thread.sleep(25);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			//gdog.repaint();  

		}//end while

	}//end run

	public int getBirdy(){
		return birdy;	
	}	

	public void setTime(int timetemp, int tempheight){
		time = timetemp;
		height = tempheight;		
	}
}//end shotsfired