package WindowsFormsCars;

import java.awt.Color;
import java.awt.Graphics;

public interface ITransport {
	void SetPosition(int x, int y, int width, int height); // / Установка
															// позиции
															// автомобиля

	void MoveTransport(Direction direction); // / Изменение направления
												// пермещения

	void DrawTruckTrailer(Graphics g);// Отрисовка автомобиля

	void SetMainColor(Color color);// Смена основного цвета автомобиля

	String getInfo();
}
