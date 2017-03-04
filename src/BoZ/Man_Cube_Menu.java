package BoZ;

import java.awt.event.KeyEvent;

import javax.media.opengl.GL;

public class Man_Cube_Menu {
	public Item_Cube[] m_ListBox=new Item_Cube[5];
	public Point3D[] m_ListPos = new Point3D[5];
	public int m_Flag[] = new int[5];
	public int m_Index_Select = 0;
	public int m_Next_Index = 0;
	public int m_Temp = 0;
	public Boolean m_TestGoto=false;
	
	public Man_Cube_Menu()
	{
		for(int i=0;i<5;i++)
		{
			m_ListBox[i]=new Item_Cube();
		}
		InitFlag();
		m_ListPos[0] = new Point3D(-6, 0, 0);
		m_ListPos[4] = new Point3D(-8, 0.5f, -2);
		m_ListPos[1] = new Point3D(-4, 0.5f, -2);
		m_ListPos[3] = new Point3D(-7, 0.5f, -4);
		m_ListPos[2] = new Point3D(-5, 0.5f, -4);
	}
	
	public void Init()
	{
		for(int i=0;i<5;i++)
		{
			m_ListBox[i].Init();
			m_ListBox[i].m_Test_Unlock = true;
			if(i==0)
			{
				m_ListBox[i].LoadImage("Image/Start_Menu.png");
			}else
				if(i==1)
				{
					m_ListBox[i].LoadImage("Image/About_Menu.png");
				}else
					if(i==2)
					{
						m_ListBox[i].LoadImage("Image/Help_Menu.png");
					}else
						if(i==4)
						{
							m_ListBox[i].LoadImage("Image/Setting_Menu.png");
						}else
							if(i==3)
							{
								m_ListBox[i].LoadImage("Image/Exit_Menu.png");
							}
		}
		
		m_ListBox[0].m_Trans = new Point3D(m_ListPos[0].X, m_ListPos[0].Y, m_ListPos[0].Z);
		m_ListBox[1].m_Trans = new Point3D(m_ListPos[1].X, m_ListPos[1].Y, m_ListPos[1].Z);
		m_ListBox[2].m_Trans = new Point3D(m_ListPos[2].X, m_ListPos[2].Y, m_ListPos[2].Z);
		m_ListBox[3].m_Trans = new Point3D(m_ListPos[3].X, m_ListPos[3].Y, m_ListPos[3].Z);
		m_ListBox[4].m_Trans = new Point3D(m_ListPos[4].X, m_ListPos[4].Y, m_ListPos[4].Z);
		
		m_ListBox[0].m_Test_Select=true;
		
		for(int i=0;i<5;i++)
		{
			m_ListBox[i].m_Trans.X+=6;
		}
		
	}
	public void DisPlay(GL gl)
	{
		update();
		for(int i=0;i<5;i++)
		{
			m_ListBox[i].DisPlay(gl);
		}
	}
	public void Reset()
	{
		for(int i=0;i<5;i++)
		{
			m_ListBox[i].m_Test_Select=false;
		}
	}
	
	public void InitFlag()
	{
		for(int i=0;i<5;i++)
		{
			m_Flag[i]=i;
		}
	}
	
	public void ResetFlag()
	{
		for(int i=0;i<5;i++)
		{
			if(m_Next_Index < 0)
			{
				if(m_Flag[i]==4)
					m_Flag[i] = 0;
				else
					m_Flag[i] = m_Flag[i] + 1;
			}
			else
			{
				if(m_Flag[i]==0)
					m_Flag[i] = 4;
				else
					m_Flag[i] = m_Flag[i] - 1;
			}
		}
	}
	
