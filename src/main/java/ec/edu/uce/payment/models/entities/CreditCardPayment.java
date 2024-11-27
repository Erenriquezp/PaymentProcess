package ec.edu.uce.payment.models.entities;

public class CreditCardPayment extends Payment {

    @Override
    public void pay() {
        System.out.println("Credit card payment");
    }
}
