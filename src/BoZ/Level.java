package BoZ;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.swing.JOptionPane;

import org.omg.CORBA._IDLTypeStub;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;


public class Level extends MyObject {
	Map m_Map;
	MyCube m_Cube;
	TestColision m_Man;
	public Boolean m_TestStart=false;
	public Label_Level m_Label_Level;
	public Boolean m_TestCom=false;
	public Boolean m_TestOver=false;
	public Boolean m_TestPause = false;
	public Man_Cube_Pause m_Pause_Cube;
	
	SoundManager m_soundManager = new SoundManager() ;
	public Level()
	{
		m_Man=new TestColision();
		m_Label_Level=new Label_Level();
		m_Pause_Cube=new Man_Cube_Pause();
		m_ID=1;
	}
	public Level(int _levelID)
	{
		m_ID=_levelID;
		m_Man=new TestColision();
		m_Label_Level=new Label_Level();
		m_Pause_Cube=new Man_Cube_Pause();
	}
	public void Init(int _levelID)
	{
		m_ID=_levelID;
		m_TestStart=false;
		m_TestCom=false;
		m_TestOver=false;
		m_TestPause = false;
		m_Map=new Map(m_ID);
		m_Cube=new MyCube(m_Map.PosXCube,m_Map.PosYCube);
		m_Pause_Cube.Init();
		m_Cube.m_Cube_TestFlyDown=true;
		m_Cube.m_Trans.Z=5;
		m_Label_Level.Init(m_ID);
		m_Tex=LoadTexture("Image/back"+m_ID+".png");
		m_Tex.enable();
		m_soundManager.Play_Music();
	}
	public void Init()
	{
		m_Pause_Cube.Init();
		m_TestStart=false;
		m_TestCom=false;
		m_TestOver=false;
		m_TestPause = false;
		m_Map=new Map(m_ID);
		m_Cube=new MyCube(m_Map.PosXCube,m_Map.PosYCube);
		m_Cube.m_Cube_TestFlyDown=true;
		m_Cube.m_Trans.Z=5;
		m_Label_Level.Init(m_ID);
		m_Tex=LoadTexture("Image/back"+m_ID+".png");
		m_Tex.enable();
		m_soundManager.Play_Music();
	}
	public void Display(GL gl)
	{
		if(m_TestPause==true)
		{
			m_Pause_Cube.Display(gl);
		}
		if(m_TestStart==true)
		{
			if(m_TestCom==false)
			{
				m_Cube.FlyDown();
				if(m_Cube.m_Trans.Z<=0)
				{
					m_Cube.m_Cube_TestFlyDown=false;
					m_TestStart=true;
					m_TestCom=true;
					m_Cube.m_Ro.Z=0;
					m_Cube.m_Trans.Z=0;
				}
			}
			update();
			m_Map.DrawMap(gl);
			m_Cube.Display(gl,m_Map);
			gl.glPushMatrix();
			m_Tex.bind();
			gl.glBegin(GL.GL_QUADS);
			
	        // Front Face
	        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( -15, 0, -5);
	        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 35, 0, -5);
	        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 35, 35, -5);
	        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( -15, 35, -5);
	        gl.glEnd();
	        gl.glPopMatrix();
		}
		else
		{
			m_Label_Level.Display(gl, this);
		}
	}
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_ESCAPE:
			if(m_TestPause==false)
				m_TestPause=true;
			break;
		}
		if(m_TestPause==false)
		{
			m_Cube.KeyPressed(arg0);
			switch(arg0.getKeyCode()){
	        case KeyEvent.VK_LEFT:
	        	m_soundManager.Play_Move1() ;
	        	break;
			
			case KeyEvent.VK_RIGHT:
		    	m_soundManager.Play_Move1() ;
		    	break;
			case KeyEvent.VK_UP:
		    	m_soundManager.Play_Move1() ;
		    	break;
			case KeyEvent.VK_DOWN:
		    	m_soundManager.Play_Move1() ;
		    	break;
			}

		}
		else
		{
			m_Pause_Cube.keyPressed(arg0);
		}
		
	}
	
	public void update()
	{
		if(m_TestPause==false)
		{
			m_Cube.UpdateRotation();
			
			m_Man.TestColition(m_Map,m_Cube);
			if(m_Cube.m_Cube_TestRef==true)
			{
				Init();
			}
			if(m_Map.m_TestEnd==true )
			{
				m_Cube.Fly();
				if(m_Cube.m_Trans.Z>=10)
					{
						m_ID+=1;
//						if(m_ID !=1){
//							m_soundManager.Stop_Music();
//						}
						if(m_ID<=7)
							if(m_ID>SaveFile.m_FinalLevel)
							{
								SaveFile.m_FinalLevel=m_ID;
								try {
									SaveFile.WriteFile(DefineCons.SAVE_FILE);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						if(m_ID>=8)
						{
							m_TestOver=true;
							return;
						}
						Init();
						m_Cube.m_Cube_TestFlyUp=false;
					}	
			}
		}
		else
			{
				m_Pause_Cube.Update();
				if(m_Pause_Cube.m_TestCome==false&&m_Pause_Cube.m_TestGo==false&&m_Pause_Cube.m_TestCom==false)
				{
					if(m_Pause_Cube.m_CubeResume.m_Test_Select==true)
					{
						m_TestPause=false;
					}
					else
					{
						m_TestOver=true;
					}
				}
			}
	}
	
}
