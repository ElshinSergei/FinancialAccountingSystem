package repository;

import entity.DebitCard;
import entity.Wallet;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DebitCardRepository {
    private Map<Long, DebitCard> cards = new HashMap<>();
    private long nextId = 1;

    /**
     * Создать новую карту
     * @param cardNumber
     * @param bankName
     * @param balance
     * @return
     */
    public DebitCard createDebitCard(String cardNumber, String bankName, BigDecimal balance) {
        DebitCard card = new DebitCard(cardNumber, bankName, balance);
        cards.put(nextId, card);
        nextId++;
        return card;
    }

    /**
     * Получить карту по ID
     * @param id
     * @return
     */
    public DebitCard getDebitCardById(long id) {
        return cards.get(id);
    }

    /**
     * Изменить данные карты
     * @param id
     * @param card
     */
    public boolean updateDebitCard(long id, DebitCard card) {
        if (cards.containsKey(id)) {
            cards.put(id, card);
            return true;
        }
        return false;
    }

    /**
     * Удалить карту
     * @param id
     */
    public void deleteDebitCard(long id) {
        cards.remove(id);
    }

    /**
     * Посмотреть все дебетовые карты
     * @return
     */
    public Map<Long, DebitCard> getAllDebitCards() {
        return new HashMap<>(cards);
    }

    /**
     * Списание средств с карты
     * @param amount
     */
    public void withdraw(BigDecimal amount, String sourceId) {
        long id;
        try {
            id = Long.parseLong(sourceId.substring(1)); // Извлекаем "C1" → 1
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат ID карты: " + sourceId);
        }

        DebitCard debitCard = cards.get(id);
        if(debitCard == null) {
            throw new IllegalArgumentException("Карта с ID " + id + " не найдена");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной!");
        }

        if (debitCard.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Недостаточно средств на карте. Текущий баланс: "
                    + debitCard.getBalance());
        }

        debitCard.setBalance(debitCard.getBalance().subtract(amount));
    }

    /**
     * Сохранение в файл
     * @param filename
     */
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(cards);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения карт: " + e.getMessage());
        }
    }

    /**
     * Загрузить из файла
     * @param filename
     */
    @SuppressWarnings("uncheсked")
    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Файл данных не найден. Будет создан новый");
            return; // Выходим, если файла нет
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            cards = (Map<Long, DebitCard>) ois.readObject();
            nextId = cards.keySet().stream()
                    .max(Long::compare)
                    .orElse(0L) + 1;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки карт: " + e.getMessage());
        }
    }

}
