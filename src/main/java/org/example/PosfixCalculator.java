package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class PosfixCalculator implements IPosfixCalculator {
    IStack<Integrer> stack;

    public PostfixCalculator() {
        private IStack<Integer> stack;

        public PostfixCalculator() {
            this.stack = new VectorStack<>();
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
                    int result = switch (token) {
                        case "+" -> a + b;
                        case "-" -> a - b;
                        case "*" -> a * b;
                        case "/" -> a / b;
                        case "%" -> a % b;
                        default -> throw new IllegalArgumentException("Operador no válido: " + token);
                    };
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