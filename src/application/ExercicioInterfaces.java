package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxServices;
import model.services.RentalService;

public class ExercicioInterfaces {
    public static void main(String[] args) throws ParseException {
        
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:ss");
        
        try {
        System.out.println("Enter Rental Data: ");
        System.out.print("Car model: ");
        String carModel = sc.nextLine();
        System.out.print("Pickup (dd/MM/yyyy hh:ss): ");
        Date start = sdf.parse(sc.nextLine());
        System.out.print("Return (dd/MM/yyyy hh:ss): ");
        Date finish = sdf.parse(sc.nextLine());        
        
        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
        
        System.out.print("Enter price per Hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Enter price per Day: ");
        double pricePerDay = sc.nextDouble();
        
        RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxServices());
        
        rentalService.processInvoice(cr);          
            
                
        System.out.println("INVOICE");
        System.out.println("Basic Payment: "+String.format("%.2f",cr.getInvoice().getBasicPayment()));
        System.out.println("Tax: "+String.format("%.2f",cr.getInvoice().getTax()));
        System.out.println("Total Payment: "+String.format("%.2f",cr.getInvoice().getTotalPayment()));
        
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        finally{
            sc.close();
        }
        
    }
    
}
