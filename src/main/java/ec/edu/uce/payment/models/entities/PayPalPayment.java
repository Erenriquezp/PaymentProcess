package ec.edu.uce.payment.models.entities;

public class PayPalPayment extends Payment {

    public PayPalPayment(String mikuNakano, double v) {
        super(mikuNakano, v);
    }

    @Override
    public void pay() {
        System.out.println("PayPal Payment: " + super.toString());
    }
}
