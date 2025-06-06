package windeath44.orchestration;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // 테스트 프로파일 사용
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class KafkaConnectionTest {

  private static final String TEST_TOPIC = "test-topic";
  private static final String TEST_MESSAGE = "Hello, Kafka!";

  @Autowired
  private ProducerFactory<String, String> producerFactory;

  @Autowired
  private ConsumerFactory<String, String> consumerFactory;

  @Test
  public void testKafkaConnection() throws InterruptedException, ExecutionException, TimeoutException {
    // Producer 생성
    try (Producer<String, String> producer = producerFactory.createProducer()) {
      // 메시지 전송
      ProducerRecord<String, String> record = new ProducerRecord<>(TEST_TOPIC, TEST_MESSAGE);
      RecordMetadata metadata = producer.send(record).get(5, TimeUnit.SECONDS);

      assertNotNull(metadata, "메시지 메타데이터는 null이 아니어야 합니다");
      assertEquals(TEST_TOPIC, metadata.topic(), "토픽 이름이 일치해야 합니다");
    }

    // Consumer 생성
    try (Consumer<String, String> consumer = consumerFactory.createConsumer(UUID.randomUUID().toString(), "test-group")) {
      // 테스트 토픽 구독
      consumer.subscribe(java.util.Collections.singletonList(TEST_TOPIC));

      // 메시지 소비
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));

      for (ConsumerRecord<String, String> record : records) {
        System.out.println("Received message:");
        System.out.println("  Topic: " + record.topic());
        System.out.println("  Partition: " + record.partition());
        System.out.println("  Offset: " + record.offset());
        System.out.println("  Key: " + record.key());
        System.out.println("  Value: " + record.value());
        System.out.println("  Timestamp: " + record.timestamp());
        System.out.println("----------------------------------");
      }

      assertFalse(records.isEmpty(), "수신된 메시지가 없습니다");
      assertEquals(TEST_MESSAGE, records.iterator().next().value(), "메시지 내용이 일치해야 합니다");
    }
  }

  @Test
  public void testKafkaConfiguration() {
    assertNotNull(producerFactory, "ProducerFactory가 주입되어야 합니다");
    assertNotNull(consumerFactory, "ConsumerFactory가 주입되어야 합니다");
  }
}