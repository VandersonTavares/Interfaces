package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
    private Double pricePerDay;
    private Double pricePerHour;
    
    private TaxService taxService;

    public RentalService() {
    }

    public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxService) {
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.taxService = taxService;
    }
    
    public void processInvoice(CarRental carRental){
        long horaPegou = carRental.getStart().getTime();
        long horaDevolucao = carRental.getFinish().getTime();
        double hours = (double)(horaDevolucao - horaPegou) /      1000      /       60       /      60;
                               //retorna em milisegundos  /retorna segundos/ retorna minutos/ retorna horas                          
        Double basicPayment;
        if(hours <= 12.0){  //Math.ceil Arredonda pra cima
             basicPayment = Math.ceil(hours) * pricePerHour;
        }else{
            basicPayment = Math.ceil(hours / 24) * pricePerDay;
        }
        double tax = taxService.tax(basicPayment);        
        
        //1-crio um novo objeto de nota de pagamento e 2- associei ao objeto de aluguel
                //2                       //1 
        carRental.setInvoice(new Invoice(basicPayment, tax));
                               
    }
}
