import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;


public class medical_diag extends JFrame{
	public JCheckBox chckbx1,chckbx2,chckbx3,chckbx4,chckbx5,chckbx6,chckbx7,chckbx8,chckbx9,chckbx10,chckbx11,chckbx12;
	private Connection con = null;
	private final String userName = "sa";
	private final String password = "1234567890";
	private String sqlFinal;
	private Statement stmtSQL;
	private String disease;
	private JButton btnClear,btnUpdate;
	
	public medical_diag() {
		getContentPane().setLayout(null);
		setSize(800,700);
		setVisible(true);
		setTitle("Medical Diagnosis");
		
		JLabel lblMedicalDiagnosis = new JLabel("Medical Diagnosis");
		lblMedicalDiagnosis.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMedicalDiagnosis.setBounds(358, 38, 255, 66);
		getContentPane().add(lblMedicalDiagnosis);
		
		JLabel lblHelloUser = new JLabel("Hello User");
		lblHelloUser.setBounds(54, 136, 104, 39);
		getContentPane().add(lblHelloUser);
		
		JLabel lblPleaseCheckmarkAll = new JLabel("Please checkmark all the applicable symptoms you are having");
		lblPleaseCheckmarkAll.setBounds(54, 213, 478, 29);
		getContentPane().add(lblPleaseCheckmarkAll);
		
		chckbx1 = new JCheckBox("Fever");
		chckbx1.setBounds(54, 279, 97, 23);
		getContentPane().add(chckbx1);
		
		chckbx2 = new JCheckBox("Nausea");
		chckbx2.setBounds(226, 279, 97, 23);
		getContentPane().add(chckbx2);
		
		chckbx3 = new JCheckBox("Vomiting");
		chckbx3.setBounds(435, 279, 97, 23);
		getContentPane().add(chckbx3);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				identifyDisease();
			}
		});
		btnSubmit.setBounds(105, 518, 89, 23);
		getContentPane().add(btnSubmit);
		
		
		
		
		
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chckbx1.setSelected(false);
				chckbx2.setSelected(false);
				chckbx3.setSelected(false);
				chckbx4.setSelected(false);
				chckbx5.setSelected(false);
				chckbx6.setSelected(false);
				chckbx7.setSelected(false);
				chckbx8.setSelected(false);
				chckbx9.setSelected(false);
				chckbx10.setSelected(false);
				sqlFinal=null;
			}
		});
		btnClear.setBounds(236, 518, 89, 23);
		getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		btnExit.setBounds(358, 518, 89, 23);
		getContentPane().add(btnExit);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateDatabase();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(485, 518, 89, 23);
		getContentPane().add(btnUpdate);
		
		
		
		
		
		chckbx4 = new JCheckBox("Trouble Sleeping");
		chckbx4.setBounds(54, 329, 140, 23);
		getContentPane().add(chckbx4);
		
		chckbx5 = new JCheckBox("Pain - Urination");
		chckbx5.setBounds(226, 329, 148, 23);
		getContentPane().add(chckbx5);
		
		chckbx6 = new JCheckBox("Headache");
		chckbx6.setBounds(435, 329, 97, 23);
		getContentPane().add(chckbx6);
		
		chckbx7 = new JCheckBox("Muscle Pain");
		chckbx7.setBounds(54, 387, 97, 23);
		getContentPane().add(chckbx7);
		
		chckbx8 = new JCheckBox("Rashes");
		chckbx8.setBounds(226, 387, 97, 23);
		getContentPane().add(chckbx8);
		
		chckbx9 = new JCheckBox("Dry Cough");
		chckbx9.setBounds(435, 387, 97, 23);
		getContentPane().add(chckbx9);
		
		chckbx10 = new JCheckBox("Runny Nose");
		chckbx10.setBounds(54, 445, 97, 23);
		getContentPane().add(chckbx10);
		
		
		
	}

	// file.
		private String getConnectionUrl()
		{
			return "jdbc:sqlserver://localhost:1433;instancename=SQLEXPRESS;database=medical";
		//	return "jdbc:sqlserver://AMEYA-MAC\\SQLEXPRESS;instanceName=SQLEXPRESS;databaseName=test";
			}

		// Return a connection to a database, or null if one cannot be found.
		private Connection getConnection()
		{
			
			try
			{
			
				 // Load the driver. This is specific to Microsoft SQL Server.
			      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			      // Use a static method of DriverManager to get a connection to the
			      // database.
			      con = java.sql.DriverManager.getConnection(getConnectionUrl(), userName, password);
			      	
				
			} catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Error Trace in getConnection() : " + e.getMessage());
			}
			return con;
		}

		/*
		 * Display the driver properties, database details
		 */
		public void displayDbProperties()
		{
			java.sql.DatabaseMetaData dm = null;
			java.sql.ResultSet rs = null;
			try
			{
				con = this.getConnection();
				if (con != null)
				{
					dm = con.getMetaData();
					System.out.println("Driver Information");
					System.out.println("\tDriver Name: " + dm.getDriverName());
					System.out.println("\tDriver Version: " + dm.getDriverVersion());
					System.out.println("\nDatabase Information ");
					System.out.println("\tDatabase Name: " + dm.getDatabaseProductName());
					System.out.println("\tDatabase Version: " + dm.getDatabaseProductVersion());
					System.out.println("Avalilable Catalogs ");
					rs = dm.getCatalogs();
					// Show all databases within the connection.
					while (rs.next())
					{
						System.out.println("\tcatalog: " + rs.getString(1));
					}
					rs.close();
					rs = null;
					closeConnection();
				} else
				{
					System.out.println("Error: No active Connection");
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			dm = null;
		}

		private void closeConnection()
		{
			try
			{
				if (con != null)
				{
					con.close();
				}
				con = null;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		public float prob_appendix(int a[])
		{	
			float c_all = 0,c_disease = 0;
			float data[]=new float[10];
			con=this.getConnection();
			String sql=null;
			  sql="select * from probability_appendix";
			  try {
				  stmtSQL = con.createStatement();
			      ResultSet rs = stmtSQL.executeQuery(sql);
			      rs.next();
			      for(int i=0;i<10;i++)
			      {
			    	  data[i]=rs.getFloat(i+1);
			      }

					rs.close();
					stmtSQL.close();
			  }
			  catch(Exception e)
			  {}
			  
			  
			 float sum=0 ;
			 
			 //to count numerator
			 for(int i=0;i<10;i++)
			 {
				 if(a[i]==0)
					 sum+=(1-data[i]);
				 else
					 sum+=data[i];
			 }
			  
			 //to count no of patients for appendix
			  sql="select count(*) from appendix";
			  try {
				  stmtSQL = con.createStatement();
			      ResultSet rs = stmtSQL.executeQuery(sql);
			      rs.next();
			      c_disease=rs.getInt(1);		 
			      rs.close();
			      stmtSQL.close();
			  }
			  catch(Exception e)
			  {}
			  
			  //to count all cases (total)
			  sql="select sum(a) as total from count_all";
			  try {
				  stmtSQL = con.createStatement();
			      ResultSet rs = stmtSQL.executeQuery(sql);
			      rs.next();
			      c_all=rs.getInt(1);		 
			      rs.close();
			      stmtSQL.close();
			  }
			  catch(Exception e)
			  {}
			  
			 
			 //final result
			 sum=sum/(c_disease/c_all);
			 sum/=100;
			 System.out.println("Appendix probability calculated ="+sum);
			 return sum;
		}
		
		public float prob_dengue(int a[])
		{	
			float c_all = 0,c_disease = 0;
			float data[]=new float[10];
			con=this.getConnection();
			String sql=null;
			  sql="select * from probability_dengue";
			  try {
				  stmtSQL = con.createStatement();
			      ResultSet rs = stmtSQL.executeQuery(sql);
			      rs.next();
			      for(int i=0;i<10;i++)
			      {
			    	  data[i]=rs.getFloat(i+1);
			      }

					rs.close();
					stmtSQL.close();
			  }
			  catch(Exception e)
			  {}
			  
			  
				 float sum=0 ;
				 
				 //to count numerator
				 for(int i=0;i<10;i++)
				 {
					 if(a[i]==0)
						 sum+=(1-data[i]);
					 else
						 sum+=data[i];
				 }
				  
				 //to count no of patients for dengue
				  sql="select count(*) from dengue";
				  try {
					  stmtSQL = con.createStatement();
				      ResultSet rs = stmtSQL.executeQuery(sql);
				      rs.next();
				      c_disease=rs.getInt(1);		 
				      rs.close();
				      stmtSQL.close();
				  }
				  catch(Exception e)
				  {}
				  
				  //to count all cases (total)
				  sql="select sum(a) as total from count_all";
				  try {
					  stmtSQL = con.createStatement();
				      ResultSet rs = stmtSQL.executeQuery(sql);
				      rs.next();
				      c_all=rs.getInt(1);		 
				      rs.close();
				      stmtSQL.close();
				  }
				  catch(Exception e)
				  {}
				  
				 //final result
				 sum=sum/(c_disease/c_all);
				 sum/=100;
				 System.out.println("Dengue probability calculated ="+sum);
				 return sum;
				 }
		
		public float prob_influenza(int a[])
		{				
			float c_all = 0,c_disease = 0;
			float data[]=new float[10];
		con=this.getConnection();
		String sql=null;
		  sql="select * from probability_influenza";
		  try {
			  stmtSQL = con.createStatement();
		      ResultSet rs = stmtSQL.executeQuery(sql);
		      rs.next();
		      for(int i=0;i<10;i++)
		      {
		    	  data[i]=rs.getFloat(i+1);
		      }

				rs.close();
				stmtSQL.close();
		  }
		  catch(Exception e)
		  {}
		  float sum=0 ;
			 
			 //to count numerator
			 for(int i=0;i<10;i++)
			 {
				 if(a[i]==0)
					 sum+=(1-data[i]);
				 else
					 sum+=data[i];
			 }
			  
			 //to count no of patients for influenza
			  sql="select count(*) from influenza";
			  try {
				  stmtSQL = con.createStatement();
			      ResultSet rs = stmtSQL.executeQuery(sql);
			      rs.next();
			      c_disease=rs.getInt(1);		 
			      rs.close();
			      stmtSQL.close();
			  }
			  catch(Exception e)
			  {}
			  
			  //to count all cases (total)
			  sql="select sum(a) as total from count_all";
			  try {
				  stmtSQL = con.createStatement();
			      ResultSet rs = stmtSQL.executeQuery(sql);
			      rs.next();
			      c_all=rs.getInt(1);		 
			      rs.close();
			      stmtSQL.close();
			  }
			  catch(Exception e)
			  {}
			  
			 //final result
			 sum=sum/(c_disease/c_all);
			 sum/=100;
			 System.out.println("Influenza probability calculated ="+sum);
			 return sum;
		}
		
		public float prob_measles(int a[])
		{	
			float c_all = 0,c_disease = 0;
			float data[]=new float[10];
			con=this.getConnection();
			String sql=null;
			  sql="select * from probability_measles";
			  try {
				  stmtSQL = con.createStatement();
			      ResultSet rs = stmtSQL.executeQuery(sql);
			      rs.next();
			      for(int i=0;i<10;i++)
			      {
			    	  data[i]=rs.getFloat(i+1);
			      }

					rs.close();
					stmtSQL.close();
			  }
			  catch(Exception e)
			  {}
			  
			  float sum=0 ;
				 
				 //to count numerator
				 for(int i=0;i<10;i++)
				 {
					 if(a[i]==0)
						 sum+=(1-data[i]);
					 else
						 sum+=data[i];
				 }
				  
				 //to count no of patients for measles
				  sql="select count(*) from measles";
				  try {
					  stmtSQL = con.createStatement();
				      ResultSet rs = stmtSQL.executeQuery(sql);
				      rs.next();
				      c_disease=rs.getInt(1);		 
				      rs.close();
				      stmtSQL.close();
				  }
				  catch(Exception e)
				  {}
				  
				  //to count all cases (total)
				  sql="select sum(a) as total from count_all";
				  try {
					  stmtSQL = con.createStatement();
				      ResultSet rs = stmtSQL.executeQuery(sql);
				      rs.next();
				      c_all=rs.getInt(1);		 
				      rs.close();
				      stmtSQL.close();
				  }
				  catch(Exception e)
				  {}
				  
				 //final result
				 sum=sum/(c_disease/c_all);
				 sum/=100;
				 System.out.println("Measles probability calculated ="+sum);
				 return sum;
				 }
		
		public float prob_pollen(int a[])
		{
			float c_all = 0,c_disease = 0;
			float data[]=new float[10];
			con=this.getConnection();
			String sql=null;
			  sql="select * from probability_pollen";
			  try {
				  stmtSQL = con.createStatement();
			      ResultSet rs = stmtSQL.executeQuery(sql);
			      rs.next();
			      for(int i=0;i<10;i++)
			      {
			    	  data[i]=rs.getFloat(i+1);
			      }

					rs.close();
					stmtSQL.close();
			  }
			  catch(Exception e)
			  {}
			  float sum=0 ;
				 
				 //to count numerator
				 for(int i=0;i<10;i++)
				 {
					 if(a[i]==0)
						 sum+=(1-data[i]);
					 else
						 sum+=data[i];
				 }
				  
				 //to count no of patients for pollen
				  sql="select count(*) from pollen";
				  try {
					  stmtSQL = con.createStatement();
				      ResultSet rs = stmtSQL.executeQuery(sql);
				      rs.next();
				      c_disease=rs.getInt(1);		 
				      rs.close();
				      stmtSQL.close();
				  }
				  catch(Exception e)
				  {}
				  
				  //to count all cases (total)
				  sql="select sum(a) as total from count_all";
				  try {
					  stmtSQL = con.createStatement();
				      ResultSet rs = stmtSQL.executeQuery(sql);
				      rs.next();
				      c_all=rs.getInt(1);		 
				      rs.close();
				      stmtSQL.close();
				  }
				  catch(Exception e)
				  {}
				  
				 //final result
				 sum=sum/(c_disease/c_all);
				 sum/=100;
				 System.out.println("Pollen probability calculated ="+sum);
				 return sum;
		}
		
		public void identifyDisease()
		{
			//validating the input from boolean to int
			int a[]=new int[10];
			a[0]=chckbx1.isSelected() ? 1 : 0;
			a[1]=chckbx2.isSelected() ? 1 : 0;
			a[2]=chckbx3.isSelected() ? 1 : 0;
			a[3]=chckbx4.isSelected() ? 1 : 0;
			a[4]=chckbx5.isSelected() ? 1 : 0;
			a[5]=chckbx6.isSelected() ? 1 : 0;
			a[6]=chckbx7.isSelected() ? 1 : 0;
			a[7]=chckbx8.isSelected() ? 1 : 0;
			a[8]=chckbx9.isSelected() ? 1 : 0;
			a[9]=chckbx10.isSelected() ? 1 : 0;
			
			
			float p_appendix=prob_appendix(a);
			float p_dengue=prob_dengue(a);
			float p_influenza=prob_influenza(a);
			float p_measles=prob_measles(a);
			float p_pollen=prob_pollen(a);
			
			//find max of 5
			float r1=Math.max(p_appendix, p_dengue);
			float r2=Math.max(p_influenza, p_measles);
			float r3=Math.max(r1, r2);
			float r4=Math.max(p_pollen, r3);
			
			//find the disease
			if(r4-p_appendix==0)
				disease="appendix";
			else if(r4-p_dengue==0)
				disease="dengue";
			else if(r4-p_influenza==0)
				disease="influenza";
			else if(r4-p_measles==0)
				disease="measles";
			else if(r4-p_pollen==0)
				disease="pollen";
			else{}
			
			//ans output
			System.out.println("The Disease which you might have is "+disease);
			
			//update database
			sqlFinal="insert into "+disease+" values("
					+a[0]+","
					+a[1]+","
					+a[2]+","
					+a[3]+","
					+a[4]+","
					+a[5]+","
					+a[6]+","
					+a[7]+","
					+a[8]+","
					+a[9]+")";
			JOptionPane.showMessageDialog(null,"The Disease which you might have is "+disease );		
			System.out.println(sqlFinal);
			JOptionPane.showMessageDialog(null,"Please Update the database if it is valid input");
			btnUpdate.setEnabled(true);
			System.out.println();
			System.out.println();
		}
		
		
		public void UpdateDatabase()
		{
			if(sqlFinal==null)
				btnUpdate.setEnabled(false);
			
			try {
				con=this.getConnection();
				stmtSQL=con.createStatement();
		//		stmtSQL.executeUpdate(sqlFinal);
				stmtSQL.close();
				con.close();
				JOptionPane.showMessageDialog(null,"Data Successfully inserted into database for record");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			btnUpdate.setEnabled(false);
			btnClear.doClick();
			sqlFinal=null;			
		}
		
		
	public static void main(String args[]) throws Exception
	{
		medical_diag A=new medical_diag();
	}
}