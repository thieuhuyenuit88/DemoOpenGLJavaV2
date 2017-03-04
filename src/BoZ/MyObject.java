package BoZ;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class MyObject {
	public Texture m_Tex;						//Hinh anh
	public Point3D m_Trans;						//gia tri de di chuyen
	public Point3D m_PosVector;					//Toa do thuc te tren man hinh
	public Point3D m_PosMap;					//Toa do trong Ma tran Map
	public Point3D m_Ro;						//Xoay theo 3 chieu
	public Point3D m_Sca;						//Zoom theo 3 chieu
	public int m_ID;
	public float m_AngRo;						//Gia tri de quay(goc quay)
	public MyObject()
	{
		m_ID=0;
		m_AngRo=0;
		m_Trans=new Point3D(0, 0, 0);
		m_Ro=new Point3D(0, 0, 0);
		m_Sca=new Point3D(0, 0, 0);
		m_PosVector=new Point3D(0, 0, 0);
		m_PosMap=new Point3D(0, 0, 0);
	}
	//Load hinh
	public Texture LoadTexture(String _path){
		Texture _result;
		BufferedImage image;
		try{
			image = ImageIO.read(this.getClass().getResource(_path));
			_result = TextureIO.newTexture(image,false);
		}catch(IllegalArgumentException e){
			JOptionPane.showMessageDialog(null, "Can not load file \"" + _path + "\". The application will be exit !"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		return _result;
	}
}
