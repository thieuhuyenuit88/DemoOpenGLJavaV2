package BoZ;
//Lop bao gom:
//-Khai bao khoi hop
//-Xoay khoi hop
//-lam cho khoi hop bay len va ha xuong
//-
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import javax.swing.*;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import java.util.Vector;

import com.sun.opengl.util.BufferUtil;
import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;


public class MyCube extends MyObject {
	
	//*********************************minhvuong**********************************//
	//khai bao cac bien:              ********************************************//
	//*********************************minhvuong**********************************//	
	public int m_Cube_StatusStand = 0;//trang thai dung cua cube(dung, nam ngang, nam doc)
	public int m_Cube_StatusRotation = 0;//trang thai xoay(trai, phai, len, xuong)
	public int m_Cube_StatusReRotation = 0;//trang thai xoay(trai, phai, len, xuong)
	
	public Point3D[] m_ArrayPointVertex = new Point3D[24];//mang chua cac toa do cua vetex
	
	public float m_Cube_PosX1=0;//X khoi hop tren
	public float m_Cube_PosY1=0;//Y khoi hop tren
	public float m_Cube_PosX2=0;//X khoi hop duoi
	public float m_Cube_PosY2=0;//Y khoi hop duoi
	
	public Boolean m_Cube_TestHor=false;//test trang thai cua Ngang
	public Boolean m_Cube_TestStand=false;//test trang thai cua Dung
	public Boolean m_Cube_TestVer=false;//test trang thai cua Y Doc
	
	public Boolean m_Cube_TestFall=false;
	public Boolean m_Cube_TestFlyUp=false;
	public Boolean m_Cube_TestFlyDown=false;
	
	public Boolean m_Cube_TestRef=false;
	//*********************************minhvuong**********************************//
	//khoi tao                        ********************************************//
	//*********************************minhvuong**********************************//
	
	public MyCube(int PosX,int PosY)
	{
		super();
		m_Cube_StatusStand=DefineCons.STAND;
		initVertexSTTStand();
		m_Tex = LoadTexture(DefineCons.CUBE_FACE);
		m_Tex.enable();
		m_Cube_PosX1=PosX;//X khoi hop tren
		m_Cube_PosY1=PosY;//Y khoi hop tren
		m_Cube_PosX2=PosX;//X khoi hop duoi
		m_Cube_PosY2=PosY;//Y khoi hop duoi
		m_Trans.X+=0.5f+m_Cube_PosX1;
		m_Trans.Y+=0.5f+m_Cube_PosY1;
		m_Cube_TestRef=false;
		
	}
	public MyCube()
	{
		super();
		m_Cube_StatusStand=DefineCons.STAND;
		
	}
	public void Fly()
	{
		if(m_Cube_TestFlyUp==true)
		{
			m_Trans.Z+=0.1f;
			m_Ro.Z+=10;
		}
	}
	public void FlyDown()
	{
		if(m_Cube_TestFlyDown==true)
		{
			m_Trans.Z-=0.1f;
			m_Ro.Z-=10;
		}
	}
	public void gotoPosMatrix(float _posX,float _posY)
	{

			m_Cube_PosX1=m_Cube_PosX2=_posX;
			m_Cube_PosY1=m_Cube_PosY2=_posY;
			m_Trans.X=_posX+0.5f;
			m_Trans.Y=_posY+0.5f;
			initVertexSTTStand();
	}
	//*********************************minhvuong**********************************//
	//Ve Khoi Hop                     ********************************************//
	//*********************************minhvuong**********************************//
	