	public void update()
	{
		Goto();
		for(int i=0;i<5;i++)
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
			if(m_Next_Index < 0)
			{
				if(m_Temp < 50)
				{
					m_Temp +=1;
					m_ListBox[m_Flag[0]].m_Trans = new Point3D(m_ListBox[m_Flag[0]].m_Trans.X - 0.04f, 
							m_ListBox[m_Flag[0]].m_Trans.Y + 0.01f, m_ListBox[m_Flag[0]].m_Trans.Z - 0.04f);
					m_ListBox[m_Flag[1]].m_Trans = new Point3D(m_ListBox[m_Flag[1]].m_Trans.X - 0.04f, m_ListBox[m_Flag[1]].m_Trans.Y - 0.01f, 
							m_ListBox[m_Flag[1]].m_Trans.Z + 0.04f);
					m_ListBox[m_Flag[2]].m_Trans = new Point3D(m_ListBox[m_Flag[2]].m_Trans.X + 0.02f, m_ListBox[m_Flag[2]].m_Trans.Y, 
							m_ListBox[m_Flag[2]].m_Trans.Z + 0.04f);
					m_ListBox[m_Flag[3]].m_Trans = new Point3D(m_ListBox[m_Flag[3]].m_Trans.X + 0.04f, m_ListBox[m_Flag[3]].m_Trans.Y, 
							m_ListBox[m_Flag[3]].m_Trans.Z);
					m_ListBox[m_Flag[4]].m_Trans = new Point3D(m_ListBox[m_Flag[4]].m_Trans.X + 0.02f, m_ListBox[m_Flag[4]].m_Trans.Y, 
							m_ListBox[m_Flag[4]].m_Trans.Z - 0.04f);
				}else
				{
					ResetFlag();
					m_Temp = 0;
					m_TestGoto = false;
				}
				
			}
			else if(m_Next_Index > 0)
			{
				if(m_Temp < 50)
				{
					m_Temp +=1;
					m_ListBox[m_Flag[0]].m_Trans = new Point3D(m_ListBox[m_Flag[0]].m_Trans.X + 0.04f, 
							m_ListBox[m_Flag[0]].m_Trans.Y + 0.01f, m_ListBox[m_Flag[0]].m_Trans.Z - 0.04f);
					m_ListBox[m_Flag[1]].m_Trans = new Point3D(m_ListBox[m_Flag[1]].m_Trans.X - 0.02f, m_ListBox[m_Flag[1]].m_Trans.Y, 
							m_ListBox[m_Flag[1]].m_Trans.Z - 0.04f);
					m_ListBox[m_Flag[2]].m_Trans = new Point3D(m_ListBox[m_Flag[2]].m_Trans.X - 0.04f, m_ListBox[m_Flag[2]].m_Trans.Y, 
							m_ListBox[m_Flag[2]].m_Trans.Z);
					m_ListBox[m_Flag[3]].m_Trans = new Point3D(m_ListBox[m_Flag[3]].m_Trans.X - 0.02f, m_ListBox[m_Flag[3]].m_Trans.Y, 
							m_ListBox[m_Flag[3]].m_Trans.Z + 0.04f);
					m_ListBox[m_Flag[4]].m_Trans = new Point3D(m_ListBox[m_Flag[4]].m_Trans.X + 0.04f, m_ListBox[m_Flag[4]].m_Trans.Y - 0.01f, 
							m_ListBox[m_Flag[4]].m_Trans.Z + 0.04f);
				}else
				{
					ResetFlag();
					m_Temp = 0;
					m_TestGoto = false;
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
				m_Next_Index = 1;
				if(m_Index_Select<0)
				{
					m_Index_Select=4;
					m_TestGoto=true;
				}
				else
					{
						m_TestGoto=true;	
					}
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(m_TestGoto==false)
			{
				m_Index_Select+=1;
				m_Next_Index = -1;
				if(m_Index_Select>4)
				{
					m_Index_Select=0;
					m_TestGoto=true;
				}
				else
					{
						m_TestGoto=true;
					}
			}
			break;
		case KeyEvent.VK_SPACE:
			if(m_TestGoto==false)
			{
				if(m_ListBox[m_Index_Select].m_Test_Select == true)
				{
					m_ListBox[m_Index_Select].m_Test_Choose=true;
				}
			}
			break;
		}
	}
}
