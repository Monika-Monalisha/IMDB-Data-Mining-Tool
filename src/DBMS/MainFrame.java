package DBMS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;



public class MainFrame extends JFrame{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private getD g= new getD();
	GetChoice l= new GetChoice();
	private ArrayList<JCheckBox> genreCheckBoxes= new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox> countryCheckboxes = new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox> tagsCheckboxes = new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox> movieresultCheckboxes = new ArrayList<JCheckBox>();
	private JTextField tfFromyear;
	private JTextField tfToyear;
	//private JDatePickerImpl datePicker, datePicker1;
	private JPanel countriesPanel,genrePanel, tagsPanel ;
	private JScrollPane countriesScrollPane, genreScrollPane,  tagsscrollPane;
	private JComboBox<String> cBattr, cBactor1, cBactor2, cBactor3,cBactor4, cBdirector,cBtagweight;
	JButton btnconfirmyear;
	private JTextField tFactor1;
	private JTextField tFactor2;
	private JTextField tFactor3;
	private JTextField tFactor4;
	private JTextField tFdirector;
	private JPanel pltagweight;
	private JLabel lbtagweight;
	private JLabel lbtagvalue,useridlable;
	private JTextField tFtagvalue;
	private JLabel lbmovieresult;
	private JPanel movieresultpanel;
	private JPanel mainpanel, useridPanel;
	private JLabel lbuserresult;
	private JScrollPane movieresultsscrollPane;
	private JButton btnConfirmCast,btnConfirmdirector,Buttonexecutemovie,Buttonuserquery;
	private JScrollPane UserresultscrollPane;
	JTextArea resultArea;
	private JScrollBar scrollBaruserids;
	
	
	//private int toYear=1990, fromYear=2000;
	//public JFrame frame;
	
//	class Tags{
//		int tagId;
//		String tagValue;
//		Tags(int tagid, String tagvalue){
//			this.tagId= tagid;
//			this.tagValue=tagvalue;
//		}
//	}
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainFrame frame = new MainFrame();
						frame.setVisible(true);
						frame.setSize(1000, 750);
					} catch (Exception e) {
						e.printStackTrace();
					}
					}
				});
			}

public MainFrame() {
	
			getContentPane().setBackground(new Color(51, 102, 204));
			
			 mainpanel = new JPanel();
			mainpanel.setBackground(new Color(0, 0, 102));
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(mainpanel, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(mainpanel, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE))
			);
			mainpanel.setLayout(null);
			
			JLabel lbmovieattr = new JLabel("Movie Attributes");
			lbmovieattr.setBounds(6, 0, 738, 33);
			lbmovieattr.setFont(new Font("Lucida Grande", Font.BOLD, 14));
			lbmovieattr.setBackground(SystemColor.textHighlight);
			lbmovieattr.setHorizontalAlignment(SwingConstants.CENTER);
			lbmovieattr.setOpaque(true);
			mainpanel.add(lbmovieattr);
			
			JLabel lbgenre = new JLabel("Genre");
			lbgenre.setBounds(6, 33, 202, 27);
			lbgenre.setBackground(SystemColor.windowBorder);
			lbgenre.setOpaque(true);
			lbgenre.setHorizontalAlignment(SwingConstants.CENTER);
			mainpanel.add(lbgenre);
			
			JLabel lbcountry = new JLabel("Country");
			lbcountry.setBounds(209, 33, 190, 27);
			lbcountry.setBackground(SystemColor.windowBorder);
			lbcountry.setOpaque(true);
			lbcountry.setHorizontalAlignment(SwingConstants.CENTER);
			mainpanel.add(lbcountry);
	
