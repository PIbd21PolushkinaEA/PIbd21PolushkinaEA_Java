package WindowsFormsCars_2;

import java.awt.Color;
import java.awt.Graphics;

public class TruckTrailer extends Truck{

	public Color DopColor;//Дополнительный цвет
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
	public TruckTrailer(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean cabin)
	{
    	super(maxSpeed, weight, mainColor);
        DopColor = dopColor;
        Cabin=cabin;
    } 
	@Override
	public void DrawCar(Graphics g)//Отрисовка автомобиля
	{	super.DrawCar(g);
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
