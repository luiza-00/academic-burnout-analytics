package services;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import collections.Burnout;
import connection.MongoConnection;

public class BurnoutService {

    MongoDatabase db = MongoConnection.getDatabase();

    MongoCollection<Document> burnouts =
            db.getCollection("burnout");

    MongoCollection<Document> students =
            db.getCollection("students");

    public void registerBurnout(Burnout burnout) {

        Document student =
                students.find(
                        eq("studentId",
                        burnout.getStudentId()))
                        .first();

        if (student == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        Document doc = new Document();

        doc.append("burnoutId", burnout.getBurnoutId());
        doc.append("studentId", burnout.getStudentId());
        doc.append("burnoutScore", burnout.getBurnoutScore());

        burnouts.insertOne(doc);

        System.out.println("Score de Burnout cadastrado!");
    }

    public void findBurnout(int burnoutId) {

        Document burnout =
                burnouts.find(
                        eq("burnoutId", burnoutId))
                        .first();

        if (burnout != null) {
            System.out.println(burnout.toJson());
        } else {
            System.out.println("Registro não encontrado!");
        }
    }

    public void updateBurnout(Burnout burnout) {

        Document existingBurnout =
                burnouts.find(
                        eq("burnoutId",
                        burnout.getBurnoutId()))
                        .first();

        if (existingBurnout == null) {
            System.out.println("Registro não encontrado!");
            return;
        }

        Document updatedData = new Document();

        updatedData.append(
                "burnoutScore",
                burnout.getBurnoutScore());

        burnouts.updateOne(
                eq("burnoutId",
                burnout.getBurnoutId()),
                new Document("$set", updatedData));

        System.out.println(
                "Score de Burnout atualizado!");
    }

    public void deleteBurnout(int burnoutId) {

        Document burnout =
                burnouts.find(
                        eq("burnoutId", burnoutId))
                        .first();

        if (burnout == null) {
            System.out.println("Registro não encontrado!");
            return;
        }

        burnouts.deleteOne(
                eq("burnoutId", burnoutId));

        System.out.println(
                "Registro de Burnout removido!");
    }

    public void listBurnouts() {

        for (Document burnout : burnouts.find()) {
            System.out.println(
                    burnout.toJson());
        }
    }
}