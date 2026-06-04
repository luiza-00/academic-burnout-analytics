package services;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import collections.AiDependency;
import connection.MongoConnection;

public class AiDependencyService {

	MongoDatabase db = MongoConnection.getDatabase();

    MongoCollection<Document> students =
            db.getCollection("students");
    
    MongoCollection<Document> aiDscore =
            db.getCollection("aiDscore");

    public void registerDependency(AiDependency dependency) {

    	Document student =
                students.find(
                        eq("studentId",
                        dependency.getStudentId()))
                        .first();

        if (student == null) {
            System.out.println("Dependencia não encontrada!");
            return;
        }

        Document doc = new Document();

        doc.append("dependencyId", dependency.getDependencyId());
        doc.append("studentId", dependency.getStudentId());
        doc.append("aiDependencyScore", dependency.getAiDependencyScore());

        aiDscore.insertOne(doc);

        System.out.println("Score de dependência cadastrado!");
    }

    public void findAiScore(int dependencyId) {

        Document dep =
        		aiDscore.find(
                        eq("dependencyId", dependencyId))
                        .first();

        if (dep != null) {
            System.out.println(dep.toJson());
        } else {
            System.out.println("Dependencia não encontrada!");
        }
    }

    public void updateDependency(AiDependency dep) {

        Document existingDependency =
                aiDscore.find(
                        eq("dependencyId",
                        		dep.getDependencyId()))
                        .first();

        if (existingDependency == null) {
            System.out.println("Dependencia não encontrada para atualização!");
            return;
        }

        Document updatedData = new Document();

        updatedData.append(
                "aiDependencyScore",
                dep.getAiDependencyScore());

        aiDscore.updateOne(
        		eq("dependencyId",
                		dep.getDependencyId()),
                new Document("$set", updatedData));

        System.out.println(
                "Score de dependencia atualizado!");
    }

    public void deleteScore(int dependencyId) {

        Document dep =
                aiDscore.find(
                        eq("dependencyId", dependencyId))
                        .first();

        if (dep == null) {
            System.out.println("Registro não encontrado!");
            return;
        }

        aiDscore.deleteOne(
                eq("dependencyId", dependencyId));

        System.out.println(
                "Registro de dependencia removido!");
    }

    public void listDependency() {

        for (Document dep : aiDscore.find()) {
            System.out.println(
            		dep.toJson());
        }
    }
}