// genre GUI including checkboxes
		 genrePanel = new JPanel();
		
		genrePanel.setLayout(new GridLayout(20,0,0,0));
	
		ArrayList<String> genreGet= g.getGenre();
		
		int i=0;
	
		for(String c: genreGet){
	
				JCheckBox chckbxNewCheckBox = new JCheckBox(c);
				
				chckbxNewCheckBox.setBounds(19, 20+i, 129, 23);
				chckbxNewCheckBox.addActionListener(l);
				genrePanel.add(chckbxNewCheckBox);
				
				genreCheckBoxes.add(chckbxNewCheckBox);
				
				i=i+25;}
		
		 genreScrollPane = new JScrollPane();
		 genreScrollPane.setBounds(6, 60, 202, 270);
		genreScrollPane.setViewportView(genrePanel);
		genreScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainpanel.add(genreScrollPane);
		
		// country panel GUI with country checkboxes
		countriesPanel = new JPanel();
		countriesPanel.setBounds(209, 60, 174, 268);
		//countryCheckboxes.get(1).setSelected(true);
		countriesScrollPane = new JScrollPane();
		countriesScrollPane.setBounds(209, 60, 190, 268);
		countriesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		countriesScrollPane.setViewportView(countriesPanel);
		countriesPanel.setLayout(new GridLayout(2,0,0,0));
	    mainpanel.add(countriesScrollPane);
	
		
		
		JLabel lbcast = new JLabel("Cast");
		lbcast.setBounds(397, 33, 175, 27);
		lbcast.setBackground(new Color(154, 154, 154));
		lbcast.setOpaque(true);
		lbcast.setHorizontalAlignment(SwingConstants.CENTER);
		mainpanel.add(lbcast);
	
		//  Movie year panel 
		JPanel panelmovieyear = new JPanel();
		panelmovieyear.setBounds(6, 328, 202, 99);
		mainpanel.add(panelmovieyear);
		panelmovieyear.setLayout(null);
		
		JLabel lbmovieyear = new JLabel("Movie Year");
		lbmovieyear.setBackground(SystemColor.windowBorder);
		lbmovieyear.setHorizontalAlignment(SwingConstants.CENTER);
		lbmovieyear.setBounds(0, 0, 208, 24);
		lbmovieyear.setOpaque(true);
		panelmovieyear.add(lbmovieyear);
		
		JLabel lbfrommy = new JLabel("From");
		lbfrommy.setHorizontalAlignment(SwingConstants.CENTER);
		lbfrommy.setBounds(0, 28, 41, 16);
		panelmovieyear.add(lbfrommy);
		
		JLabel lbtomy = new JLabel("To");
		lbtomy.setHorizontalAlignment(SwingConstants.CENTER);
		lbtomy.setBounds(0, 64, 30, 16);
		panelmovieyear.add(lbtomy);
		
		tfToyear = new JTextField();
		tfToyear.setText("2000");
		tfToyear.setBounds(42, 69, 80, 24);
		panelmovieyear.add(tfToyear);
		tfToyear.setColumns(10);
		
		tfFromyear = new JTextField();
		tfFromyear.setText("1988");
		tfFromyear.setBounds(42, 31, 80, 26);
		panelmovieyear.add(tfFromyear);
		tfFromyear.setColumns(10);
		
		btnconfirmyear = new JButton("confirm");
		btnconfirmyear.setBounds(122, 45, 80, 24);
		panelmovieyear.add(btnconfirmyear);
		btnconfirmyear.addActionListener(l);
			
		//  attribute search panel GUI	
		JPanel plattribute = new JPanel();
		plattribute.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		plattribute.setBounds(209, 328, 190, 99);
		mainpanel.add(plattribute);
		plattribute.setLayout(null);
		
		JLabel lbsearchfor = new JLabel("Search For");
		lbsearchfor.setBackground(SystemColor.windowBorder);
		lbsearchfor.setHorizontalAlignment(SwingConstants.CENTER);
		lbsearchfor.setBounds(6, 6, 178, 24);
		lbsearchfor.setOpaque(true);
		plattribute.add(lbsearchfor);
		
		 cBattr = new JComboBox<String>();
		cBattr.setModel(new DefaultComboBoxModel<String>(new String[] {"AND", "OR"}));
		cBattr.setBounds(36, 50, 97, 27);
		cBattr.addActionListener(l);
		plattribute.add(cBattr);
		
		JPanel plcast = new JPanel();
		plcast.setBounds(397, 60, 175, 367);
		mainpanel.add(plcast);
		plcast.setLayout(null);
		
		JLabel lbactor = new JLabel("Actror/Actress");
		lbactor.setHorizontalAlignment(SwingConstants.CENTER);
		lbactor.setBackground(SystemColor.windowBorder);
		lbactor.setBounds(6, 6, 155, 35);
		plcast.add(lbactor);
		lbactor.setOpaque(true);
		
		tFactor1 = new JTextField();
		tFactor1.setBounds(6, 53, 130, 33);
		plcast.add(tFactor1);
		tFactor1.setColumns(10);
		
		tFactor2 = new JTextField();
		tFactor2.setBounds(6, 98, 130, 33);
		plcast.add(tFactor2);
		tFactor2.setColumns(10);
		
		tFactor3 = new JTextField();
		tFactor3.setBounds(6, 143, 130, 33);
		plcast.add(tFactor3);
		tFactor3.setColumns(10);
		
		 tFactor4 = new JTextField();
		tFactor4.setBounds(6, 188, 130, 33);
		plcast.add(tFactor4);
		tFactor4.setColumns(10);
		
		tFdirector = new JTextField();
		tFdirector.setBounds(6, 295, 130, 36);
		plcast.add(tFdirector);
		tFdirector.setColumns(10);
		
		JLabel lbdirector = new JLabel("Director");
		lbdirector.setBackground(SystemColor.windowBorder);
		lbdirector.setHorizontalAlignment(SwingConstants.CENTER);
		lbdirector.setBounds(6, 257, 155, 26);
		lbdirector.setOpaque(true);
		plcast.add(lbdirector);
		
		cBdirector = new JComboBox<String>();
		 cBdirector.setBounds(134, 301, 41, 27);
		 cBdirector.addActionListener(l);
		 plcast.add(cBdirector);
		
		 cBactor1 = new JComboBox<String>();
