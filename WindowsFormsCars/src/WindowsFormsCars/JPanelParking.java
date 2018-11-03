package WindowsFormsCars;

import java.awt.Graphics;

import javax.swing.JPanel;


public class JPanelParking extends JPanel {
	private Parking<ITransport> parking;

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			if (parking != null) {
				parking.Draw(g);
			}
		} catch (Exception ex) {
		}
	}
}
