package BoZ;

import java.awt.event.KeyEvent;

import javax.media.opengl.GL;

public class Man_Cube_Pause {
	public Item_Cube m_CubeResume;
	public Item_Cube m_CubeExit;
	public Boolean m_TestCome;
	public Boolean m_TestGo;
	public Boolean m_TestCom;
	public float m_LeftPos;
	public float m_RightPos;
	public float m_LengPos;
	public Man_Cube_Pause()
	{
		m_CubeResume=new Item_Cube();
		m_CubeExit=new Item_Cube();
	}
	public void Init()
	{
		m_LeftPos=4;
		m_RightPos=16;
		m_LengPos=5;
		m_CubeResume.Init();
		m_CubeExit.Init();
		m_CubeExit.m_Test_Select=false;
		m_CubeResume.m_Test_Select=true;
		m_CubeResume.LoadImage("abc");
		m_CubeExit.LoadImage("abc");
		m_TestCome=false;
		m_TestGo=false;
		m_TestCom=false;
		
		m_CubeResume.m_Trans=new Point3D(m_LeftPos,-1, 6);
		m_CubeExit.m_Trans=new Point3D(m_RightPos, -1, 6);
	}
	public void Update() 
	{
		m_CubeExit.update();
		m_CubeResume.update();
		if(m_TestCome==true)
		{
			Come();
		}
		if(m_TestGo==true)
		{
			Go();
		}
	}
	public void Come()
	{
		m_CubeResume.m_Trans.X+=0.1;
		m_CubeExit.m_Trans.X-=0.1;
		if(m_CubeResume.m_Trans.X>=m_LeftPos+m_LengPos&&m_CubeExit.m_Trans.X<=m_RightPos-m_LengPos)
		{
			m_TestCome=false;
			m_TestCom=true;
		}
	}
	public void Go()
	{
		m_CubeResume.m_Trans.X-=0.1;
		m_CubeExit.m_Trans.X+=0.1;
		if(m_CubeResume.m_Trans.X<=m_LeftPos&&m_CubeExit.m_Trans.X>=m_RightPos)
		{
			m_TestGo=false;
			m_TestCom=false;
		}
	}
	public void Display(GL gl)
	{
		m_CubeExit.DisPlay(gl);
		m_CubeResume.DisPlay(gl);
	}
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode())
		{
		case KeyEvent.VK_ESCAPE:
			if(m_TestCome==false&&m_TestGo==false&&m_TestCom==false)
			{
				m_TestCome=true;
			}

			break;
		case KeyEvent.VK_LEFT:	
			if(m_CubeExit.m_Test_Select==true)	
			{
				m_CubeExit.m_Test_Select=false;
				m_CubeResume.m_Test_Select=true;
			}
			else
			{
				m_CubeExit.m_Test_Select=true;
				m_CubeResume.m_Test_Select=false;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(m_CubeExit.m_Test_Select==true)	
			{
				m_CubeExit.m_Test_Select=false;
				m_CubeResume.m_Test_Select=true;
			}
			else
			{
				m_CubeExit.m_Test_Select=true;
				m_CubeResume.m_Test_Select=false;
			}
			break;
		case KeyEvent.VK_SPACE:
			if(m_TestCome==false&&m_TestGo==false&&m_TestCom==true)
			{
				m_TestGo=true;
			}
			break;
		}
	}
}
