package BoZ;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.swing.JOptionPane;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;


public class Label_Level extends MyObject {
	public Label_Level()
	{
		
	}
	public void Init(int _level)
	{
		m_Tex=LoadTexture("Image/level"+_level+".png");
		m_Trans=new Point3D(-55,0,0);
	}
	public void Display(GL gl,Level _level)
	{
		gl.glPushMatrix();
		m_Tex.bind();
		gl.glTranslated(m_Trans.X, m_Trans.Y, m_Trans.Z);
		if(m_Trans.X>=-55&&m_Trans.X<=-15)
		{
			m_Trans.X+=1;
		}
		else
		if(m_Trans.X>=0&&m_Trans.X<=20)
		{
			m_Trans.X+=1;
		}else
			m_Trans.X+=0.2;
		if(m_Trans.X>=20)
		{
			_level.m_TestStart=true;
		}
		gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 0, 0, 0);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 20f, 0, 0);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 20f, 4f, 0);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 0, 4f, 0);
        gl.glEnd();
        gl.glPopMatrix();
	}
}
