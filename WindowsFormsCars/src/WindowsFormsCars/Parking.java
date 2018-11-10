package WindowsFormsCars;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Graphics;

public class Parking<T extends ITransport> {
	private HashMap<Integer, T> _places;//������ ��������, ������� ������
	private int _maxCount;
	private int PictureWidth;//������ ���� ���������

	public int getPictureWidth() {
		return PictureWidth;
	}

	public void setPictureWidth(int value) {
		PictureWidth = value;
	}

	private int PictureHeight;// / ������ ���� ���������

	public int getPictureHeight() {
		return PictureHeight;
	}

	public void setPPictureHeighth(int value) {
		PictureHeight = value;
	}

	private int _placeSizeWidth = 210;// / ������ ������������ ����� (������)
	private int _placeSizeHeight = 80;// / ������ ������������ ����� (������)

	public Parking(int size, int pictureWidth, int pictureHeight) { // �����������
		_maxCount = size;
		_places = new HashMap<Integer, T>();
		this.PictureWidth = PictureWidth;
		this.PictureHeight = PictureHeight;
	}

	public int addTransport(T transport) {
		if (_places.size() == _maxCount) {
			return -1;
		}
		for (int i = 0; i < _maxCount; i++) {
			if (checkFreePlace(i)) {
				_places.put(i, transport);
				_places.get(i).SetPosition(5 + i / 5 * _placeSizeWidth + 5,
						i % 5 * _placeSizeHeight + 15, PictureWidth,
						PictureHeight);
				return i;
			}
		}
		return -1;
	}

	public T removeTransport(int index) {
		if (!checkFreePlace(index)) {
			T transport = _places.get(index);
			_places.remove(index);
			return transport;
		}
		return null;
	}

	private boolean checkFreePlace(int index) {// / ����� �������� ������������
												// ������������ ����� (������
												// �������)
		return !_places.containsKey(index);
	}

	public void Draw(Graphics g) {// / ����� ��������� ��������
		DrawMarking(g);
		for (T transport : _places.values()) {
			transport.DrawTruckTrailer(g);
		}
	}

	private void DrawMarking(Graphics g)// / ����� ��������� ��������
										// ����������� ����
	{
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, (_maxCount / 5) * _placeSizeWidth, 480);// �������
																	// ��������
		for (int i = 0; i < _maxCount / 5; i++) {// ������������, �� 5 ����
													// �� �����
			for (int j = 0; j < 6; ++j) {
				g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i
						* _placeSizeWidth + 110, j * _placeSizeHeight);
			}
			g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
		}
	}

}
