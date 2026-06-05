package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import connection.MongoConnection;

public class CsvImportService extends Thread {

    private static final String DATASETS_PATH = "projeto_tabd/assets/datasets/";

    private final String fileName;
    private final MongoDatabase db = MongoConnection.getDatabase();
    private final MongoCollection<Document> students = db.getCollection("students");
    private final MongoCollection<Document> burnouts = db.getCollection("burnout");
    private final MongoCollection<Document> aiDependencies = db.getCollection("aiDependency");

    public CsvImportService(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        importFromCsv();
    }

    public void importFromCsv() {
        Path filePath = Paths.get(DATASETS_PATH, fileName);

        if (!Files.exists(filePath)) {
            System.out.println("Arquivo nao encontrado: " + filePath);
            return;
        }

        List<Document> studentDocs = new ArrayList<>();
        List<Document> burnoutDocs = new ArrayList<>();
        List<Document> aiDependencyDocs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String header = reader.readLine();
            if (header == null) {
                System.out.println("Arquivo CSV vazio.");
                return;
            }

            String[] columns = parseLine(header);
            int[] columnIndex = mapColumns(columns);

            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;

                try {
                    String[] values = parseLine(line);
                    int studentId = lineNumber - 1;

                    studentDocs.add(buildStudentDocument(studentId, values, columnIndex));
                    burnoutDocs.add(buildBurnoutDocument(studentId, values, columnIndex));
                    aiDependencyDocs.add(buildAiDependencyDocument(studentId, values, columnIndex));

                    if (studentDocs.size() >= 500) {
                        flushBatch(studentDocs, burnoutDocs, aiDependencyDocs);
                    }
                } catch (Exception e) {
                    System.out.println("Erro na linha " + lineNumber + ": " + e.getMessage());
                }
            }

            if (!studentDocs.isEmpty()) {
                flushBatch(studentDocs, burnoutDocs, aiDependencyDocs);
            }

            System.out.println("Importacao concluida. Total de registros: " + (lineNumber - 1));

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo CSV: " + e.getMessage());
        }
    }

    private void flushBatch(List<Document> studentDocs, List<Document> burnoutDocs, List<Document> aiDependencyDocs) {
        students.insertMany(new ArrayList<>(studentDocs));
        burnouts.insertMany(new ArrayList<>(burnoutDocs));
        aiDependencies.insertMany(new ArrayList<>(aiDependencyDocs));

        studentDocs.clear();
        burnoutDocs.clear();
        aiDependencyDocs.clear();
    }

    private Document buildStudentDocument(int studentId, String[] values, int[] idx) {
        Document doc = new Document();
        doc.append("studentId", studentId);
        doc.append("age", parseIntSafe(values, idx[0]));
        doc.append("gender", getValueSafe(values, idx[1]));
        doc.append("urbanOrRural", getValueSafe(values, idx[2]));
        return doc;
    }

    private Document buildBurnoutDocument(int studentId, String[] values, int[] idx) {
        Document doc = new Document();
        doc.append("burnoutId", studentId);
        doc.append("studentId", studentId);
        doc.append("burnoutScore", parseDoubleSafe(values, idx[3]));
        return doc;
    }

    private Document buildAiDependencyDocument(int studentId, String[] values, int[] idx) {
        Document doc = new Document();
        doc.append("dependencyId", studentId);
        doc.append("studentId", studentId);
        doc.append("aiDependencyScore", parseDoubleSafe(values, idx[4]));
        return doc;
    }

    private int[] mapColumns(String[] headers) {
        int ageIdx = -1;
        int genderIdx = -1;
        int urbanRuralIdx = -1;
        int burnoutIdx = -1;
        int aiDepIdx = -1;

        for (int i = 0; i < headers.length; i++) {
            String col = headers[i].trim().toLowerCase().replace("_", "").replace(" ", "");

            if (col.contains("age")) ageIdx = i;
            else if (col.contains("gender")) genderIdx = i;
            else if (col.contains("urban") || col.contains("rural") || col.contains("location")) urbanRuralIdx = i;
            else if (col.contains("burnout")) burnoutIdx = i;
            else if (col.contains("aidep") || col.contains("dependency")) aiDepIdx = i;
        }

        return new int[]{ageIdx, genderIdx, urbanRuralIdx, burnoutIdx, aiDepIdx};
    }

    private String[] parseLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        fields.add(current.toString().trim());

        return fields.toArray(new String[0]);
    }

    private String getValueSafe(String[] values, int index) {
        if (index < 0 || index >= values.length) return "";
        return values[index].trim();
    }

    private int parseIntSafe(String[] values, int index) {
        String value = getValueSafe(values, index);
        if (value.isEmpty()) return 0;
        return (int) Double.parseDouble(value);
    }

    private double parseDoubleSafe(String[] values, int index) {
        String value = getValueSafe(values, index);
        if (value.isEmpty()) return 0.0;
        return Double.parseDouble(value);
    }
}
