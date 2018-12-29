package WindowsFormsCars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.Color;
import java.awt.Graphics;

public class Parking<T extends ITransport> implements Iterable<T>, Iterator<T>,
		Comparable<Parking<T>> {
	private HashMap<Integer, T> _places;// Массив объектов, которые храним
	private int _maxCount;
	private int PictureWidth;// Ширина окна отрисовки

	public int getPictureWidth() {
		return PictureWidth;
	}

	public void setPictureWidth(int value) {
		PictureWidth = value;
	}

	private int PictureHeight;// / Высота окна отрисовки

	public int getPictureHeight() {
		return PictureHeight;
	}

	public void setPPictureHeighth(int value) {
		PictureHeight = value;
	}

	private int _placeSizeWidth = 210;// / Размер парковочного места (ширина)
	private int _placeSizeHeight = 80;// / Размер парковочного места (высота)
	private int currentIndex;

	public Parking(int size, int pictureWidth, int pictureHeight) { // Конструктор
		_maxCount = size;
		_places = new HashMap<Integer, T>();
		this.PictureWidth = PictureWidth;
		this.PictureHeight = PictureHeight;
	}

	public int addTransport(T transport) throws ParkingOverflowException,
			ParkingOccupiedPlaceException, ParkingAlreadyHaveException {

		if (this._places.size() == this._maxCount) {
			throw new ParkingOverflowException();
		}
		int index = _places.size();
		for (int i = 0; i <= _places.size(); i++) {
			if (checkFreePlace(i))
				index = i;
			if (_places.containsValue(transport))
				throw new ParkingAlreadyHaveException();
		}
		if (index != _places.size()) {
			_places.put(index, transport);
			_places.get(index).SetPosition(5 + index / 5 * _placeSizeWidth + 5,
					index % 5 * _placeSizeHeight + 15, PictureWidth,
					PictureHeight);
			return index;
		}
		_places.put(this._places.size(), transport);
		_places.get(index).SetPosition(5 + index / 5 * _placeSizeWidth + 5,
				index % 5 * _placeSizeHeight + 15, PictureWidth, PictureHeight);
		return this._places.size() - 1;
	}

	public T removeTransport(int index) throws ParkingNotFoundException {
		if (!checkFreePlace(index)) {
			T transport = _places.get(index);
			_places.remove(index);
			return transport;
		}
		throw new ParkingNotFoundException();
	}

	private boolean checkFreePlace(int index) {// / Метод проверки заполнености
												// парковочного места (ячейки
												// массива)
		return !_places.containsKey(index);
	}

	public void Draw(Graphics g) {// / Метод отрисовки парковки
		DrawMarking(g);
		for (T transport : _places.values()) {
			transport.DrawTruckTrailer(g);
		}
	}

	private void DrawMarking(Graphics g)// / Метод отрисовки разметки
										// парковочных мест
	{
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, (_maxCount / 5) * _placeSizeWidth, 480);// границы
																	// праковки
		for (int i = 0; i < _maxCount / 5; i++) {// отрисовываем, по 5 мест
													// на линии
			for (int j = 0; j < 6; ++j) {
				g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i
						* _placeSizeWidth + 110, j * _placeSizeHeight);
			}
			g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
		}
	}

	public T getTruck(int index) {
		if (_places.get(index) != null) {
			return _places.get(index);
		} else {
			return null;
		}

	}

	@Override
	public int compareTo(Parking<T> other) {
		if (this._places.size() > other._places.size()) {
			return -1;
		} else if (this._places.size() < other._places.size()) {
			return 1;
		} else {
			Integer[] thisKeys = this._places.keySet().toArray(
					new Integer[this._places.size()]);
			Integer[] otherKeys = other._places.keySet().toArray(
					new Integer[other._places.size()]);
			for (int i = 0; i < this._places.size(); i++) {
				if (this._places.get(thisKeys[i]).getClass()
						.equals(Truck.class)
						&& other._places.get(otherKeys[i]).getClass()
								.equals(TruckTrailer.class)) {
					return 1;
				}
				if (this._places.get(thisKeys[i]).getClass()
						.equals(TruckTrailer.class)
						&& other._places.get(otherKeys[i]).getClass()
								.equals(Truck.class)) {
					return -1;
				}
				if (this._places.get(thisKeys[i]).getClass()
						.equals(Truck.class)
						&& other._places.get(otherKeys[i]).getClass()
								.equals(Truck.class)) {
					return ((Truck) this._places.get(thisKeys[i]))
							.compareTo((Truck) other._places.get(otherKeys[i]));
				}
				if (this._places.get(thisKeys[i]).getClass()
						.equals(TruckTrailer.class)
						&& other._places.get(otherKeys[i]).getClass()
								.equals(TruckTrailer.class)) {
					return ((TruckTrailer) this._places.get(thisKeys[i]))
							.compareTo((TruckTrailer) other._places
									.get(otherKeys[i]));
				}
			}
		}
		return 0;
	}
	//Iterable
	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		if (currentIndex + 1 >= _places.size()) {
			currentIndex = -1;
			return false;
		}
		currentIndex++;
		return true;
	}

	@Override
	public T next() {
		return (T) _places.get(currentIndex);
	}

	private void reset() {
		currentIndex = -1;
	}
}
