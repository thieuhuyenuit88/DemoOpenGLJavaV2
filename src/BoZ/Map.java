package BoZ;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.swing.JOptionPane;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;


public class Map {
	public int[][] m_ArrayMap;
	public Item_Map
					m_Land_Nomal,
					m_Land_Nomal_Hight,
					m_Land_Nomal_Low,
					m_Land_Net,
					m_Land_Finish,
					m_Land_Trans_1,
					m_Land_Trans_2,
					m_Land_Trans1_1,
					m_Land_Trans1_2,
					m_Land_Hor,
					m_Land_Bri;
	public int PosXCube,PosYCube;
	public Boolean m_TestEnd=false;
	public Map()
	{
		m_ArrayMap=InitLevel1();
		Init();
	}
	public Map(int _level)
	{
		Init();
		if(_level==1)
		{
			
			m_ArrayMap=InitLevel1();
		}
		if(_level==2)
		{
			m_ArrayMap=InitLevel2();
		}
		if(_level==3)
		{
			m_ArrayMap=InitLevel3();
		}
		if(_level==4)
		{
			m_ArrayMap=InitLevel4();
		}
		if(_level==5)
		{
			m_ArrayMap=InitLevel5();
		}
		if(_level==6)
		{
			m_ArrayMap=InitLevel6();
		}
		if(_level==7)
		{
			m_ArrayMap=InitLevel7();
		}
//		if(_level==8)
//		{
//			m_ArrayMap=InitLevel8();
//		}
//		if(_level==9)
//		{
//			m_ArrayMap=InitLevel9();
//		}
//		if(_level==10)
//		{
//			m_ArrayMap=InitLevel10();
//		}
	}
	public void Init()
	{
		m_TestEnd=false;
		m_Land_Nomal=new Item_Map(1,new Point3D(0,0,0),new Point3D(1,1,1),new Point3D(0,0,0),0,DefineCons.LAND_NORMAL);
		m_Land_Nomal_Hight=new Item_Map(6,new Point3D(0,0,0),new Point3D(1,1,1),new Point3D(0,0,0),0,DefineCons.LAND_NORMAL);
		m_Land_Nomal_Low=new Item_Map(7,new Point3D(0,0,0),new Point3D(1,1,1),new Point3D(0,0,0),0,DefineCons.LAND_NORMAL);
		m_Land_Net=new Item_Map(4,new Point3D(0,0,0),new Point3D(1,1,1),new Point3D(0,0,0),0,DefineCons.LAND_NET);
		m_Land_Finish=new Item_Map(5,new Point3D(0,0,0),new Point3D(1,1,1),new Point3D(0,0,0),0,DefineCons.LAND_FINISH);
		m_Land_Trans_1=new Item_Map(9,new Point3D(0,0,0),new Point3D(1,1,1),new Point3D(0,0,0),0,DefineCons.LAND_TRANS);
		m_Land_Trans_2=new Item_Map(91,new Point3D(0,0,0),new Point3D(1,1,1),new Point3D(0,0,0),0,DefineCons.LAND_TRANS);
		m_Land_Trans1_1=new Item_Map(92,new Point3D(0,0,0),new Point3D(1,1,1),new Point3D(0,0,0),0,DefineCons.LAND_TRANS1);
		m_Land_Trans1_2=new Item_Map(93,new Point3D(0,0,0),new Point3D(1,1,1),new Point3D(0,0,0),0,DefineCons.LAND_TRANS1);
		m_Land_Hor=new Item_Map(2,new Point3D(0,0,0),new Point3D(1,1,1),new Point3D(0,0,0),0,DefineCons.LAND_HOR);
		m_Land_Bri=new Item_Map(3,new Point3D(0,0,0),new Point3D(1,1,1),new Point3D(0,0,0),0,DefineCons.LAND_BRI);
		
	}

