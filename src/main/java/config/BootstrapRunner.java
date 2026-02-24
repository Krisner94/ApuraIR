package config;

import exception.ApplicationStartupException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapRunner implements ApplicationRunner {
    private final PortManager portManager;
    private final ElectronLauncher electronLauncher;

    public BootstrapRunner(
            PortManager portManager,
            ElectronLauncher electronLauncher
    ) {
        this.portManager = portManager;
        this.electronLauncher = electronLauncher;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            int port = portManager.initialize();
            electronLauncher.launch(port);
            portManager.waitForConnection();
            System.out.println("Electron conectado.");
        } catch (RuntimeException e) {
            throw new ApplicationStartupException("Erro crítico na inicialização da aplicação.", e);
        }
    }
}