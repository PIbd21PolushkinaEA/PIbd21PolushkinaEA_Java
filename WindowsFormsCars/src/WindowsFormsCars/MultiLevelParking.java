package WindowsFormsCars;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.omg.CORBA.Environment;

public class MultiLevelParking {

	ArrayList<Parking<ITransport>> parkingStages;// / Список с уровнями парковки
	private final int countPlaces = 15; // / Сколько мест на каждом уровне

	int pictureWidth;
	int pictureHeight;

	// Конструктор
	public MultiLevelParking(int countStages, int pictureWidth,
			int pictureHeight) {
		parkingStages = new ArrayList<Parking<ITransport>>();
		for (int i = 0; i < countStages; ++i) {
			parkingStages.add(new Parking<ITransport>(countPlaces,
					pictureWidth, pictureHeight));
		}
	}

	// (Индексатор)
	public Parking<ITransport> getParking(int index) {
		if ((index > -1) && (index < parkingStages.size())) {
			return parkingStages.get(index);
		}
		return null;
	}

}
