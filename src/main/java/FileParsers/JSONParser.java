package FileParsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONParser {

    public List<List<String>> parseJSON(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            List<Map<String, String>> entries = parseToArrayOfObjects(content);

            // Assuming the first object contains headers
            List<String> headers = new ArrayList<>(entries.get(0).keySet());
            records.add(headers);

            for (Map<String, String> entry : entries) {
                List<String> row = new ArrayList<>();
                for (String header : headers) {
                    row.add(entry.getOrDefault(header, ""));
                }
                records.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }

    private List<Map<String, String>> parseToArrayOfObjects(String content) {
        List<Map<String, String>> entries = new ArrayList<>();
        // Remove the outer brackets and split into individual object strings
        String[] objects = content.trim().substring(1, content.length() - 1).split("(?<=\\}),");

        for (String object : objects) {
            Map<String, String> map = new HashMap<>();
            object = object.replaceAll("[{}]", "").trim();
            String[] pairs = object.split(",");
            for (String pair : pairs) {
                String[] keyVal = pair.split(":");
                if (keyVal.length == 2) {
                    String key = keyVal[0].trim().replaceAll("^\"|\"$", "");
                    String value = keyVal[1].trim().replaceAll("^\"|\"$", "");
                    map.put(key, value);
                }
            }
            entries.add(map);
        }

        return entries;
    }


}
