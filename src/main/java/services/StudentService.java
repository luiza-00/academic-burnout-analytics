package services;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

import collections.Student;
import connection.MongoConnection;

public class StudentService {
	
	MongoDatabase db = MongoConnection.getDatabase();

    MongoCollection<Document> students =
            db.getCollection("students");
    
    public void registerStudent(Student student) {

        Document doc = new Document();

        doc.append("studentId", student.getStudentId());
        doc.append("age", student.getAge());
        doc.append("gender", student.getGender());
        doc.append("urbanOrRural", student.getUrbanOrRural());

        students.insertOne(doc);

        System.out.println("Aluno cadastrado!");
    }
    
    public void findStudent(int studentId) {

        Document student = students.find(eq("studentId", studentId))
                        .first();

        if (student != null) {
            System.out.println(student.toJson());
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }
    
    public void updateStudent(Student student) {

        Document updatedData = new Document();

        updatedData.append("age", student.getAge());
        updatedData.append("gender", student.getGender());
        updatedData.append("urbanOrRural", student.getUrbanOrRural());

        students.updateOne(
                eq("studentId", student.getStudentId()),
                new Document("$set", updatedData));

        System.out.println("Aluno atualizado!");
    }
    
    public void deleteStudent(int studentId) {

        students.deleteOne(
                eq("studentId", studentId));

        System.out.println("Aluno removido!");
    }
    
    
}