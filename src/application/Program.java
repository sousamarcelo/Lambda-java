package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("nter full file path: ");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List<Employee> list = new ArrayList<Employee>();
			
			String line = br.readLine();
			
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])) );
				line = br.readLine();
			}
			
			Comparator<String> com = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			System.out.print("Enter a salary: ");
			double salaryParameter = sc.nextDouble();
			
			List<String> emails = list.stream()
					.filter(p -> p.getSalary() > salaryParameter)
					.map(p -> p.getEmail()).sorted(com)
					.collect(Collectors.toList());
			
			System.out.printf("Email of people whose salary is more than %s:%n", String.format("%.2f", salaryParameter));
			emails.forEach(System.out::println);
					
			
		} catch (IOException e) {
			System.out.println("Error :" + e.getMessage());
		}
		
		sc.close();

	}

}
