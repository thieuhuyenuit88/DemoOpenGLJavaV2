package BoZ;

import java.awt.event.KeyEvent;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;


public class State_MainGame extends IState {
	Level m_Level=new Level();
	public int m_StartLevel;
	public State_MainGame(IPlay _Play) {
		super(_Play);
		m_IDState=DefineCons.STATE_MAINGAME;
		m_StartLevel=1;
	}
	public State_MainGame(IPlay _Play,int _StartLevel) {
		super(_Play);
		m_StartLevel=_StartLevel;
		m_IDState=DefineCons.STATE_MAINGAME;
	}
	 public void Init (){
		 
		 m_Level.Init(m_StartLevel);
	    }
	    public void Display (GLU glu,GL gl){
	    	if(m_Level.m_TestStart==false)
			{
				glu.gluLookAt(0,0,40, 0, 0, 0, 0.0, 1.0, 0.0);
			}
			else
				{
					glu.gluLookAt(10,-5,10, 10, 5, 0, 0.0, 1.0, 0.0);
				}
			m_Level.Display(gl);
			if(m_Level.m_TestOver==true)
			{
				m_Play.m_NextState=new State_Menu_Level(m_Play);
			}
	    }
	    public void Destroy (){
	        
	    }
	    public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			m_Level.keyPressed(arg0);
		}

}
