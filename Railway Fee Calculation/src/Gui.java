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
        frame.setSize(1500,726);
        frame.setResizable(false);
        
        BufferedImage routeMap = ImageIO.read(new File("image/route.jpg"));
        JLabel routeMapLabel = new JLabel(new ImageIcon(routeMap));
        routeMapLabel.setBounds(0, 0, 1024, 726);
        
        JLabel label1 = new JLabel("Start station");
        label1.setBounds(1050, 0, 100, 100);
        frame.add(label1);
        
        JLabel label2 = new JLabel("End station");
        label2.setBounds(1050, 100, 100, 100);
        frame.add(label2);
        
        JLabel label3 = new JLabel("Quantity");
        label3.setBounds(1050, 200, 100, 100);
        frame.add(label3);
        
        JLabel label4 = new JLabel("Payment method");
        label4.setBounds(1050, 300, 100, 100);
        frame.add(label4);
        
        JLabel label5 = new JLabel("Age group");
        label5.setBounds(1050, 400, 100, 100);
        frame.add(label5);
        
        
        
        JTextArea area1 = new JTextArea();
        area1.setBounds(1200,30,200,50);
        area1.setFocusable(false);
        frame.add(area1);
        
        JTextArea area2 = new JTextArea();
        area2.setBounds(1200,125,200,50);
        area2.setFocusable(false);
        frame.add(area2);
        
        JButton jb = new JButton("Calculate distance");
        jb.setBounds(1150,500,200,50);
        frame.add(jb);
        
        String[] quantity ={"1","2","3","4","5","6","7","8","9","10"}; 
        JComboBox jc = new JComboBox(quantity);
        jc.setBounds(1200,220,200,50);
        frame.add(jc);
        
        
        String[] paymentMethod = {"Octopus","Ticket"};
        JComboBox jc2 = new JComboBox(paymentMethod);
        jc2.setBounds(1200,320,200,50);
        frame.add(jc2);
        
        
        String[] ageGroup ={"Child","Student","Adult","Elderly"}; 
        JComboBox jc3 = new JComboBox(ageGroup);
        jc3.setBounds(1200,420,200,50);
        frame.add(jc3);
        
        jb.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent e) {
				double distance = pc.stationDistance(start,dest,null);
				int quantity = jc.getSelectedIndex()+1;
				int paymentMethod = jc2.getSelectedIndex()+1;
				int ageGroup = jc3.getSelectedIndex()+1;
				int startCode = start.getCode();
				int destCode = dest.getCode();
				JOptionPane.showMessageDialog(null, "The distance: " + distance + "\nThe price is:" + pc.finalCalculation(ageGroup, quantity, paymentMethod, start, dest));
				
			}
        });
        
        
        frame.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) 
            {
            	if(SwingUtilities.isLeftMouseButton(me))
            	{
//            		JOptionPane.showMessageDialog(null, "The mouse getX:" + me.getX());
//            		System.out.print(me.getY() + ",");
            		start = lc.getStationByPos(me.getX(), me.getY());
            		
            		if(start != null)
            		{
            			area1.setText(start.getStation());
            		}
            	}
            	
            	else
            	{
            		dest = lc.getStationByPos(me.getX(), me.getY());
            		
            		if(dest != null)
            		{
            			area2.setText(dest.getStation());
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
