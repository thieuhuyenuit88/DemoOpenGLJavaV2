package BoZ;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;


public class Item_Map extends MyObject {

	public Boolean m_TestColision;		//kiem tra va cham voi Map
	
	public Item_Map(int _ID,Point3D _trans,Point3D _scale,Point3D _RoXYZ,float _rotation,String _path)
	{
		m_TestColision=false;
		m_ID=_ID;
		m_Trans=_trans;
		m_Sca=_scale;
		m_Ro=_RoXYZ;
		m_AngRo=_rotation;
		m_Tex = LoadTexture(_path);
		m_Tex.enable();
		
	}
	public Item_Map()
	{
		m_TestColision=false;
		m_ID=0;
		m_Trans=new Point3D(0,0,0);
		m_Sca=new Point3D(0,0,0);
		m_Ro=new Point3D(0,0,0);
		m_AngRo=0;
	}
	public void Init(String _path)
	{
		m_Tex = LoadTexture(_path);
		m_Tex.enable();

	}

}
