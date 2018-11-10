package WindowsFormsCars;

import java.util.ArrayList;

public class MultiLevelParking {

	ArrayList<Parking<ITransport>> parkingStages;// / Список с уровнями парковки
	private final int countPlaces = 20; // / Сколько мест на каждом уровне

	//Конструктор
	public MultiLevelParking(int countStages, int pictureWidth,
			int pictureHeight) {
		parkingStages = new ArrayList<Parking<ITransport>>();
		for (int i = 0; i < countStages; ++i) {
			parkingStages.add(new Parking<ITransport>(countPlaces,
					pictureWidth, pictureHeight));
		}
	}
	//(Индексатор)
	public Parking<ITransport> getParking(int index) {
		if ((index > -1) && (index < parkingStages.size())) {
			return parkingStages.get(index);
		}
		return null;
	}
}
