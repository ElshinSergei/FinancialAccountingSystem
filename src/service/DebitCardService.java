package service;

import entity.DebitCard;
import repository.DebitCardRepository;

import java.math.BigDecimal;
import java.util.Map;

public class DebitCardService {
    private final DebitCardRepository debitCardRepository;

    public DebitCardService(DebitCardRepository debitCardRepository) {
        this.debitCardRepository = debitCardRepository;
    }

    /**
     * ПОлучить карту по ID
     * @param id
     * @return
     */
    public DebitCard getDebitCardById(long id) {
        return debitCardRepository.getDebitCardById(id);
    }

    /**
     * Добавить карту
     * @param cardNumber
     * @param bankName
     * @param balance
     */
    public void createDebitCard(String cardNumber, String bankName, BigDecimal balance) {
        debitCardRepository.createDebitCard(cardNumber, bankName, balance);
    }

    /**
     * Удалить карту
     * @param id
     * @return
     */
    public boolean deleteDebitCard(Long id) {
        if (debitCardRepository.getDebitCardById(id) != null) {
            debitCardRepository.deleteDebitCard(id);
            return true;
        }
        return false;
    }

    /**
     * Получить все карты
     * @return
     */
    public Map<Long, DebitCard> getAllCards() {
        return debitCardRepository.getAllDebitCards();
    }

    /**
     * Изменить данные карты
     * @param id
     * @param newCardNumber
     * @param newBankName
     * @param newBalance
     * @return
     */
    public boolean updateCard(long id, String newCardNumber, String newBankName, BigDecimal newBalance) {
        DebitCard debitCard = debitCardRepository.getDebitCardById(id);
        if (debitCard != null) {
            DebitCard updateCard = new DebitCard(newCardNumber, newBankName, newBalance);
            return debitCardRepository.updateDebitCard(id, updateCard);
        }
        return false;
    }
}
