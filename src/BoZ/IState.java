package BoZ;

import java.awt.event.KeyEvent;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;


public class IState {
    public IPlay m_Play;
    public int m_IDState;
    public IState (IPlay _Play){
    	m_Play=_Play;
    }
 
    public void Init (){
        
    }
    public void Update (){
        
    }
    public void Display (GLU glu,GL gl){
        
    }
    public void Destroy (){
        
    }
    public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
