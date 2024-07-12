package model.service;

import java.time.LocalDate;

import entities.Contract;
import entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	//estrutura do serviço
	public void processContract(Contract contract, int months) {	
			Double parcelBasic = contract.getTotalValue() / months;	
			
		for(int i=1; i<=months; i++) {
			LocalDate DateVenc = contract.getDate().plusMonths(i);
			//juros e taxa
			double fees = onlinePaymentService.interest(parcelBasic, i);
			double fee = onlinePaymentService.paymentFee(parcelBasic + fees);
			double quota = parcelBasic + fees + fee;		
			contract.getInstallment().add(new Installment(DateVenc, quota));
		}
	}
}
