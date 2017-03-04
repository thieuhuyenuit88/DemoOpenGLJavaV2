package BoZ;

import java.awt.event.KeyEvent;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;


public class GamePlay extends IPlay {
    public GamePlay (){
        m_CurState = new State_MainMenu(this);
        m_NextState = m_CurState;        
    }
    public void Init (){
        m_CurState.Init();
    }
    public void Update (){
        
    }
    public void Display (GLU glu,GL gl){
    	m_CurState.Update();
        if(m_CurState.m_IDState != m_NextState.m_IDState){
            m_CurState = m_NextState;
            m_CurState.Init();
        }else{
             m_CurState.Display(glu,gl);
        }
    }
    public void Destroy (){
        
    }
    public void keyPressed(KeyEvent arg0) {
    	m_CurState.keyPressed(arg0);
	}
}
