# Real-Time Messaging with Apache Kafka in KRaft Mode

## Description
This project implements a real-time weather data pipeline using Apache Kafka and the OpenMeteo API. It demonstrates the integration of real-time data streaming and processing, which is critical in modern applications. The system efficiently ingests, processes, and visualizes weather data for decision-making.

## Installation
```bash
# Install dependencies
npm install
```

## Usage
1. **Producer**: Fetches weather data using the OpenMeteo API and sends it to a Kafka topic.
2. **Consumer**: Consumes weather data from Kafka, parses JSON data to extract relevant weather attributes, and displays temperature, wind speed, and location.

## Contributing
Contributions are always welcome!

## License
This project is licensed under the MIT License - see the LICENSE file for details.


## Your Info:
1. - Matric Number: 296015
   - Name: Nilam Muhammad Azrilluqman Bin Jaafar
   - Phone Number: 019-7273725     
     
2. - Matric Number : 288449
   - Name : Muhammad Akmal Hisyam Bin Riza
   - Phone Number : 012-5158123
   

## Title: Real-Time Messaging with Apache Kafka in KRaft Mode
## Abstract (in 300 words)
   1. Background
      - The integration of real-time data streaming and processing is critical in modern applications.
      - Kafka serves as a robust messaging platform for real-time data pipelines.
      - OpenMeteo provides API-based weather forecasting, making it suitable for real-time weather data applications.
   2. Problem Statement (from article)
      - Real-time weather data needs efficient ingestion, processing, and visualization for decision-making.
      - A reliable pipeline between an API and a Kafka cluster is required.
      - Parsing and presenting the data meaningfully for end-users is a challenge.
   3. Main objective
      - To design and implement a real-time weather data pipeline.
      - Use Kafka for efficient data transmission.
      - Retrieve and process weather data from OpenMeteo API.
   5. Methodology
      - Producer Implementation:
        - Fetch weather data using OpenMeteo API.
        - Serialize and send the data to a Kafka topic (api-data).
      - Consumer Implementation:
        - Consume weather data from Kafka.
        - Parse JSON data to extract relevant weather attributes.
        - Display temperature, wind speed, and location.
      - Tools and Technologies:
        - Kafka for messaging.
        - OpenMeteo API for weather data.
        - Gson library for JSON parsing.
        - Java for implementation.
   7. Result
      - Successfully ingested real-time weather data into Kafka.
      - Extracted, parsed, and displayed meaningful weather metrics from the JSON API data.
      - Demonstrated the feasibility of a real-time weather data pipeline.
   9. Conclusion
      - Kafka and OpenMeteo API provide a scalable and effective solution for real-time weather data processing.
      - The developed pipeline can be extended to handle more complex use cases like predictive analysis or integration with IoT devices.


## References (Not less than 10)
   1. Apache Kafka Documentation - https://kafka.apache.org/documentation/
   2. OpenMeteo API Documentation - https://open-meteo.com/en/docs
   3. OkHttp Client Documentation - https://square.github.io/okhttp/
   4. Gson User Guide - https://github.com/google/gson/blob/master/UserGuide.md
   5. Java Kafka Client Library - https://kafka.apache.org/quickstart
   6. JSON Parsing in Java with Gson - https://www.baeldung.com/gson
   7. Real-Time Data Streaming with Kafka - https://medium.com/stream-processing
   8. REST API Design Best Practices - https://restfulapi.net/
   9. Weather Data Use Cases in IoT - https://www.iotforall.com/weather-data-in-iot
   10. Java Consumer and Producer APIs for Kafka - https://www.confluent.io/blog
   11. Implementing Messaging Systems - https://dzone.com/articles/messaging-systems
   12. Real-Time Weather Forecast Systems - https://journals.sagepub.com/weather-forecast

## Result/Output (Screenshot of the output)
producer: ![image](https://github.com/user-attachments/assets/4f9d9b7a-9c54-448a-9446-5ab85f308fe3)
consumer: ![Screenshot 2024-12-01 173710](https://github.com/user-attachments/assets/23b319ef-edff-46a2-8cfe-223b4d011a10)



