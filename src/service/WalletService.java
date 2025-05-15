package service;

import controller.WalletController;
import entity.Wallet;
import repository.WalletRepository;

import java.math.BigDecimal;
import java.util.Map;

public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    private static WalletRepository getWalletRepository(WalletRepository walletRepository) {
        return walletRepository;
    }

    public Wallet getWalletById(long id) {
        return walletRepository.getWalletById(id);
    }

    public void createWallet(String nameWallet, BigDecimal balance) {
        walletRepository.createWallet(nameWallet, balance);
    }

    public boolean deleteWallet(long id) {
        if (walletRepository.getWalletById(id) != null) {
            walletRepository.deleteWallet(id);
            return true;
        }
        return false;
    }

    public Map<Long, Wallet> getAllWallets() {
        return walletRepository.getAllWallets();
    }

    public boolean updateWallet(long id, String newName, BigDecimal newBalance) {
        Wallet wallet = walletRepository.getWalletById(id);
        if (wallet != null) {
            Wallet updateWallet = new Wallet(newName, newBalance);
            return walletRepository.updateWallet(id, updateWallet);
        }
        return false;
    }
}
