
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;


public class YelpApplication {
	JFrame frame=new JFrame();
	JPanel mainPanel=new JPanel();
	JPanel container=new JPanel();
	JPanel ReviewPanel=new JPanel();
	JPanel ReviewListPanel=new JPanel();
	JPanel StartPanel=new JPanel();
	JPanel StatePanel=new JPanel();
	JPanel Holder=new JPanel();
	JPanel MaincatPanel=new JPanel();
	JPanel SubcatPanel=new JPanel();
	JPanel AttributePanel=new JPanel();
	JPanel ResultPanel=new JPanel();
	JPanel EndPanel=new JPanel();
	JFrame frame2=new JFrame();
	
	JButton StartButton=new JButton();
	JButton SearchButton=new JButton();
	JButton CloseButton=new JButton();
	JButton RCloseButton=new JButton();
	JLabel WeekLabel=new JLabel("Select Week:");
	JLabel Start=new JLabel("From:");
	JLabel Close=new JLabel("To:");
	JLabel Attr=new JLabel("Attribute Select:");
	JLabel statelabel=new JLabel("Select State:");
	JLabel citylabel=new JLabel("Select City");
	JComboBox WeekCombo=new JComboBox();
	JComboBox Starttime=new JComboBox();
	JComboBox Closetime=new JComboBox();
	JComboBox AttrSelect=new JComboBox();
	JComboBox stateCombo=new JComboBox();
	JComboBox cityCombo=new JComboBox();
	Object[][] businessrows=new String[50][6];
	Object[][] reviewrows=new String[50][5];
	Object[] columns={"BID","BusinessName", "City", "State", "Stars", "Reviews"};
	Object[] reviewcolumns={"Review Date","Stars","Review","User Name","Useful Votes"};
	
	TableModel myModel;
	JTable businesstable=new JTable(businessrows,columns);
	JTable reviewtable=new JTable(reviewrows,reviewcolumns);
	
