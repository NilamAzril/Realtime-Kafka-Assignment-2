package my.uum;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Consumer {
    public static void main(String[] args) {
        String topicName = "api-data"; // Kafka topic name

        // Kafka consumer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "139.59.239.225:29092"); // Kafka broker address (PC1 IP)
        props.put("group.id", "weather-consumer-group"); // Unique group ID
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest"); // Start from the beginning if no previous offset exists

        // Create Kafka consumer
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            // Subscribe to the topic
            consumer.subscribe(Collections.singletonList(topicName));
            System.out.println("Subscribed to topic: " + topicName);

            // Set to track processed states
            Set<String> processedStates = new HashSet<>();

            // Poll for messages
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

                if (!records.isEmpty()) {
                    for (ConsumerRecord<String, String> record : records) {
                        String stateName = record.key(); // State name as the key
                        String receivedMessage = record.value(); // JSON payload

                        // Skip if state has already been processed
                        if (processedStates.contains(stateName)) {
                            continue;
                        }

                        processedStates.add(stateName);
                        System.out.println("Processing message for state: " + stateName);

                        try {
                            // Parse JSON data
                            JsonObject jsonObject = JsonParser.parseString(receivedMessage).getAsJsonObject();

                            // Extract latitude and longitude
                            double latitude = jsonObject.get("latitude").getAsDouble();
                            double longitude = jsonObject.get("longitude").getAsDouble();

                            // Extract current weather data
                            if (jsonObject.has("current_weather")) {
                                JsonObject weatherData = jsonObject.getAsJsonObject("current_weather");

                                // Extract temperature and windspeed
                                double temperature = weatherData.get("temperature").getAsDouble();
                                double windSpeed = weatherData.get("windspeed").getAsDouble();

                                // Print state and weather information
                                System.out.println("State: " + stateName);
                                System.out.println("Latitude: " + latitude + ", Longitude: " + longitude);
                                System.out.println("Current Temperature: " + temperature + "°C");
                                System.out.println("Wind Speed: " + windSpeed + " km/h");
                                System.out.println("-------------------------------------");
                            } else {
                                System.out.println("No weather data found for state: " + stateName);
                            }
                        } catch (Exception e) {
                            System.err.println("Error processing message for state: " + stateName);
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error while consuming messages: " + e.getMessage());
            e.printStackTrace();
        }
    }
}