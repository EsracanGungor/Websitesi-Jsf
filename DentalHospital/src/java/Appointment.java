import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean ( name="appointment" )
@SessionScoped
public class Appointment {
    
private String patient_name;
private String patient_surname;
private String date;
private String branch;
private String doctor_id;
private String country;
private String treatment;
private String silinecek_id;

CachedRowSet rowSet=null;

   DataSource dataSource;
   public Appointment() {
       
            try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("jdbc/dentalhospital1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
        
    }
   

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_surname() {
        return patient_surname;
    }

    public void setPatient_surname(String patient_surname) {
        this.patient_surname = patient_surname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDoctor_id() {
        String sayi1;
    Random rnd = new Random();
    int sayi = rnd.nextInt(8);
    sayi1=Integer.toString(sayi);
    return sayi1;
          }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
 public String getSilinecek_id()
 {
 return silinecek_id;
 } 
 public void setSilinecek_id( String silinecek_id )
 {
 this.silinecek_id = silinecek_id;
 }

    public java.sql.Date tipcevir(String tarih) throws ParseException{
 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
 java.util.Date date = sdf1.parse(tarih);
 java.sql.Date sqlDate = new java.sql.Date(date.getTime());
 return sqlDate;
 }
 
 public String ekle() throws SQLException, ParseException
 {

 if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );

 try
 {
 PreparedStatement addEntry =
 connection.prepareStatement( "INSERT INTO APPOINTMENTS" +
 "(PATIENT_NAME,PATIENT_SURNAME,DATE,BRANCH,DENTIST_ID)" +
 "VALUES ( ?, ?, ?, ?,? )" );
 
 addEntry.setString( 1, getPatient_name());
 addEntry.setString( 2, getPatient_surname() );
 addEntry.setDate( 3, tipcevir(getDate()) );
 addEntry.setString(4,getBranch() );
  addEntry.setInt( 5, Integer.parseInt(getDoctor_id()) );


addEntry.executeUpdate(); 
 return "index"; 
 } 
 finally
 {
 connection.close(); 
 }
 } 


 public String sil() throws SQLException
 {
 if ( dataSource == null ){ 
      throw new SQLException( "Unable to obtain DataSource" );
 }
 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );

 try
 {
 // create a PreparedStatement to insert a new address book entry
 PreparedStatement myObject =
 connection.prepareStatement( "delete from appointments where id=?");

 // specify the PreparedStatement's arguments
 myObject.setInt( 1, Integer.parseInt(getSilinecek_id()) );
 
myObject.executeUpdate(); // insert the entry
 return "login"; // go back to index.xhtml page
 } // end try
 finally
 {
 connection.close(); // return this connection to pool
 } // end finally
 } 

}