//		 String actorNames[]=g.getActor(country);
//		 cBactor1.setModel(new DefaultComboBoxModel<String>(actorNames));
		 cBactor1.setBounds(134, 53, 41, 27);
		 cBactor1.addActionListener(l);
		 plcast.add(cBactor1);
		 
		 cBactor2 = new JComboBox<String>();
//		 cBactor2.setModel(new DefaultComboBoxModel<String>(actorNames));
		 cBactor2.setBounds(134, 98, 41, 27);
		 cBactor2.addActionListener(l);
		 plcast.add(cBactor2);
		 
		 cBactor3 = new JComboBox<String>();
//		 cBactor3.setModel(new DefaultComboBoxModel<String>(actorNames));
		 cBactor3.setBounds(134, 147, 41, 27);
		 cBactor3.addActionListener(l);
		 plcast.add(cBactor3);

		 cBactor4 = new JComboBox<String>();
//		 cBactor4.setModel(new DefaultComboBoxModel<String>(actorNames));
		 cBactor4.setBounds(134, 188, 41, 27);
		 cBactor4.addActionListener(l);
		 plcast.add(cBactor4);
		 
		 btnConfirmCast = new JButton("Confirm");
		 btnConfirmCast.setBounds(16, 227, 117, 29);
		 plcast.add(btnConfirmCast);
		 
		 btnConfirmdirector = new JButton("Confirm");
		 btnConfirmdirector.addActionListener(l);
		 btnConfirmdirector.setBounds(29, 332, 117, 29);
		 plcast.add(btnConfirmdirector);
		 btnConfirmCast.addActionListener(l);
		 
		 
		 JLabel lbtag = new JLabel("Tag ids and Values");
		 lbtag.setBounds(572, 33, 180, 27);
		 lbtag.setBackground(SystemColor.windowBorder);
		 lbtag.setHorizontalAlignment(SwingConstants.CENTER);
		 lbtag.setOpaque(true);
		 mainpanel.add(lbtag);
		 
		  tagsPanel = new JPanel();
		 tagsPanel.setLayout(new GridLayout(50,0,0,0));
		 tagsPanel.setBounds(572, 66, 150, 264);
		 
		 
		 
		 tagsscrollPane = new JScrollPane();
		 tagsscrollPane.setBounds(572, 60, 180, 270);
		 tagsscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 tagsscrollPane.setViewportView(tagsPanel);
		 mainpanel.add(tagsscrollPane);
		 
		 pltagweight = new JPanel();
		 pltagweight.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		 pltagweight.setBounds(572, 328, 180, 99);
		 mainpanel.add(pltagweight);
		 pltagweight.setLayout(null);
		 
		 lbtagweight = new JLabel("Tag weight");
		 lbtagweight.setBackground(SystemColor.windowBorder);
		 lbtagweight.setHorizontalAlignment(SwingConstants.CENTER);
		 lbtagweight.setBounds(6, 6, 82, 27);
		 lbtagweight.setOpaque(true);
		 pltagweight.add(lbtagweight);
		 
		  cBtagweight = new JComboBox<String>();
		  cBtagweight.setModel(new DefaultComboBoxModel<String>(new String[] {"=", "<",">"}));
		 cBtagweight.setBounds(92, 7, 74, 27);
		 pltagweight.add(cBtagweight);
		 
		 lbtagvalue = new JLabel("value");
		 lbtagvalue.setBackground(SystemColor.windowBorder);
		 lbtagvalue.setHorizontalAlignment(SwingConstants.CENTER);
		 lbtagvalue.setBounds(6, 50, 82, 27);
		 lbtagvalue.setOpaque(true);
		 pltagweight.add(lbtagvalue);
		 
		 tFtagvalue = new JTextField();
		 tFtagvalue.setHorizontalAlignment(SwingConstants.CENTER);
		 tFtagvalue.setBounds(92, 50, 74, 26);
		 pltagweight.add(tFtagvalue);
		 tFtagvalue.setColumns(10);
		 
		 lbmovieresult = new JLabel("Movie Result");
		 lbmovieresult.setBackground(SystemColor.textHighlight);
		 lbmovieresult.setHorizontalAlignment(SwingConstants.CENTER);
		 lbmovieresult.setBounds(744, 0, 232, 33);
		 lbmovieresult.setOpaque(true);
		 mainpanel.add(lbmovieresult);
		 
		 JPanel querypanel = new JPanel();
		 querypanel.setBackground(SystemColor.window);
		 querypanel.setBounds(6, 444, 723, 178);
		 mainpanel.add(querypanel);
		 querypanel.setLayout(null);
		 
		 JLabel lblNewLabel = new JLabel("QUERY");
		 lblNewLabel.setBackground(SystemColor.textHighlight);
		 lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 lblNewLabel.setBounds(6, 6, 711, 23);
		 querypanel.add(lblNewLabel);
		 lblNewLabel.setOpaque(true);
		 
		  resultArea = new JTextArea();
		  resultArea.setBounds(0, 25, 650, 100);
		  resultArea.setLayout(new GridLayout(1,0,0,0));
		  
		 JScrollPane queryscrollPane = new JScrollPane();
		 queryscrollPane.setBounds(8, 31, 711, 131);
		 queryscrollPane.setViewportView(resultArea);
		 querypanel.add(queryscrollPane);
		 
		  Buttonexecutemovie = new JButton("Execute movie Query");
		 Buttonexecutemovie.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		 Buttonexecutemovie.setBackground(new Color(0, 204, 255));
		 Buttonexecutemovie.setBounds(63, 645, 162, 50);
		 Buttonexecutemovie.setOpaque(true);
		 Buttonexecutemovie.addActionListener(l);
		 mainpanel.add(Buttonexecutemovie);
		 
		 Buttonuserquery = new JButton("Execute User Query");
		 Buttonuserquery.setBackground(new Color(0, 204, 255));
		 Buttonuserquery.setBounds(297, 645, 162, 50);
		 Buttonuserquery.addActionListener(l);
		 Buttonuserquery.setOpaque(true);
		 mainpanel.add(Buttonuserquery);
		 
		 movieresultpanel= new JPanel();
		 movieresultpanel.setBounds(750, 33, 190, 388);	
		 movieresultsscrollPane = new JScrollPane();
		 movieresultsscrollPane.setBounds(754, 33, 222, 394);
		 movieresultsscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 movieresultsscrollPane.setViewportView(movieresultpanel);
		 mainpanel.add(movieresultsscrollPane);
		 
		 lbuserresult = new JLabel("New label");
		 lbuserresult.setBounds(754, 436, 222, 31);
		 mainpanel.add(lbuserresult);
		 lbuserresult.setBackground(SystemColor.textHighlight);
		 lbuserresult.setHorizontalAlignment(SwingConstants.CENTER);
		 lbuserresult.setOpaque(true);
		 
