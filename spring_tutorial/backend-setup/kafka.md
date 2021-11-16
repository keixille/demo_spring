```shell
sudo apt -y install openjdk-11-jdk
wget "https://dlcdn.apache.org/kafka/3.0.0/kafka_2.13-3.0.0.tgz"
mkdir kafka
cd kafka
tar -xvzf /home/ubuntu/kafka_2.13-3.0.0.tgz --strip 1
```

2. Change the Kafka configuration
```shell
sudo vi /home/ubuntu/kafka/config/server.properties
```
ec2-54-156-28-227.compute-1.amazonaws.com
```diff
***

#   FORMAT:
#     listeners = listener_name://host_name:port
#   EXAMPLE:
#     listeners = PLAINTEXT://your.host.name:9092
+ listeners=PLAINTEXT://ec2-174-129-71-73.compute-1.amazonaws.com:9092

# Hostname and port the broker will advertise to producers and consumers. If not set,
# it uses the value for "listeners" if configured.  Otherwise, it will use the value
# returned from java.net.InetAddress.getCanonicalHostName().
+ advertised.listeners=PLAINTEXT://ec2-174-129-71-73.compute-1.amazonaws.com:9092

***

- log.dirs=/tmp/kafka-logs
+ log.dirs=/home/ubuntu/kafka/logs

***

+ delete.topic.enable=true
```

3. Enable auto start zookeeper when instance restarted
```shell
sudo vi /etc/systemd/system/zookeeper.service
```

```service
[Unit]
Requires=network.target remote-fs.target
After=network.target remote-fs.target

[Service]
Type=simple
User=ubuntu
ExecStart=/home/ubuntu/kafka/bin/zookeeper-server-start.sh /home/ubuntu/kafka/config/zookeeper.properties
ExecStop=/home/ubuntu/kafka/bin/zookeeper-server-stop.sh
Restart=on-abnormal

[Install]
WantedBy=multi-user.target
```

4. Enable auto start kafka when instance restarted
```shell
sudo vi /etc/systemd/system/kafka.service
```

```service
[Unit]
Requires=zookeeper.service
After=zookeeper.service

[Service]
Type=simple
User=ubuntu
ExecStart=/bin/sh -c '/home/ubuntu/kafka/bin/kafka-server-start.sh /home/ubuntu/kafka/config/server.properties > /home/ubuntu/kafka/kafka.log 2>&1'
ExecStop=/home/ubuntu/kafka/bin/kafka-server-stop.sh
Restart=on-abnormal

[Install]
WantedBy=multi-user.target
```

sudo systemctl start kafka

sudo systemctl enable zookeeper
sudo systemctl enable kafka


/home/ubuntu/kafka/bin/kafka-topics.sh --create --topic test-topic --bootstrap-server ec2-54-156-28-227.compute-1.amazonaws.com:9092 --replication-factor 1 --partitions 3
/home/ubuntu/kafka/bin/kafka-topics.sh --describe --topic test-topic --bootstrap-server ec2-54-156-28-227.compute-1.amazonaws.com:9092
/home/ubuntu/kafka/bin/kafka-topics.sh --list --bootstrap-server ec2-54-156-28-227.compute-1.amazonaws.com:9092

/home/ubuntu/kafka/bin/kafka-console-producer.sh --topic test-topic --bootstrap-server ec2-54-156-28-227.compute-1.amazonaws.com:9092

/home/ubuntu/kafka/bin/kafka-console-consumer.sh --from-beginning --topic test-topic --bootstrap-server ec2-54-156-28-227.compute-1.amazonaws.com:9092
/home/ubuntu/kafka/bin/kafka-console-consumer.sh --topic test-topic --bootstrap-server ec2-54-156-28-227.compute-1.amazonaws.com:9092
/home/ubuntu/kafka/bin/kafka-console-consumer.sh --group my-application --topic test-topic --bootstrap-server ec2-54-156-28-227.compute-1.amazonaws.com:9092

/home/ubuntu/kafka/bin/kafka-console-producer.sh --topic test-topic --bootstrap-server ec2-54-156-28-227.compute-1.amazonaws.com:9092 --property parse.key=true --property key.separator=,
/home/ubuntu/kafka/bin/kafka-console-consumer.sh --property print.key=true --property key.separator=,