package service;

import entity.Wallet;
import repository.WalletRepository;

import java.math.BigDecimal;
import java.util.Map;

public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    /**
     * Получить кошелек по ID
     * @param id
     * @return
     */
    public Wallet getWalletById(long id) {
        return walletRepository.getWalletById(id);
    }

    /**
     * Создать новый кошелек
     * @param nameWallet
     * @param balance
     */
    public void createWallet(String nameWallet, BigDecimal balance) {
        walletRepository.createWallet(nameWallet, balance);
    }

    /**
     * Удалить кошелек
     * @param id
     * @return
     */
    public boolean deleteWallet(long id) {
        if (walletRepository.getWalletById(id) != null) {
            walletRepository.deleteWallet(id);
            return true;
        }
        return false;
    }

    /**
     * Получить все кошельки
     * @return
     */
    public Map<Long, Wallet> getAllWallets() {
        return walletRepository.getAllWallets();
    }

    /**
     * Изменить данные кошелька
     * @param id
     * @param newName
     * @param newBalance
     * @return
     */
    public boolean updateWallet(long id, String newName, BigDecimal newBalance) {
        Wallet wallet = walletRepository.getWalletById(id);
        if (wallet != null) {
            Wallet updateWallet = new Wallet(newName, newBalance);
            return walletRepository.updateWallet(id, updateWallet);
        }
        return false;
    }
}
