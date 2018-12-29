package WindowsFormsCars;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Truck extends TruckAbstract implements Comparable<Truck> {

	protected final int carWidth = 100;// / Ширина отрисовки автомобиля
	protected final int carHeight = 60;// / Ширина отрисовки автомобиля

	public Truck(int maxSpeed, int weight, Color mainColor) // / Конструктор
	{
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
	}

	public Truck(String info)// / Конструктор
	{
		String[] strs = info.split(";");
		if (strs.length == 5) {
			MaxSpeed = Integer.parseInt(strs[0]);
			Weight = Integer.parseInt(strs[1]);
			MainColor = new Color(Integer.parseInt(strs[2]),
					Integer.parseInt(strs[3]), Integer.parseInt(strs[4]));
		}
	}

	@Override
	public void MoveTransport(Direction direction) {
		float step = getMaxSpeed() * 100 / getWeight();
		switch (direction) {
		// вправо
		case Right:
			if (_startPosX + step < _pictureWidth - carWidth) {
				_startPosX += step;
			}
			break;
		// влево
		case Left:
			if (_startPosX - step > 0) {
				_startPosX -= step;
			}
			break;
		// вверх
		case Up:
			if (_startPosY - step > 0) {
				_startPosY -= step;
			}
			break;
		// вниз
		case Down:
			if (_startPosY + step < _pictureHeight - carHeight) {
				_startPosY += step;
			}
			break;
		}
	}

	@Override
	public void DrawTruckTrailer(Graphics g) {
		g.setColor(getMainColor());// кабина
		g.fillRect((int) _startPosX - 3, (int) _startPosY + 10, 35, 38);
		g.fillRect((int) _startPosX + 30, (int) _startPosY + 43, 70, 5);

		g.setColor(Color.black);// колеса
		g.fillOval((int) _startPosX + 83, (int) _startPosY + 40, 17, 17);
		g.fillOval((int) _startPosX + 70, (int) _startPosY + 40, 17, 17);
		g.fillOval((int) _startPosX + 40, (int) _startPosY + 40, 17, 17);
		g.fillOval((int) _startPosX + 7, (int) _startPosY + 40, 17, 17);

		g.setColor(Color.blue);// окно
		g.fillRect((int) _startPosX + 5, (int) _startPosY + 15, 20, 15);
	}

	@Override
	public String getInfo() {
		return MaxSpeed + ";" + Weight + ";" + MainColor.getRed() + ";"
				+ MainColor.getGreen() + ";" + MainColor.getBlue();
	}

	@Override
	public int compareTo(Truck another) {
		if (another == null) {
			return 1;
		}
		if (MaxSpeed != another.MaxSpeed) {
			return Integer.valueOf(MaxSpeed).compareTo(another.MaxSpeed);
		}
		if (Weight != another.Weight) {
			return Integer.valueOf(Weight).compareTo(another.Weight);
		}
		if (MainColor != another.MainColor) {
			return Integer.valueOf(MainColor.getRGB()).compareTo(
					another.MainColor.getRGB());
		}
		return 0;
	}

	@Override
	public boolean equals(Object another) {
		if (another == null) {
			return false;
		}
		if (!(another instanceof Truck)) {
			return false;
		}
		Truck truck = (Truck) another;
		return equals(truck);
	}

	public boolean equals(Truck another) {
		if (another == null) {
			return false;
		}
		if (MaxSpeed != another.MaxSpeed) {
			return false;
		}
		if (Weight != another.Weight) {
			return false;
		}
		if (MainColor != another.MainColor) {
			return false;
		}
		return true;
	}

}
