package repository;

import entity.CurrentExpense;
import entity.DebitCard;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentExpenseRepository {
    private Map<Long, CurrentExpense> currentExpenses = new HashMap<>();
    private long nextId = 1;

    /**
     * Добавление операции
     * @param currentExpense
     * @return
     */
    public CurrentExpense add(CurrentExpense currentExpense) {
        currentExpense.setId(nextId);
        currentExpenses.put(nextId, currentExpense);
        nextId++;
        return currentExpense;
    }

    /**
     * Показать все операции
     * @return
     */
    public List<CurrentExpense> getAll() {
        return new ArrayList<>(currentExpenses.values());
    }

    /**
     * Удаление по ID
     * @param id
     * @return
     */
    public boolean delete(Long id) {
        return currentExpenses.remove(id) != null;
    }

    /**
     * Обновление операции
     * @param currentExpense
     * @return
     */
    public boolean update(CurrentExpense currentExpense) {
        if (currentExpenses.containsKey(currentExpense.getId())) {
            currentExpenses.put(currentExpense.getId(), currentExpense);
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
            oos.writeObject(currentExpenses);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения текущих расходов: " + e.getMessage());
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
            currentExpenses = (Map<Long, CurrentExpense>) ois.readObject();
            nextId = currentExpenses.keySet().stream()
                    .max(Long::compare)
                    .orElse(0L) + 1;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки текущих расходов: " + e.getMessage());
        }
    }
}


