package collections;

public class AiDependency {
	private int dependencyId;
	private int studentId;
	private Double aiDependencyScore;
	
	public AiDependency(int dependencyId, int studentId, Double aiDependencyScore) {
		super();
		this.dependencyId = dependencyId;
		this.studentId = studentId;
		this.aiDependencyScore = aiDependencyScore;
	}

	public int getDependencyId() {
		return dependencyId;
	}

	public void setDependencyId(int dependencyId) {
		this.dependencyId = dependencyId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public Double getAiDependencyScore() {
		return aiDependencyScore;
	}

	public void setAiDependencyScore(Double aiDependencyScore) {
		this.aiDependencyScore = aiDependencyScore;
	}
	
	
}
