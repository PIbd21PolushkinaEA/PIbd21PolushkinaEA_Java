package WindowsFormsCars_2;

import java.awt.Color;
import java.awt.Graphics;

public abstract class TruckAbstract implements ITransport{
    protected float _startPosX;/// Левая координата отрисовки автомобиля
    protected float _startPosY;/// Правая кооридната отрисовки автомобиля
    protected int _pictureWidth;  /// Ширина окна отрисовки
    protected int _pictureHeight;//Высота окна отрисовки
    public int MaxSpeed; //Максимальная скорость
    public int getMaxSpeed()
	{
		return MaxSpeed;
	}
	public void setMaxSpeed(int value)
	{
		MaxSpeed = value;
	}  
	public float Weight;//Вес автомобиля
	public  float getWeight()
	{
		return Weight;
	}
	public void setWeight(float value)
	{
		Weight = value;
	}
	public Color MainColor;//Основной цвет кузова
	public  Color getMainColor()
	{
		return MainColor;
	}
	public void setMainColor(Color value)
	{
		MainColor = value;
	}
    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }
    public abstract void DrawCar(Graphics g);
    public abstract void MoveTransport(Direction direction);
}


