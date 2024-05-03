package oregonTrail.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import oregonTrail.OregonTrail;
import oregonTrail.landmark.River;
import java.awt.Color;

/**
 * Panel class representing options available when located at a river in the Oregon Trail game.
 * This panel displays buttons for various actions such as opening the map, changing the rate of travel,
 * changing rations, attempting to cross the river, caulking the wagon, and waiting a day.
 * It also includes action listeners for each button to handle the corresponding actions.
 * 
 * @author Ray Otto
 * @date 2024-14-04
 * @filename RiverPanel.java
 */
public class RiverPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private OregonTrail oregonTrail;
    private String riverName;
    private ImageIcon riverImageIcon;
    
    /**
     * Constructs a new RiverPanel with the specified OregonTrail instance and river image icon.
     * 
     * @param pOregonTrail The OregonTrail instance associated with the game.
     * @param river The river object used for this panel.
     */
    public RiverPanel(OregonTrail pOregonTrail, River river) {
        this.oregonTrail = pOregonTrail;
        this.riverName = river.getName();
        
        // Scale up image
        ImageIcon originalIcon = river.getPicture();
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        this.riverImageIcon = new ImageIcon(scaledImage);
        
        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("WELCOME TO THE " + riverName);
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(Color.BLACK);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Impact", Font.BOLD, 30));
        add(welcomeLabel, BorderLayout.NORTH);

        // Create image label and add it to the top half
        JLabel imageLabel = new JLabel(riverImageIcon);
        add(imageLabel, BorderLayout.CENTER);

        // Create panel for buttons on the bottom half
        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        // Create buttons
        JButton openMapButton = new JButton("Open Map");
        openMapButton.setBackground(Color.BLACK);
        openMapButton.setForeground(Color.WHITE);
        openMapButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton viewRiverDataButton = new JButton("River Stats");
        viewRiverDataButton.setBackground(Color.BLACK);
        viewRiverDataButton.setForeground(Color.WHITE);
        viewRiverDataButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton changeRateButton = new JButton("Change Rate of Travel");
        changeRateButton.setBackground(Color.BLACK);
        changeRateButton.setForeground(Color.WHITE);
        changeRateButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton changeRationsButton = new JButton("Change Rations");
        changeRationsButton.setBackground(Color.BLACK);
        changeRationsButton.setForeground(Color.WHITE);
        changeRationsButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton attemptToCrossButton = new JButton("Attempt to Cross");
        attemptToCrossButton.setBackground(Color.BLACK);
        attemptToCrossButton.setForeground(Color.WHITE);
        attemptToCrossButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton caulkWagonButton = new JButton("Caulk the Wagon");
        caulkWagonButton.setBackground(Color.BLACK);
        caulkWagonButton.setForeground(Color.WHITE);
        caulkWagonButton.setFont(new Font("Impact", Font.PLAIN, 20));
        JButton waitButton = new JButton("Wait");
        waitButton.setBackground(Color.BLACK);
        waitButton.setForeground(Color.WHITE);
        waitButton.setFont(new Font("Impact", Font.PLAIN, 20));

        // Add buttons to button panel
        buttonPanel.add(openMapButton);
        buttonPanel.add(viewRiverDataButton);
        buttonPanel.add(changeRateButton);
        buttonPanel.add(changeRationsButton);
        buttonPanel.add(attemptToCrossButton);
        buttonPanel.add(caulkWagonButton);
        buttonPanel.add(waitButton);

        // Add action listeners to buttons
        openMapButton.addActionListener(e -> oregonTrail.openMap(riverName));
        
        viewRiverDataButton.addActionListener(e -> river.viewRiverData(this));

        changeRateButton.addActionListener(e -> oregonTrail.WAGON.travelSpeedDialog(this));

        changeRationsButton.addActionListener(e -> oregonTrail.WAGON.foodConsumptionDialog(this));

        attemptToCrossButton.addActionListener(e -> {
            river.attemptToCross(this);
            oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            // Display dialogue box with message
            JOptionPane.showMessageDialog(this, "There’s plenty of water and grass for the horses. We should catch some fish in the meantime. +3 fish");
        });
      
        attemptToCrossButton.addActionListener(e -> {
            river.attemptToCross(this);
            oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
            // Display dialogue box with message
            JOptionPane.showMessageDialog(this, "There’s plenty of water and grass for the horses. We should catch some fish in the meantime. +3 fish");
        });
      
        caulkWagonButton.addActionListener(e -> {
            river.caulkWagon(this);
            oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
        });

        waitButton.addActionListener(e -> {
            // Move the player back 12 miles
            oregonTrail.getTravelState().moveBack(12);
            // Update the display with the new distance traveled
            oregonTrail.getTravelState().getMilesTraveled();
            // Update the next landmark based on the new position
            oregonTrail.getTravelState().updateNextLandmark();
            oregonTrail.openPanel(oregonTrail.TRAVEL_PANEL);
        });
    } 
}