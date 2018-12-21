package WindowsFormsCars;

public class ParkingNotFoundException extends Exception {

		public ParkingNotFoundException() {
			super("Грузовик не найден");
		}
}
