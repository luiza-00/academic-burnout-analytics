package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DatasetDownloadService extends Thread {

    private static final String ENV_PATH = "projeto_tabd/assets/.env";
    private static final String DATASETS_PATH = "projeto_tabd/assets/datasets";

    private final String dataset;

    public DatasetDownloadService(String dataset) {
        this.dataset = dataset;
    }

    @Override
    public void run() {
        fetchDataset();
    }

    public void fetchDataset() {
        Map<String, String> env = loadEnv();

        String username = env.get("KAGGLE_USERNAME");
        String apiToken = env.get("KAGGLE_API_TOKEN");

        try {
            executeCommand("pip", "install", "kaggle", "--quiet");

            try {
                executeCommand("cmd.exe", "/c", "md", "%USERPROFILE%\\.kaggle");
            } catch (IOException e) {
                System.out.println("Diretorio .kaggle ja existe, continuando...");
            }

            String kaggleJson = "{\"username\":\"" + username + "\",\"key\":\"" + apiToken + "\"}";
            executeCommand("cmd.exe", "/c",
                    "echo " + kaggleJson + " > %USERPROFILE%\\.kaggle\\kaggle.json");

            int exitCode = executeCommandWithExitCode("kaggle", "datasets", "download",
                    "-d", dataset, "--path", DATASETS_PATH, "--unzip");

            if (exitCode == 0) {
                System.out.println("Dataset baixado e descompactado com sucesso!");
            } else {
                System.err.println("Erro ao baixar o dataset. Codigo de saida: " + exitCode);
            }
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro durante execucao: " + e.getMessage());
        }
    }

    private void executeCommand(String... command) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.inheritIO();
        Process process = pb.start();
        process.waitFor();
        process.destroy();
    }

    private int executeCommandWithExitCode(String... command) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.inheritIO();
        Process process = pb.start();
        int exitCode = process.waitFor();
        process.destroy();
        return exitCode;
    }

    private Map<String, String> loadEnv() {
        Map<String, String> env = new HashMap<>();
        Path path = Paths.get(ENV_PATH);

        if (!Files.exists(path)) {
            System.err.println("Arquivo .env nao encontrado: " + ENV_PATH);
            return env;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                int separator = line.indexOf('=');
                if (separator > 0) {
                    String key = line.substring(0, separator).trim();
                    String value = line.substring(separator + 1).trim();
                    env.put(key, value);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler .env: " + e.getMessage());
        }

        return env;
    }
}
