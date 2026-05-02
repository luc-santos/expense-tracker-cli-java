import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {
    private static final String FILE_NAME = "expenses.json";
    private final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .setPrettyPrinting()
        .create();

    public List<Expense> loadExpenses() {
        try (FileReader reader = new FileReader (FILE_NAME)) {
            Type listType = new TypeToken<List<Expense>>() {}.getType();
            List<Expense> expenses = gson.fromJson(reader, listType);

            if (expenses == null) {
                return new ArrayList<>();
            }

            return expenses;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveExpenses(List<Expense> expenses) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(expenses, writer);
        } catch (IOException e) {
            System.out.println("Error saving expenses.");
        }
    }
}