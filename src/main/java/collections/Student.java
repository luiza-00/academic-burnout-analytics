package collections;

public class Student {
	
	private int studentId;
	private int age;
	private String gender;
	private String urbanOrRural;
	
	public Student(int studentId, int age, String gender, String urbanOrRural) {
		super();
		this.studentId = studentId;
		this.age = age;
		this.gender = gender;
		this.urbanOrRural = urbanOrRural;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUrbanOrRural() {
		return urbanOrRural;
	}

	public void setUrbanOrRural(String urbanOrRural) {
		this.urbanOrRural = urbanOrRural;
	}
	
}
