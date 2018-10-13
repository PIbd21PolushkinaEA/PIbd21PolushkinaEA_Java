package WindowsFormsCars_2;
import java.awt.Graphics;

public interface ITransport {
    void SetPosition(int x, int y, int width, int height);  /// Установка позиции автомобиля
    void MoveTransport(Direction direction); /// Изменение направления пермещения
    void DrawCar(Graphics g);/// Отрисовка автомобиля
}
