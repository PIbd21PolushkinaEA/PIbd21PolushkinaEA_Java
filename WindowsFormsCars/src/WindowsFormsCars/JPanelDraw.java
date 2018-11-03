package WindowsFormsCars;

import java.awt.Graphics;

import javax.swing.JPanel;


public class JPanelDraw extends JPanel {
	private ITransport transport;

	public void setTransport(ITransport transport) {
		this.transport = transport;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (transport != null) {
			transport.DrawTruckTrailer(g);
		}
	}
}
