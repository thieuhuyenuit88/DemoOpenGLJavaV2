package BoZ;

import java.awt.event.KeyEvent;

import javax.media.opengl.GL;

public class Man_Cube_Level {
	public Item_Cube[] m_ListBox=new Item_Cube[7];
	public int m_Index_Select=0;
	public Boolean m_TestGoto=false;
	public int m_Index_Goto=0;
	public float m_Temp=0;
	public Man_Cube_Level()
	{
		for(int i=0;i<7;i++)
		{
			m_ListBox[i]=new Item_Cube();
		}
		
	}
	public void Init(int _finalLevel)
	{
		for(int i=0;i<_finalLevel;i++)
			m_ListBox[i].m_Test_Unlock=true;
		for(int i=0;i<7;i++)
		{
			m_ListBox[i].Init();
			m_ListBox[i].m_Trans.X=-6+i*2;
			int j=1+i;
			m_ListBox[i].LoadImage("Image/LevelBox"+j+".png");
		}
		m_ListBox[0].m_Test_Select=true;
		
		m_Index_Select=0;
		for(int i=0;i<7;i++)
		{
			m_ListBox[i].m_Trans.X+=6;
		}
		
	}
	public void DisPlay(GL gl)
	{
		update();
		for(int i=0;i<7;i++)
		{
			m_ListBox[i].DisPlay(gl);
		}
	}
	public void Reset()
	{
		for(int i=0;i<7;i++)
		{
			m_ListBox[i].m_Test_Select=false;
		}
	}
	public void update()
	{
		Goto();
		for(int i=0;i<7;i++)
		{
			m_ListBox[i].update();
		}
		Reset();
		m_ListBox[m_Index_Select].m_Test_Select=true;
	}
	public void Goto()
	{
		if(m_TestGoto==true)
		{
			
			if(m_Index_Goto<0)
			{
				for(int i=0;i<7;i++)
				{
					m_ListBox[i].m_Trans.X-=0.05f;
				}
					m_Temp-=0.05f;
					if(m_Temp<=2*m_Index_Goto)
						{
							m_TestGoto=false;
							m_Temp=0;
							m_Index_Goto=0;
						}
			}
			if(m_Index_Goto>0)
			{
				for(int i=0;i<7;i++)
				{
					m_ListBox[i].m_Trans.X+=0.05f;
				}
					m_Temp+=0.05f;
					if(m_Temp>=2*m_Index_Goto)
						{
							m_TestGoto=false;
							m_Temp=0;
							m_Index_Goto=0;
						}
			}
		
		}
	}
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_LEFT:	
			if(m_TestGoto==false)
			{
				
				m_Index_Select-=1;
				if(m_Index_Select<0)
				{
					m_Index_Select=6;
					m_Index_Goto=-6;
					m_TestGoto=true;
				}
				else
					{
						m_Index_Goto=1;
						m_TestGoto=true;
					}
				
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(m_TestGoto==false)
			{
				
				m_Index_Select+=1;
				if(m_Index_Select>6)
				{
					m_Index_Select=0;
					m_Index_Goto=+6;
					m_TestGoto=true;
				}
				else
					{
						m_Index_Goto=-1;
						m_TestGoto=true;
					}
				
			}
			break;
		case KeyEvent.VK_SPACE:
			if(m_TestGoto==false)
			{
				if(m_ListBox[m_Index_Select].m_Test_Unlock==true)
				m_ListBox[m_Index_Select].m_Test_Choose=true;
				
			}
			break;

		}
	}
	
}
