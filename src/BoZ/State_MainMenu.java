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


public class State_MainMenu extends IState {
	Texture m_Back;
	public Man_Cube_Menu m_Box;
	
	public State_MainMenu(IPlay _Play) {
		super(_Play);
		m_IDState=DefineCons.STATE_MENU;
		m_Box=new Man_Cube_Menu();
	}
	
	 public void Init (){
		 m_Back=LoadTexture("Image/Menu_Screen.png");
		 m_Back.enable();
		 m_Box.Init();
	    }
	
    public void Display (GLU glu,GL gl){
		glu.gluLookAt(0,-4,4, 0, 0, 0, 0.0, 1.0, 0.0);
		
		gl.glPushMatrix();
		m_Back.bind();
		gl.glTranslated(-8, -8.4f, 0);
		gl.glRotatef(45, 1, 0, 0);
		gl.glScalef(0.02f, 0.02f, 0.02f);
		gl.glBegin(GL.GL_QUADS);
		
        // Front Face
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 0, 0, -680);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 800, 0, -680);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 800, 600, -680);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 0, 600, -680);
        
        gl.glEnd();
        gl.glPopMatrix();
        
		m_Box.DisPlay(gl);
    }
    public void Destroy (){
        
    }
    public void Update (){
    	for(int i=0;i<5;i++)
    	{
    		if(m_Box.m_ListBox[i].m_Test_Choose==true)
    		{
    			switch (i) {
					case 0:
						m_Play.m_NextState=new State_Menu_Level(m_Play);
						break;
					case 1:
						m_Play.m_NextState=new State_AboutMenu(m_Play);
						break;
					case 2:
						m_Play.m_NextState=new State_HelpMenu(m_Play);
						break;
					case 3:
						System.exit(0);
						break;
					case 4:
						m_Play.m_NextState=new State_SettingMenu(m_Play);
						break;
				}
    				
    		}
    	}
    	m_Box.update();
    	
    }
    public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		m_Box.keyPressed(arg0);
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
