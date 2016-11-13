import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*; 

public class Gui {
	
	private LineCenter lc = LineCenter.getInstance();
	private PriceCalculator pc = PriceCalculator.getInstance();
	private Station start;
	private Station dest;
	
	public void display() throws Exception
	{
		 //Create and set up the window.
        JFrame frame = new JFrame("MTR fare calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Size and display the window.
        frame.setSize(1500,689);
        frame.setResizable(false);
        
        BufferedImage routeMap = ImageIO.read(new File("image/route.jpg"));
        JLabel routeMapLabel = new JLabel(new ImageIcon(routeMap));
        routeMapLabel.setBounds(0, 0, 950, 689);
        
        JLabel label1 = new JLabel("Start station");
        label1.setBounds(1000, 0, 100, 100);
        frame.add(label1);
        
        JLabel label2 = new JLabel("End station");
        label2.setBounds(1000, 100, 100, 100);
        frame.add(label2);
        
        JLabel label3 = new JLabel("Quantity");
        label3.setBounds(1000, 300, 100, 100);
        frame.add(label3);
        
        JLabel label4 = new JLabel("Payment method");
        label4.setBounds(1000, 200, 100, 100);
        frame.add(label4);
        
        JLabel label5 = new JLabel("Age group");
        label5.setBounds(1000, 400, 100, 100);
        frame.add(label5);
        
        
        
        JTextArea area1 = new JTextArea();
        area1.setBounds(1125,30,200,50);
        area1.setFocusable(false);
        frame.add(area1);
        
        JTextArea area2 = new JTextArea();
        area2.setBounds(1125,125,200,50);
        area2.setFocusable(false);
        frame.add(area2);
        
        JButton jb = new JButton("Calculate distance");
        jb.setBounds(1125,500,200,50);
        frame.add(jb);
        
        String[] quantity ={"1","2","3","4","5","6","7","8","9","10"}; 
        JComboBox jc = new JComboBox(quantity);
        jc.setBounds(1125,320,200,50);
        jc.setEnabled(false);
        frame.add(jc);
        
        
        String[] paymentMethod = {"Octopus","Ticket"};
        JComboBox jc2 = new JComboBox(paymentMethod);
        jc2.setBounds(1125,220,200,50);
        frame.add(jc2);
        
        
        String[] ageGroup ={"Child","Student","Adult","Elderly"}; 
        JComboBox jc3 = new JComboBox(ageGroup);
        jc3.setBounds(1125,420,200,50);
        frame.add(jc3);
        
        jc2.addActionListener(new ActionListener()
        {
        	@Override
			public void actionPerformed(ActionEvent e)
        	{
        		if(jc2.getSelectedIndex() == 1)
        		{
        			jc.setEnabled(true);
        		}
        		
        		else
        			jc.setEnabled(false);
        	}
        });

        
        jb.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) {
				double distance = pc.stationDistance(start,dest,null);
				int quantity = fee.setQuantity(jc.getSelectedIndex()+1);
				int paymentMethod = fee.setMethod(jc2.getSelectedIndex()+1);
				int ageGroup = fee.setAgeGroup(jc3.getSelectedIndex()+1);
				int startCode = fee.setStartCode(start.getCode());
				int destCode = fee.setDestCode(dest.getCode());
				JOptionPane.showMessageDialog(null, "The distance: " + distance + "\nThe price is:" + fee.finalCalculation());
				
			}
        });
        
        
        frame.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) 
            {
            	if(SwingUtilities.isLeftMouseButton(me) && me.getX() <= 950 && me.getY() <= 689)
            	{
            		Object[] options = {"Start station","Destinition station"};
            		
            		int n = JOptionPane.showOptionDialog(frame,
            			    "Please choose for start or destinition",
            			    "",
            			    JOptionPane.YES_NO_CANCEL_OPTION,
            			    JOptionPane.QUESTION_MESSAGE,
            			    null,
            			    options,
            			    options[0]);
            		
            		if(n == 0)
            		{
                		start = lc.getStationByPos(me.getX(), me.getY());
                		
                		if(start != null)
                		{
                			area1.setText(start.getStation());
                		}
            		}
            		
            		else if(n == 1)
            		{
                		dest = lc.getStationByPos(me.getX(), me.getY());
                		
                		if(dest != null)
                		{
                			area2.setText(dest.getStation());
                		}
            		}
            		
            		
            	}          	
             } 
           	});

        frame.add(routeMapLabel);
        frame.setVisible(true);
        

	}
	
    public static void addComponentsToPane(Container pane) throws Exception{
        pane.setLayout(null);
        
        BufferedImage routeMap = ImageIO.read(new File("image/route.jpg"));
        JLabel routeMapLabel = new JLabel(new ImageIcon(routeMap));
        pane.add(routeMapLabel);

    }

}
