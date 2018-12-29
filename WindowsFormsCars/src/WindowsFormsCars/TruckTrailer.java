package WindowsFormsCars;

import java.awt.Color;
import java.awt.Graphics;

public class TruckTrailer extends Truck {

	public Color DopColor;// Дополнительный цвет

	public Color getDopColor() {
		return DopColor;
	}

	public void setDopColor(Color value) {
		DopColor = value;
	}

	private boolean Cabin;// Признак наличия кабины

	public boolean getCabin() {
		return Cabin;
	}

	public void setCabin(boolean value) {
		Cabin = value;
	}

	public TruckTrailer(int maxSpeed, int weight, Color mainColor,// Конструктор
			Color dopColor, boolean cabin) {
		super(maxSpeed, weight, mainColor);
		DopColor = dopColor;
		Cabin = cabin;
	}

	public TruckTrailer(String info) {// / Конструктор
		super(info);
		String[] strs = info.split(";");
		if (strs.length == 9) {
			MaxSpeed = Integer.parseInt(strs[0]);
			Weight = Integer.parseInt(strs[1]);
			MainColor = new Color(Integer.parseInt(strs[2]),
					Integer.parseInt(strs[3]), Integer.parseInt(strs[4]));
			DopColor = new Color(Integer.parseInt(strs[5]),
					Integer.parseInt(strs[6]), Integer.parseInt(strs[7]));
			Cabin = Boolean.parseBoolean(strs[8]);
		}
	}

	@Override
	public void DrawTruckTrailer(Graphics g)// Отрисовка автомобиля
	{
		super.DrawTruckTrailer(g);
		g.setColor(getDopColor()); // прицеп
		g.fillRect((int) _startPosX + 35, (int) _startPosY - 4, 72, 50);

		g.setColor(getMainColor());// кабина
		g.fillRect((int) _startPosX - 3, (int) _startPosY + 10, 35, 38);
		g.fillRect((int) _startPosX + 30, (int) _startPosY + 43, 30, 5);

		g.setColor(Color.black);// колеса
		g.fillOval((int) _startPosX + 83, (int) _startPosY + 40, 17, 17);
		g.fillOval((int) _startPosX + 70, (int) _startPosY + 40, 17, 17);
		g.fillOval((int) _startPosX + 40, (int) _startPosY + 40, 17, 17);
		g.fillOval((int) _startPosX + 7, (int) _startPosY + 40, 17, 17);

		g.setColor(Color.blue);// окно
		g.fillRect((int) _startPosX + 5, (int) _startPosY + 15, 20, 15);
	}

	// / Смена дополнительного цвета
	// / </summary>
	// / <param name="color"></param>
	public void SetDopColor(Color color) {
		DopColor = color;
	}

	public String getInfo() {
		return MaxSpeed + ";" + Weight + ";" + MainColor.getRed() + ";"
				+ MainColor.getGreen() + ";" + MainColor.getBlue() + ";"
				+ DopColor.getRed() + ";" + DopColor.getGreen() + ";"
				+ DopColor.getBlue() + ";" + Cabin;
	}

	public int compareTo(TruckTrailer another) {
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
		if (DopColor != another.DopColor) {
			return Integer.valueOf(DopColor.getRGB()).compareTo(
					another.DopColor.getRGB());
		}
		if (Cabin != another.Cabin) {
			return Boolean.valueOf(Cabin).compareTo(another.Cabin);
		}
		return 0;
	}

	@Override
	public boolean equals(Object another) {
		if (another == null) {
			return false;
		}
		if (!(another instanceof TruckTrailer)) {
			return false;
		}
		TruckTrailer tank = (TruckTrailer) another;
		return equals(tank);
	}

	public boolean equals(TruckTrailer another) {
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
		if (DopColor != another.DopColor) {
			return false;
		}
		if (Cabin != another.Cabin) {
			return false;
		}

		return true;
	}
}
