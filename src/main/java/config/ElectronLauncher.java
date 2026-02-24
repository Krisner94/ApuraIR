package config;

import exception.ElectronLaunchException;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

@Component
public class ElectronLauncher {

    private Process process;

    public void launch(int port) {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "bash",
                    "-c",
                    "npx electron . --port=" + port
            );

            builder.directory(new File("electron/my-electron-app"));
            builder.inheritIO();

            process = builder.start();

        } catch (IOException e) {
            throw new ElectronLaunchException("Falha ao iniciar processo Electron.", e);
        }
    }

    @PreDestroy
    public void destroy() {
        if (process != null && process.isAlive()) {
            process.destroy();
        }
    }
}