package com.project.services.tasks;

import io.github.cdimascio.dotenv.Dotenv;

public class ApiConsumer extends Thread {

        Dotenv dotenv = Dotenv.configure().directory("projeto_tabd/assets").load();

    String dataset = "";
    private long response;

    public ApiConsumer(String dataset) {
        this.dataset = dataset;
    }

    public void fetchDataset() {

            ProcessBuilder pb1 = new ProcessBuilder(
                "pip", "install", "kaggle"
            );

            ProcessBuilder pb2 = new ProcessBuilder(
                "cmd.exe", "/c", "md", "%USERPROFILE%\\.kaggle"
            );

            ProcessBuilder pb3 = new ProcessBuilder(
                 "cmd.exe", "/c", "echo {\"username\":\"" + dotenv.get("KAGGLE_USERNAME") + "\",\"key\":\"" + dotenv.get("KAGGLE_API_TOKEN") + "\"} > %USERPROFILE%\\.kaggle\\kaggle.json"
            );

            ProcessBuilder pb = new ProcessBuilder(
                    "kaggle", "datasets", "download", "-d", this.dataset, "--path",  "projeto_tabd/assets/datasets",
                    "--unzip"

            );

            try {
                pb1.inheritIO();
                Process process1 = pb1.start();
                process1.waitFor();

                pb2.inheritIO();
                Process process2 = pb2.start();
                process2.waitFor();

                pb3.inheritIO();
                Process process3 = pb3.start();
                process3.waitFor();

                pb.inheritIO();
                Process process = pb.start();
                int exitCode = process.waitFor();

                if (exitCode == 0) {
                    System.out.println("Dataset baixado e descompactado com sucesso!");
                } else {
                    System.err.println("Erro ao baixar o dataset. Código de saída: " + exitCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    public long getResponse() {

        return this.response;
    }

    @Override
    public void run() {
        try {
            fetchDataset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
