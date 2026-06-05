package main;

import java.util.List;
import java.util.Scanner;

import collections.AiDependency;
import collections.Burnout;
import collections.Student;
import services.AiDependencyService;
import services.BurnoutService;
import services.SearchService;
import services.StudentService;
import collections.Metodos;

public class Main {
	
    static Scanner resposta = new Scanner(System.in);
	private static StudentService studentService = new StudentService();
	private static AiDependencyService dependencyService = new AiDependencyService();
	private static BurnoutService burnoutService = new BurnoutService();
	private static SearchService searchService = new SearchService();

    public static void main(String[] args) {
        
        System.out.println("Análise: AI Depndency, Career Anxiety and Student Burnout");
        exibirMenuPrincipal();
    }
    
        public static void exibirMenuPrincipal(){
        	   
            System.out.println("--------------------------------------");
            System.out.println("|   | MENU PRINCIPAL                |");
            System.out.println("| 1 | Alunos                        |");
            System.out.println("| 2 | Dependência de IA             |");
            System.out.println("| 3 | Índices de Burnout            |");
            System.out.println("| 4 | Pesquisa em filtros           |");
            System.out.println("| 5 | Finalizar análise             |");
            System.out.println("--------------------------------------");
            
            int opcao = Metodos.lerInteiro(resposta);
            
            switch (opcao) {
    		case 1:
                menuStudent();
                break;
                
            case 2:
                menuAiDependency();
                break;
                
            case 3:
                menuBurnout();
                break;
                
            case 4:
            	pesquisaFiltro();
                break;
                
            case 5:
                System.out.println("Finalizando a análise!");

                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
    			break;

            }
         }
    
        private static void menuStudent() {
        System.out.println("--------------------------------------");
        System.out.println("|   | ESTUDANTES                    |");
        System.out.println("| 1 | Cadastrar aluno               |");
        System.out.println("| 2 | Localização por ID            |");
        System.out.println("| 3 | Alterar aluno                 |");
        System.out.println("| 4 | Excluir aluno                 |");
        System.out.println("| 5 | Retornar ao menu principal    |");
        System.out.println("--------------------------------------");
        
        int opcao = Metodos.lerInteiro(resposta);
        switch (opcao) {
		case 1:
		
		System.out.println("ID do aluno: ");
		int idAluno = Metodos.lerInteiro(resposta);
		System.out.println("Idade: ");
		int idade = Metodos.lerInteiro(resposta);
		System.out.print("Gênero: ");
        String genero = resposta.nextLine();
        System.out.print("Área urbana ou rural: ");
        String area = resposta.nextLine(); 
		
		Student cadastroAluno = new Student(idAluno, idade, genero, area);
			studentService.registerStudent(cadastroAluno);
			
			System.out.println("Aluno cadastrado.");
			exibirMenuPrincipal();
        break;
        
		case 2:
		System.out.println("Digite o id do aluno que deseja localizar:");
		int localizarID = Metodos.lerInteiro(resposta);
		studentService.findStudent(localizarID);
			exibirMenuPrincipal();
		break;
		
		case 3:
			System.out.println("Digite o id do aluno que deseja alterar:");
			int idAlteracao = Metodos.lerInteiro(resposta);
			System.out.println("Idade: ");
			int idadeAlteracao = Metodos.lerInteiro(resposta);
			System.out.println("Gênero: ");
			String generoAlteracao = resposta.nextLine();
			System.out.println("Área urbana ou rural: ");
			String areaAlteracao = resposta.nextLine(); 
			
			Student alteracaoAluno = new Student(idAlteracao, idadeAlteracao, generoAlteracao, areaAlteracao);
				studentService.updateStudent(alteracaoAluno);
				
				exibirMenuPrincipal();	
			break;
			
		case 4:
			System.out.println("Digite o id do aluno que deseja excluir:");
			int excluirID = Metodos.lerInteiro(resposta);
			studentService.deleteStudent(excluirID);
			
			exibirMenuPrincipal();	
			break;
			
		case 5:
			exibirMenuPrincipal();
			break;
			
		default:
	            System.out.println("Opção inválida! Tente novamente.");
			break;
		}
            }
        
        private static void menuAiDependency() {
        	
        System.out.println("--------------------------------------");
        System.out.println("|   | DEPENDENCIA DE IA             |");
        System.out.println("| 1 | Cadastrar score dependência   |");
        System.out.println("| 2 | Localização por ID            |");
        System.out.println("| 3 | Alterar score dependência     |");
        System.out.println("| 4 | Excluir dependência           |");
        System.out.println("| 5 | Listar as dependências        |");
        System.out.println("| 6 | Retornar ao menu principal    |");
        System.out.println("--------------------------------------");
        
        int opcao = Metodos.lerInteiro(resposta);
        	
        switch (opcao) {
		case 1:
			
			System.out.println("ID da dependência: ");
			int idDependencia = Metodos.lerInteiro(resposta);
			System.out.println("ID do aluno: ");
			int idAluno = Metodos.lerInteiro(resposta);
			System.out.println("Score da dependência: ");
			Double score = Metodos.lerDouble(resposta);
			
			AiDependency cadastroDependencia = new AiDependency(idDependencia, idAluno, score);
			dependencyService.registerDependency(cadastroDependencia);
			
			exibirMenuPrincipal();
			break;
			
		case 2:
			System.out.println("Digite o id da dependência que deseja localizar:");
			int localizarID = Metodos.lerInteiro(resposta);
			dependencyService.findAiScore(localizarID);
			
			exibirMenuPrincipal();
			break;
			
		case 3:
			System.out.println("Digite o id do dependência que deseja alterar o score:");
			int idAlteracao = Metodos.lerInteiro(resposta);
			System.out.println("Score: ");
			double scoreAlteracao = Metodos.lerDouble(resposta);
			
			AiDependency alteracaoDependencia = new AiDependency(idAlteracao, 0, scoreAlteracao);
			dependencyService.updateDependency(alteracaoDependencia);
			
			exibirMenuPrincipal();
			break;
			
		case 4:
			System.out.println("Digite o id da dependência que deseja excluir:");
			int excluirID = Metodos.lerInteiro(resposta);
			dependencyService.deleteScore(excluirID);
			break;
			
		case 5:
			System.out.println("LISTA DE DEPENDÊNCIAS:");
			dependencyService.listDependency();
			break;
			
		case 6:
			exibirMenuPrincipal();
			break;
			
		default:
	            System.out.println("Opção inválida! Tente novamente.");
			break;
		}
            }
     
