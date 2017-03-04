package BoZ;

import java.awt.event.KeyEvent;

import javax.media.opengl.GL;

public class Man_Cube_Setting {
	public Item_Cube m_CubeOn;
	public Item_Cube m_CubeOff;
	public Boolean m_TestCome;
	public Boolean m_TestGo;
	public Boolean m_TestCom;
	public Boolean m_TestFinish;
	public float m_LeftPos;
	public float m_RightPos;
	public float m_LengPos;
	
	public Man_Cube_Setting()
	{
		m_CubeOn=new Item_Cube();
		m_CubeOff=new Item_Cube();
	}
	public void Init()
	{
		m_LeftPos=-5f;
		m_RightPos=5f;
		m_LengPos=4;
		m_CubeOn.Init();
		m_CubeOff.Init();
		m_CubeOff.m_Test_Unlock =true;
		m_CubeOn.m_Test_Unlock =true;
		m_CubeOff.m_Test_Select=false;
		m_CubeOn.m_Test_Select=true;
		m_CubeOn.LoadImage("Image/Music_On.png");
		m_CubeOff.LoadImage("Image/Music_Off.png");
		
		m_TestCome=false;
		m_TestGo=false;
		m_TestCom=false;
		m_TestFinish = false;
		
		m_CubeOn.m_Trans=new Point3D(m_LeftPos, 1.5f, -2);
		m_CubeOff.m_Trans=new Point3D(m_RightPos, 1.5f, -2);
	}
	public void Update() 
	{
		m_CubeOff.update();
		m_CubeOn.update();
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
		m_CubeOn.m_Trans.X+=0.1;
		m_CubeOff.m_Trans.X-=0.1;
		if(m_CubeOn.m_Trans.X>=m_LeftPos+m_LengPos&&m_CubeOff.m_Trans.X<=m_RightPos-m_LengPos)
		{
			m_TestCome=false;
			m_TestCom=true;
		}
	}
	public void Go()
	{
		m_CubeOn.m_Trans.X-=0.1;
		m_CubeOff.m_Trans.X+=0.1;
		if(m_CubeOn.m_Trans.X<=m_LeftPos&&m_CubeOff.m_Trans.X>=m_RightPos)
		{
			m_TestGo=false;
			m_TestCom=false;
			m_TestFinish = true;
		}
	}
	public void Display(GL gl)
	{
		m_CubeOff.DisPlay(gl);
		m_CubeOn.DisPlay(gl);
	}
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode())
		{
		case KeyEvent.VK_LEFT:	
			if(m_CubeOff.m_Test_Select==true)	
			{
				m_CubeOff.m_Test_Select=false;
				m_CubeOn.m_Test_Select=true;
			}
			else
			{
				m_CubeOff.m_Test_Select=true;
				m_CubeOn.m_Test_Select=false;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(m_CubeOff.m_Test_Select==true)	
			{
				m_CubeOff.m_Test_Select=false;
				m_CubeOn.m_Test_Select=true;
			}
			else
			{
				m_CubeOff.m_Test_Select=true;
				m_CubeOn.m_Test_Select=false;
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

