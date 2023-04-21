import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class M_1_2_AwtApplication implements ActionListener,WindowListener
{
	Frame f1;
	Panel p1,p2,p3,p4,p5;
	Label l1,l2,l3,l4,l5,l6,l7,l8;
	TextField t1,t2,t3;
	TextArea ta1;
	Checkbox ch1,ch2,ch3,ch4,ch5,ch6;
	CheckboxGroup cg1;
	Choice c1;
	Button b1,b2,b3;
	static String reg,name,dob,gen,branch,prolan="",addr,operations,comma=", ";
	M_1_2_AwtApplication()
	{
		f1 = new Frame("AwtApplication");
		GridLayout gl1= new GridLayout(1,2,5,5);
		l1 = new Label("Reg Number: ");
		l2 = new Label("Student Name: ");
		l3  = new Label("Date of Birth(DD-MM-YYYY): ");
		l4 = new Label("Gender: ");
		l5 = new Label("Branch: ");
		l6 = new Label("Programming Language: ");
		l7 = new Label("Address: ");
		l8 = new Label("Operations: ");
		
		t1=new TextField(80);
		t2=new TextField(80);
		t3=new TextField(80);
		ta1=new TextArea(2,10);
		
		cg1 = new CheckboxGroup();
		ch1 = new Checkbox("-Male",cg1,false);
		ch2 = new Checkbox("-Female",cg1,false); 
		ch3 = new Checkbox("C"); 
		ch4 = new Checkbox("Python"); 
		ch5 = new Checkbox("Java"); 
		ch6 = new Checkbox("HTML");

		b1 = new Button("Register");
		b2 = new Button("Get Details");
		b3 = new Button("Clear");
		
		c1 = new Choice();

		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		p5 = new Panel();


		//add area
		/*f1.add(l1); f1.add(l2); f1.add(l3); f1.add(l4); f1.add(l5); f1.add(l6); f1.add(l7); f1.add(l8);*/
		p1.add(l1); p1.add(l2); p1.add(l3); p1.add(l4); p1.add(l5); p1.add(l6); p1.add(l7); p1.add(l8);
		f1.add(p1);
		f1.addWindowListener(this);
		/*f1.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				f1.dispose();
			}
		});*/

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		p2.add(t1);	
		p2.add(t2);
		p2.add(t3);
		p2.add(p3);
		p2.add(c1);
		p2.add(p4);
		p2.add(ta1);
		p2.add(p5);

		p3.add(ch1);
		p3.add(ch2);
		p4.add(ch3);
		p4.add(ch4);
		p4.add(ch5);
		p4.add(ch6);
		p5.add(b1);
		p5.add(b2);
		p5.add(b3);
		f1.add(p2);

		c1.add("CSE");
		c1.add("ECE");
		c1.add("IT");
		c1.add("EEE");
		c1.add("MECH");
		c1.add("CIVIL");
		

		//visibility and layout section
		f1.setSize(400,400);
		f1.setVisible(true);
		f1.setLayout(gl1);
		p1.setLayout(new GridLayout(8,1,5,5));
		p3.setLayout(new GridLayout(1,2,0,0));
		p2.setLayout(new GridLayout(8,1,5,5));
		p4.setLayout(new GridLayout(1,4,2,2));
		p5.setLayout(new GridLayout(1,3,2,2));

	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource()==b3)
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
			ta1.setText("");
			reg="";
			name="";
			dob="";
			addr="";
			prolan="";
			branch="";
		}
		if (ae.getSource()==b1)
		{
			if (ch1.getState())
			{
				gen = ch1.getLabel();
			}
			if (ch2.getState())
			{
				gen = ch2.getLabel();	
			}
			if (ch3.getState())
			{
				prolan=prolan+","+ch3.getLabel();
			}
			if (ch4.getState())
			{
				prolan=prolan+","+ch4.getLabel();
			}
			if (ch5.getState())
			{
				prolan=prolan+","+ch5.getLabel();
			}
			if (ch6.getState())
			{
				prolan=prolan+","+ch6.getLabel();
			}
			reg = t1.getText();
			name = t2.getText();
			dob = t3.getText();
			addr = ta1.getText();
			branch = c1.getSelectedItem();
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@laptop-ftb4ntjs:1526:xe", "system", "Madhu123");
				String query = "insert into stureg values(?,?,?,?,?,?,?)";
				PreparedStatement ps1 = con.prepareStatement(query);
				ps1.setString(1,reg);
				ps1.setString(2,name);
				ps1.setString(3,dob);
				ps1.setString(4,gen);
				ps1.setString(5,branch);
				ps1.setString(6,prolan);
				ps1.setString(7,addr);
				ps1.executeUpdate();
				//ps1.executeQuery();
				System.out.println("Data Stored in the Database Successfully...");
				con.close();
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		if (ae.getSource()==b2)
		{
			System.out.println("reg: "+reg);
			System.out.println("name: "+name);
			System.out.println("dob: "+dob);
			System.out.println("gen: "+gen);
			System.out.println("branch: "+branch);
			System.out.println("prolan: "+prolan);
			System.out.println("addr: "+addr);
		}
		    
	}
	public void windowClosing(WindowEvent we)
	{
		f1.dispose();
	}
	
	public void windowActivated (WindowEvent arg0) {    
		System.out.println("activated");    
	}    
  

	public void windowClosed (WindowEvent arg0) {    
		System.out.println("closed");    
	}    
    
	public void windowDeactivated (WindowEvent arg0) {    
		System.out.println("deactivated");    
	}    
  

	public void windowDeiconified (WindowEvent arg0) {    
		System.out.println("deiconified");    
	}    
  

	public void windowIconified(WindowEvent arg0) {    
		System.out.println("iconified");    
	}    
  

	public void windowOpened(WindowEvent arg0) {    
		System.out.println("opened");    
	}
	
	public static void main(String args[])
	{
		new M_1_2_AwtApplication();
	}

}