	JScrollPane MaincatScroll=new JScrollPane(MaincatPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JScrollPane SubcatScroll=new JScrollPane(SubcatPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JScrollPane AttributeScroll=new JScrollPane(AttributePanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JScrollPane ResultSroll=new JScrollPane(ResultPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JScrollPane ReviewScroll=new JScrollPane(reviewtable,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JScrollPane businessscroll=new JScrollPane(businesstable,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	
	ItemListener itemlistener1;
	ItemListener itemlistener2;
	ItemListener itemlistener3;
	ItemListener itemlistener4;
	ItemListener itemlistener5;
	
	
	static final String JDBC_DRIVER="oracle.jdbc.driver.OracleDriver";
	static final String DB_URL="jdbc:oracle:thin:@localhost:1521:ORCL";
	static final String Username="scott";
	static final String pass="tiger";
	Connection conn=null;
	Statement stmt=null;
	
	Set<String> Allattrlist=new HashSet<String>();
	Set<String> Amainhlist=new HashSet<String>();
	Set<String> Asubhlist=new HashSet<String>();
	Set<String> Attributeshlist=new HashSet<String>();
	TreeSet<String> Amainlist=new TreeSet<String>();
	TreeSet<String> Asublist=new TreeSet<String>();
	TreeSet<String> Attributeslist=new TreeSet<String>();
	Set<String> dmlist=new HashSet<String>();
	Set<String> dslist=new HashSet<String>();
	Set<String> dalist=new HashSet<String>();
	Set<String> Aallbids=new HashSet<String>();
	Set<String> mbids=new HashSet<String>();
	Set<String> sbids=new HashSet<String>();
	Set<String> Anyattrbids=new HashSet<String>();
	Set<String> Allattrbids=new HashSet<String>();
	Set<String> Exceptattrbids=new HashSet<String>();
	Set<String> SearchBids=new HashSet<String>();
	Set<String> stateBids=new HashSet<String>();
	Set<String> cityBids=new HashSet<String>();
	List<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
	List<JCheckBox> checkboxes1 = new ArrayList<JCheckBox>();
	List<JCheckBox> checkboxes2 = new ArrayList<JCheckBox>();

	public void firstStep(){
		
							StartButton.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
							StartButton.setForeground(new java.awt.Color(102, 0, 0));
							StartButton.setText("START");
							StartButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));
							StartButton.setOpaque(false);
							
							SearchButton.setBackground(new java.awt.Color(255, 255, 0));
							SearchButton.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
							SearchButton.setForeground(new java.awt.Color(255, 153, 0));
							SearchButton.setText("SEARCH");
							SearchButton.addActionListener(new java.awt.event.ActionListener() {
							    public void actionPerformed(java.awt.event.ActionEvent evt) {
							    Set<String> search=searchFilter();
							     Object[][] table1=businessTable(search);
							     changeTable(table1);
							    	
							    }
							});
							
							CloseButton.setBackground(new java.awt.Color(255, 255, 0));
							CloseButton.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
							CloseButton.setForeground(new java.awt.Color(255, 153, 0));
							CloseButton.setText("CLOSE");
							CloseButton.addActionListener(new java.awt.event.ActionListener() {
							    public void actionPerformed(java.awt.event.ActionEvent evt) {
							    	
							      System.exit(0);
							    }
							});
							
							RCloseButton.setBackground(new java.awt.Color(255, 255, 0));
							RCloseButton.setFont(new java.awt.Font("Verdana", 3, 12)); // NOI18N
							RCloseButton.setForeground(new java.awt.Color(255, 153, 0));
							RCloseButton.setText("Back");
							RCloseButton.addActionListener(new java.awt.event.ActionListener() {
							    public void actionPerformed(java.awt.event.ActionEvent evt) {
							    	ReviewPanel.removeAll();
							    	Holder.remove(ReviewPanel);
							    	Holder.remove(ReviewScroll);
							    	MaincatScroll.setPreferredSize( new Dimension(200, 400) );
									SubcatScroll.setPreferredSize( new Dimension(200, 400) );
									AttributeScroll.setPreferredSize( new Dimension(200, 400) );
									businesstable.getColumnModel().getColumn(0).setMinWidth(0);
								      businesstable.getColumnModel().getColumn(0).setMaxWidth(0);
								      businesstable.getColumnModel().getColumn(3).setMinWidth(50);
								      businesstable.getColumnModel().getColumn(3).setMaxWidth(50);
								      businesstable.getColumnModel().getColumn(4).setMinWidth(50);
								      businesstable.getColumnModel().getColumn(4).setMaxWidth(50);
								      businesstable.getColumnModel().getColumn(5).setMinWidth(50);
								      businesstable.getColumnModel().getColumn(5).setMaxWidth(50);
									   businessscroll.setPreferredSize( new Dimension(400, 600));
									   StartButton.setVisible(true);;
											  StartPanel.remove(RCloseButton);
											  WeekCombo.setVisible(true);
											  Start.setVisible(true);
											  Close.setVisible(true);
											 Closetime.setVisible(true);
											 AttrSelect.setVisible(true);
											 WeekLabel.setVisible(true);
											  Starttime.setVisible(true);
											  Closetime.setVisible(true);
											  SearchButton.setVisible(true);
											 Attr.setVisible(true);
									
							    	
							    }
							});
							//RCloseButton.addActionListener(undoAction);
							
							WeekCombo.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
							WeekCombo.setForeground(new java.awt.Color(255, 51, 204));
							WeekCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday","Friday","Saturday","Sunday" }));
							//WeekCombo.setEditable(true);
							WeekCombo.setSelectedIndex(-1);
					          
						
							
							
					    
							
							Starttime.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
							Starttime.setForeground(new java.awt.Color(255, 51, 204));
							Starttime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00", "2:00", "3:00", "4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00" }));
							Starttime.setSelectedIndex(-1);
					         
							Closetime.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
							Closetime.setForeground(new java.awt.Color(255, 51, 204));
							Closetime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1:00", "2:00", "3:00", "4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00" }));
							 Closetime.setSelectedIndex(-1);
					         
							AttrSelect.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
							AttrSelect.setForeground(new java.awt.Color(255, 51, 204));
							AttrSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All Selected Attributes", "Any Selected Attributes", "None of Selected", "Skip Attributes" }));
							AttrSelect.setSelectedIndex(0); 
							 
							stateCombo.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
							stateCombo.setForeground(new java.awt.Color(255, 51, 204));
							Set<String> state=States();
							stateCombo.setModel(new javax.swing.DefaultComboBoxModel(state.toArray()));
							stateCombo.setSelectedIndex(-1);
							stateCombo.addItemListener(itemlistener4);
							
							cityCombo.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
							cityCombo.setForeground(new java.awt.Color(255, 51, 204));
							if(stateCombo.getSelectedIndex()!=-1){
								String c=(String)stateCombo.getSelectedItem();
							Set<String> city=city(c);
							cityCombo.setModel(new javax.swing.DefaultComboBoxModel(city.toArray()));
							}
							cityCombo.setSelectedIndex(-1);
							cityCombo.addItemListener(itemlistener5);
							
							WeekLabel.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
							WeekLabel.setForeground(new java.awt.Color(0, 255, 255));
							WeekLabel.setText("Day:");
							
							Start.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
							Start.setForeground(new java.awt.Color(0, 255, 255));
							Start.setText("To:");
							
							Close.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
							Close.setForeground(new java.awt.Color(0, 255, 255));
							Close.setText("From:");
							
							Attr.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
							Attr.setForeground(new java.awt.Color(0, 255, 255));
							Attr.setText("Selection:");
							
							//MaincatScroll.setpreferredSize(new Dimension(20,400));
							
							EndPanel.setBackground(new java.awt.Color(153, 0, 255));
							MaincatPanel.setBackground(new java.awt.Color(255, 153, 255));
							SubcatPanel.setBackground(new java.awt.Color(255, 102, 102));
							AttributePanel.setBackground(new java.awt.Color(255, 204, 102));
							ResultPanel.setBackground(new java.awt.Color(204, 255, 153));
							StartPanel.setBackground(new java.awt.Color(0, 153, 255));
							ReviewPanel.setBackground(new java.awt.Color(255, 153, 255));
							StatePanel.setBackground(new java.awt.Color(153, 0, 255));
							
							
							MaincatScroll.setPreferredSize( new Dimension(200, 400) );
							SubcatScroll.setPreferredSize( new Dimension(200, 400) );
							AttributeScroll.setPreferredSize( new Dimension(200, 400) );
							StartPanel.setPreferredSize( new Dimension(1000, 50) );
							ResultPanel.setPreferredSize( new Dimension(400, 400) );
							EndPanel.setPreferredSize( new Dimension(1000, 50) );
							Holder.setPreferredSize( new Dimension(600,600) );
							ReviewPanel.setPreferredSize(new Dimension(600,600));
							businessscroll.setPreferredSize(new Dimension(400,600));
							
							
							
							
							container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
							Holder.setLayout(new BoxLayout(Holder,BoxLayout.X_AXIS));
							EndPanel.setLayout(new FlowLayout());
							MaincatPanel.setLayout(new GridLayout(0,1));
							SubcatPanel.setLayout(new GridLayout(0,1));
							AttributePanel.setLayout(new GridLayout(0,1));
							
							 businesstable.getColumnModel().getColumn(0).setMinWidth(0);
						     businesstable.getColumnModel().getColumn(0).setMaxWidth(0);
						     businesstable.getColumnModel().getColumn(3).setMinWidth(50);
						     businesstable.getColumnModel().getColumn(3).setMaxWidth(50);
						     businesstable.getColumnModel().getColumn(4).setMinWidth(50);
						     businesstable.getColumnModel().getColumn(4).setMaxWidth(50);
						     businesstable.getColumnModel().getColumn(4).setMinWidth(50);
						     businesstable.getColumnModel().getColumn(4).setMaxWidth(50);
						     
							
							 //frame.getDocument().addUndoableEditListener(manager);
							
							//, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
							StartPanel.add(StartButton);
							EndPanel.add(WeekLabel);
							EndPanel.add(WeekCombo);
							EndPanel.add(Start);
							EndPanel.add(Starttime);
							EndPanel.add(Close);
							EndPanel.add(Closetime);
							EndPanel.add(Attr);
							EndPanel.add(AttrSelect);
							EndPanel.add(SearchButton);
							EndPanel.add(CloseButton);
							Holder.add(MaincatScroll);
							Holder.add(SubcatScroll);
							Holder.add(AttributeScroll);
							Holder.add(ResultPanel);
							container.add(StartPanel);
							container.add(Holder);
							container.add(EndPanel);
							ResultPanel.add(businessscroll);
							
							
							itemlistener3=new ItemListener(){
								public void itemStateChanged(ItemEvent e){
									if(e.getStateChange()==ItemEvent.SELECTED){
										//attrBid();
										Set<String> P=fetchallattrSelectedBids();
										Object[][] c=businessTable(P);
										changeTable(c);
										
									     
									}else{
										//attrBid();
										
										Set<String> P1=fetchallattrSelectedBids();
										Object[][] c1=businessTable(P1);
										changeTable(c1);
									}
								}
							};
							itemlistener5 = new ItemListener() {
								public void itemStateChanged(ItemEvent e){
								if(e.getStateChange()==ItemEvent.SELECTED){
									
									Object[][] city=sortCity();
									changeTable(city);
								}
								}
							};
							itemlistener4 = new ItemListener() {
								public void itemStateChanged(ItemEvent e){
									
								if(e.getStateChange()==ItemEvent.SELECTED){
									Set<String> city=city((String)stateCombo.getSelectedItem());
									cityCombo.setModel(new javax.swing.DefaultComboBoxModel(city.toArray()));
									
									Object[][] state=sortState();
									changeTable(state);
								}
								}
							};
							
							itemlistener2 = new ItemListener() {
								public void itemStateChanged(ItemEvent e){
								if(e.getStateChange()==ItemEvent.SELECTED){
									//System.out.println("Sub category Selected");
									attributesDisplay();
							}else{
								//System.out.println("Sub category Deslected");
								attributesDisplay();
								
							    }
								}
							};
							
							itemlistener1 = new ItemListener() {
								public void itemStateChanged(ItemEvent e){
								if(e.getStateChange()==ItemEvent.SELECTED){
									//System.out.println("Main category Selected");
									subcatDisplay();
							}else{
								//System.out.println("Main category Deslected");
								subcatDisplay();
								
							    }
								}
							};
							StartButton.addActionListener(new java.awt.event.ActionListener() {
							    public void actionPerformed(java.awt.event.ActionEvent evt) {
							    	startbutton(); 	
							}
							});
							businesstable.addMouseListener(new MouseAdapter() {
								  public void mouseClicked(MouseEvent e) {
									  int row = businesstable.getSelectedRow();
							          int col = businesstable.getSelectedColumn();

							        String data = (String)businesstable.getValueAt(row, 0);
							        JOptionPane.showMessageDialog(null, data);
								  //System.out.println(data);
								  Object[][] reviewrows=reviews(data);
								  addreviewtable(reviewrows);
								  
								  }
								});
							
							
							frame.setContentPane(container);
							frame.pack();
							frame.setTitle("Yelp Application");
							frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							frame.setVisible(true);
	}
public void maincategorySelection(){
	dmlist.clear();
	for(int i=0;i<checkboxes.size();i++){
		if(checkboxes.get(i).isSelected()){
			String mname=checkboxes.get(i).getText();
			dmlist.add(mname);
		}
	}
	//System.out.println("Maincategories selected:"+dmlist.size());
}
public void subcategorySelection(){
	dslist.clear();
	for(int j=0;j<checkboxes1.size();j++){
		if(checkboxes1.get(j).isSelected()){
			String sname=checkboxes1.get(j).getText();
			dslist.add(sname);
		}
	}
	//System.out.println("Subcategories selected:"+dslist.size());
}
public void attributeSelection(){
	dalist.clear();
	for(int k=0;k<checkboxes2.size();k++){
		if(checkboxes2.get(k).isSelected()){
			String aname=checkboxes2.get(k).getText();
			dalist.add(aname);
		}
	}
	//System.out.println("Attributes selected:"+dalist.size());
}
public void subcatScreen(){
	Asubhlist.clear();
	Asublist.clear();
	maincategorySelection();
	for(String s1:dmlist){
		try{
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(DB_URL,Username,pass);
			stmt=conn.createStatement();
			String q1="SELECT DISTINCT Subcategory FROM Categories WHERE Maincategory=\'"+s1+"\'";
			stmt.execute(q1);
			ResultSet rs1=stmt.executeQuery(q1);
			while(rs1.next()){
				Asubhlist.add(rs1.getString("Subcategory"));
			}						
	    }catch(SQLException se){
	    se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
		
	     if(conn!=null)
	        conn.close();

		}catch(SQLException se){
	     se.printStackTrace();
		}
		}
		for(String ss:Asubhlist){
			Asublist.add(ss);
		}
		}
	//System.out.println("Asubhlist:"+Asubhlist.size());
	//System.out.println("Asublist:"+Asublist.size());
}
public void subcatDisplay(){
	subcatScreen();
	checkboxes1.clear();
	checkboxes2.clear();
	SubcatPanel.removeAll();
	AttributePanel.removeAll();
	ResultPanel.removeAll();
	for(String sss:Asublist){
		JCheckBox checkbox1=new JCheckBox(sss);
		checkboxes1.add(checkbox1);
		SubcatPanel.add(checkbox1);
		checkbox1.setOpaque(false);;
		checkbox1.addItemListener(itemlistener2);
	}
	SubcatPanel.revalidate();
	SubcatPanel.repaint();
	SubcatPanel.revalidate();
	AttributePanel.repaint();
	AttributePanel.revalidate();
	ResultPanel.repaint();
	ResultPanel.revalidate();
	//System.out.println("Maincat screen check:");
	//System.out.println("checkboxes:"+checkboxes.size());
	//System.out.println("checkboxes1:"+checkboxes1.size());
	//System.out.println("checkboxes2:"+checkboxes2.size());
	//System.out.println("Asublist:"+Asublist.size());
}
public void maincatBid(){
	mbids.clear();
	maincategorySelection();
	
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(DB_URL,Username,pass);
			stmt=conn.createStatement();
			for(String s2:dmlist){
			String q2="SELECT DISTINCT business_id FROM Categories WHERE Maincategory=\'"+s2+"\'";
			stmt.execute(q2);
			ResultSet rs2=stmt.executeQuery(q2);
			while(rs2.next()){
				mbids.add(rs2.getString("business_id"));
			}
			}
	    }catch(SQLException se){
	    se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
		
	     if(conn!=null)
	        conn.close();

		}catch(SQLException se){
	     se.printStackTrace();
		}
		}
		
		//System.out.println("Maincategory business:"+mbids.size());	
}

public void subcatBid(){
		sbids.clear();
		maincatBid();
		subcategorySelection();
		Set<String> ssparebids=new HashSet<String>();
		//System.out.println("subcategories selected:"+dslist.size());
		
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection(DB_URL,Username,pass);
				stmt=conn.createStatement();
				for(String s3:dslist){
				String q3="SELECT DISTINCT business_id FROM Categories WHERE Subcategory=\'"+s3+"\'";
				stmt.execute(q3);
				ResultSet rs3=stmt.executeQuery(q3);
				while(rs3.next()){
					ssparebids.add(rs3.getString("business_id"));
				}
				}
		    }catch(SQLException se){
		    se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
			
		     if(conn!=null)
		        conn.close();

			}catch(SQLException se){
		     se.printStackTrace();
			}
			}
			
