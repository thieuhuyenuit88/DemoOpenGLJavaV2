package BoZ;
//Khai bao mot kieu gom 3 toa do X,Y,Z
//------------Nguyen Minh Vuong----------------\\
import java.io.Serializable;

public class Point3D  {
	
	public float X;//toa do X
	public float Y;//toa do Y
	public float Z;//toa do Z
	
	public Point3D(float X, float Y, float Z)
	{
		this.X = X;
		this.Y = Y;
		this.Z = Z;
	}
	
	public boolean CheckEqual(Point3D _Check)
	{
		if(this.X == _Check.X && this.Y == _Check.Y && this.Z == _Check.Y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
