package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class DebitCard implements Serializable {
    private String cardNumber;     // номер карты
    private String bankName;     // имя банка
    private BigDecimal balance;

    public DebitCard(String cardNumber, String bankName, BigDecimal balance) {
        this.cardNumber = cardNumber;
        this.bankName = bankName;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DebitCard debitCard = (DebitCard) o;
        return Objects.equals(cardNumber, debitCard.cardNumber) && Objects.equals(bankName, debitCard.bankName) && Objects.equals(balance, debitCard.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, bankName, balance);
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
