package ec.edu.uce.payment.models.entities;

public class TransferPayment extends Payment  {
    @Override
    public void pay() {
        System.out.println("Transfer Payment");
    }
}
