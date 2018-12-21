package WindowsFormsCars;

public class ParkingOccupiedPlaceException extends Exception {
	
	public ParkingOccupiedPlaceException() {
		super("Это место уже занято");
	}

}
