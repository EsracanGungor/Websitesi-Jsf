import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;


@ManagedBean(name = "user")
@RequestScoped

public class User {
       
public String goRegisterPage(){
return "register.xhtml";}

    private String message,idNumber,name,surname,gender,cellphone, birthdate;
    boolean checkIt;
    private String password;
    private String dbIdNumber;
    private String dbPassword;
     DataSource ds;

        public User() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/dentalhospital1");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
         /*public User(String id,String pw)
         throws NoSuchAlgorithmException {
    this.idNumber=id;
      this.password = getMD5Hash(pw);
    }

public static String getMD5Hash(String input) {
      StringBuffer sb = new StringBuffer();

      try {
         MessageDigest md =
            MessageDigest.getInstance("MD5");
         md.update(input.getBytes());
          byte[] mdbytes = md.digest();

         //convert the byte to hex format
         for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff)
               + 0x100, 16).substring(1));
         }
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
         System.exit(-1);
      }
      return sb.toString();
   }*/
    public String getDbIdNumber() {
        return dbIdNumber;
    }

    public String getDbPassword() {
        return dbPassword;
    }

      public String getSayi(){
    String sayi1;
    Random rnd = new Random();
    int sayi = rnd.nextInt(9);
    sayi1=Integer.toString(sayi);
    return sayi1;
}
public String getPassword() {
    return this.password;
 }

public void setPassword(String pw) {
    this.password = pw;
}

public String getBirthdate() {
         return  birthdate;
}

public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
}

public String getIdNumber() {
        return idNumber;
}

public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

public String getGender() {
        return gender;
}

public void setGender(String gender) {
        this.gender = gender;
}

public String getCellphone() {
        return cellphone;
}

public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
}

public boolean getCheckIt(){
          return checkIt;
}

public void setCheckIt(boolean checkIt){
         this.checkIt=checkIt;
}
   
public String getMessage(){
     if(!checkIt){
      return  message="You did not agree to the terms!";
     }
     else{
       return  message="You agree to the terms";
    }
}

public java.sql.Date tipcevir(String tarih) throws ParseException{
 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
 java.util.Date date = sdf1.parse(tarih);
 java.sql.Date sqlDate = new java.sql.Date(date.getTime());
 return sqlDate;
 }

public String add(){
      int i = 0;
 if ( idNumber!= null ){
   PreparedStatement ps = null;
   Connection con = null;
 try
 {
     if ( ds != null ){
  con = ds.getConnection();
     if (con != null) {
      String sql = "INSERT INTO PATIENTS (IDENTIFICATION_NUMBER,PATIENT_NAME,PATIENT_SURNAME,BIRTHDATE,PASSWORD,DENTIST_ID)"
              +"VALUES(?,?,?,?,?,?)";
                        ps = con.prepareStatement(sql);
                        ps.setString(1, idNumber);
                        ps.setString(2, name);
                         ps.setString(3, surname);
                        ps.setDate(4,tipcevir(getBirthdate()) );
                        ps.setString(5,password );
                        ps.setString(6, getSayi());
                        i = ps.executeUpdate();
                        System.out.println("Data Added Successfully");
                    }
 }
 }catch (Exception e) {
                System.out.println(e); 
         }finally {
     try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
         }
 }}
  if (i > 0) {
            return "success";
        } else
            return "unsuccess";
 }

       public void dbData(String uName) {
        if (uName != null) {
            PreparedStatement ps = null;
            Connection con = null;
            ResultSet rs = null;

            if (ds != null) {
                try {
                    con = ds.getConnection();
                    if (con != null) {
                        String sql = "SELECT IDENTIFICATION_NUMBER,PASSWORD FROM PATIENTS WHERE IDENTIFICATION_NUMBER = '"+ uName +"'";
                        ps = con.prepareStatement(sql);
                        rs = ps.executeQuery();
                        rs.next();
                        dbIdNumber = rs.getString("IDENTIFICATION_NUMBER");
                        dbPassword = rs.getString("PASSWORD");
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

      
       public String login() {
        dbData(idNumber);
        if (idNumber.equals(dbIdNumber) && password.equals(dbPassword)) {
            return "output";
        } else
            return "invalid";
    }
     
 }       