        private static void menuBurnout() {

        System.out.println("--------------------------------------");
        System.out.println("|   | BURNOUT                       |");
        System.out.println("| 1 | Cadastrar score burnout       |");
        System.out.println("| 2 | Localização por ID            |");
        System.out.println("| 3 | Alterar score burnout         |");
        System.out.println("| 4 | Excluir burnout               |");
        System.out.println("| 5 | Listar os burnouts            |");
        System.out.println("| 6 | Retornar ao menu principal    |");
        System.out.println("--------------------------------------");
        
        int opcao = Metodos.lerInteiro(resposta);
        
        switch (opcao) {
		case 1:
			System.out.print("ID do Registro de Burnout: ");
            int idBurnout = Metodos.lerInteiro(resposta);
            System.out.print("ID do Aluno: ");
            int idAluno = Metodos.lerInteiro(resposta);
            System.out.print("Score de Burnout: ");
            double score = Metodos.lerDouble(resposta);

            Burnout cadastroBurnout = new Burnout(idBurnout, idAluno, score);
            burnoutService.registerBurnout(cadastroBurnout);
            
            exibirMenuPrincipal();
            break;
            
		case 2:
			System.out.println("Digite o id do burnout que deseja localizar: ");
			int localizarID = Metodos.lerInteiro(resposta);
			burnoutService.findBurnout(localizarID);
			
			exibirMenuPrincipal();
			break;
			
		case 3:
			System.out.println("Digite o id do burnout que deseja alterar o score: ");
			int idAlteracao = Metodos.lerInteiro(resposta);
			System.out.println("Score: ");
			double scoreAlteracao = Metodos.lerDouble(resposta);
			
			Burnout alteracaoDependencia = new Burnout(idAlteracao, 0, scoreAlteracao);
			burnoutService.updateBurnout(alteracaoDependencia);
			
			exibirMenuPrincipal();
			break;
			
		case 4:
			System.out.println("Digite o id do burnout que deseja excluir:");
			int excluirID = Metodos.lerInteiro(resposta);
			burnoutService.deleteBurnout(excluirID);
			
			exibirMenuPrincipal();
			break;
			
		case 5:
			System.out.println("LISTA DE BURNOUTS:");
			burnoutService.listBurnouts();
			
			exibirMenuPrincipal();
			break;
			
		case 6:
			exibirMenuPrincipal();
			break;
			
		default:
	            System.out.println("Opção inválida! Tente novamente.");
			break;
		}
            }

        private static void pesquisaFiltro() {
        System.out.println("---------------------------------------");
        System.out.println("Pressione o enter para ignorar o filtro");
      
        System.out.print("Filtrar por Idade: ");
        int idadeFiltro = Metodos.lerInteiro(resposta);
        Integer idade = (idadeFiltro == 0) ? null : idadeFiltro;
     
        System.out.print("Filtrar por Gênero: ");
        String generoInput = resposta.nextLine();
        String genero = generoInput.trim().isEmpty() ? null : generoInput;

        System.out.print("Filtrar por Zona - Urban/Rural: ");
        String areaInput = resposta.nextLine();
        String area = areaInput.trim().isEmpty() ? null : areaInput;
       
        System.out.print("Minímo Score de Dependência de IA (ou 0 para ignorar): ");
        double inputDependencia = Metodos.lerDouble(resposta);
        Double dadosDependencia = (inputDependencia == 0.0) ? null : inputDependencia;
        
        System.out.print("Minímo Score de Burnout (ou 0 para ignorar): ");
        double inputBurnout = Metodos.lerDouble(resposta);
        Double dadosBurnout = (inputBurnout == 0.0) ? null : inputBurnout;
        
        System.out.println("Combinação de resultados:");
        List<Student> resultado = searchService.pesquisarAvancado(idade, genero, area, dadosDependencia, dadosBurnout);
        if (resultado.isEmpty()) {
            System.out.println("Nenhum estudante corresponde aos filtros aplicados.");
        } else {
            System.out.println("Estudantes Encontrados (" + resultado.size() + "):");
            for (Student aluno : resultado) {
            System.out.printf("ID: %d | Idade: %d | Gênero: %s | Zona: %s%n", 
            aluno.getStudentId(), aluno.getAge(), aluno.getGender(), aluno.getUrbanOrRural());
            }
        } exibirMenuPrincipal();
            } 
        }
        
