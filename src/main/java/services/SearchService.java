package services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import collections.Student;
import collections.AiDependency;
import collections.Burnout;
import connection.MongoConnection;

public class SearchService {

    private MongoDatabase db = MongoConnection.getDatabase();
    private MongoCollection<Document> studentsCollection = db.getCollection("students");
    private MongoCollection<Document> aiDscoreCollection = db.getCollection("aiDscore");
    private MongoCollection<Document> burnoutCollection = db.getCollection("burnout");

    private List<Student> carregarEstudantes() {
        List<Student> lista = new ArrayList<>();
        for (Document doc : studentsCollection.find()) {
            lista.add(new Student(
                doc.getInteger("studentId"),
                doc.getInteger("age"),
                doc.getString("gender"),
                doc.getString("urbanOrRural")
            ));
        }
        return lista;
    }

    private List<AiDependency> carregarDependencias() {
        List<AiDependency> lista = new ArrayList<>();
        for (Document doc : aiDscoreCollection.find()) {
            lista.add(new AiDependency(
                doc.getInteger("dependencyId"),
                doc.getInteger("studentId"),
                doc.getDouble("aiDependencyScore")
            ));
        }
        return lista;
    }

    private List<Burnout> carregarBurnouts() {
        List<Burnout> lista = new ArrayList<>();
        for (Document doc : burnoutCollection.find()) {
            lista.add(new Burnout(
                doc.getInteger("burnoutId"),
                doc.getInteger("studentId"),
                doc.getDouble("burnoutScore")
            ));
        }
        return lista;
    }

    public List<Student> pesquisarAvancado(Integer idade, String genero, String zona, Double minAiDependency, Double minBurnout) {
        
        List<Student> listaEstudantes = carregarEstudantes();
        List<AiDependency> listaDependencias = carregarDependencias();
        List<Burnout> listaBurnouts = carregarBurnouts();

        return listaEstudantes.stream()
            .filter(st -> (idade == null || st.getAge() == idade))
            .filter(st -> (genero == null || st.getGender().equalsIgnoreCase(genero)))
            .filter(st -> (zona == null || st.getUrbanOrRural().equalsIgnoreCase(zona)))
            
            .filter(st -> {
                if (minAiDependency == null) return true;
                return listaDependencias.stream()
                    .anyMatch(dep -> dep.getStudentId() == st.getStudentId() 
                                    && dep.getAiDependencyScore() >= minAiDependency);
            })
            
            .filter(st -> {
                if (minBurnout == null) return true;
                return listaBurnouts.stream()
                    .anyMatch(burn -> burn.getStudentId() == st.getStudentId() 
                                    && burn.getBurnoutScore() >= minBurnout);
            })
            .collect(Collectors.toList());
    }
}
