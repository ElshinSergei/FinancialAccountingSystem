package repository;

import entity.Wallet;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class WalletRepository {
    private Map<Long, Wallet> wallets = new HashMap<>();
    private long nextId = 1;

    /**
     * Создаем новый кошелек
     * @return
     */
    public Wallet createWallet(String nameWallet, BigDecimal balance) {
        Wallet wallet = new Wallet(nameWallet, balance);
        wallets.put(nextId, wallet);
        nextId++;
        return wallet;
    }

    /**
     * Удалить кошелек
     * @param id
     */
    public void deleteWallet(long id) {
        wallets.remove(id);
    }

    /**
     * Получить кошелек по id
     * @param id
     * @return
     */
    public Wallet getWalletById(long id) {
        return wallets.get(id);
    }

    /**
     * Обновляет данные кошелька
     * @param id
     * @param wallet
     * @return
     */
    public boolean updateWallet(long id, Wallet wallet) {
        if (wallets.containsKey(id)) {
            wallets.put(id, wallet);
            return true;
        }
        return false;
    }

    /**
     * Посмотреть все кошельки
     * @return
     */
    public Map<Long, Wallet> getAllWallets() {
        return new HashMap<>(wallets);
    }

    /**
     * Списание средств с кошелька
     * @param amount
     */
    public void withdraw (BigDecimal amount, String sourceId) {
        long id;
        try {
            id = Long.parseLong(sourceId.substring(1)); // Извлекаем "W1" → 1
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат ID кошелька: " + sourceId);
        }

        Wallet wallet = wallets.get(id);
        if(wallet == null) {
            throw new IllegalArgumentException("Кошелек с ID " + id + " не найден");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной!");
        }

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Недостаточно средств на кошельке. Текущий баланс: "
                    + wallet.getBalance());
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(wallets);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения кошельков: " + e.getMessage());
        }
    }

    @SuppressWarnings("uncheсked")
    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Файл данных не найден. Будет создан новый");
            return; // Выходим, если файла нет
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            wallets = (Map<Long, Wallet>) ois.readObject();
            nextId = wallets.keySet().stream()
                    .max(Long::compare)
                    .orElse(0L) + 1;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки кошельков: " + e.getMessage());
        }
    }

}
