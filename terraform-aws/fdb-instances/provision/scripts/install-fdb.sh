#!/bin/bash

wget https://www.foundationdb.org/downloads/6.3.15/ubuntu/installers/foundationdb-clients_6.3.15-1_amd64.deb
wget https://www.foundationdb.org/downloads/6.3.15/ubuntu/installers/foundationdb-server_6.3.15-1_amd64.deb
dpkg -i foundationdb-clients_6.3.15-1_amd64.deb foundationdb-server_6.3.15-1_amd64.deb