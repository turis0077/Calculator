package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class PostfixCalculator implements IPostfixCalculator {
    private IStack<Integer> stack;

    public PostfixCalculator() {
        this.stack = new VectorStack<>();
    }

    @Override
    public void readFromFile(String path) throws IOException {
        // Ejemplo de lectura de archivo y procesamiento de expresión
        String content = new String(Files.readAllBytes(Paths.get(path)));
        int result = evaluateExpression(content.trim());
        System.out.println("Resultado de la expresión: " + result);
    }

    @Override
    public int evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");
        for (String token : tokens) {
            if (token.matches("\\d+")) { // Es un número
                stack.push(Integer.parseInt(token));
            } else { // Es un operador
                int b = stack.pop();
                int a = stack.pop();
                int result = 0;
                switch (token) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        result = a / b;
                        break;
                    case "%":
                        result = a % b;
                        break;
                    default:
                        throw new IllegalArgumentException("Operador no válido: " + token);
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo de texto (incluya la extensión .txt): ");
        String filePath = scanner.nextLine();

        PostfixCalculator calculator = new PostfixCalculator();
        try {
            calculator.readFromFile(filePath);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            scanner.close(); // Cerrar el scanner para liberar recursos
        }
    }
}
