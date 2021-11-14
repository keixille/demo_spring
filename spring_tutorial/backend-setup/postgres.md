1. Install PostgreSQL
```shell
sudo apt-get update
sudo apt-get -y install postgresql-12
```

2. Change the PostgreSQL configuration file to listen all address
```shell
sudo vi /etc/postgresql/12/main/postgresql.conf
```

```diff
***

# - Connection Settings -

- #listen_addresses = 'localhost'
+ listen_addresses = '*'

port = 5432

***
```

3. Change the host based authentication configuration to bypass password authentication
```shell
sudo vi /etc/postgresql/12/main/pg_hba.conf
```

```diff
***

# Database administrative login by Unix domain socket

- local   all             postgres                                peer
+ local   all             postgres                                md5

# TYPE  DATABASE        USER            ADDRESS                 METHOD

***

# IPv4 local connections:
host    all             all             127.0.0.1/32            md5
+ host    all             all             all                     md5

***
```

4. Login with "postgres" user
```shell
sudo -u postgres psql postgres
```
Type new password for postgres user
```shell
postgres=# \password postgres

Enter new password: 
Enter it again: 
```

5. Change the host based authentication configuration to enable password authentication
```shell
sudo vi /etc/postgresql/12/main/pg_hba.conf
```

```diff
***

# Database administrative login by Unix domain socket

- local   all             postgres                              trust
+ local   all             postgres                              md5

# TYPE  DATABASE        USER            ADDRESS                 METHOD

***
```

6. Restart the PostgreSQL service
```shell
sudo service postgresql restart
```

```sql
CREATE TABLE IF NOT EXISTS customer (
user_id BIGSERIAL PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL
);
```
