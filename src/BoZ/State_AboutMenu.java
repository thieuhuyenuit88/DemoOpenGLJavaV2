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


public class State_AboutMenu extends IState {
	Texture m_Back;
	
	public State_AboutMenu(IPlay _Play) {
		super(_Play);
		m_IDState=DefineCons.STATE_ABOUT;
	}
	
	 public void Init (){
		 m_Back=LoadTexture("Image/About_Screen.png");
		 m_Back.enable();
		 
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
    }
    public void Destroy (){
        
    }
    public void Update (){
    	
    }
    public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
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