		for(String s4:ssparebids){
			if(mbids.contains(s4)){
				sbids.add(s4);
			}
				
			}
		//System.out.println("Subcategory business:"+sbids.size());	
		
	}
	
public void anyattrBid(){
	Anyattrbids.clear();
	attributeSelection();
	Set<String> asparebids=new HashSet<String>();
	try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(DB_URL,Username,pass);
			stmt=conn.createStatement();
			for(String attr:dalist){
			String q2="SELECT DISTINCT business_id FROM BAttributes WHERE Hasattribute=\'"+attr+"\'";
			stmt.execute(q2);
			ResultSet rs2=stmt.executeQuery(q2);
			while(rs2.next()){
				asparebids.add(rs2.getString("business_id"));
			}
			}
	    }catch(SQLException se){
	    se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
		
	     if(conn!=null)
	        conn.close();

		}catch(SQLException se){
	     se.printStackTrace();
		}
		}
	for(String b:asparebids){
		if(sbids.contains(b)){
		Anyattrbids.add(b);
		}
	}
	//System.out.println("Any Attribute business:"+Anyattrbids.size());
		
}
public void attributesScreen(){
	Attributeshlist.clear();
	Attributeslist.clear();
	subcatBid();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(DB_URL,Username,pass);
			stmt=conn.createStatement();
			for(String s5:sbids){
			String q4="SELECT Hasattribute from BAttributes WHERE business_id=\'"+s5+"\'";
			stmt.executeQuery(q4);
			ResultSet rs4=stmt.executeQuery(q4);
			while(rs4.next()){
				String attribute=rs4.getString("Hasattribute");
					Attributeshlist.add(attribute);
				}
		}						
	    }catch(SQLException se){
	    se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
		
	     if(conn!=null)
	        conn.close();

		}catch(SQLException se){
	     se.printStackTrace();
		}
		}
		for(String sa:Attributeshlist){
			Attributeslist.add(sa);
		}
		
	//System.out.println("Attributeslist:"+Attributeslist.size());
	
}
public void exceptSelectedattrBid(){
	attributesScreen();
	attributeSelection();
	Set<String> exceptattr=new HashSet<String>();
	exceptattr=Attributeslist;
	for(String s:dalist){
		exceptattr.remove(s);
	}
	Set<String> asparebids=new HashSet<String>();
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection(DB_URL,Username,pass);
		stmt=conn.createStatement();
		for(String attr:exceptattr){
		String q2="SELECT DISTINCT business_id FROM BAttributes WHERE Hasattribute=\'"+attr+"\'";
		stmt.execute(q2);
		ResultSet rs2=stmt.executeQuery(q2);
		while(rs2.next()){
			asparebids.add(rs2.getString("business_id"));
		}
		}
    }catch(SQLException se){
    se.printStackTrace();
}catch(Exception e){
	e.printStackTrace();
}
finally{
	try{
	
     if(conn!=null)
        conn.close();

	}catch(SQLException se){
     se.printStackTrace();
	}
	}
