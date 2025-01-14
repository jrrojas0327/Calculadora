 package org.cuatrovientos.dam.calculadora;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Definición de variables necesarias
		Scanner scan = new Scanner(System.in);
		int opcion;
		// Mostrar menú y elegir opción
		do {
			System.out.println("\tCALCULADORA\n" + 
					"============================\n" +
					"Elige una opción\n" +
					"1. Suma\n" +
					"2. Resta\n" +
					"3. Multiplicación\n" +
					"4. División\n" +
					"5. Salir del programa\n"+
					"============================");
			opcion = leerOpcion(scan);
			// Realizar operación de la opción selecionada
			switch (opcion) {
				case 1 -> realizarOperaciones(scan, "SUMA", (a, b) -> a + b);
				case 2 -> realizarOperaciones(scan, "RESTA", (a, b) -> a - b);
				case 3 -> realizarOperaciones(scan, "MULTIPLICACIÓN", (a, b) -> a * b);
				case 4 -> realizarOperaciones(scan, "DIVISIÓN", (a, b) -> a / b);
				case 5 -> System.out.println("Programa finalizado.");
			}
		} while (opcion != 5);
		
		// Cerrar scanner
		scan.close();
	}
	//	Leer opción y validar
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
	//	Realizar operaciones según la opcion elegida
	private static void realizarOperaciones(Scanner scan, String operacion, Operacion op) {
		System.out.println("\t" + operacion + "\n");
		double num1 = leerNumero(scan, "Ingresa el primer número: ");
		double num2 = leerNumero(scan, "Ingresa el segundo número: ");
					
		System.out.println("\nEl resultado de la " + operacion + " es: " + op.calcular(num1, num2) + "\n\n");
//		FIXME (Error, al dividir por 0 se rompe el programa)
	}

	//	Validar entrada de números
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
