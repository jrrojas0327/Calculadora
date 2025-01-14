package org.cuatrovientos.dam.calculadora;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Definición de variables necesarias
		Scanner scan = new Scanner(System.in);
		int opcion;

		// Mostrar menú y elegir opción
		do {
			mostrarMenu();
			opcion = leerOpcion(scan);

			// Realizar operación de la opción selecionada
			switch (opcion) {
				case 1 -> realizarOperacion(scan, "SUMA", (a, b) -> a + b);
				case 2 -> realizarOperacion(scan, "RESTA", (a, b) -> a - b);
				case 3 -> realizarOperacion(scan, "MULTIPLICACIÓN", (a, b) -> a * b); //TODO ARREGLAR LIMITE DE DOUBLE
				case 4 -> realizarOperacion(scan, "DIVISIÓN", (a, b) -> a / b);
				case 5 -> System.out.println("Programa finalizado.");
			}
		} while (opcion != 5);

		// Cerrar scanner
		scan.close();
	}

	private static void mostrarMenu() {
		System.out.println("\tCALCULADORA\n" + "============================\n" + "Elige una opción\n" + "1. Suma\n"
				+ "2. Resta\n" + "3. Multiplicación\n" + "4. División\n" + "5. Salir del programa\n"
				+ "============================");
	}

	// Leer opción y validar
	private static int leerOpcion(Scanner scan) {
		int opcion;
		do {
			System.out.print("> ");
			while (!scan.hasNextInt()) {
				System.out.println("Por favor, ingresa un número valido.");
				scan.next();
				System.out.print("> ");
			}
			opcion = scan.nextInt();
			if (opcion > 5 || opcion < 1) {
				System.out.println("Por favor, ingresa un número valido (1-5)");
			}
		} while (opcion > 5 || opcion < 1);
		return opcion;
	}

	// Realizar operaciones según la opcion elegida
	private static void realizarOperacion(Scanner scan, String operacion, Operacion op) {
		System.out.println("\t" + operacion + "\n");
		double num1 = leerNumero(scan, "Ingresa el primer número: ");
		double num2;
		if (operacion.equals("DIVISIÓN")) {
			do {
				num2 = leerNumero(scan, "Ingresa el segundo número (no puede ser 0): ");
				if (num2 == 0) {
					System.out.println("El divisor no puede ser 0. Intenta nuevamente.");
				}
			} while (num2 == 0);
		}else {
			num2 = leerNumero(scan, "Ingresa el segundo número: ");

		System.out.println("\nEl resultado de la " + operacion + " es: " + op.calcular(num1, num2) + "\n\n");
	}

	// Validar entrada de números
	private static double leerNumero(Scanner scan, String mensaje) {
		System.out.print(mensaje);
		while (!scan.hasNextDouble()) {
			System.out.println("Entrada no válida. Por favor, ingresa un número válido.");
			scan.next();
			System.out.print(mensaje);
		}
		return scan.nextDouble();
	}

	interface Operacion {
		double calcular(double a, double b);
	}
}
