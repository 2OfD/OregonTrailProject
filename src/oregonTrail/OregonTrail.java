package oregonTrail;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import oregonTrail.panel.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;

public class OregonTrail {

	private JFrame frame;
	private StartupPanel startupPanel;
	private TravelPanel loadWagonPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OregonTrail window = new OregonTrail();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OregonTrail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.GRAY);
		frame.setTitle("Oregon Trail");
		frame.setBounds(100, 100, 839, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		// Always start program maximized!
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// TODO: Separate image logic into its own class with images stored
		// TODO (optional): Make automatic parsing of image files in directory and 
		//                  automatically create JLabels from them. Perhaps we could
		//                  also have a CSV file which contains the appropriate scaling for each image.
		// Add Chimney Rock Image
		ImageIcon chimneyRockImage = new ImageIcon("src/images/chimney_rock_1.jpg");
		// Resize image to fit into frame
        Image image = chimneyRockImage.getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(image);
        
		JLabel imageLabel = new JLabel(scaledImage);
		
		// Show logo and team name
		startupPanel = new StartupPanel();
		frame.getContentPane().add(startupPanel, BorderLayout.CENTER);
		
		Timer startupTimer = new Timer(StartupPanel.STARTUP_TIME, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// After startup screen is finished, display first panel
				frame.getContentPane().remove(startupPanel);
				frame.getContentPane().add(loadWagonPanel = new TravelPanel());
				// The image must be added separate from the panel because LoadWagonPanel uses
				// a Grid Layout, and the image won't fit nicely
				// Update frame
				frame.setVisible(true);
				
			}
		});
		startupTimer.setRepeats(false);
		startupTimer.start();
	}
}
