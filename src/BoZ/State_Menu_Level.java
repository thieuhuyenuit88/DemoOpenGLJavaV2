package BoZ;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.swing.JOptionPane;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;


public class State_Menu_Level extends IState {
	public int m_Final_Level;
	Texture m_Back;
	public Man_Cube_Level m_Box;
	
	public State_Menu_Level(IPlay _Play) {
		super(_Play);
		m_IDState=DefineCons.STATE_MENU_LEVEL;
		m_Final_Level=1;
		m_Box=new Man_Cube_Level();
	}
	
	public State_Menu_Level(IPlay _Play,int _Final_Level) {
		super(_Play);
		m_Final_Level=_Final_Level;
		m_IDState=DefineCons.STATE_MENU_LEVEL;
		m_Box=new Man_Cube_Level();
	}
	 public void Init (){
		 m_Back=LoadTexture("Image/BackLevel.png");
		 m_Back.enable();
		 try {
				SaveFile.ReadFile(DefineCons.SAVE_FILE);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 m_Box.Init(SaveFile.m_FinalLevel);
	    }
	
    public void Display (GLU glu,GL gl){
		glu.gluLookAt(0,-4,4, 0, 0, 0, 0.0, 1.0, 0.0);
		
		gl.glPushMatrix();
		m_Back.bind();
		gl.glTranslated(-10, 0, 0);
		gl.glBegin(GL.GL_QUADS);
		
        // Front Face
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( -15, 0, -12);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 35, 0, -12);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 35, 35, -12);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( -15, 35, -12);
        gl.glEnd();
        gl.glPopMatrix();
        
		m_Box.DisPlay(gl);
    }
    public void Destroy (){
        
    }
    public void Update (){
    	for(int i=0;i<7;i++)
    	{
    		if(m_Box.m_ListBox[i].m_Test_Choose==true)
    		{
    			m_Play.m_NextState=new State_MainGame(m_Play,i+1);
    			return;
    		}
    	}
    	m_Box.update();
    	
    }
    public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		m_Box.keyPressed(arg0);
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_ESCAPE:
			m_Play.m_NextState=new State_MainMenu(m_Play);
			break;
		}
	}
    
    public Texture LoadTexture(String _path){
		Texture _result;
		BufferedImage image;
		try{
			image = ImageIO.read(this.getClass().getResource(_path));
			_result = TextureIO.newTexture(image,false);
		}catch(IllegalArgumentException e){
			JOptionPane.showMessageDialog(null, "Can not load file \"" + _path + "\". The application will be exit !"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			//System.exit(0);
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		return _result;
		
	}

}
