package GUI;

public class DAO {
	String id;
	String pw;
	String name;
	
	public void setName(String name) {
		this.name = name;
		System.out.println("세터 받아와짐 " + this.name);
	}
	
	public String getName() {
		return this.name;
	}
}
