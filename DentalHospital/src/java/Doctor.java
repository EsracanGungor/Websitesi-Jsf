
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean ( name="doctorBean" )
@SessionScoped

public class Doctor {
    
private String id;
private String name;
private String surname;
private String profession;
private String appointment_number;
private String bulunacak_doktor;
private String guncellenecek_no;
private String silinecek_id;

   
        public String getBulunacak_doktor() {
            return bulunacak_doktor;
        }

        public void setBulunacak_doktor(String bulunacak_doktor) {
            this.bulunacak_doktor = bulunacak_doktor;
        }

CachedRowSet rowSet=null;

   DataSource dataSource;
    public Doctor() {
       
            try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("jdbc/dentalhospital1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
        
    }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getAppointment_number() {
            return appointment_number;
        }

        public void setAppointment_number(String appointment_number) {
            this.appointment_number = appointment_number;
        }

        public String getSilinecek_id() {
            return silinecek_id;
        }

        public void setSilinecek_id(String silinecek_id) {
            this.silinecek_id = silinecek_id;
        }

    public String getGuncellenecek_no() {
        return guncellenecek_no;
    }

    public void setGuncellenecek_no(String guncellenecek_no) {
        this.guncellenecek_no = guncellenecek_no;
    }


 public ResultSet bul() throws SQLException
 {
 if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );

 try
 {
 PreparedStatement ps =
 connection.prepareStatement( "SELECT * FROM DENTISTS WHERE DENTISTS.NAME=?" );
 ps.setString( 1, getBulunacak_doktor() );
 rowSet = new com.sun.rowset.CachedRowSetImpl();
 rowSet.populate( ps.executeQuery() );
return rowSet;
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
 PreparedStatement myObject =
 connection.prepareStatement( "DELETE FROM DENTISTS WHERE DENTIS_ID=?");

 myObject.setInt( 1, Integer.parseInt(getSilinecek_id()) );
 
myObject.executeUpdate(); 
 return "index"; 
 } 
 finally
 {
 connection.close();
 } 
 } 

public String guncelle() throws SQLException
 {
 if ( dataSource == null ){ 
      throw new SQLException( "Unable to obtain DataSource" );
 }

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );
  

 try
 {  

 PreparedStatement myObject =
 connection.prepareStatement( "UPDATE DENTISTS SET APPOINTMENT_NUMBER=? WHERE DENTIS_ID=?");

myObject.setInt(1,36);
myObject.setInt(2, 6);

 myObject.executeUpdate(); 
   
 return "index";
 }
 finally
 {
 connection.close();
 } 
 } 


}    

    
