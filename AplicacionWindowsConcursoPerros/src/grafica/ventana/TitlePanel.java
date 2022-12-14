package grafica.ventana;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class TitlePanel  {

	

	public static JPanel getCloseTitlePanel(JTabbedPane tabbedPane, JPanel panel, String title)
	{
			  JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
			  titlePanel.setOpaque(false);
			  JLabel titleLbl = new JLabel(title);
			  titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
			  titlePanel.add(titleLbl);
			  JButton closeButton = new JButton("x");
			  closeButton.setBackground(Color.WHITE);
			  closeButton.setBorder(null);
			  titlePanel.add(closeButton);
			  

			  closeButton.addMouseListener(new MouseAdapter()
			  {
				@Override
				public void mouseEntered(MouseEvent e)
				{
					closeButton.setForeground(Color.RED);
				}
			   @Override
			   public void mouseClicked(MouseEvent e)
			   {
				   if(tabbedPane.getTabCount() > 1)
				   {
					   tabbedPane.remove(panel);
				   }
				   else
				   {
					  tabbedPane.setVisible(false);
					
				   }
			   }
			   @Override
				public void mouseExited(MouseEvent e) {
				  
					int i = tabbedPane.indexOfTabComponent(titlePanel);
						 
					if(tabbedPane.getSelectedIndex() != i)
					{
						tabbedPane.setTabComponentAt(i,getNormalTitlePanel(tabbedPane,title));
						tabbedPane.setBackgroundAt(i, Color.LIGHT_GRAY);
					}
					closeButton.setForeground(Color.BLACK);
			   }
			 
			  });
			  
			  titlePanel.addMouseListener(new MouseAdapter() {
				  @Override
					public void mouseExited(MouseEvent e) {
					  
						int i = tabbedPane.indexOfTabComponent(titlePanel);
						 Component c = SwingUtilities.getDeepestComponentAt(e.getComponent(), e.getX(), e.getY());
						if(tabbedPane.getSelectedIndex() != i && c == null )
						{
							tabbedPane.setTabComponentAt(i,getNormalTitlePanel(tabbedPane,title));
							tabbedPane.setBackgroundAt(i, Color.LIGHT_GRAY);
						}
						
						
					}
				  @Override
					public void mouseClicked(MouseEvent e) {
						int i = tabbedPane.indexOfTabComponent(titlePanel);
						tabbedPane.setSelectedComponent(tabbedPane.getComponentAt(i));
					}
			  });
			  
			  
			  
			  return titlePanel;
	}
	
	public static JLabel getNormalTitlePanel(JTabbedPane tabbedPane, String title) {
		
		JLabel lbltitle = new JLabel(title);
		
		lbltitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				int i = tabbedPane.indexOfTabComponent(lbltitle);
				tabbedPane.setTabComponentAt(i,getCloseTitlePanel(tabbedPane, (JPanel)tabbedPane.getComponentAt(i),title));
				tabbedPane.setBackgroundAt(i, Color.WHITE);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tabbedPane.indexOfTabComponent(lbltitle);
				tabbedPane.setSelectedComponent(tabbedPane.getComponentAt(i));
			}
			

		});
		
		return lbltitle;
	}
	
}
