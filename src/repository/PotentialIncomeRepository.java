package repository;

import entity.CurrentExpense;
import entity.PotentialExpense;
import entity.PotentialIncome;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PotentialIncomeRepository {
    private Map<Long, PotentialIncome> potentialIncomes = new HashMap<>();
    private long nextId = 1;

    /**
     * Добавление операции
     * @param potentialIncome
     * @return
     */
    public PotentialIncome add(PotentialIncome potentialIncome) {
        potentialIncome.setId(nextId);
        potentialIncomes.put(nextId, potentialIncome);
        nextId++;
        return potentialIncome;
    }

    /**
     * Показать все операции
     * @return
     */
    public List<PotentialIncome> getAll() {
        return new ArrayList<>(potentialIncomes.values());
    }

    /**
     * Удаление по ID
     * @param id
     * @return
     */
    public boolean delete(Long id) {
        return potentialIncomes.remove(id) != null;
    }

    /**
     * Обновление операции
     * @param potentialIncome
     * @return
     */
    public boolean update(PotentialIncome potentialIncome) {
        if (potentialIncomes.containsKey(potentialIncome.getId())) {
            potentialIncomes.put(potentialIncome.getId(), potentialIncome);
            return true;
        }
        return false;
    }

    /**
     * Сохранение в файл
     * @param filename
     */
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(potentialIncomes);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения потенциальных доходов: " + e.getMessage());
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
            potentialIncomes = (Map<Long, PotentialIncome>) ois.readObject();
            nextId = potentialIncomes.keySet().stream()
                    .max(Long::compare)
                    .orElse(0L) + 1;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки потенциальных доходов: " + e.getMessage());
        }
    }
}
