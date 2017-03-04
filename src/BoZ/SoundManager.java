package BoZ;

import java.io.IOException;
public class SoundManager {

	private Sound	Move;
	private Sound	MenuCube;
	private Sound	Menu;
	private Sound   Move1;
	private Sound   m_MusicGame ;
	
	public SoundManager()
	{
		try {
			Move 		= new Sound(ResourceRetriever.getResourceAsStream(DefineCons.fileSound_Move));
			MenuCube	= new Sound(ResourceRetriever.getResourceAsStream(DefineCons.fileSound_MenuCube));
			Menu 		= new Sound(ResourceRetriever.getResourceAsStream(DefineCons.fileSound_Menu));
			Move1 		= new Sound(ResourceRetriever.getResourceAsStream(DefineCons.fileSound_Move1));
			m_MusicGame	= new Sound(ResourceRetriever.getResourceAsStream(DefineCons.MusicGame));
			
		} catch (IOException e) {
		}
	}
	
	public void Play_Move()
	{
		Move.play();
	}
	
	public void Play_MenuCube()
	{
		MenuCube.play();
	}
	
	public void Play_Menu()
	{
		Menu.play();
	}
	public void Play_Move1()
	{
		Move1.play() ;
	}
	public void Play_Music(){
		m_MusicGame.play(true);
	}
	public void Stop_Music(){
		m_MusicGame.stop();
	}
	
}
