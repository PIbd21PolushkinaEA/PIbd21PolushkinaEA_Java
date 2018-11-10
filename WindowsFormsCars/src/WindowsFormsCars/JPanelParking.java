package WindowsFormsCars;

import java.awt.Graphics;

import javax.swing.JList;
import javax.swing.JPanel;

public class JPanelParking extends JPanel {
	private MultiLevelParking parking;
	private JList listLevels;

	public void setParking(MultiLevelParking parking) {
		this.parking = parking;
	}

	public void setList(JList listLevels) {
		this.listLevels = listLevels;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			if (parking != null) {
				if (listLevels.getSelectedIndex() != -1) {
					parking.getParking(listLevels.getSelectedIndex()).Draw(g);
				}
			}
		} catch (Exception ex) {
		}
	}
}
