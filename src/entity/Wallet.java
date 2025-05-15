package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Wallet implements Serializable {
    private String nameWallet;
    private BigDecimal balance;

    public Wallet() {
        this.balance = BigDecimal.ZERO; // Инициализация по умолчанию
    }

    public Wallet(String name, BigDecimal balance) {
        this.nameWallet = name;
        this.balance = balance;
    }

    public String getNameWallet() {
        return nameWallet;
    }

    public void setNameWallet(String nameWallet) {
        this.nameWallet = nameWallet;
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
        Wallet wallet = (Wallet) o;
        return Objects.equals(nameWallet, wallet.nameWallet) && Objects.equals(balance, wallet.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameWallet, balance);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "name='" + nameWallet + '\'' +
                ", balance=" + balance +
                '}';
    }
}
