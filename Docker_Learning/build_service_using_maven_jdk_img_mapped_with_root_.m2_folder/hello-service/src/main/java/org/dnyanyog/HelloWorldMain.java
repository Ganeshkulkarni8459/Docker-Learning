package org.dnyanyog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldMain {

  public static void main(String[] args) {

    for (String s : args) {
      System.out.println("Received Param - " + s);
    }

    System.out.println("Environmentail Param - " + System.getenv("SENSITIVE_CREDENTIALS"));

    runCommand("cat "+System.getenv("SENSITIVE_CREDENTIALS"));

    SpringApplication.run(HelloWorldMain.class, args);
  }

  public static void runCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", command); // For Linux/macOS
            // processBuilder.command("cmd.exe", "/c", command); // For Windows
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Command executed with exit code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
