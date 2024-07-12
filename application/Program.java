package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import model.service.ContractService;
import model.service.PaypalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("Dados do contrato:");
		System.out.print("Números: ");
		int number = sc.nextInt();
		
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), dtf);
		System.out.print("Valor do contrato: ");
		double parcel = sc.nextDouble();
	 	
		//Criando o contrato
		Contract contract = new Contract(number, date, parcel);
		System.out.print("Números de parcelas: ");
		int n = sc.nextInt();
		
		ContractService service = new ContractService(new PaypalService());
		service.processContract(contract, n);
		
		//imprimindo as parcelas
		System.out.println("Parcelas:");
		for(Installment installment : contract.getInstallment()) {
			System.out.println(installment);
		}

		sc.close();
	}
}
