package WindowsFormsCars;

import java.awt.Color;
import java.awt.Graphics;

public class Truck {
	private float _startPosX;//����� ���������� ��������� ����������
	private float _startPosY;//������ ���������� ��������� ����������
	public int _pictureWidth; //������ ���� ���������
	public int _pictureHeight;//������ ���� ���������
	private static final int carWidth = 100;//������ ��������� ����������
	private static final int carHeight = 60;//������ ��������� ����������
	private int MaxSpeed; //������������ ��������
	public int getMaxSpeed()
	{
		return MaxSpeed;
	}
	public void setMaxSpeed(int value)
	{
		MaxSpeed = value;
	}
	private float Weight;//��� ����������
	public  float getWeight()
	{
		return Weight;
	}
	public void setWeight(float value)
	{
		Weight = value;
	}
	private Color MainColor;//�������� ���� ������
	public  Color getMainColor()
	{
		return MainColor;
	}
	public void setMainColor(Color value)
	{
		MainColor = value;
	}
	private Color DopColor;//�������������� ����
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
	public Truck(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean cabin)throws Exception
	{
		MaxSpeed=maxSpeed;
		setWeight(weight);
		setMainColor(mainColor);
		setDopColor(dopColor);
		setCabin(cabin);

	}
	/** 
	 ��������� ������� ����������
	 @param x ���������� X
	 @param y ���������� Y
	 @param width ������ ��������
	 @param height ������ ��������
	*/
	public void SetPosition(int x, int y, int width, int height)throws Exception
	{
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}
	public  void MoveTransport(Direction direction)throws Exception //��������� ����������� ����������
	{
		float step = getMaxSpeed() * 100 / getWeight();
		switch (direction)
		{
			// ������
			case Right:
				if (_startPosX + step < _pictureWidth - carWidth)
				{
					_startPosX += step;
				}
				break;
			//�����
			case Left:
				if (_startPosX - step > 0)
				{
					_startPosX -= step;
				}
				break;
			//�����
			case Up:
				if (_startPosY - step > 0)
				{
					_startPosY -= step;
				}
				break;
			//����
			case Down:
				if (_startPosY + step < _pictureHeight - carHeight)
				{
					_startPosY += step;
				}
				break;
		}
	}
	public void DrawCar(Graphics g)throws Exception//��������� ����������
	{
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
