package WindowsFormsCars;

import java.awt.Color;
import java.awt.Graphics;

public class Truck {
	private float _startPosX;//Левая координата отрисовки автомобиля
	private float _startPosY;//Правая кооридната отрисовки автомобиля
	public int _pictureWidth; //Ширина окна отрисовки
	public int _pictureHeight;//Высота окна отрисовки
	private static final int carWidth = 100;//Ширина отрисовки автомобиля
	private static final int carHeight = 60;//Высота отрисовки автомобиля
	private int MaxSpeed; //Максимальная скорость
	public int getMaxSpeed()
	{
		return MaxSpeed;
	}
	public void setMaxSpeed(int value)
	{
		MaxSpeed = value;
	}
	private float Weight;//Вес автомобиля
	public  float getWeight()
	{
		return Weight;
	}
	public void setWeight(float value)
	{
		Weight = value;
	}
	private Color MainColor;//Основной цвет кузова
	public  Color getMainColor()
	{
		return MainColor;
	}
	public void setMainColor(Color value)
	{
		MainColor = value;
	}
	private Color DopColor;//Дополнительный цвет
	public Color getDopColor()
	{
		return DopColor;
	}
	public void setDopColor(Color value)
	{
		DopColor = value;
	}
	private boolean Cabin;//Признак наличия кабины
	public boolean getCabin()
	{
		return Cabin;
	}
	public void setCabin(boolean value)
	{
		Cabin = value;
	}
	/** 
	 Конструктор
	 @param maxSpeed Максимальная скорость
	 @param weight Вес автомобиля
	 @param mainColor Основной цвет кузова
	 @param dopColor Дополнительный цвет
	 @param frontSpoiler Признак наличия переднего спойлера
	 @param sideSpoiler Признак наличия боковых спойлеров
	 @param backSpoiler Признак наличия заднего спойлера
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
	 Установка позиции автомобиля
	 @param x Координата X
	 @param y Координата Y
	 @param width Ширина картинки
	 @param height Высота картинки
	*/
	public void SetPosition(int x, int y, int width, int height)throws Exception
	{
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}
	public  void MoveTransport(Direction direction)throws Exception //Изменение направления пермещения
	{
		float step = getMaxSpeed() * 100 / getWeight();
		switch (direction)
		{
			// вправо
			case Right:
				if (_startPosX + step < _pictureWidth - carWidth)
				{
					_startPosX += step;
				}
				break;
			//влево
			case Left:
				if (_startPosX - step > 0)
				{
					_startPosX -= step;
				}
				break;
			//вверх
			case Up:
				if (_startPosY - step > 0)
				{
					_startPosY -= step;
				}
				break;
			//вниз
			case Down:
				if (_startPosY + step < _pictureHeight - carHeight)
				{
					_startPosY += step;
				}
				break;
		}
	}
	public void DrawCar(Graphics g)throws Exception//Отрисовка автомобиля
	{
		g.setColor(getMainColor()); //прицеп 
		g.fillRect( (int)_startPosX + 35,(int) _startPosY - 4, 72, 50);
		
		g.setColor(getDopColor());//кабина
		g.fillRect((int) _startPosX - 3, (int)_startPosY + 10, 35, 38);
		g.fillRect((int) _startPosX + 30,(int) _startPosY + 43, 30, 5);
		
		g.setColor(Color.black);//колеса
		g.fillOval((int) _startPosX + 83,(int) _startPosY + 40, 17, 17);
		g.fillOval((int) _startPosX + 70,(int) _startPosY + 40, 17, 17);
		g.fillOval( (int)_startPosX + 40,(int) _startPosY + 40, 17, 17);
		g.fillOval((int) _startPosX + 7,(int) _startPosY + 40, 17, 17);
		 
		g.setColor(Color.blue);//окно
		g.fillRect( (int)_startPosX + 5,(int) _startPosY + 15, 20, 15);
	}
}