//		 userresultArea = new JTextArea(); 
//		 userresultArea.setBounds(763, 456, 200, 222);
		 useridlable = new JLabel();
		 useridlable.setBounds(763, 456, 200, 222);
		 UserresultscrollPane = new JScrollPane();
		 UserresultscrollPane.setBounds(754, 468, 222, 234);
		 UserresultscrollPane.setViewportView(useridlable);
		 mainpanel.add(UserresultscrollPane);

}
//lblNewLabel.setOpaque(true);
	class GetChoice implements ActionListener
	{
		int fromyear, toyear;
	 	String directorname="";
	 	ArrayList<String> actorlist= new ArrayList<>();
		ArrayList<String> genre= new ArrayList<String>();
		ArrayList<Integer> tagids = new ArrayList<Integer>();
		ArrayList<String> movieId= new ArrayList<String>();
	 	String attrS;
	 	String sign;
	 	int tagweight;
	 	
		public void displayTags(){
			tagids.clear();
			ArrayList<Tags> tags= g.getTags();
			System.out.println(tags.size());
			int i=0;
			tagsPanel.removeAll();
			tagsCheckboxes.clear();
			for(Tags c: tags){
				JCheckBox chckbxNewCheckBox = new JCheckBox();
				chckbxNewCheckBox.setBounds(30, 30+i, 129, 23);
				chckbxNewCheckBox.addActionListener(l);
				chckbxNewCheckBox.setText(c.tagId+" "+c.tagValue);
				tagsPanel.add(chckbxNewCheckBox);
				tagsCheckboxes.add(chckbxNewCheckBox);
				i=i+25;
			}     
		}
		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {

			if(!tfFromyear.getText().equals("")){
				
				fromyear=Integer.parseInt(tfFromyear.getText());
//					System.out.println("fromyear="+fromyear);
			}
			else{
				fromyear=1990;
			}
			if(!tfToyear.getText().equals("")){
				toyear=Integer.parseInt(tfToyear.getText());
//					System.out.println("toyear="+toyear);
			}
			else
			{
				toyear= 2007;
			}
			 
			// TODO Auto-generated method stub
			//action on genre click
				if(genreCheckBoxes.contains(e.getSource())  || e.getSource()== cBattr || e.getSource()==btnconfirmyear){
					 attrS=(String) cBattr.getSelectedItem();
					System.out.println("genre clicked");
					
					genre.clear();
					 for (JCheckBox checkBox : genreCheckBoxes) {
					        if (checkBox.isSelected()) {
					        	genre.add(checkBox.getText());}
					  }
					if(genre.isEmpty()){
						JOptionPane.showMessageDialog(mainpanel, "Please select a genre value");
							tagsPanel.removeAll();
							countriesPanel.removeAll();
							 cBactor1.setModel(new DefaultComboBoxModel<String>());
							 cBactor2.setModel(new DefaultComboBoxModel<String>());
							 cBactor3.setModel(new DefaultComboBoxModel<String>());
							 cBactor4.setModel(new DefaultComboBoxModel<String>());
						     tFactor1.setText("");
						     tFactor2.setText("");
						     tFactor3.setText("");
						     tFactor4.setText("");
						     cBdirector.setModel(new DefaultComboBoxModel<String>()); 
							tFdirector.setText(""); 
							tFtagvalue.getText().equals("");
							resultArea.setText("");
							movieresultpanel.removeAll();
							useridlable.removeAll();
					}
					else{
//				        for(String g: genre){
//				        	System.out.println(g);
//				        }
//				       //clearing countries
				        countriesPanel.removeAll();
				        g.genreClicked(genre,attrS, fromyear, toyear);
						ArrayList<String> countries= g.getCountries();
						int rows=countries.size();
						if(rows==0)
							{rows=2;}
						countriesPanel.setLayout(new GridLayout(rows,0,0,0));
						System.out.println(countries.size());
						int i=0;
						countryCheckboxes.clear();
						for(String c: countries){
							JCheckBox chckbxNewCheckBox = new JCheckBox(c);
							chckbxNewCheckBox.setBounds(30, 30+i, 129, 23);
							chckbxNewCheckBox.addActionListener(l);
							countriesPanel.add(chckbxNewCheckBox);
							countryCheckboxes.add(chckbxNewCheckBox);
							i=i+25;
						} 
						
						displayactors();
						displaydirectors();
						displayTags();
						
					
					}
//					movieresultpanel.removeAll();
//					useridlable.removeAll();
					 resultArea.setText(""); 
			}
			if(countryCheckboxes.contains(e.getSource()))
			{
				ArrayList<String> country= new ArrayList<String>();
				country.clear();
				System.out.println("country clicked");
				
				 for (JCheckBox checkBox : countryCheckboxes) {
				        if (checkBox.isSelected()) {
				        	country.add(checkBox.getText());}
				  }
			    for(String c: country){
			    	System.out.println(c);
			    }
			    System.out.println(country);
			    g.countryClicked(country, attrS, fromyear, toyear);
			    displayactors();
			    displaydirectors();
				displayTags();
				movieresultpanel.removeAll();
				useridlable.removeAll();
			}
			
	    	if(e.getSource()==cBdirector ){
				tFdirector.setText((String) cBdirector.getSelectedItem());
	    	}
			if(e.getSource()==cBactor1){
			tFactor1.setText((String) cBactor1.getSelectedItem());
			System.out.println((String) cBactor1.getSelectedItem());
			}
			if(e.getSource()==cBactor2){
			
			tFactor2.setText((String) cBactor2.getSelectedItem());
			}
			if(e.getSource()==cBactor3){
			tFactor3.setText((String) cBactor3.getSelectedItem());
			}
			if(e.getSource()==cBactor4){
				tFactor4.setText((String) cBactor4.getSelectedItem());
			}
			
			 if(e.getSource()== btnConfirmCast)
			 {
				 if(!tFactor1.getText().equals("")){
					 actorlist.add(tFactor1.getText());	
						
					} 
				 if(!tFactor2.getText().equals("")){
					 actorlist.add(tFactor2.getText());	
				 }
				 if(!tFactor3.getText().equals("")){
					 actorlist.add(tFactor3.getText());	
				 }
				 if(!tFactor4.getText().equals("")){
					 actorlist.add(tFactor4.getText());	
				 }
				 g.ActorClicked(actorlist, fromyear, toyear, attrS);
				 displaydirectors();
				 displayTags();
				 movieresultpanel.removeAll();
				useridlable.removeAll();
			 }
			 if(e.getSource()== btnConfirmdirector){
				 if(!tFdirector.getText().equals("")){
					 directorname=tFdirector.getText();	
				 }
				 g.DirectorClicked(directorname, fromyear, toyear);
				 displayTags();
				 movieresultpanel.removeAll();
				useridlable.removeAll();
				 resultArea.setText("");
			 }
			 if(tagsCheckboxes.contains(e.getSource())){
				 System.out.println("Tags clicked");
				 tagids.clear();
				 for (JCheckBox checkBox : tagsCheckboxes) {
				        if (checkBox.isSelected()) {
				        	String selection= checkBox.getText();
				        	String[] s= selection.split(" ");
				        	System.out.println(s[0]);
				        	tagids.add(Integer.valueOf(s[0]));
				  }
				        
				 }
				 
				 
//				 cBtagweight.setModel(new DefaultComboBoxModel<String>());
			 }
			 
				
			 
			 if(e.getSource()==Buttonexecutemovie){
				 System.out.println("execute movie query clicked");
				 int tw;
				 if(!tFtagvalue.getText().equals("")){
					  tw=Integer.valueOf(tFtagvalue.getText());	}
			
				 else
					 {tw=-1;}
				 sign=(String) cBtagweight.getSelectedItem();
				 g.TagsClicked(tagids, sign, tw, fromyear, toyear, attrS);
				// movieresultpanel.removeAll();
				// useridlable.removeAll();
				
				 ArrayList<Results> results= g.getResult();
				 int size= results.size();
				 if(size==0)
					 size=2;
				 movieresultpanel.setLayout(new GridLayout(size,0,0,0));
				 //System.out.println(x);
				 int i=0;
				 movieresultpanel.removeAll();
				 movieresultCheckboxes.clear();
				 for(Results r: results){
					    JCheckBox chckbxNewCheckBox = new JCheckBox();
						chckbxNewCheckBox.setBounds(30, 10+i, 129, 23);
						chckbxNewCheckBox.addActionListener(l);
						chckbxNewCheckBox.setText(r.id+" "+r.title+" "+r.year+" "+r.audrating+" "+r.audnumrating+" "+ r.country+" "+r.genre);
						movieresultpanel.add(chckbxNewCheckBox);
						movieresultCheckboxes.add(chckbxNewCheckBox);
						i=i+25;
				 }
				 String moviequery= g.getmovieQuery();
				 
				 resultArea.setText("");
				 resultArea.setText(moviequery);
				 
				// tFtagvalue.setText("");
			 }
			 if (movieresultCheckboxes.contains(e.getSource())){
				 System.out.println(" movie result clicked");
				 movieId.clear();
				 Set<String> set= new HashSet<>();
				 for (JCheckBox checkBox : movieresultCheckboxes) {
				        if (checkBox.isSelected()) {
				        	String text= checkBox.getText();
				        	String[] splitText= text.split(" ");
				        	set.add(splitText[0]);
				        	System.out.println("movieid ="+splitText[0]);
				        	
				        }
				  }
				 
				 movieId.addAll(set);
				 System.out.println("movieid"+set);
			 }
			 if(e.getSource()==Buttonuserquery){
				 System.out.println("movieid length"+movieId );
				 System.out.println("tagid length"+tagids);
				ArrayList<String> users= g.useridresults(tagids, movieId);  
				 System.out.println(users.size());
				 for(String s: users){
					  String text=useridlable.getText();
					  useridlable.setText(text+ "\n " +s);
				 }
				String userquery= g.getUserquery();
				 resultArea.setText("");
				 System.out.println(userquery);
				 resultArea.setText(userquery);
			 }
	
			 repaint();
			 revalidate(); 
		}
		public void displayactors(){
		     //actorlist.clear();
		     cBactor1.setModel(new DefaultComboBoxModel<String>());
			 cBactor2.setModel(new DefaultComboBoxModel<String>());
			 cBactor3.setModel(new DefaultComboBoxModel<String>());
			 cBactor4.setModel(new DefaultComboBoxModel<String>());
		     tFactor1.setText("");
		     tFactor2.setText("");
		     tFactor3.setText("");
		     tFactor4.setText("");
		     String actorNames[]=g.getActors();
			 cBactor1.setModel(new DefaultComboBoxModel<String>(actorNames));
			 cBactor2.setModel(new DefaultComboBoxModel<String>(actorNames));
			 cBactor3.setModel(new DefaultComboBoxModel<String>(actorNames));
			 cBactor4.setModel(new DefaultComboBoxModel<String>(actorNames));
		}
		public void displaydirectors(){
			cBdirector.setModel(new DefaultComboBoxModel<String>()); 
			tFdirector.setText(""); 
			 String directorNames[]=g.getDirectors();
			 cBdirector.setModel(new DefaultComboBoxModel<String>(directorNames));
		}			
				 
	}
}   
	