for(String b:asparebids){
	if(sbids.contains(b)){
	Exceptattrbids.add(b);
	}
}
	
}
public void allSelected(){
	Aallbids.clear();
	subcatBid();
	
	attributesScreen();
	//System.out.println("checkboxes:"+checkboxes2.size());
	//System.out.println(dalist.size());
	attributeSelection();
	//System.out.println("checkboxes:"+checkboxes2.size());
	//System.out.println(dalist.size());
	Set<String> battr=new HashSet<String>();
	if(dalist.size()!=0){
	try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(DB_URL,Username,pass);
			stmt=conn.createStatement();
			for(String b:sbids){
				battr.clear();
			String q2="SELECT DISTINCT Hasattribute FROM BAttributes WHERE business_id=\'"+b+"\'";
			ResultSet rs2=stmt.executeQuery(q2);
			while(rs2.next()){
				battr.add(rs2.getString("Hasattribute"));
			}
			if(battr.containsAll(dalist)){
				Aallbids.add(b);
			}
			}
	    }catch(SQLException se){
	    se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		try{
		
	     if(conn!=null)
	        conn.close();

		}catch(SQLException se){
	     se.printStackTrace();
		}
		}
	}
	
	//System.out.println("AllAttribute business:"+Aallbids.size());
}



public void attributesDisplay(){
	AttributePanel.removeAll();
	ResultPanel.removeAll();
	checkboxes2.clear();
	attributesScreen();
	for(String sas:Attributeslist){
		JCheckBox checkbox2=new JCheckBox(sas);
		checkboxes2.add(checkbox2);
		AttributePanel.add(checkbox2);
		checkbox2.setOpaque(false);
		checkbox2.addItemListener(itemlistener3);
	}
	AttributePanel.revalidate();
	AttributePanel.repaint();
	AttributePanel.revalidate();
	ResultPanel.repaint();
	ResultPanel.revalidate();
	//System.out.println("Subcat listener check:");
	//System.out.println("checkboxes:"+checkboxes.size());
	//System.out.println("checkboxes1:"+checkboxes1.size());
	//System.out.println("checkboxes2:"+checkboxes2.size());
	//System.out.println("Attributeslist:"+Attributeslist.size());
	
}
public void maincat(){
	Amainhlist.clear();
	Amainlist.clear();
	try{
    	Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection(DB_URL,Username,pass);
		stmt=conn.createStatement();
		Amainhlist=new HashSet<String>();
		String qm="SELECT DISTINCT Maincategory FROM Categories";
		stmt.execute(qm);
		ResultSet rsm=stmt.executeQuery(qm);
			while(rsm.next()){
				Amainhlist.add(rsm.getString("Maincategory"));
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
				try{
						if(conn!=null)
							conn.close();

				}catch(SQLException se){
					se.printStackTrace();
				}
			}
	for(String sm:Amainhlist){
		Amainlist.add(sm);
	}
}
public void startbutton(){
	
						checkboxes.clear();
						checkboxes1.clear();
						checkboxes2.clear();
						MaincatPanel.removeAll();
						SubcatPanel.removeAll();
						AttributePanel.removeAll();
						ResultPanel.removeAll();
						ReviewPanel.removeAll();
						Holder.remove(ReviewPanel);
						firstStep();
						maincat();
							for (String j:Amainlist) {
						        JCheckBox checkbox = new JCheckBox(j);
						        checkbox.setOpaque(false);
						        checkboxes.add(checkbox); //for further use you add it to the list
						        MaincatPanel.add(checkbox);
						        checkbox.addItemListener(itemlistener1);
						        MaincatPanel.revalidate();
						        }
							MaincatPanel.repaint();
							MaincatPanel.revalidate();
							SubcatPanel.repaint();
							SubcatPanel.revalidate();
							AttributePanel.repaint();
							AttributePanel.revalidate();
							ResultPanel.repaint();
							ResultPanel.revalidate();
							  WeekCombo.setSelectedIndex(-1);
					          Starttime.setSelectedIndex(-1);
					          Closetime.setSelectedIndex(-1);
					          AttrSelect.setSelectedIndex(-1);
					          //System.out.println("Startbutton requirements check");
					          //System.out.println("Weekcombo"+WeekCombo.getSelectedItem());
					         // System.out.println("Starttime"+Starttime.getSelectedItem());
					         // System.out.println("Closetime"+Closetime.getSelectedItem());
					         // System.out.println("AttrSelect"+AttrSelect.getSelectedItem());
					         // System.out.println("checkbboxes:"+checkboxes.size());
					         // System.out.println("checkbboxes1:"+checkboxes1.size());
					          //System.out.println("checkbboxes2:"+checkboxes2.size());
}

public Object[][] businessTable(Set P){
	//attrBid();
	//List<String> P=new ArrayList<String>(p);
	Set<String> Pset=P;
	Object[][] rows=new String[Pset.size()][6];
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection(DB_URL,Username,pass);
		stmt=conn.createStatement();
		int i=0;
		for(String b1:Pset){
			String query1="select business_id,bname,City,State,stars,reviewcount from Business where business_id=\'"+b1+"\'";
			ResultSet rs6=stmt.executeQuery(query1);
	        while(rs6.next()) {
	            Object bid=rs6.getString("business_id");
	            Object bname = rs6.getString("bname");
	            Object city = rs6.getString("City");
	            Object state = rs6.getString("State");
	            Object stars = Integer.toString(rs6.getInt("Stars"));
	            Object rcount =Integer.toString(rs6.getInt("reviewcount"));      
	            rows[i][0]=bid;
	            rows[i][1]=bname;
	            rows[i][2]=city;
	            rows[i][3]=state;
	            rows[i][4]=stars;
	            rows[i][5]=rcount;     
	        i++;
	        }
		
		}
		
	}catch(SQLException se){
	    se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
		finally{
		try{
		
	     if(conn!=null)
	        conn.close();
	
	}catch(SQLException se){
	     se.printStackTrace();
	  }
		}
	
	return rows;
}
public void changeTable(Object[][] R){
	ResultPanel.removeAll();
      businesstable=new JTable(R,columns);
      //businesstable.setEnabled(false);
      businesstable.setFocusable(false);
      businesstable.getColumnModel().getColumn(0).setMinWidth(0);
      businesstable.getColumnModel().getColumn(0).setMaxWidth(0);
      businesstable.getColumnModel().getColumn(3).setMinWidth(50);
      businesstable.getColumnModel().getColumn(3).setMaxWidth(50);
      businesstable.getColumnModel().getColumn(4).setMinWidth(50);
      businesstable.getColumnModel().getColumn(4).setMaxWidth(50);
      businesstable.getColumnModel().getColumn(5).setMinWidth(50);
      businesstable.getColumnModel().getColumn(5).setMaxWidth(50);
      businesstable.addMouseListener(new MouseAdapter() {
    	  public void mouseClicked(MouseEvent e) {
    		  businesstable.getColumnModel().getColumn(2).setMinWidth(70);
    	      businesstable.getColumnModel().getColumn(2).setMaxWidth(70);
    		  businesstable.getColumnModel().getColumn(3).setMinWidth(30);
    	      businesstable.getColumnModel().getColumn(3).setMaxWidth(30);
    		  businesstable.getColumnModel().getColumn(4).setMinWidth(30);
    	      businesstable.getColumnModel().getColumn(4).setMaxWidth(30);
    	      businesstable.getColumnModel().getColumn(5).setMinWidth(30);
    	      businesstable.getColumnModel().getColumn(5).setMaxWidth(30);
    	      businessscroll.setPreferredSize( new Dimension(300, 600));
    		  ReviewPanel.removeAll();
    		  int row = businesstable.getSelectedRow();
              int col = businesstable.getSelectedColumn();
             // businesstable.setToolTipText((String)businesstable.getValueAt(row, col));
            String data = (String)businesstable.getValueAt(row, 0);
    	  System.out.println(data);
    	  Object[][] reviewrows=reviews(data);
    	  addreviewtable(reviewrows);
    	  
    	  }
    	});
      JScrollPane businessscroll=new JScrollPane(businesstable,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      //ResultPanel.setPreferredSize(new Dimension(300,600));
      businessscroll.setPreferredSize( new Dimension(400, 600));
      businesstable.setAutoCreateRowSorter(true); 
      
         businesstable.setForeground(Color.blue);
         businesstable.setRowHeight(24);
         businesstable.setFont(new Font("Arial", Font.BOLD, 12));
         // c.setBackground(Color.RED);
       
		businessscroll.setOpaque(false);
		businessscroll.getViewport().setOpaque(false);
		ResultPanel.add(businessscroll);
		ResultPanel.setLayout(new BoxLayout(ResultPanel,BoxLayout.Y_AXIS));
		
		StatePanel.setLayout(new FlowLayout());
		StatePanel.add(statelabel);

	      
	      StatePanel.add(stateCombo);
	StatePanel.add(citylabel);

	      
	      StatePanel.add(cityCombo);
		ResultPanel.add(StatePanel);
		ResultPanel.repaint();
		ResultPanel.revalidate();
	}
public Object[][] sortCity(){
	
	
	int row = businesstable.getRowCount();
	int column = businesstable.getColumnCount();
	Object[][] city=new String[row][6];
	Object[][] citynew=new String[row][6];
	for (int j = 0; j  < row; j++) {
		String C=(String)businesstable.getValueAt(j, 2);
	        System.out.println(businesstable.getValueAt(j, 2));
		if(C!=null){
	       if(C.toLowerCase().equals(((String)cityCombo.getSelectedItem()).toLowerCase())){
	        	for (int i = 0; i  < 6; i++) {
	        		city[j][i]=(String)businesstable.getValueAt(j, i);
	        	}
	        }
	        
		
	}
	}
	
	int i=0;
	for(int k=0;k<city.length;k++){
		if(city[k][0]!=null){
			citynew[i][0]=city[k][0];
			citynew[i][1]=city[k][1];
			citynew[i][2]=city[k][2];
			citynew[i][3]=city[k][3];
			citynew[i][4]=city[k][4];
			citynew[i][5]=city[k][5];
			i++;
		}
	}
	 return citynew;
}
public Object[][] sortState(){
	Object[][] state=new String[100][6];
	Object[][] statenew=new String[100][6];
							    	int row = businesstable.getRowCount();
							    	int column = businesstable.getColumnCount();
							    	System.out.println(row);
							    	System.out.println(column);
							    	System.out.println((String)stateCombo.getSelectedItem());
							    	for (int j = 0; j  < row; j++) {
							    		String S=(String)businesstable.getValueAt(j, 3);
							    	        System.out.println(businesstable.getValueAt(j, 3));
							    	        if(((String)stateCombo.getSelectedItem()).equals(S)){
							    	        	for (int i = 0; i  < column; i++) {
							    	        		state[j][i]=(String)businesstable.getValueAt(j, i);
							    	        	}
							    	        }
							    	        
							    	    
							    	}
							    	int i=0;
							    	for(int k=0;k<state.length;k++){
							    		if(state[k][0]!=null){
							    			//System.out.println(state[k][3]);
							    			statenew[i][0]=state[k][0];
							    			statenew[i][1]=state[k][1];
							    			statenew[i][2]=state[k][2];
							    			statenew[i][3]=state[k][3];
							    			statenew[i][4]=state[k][4];
							    			statenew[i][5]=state[k][5];
							    			i++;
							    		}
							    	}
							    	
							    	
							    	for(int s=0;s<statenew.length;s++){
							    		System.out.println("State is:"+s+statenew[s][3]);
							    	}
							    	return statenew;
}
public void addreviewtable(Object[][] p){
			 //ResultPanel.removeAll();
			 // businesstable=new JTable(p,reviewcolumns);
			reviewtable=new JTable(p,reviewcolumns);
			reviewtable.getColumnModel().getColumn(0).setMinWidth(100);
		    reviewtable.getColumnModel().getColumn(0).setMaxWidth(100);
		    reviewtable.getColumnModel().getColumn(1).setMinWidth(20);
		    reviewtable.getColumnModel().getColumn(1).setMaxWidth(20);
		    reviewtable.getColumnModel().getColumn(2).setMinWidth(350);
		    reviewtable.getColumnModel().getColumn(2).setMaxWidth(350);
		    reviewtable.getColumnModel().getColumn(3).setMinWidth(50);
		    reviewtable.getColumnModel().getColumn(3).setMaxWidth(50);
		    reviewtable.getColumnModel().getColumn(4).setMinWidth(50);
		    reviewtable.getColumnModel().getColumn(4).setMaxWidth(50);
		    reviewtable.getColumnModel().getColumn(2).sizeWidthToFit();
		    reviewtable.setForeground(Color.blue);
		    reviewtable.setBackground(Color.LIGHT_GRAY);
		    reviewtable.getColumnModel().getColumn(2).setResizable(true);;
		    reviewtable.setRowHeight(50);
		    reviewtable.setFont(new Font("Arial", Font.BOLD, 12));
			  JScrollPane reviewscroll=new JScrollPane(reviewtable,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			  reviewscroll.setPreferredSize(new Dimension(600,600));
			  reviewscroll.setEnabled(false);
			  reviewscroll.setOpaque(false);
			  reviewscroll.getViewport().setOpaque(false);
			  int rrow = businesstable.getSelectedRow();
              int rcol = businesstable.getSelectedColumn();
			  //reviewtable.setToolTipText((String)reviewtable.getValueAt(rrow,2));
			  ReviewPanel.add(reviewscroll);
			  ReviewPanel.revalidate();
			  ReviewPanel.repaint();
			  //Holder.remove(MaincatScroll);
			  MaincatScroll.setPreferredSize(new Dimension(0,0));
			 // Holder.remove(SubcatScroll);
			 SubcatScroll.setPreferredSize(new Dimension(0,0));
			  //Holder.remove(AttributeScroll);
			 AttributeScroll.setPreferredSize(new Dimension(0,0));
			  Holder.revalidate();
			  Holder.repaint();
			  Holder.add(ReviewPanel);
			
			  //Holder.remove(ResultPanel);
			  //Holder.add(businesss2croll);
			  //Holder.setBackground(new java.awt.Color(204, 255, 153));
			  Holder.revalidate();
			  Holder.repaint();
			  //container.remove(StartPanel);
			  //StartPanel.remove(StartButton);
			  StartButton.setVisible(false);;
			  StartPanel.add(RCloseButton);
			  //EndPanel.remove(WeekCombo);
			  WeekCombo.setVisible(false);
			 // EndPanel.remove(Starttime);
			  Starttime.setVisible(false);
			  //EndPanel.remove(Closetime);
			 Closetime.setVisible(false);
			 // EndPanel.remove(AttrSelect);
			 AttrSelect.setVisible(false);
			  //EndPanel.remove(WeekLabel);
			  WeekLabel.setVisible(false);
			 // EndPanel.remove(Start);
			  Start.setVisible(false);
			  //EndPanel.remove(Close);
			 Close.setVisible(false);
			  //EndPanel.remove(SearchButton);
			  SearchButton.setVisible(false);
			  //EndPanel.remove(Attr);
			 Attr.setVisible(false);
			  //EndPanel.add(RCloseButton);
			  
			  EndPanel.revalidate();
			  EndPanel.repaint();
			  
			  
			  //ResultPanel.revalidate();
				//ResultPanel.repaint();
			  //container.revalidate();
			  //container.repaint();
				
}
public String clobString(Clob clb) throws IOException, SQLException
{
  if (clb == null)
 return  "";
        
  StringBuffer str = new StringBuffer();
  String strng;
          

BufferedReader bufferRead = new BufferedReader(clb.getCharacterStream());

  while ((strng=bufferRead .readLine())!=null)
   str.append(strng);

  return str.toString();
}  
	public Object[][] reviews(String data){
		Object[][] reviewrows=new String[0][5];
		
											try{
										    	Class.forName("oracle.jdbc.driver.OracleDriver");
												System.out.println("connecting to database....");
												conn=DriverManager.getConnection(DB_URL,Username,pass);
												stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
													    ResultSet.CONCUR_READ_ONLY);
												String query="select rdate,rstars,Text,user_name,rvotes_useful from Yelp_Review R,YelpUser U WHERE R.user_id=U.user_id AND R.business_id=\'"+data+"\'";
												ResultSet rs=stmt.executeQuery(query);
												int count=0;
												while(rs.next())
												{
													count++;
												}
												System.out.println(count);
												rs.beforeFirst();
												Object[][] rows=new String[count][5];
												//System.out.println("here");
												int i=0;
													
										        while(rs.next()) {
										            Object rdate=rs.getString("rdate");
										            Object stars = Integer.toString(rs.getInt("rstars"));
										            Object review = clobString(rs.getClob("Text"));
										            Object uname = rs.getString("user_name");
										            Object votes = Integer.toString(rs.getInt("rvotes_useful"));
										                 
										            rows[i][0]=rdate;
										            rows[i][1]=stars;
										            rows[i][2]=review;
										            rows[i][3]=uname;
										            rows[i][4]=votes;
										            
										             //System.out.println(count);      
										
										        i++;
										        }
										        
										        		reviewrows=rows;
										        
										        
												System.out.println(count);
										    }catch(SQLException se){
										    se.printStackTrace();
										}catch(Exception e){
											e.printStackTrace();
										}
											finally{
											try{
											
										     if(conn!=null)
										        conn.close();
										
										}catch(SQLException se){
										     se.printStackTrace();
										  }
											}
											//System.out.println(reviewrows[1][2]);
											return reviewrows;
											
											
											
											

	}


public Set fetchallattrSelectedBids(){
	allSelected();
	System.out.println(Aallbids.size());
	return Aallbids;
}
public Set fetchanyattrSelectedBids(){
	anyattrBid();
	return Anyattrbids;
}
public Set fetchexceptSelectedBids(){
	exceptSelectedattrBid();
	return Exceptattrbids;
}
public Set searchFilter(){
	
											ResultPanel.removeAll();
											Set<String> searchBids=new HashSet<String>();
										
											String ws=null;
											String wc=null;
											String w=(String)WeekCombo.getSelectedItem();
											String stime=(String)Starttime.getSelectedItem();
											String ctime=(String)Closetime.getSelectedItem();
											String attrchange=(String)AttrSelect.getSelectedItem();
											if(AttrSelect.getSelectedIndex()==-1){
												AttrSelect.setSelectedItem("All Selected Attributes");
												searchBids=fetchallattrSelectedBids();
												System.out.println("All attributes:"+searchBids.size());
											}else{
											if(attrchange.equals("All Selected Attributes")){
												searchBids=fetchallattrSelectedBids();
												System.out.println("All attributes:"+searchBids.size());
											}
											if(attrchange.equals("Any Selected Attributes")){
												searchBids=fetchanyattrSelectedBids();
												System.out.println("Any attributes:"+searchBids.size());
											}
											if(attrchange.equals("None of Selected")){
												searchBids=fetchexceptSelectedBids();
												System.out.println("none:"+searchBids.size());
											}
											if(attrchange.equals("Skip Attributes")){
												subcatBid();
												searchBids=sbids;
												System.out.println("skipping gives SubcatBids:"+searchBids.size());
											}
											}
											if(w==null){
												SearchBids=searchBids;
											}
												//JOptionPane.showMessageDialog(null, "Select any week day");
											else{
															if(w=="Monday"){
																ws="Monopen";
																wc="Monclose";
															}
															if(w=="Tuesday"){
																ws="Tuesopen";
																wc="Tuesclose";
															}
															if(w=="Wednesday"){
																ws="wedopen";
																wc="wedclose";
															}
															if(w=="Thursday"){
																ws="thuopen";
																wc="thuclose";
															}
															if(w=="Friday"){
																ws="friopen";
																wc="friclose";
															}
															if(w=="Saturday"){
																ws="satopen";
																wc="satclose";
															}
															if(w=="Sunday"){
																ws="sunopen";
																wc="sunclose";
															}
															
															if(stime==null || ctime==null){
																//JOptionPane.showMessageDialog(null, "Select proper timings");
																stime="00:01";
																ctime="24:00";
															}
															SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
															//System.out.println(w);
															//System.out.println(ws);
															//System.out.println(wc);
															try{
															//System.out.println(parser.parse(stime));
															//System.out.println(parser.parse(ctime));
															}catch(Exception e){
																e.printStackTrace();
															}
															if(searchBids.isEmpty()){
																JOptionPane.showMessageDialog(null,"Sorry!!!No Businesses within your selection");
															}else{
																
																try{
															    	Class.forName("oracle.jdbc.driver.OracleDriver");
																	System.out.println("connecting to database....");
																	conn=DriverManager.getConnection(DB_URL,Username,pass);
																	stmt=conn.createStatement();
																	SearchBids.clear();
																	for(String b2:searchBids){
															String query1="Select "+ws+","+wc+" from Hours Where business_id=\'"+b2+"\'";
															ResultSet rst=stmt.executeQuery(query1);
															while(rst.next()){
															String opentime=rst.getString(ws);
															String closetime=rst.getString(wc);
															if(opentime==null){
																opentime="00:01";
																
															}
															if(closetime==null){
																closetime="24:00";
															}
															if(opentime!=null && closetime!=null){
															// System.out.println(parser.parseObject(opentime));
																
															if((parser.parse(opentime)).before(parser.parse(stime)) && (parser.parse(closetime)).after(parser.parse(ctime))){
																SearchBids.add(b2);
															}
															}
															}
															}
															}catch(SQLException se){
															    se.printStackTrace();
															}catch(Exception e){
																e.printStackTrace();
															}
																finally{
																try{
																
															     if(conn!=null)
															        conn.close();
															
															}catch(SQLException se){
															     se.printStackTrace();
															  }
															
																}
															}
											}
											System.out.println("screen completed");
											
											return SearchBids;

}

public Set States(){
	Set<String> states=new TreeSet<String>();
	try{
    	Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("connecting to database....");
		conn=DriverManager.getConnection(DB_URL,Username,pass);
		stmt=conn.createStatement();
		String query="SELECT DISTINCT State FROM Business";
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next())
		{
			states.add(rs.getString("State"));
		}
		
    }catch(SQLException se){
    se.printStackTrace();
}catch(Exception e){
	e.printStackTrace();
}
	finally{
	try{
	
     if(conn!=null)
        conn.close();

}catch(SQLException se){
     se.printStackTrace();
  }
	}
	
	return states;
	
	
}
public Set city(String C){
	Set<String> cities=new TreeSet<String>();
	
	try{
    	Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("connecting to database....");
		conn=DriverManager.getConnection(DB_URL,Username,pass);
		stmt=conn.createStatement();
		
		String query="SELECT DISTINCT City FROM Business WHERE State=\'"+C+"\'";
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next())
		{
			cities.add(rs.getString("City"));
		}
		
    }catch(SQLException se){
    se.printStackTrace();
}catch(Exception e){
	e.printStackTrace();
}
	finally{
	try{
	
     if(conn!=null)
        conn.close();

}catch(SQLException se){
     se.printStackTrace();
  }
	}
	
	return cities;
	
	
}
									    
										
										
										
										
										
										
										

public YelpApplication(){
	firstStep();
}

public static void main(String[] args){
		new YelpApplication();
	}
}
	
