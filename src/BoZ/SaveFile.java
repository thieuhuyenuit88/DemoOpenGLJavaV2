package BoZ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveFile {
	
	public static int m_FinalLevel=1;
	public SaveFile()
	{
	}
	
	
	public static void WriteFile( String filepath) throws IOException
	{
		File game = new File(filepath);
		FileOutputStream fo = new FileOutputStream(game);
		ObjectOutputStream out = new ObjectOutputStream(fo);

		
		out.writeObject(m_FinalLevel);
		
		out.flush();
		
		fo.close();
	}
	
	public static void ReadFile( String filepath) throws IOException, ClassNotFoundException{
		File game = new File(filepath);
		if(!game.exists()){
			throw new FileNotFoundException("Can not find file name : " + filepath);
		}
		
		FileInputStream fi = new FileInputStream( game );
        ObjectInputStream in = new ObjectInputStream(fi);
       
        m_FinalLevel = (Integer) in.readObject();
	}
}

