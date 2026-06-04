package collections;

public class Burnout {
	private int burnoutId;
	private int studentId;
	private double burnoutScore;
	
	public Burnout(int burnoutId, int studentId, double burnoutScore) {
		super();
		this.burnoutId = burnoutId;
		this.studentId = studentId;
		this.burnoutScore = burnoutScore;
	}

	public int getBurnoutId() {
		return burnoutId;
	}

	public void setBurnoutId(int burnoutId) {
		this.burnoutId = burnoutId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public double getBurnoutScore() {
		return burnoutScore;
	}

	public void setBurnoutScore(double burnoutScore) {
		this.burnoutScore = burnoutScore;
	}
	
	
}
