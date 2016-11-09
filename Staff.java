import java.io.Serializable;

public class Staff implements Serializable{
	
	private String name;
	private int employeeID;
	private String gender;
	private String jobTitle;
	
	public Staff(int employeeID, String name, String gender, String jobTitle) {
		this.employeeID = employeeID;
		this.name = name;
		this.gender = gender;
		this.jobTitle = jobTitle;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public String getJobTitle() {
		return this.jobTitle;
	}
	
	public int getEmployeeID() {
		return this.employeeID;
	}
	
}
