package BoZ;
//Dinh nghia cac hang va chuoi can thiet trong game
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class DefineCons {
//------------------Dinh nghia cho Cube-----------------------------//
//Dinh nghia trang thai dung cho Cube
	
	public final static int STAND				= 1;						//Dung
	public final static int HOR					= 2;						//Nam Ngang
	public final static int VER					= 3;						//Nam doc
//Dinh nghia trang thai xoay cho Cube
	
	public final static int ROLEFT				= 1;						//Xoay trai
	public final static int RORIGHT 			= 2;						//Xoay Phai
	public final static int ROUP				= 3;						//lat len
	public final static int RODOWN				= 4;						//Lat xuong
//Dinh nghia chuoi file hinh anh cho Cube
	
	public final static String CUBE_FACE        ="Image/Cube_Face.png";
//------------------Dinh ngia cho Map--------------------------------//
//Dinh nghia cac loai be mat
	
	public final static String LAND_NORMAL      ="Image/LandNormal.png";	//Binh thuong
	public final static String LAND_NET       	="Image/LandNet.png";		//Luoi
	public final static String LAND_FINISH      ="Image/LandFinish.png";	//Dich Ket thuc
	public final static String LAND_TRANS       ="Image/LandTrans.png";		//Di chuyen 1
	public final static String LAND_TRANS1      ="Image/LandTrans1.png";	//Di chuyen 2
	public final static String LAND_HOR     	="Image/LandHor.png";		//chi cho phep di qua trong trang thai nam doc
	public final static String LAND_BRI     	="Image/LandBri.png";		//Giup nang, ha cau
//-------------------Dinh nghia cho State Game-------------------------//
//Dinh nghia cac trong thai cua game play
	
	public final static int STATE_MENU			=0;							//Menu
	public final static int STATE_ABOUT			=1;							//About
	public final static int STATE_HELP			=2;							//Help
	public final static int STATE_SETTING		=3;							//Setting
	public final static int STATE_MAINGAME		=4;							//Game
	public final static int STATE_MENU_LEVEL	=5;							//Chon level
	
//-----------------Dinh nghia cho Save File---------------------------//
//Dinh nghia ten File Save
	
	public final static String SAVE_FILE       ="Save.txt";

	// File Sound for game
	public static final String		fileSound_Move			= "sounds/Move.wav";
	public static final String		fileSound_Menu			= "sounds/Menu.wav";
	public static final String		fileSound_MenuCube		= "sounds/MenuCube.wav";
	public static final String      fileSound_Move1         = "sounds/click.wav";
	public static final String      MusicGame               = "sounds/EyesOnMe.wav";
}