	public void DrawItem(Item_Map _Item,GL gl)
	{
		_Item.m_Tex.bind();
		gl.glPushMatrix();
		gl.glTranslatef(_Item.m_Trans.X, _Item.m_Trans.Y, _Item.m_Trans.Z);
		gl.glRotatef(_Item.m_AngRo, _Item.m_Ro.X, _Item.m_Ro.Y, _Item.m_Ro.Z);
		gl.glScalef(_Item.m_Sca.X, _Item.m_Sca.Y, _Item.m_Sca.Z);
		gl.glBegin(GL.GL_QUADS);
		for(int i=0;i<14;i++)
			for(int j=0;j<19;j++)
			{
				if(m_ArrayMap[i][j]==_Item.m_ID)
				{
					
					gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(1.0f+j, 1.0f+i, 0);
			        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.0f+j, 1.0f+i, 0);
			        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.0f+j, 0.0f+i, 0);
			        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(1.0f+j, 0.0f+i, 0);   
				}
			}		
        gl.glEnd();
        gl.glPopMatrix();
	}
	public void FindItem(Item_Map _Item)
	{
		for(int i=0;i<14;i++)
			for(int j=0;j<19;j++)
			{
				if(m_ArrayMap[i][j]==_Item.m_ID)
				{
					_Item.m_PosMap.X=j;
					_Item.m_PosMap.Y=i;
				}
			}		
	}
	public void DrawMap(GL gl)
	{
		DrawItem(m_Land_Nomal,gl);
		DrawItem(m_Land_Nomal_Hight,gl);
		DrawItem(m_Land_Nomal_Low,gl);
		DrawItem(m_Land_Net,gl);
		DrawItem(m_Land_Finish,gl);
		DrawItem(m_Land_Trans_1,gl);
		DrawItem(m_Land_Trans_2,gl);
		DrawItem(m_Land_Trans1_1,gl);
		DrawItem(m_Land_Trans1_2,gl);
		DrawItem(m_Land_Hor,gl);
		DrawItem(m_Land_Bri,gl);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public int[][] InitLevel1()
	{

		m_Land_Net.m_Trans.Z=-3;
		m_Land_Nomal_Hight.m_Trans.Z=1;
		m_Land_Nomal_Hight.m_Trans.Z=-1;
		PosXCube=3;
		PosYCube=5;
		int[][] araymap = { 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 5, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			};  
		return araymap;
	
	}
	public int[][] InitLevel2()
	{
		m_Land_Net.m_Trans.Z=-3;
		PosXCube=2;
		PosYCube=5;
		int[][] araymap = { 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 5, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 3, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			};
		return araymap;
	}
	public int[][] InitLevel3()
	{
		m_Land_Net.m_Trans.Z=-3;
		PosXCube=2;
		PosYCube=5;
		int[][] araymap = { 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 9, 1, 0, 0,91, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0},
				{0, 0, 1, 1, 1, 1, 0, 0, 1, 1,92, 0, 0, 1, 1, 1, 1, 0, 0},
				{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 5, 1, 0, 0},
				{0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,93, 1, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
			};
		return araymap;
	}
	public int[][] InitLevel4()
	{
		m_Land_Net.m_Trans.Z=-3;
		PosXCube=3;
		PosYCube=4;
		int[][] araymap={ 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 1, 9, 0, 0,91, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 3, 1, 1, 4, 4, 1, 1, 0, 0, 0, 1, 1, 1, 5, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
			};
		return araymap;
	}
	public int[][] InitLevel5()
	{
		m_Land_Net.m_Trans.Z=-3;
		PosXCube=3;
		PosYCube=4;
		int[][] araymap = { 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1, 1, 1, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
			};
		return araymap;
	}
	public int[][] InitLevel6()
	{
		m_Land_Net.m_Trans.Z=-3;
		PosXCube=3;
		PosYCube=3;
		int[][] araymap = { 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1,92, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,93, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 1, 5, 1, 1, 4, 4, 4, 1, 1, 1, 1, 1, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
			};
		return araymap;
	}
	public int[][] InitLevel7()
	{
		m_Land_Net.m_Trans.Z=-3;
		PosXCube=11;
		PosYCube=3;
		int[][] araymap = { 
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 5, 0, 1, 1, 0, 1, 0, 0},
				{0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			};
		return araymap;
	}
//	public int[][] InitLevel8()
//	{
//		m_Land_Net.m_Trans.Z=-3;
//		PosXCube=2;
//		PosYCube=5;
//		int[][] araymap = { 
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
//				{0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0},
//				{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 5, 1, 0, 0},
//				{0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 3, 0, 0, 1, 1, 1, 0, 0},
//				{0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0},
//				{0, 0, 0, 1, 1, 4, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
//			};
//		return araymap;
//	}
//	public int[][] InitLevel9()
//	{
//		m_Land_Net.m_Trans.Z=-3;
//		PosXCube=2;
//		PosYCube=3;
//		int[][] araymap = { 
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 1, 1, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 1, 5, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
//				{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			};
//		return araymap;
//	}
//	public int[][] InitLevel10()
//	{
//		m_Land_Net.m_Trans.Z=-3;
//		PosXCube=2;
//		PosYCube=8;
//		int[][] araymap = { 
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 1, 1, 1, 1, 4, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 1, 5, 1, 4, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
//				{0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
//				{0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
//				{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			};
//		return araymap;
//	}
	
}
