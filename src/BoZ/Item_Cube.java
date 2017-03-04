package BoZ;
//Dinh nghia cho mot khoi hop duoc dung lam vat pham de chon, sau do thuc hien thao tac nao do
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.swing.JOptionPane;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class Item_Cube extends MyCube {
	//Cac bien kiem tra su kien tac dong den, chon, mo khoa
	public Boolean m_Test_Select=false;
	public Boolean m_Test_Choose=false;
	public Boolean m_Test_Unlock=false;
	
	public Item_Cube()
	{
		m_Test_Select=false;
		m_Test_Choose=false;
		m_Test_Unlock=false;
	}
	//Load Hinh anh cho Item Cube
	
	public void LoadImage(String _path)
	{
		if(m_Test_Unlock==true)
			m_Tex=LoadTexture(_path);
		else
			m_Tex=LoadTexture("Image/LevelBox0.png");	//Neu nhu chua duoc Unlock, load hinh anh cua chiec o khoa
		m_Tex.enable();
	}
	
	public void Init()
	{
		initVertexNomal();									//khoi tao ban dau cho ma tran toa do
	}
	
	public void DisPlay(GL gl)
	{
		update();
		gl.glPushMatrix();
		gl.glTranslated(m_Trans.X, m_Trans.Y, m_Trans.Z);
		gl.glRotatef(m_Ro.X, 0,1, 0);
		gl.glRotatef(m_Ro.Y, 1,0, 0);
		gl.glRotatef(m_Ro.Z, 0,0, 1);
		DrawCube(m_Tex, gl);
		gl.glPopMatrix();
	} 
	
	public void update()
	{
		if(m_Test_Select==true)//neu duoc chon thi cho cube quay
		{
			m_Ro.Z += 1;
		}
		else
		{
			if(m_Ro.Z%90!=0)//neu khong duoc chon thi khong quay hoac doi cho den khi quay duoc ngay ngan thi dung lai
			{
				m_Ro.Z+=1;
			}
		}
	}
	
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
    	
	}
	//khoi tao gia tri cho ma tran vertex
	
	public void initVertexNomal()
	{	
		// Front Face
		m_ArrayPointVertex[0] = new Point3D( 0.5f, 0.5f, 1.0f);
        m_ArrayPointVertex[1] = new Point3D(-0.5f, 0.5f, 1.0f);
        m_ArrayPointVertex[2] = new Point3D(-0.5f,-0.5f, 1.0f);
        m_ArrayPointVertex[3] = new Point3D( 0.5f,-0.5f, 1.0f);
        // Back Face
        m_ArrayPointVertex[4] = new Point3D( 0.5f, 0.5f, 0.0f);
        m_ArrayPointVertex[5] = new Point3D( 0.5f,-0.5f, 0.0f);
        m_ArrayPointVertex[6] = new Point3D(-0.5f,-0.5f, 0.0f);
        m_ArrayPointVertex[7] = new Point3D(-0.5f, 0.5f, 0.0f);
        // Top Face
        m_ArrayPointVertex[8] = new Point3D(-0.5f,-0.5f, 1.0f);
        m_ArrayPointVertex[9] = new Point3D(-0.5f,-0.5f, 0.0f);
        m_ArrayPointVertex[10]= new Point3D( 0.5f,-0.5f, 0.0f);
        m_ArrayPointVertex[11]= new Point3D( 0.5f,-0.5f, 1.0f);
        // Bottom Face
        m_ArrayPointVertex[12]= new Point3D( 0.5f, 0.5f, 1.0f);
        m_ArrayPointVertex[13]= new Point3D( 0.5f, 0.5f, 0.0f);
        m_ArrayPointVertex[14]= new Point3D(-0.5f, 0.5f, 0.0f);
        m_ArrayPointVertex[15]= new Point3D(-0.5f, 0.5f, 1.0f);
        // Right face
        m_ArrayPointVertex[16]= new Point3D(-0.5f, 0.5f, 1.0f);
        m_ArrayPointVertex[17]= new Point3D(-0.5f, 0.5f, 0.0f);
        m_ArrayPointVertex[18]= new Point3D(-0.5f,-0.5f, 0.0f);
        m_ArrayPointVertex[19]= new Point3D(-0.5f,-0.5f, 1.0f);
        // Left Face
        m_ArrayPointVertex[20]= new Point3D( 0.5f, 0.5f, 0.0f);
        m_ArrayPointVertex[21]= new Point3D( 0.5f, 0.5f, 1.0f);
        m_ArrayPointVertex[22]= new Point3D( 0.5f,-0.5f, 1.0f);
        m_ArrayPointVertex[23]= new Point3D( 0.5f,-0.5f, 0.0f);
       
	}
	
}
