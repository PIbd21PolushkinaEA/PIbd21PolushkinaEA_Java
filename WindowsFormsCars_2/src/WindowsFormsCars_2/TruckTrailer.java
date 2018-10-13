package WindowsFormsCars_2;

import java.awt.Color;
import java.awt.Graphics;

public class TruckTrailer extends Truck{

	public Color DopColor;//�������������� ����
	public Color getDopColor()
	{
		return DopColor;
	}
	public void setDopColor(Color value)
	{
		DopColor = value;
	}
	private boolean Cabin;//������� ������� ������
	public boolean getCabin()
	{
		return Cabin;
	}
	public void setCabin(boolean value)
	{
		Cabin = value;
	}
	/** 
	 �����������
	 @param maxSpeed ������������ ��������
	 @param weight ��� ����������
	 @param mainColor �������� ���� ������
	 @param dopColor �������������� ����
	 @param frontSpoiler ������� ������� ��������� ��������
	 @param sideSpoiler ������� ������� ������� ���������
	 @param backSpoiler ������� ������� ������� ��������
	*/
	public TruckTrailer(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean cabin)
	{
    	super(maxSpeed, weight, mainColor);
        DopColor = dopColor;
        Cabin=cabin;
    } 
	@Override
	public void DrawCar(Graphics g)//��������� ����������
	{	super.DrawCar(g);
		g.setColor(getMainColor()); //������ 
		g.fillRect( (int)_startPosX + 35,(int) _startPosY - 4, 72, 50);
		
		g.setColor(getDopColor());//������
		g.fillRect((int) _startPosX - 3, (int)_startPosY + 10, 35, 38);
		g.fillRect((int) _startPosX + 30,(int) _startPosY + 43, 30, 5);
		
		g.setColor(Color.black);//������
		g.fillOval((int) _startPosX + 83,(int) _startPosY + 40, 17, 17);
		g.fillOval((int) _startPosX + 70,(int) _startPosY + 40, 17, 17);
		g.fillOval( (int)_startPosX + 40,(int) _startPosY + 40, 17, 17);
		g.fillOval((int) _startPosX + 7,(int) _startPosY + 40, 17, 17);
		 
		g.setColor(Color.blue);//����
		g.fillRect( (int)_startPosX + 5,(int) _startPosY + 15, 20, 15);
	}
}
