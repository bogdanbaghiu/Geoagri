package com.bogdanbaghiu.duplicatetransactions;

import com.bogdanbaghiu.duplicatetransactions.model.Transaction;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class DuplicateTransactions {

    private static final List<Transaction> transactionList = new ArrayList<>();
    private static final List<List<Transaction>> finalList = new ArrayList<>();
    private static final String FILE_EXTENSION = "json";
    private static final String FILE_DESCRIPTION = "JSON FILES";
    private static final String FORMAT_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String INIT_DIR = "user.home";
    private static final String TIMEZONE = "CET";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        manageFile();
    }

    private static void manageFile() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_DESCRIPTION, FILE_EXTENSION);
            fileChooser.setFileFilter(filter);
            fileChooser.setCurrentDirectory(new File(System.getProperty(INIT_DIR)));
            int result = fileChooser.showOpenDialog(fileChooser);
            if (result == JFileChooser.APPROVE_OPTION) {
                JsonParser parser = new JsonParser();
                try {
                    Object obj = parser.parse(new FileReader(fileChooser.getSelectedFile().getPath()));
                    JsonArray jsonArray = (JsonArray) obj;
                    Iterator<JsonElement> iterator = jsonArray.iterator();
                    while (iterator.hasNext()) {
                        transactionList.add(transformJSONObj2Transaction((JsonObject) iterator.next()));
                    }
                    generateFinalJson();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.exit(1);
    }

    private static Transaction transformJSONObj2Transaction(JsonObject jsonObject) {
        Transaction transaction = new Transaction();
        transaction.setId(jsonObject.get("id").getAsInt());
        transaction.setSourceAccount(jsonObject.get("sourceAccount").getAsString());
        transaction.setTargetAccount(jsonObject.get("targetAccount").getAsString());
        transaction.setAmount(jsonObject.get("amount").getAsInt());
        Date date = parseStringDate(jsonObject.get("time").getAsString());
        if (date != null) {
            transaction.setTime(date);
        }
        return transaction;
    }

    private static Date parseStringDate(String input) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE_TIME);
            return df.parse(input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static void generateFinalJson() {
        duplicatedTransactions();
        sortTransaction();
        JsonArray jsonArray = generateJSON();
        save2File(jsonArray);
    }

    private static void duplicatedTransactions() {
        try {
            for (Transaction tran : transactionList) {
                Optional<Transaction> transaction = transactionList.stream().
                        filter(p -> {
                            org.joda.time.LocalDateTime tranDateTime = new DateTime(tran.getTime()).toLocalDateTime();
                            org.joda.time.LocalDateTime pDateTime = new DateTime(p.getTime()).toLocalDateTime();
                            int diff = Math.abs(Seconds.secondsBetween(tranDateTime, pDateTime).getSeconds());
                            return p.getId() != tran.getId() && p.getSourceAccount().equals(tran.getSourceAccount())
                                    && p.getTargetAccount().equals(tran.getTargetAccount()) && diff <= 60;
                        }).
                        findFirst();
                if (transaction.isPresent()) {
                    generateDuplicatedList(tran);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void generateDuplicatedList(Transaction transaction) {
        boolean found = false;
        for (List<Transaction> transactionList : finalList) {
            for (Transaction transaction1 : transactionList) {
                org.joda.time.LocalDateTime tranDateTime = new DateTime(transaction.getTime()).toLocalDateTime();
                org.joda.time.LocalDateTime pDateTime = new DateTime(transaction1.getTime()).toLocalDateTime();
                int diff = Math.abs(Seconds.secondsBetween(tranDateTime, pDateTime).getSeconds());
                if (transaction.getId() != transaction1.getId()
                        && transaction.getSourceAccount().equals(transaction1.getSourceAccount())
                        && transaction.getTargetAccount().equals(transaction1.getTargetAccount()) && diff <= 60) {
                    transactionList.add(transaction);
                    found = true;
                    break;
                }
                if (found) {
                    break;
                }
            }
        }
        if (!found) {
            List<Transaction> transactionList1 = new ArrayList<>();
            transactionList1.add(transaction);
            finalList.add(transactionList1);
        }
    }

    private static void sortTransaction() {
        for (List<Transaction> transactionList : finalList) {
            Collections.sort(transactionList,
                    (o1, o2) -> {
                        if (o1.getTime() == null || o2.getTime() == null)
                            return 0;
                        return o1.getTime().compareTo(o2.getTime());
                    });
        }
    }

    private static JsonArray generateJSON() {
        JsonArray mainArray = new JsonArray();
        for (List<Transaction> transactionList : finalList) {
            JsonArray jsonArray = new JsonArray();
            for (Transaction transaction : transactionList) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", transaction.getId());
                jsonObject.addProperty("sourceAccount", transaction.getSourceAccount());
                jsonObject.addProperty("targetAccount", transaction.getTargetAccount());
                jsonObject.addProperty("amount", transaction.getAmount());
                jsonObject.addProperty("time", convertDate2ISO8601(transaction.getTime()));
                jsonArray.add(jsonObject);
            }
            mainArray.add(jsonArray);
        }
        return mainArray;
    }

    private static String convertDate2ISO8601(Date date) {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(FORMAT_DATE_TIME);
        sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
        String text = sdf.format(date);
        return text;
    }

    private static void save2File(JsonArray jsonArray) {
        try {
            JFrame parentFrame = new JFrame();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            int userSelection = fileChooser.showSaveDialog(parentFrame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                FileWriter file = new FileWriter(fileToSave.getAbsolutePath() + "." + FILE_EXTENSION);
                try {
                    file.write(jsonArray.toString());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                } finally {
                    file.flush();
                    file.close();
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
