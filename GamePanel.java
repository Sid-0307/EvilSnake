
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math.*;
import java.util.Random; 

public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 500;
	static final int SCREEN_HEIGHT = 500;
	static final int UNIT_SIZE = 10;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 100; //delay for game
	static int count=0;
	static int score=0;
	static int repeat=0;
	static int c=0;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS]; //snake coordinates
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
  char directionsnake='R';
	boolean running = false;
	Timer timer;
	Random random=new Random();
  int randomx,randomy;
  //Game Screen
	GamePanel(){
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter()); //keyboard control
    appleX=100;
    appleY=100;
		startGame();
	}

  //Start game
	public void startGame() {
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}

  //Paint,repaint and colour changes of snake based on the score
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
    if(score<30)
		  draw(g);
    else if(score>=30 && score<60)
      draw(g,score);
    else if(score>=60 && score<90)
      draw(g,score,score);
    else
      draw(g,score,score,score);
	}

  //Level 1:score<30
	public void draw(Graphics g) {
		
		if(running) {
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			} //set graph
			g.setColor(Color.magenta);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); //apple coords
		
			for(int i = 0; i< bodyParts;i++) {
				if(i == 0) {
					g.setColor(new Color(245, 255, 135));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); //snake head
				}
				else {
					g.setColor(new Color(231, 245, 73));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); //snake body
				}			
			}
			g.setColor(Color.red); //score display
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+score, (SCREEN_WIDTH - metrics.stringWidth("Score: "+score))/2, g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
		
	}

  //Level 2: 30<=score<60
	public void draw(Graphics g,int scorex) {
		
		if(running) {
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			g.setColor(Color.magenta);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
			for(int i = 0; i< bodyParts;i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					g.setColor(new Color(45,180,0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}			
			}
			g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+score, (SCREEN_WIDTH - metrics.stringWidth("Score: "+score))/2, g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
		
	}

  //Level 3: 60<=score<90
	public void draw(Graphics g,int scorex,int scorey) {
		
		if(running) {
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			g.setColor(Color.magenta);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
			for(int i = 0; i< bodyParts;i++) {
        if(i==0){
					g.setColor(new Color(94, 181, 247));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}		
        else
        {
					g.setColor(new Color(10, 149, 255));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);	
        }
      }
			g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+score, (SCREEN_WIDTH - metrics.stringWidth("Score: "+score))/2, g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
		
	}

  //Level 4: score>=90
	public void draw(Graphics g,int scorex,int scorey,int scorez) {
		
		if(running) {
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			g.setColor(Color.magenta);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
			for(int i = 0; i< bodyParts;i++) {
				if(i%2== 0) {
					g.setColor(Color.red);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					g.setColor(new Color(117, 0, 0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}			
			}
			g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+score, (SCREEN_WIDTH - metrics.stringWidth("Score: "+score))/2, g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
		
	}

  //Controls to Move apple
	public void moveapple(){

  	//border cases
    if(appleX==0 && appleY==0)
    {
      switch(direction) {
		  case 'U':
			  break;
		  case 'D':
			  appleY = appleY + UNIT_SIZE;
			  break;
		  case 'L':
			  break;
		  case 'R':
			  appleX = appleX + UNIT_SIZE;
			  break;
    }
    }
    else if(appleX==0 && appleY==SCREEN_HEIGHT-UNIT_SIZE)
    {
      switch(direction) {
		  case 'U':
        appleY = appleY - UNIT_SIZE;
			  break;
		  case 'D':
			  break;
		  case 'L':
			  break;
		  case 'R':
			  appleX = appleX + UNIT_SIZE;
			  break;
    }
    }
    else if(appleX==SCREEN_WIDTH-UNIT_SIZE && appleY==0)
    {
      switch(direction) {
		  case 'U':
			  break;
		  case 'D':
			  appleY = appleY + UNIT_SIZE;
			  break;
		  case 'L':
        appleX = appleX - UNIT_SIZE;
			  break;
		  case 'R':
			  break;
    }
    }
    else if(appleX==SCREEN_WIDTH-UNIT_SIZE && appleY==SCREEN_HEIGHT-UNIT_SIZE)
    {
      switch(direction) {
		  case 'U':
        appleY = appleY - UNIT_SIZE;
			  break;
		  case 'D':
			  break;
		  case 'L':
        appleX = appleX - UNIT_SIZE;
			  break;
		  case 'R':
			  break;
    }
    }
    else if(appleX==0)
    {
      switch(direction) {
		  case 'U':
			  appleY = appleY - UNIT_SIZE;
			  break;
		  case 'D':
			  appleY = appleY + UNIT_SIZE;
			  break;
		  case 'L':
        appleX = 0;
			  break;
		  case 'R':
			  appleX = appleX + UNIT_SIZE;
			  break;
      }
    }
    else if(appleY==0)
    {
      switch(direction) {
		  case 'U':
			  break;
		  case 'D':
			  appleY = appleY + UNIT_SIZE;
			  break;
		  case 'L':
        appleX = appleX - UNIT_SIZE;
			  break;
		  case 'R':
			  appleX = appleX + UNIT_SIZE;
			  break;
      }
    }
    else if(appleX==SCREEN_WIDTH-UNIT_SIZE)
    {
      switch(direction) {
		  case 'U':
			  appleY = appleY - UNIT_SIZE;
			  break;
		  case 'D':
			  appleY = appleY + UNIT_SIZE;
			  break;
		  case 'L':
			  appleX = appleX - UNIT_SIZE;
			  break;
		  case 'R':
			  break;
      }
    }
    else if(appleY==SCREEN_HEIGHT-UNIT_SIZE)
    {
      switch(direction) {
		  case 'U':
			  appleY = appleY - UNIT_SIZE;
			  break;
		  case 'D':
			  break;
		  case 'L':
			  appleX = appleX - UNIT_SIZE;
			  break;
		  case 'R':
			  appleX = appleX + UNIT_SIZE;
			  break;
      }
    }

  //other cases
    else
    {
      switch(direction) {
		  case 'U':
			  appleY = appleY - UNIT_SIZE;
			  break;
		  case 'D':
			  appleY = appleY + UNIT_SIZE;
			  break;
		  case 'L':
			  appleX = appleX -UNIT_SIZE;
			  break;
		  case 'R':
			  appleX = appleX + UNIT_SIZE;
			  break;
      }
    }
  }

  //Checking collisions of apple with any part of the snake
	public void checkcollisions() {
  for(int i=0;i<bodyParts;i++)
    {  
		  if((x[i] == appleX) && (y[i] == appleY)) 
      {
        running=false;
		  }
    }
	}

  //Movement of snake
	public void movesnake(){
    for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(directionsnake) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
      y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}

  //brain of snake
  public void snakeai()
  {
    int ydiff,xdiff;
    ydiff=appleY-y[0];
    xdiff=appleX-x[0];
    if(Math.abs(xdiff)>Math.abs(ydiff))
    {
      if(xdiff>=0)
        directionsnake='R';
      else
        directionsnake='L';
    }
    else
    {
      if(ydiff<=0)
        directionsnake='U';
      else
        directionsnake='D';
    }
    movesnake();
  }

  //Game Over
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+score, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+score))/2, g.getFont().getSize());
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			moveapple();
			checkcollisions();
      if(score<30)
      { 
        if((count)%2==0)
        {
          if((count%10)==0)
          { score++;
            bodyParts++;
            snakeai();
          }
          snakeai();
          checkcollisions();
        }
      }
      else if(score<60 && score>=30)
      {
        if((count%(3))==0)
        {
          if((count%6)==0)
          { score++;
            bodyParts++;
            snakeai();
          }
          snakeai();

        }
        if(count%5==0)
          snakeai();
        checkcollisions();
      }
      else if(score<90 && score>=60)
      {
        if(count%3==0)
        {
          if((count%6)==0)
          { score++;
            bodyParts++;
            c++;
            if(c!=1)
              snakeai();
          }
          snakeai();
        }
        if(count%2==0)
          snakeai();
        checkcollisions();
      }
      else if(count>=90)
      {
        snakeai();
        if(count%2==0)
        { snakeai();
          if(score!=99)
            score++;
        }
        else 
        checkcollisions();
      }
      count++;
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				direction = 'L';
				break;
			case KeyEvent.VK_RIGHT:
				direction = 'R';
				break;
			case KeyEvent.VK_UP:
				direction = 'U';
				break;
			case KeyEvent.VK_DOWN:
				direction = 'D';
				break;
			}
		}
	}
}