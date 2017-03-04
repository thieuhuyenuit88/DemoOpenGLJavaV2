package BoZ;


public class TestColision {

	public TestColision() {
		// TODO Auto-generated constructor stub
	}
	public void TestColition(Map m_Map,MyCube m_Cube)
	{
		TestFall(m_Map, m_Cube);
		
		
		
		if(TestPostItem(m_Map, m_Cube, m_Map.m_Land_Finish)==true)
		{
			m_Map.m_TestEnd=true;
			m_Cube.m_Cube_TestFlyUp=true;
		}
		if(TestPostItem(m_Map, m_Cube, m_Map.m_Land_Bri)==true)
		{
			if(m_Map.m_Land_Net.m_Trans.Z==-3)
				m_Map.m_Land_Net.m_Trans.Z=0;
			else
				m_Map.m_Land_Net.m_Trans.Z=-3;
		}
	
		
		if(TestFallItem(m_Map, m_Cube, m_Map.m_Land_Net)==true)
		{
			m_Cube.m_Cube_TestFall=true;
		}
		if(TestFallItem1(m_Map, m_Cube, m_Map.m_Land_Hor)==true)
		{
			m_Cube.m_Cube_TestFall=true;
		}
		
		TestPostItemMove(m_Map, m_Cube, m_Map.m_Land_Trans_1, m_Map.m_Land_Trans_2);
		MoveItem(m_Map, m_Cube,  m_Map.m_Land_Trans_1, m_Map.m_Land_Trans_2);
		TestPostItemMove(m_Map, m_Cube, m_Map.m_Land_Trans1_1, m_Map.m_Land_Trans1_2);
		MoveItem(m_Map, m_Cube,  m_Map.m_Land_Trans1_1, m_Map.m_Land_Trans1_2);

	}
	public void TestFall(Map m_Map,MyCube _Cube)
	{
		if(_Cube.m_Ro.X==0&&_Cube.m_Ro.Y==0)
		{
			if((m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY1][(int) _Cube.m_Cube_PosX1]==0||m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY2][(int) _Cube.m_Cube_PosX2]==0))
			{
				_Cube.m_Cube_TestFall=true;
			}
		}
	}
	public Boolean TestFallItem(Map m_Map,MyCube _Cube,Item_Map _Item)
	{
		if(_Cube.m_Ro.X==0&&_Cube.m_Ro.Y==0)
		{
			if(_Item.m_Trans.Z!=0&&(m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY1][(int) _Cube.m_Cube_PosX1]==_Item.m_ID||m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY2][(int) _Cube.m_Cube_PosX2]==_Item.m_ID))
			{
				return true;
			}
		}
		return false;
	}
	public Boolean TestFallItem1(Map m_Map,MyCube _Cube,Item_Map _Item)
	{
		if(_Cube.m_Ro.X==0&&_Cube.m_Ro.Y==0)
		{
			if((m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY1][(int) _Cube.m_Cube_PosX1]==_Item.m_ID||m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY2][(int) _Cube.m_Cube_PosX2]==_Item.m_ID))
			{
				if(_Cube.m_Cube_StatusStand==DefineCons.HOR||_Cube.m_Cube_StatusStand==DefineCons.STAND)
				{
					return true;
				}
				
			}	
			
			
		}
		return false;
	}
	public Boolean TestPostItem(Map m_Map,MyCube _Cube,Item_Map _Item)
	{
		if(_Cube.m_Ro.X==-85||_Cube.m_Ro.Y==-85||_Cube.m_Ro.X==85||_Cube.m_Ro.Y==85)
		{
			if((m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY1][(int) _Cube.m_Cube_PosX1]==_Item.m_ID&&m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY2][(int) _Cube.m_Cube_PosX2]==_Item.m_ID))
			{
				return true;
				
			}
		}
		return false;
	}
	public void TestPostItemMove(Map m_Map,MyCube _Cube,Item_Map _Item1,Item_Map _Item2)
	{
		if(_Cube.m_Ro.X==-85||_Cube.m_Ro.Y==-85||_Cube.m_Ro.X==85||_Cube.m_Ro.Y==85)
		{
			if((m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY1][(int) _Cube.m_Cube_PosX1]==_Item1.m_ID&&m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY2][(int) _Cube.m_Cube_PosX2]==_Item1.m_ID))
			{
				_Item1.m_TestColision=true;
				m_Map.FindItem(_Item2);
				_Cube.m_Cube_TestFlyUp=true;
				
			}else
				if((m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY1][(int) _Cube.m_Cube_PosX1]==_Item2.m_ID&&m_Map.m_ArrayMap[(int) _Cube.m_Cube_PosY2][(int) _Cube.m_Cube_PosX2]==_Item2.m_ID))
				{
					_Item2.m_TestColision=true;
					m_Map.FindItem(_Item1);
					_Cube.m_Cube_TestFlyUp=true;
				}
		}
	}
	public void MoveItem(Map m_Map,MyCube _Cube,Item_Map _Item1,Item_Map _Item2)
	{
		if(_Item2.m_TestColision==true&&(_Cube.m_Ro.X==0&&_Cube.m_Ro.Y==0))
		{
			if(_Cube.m_Cube_TestFlyUp==true)
			{
				_Cube.m_Ro.Z-=10;
				_Cube.m_Trans.Z+=0.1f;
				if(_Cube.m_Trans.Z>=5)
				{
					_Cube.m_Cube_TestFlyUp=false;
					_Cube.m_Cube_TestFlyDown=true;
				}
			}
			if(_Cube.m_Cube_TestFlyDown==true)
			{
				_Cube.gotoPosMatrix(_Item1.m_PosMap.X, _Item1.m_PosMap.Y);
				_Cube.m_Ro.Z+=10;
				_Cube.m_Trans.Z-=0.1f;
				if(_Cube.m_Trans.Z<=0)
				{
					_Cube.m_Ro.Z=0;
					_Item2.m_TestColision=false;
					_Cube.m_Cube_TestFlyDown=false;
					_Cube.m_Trans.Z=0;
				}
			}
			
		}else
			if(_Item1.m_TestColision==true&&(_Cube.m_Ro.X==0&&_Cube.m_Ro.Y==0))
			{
				if(_Cube.m_Cube_TestFlyUp==true)
				{
					_Cube.m_Ro.Z-=10;
					_Cube.m_Trans.Z+=0.1f;
					if(_Cube.m_Trans.Z>=5)
					{
						_Cube.m_Cube_TestFlyUp=false;
						_Cube.m_Cube_TestFlyDown=true;
					}
				}
				if(_Cube.m_Cube_TestFlyDown==true)
				{
					_Cube.gotoPosMatrix(_Item2.m_PosMap.X, _Item2.m_PosMap.Y);
					_Cube.m_Ro.Z+=10;
					_Cube.m_Trans.Z-=0.1f;
					if(_Cube.m_Trans.Z<=0)
					{
						_Cube.m_Ro.Z=0;
						_Item1.m_TestColision=false;
						_Cube.m_Cube_TestFlyDown=false;
						_Cube.m_Trans.Z=0;
					}
				}
			}

	}
}