	public void DrawCube(Texture _tex,GL gl)
	{
		_tex.bind();//kich hoat
		gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glNormal3f( 0.0f, 0.0f, 1.0f);//vector phap tuyen
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[0].X, m_ArrayPointVertex[0].Y,  m_ArrayPointVertex[0].Z);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[1].X, m_ArrayPointVertex[1].Y,  m_ArrayPointVertex[1].Z);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[2].X, m_ArrayPointVertex[2].Y,  m_ArrayPointVertex[2].Z);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[3].X, m_ArrayPointVertex[3].Y,  m_ArrayPointVertex[3].Z);
        // Back Face
        gl.glNormal3f( 0.0f, 0.0f,-1.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[4].X, m_ArrayPointVertex[4].Y,  m_ArrayPointVertex[4].Z);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[5].X, m_ArrayPointVertex[5].Y,  m_ArrayPointVertex[5].Z);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[6].X, m_ArrayPointVertex[6].Y,  m_ArrayPointVertex[6].Z);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[7].X, m_ArrayPointVertex[7].Y,  m_ArrayPointVertex[7].Z);
        // Top Face
        gl.glNormal3f( 0.0f, 1.0f, 0.0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[8].X, m_ArrayPointVertex[8].Y,  m_ArrayPointVertex[8].Z);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[9].X, m_ArrayPointVertex[9].Y,  m_ArrayPointVertex[9].Z);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[10].X, m_ArrayPointVertex[10].Y,  m_ArrayPointVertex[10].Z);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[11].X, m_ArrayPointVertex[11].Y,  m_ArrayPointVertex[11].Z);
        // Bottom Face
        gl.glNormal3f( 0.0f,-1.0f, 0.0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[12].X, m_ArrayPointVertex[12].Y,  m_ArrayPointVertex[12].Z);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[13].X, m_ArrayPointVertex[13].Y,  m_ArrayPointVertex[13].Z);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[14].X, m_ArrayPointVertex[14].Y,  m_ArrayPointVertex[14].Z);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[15].X, m_ArrayPointVertex[15].Y,  m_ArrayPointVertex[15].Z);
        // Right face
        gl.glNormal3f( 1.0f, 0.0f, 0.0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[16].X, m_ArrayPointVertex[16].Y,  m_ArrayPointVertex[16].Z);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[17].X, m_ArrayPointVertex[17].Y,  m_ArrayPointVertex[17].Z);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[18].X, m_ArrayPointVertex[18].Y,  m_ArrayPointVertex[18].Z);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[19].X, m_ArrayPointVertex[19].Y,  m_ArrayPointVertex[19].Z);
        // Left Face
        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[20].X, m_ArrayPointVertex[20].Y,  m_ArrayPointVertex[20].Z);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[21].X, m_ArrayPointVertex[21].Y,  m_ArrayPointVertex[21].Z);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(m_ArrayPointVertex[22].X, m_ArrayPointVertex[22].Y,  m_ArrayPointVertex[22].Z);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(m_ArrayPointVertex[23].X, m_ArrayPointVertex[23].Y,  m_ArrayPointVertex[23].Z);
        gl.glEnd();
	}

	//*********************************minhvuong**********************************//
	//Hanh Dong roi                   ********************************************//
	//*********************************minhvuong**********************************//
	public void Fall()
	{
		if(m_Cube_TestFall==true)
		{

			m_Trans.Z-=0.1f;
			if(m_Cube_StatusReRotation==DefineCons.ROLEFT)
			{	
				m_Ro.X-=10;
			}
			if(m_Cube_StatusReRotation==DefineCons.RORIGHT)
			{	
				m_Ro.X+=10;
			}
			if(m_Cube_StatusReRotation==DefineCons.ROUP)
			{	
				m_Ro.Y-=10;
			}
			if(m_Cube_StatusReRotation==DefineCons.RODOWN)
			{	
				m_Ro.Y+=10;
			}
			
			if(m_Trans.Z<=-10)
				{
					m_Cube_TestFall=false;
					m_Cube_TestRef=true;
				}
		}
	}
	public void UpdateRotation()
	{
		if(m_Cube_StatusRotation==DefineCons.ROLEFT)
		{
			RotationLeft();
		}
		if(m_Cube_StatusRotation==DefineCons.RORIGHT)
		{
			RotationRight();
		}
		if(m_Cube_StatusRotation==DefineCons.ROUP)
		{
			RotationUp();
		}
		if(m_Cube_StatusRotation==DefineCons.RODOWN)
		{
			RotationDown();
		}
	}
	//*********************************minhvuong**********************************//
	//xu ly phim                      ********************************************//
	//*********************************minhvuong**********************************//
	
	public void KeyPressed(KeyEvent arg0)
	{
		if(m_Ro.X==0&&m_Ro.Y==0&&m_Cube_TestFall==false&&m_Cube_TestFlyUp==false&&m_Cube_TestFlyDown==false)
		{
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_LEFT:
			m_Cube_StatusRotation=1;
			if(m_Cube_StatusStand==DefineCons.STAND)
			{
				m_Trans.X-=0.5f;//dich truoc khi xoay(khi thay doi truc toa do, lech 0.5 don vi)
				for(int i=0;i<24;i++)//thuc hien doi truc xoay
				{
					m_ArrayPointVertex[i].X+=0.5f;
				}
				//kiem tra trang thai(co hai trang thai(dung thang va chong cay chuoi)
				if(m_Cube_TestStand==false)
				{
					m_Cube_PosX1-=1;
					m_Cube_PosX2-=2;
					m_Cube_TestHor=false;
					
				}
				else
				{
					m_Cube_PosX1-=2;
					m_Cube_PosX2-=1;
					m_Cube_TestHor=true;
				}
			}
			if(m_Cube_StatusStand==DefineCons.HOR)
			{
				m_Trans.X-=1.0f;
				for(int i=0;i<24;i++)
				{
					m_ArrayPointVertex[i].X+=1.0f;
				}
				if(m_Cube_TestHor==false)
				{
					m_Cube_PosX1-=2;
					m_Cube_PosX2-=1;
					m_Cube_TestStand=true;
				}
				else
				{	
					m_Cube_PosX1-=1;
					m_Cube_PosX2-=2;
					m_Cube_TestStand=false;
				}
				
			}
			if(m_Cube_StatusStand==DefineCons.VER)
			{
				m_Trans.X-=0.5f;
				for(int i=0;i<24;i++)
				{
					m_ArrayPointVertex[i].X+=0.5f;
				}
				m_Cube_PosX1-=1;
				m_Cube_PosX2-=1;
			}
			break;
		case KeyEvent.VK_RIGHT:	
			
			m_Cube_StatusRotation=2;
			if(m_Cube_StatusStand==DefineCons.STAND)
			{
				m_Trans.X+=0.5f;
				for(int i=0;i<24;i++)
				{
					m_ArrayPointVertex[i].X-=0.5f;
				}
				if(m_Cube_TestStand==false)
				{
					m_Cube_PosX1+=1;
					m_Cube_PosX2+=2;
					m_Cube_TestHor=true;
				}
				else
				{
					m_Cube_PosX1+=2;
					m_Cube_PosX2+=1;
					m_Cube_TestHor=false;
				}
			}
			if(m_Cube_StatusStand==DefineCons.HOR)
			{
				m_Trans.X+=1.0f;
				for(int i=0;i<24;i++)
				{
					m_ArrayPointVertex[i].X-=1.0f;
				}
				
				if(m_Cube_TestHor==false)
				{
					m_Cube_PosX1+=1;
					m_Cube_PosX2+=2;
					m_Cube_TestStand=false;
				}
				else
				{	
					m_Cube_PosX1+=2;
					m_Cube_PosX2+=1;
					m_Cube_TestStand=true;
				}
				
			}
			if(m_Cube_StatusStand==DefineCons.VER)
			{
				m_Trans.X+=0.5f;
				for(int i=0;i<24;i++)
				{
					m_ArrayPointVertex[i].X-=0.5f;
				}
				m_Cube_PosX1+=1;
				m_Cube_PosX2+=1;
			}
			break;
		case KeyEvent.VK_UP:
			m_Cube_StatusRotation=3;
			if(m_Cube_StatusStand==DefineCons.STAND)
			{
				m_Trans.Y+=0.5f;
				for(int i=0;i<24;i++)
				{
					m_ArrayPointVertex[i].Y-=0.5f;
				}
				if(m_Cube_TestStand==false)
				{
					m_Cube_PosY1+=1;
					m_Cube_PosY2+=2;
					m_Cube_TestVer=false;
				}
				else
				{
					m_Cube_PosY1+=2;
					m_Cube_PosY2+=1;
					m_Cube_TestVer=true;
				}
			}
			if(m_Cube_StatusStand==DefineCons.HOR)
			{
				
				m_Trans.Y+=0.5f;
				for(int i=0;i<24;i++)
				{
					m_ArrayPointVertex[i].Y-=0.5f;
				}
				m_Cube_PosY1+=1;
				m_Cube_PosY2+=1;
			}
			if(m_Cube_StatusStand==DefineCons.VER)
			{
				m_Trans.Y+=1.0f;
				for(int i=0;i<24;i++)
				{
					m_ArrayPointVertex[i].Y-=1.0f;
				}
				if(m_Cube_TestVer==false)
				{
					m_Cube_PosY1+=2;
					m_Cube_PosY2+=1;
					m_Cube_TestStand=true;
				}
				else
				{
					m_Cube_PosY1+=1;
					m_Cube_PosY2+=2;
					m_Cube_TestStand=false;
				}
				
			}
			break;
		case KeyEvent.VK_DOWN:
			m_Cube_StatusRotation=4;
			if(m_Cube_StatusStand==DefineCons.STAND)
			{
				m_Trans.Y-=0.5f;
				for(int i=0;i<24;i++)
				{
					m_ArrayPointVertex[i].Y+=0.5f;
				}
				if(m_Cube_TestStand==false)
				{
					m_Cube_PosY1-=1;
					m_Cube_PosY2-=2;
					m_Cube_TestVer=true;
				}
				else
				{
					m_Cube_PosY1-=2;
					m_Cube_PosY2-=1;
					m_Cube_TestVer=false;
				}
			}
			if(m_Cube_StatusStand==DefineCons.HOR)
			{
				m_Trans.Y-=0.5f;
				for(int i=0;i<24;i++)
				{
					m_ArrayPointVertex[i].Y+=0.5f;
				}
				m_Cube_PosY1-=1;
				m_Cube_PosY2-=1;
			}
			if(m_Cube_StatusStand==DefineCons.VER)
			{
				m_Trans.Y-=1.0f;
				for(int i=0;i<24;i++)
				{
					m_ArrayPointVertex[i].Y+=1.0f;
				}
				
				if(m_Cube_TestVer==false)
				{
					m_Cube_PosY1-=1;
					m_Cube_PosY2-=2;
					m_Cube_TestStand=false;
				}
				else
				{
					m_Cube_PosY1-=2;
					m_Cube_PosY2-=1;
					m_Cube_TestStand=true;
				}
				
			}
			break;
		}
		}
	}
	//*********************************minhvuong**********************************//
	//Ve len man hinh                 ********************************************//
	//*********************************minhvuong**********************************//
	
	public void Display(GL gl,Map _map)
	{
		
		Fall();
		gl.glPushMatrix();
		gl.glTranslated(m_Trans.X, m_Trans.Y, m_Trans.Z);
		gl.glRotatef(m_Ro.X, 0,1, 0);
		gl.glRotatef(m_Ro.Y, 1,0, 0);
		gl.glRotatef(m_Ro.Z, 0,0, 1);
		DrawCube(m_Tex, gl);
		gl.glPopMatrix();
	}
	
	//*********************************minhvuong**********************************//
	//Xu ly khi xoay                  ********************************************//
	//*********************************minhvuong**********************************//
	
	public void RotationLeft()
	{
		m_Ro.X-=5;	
		if(m_Ro.X==-90)
		{
			m_Cube_StatusRotation=0;
			m_Cube_StatusReRotation=DefineCons.ROLEFT;
			m_Ro.X=0;	
			ChangleMatrixLeft();
		}
		
	}
	
	public void RotationRight()
	{
		m_Ro.X+=5;	
		if(m_Ro.X==90)
		{
			m_Cube_StatusRotation=0;
			m_Cube_StatusReRotation=DefineCons.RORIGHT;
			m_Ro.X=0;	
			ChangleMatrixRight();
		}
		
	}
	
	public void RotationUp()
	{
		m_Ro.Y-=5;	
		if(m_Ro.Y==-90)
		{
			m_Cube_StatusRotation=0;
			m_Cube_StatusReRotation=DefineCons.ROUP;
			m_Ro.Y=0;	
			ChangleMatrixUp();
		}
		
	}
	
	public void RotationDown()
	{
		m_Ro.Y+=5;	
		if(m_Ro.Y==90)
		{
			m_Cube_StatusRotation=0;
			m_Cube_StatusReRotation=DefineCons.RODOWN;
			m_Ro.Y=0;	
			ChangleMatrixDown();
		}
		
	}	
	
	
	//*********************************minhvuong**********************************//
	//Xu ly sau khi xoay              ********************************************//
	//*********************************minhvuong**********************************//
	
	public void ChangleMatrixLeft()
	{
		switch (m_Cube_StatusStand) {
		case DefineCons.STAND :
			m_Cube_StatusStand=DefineCons.HOR;	
			initVertexSTTHor();	
			m_Trans.X-=1.0f;
			break;
		case DefineCons.HOR :
			m_Cube_StatusStand=DefineCons.STAND;
			initVertexSTTStand();
			m_Trans.X-=0.5f;
			break;
		case DefineCons.VER:
			m_Trans.X-=0.5f;
			initVertexSTTVer();
			break;
		}
	}
	
	public void ChangleMatrixRight()
	{
		switch (m_Cube_StatusStand) {
		case DefineCons.STAND :
			m_Cube_StatusStand=DefineCons.HOR;				
			initVertexSTTHor();			
			m_Trans.X+=1.0f;
			break;
		case DefineCons.HOR :
			m_Cube_StatusStand=DefineCons.STAND;
			initVertexSTTStand();
			m_Trans.X+=0.5f;
			break;
		case DefineCons.VER:
			m_Trans.X+=0.5f;
			initVertexSTTVer();
			break;
		}
		
	}
	
	public void ChangleMatrixUp()
	{
		switch (m_Cube_StatusStand) {
		case DefineCons.STAND :
			m_Cube_StatusStand=DefineCons.VER;
			initVertexSTTVer();
			m_Trans.Y+=1.0f;
			break;
		case DefineCons.HOR :
			m_Trans.Y+=0.5f;
			initVertexSTTHor();
			break;
		case DefineCons.VER:
			m_Cube_StatusStand=DefineCons.STAND;
			initVertexSTTStand();
			m_Trans.Y+=0.5f;
			break;
		}
		
	}
	
	public void ChangleMatrixDown()
	{
		switch (m_Cube_StatusStand) {
		case DefineCons.STAND :
			m_Cube_StatusStand=DefineCons.VER;
			initVertexSTTVer();
			m_Trans.Y-=1.0f;
			break;
		case DefineCons.HOR :
			m_Trans.Y-=0.5f;
			initVertexSTTHor();
			break;
		case DefineCons.VER:
			m_Cube_StatusStand=DefineCons.STAND;
			initVertexSTTStand();
			m_Trans.Y-=0.5f;
			break;
		}
	}
	
	//*********************************minhvuong**********************************//
	//Thiet lap cac ma tran toa do    ********************************************//
	//*********************************minhvuong**********************************//
	
	
	
	public void initVertexSTTHor()
	{
		
		// Front Face
		m_ArrayPointVertex[0] = new Point3D( 1.0f, 0.5f, 1.0f);
        m_ArrayPointVertex[1] = new Point3D(-1.0f, 0.5f, 1.0f);
        m_ArrayPointVertex[2] = new Point3D(-1.0f,-0.5f, 1.0f);
        m_ArrayPointVertex[3] = new Point3D( 1.0f,-0.5f, 1.0f);
        // Back Face
        m_ArrayPointVertex[4] = new Point3D( 1.0f, 0.5f, 0.0f);
        m_ArrayPointVertex[5] = new Point3D( 1.0f,-0.5f, 0.0f);
        m_ArrayPointVertex[6] = new Point3D(-1.0f,-0.5f, 0.0f);
        m_ArrayPointVertex[7] = new Point3D(-1.0f, 0.5f, 0.0f);
        // Top Face
        m_ArrayPointVertex[8] = new Point3D( 1.0f,-0.5f, 0.0f);
        m_ArrayPointVertex[9] = new Point3D( 1.0f,-0.5f, 1.0f);
        m_ArrayPointVertex[10]= new Point3D(-1.0f,-0.5f, 1.0f);
        m_ArrayPointVertex[11]= new Point3D(-1.0f,-0.5f, 0.0f);
        // Bottom Face
        m_ArrayPointVertex[12]= new Point3D( 1.0f, 0.5f, 0.0f);
        m_ArrayPointVertex[13]= new Point3D(-1.0f, 0.5f, 0.0f);
        m_ArrayPointVertex[14]= new Point3D(-1.0f, 0.5f, 1.0f);
        m_ArrayPointVertex[15]= new Point3D( 1.0f, 0.5f, 1.0f);
        // Right face
        m_ArrayPointVertex[16]= new Point3D(-1.0f, 0.5f, 0.0f);
        m_ArrayPointVertex[17]= new Point3D(-1.0f,-0.5f, 0.0f);
        m_ArrayPointVertex[18]= new Point3D(-1.0f,-0.5f, 1.0f);
        m_ArrayPointVertex[19]= new Point3D(-1.0f, 0.5f, 1.0f);
        // Left Face
        m_ArrayPointVertex[20]= new Point3D( 1.0f, 0.5f, 0.0f);
        m_ArrayPointVertex[21]= new Point3D( 1.0f, 0.5f, 1.0f);
        m_ArrayPointVertex[22]= new Point3D( 1.0f,-0.5f, 1.0f);
        m_ArrayPointVertex[23]= new Point3D( 1.0f,-0.5f, 0.0f);
        
	}
	
	public void initVertexSTTVer()
	{
		
		// Front Face
		m_ArrayPointVertex[0] = new Point3D( 0.5f, 1.0f,1.0f);
        m_ArrayPointVertex[1] = new Point3D(-0.5f, 1.0f, 1.0f);
        m_ArrayPointVertex[2] = new Point3D(-0.5f,-1.0f, 1.0f);
        m_ArrayPointVertex[3] = new Point3D( 0.5f,-1.0f, 1.0f);
        // Back Face
        m_ArrayPointVertex[4] = new Point3D( 0.5f, 1.0f, 0.0f);
        m_ArrayPointVertex[5] = new Point3D( 0.5f,-1.0f, 0.0f);
        m_ArrayPointVertex[6] = new Point3D(-0.5f,-1.0f, 0.0f);
        m_ArrayPointVertex[7] = new Point3D(-0.5f, 1.0f, 0.0f);
        // Top Face
        m_ArrayPointVertex[8] = new Point3D( 0.5f,-1.0f, 0.0f);
        m_ArrayPointVertex[9] = new Point3D( 0.5f,-1.0f, 1.0f);
        m_ArrayPointVertex[10]= new Point3D(-0.5f,-1.0f, 1.0f);
        m_ArrayPointVertex[11]= new Point3D(-0.5f,-1.0f, 0.0f);
        // Bottom Face
        m_ArrayPointVertex[12]= new Point3D( 0.5f, 1.0f, 0.0f);
        m_ArrayPointVertex[13]= new Point3D(-0.5f, 1.0f, 0.0f);
        m_ArrayPointVertex[14]= new Point3D(-0.5f, 1.0f, 1.0f);
        m_ArrayPointVertex[15]= new Point3D( 0.5f, 1.0f, 1.0f);
        // Right face
        m_ArrayPointVertex[16]= new Point3D(-0.5f, 1.0f, 0.0f);
        m_ArrayPointVertex[17]= new Point3D(-0.5f,-1.0f, 0.0f);
        m_ArrayPointVertex[18]= new Point3D(-0.5f,-1.0f, 1.0f);
        m_ArrayPointVertex[19]= new Point3D(-0.5f, 1.0f, 1.0f);
        // Left Face
        m_ArrayPointVertex[20]= new Point3D( 0.5f, 1.0f, 0.0f);
        m_ArrayPointVertex[21]= new Point3D( 0.5f, 1.0f, 1.0f);
        m_ArrayPointVertex[22]= new Point3D( 0.5f,-1.0f, 1.0f);
        m_ArrayPointVertex[23]= new Point3D( 0.5f,-1.0f, 0.0f);
        
	}
	
	public void initVertexSTTStand()
	{

		// Front Face
		m_ArrayPointVertex[0] = new Point3D( 0.5f, 0.5f, 2.0f);
        m_ArrayPointVertex[1] = new Point3D(-0.5f, 0.5f, 2.0f);
        m_ArrayPointVertex[2] = new Point3D(-0.5f,-0.5f, 2.0f);
        m_ArrayPointVertex[3] = new Point3D( 0.5f,-0.5f, 2.0f);
        // Back Face
        m_ArrayPointVertex[4] = new Point3D( 0.5f, 0.5f, 0.0f);
        m_ArrayPointVertex[5] = new Point3D( 0.5f,-0.5f, 0.0f);
        m_ArrayPointVertex[6] = new Point3D(-0.5f,-0.5f, 0.0f);
        m_ArrayPointVertex[7] = new Point3D(-0.5f, 0.5f, 0.0f);
        // Top Face
        m_ArrayPointVertex[8] = new Point3D( 0.5f,-0.5f, 0.0f);
        m_ArrayPointVertex[9] = new Point3D( 0.5f,-0.5f, 2.0f);
        m_ArrayPointVertex[10]= new Point3D(-0.5f,-0.5f, 2.0f);
        m_ArrayPointVertex[11]= new Point3D(-0.5f,-0.5f, 0.0f);
        // Bottom Face
        m_ArrayPointVertex[12]= new Point3D( 0.5f, 0.5f, 0.0f);
        m_ArrayPointVertex[13]= new Point3D(-0.5f, 0.5f, 0.0f);
        m_ArrayPointVertex[14]= new Point3D(-0.5f, 0.5f, 2.0f);
        m_ArrayPointVertex[15]= new Point3D( 0.5f, 0.5f, 2.0f);
        // Right face
        m_ArrayPointVertex[16]= new Point3D(-0.5f, 0.5f, 0.0f);
        m_ArrayPointVertex[17]= new Point3D(-0.5f,-0.5f, 0.0f);
        m_ArrayPointVertex[18]= new Point3D(-0.5f,-0.5f, 2.0f);
        m_ArrayPointVertex[19]= new Point3D(-0.5f, 0.5f, 2.0f);
        // Left Face
        m_ArrayPointVertex[20]= new Point3D( 0.5f, 0.5f, 0.0f);
        m_ArrayPointVertex[21]= new Point3D( 0.5f, 0.5f, 2.0f);
        m_ArrayPointVertex[22]= new Point3D( 0.5f,-0.5f, 2.0f);
        m_ArrayPointVertex[23]= new Point3D( 0.5f,-0.5f, 0.0f);
        
	}
	
}
