package WindowsFormsCars;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class FormTruck extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private Truck truck;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTruck frame = new FormTruck();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			if (truck != null) {
				truck.DrawCar(g);
			}
		} catch (Exception ex) {

		}
	}

	public void moveButton(JButton sender) {
		try {
			String name = sender.getToolTipText();
			switch (name) {
			case "Up":
				truck.MoveTransport(Direction.Up);
				break;
			case "Down":
				truck.MoveTransport(Direction.Down);
				break;
			case "Left":
				truck.MoveTransport(Direction.Left);
				break;
			case "Right":
				truck.MoveTransport(Direction.Right);
				break;
			}
			this.repaint();
		} catch (Exception ex) {
			System.out.print("Iiii?aeun ia nicaai");
		}
	}

	public FormTruck() {
		initialize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton buttonCreate = new JButton(
				"\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					truck = new Truck(100 + (int) (Math.random() * 300),
							1000 + (int) (Math.random() * 2000), Color.BLUE,
							Color.RED, true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					truck.SetPosition(70 + (int) (Math.random() * 160),
							70 + (int) (Math.random() * 160),
							FormTruck.this.getWidth(),
							FormTruck.this.getHeight());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				FormTruck.this.repaint();
			}
		});
		buttonCreate.setBounds(10, 11, 89, 42);
		contentPane.add(buttonCreate);

		JButton buttonUp = new JButton("");
		buttonUp.setBackground(Color.WHITE);
		buttonUp.setIcon(new ImageIcon(
				"D:\\WindowsFormsCars\\src\\WindowsFormsCars\\up.jpg"));
		buttonUp.setToolTipText("Up");
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonUp);
			}
		});
		buttonUp.setBounds(780, 347, 40, 40);
		contentPane.add(buttonUp);

		JButton buttonLeft = new JButton("");
		buttonLeft.setBackground(Color.WHITE);
		buttonLeft.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonLeft.setForeground(Color.BLACK);
		buttonLeft.setIcon(new ImageIcon(
				"D:\\WindowsFormsCars\\src\\WindowsFormsCars\\left.jpg"));
		buttonLeft.setToolTipText("Left");

		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonLeft);
			}
		});
		buttonLeft.setBounds(728, 400, 40, 40);
		contentPane.add(buttonLeft);

		JButton buttonDown = new JButton("");
		buttonDown.setBackground(Color.WHITE);
		buttonDown.setIcon(new ImageIcon(
				"D:\\WindowsFormsCars\\src\\WindowsFormsCars\\down.jpg"));
		buttonDown.setToolTipText("Down");
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonDown);
			}
		});
		buttonDown.setBounds(780, 400, 40, 40);
		contentPane.add(buttonDown);

		JButton buttonRight = new JButton("");
		buttonRight.setBackground(Color.WHITE);
		buttonRight.setIcon(new ImageIcon(
				"D:\\WindowsFormsCars\\src\\WindowsFormsCars\\right.jpg"));
		buttonRight.setToolTipText("Right");
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonRight);
			}
		});
		buttonRight.setBounds(830, 400, 40, 40);
		contentPane.add(buttonRight);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
