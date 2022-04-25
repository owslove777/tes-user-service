# AMF user-service 구현 

- user생성

## 프로젝트 실행
```
mvn spring-boot:run
```

## Kafka 의 접속
* Kafka 의 실행 (Docker Compose)
```
cd kafka
docker-compose up -d     # docker-compose 가 모든 kafka 관련 리소스를 받고 실행할 때까지 기다림
```
* Kafka 정상 실행 확인
```
$ docker-compose logs kafka | grep -i started    

kafka-kafka-1  | [2022-04-21 22:07:03,262] INFO [KafkaServer id=1] started (kafka.server.KafkaServer)
```
* Kafka topic list 확인 및  consumer 접속
```
./kafka-topics --bootstrap-server localhost:9092 --list
./kafka-console-consumer --bootstrap-server localhost:9092 --topic userservice

```
#### 이슈해결 : docker-compose로 kafka 수행오류해결 전  local로 수행
```
C:\kafka_2.12-2.7.0\bin\windows\zookeeper-server-start.bat C:\kafka_2.12-2.7.0\config\zookeeper.properties  
C:\kafka_2.12-2.7.0\bin\windows\kafka-server-start.bat C:\kafka_2.12-2.7.0\config\server.properties   
C:\kafka_2.12-2.7.0\bin\windows\kafka-topics.bat --create --topic userservice --bootstrap-server localhost:9092 --partitions 1
C:\kafka_2.12-2.7.0\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic userservice --from-beginning

C:\Users\kyk>C:\kafka_2.12-2.7.0\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic userservice --from-beginning
{"eventType":"UserCreated","timestamp":1650816862758,"userId":"14cea77c-bccf-44a2-acde-ef5030bdad7f","status":"valid"}


```
