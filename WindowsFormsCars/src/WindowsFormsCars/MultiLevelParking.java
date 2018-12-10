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

	// Сохранение информации по автомобилям на парковках в файл
	public Boolean SaveData(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			file.delete();
		}
		try (FileOutputStream fileStream = new FileOutputStream(file)) {
			try (BufferedOutputStream bos = new BufferedOutputStream(fileStream)) {
				String str = "CountLeveles:" + parkingStages.size()
						+ System.lineSeparator();
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				for (int i = 0; i < str.length(); i++) {
					byteOut.write(str.charAt(i));
				}
				byte[] info = byteOut.toByteArray();
				fileStream.write(info, 0, info.length);
				for (Parking<ITransport> level : parkingStages) {
					byteOut = new ByteArrayOutputStream();
					str = "Level" + System.lineSeparator();

					for (int i = 0; i < str.length(); i++) {
						byteOut.write(str.charAt(i));
					}
					info = byteOut.toByteArray();
					fileStream.write(info, 0, info.length);
					for (int i = 0; i < countPlaces; i++) {
						ITransport truck = level.getTruck(i);
						if (truck != null) {
							byteOut = new ByteArrayOutputStream();
							String truckInfo = truck.getClass().getName() + ":"
									+ truck.getInfo() + System.lineSeparator();
							truckInfo = truckInfo.substring(17);
							for (int j = 0; j < truckInfo.length(); j++) {
								byteOut.write(truckInfo.charAt(j));
							}
							info = byteOut.toByteArray();
							fileStream.write(info, 0, info.length);
						}
					}
				}
			}
			fileStream.close();
			return true;
		} catch (IOException e) {
			return false;

		}
	}

	public boolean LoadData(String filename) {
		File file = new File(filename);
		if (!file.exists()) {
			return false;
		}
		try (FileInputStream fileStream = new FileInputStream(filename)) {
			String s = "";
			try (BufferedInputStream bs = new BufferedInputStream(fileStream)) {
				Path path = Paths.get(file.getAbsolutePath());
				byte[] b = new byte[fileStream.available()];
				b = Files.readAllBytes(path);
				ByteArrayInputStream bis = new ByteArrayInputStream(b);
				String value = new String(b, StandardCharsets.UTF_8);
				while (bis.read(b, 0, b.length) > 0) {
					s += value;
				}
				s = s.replace("\r", "");
				String[] strs = s.split("\n");
				if (strs[0].contains("CountLeveles")) {
					if (parkingStages != null) {
						parkingStages.clear();
					}
					parkingStages = new ArrayList<Parking<ITransport>>();
				} else {
					return false;
				}
				int counter = -1;
				for (int i = 0; i < strs.length; i++) {
					if (strs[i].startsWith("Level")) {
						counter++;
						parkingStages.add(new Parking<ITransport>(countPlaces,
								pictureWidth, pictureHeight));
						continue;
					}
					if (strs[i].startsWith("TruckTrailer")) {
						ITransport truck = new TruckTrailer(
								strs[i].split(":")[1]);
						int number = parkingStages.get(counter).addTransport(
								truck);
						if (number == -1) {
							return false;
						}
						continue;
					}
					if (strs[i].startsWith("Truck")) {
						ITransport truck = new Truck(strs[i].split(":")[1]);
						int number = parkingStages.get(counter).addTransport(
								truck);
						if (number == -1) {
							return false;
						}
						continue;
					}
				}
			}
			return true;
		} catch (IOException ex) {
			return false;
		}
	}

}
