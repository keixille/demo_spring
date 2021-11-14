1. Install Redis
```shell
sudo apt update
sudo apt -y install redis-server
```

2. Change configuration file to listen all interface and disable protection
```shell
sudo vi /etc/redis/redis.conf
```

```shell
***

# IF YOU ARE SURE YOU WANT YOUR INSTANCE TO LISTEN TO ALL THE INTERFACES
# JUST COMMENT THE FOLLOWING LINE.
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- bind 127.0.0.1 ::1
+ # bind 127.0.0.1 ::1

***
# By default protected mode is enabled. You should disable it only if
# you are sure you want clients from other hosts to connect to Redis
# even if no authentication is configured, nor a specific set of interfaces
# are explicitly listed using the "bind" directive.
- protected-mode yes
+ protected-mode no

***
```

3. Restart the Redis service
```shell
sudo service redis-server restart
```