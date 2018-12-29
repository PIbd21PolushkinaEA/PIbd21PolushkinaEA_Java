package WindowsFormsCars;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;

public class FormTruckConfig extends JDialog {

	ITransport truck;
	JPanel panel;
	boolean r;

	Color color;
	Color dopColor;
	int maxSpeed;

	public FormTruckConfig(JFrame parent) {
		super(parent, true);
		initialize();
	}

	public boolean res() {
		setVisible(true);
		return r;
	}

	private void initialize() {
		this.getContentPane().setBackground(SystemColor.controlHighlight);
		this.setBounds(100, 100, 540, 350);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBounds(194, 29, 143, 127);
		this.getContentPane().add(panel);

		MouseListener mouseL = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				JComponent jc = (JComponent) e.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, e, TransferHandler.COPY);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		};

		panel.setDropTarget(new DropTarget() {

			public void drop(DropTargetDropEvent e) {
				try {
					for (DataFlavor df : e.getTransferable()
							.getTransferDataFlavors()) {
						if (e.getTransferable().getTransferData(df) == "Обычный грузовик") {
							truck = new Truck(100,1000,Color.WHITE);
						} else if (e.getTransferable().getTransferData(df) == "Грузовик-полуприцеп") {
							truck = new TruckTrailer(100,1000,Color.WHITE, Color.BLACK, true);
						}
						draw(panel, truck);
					}
				} catch (Exception ex) {
				}
			}

			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable()
						.getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JLabel lblMainColor = new JLabel(
				"\u041E\u0441\u043D\u043E\u0432\u043D\u043E\u0439 \u0446\u0432\u0435\u0442");
		lblMainColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMainColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainColor.setBounds(194, 187, 143, 27);
		this.getContentPane().add(lblMainColor);

		JLabel lblDopColor = new JLabel(
				"\u0414\u043E\u043F.\u0446\u0432\u0435\u0442");
		lblDopColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDopColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDopColor.setBounds(194, 235, 143, 27);
		this.getContentPane().add(lblDopColor);

		lblMainColor.setDropTarget(new DropTarget() {

			public void drop(DropTargetDropEvent e) {
				if (truck != null) {
					try {
						for (DataFlavor df : e.getTransferable()
								.getTransferDataFlavors()) {
							truck.SetMainColor((selectColor(e.getTransferable()
									.getTransferData(df).toString())));
							draw(panel, truck);
						}
					} catch (Exception ex) {
					}
				}
			}

			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable()
						.getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (UnsupportedFlavorException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		lblDopColor.setDropTarget(new DropTarget() {

			public void drop(DropTargetDropEvent e) {
				if (truck != null) {
					try {
						for (DataFlavor df : e.getTransferable()
								.getTransferDataFlavors()) {
							((TruckTrailer) truck).setDopColor((selectColor(e
									.getTransferable().getTransferData(df)
									.toString())));
							draw(panel, truck);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}

			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable()
						.getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (UnsupportedFlavorException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel panelYellow = new JPanel();
		panelYellow.setName("yellow");
		panelYellow.setBackground(Color.YELLOW);
		panelYellow.setBounds(444, 135, 46, 39);
		this.getContentPane().add(panelYellow);

		JPanel panelBlue = new JPanel();
		panelBlue.setName("blue");
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(386, 135, 46, 39);
		this.getContentPane().add(panelBlue);

		JPanel panelRed = new JPanel();
		panelRed.setName("red");
		panelRed.setBackground(Color.RED);
		panelRed.setBounds(444, 83, 46, 39);
		this.getContentPane().add(panelRed);

		JPanel panelGreen = new JPanel();
		panelGreen.setName("green");
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(386, 83, 46, 39);
		this.getContentPane().add(panelGreen);

		JPanel panelBlack = new JPanel();
		panelBlack.setName("black");
		panelBlack.setBackground(Color.BLACK);
		panelBlack.setBounds(386, 29, 46, 39);
		this.getContentPane().add(panelBlack);

		JPanel panelWhite = new JPanel();
		panelWhite.setName("white");
		panelWhite.setBackground(Color.WHITE);
		panelWhite.setBounds(444, 29, 46, 39);
		this.getContentPane().add(panelWhite);

		JPanel panelGray = new JPanel();
		panelGray.setName("gray");
		panelGray.setBackground(Color.GRAY);
		panelGray.setBounds(386, 187, 46, 39);
		this.getContentPane().add(panelGray);

		JPanel panelGold = new JPanel();
		panelGold.setName("gold");
		panelGold.setBackground(Color.ORANGE);
		panelGold.setBounds(444, 187, 46, 39);
		this.getContentPane().add(panelGold);

		panelYellow.addMouseListener(mouseL);
		panelYellow.setTransferHandler(new TransferHandler("name"));

		panelBlue.addMouseListener(mouseL);
		panelBlue.setTransferHandler(new TransferHandler("name"));

		panelRed.addMouseListener(mouseL);
		panelRed.setTransferHandler(new TransferHandler("name"));

		panelGreen.addMouseListener(mouseL);
		panelGreen.setTransferHandler(new TransferHandler("name"));

		panelBlack.addMouseListener(mouseL);
		panelBlack.setTransferHandler(new TransferHandler("name"));

		panelWhite.addMouseListener(mouseL);
		panelWhite.setTransferHandler(new TransferHandler("name"));

		panelGray.addMouseListener(mouseL);
		panelGray.setTransferHandler(new TransferHandler("name"));

		panelGold.addMouseListener(mouseL);
		panelGold.setTransferHandler(new TransferHandler("name"));

		JButton buttonOk = new JButton(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		buttonOk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				r = true;
				dispose();
			}
		});
		buttonOk.setBounds(27, 197, 100, 40);
		this.getContentPane().add(buttonOk);

		JButton buttonCancel = new JButton(
				"\u041E\u0442\u043C\u0435\u043D\u0430");
		buttonCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				r = false;
				dispose();
			}
		});
		buttonCancel.setBounds(27, 250, 100, 40);
		this.getContentPane().add(buttonCancel);

		JPanel panelGroup = new JPanel();
		panelGroup.setBounds(12, 29, 170, 127);
		getContentPane().add(panelGroup);
		panelGroup.setLayout(null);

		JLabel lblTruckTrailer = new JLabel(
				"\u0413\u0440\u0443\u0437\u043E\u0432\u0438\u043A-\u043F\u043E\u043B\u0443\u043F\u0440\u0438\u0446\u0435\u043F");
		lblTruckTrailer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTruckTrailer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTruckTrailer.setBounds(13, 72, 145, 30);
		panelGroup.add(lblTruckTrailer);
		lblTruckTrailer.addMouseListener(mouseL);
		lblTruckTrailer.setTransferHandler(new TransferHandler("text"));

		JLabel lblTruck = new JLabel(
				"\u041E\u0431\u044B\u0447\u043D\u044B\u0439 \u0433\u0440\u0443\u0437\u043E\u0432\u0438\u043A");
		lblTruck.setHorizontalAlignment(SwingConstants.CENTER);
		lblTruck.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTruck.setBounds(13, 23, 144, 30);
		panelGroup.add(lblTruck);

		lblTruck.addMouseListener(mouseL);
		lblTruck.setTransferHandler(new TransferHandler("text"));
		buttonCancel.addActionListener((ActionEvent e) -> {
			r = false;
			dispose();
		});
	}

	public ITransport getTruck() {
		return truck;
	}

	public void draw(JPanel panel, ITransport truck) {
		if (truck != null) {
			Graphics gr = panel.getGraphics();
			gr.clearRect(0, 0, panel.getWidth(), panel.getHeight());
			truck.SetPosition(10, 35, panel.getWidth(), panel.getHeight());
			truck.DrawTruckTrailer(gr);
		}
	}

	public Color selectColor(String s) {
		switch (s) {
		case "yellow":
			return Color.yellow;
		case "blue":
			return Color.blue;
		case "red":
			return Color.red;
		case "green":
			return Color.green;
		case "black":
			return Color.black;
		case "white":
			return Color.white;
		case "gray":
			return Color.gray;
		case "gold":
			return Color.orange;
		}
		return null;
	}
}
