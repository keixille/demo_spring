wget https://www.foundationdb.org/downloads/6.3.12/ubuntu/installers/foundationdb-clients_6.3.12-1_amd64.deb
wget https://www.foundationdb.org/downloads/6.3.12/ubuntu/installers/foundationdb-server_6.3.12-1_amd64.deb

sudo dpkg -i foundationdb-clients_6.3.12-1_amd64.deb \
foundationdb-server_6.3.12-1_amd64.deb

sudo rm foundationdb-clients_6.3.12-1_amd64.deb
sudo rm foundationdb-server_6.3.12-1_amd64.deb

sudo apt install -Y python3

sudo /usr/lib/foundationdb/make_public.py