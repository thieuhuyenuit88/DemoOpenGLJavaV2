package BoZ;


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
import javax.swing.text.StyledEditorKit.BoldAction;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import java.util.Vector;

import com.sun.opengl.impl.mipmap.HalveImage;
import com.sun.opengl.util.BufferUtil;
import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;


public class Game_Box extends Frame implements GLEventListener,KeyListener,MouseListener,MouseMotionListener  {

	public static int HEIGHT = 600,WIDTH= 800;
	public static GL gl;
	public static GLU glu;
	public static GLCanvas m_Canvas;
	public static FPSAnimator m_Animator;
	public GamePlay m_GamePlay;
	
	public Game_Box()
	{
		m_Canvas=new GLCanvas();
		m_Canvas.addGLEventListener(this);
		m_GamePlay=new GamePlay();
		m_Animator = new FPSAnimator(m_Canvas,60,true);
		
		gl=m_Canvas.getGL();
		glu=new GLU();
		
		this.add(m_Canvas,BorderLayout.CENTER);
		m_Canvas.addMouseListener(this);
		m_Canvas.addMouseMotionListener(this);
		m_Canvas.addKeyListener(this);
		
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				m_Animator.stop();
				System.exit(0);
			}
		});
	}
	
	@Override
	public void init(GLAutoDrawable arg0) 
	{
		gl.glClearColor(0,0,0,0);
		gl.glDrawBuffer(GL.GL_FRONT_AND_BACK);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		gl.glEnableClientState(GL.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL.GL_COLOR_ARRAY);
		gl.glShadeModel(GL.GL_SMOOTH);
		gl.glEnable(GL.GL_CULL_FACE);
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glFrontFace(GL.GL_CCW);
		gl.glCullFace(GL.GL_BACK); 
		gl.glEnable(GL.GL_DEPTH_TEST);
		
		m_Animator.start(); // start animator thread
		m_GamePlay.Init();
		
		try 
		{
			SaveFile.WriteFile(DefineCons.SAVE_FILE);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void display(GLAutoDrawable arg0) 
	{
		gl.glClear(GL.GL_COLOR_BUFFER_BIT| GL.GL_DEPTH_BUFFER_BIT| GL.GL_STENCIL_BUFFER_BIT);
		gl.glLoadIdentity();
		
        m_GamePlay.Display(glu,gl);
	}

	
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) 
	{
		
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) 
	{
		WIDTH = width; 
		HEIGHT = height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(gl.GL_PROJECTION);
		gl.glLoadIdentity ();
		glu.gluPerspective(45, (float) width / height, 1, 1000);
		gl.glMatrixMode(gl.GL_MODELVIEW);
		gl.glLoadIdentity();
		
	}
	public static void main(String[] args) 
	{
		Game_Box  f = new Game_Box();
		// 8. add a title on the frame
		f.setTitle("GAME BOZ");
		f.setSize(WIDTH, HEIGHT);
		f.setVisible(true);
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		m_GamePlay.keyPressed(arg0);
	}
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
