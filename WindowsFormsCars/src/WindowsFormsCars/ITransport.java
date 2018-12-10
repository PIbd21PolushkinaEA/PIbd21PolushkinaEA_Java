package WindowsFormsCars;

import java.awt.Color;
import java.awt.Graphics;

public interface ITransport {
	void SetPosition(int x, int y, int width, int height); // / ���������
															// �������
															// ����������

	void MoveTransport(Direction direction); // / ��������� �����������
												// ����������

	void DrawTruckTrailer(Graphics g);// ��������� ����������

	void SetMainColor(Color color);// ����� ��������� ����� ����������

	String getInfo();
}
