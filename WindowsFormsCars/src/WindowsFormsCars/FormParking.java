package WindowsFormsCars;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FormParking extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	private ITransport truck;
	private MultiLevelParking parking; // Объект от класса многоуровневой
										// парковки

	private final int countLevel = 5; // Количество уровней-парковок

	private static JPanelParking panelParking;
	FormTruckConfig select;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormParking frame = new FormParking();
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
	public FormParking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanelParking panelParking = new JPanelParking();
		panelParking.setBounds(12, 50, 630, 432);
		contentPane.add(panelParking);

		DefaultListModel listModel = new DefaultListModel();
		for (int i = 0; i < countLevel; i++) {
			listModel.addElement("Уровень " + Integer.toString(i + 1));
		}

		JList listLevels = new JList(listModel);
		listLevels.setBounds(701, 40, 170, 124);
		contentPane.add(listLevels);
		listLevels
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listLevels.setSelectedIndex(0);

		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				panelParking.repaint();
			}
		};
		listLevels.addListSelectionListener(listSelectionListener);

		parking = new MultiLevelParking(countLevel, panelParking.getWidth(),
				panelParking.getHeight());
		panelParking.setParking(parking);
		panelParking.setList(listLevels);

		JButton buttonSetTruck = new JButton(
				"\u0417\u0430\u043A\u0430\u0437\u0430\u0442\u044C \u0433\u0440\u0443\u0437\u043E\u0432\u0438\u043A");
		buttonSetTruck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				select = new FormTruckConfig(frame);
				if (select.res()) {
					ITransport truck = select.getTruck();
					if (truck != null) {
						int place = parking.getParking(
								listLevels.getSelectedIndex()).addTransport(
								truck);

						if (place != -1) {
							panelParking.repaint();
						}
					}
					contentPane.repaint();
				}
			}
		});
		buttonSetTruck.setBounds(665, 185, 245, 45);
		contentPane.add(buttonSetTruck);
		JPanel panelGroupElements = new JPanel();
		panelGroupElements.setBounds(665, 243, 245, 199);
		contentPane.add(panelGroupElements);
		panelGroupElements.setLayout(null);
		JLabel lblNewLabel = new JLabel("\u041C\u0435\u0441\u0442\u043E");
		lblNewLabel.setBounds(26, 12, 40, 14);
		panelGroupElements.add(lblNewLabel);
		JPanelDraw panelTakeTrain = new JPanelDraw();
		panelTakeTrain.setBounds(10, 72, 223, 122);
		panelGroupElements.add(panelTakeTrain);
		JButton buttonTakeTruck = new JButton(
				"\u0417\u0430\u0431\u0440\u0430\u0442\u044C ");
		buttonTakeTruck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (listLevels.getSelectedIndex() == -1) {
					return;
				}
				int numberOfPlace = 0;
				try {
					numberOfPlace = Integer.parseInt(textField.getText());
				} catch (Exception ex) {
					textField.setText("Invalid input");
					return;
				}
				truck = parking.getParking(listLevels.getSelectedIndex())
						.removeTransport(numberOfPlace);
				if (truck != null) {
					truck.SetPosition(5, 5, panelTakeTrain.getWidth(),
							panelTakeTrain.getHeight());
				}
				panelTakeTrain.setTransport(truck);
				panelTakeTrain.repaint();
				panelParking.repaint();
			}
		});

		buttonTakeTruck.setBounds(10, 39, 223, 23);
		panelGroupElements.add(buttonTakeTruck);
		textField = new JTextField();
		textField.setBounds(78, 11, 79, 20);
		panelGroupElements.add(textField);
		textField.setColumns(10);

		JLabel levelsLabel = new JLabel("\u0423\u0440\u043E\u0432\u043D\u0438:");
		levelsLabel.setBounds(756, 11, 56, 16);
		contentPane.add(levelsLabel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(12, 11, 50, 26);
		contentPane.add(menuBar);
		JMenu menufile = new JMenu("Файл");
		menufile.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menufile);
		JMenuItem save = new JMenuItem("Сохнанить");
		JMenuItem download = new JMenuItem("Загрузить");
		menufile.add(save);
		menufile.addSeparator();
		menufile.add(download);

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser filesave = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"txt file", "txt");
				filesave.setFileFilter(filter);
				if (filesave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = filesave.getSelectedFile();
					String path = file.getAbsolutePath();
					if (parking.SaveData(path)) {
						JOptionPane.showMessageDialog(null,
								"Сохранение прошло успешно");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "Не сохранилось",
								"", 0, null);
					}
				}

			}

		});
		download.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"txt file", "txt");
				fileChooser.setFileFilter(filter);
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						if (parking.LoadData(file.getAbsolutePath())) {
							JOptionPane.showMessageDialog(null, "Loaded");
						} else {
							JOptionPane.showMessageDialog(null, "Load failed",
									"", 0, null);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(),
								"", 0, null);
					}
					contentPane.repaint();
				}
			}

		});

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
