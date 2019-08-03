


 
	import javax.annotation.PostConstruct;	   
	import javax.faces.bean.ManagedBean;	   
	import javax.faces.bean.RequestScoped;	 
     
	@ManagedBean	   
	@RequestScoped	   
	public class FormValidationBean {	   
	private String name;	   
	private String surname;	   
		   
	private String email;
        private String phoneNo;	   

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
	   
	   
	private String total;	   
	   	   
	    public String getTotal() {	   
	        return total;	   
	    }	   
	
	   
	    public void setTotal(String total) {	   
	        this.total = total;	   
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
	
	    public String getEmail() {	   
	        return email;	   
	    }	   
	
	   
	    public void setEmail(String email) {	   
	        this.email = email;	   
	    }	   
	
	}	 
	
