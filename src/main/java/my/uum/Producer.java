package my.uum;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Producer {
    public static void main(String[] args) {
        String topicName = "api-data"; // Kafka topic name

        // Kafka producer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.20.10.2:9093"); // producer ip pc1
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Malaysian states with their approximate latitude and longitude
        Map<String, double[]> states = new HashMap<>();
        states.put("Johor", new double[]{1.4927, 103.7414});
        states.put("Kedah", new double[]{6.1248, 100.3678});
        states.put("Kelantan", new double[]{6.1254, 102.2381});
        states.put("Kuala Lumpur", new double[]{3.1390, 101.6869});
        states.put("Malacca", new double[]{2.1896, 102.2501});
        states.put("Negeri Sembilan", new double[]{2.7250, 101.9424});
        states.put("Pahang", new double[]{3.8126, 103.3256});
        states.put("Penang", new double[]{5.4164, 100.3327});
        states.put("Perak", new double[]{4.5921, 101.0901});
        states.put("Perlis", new double[]{6.4440, 100.2141});
        states.put("Putrajaya", new double[]{2.9264, 101.6964});
        states.put("Sabah", new double[]{5.9788, 116.0753});
        states.put("Sarawak", new double[]{1.5533, 110.3592});
        states.put("Selangor", new double[]{3.0738, 101.5183});
        states.put("Terengganu", new double[]{5.3117, 103.1324});

        // Configure HTTP client with timeouts
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) // Connection timeout
                .readTimeout(20, TimeUnit.SECONDS)    // Read timeout
                .writeTimeout(20, TimeUnit.SECONDS)   // Write timeout
                .build();

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            for (Map.Entry<String, double[]> entry : states.entrySet()) {
                String stateName = entry.getKey();
                double[] coordinates = entry.getValue();

                // OpenMeteo API setup
                String apiUrl = String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current_weather=true",
                        coordinates[0], coordinates[1]);

                // Fetch data from the OpenMeteo API
                Request request = new Request.Builder().url(apiUrl).build();
                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful() && response.body() != null) {
                        String apiData = response.body().string();

                        // Send the API data to Kafka topic
                        producer.send(new ProducerRecord<>(topicName, stateName, apiData));
                        System.out.println("Sent to Kafka for " + stateName + ": " + apiData);
                    } else {
                        System.out.println("Failed to fetch API data for " + stateName + ". HTTP status: " + response.code());
                    }
                } catch (Exception e) {
                    System.out.println("Error fetching data for " + stateName);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//