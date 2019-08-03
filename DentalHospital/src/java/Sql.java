
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean ( name="sqlBean" )
@RequestScoped

public class Sql {
    DataSource ds;
 Connection con;
        public Sql() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/dentalhospital1");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    public ResultSet enBuyukTarih() throws SQLException
    {
     if ( con == null ){
 throw new SQLException( "Unable to connect to DataSource" );
     }
     try{
    PreparedStatement ps;
        ps = con.prepareStatement("SELECT DATE FROM APPOINTMENTS WHERE DATE=(SELECT MAX(DATE)  FROM APPOINTMENTS)");
CachedRowSet rs=new com.sun.rowset.CachedRowSetImpl();
rs.populate(ps.executeQuery());
return rs;
     }finally{
         con.close();
     }
  
     
     }
    
    public ResultSet doktorHasta() throws SQLException
    {
     if ( con == null ){
 throw new SQLException( "Unable to connect to DataSource" );
     }
     try{
    PreparedStatement ps;
        ps = con.prepareStatement("SELECT K.PATIENT_NAME,K.PATIENT_SURNAME,Y.NAME,Y.SURNAME FROM APPOINTMENTS AS K" +
"INNER JOIN DENTISTS AS Y ON Y.DENTIS_ID=K.DENTIS_ID");
CachedRowSet rs=new com.sun.rowset.CachedRowSetImpl();
rs.populate(ps.executeQuery());
return rs;
     }finally{
         con.close();
     }
  
     
     }  
    
    public ResultSet istGazi() throws SQLException
    {
     if ( con == null ){
 throw new SQLException( "Unable to connect to DataSource" );
     }
     try{
    PreparedStatement ps;
        ps = con.prepareStatement("SELECT PATIENT_NAME,PATIENT_SURNAME,BRANCH FROM APPOINTMENTS" +
"WHERE DENTIST_ID IN (SELECT DENTIS_ID FROM DENTISTS WHERE BRANCH IN('ISTANBUL','GAZIANTEP'))");
CachedRowSet rs=new com.sun.rowset.CachedRowSetImpl();
rs.populate(ps.executeQuery());
return rs;
     }finally{
         con.close();
     }
  
     
     }    

public ResultSet ort() throws SQLException
    {
     if ( con == null ){
 throw new SQLException( "Unable to connect to DataSource" );
     }
     try{
    PreparedStatement ps;
        ps = con.prepareStatement("SELECT NAME,SURNAME,APPOINTMENT_NUMBER FROM DENTISTS" +
"WHERE APPOINTMENT_NUMBER >= (SELECT AVG(APPOINTMENT_NUMBER) FROM DENTISTS)");
CachedRowSet rs=new com.sun.rowset.CachedRowSetImpl();
rs.populate(ps.executeQuery());
return rs;
     }finally{
         con.close();
     }
  
     
     }    

public ResultSet patient() throws SQLException
    {
     if ( con == null ){
 throw new SQLException( "Unable to connect to DataSource" );
     }
     try{
    PreparedStatement ps;
        ps = con.prepareStatement("select PATIENTS.PATIENT_NAME,PATIENTS.PATIENT_SURNAME, TotalAppointments from PATIENTS inner join ( select DENTIST_ID, count (DENTIST_ID) as TotalAppointments from APPOINTMENTS group by DENTIST_ID) as aratablo on PATIENTS.DENTIST_ID=aratablo.DENTIST_ID");
CachedRowSet rs=new com.sun.rowset.CachedRowSetImpl();
rs.populate(ps.executeQuery());
return rs;
     }finally{
         con.close();
     }
  
     
     }    
}

    
    

