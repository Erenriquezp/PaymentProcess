package ec.edu.uce.payment.models;

public abstract class Payment implements IPay {
    protected String detail;
    protected double amount;

    public Payment(String detail, double amount) {
        this.detail = detail;
        this.amount = amount;
    }

    public Payment() {
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "detail='" + detail + '\'' +
                ", amount=" + amount;
    }
}
