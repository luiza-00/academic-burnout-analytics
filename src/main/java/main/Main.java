package main;

import collections.AiDependency;
import collections.Burnout;
import collections.Student;
import services.AiDependencyService;
import services.BurnoutService;
import services.StudentService;

public class Main {

    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        AiDependencyService dependencyService = new AiDependencyService();
        BurnoutService burnoutService = new BurnoutService();

        System.out.println("=== CADASTRANDO ALUNO ===");

        Student student =
                new Student(
                        100,
                        21,
                        "Female",
                        "Urban");

        studentService.registerStudent(student);

        System.out.println("\n=== BUSCANDO ALUNO ===");

        studentService.findStudent(100);

        System.out.println("\n=== CADASTRANDO DEPENDÊNCIA ===");

        AiDependency dependency =
                new AiDependency(
                        1,
                        100,
                        8.5);

        dependencyService.registerDependency(dependency);

        System.out.println("\n=== BUSCANDO DEPENDÊNCIA ===");

        dependencyService.findAiScore(1);

        System.out.println("\n=== CADASTRANDO BURNOUT ===");

        Burnout burnout =
                new Burnout(
                        1,
                        100,
                        6.7);

        burnoutService.registerBurnout(burnout);

        System.out.println("\n=== BUSCANDO BURNOUT ===");

        burnoutService.findBurnout(1);

        System.out.println("\n=== LISTANDO DEPENDÊNCIAS ===");

        dependencyService.listDependency();

        System.out.println("\n=== LISTANDO BURNOUTS ===");

        burnoutService.listBurnouts();
    }
}