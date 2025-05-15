package repository;

import entity.CurrentExpense;
import entity.PotentialExpense;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PotentialExpenseRepository {
    private Map<Long, PotentialExpense> potentialExpenses = new HashMap<>();
    private long nextId = 1;

    /**
     * Добавление операции
     * @param potentialExpense
     * @return
     */
    public PotentialExpense add(PotentialExpense potentialExpense) {
        potentialExpense.setId(nextId);
        potentialExpenses.put(nextId, potentialExpense);
        nextId++;
        return potentialExpense;
    }

    /**
     * Показать все операции
     * @return
     */
    public List<PotentialExpense> getAll() {
        return new ArrayList<>(potentialExpenses.values());
    }

    /**
     * Удаление по ID
     * @param id
     * @return
     */
    public boolean delete(Long id) {
        return potentialExpenses.remove(id) != null;
    }


    /**
     * Сохранение в файл
     * @param filename
     */
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(potentialExpenses);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения потенциальных расходов: " + e.getMessage());
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
            potentialExpenses = (Map<Long, PotentialExpense>) ois.readObject();
            nextId = potentialExpenses.keySet().stream()
                    .max(Long::compare)
                    .orElse(0L) + 1;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки потенциальных расходов: " + e.getMessage());
        }
    }
}