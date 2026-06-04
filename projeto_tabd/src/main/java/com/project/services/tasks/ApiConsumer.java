package com.project.services.tasks;

public class ApiConsumer extends Thread {

    String dataset = "";
    private long response;

    public ApiConsumer(String dataset) {
        this.dataset = dataset;
    }

    public void fetchDataset() {

        
            ProcessBuilder pb = new ProcessBuilder(
                    "kaggle", "datasets", "download", "-d", this.dataset, "--path",  "../../../../../../datasets",
                    "--unzip"

            );

            try {
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
