package tuprak6;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LibraryLogger {
    private ArrayList<String> logs;

    public LibraryLogger() {
        logs = new ArrayList<>();
    }

    public void logActivity(String activity) {
        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String time = LocalDateTime.now().format(format);
        logs.add(time + " | " + activity);
    }

    public String getLogs() {
        if (logs.isEmpty())
            return "Belum ada log.";

        String result = "";

        for (String log : logs) {
            result += log + "\n";
        }

        return result;
    }

    public void clearLogs() {
        logs.clear();
    }
}
