package config;

import exception.PortInitializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;

@Component
public class PortManager {
    @Value("${local.server.port}")
    private int port;
    private ServerSocket serverSocket;


    public int initialize() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado na porta: " + serverSocket.getLocalPort());
            return port;
        } catch (IOException e) {
            throw new PortInitializationException("Falha ao inicializar porta dinâmica.", e);
        }
    }

    public void waitForConnection() {
        try {
            serverSocket.accept();
        } catch (IOException e) {
            throw new PortInitializationException("Erro ao aguardar conexão do Electron.", e);
        }
    }
}