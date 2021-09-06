variable "number_of_transactions" {
    type    = number
    default = 3
}

variable "number_of_storages" {
    type    = number
    # Reduce by 1 as coordinator is acting as storage
    default = 2
}

variable "ami" {
    type    = string
    default = "ami-09e67e426f25ce0d7"
}

variable "instance_type" {
    type    = string
    default = "t3.micro"
}

variable "subnet_id" {
    type    = string
    default = "subnet-7dbc9230"
}

variable "volume_size" {
    type    = number
    default = 8
}

variable "volume_type" {
    type    = string
    default = "gp2"
}

variable "tag_prefix_name" {
    type    = string
    default = "fdb-test"
}

variable "tag_project" {
    type    = string
    default = "Test Project"
}

variable "tag_task" {
    type    = string
    default = "Test Task"
}

variable "security_group" {
    type    = list(string)
    default = [
		"sg-0c6f5cf2fab60ccd3"
	]
}

variable "key_name" {
    type    = string
    default = "ssh_personal"
}

variable "storage_conf" {
    type    = string
    default = <<EOT
## foundationdb.conf
##
## Configuration file for FoundationDB server processes
## Full documentation is available at
## https://apple.github.io/foundationdb/configuration.html#the-configuration-file

[fdbmonitor]
user = foundationdb
group = foundationdb

[general]
restart_delay = 60
## by default, restart_backoff = restart_delay_reset_interval = restart_delay
# initial_restart_delay = 0
# restart_backoff = 60
# restart_delay_reset_interval = 60
cluster_file = /etc/foundationdb/fdb.cluster
# delete_envvars =
# kill_on_configuration_change = true

## Default parameters for individual fdbserver processes
[fdbserver]
command = /usr/sbin/fdbserver
public_address = auto:$ID
listen_address = public
datadir = /var/lib/foundationdb/data/$ID
logdir = /var/log/foundationdb
# logsize = 10MiB
# maxlogssize = 100MiB
# machine_id =
# datacenter_id =
# class =
# memory = 8GiB
# storage_memory = 1GiB
# cache_memory = 2GiB
# metrics_cluster =
# metrics_prefix =

## An individual fdbserver process with id 4500
## Parameters set here override defaults from the [fdbserver] section
[fdbserver.4500]
class = stateless

[fdbserver.4502]
class = storage

[backup_agent]
command = /usr/lib/foundationdb/backup_agent/backup_agent
logdir = /var/log/foundationdb

[backup_agent.1]
EOT
}

variable "transaction_conf" {
    type    = string
    default = <<EOT
## foundationdb.conf
##
## Configuration file for FoundationDB server processes
## Full documentation is available at
## https://apple.github.io/foundationdb/configuration.html#the-configuration-file

[fdbmonitor]
user = foundationdb
group = foundationdb

[general]
restart_delay = 60
## by default, restart_backoff = restart_delay_reset_interval = restart_delay
# initial_restart_delay = 0
# restart_backoff = 60
# restart_delay_reset_interval = 60
cluster_file = /etc/foundationdb/fdb.cluster
# delete_envvars =
# kill_on_configuration_change = true

## Default parameters for individual fdbserver processes
[fdbserver]
command = /usr/sbin/fdbserver
public_address = auto:$ID
listen_address = public
datadir = /var/lib/foundationdb/data/$ID
logdir = /var/log/foundationdb
# logsize = 10MiB
# maxlogssize = 100MiB
# machine_id =
# datacenter_id =
# class =
# memory = 8GiB
# storage_memory = 1GiB
# cache_memory = 2GiB
# metrics_cluster =
# metrics_prefix =

## An individual fdbserver process with id 4500
## Parameters set here override defaults from the [fdbserver] section
[fdbserver.4500]
class = stateless

[fdbserver.4501]
class = transaction

[backup_agent]
command = /usr/lib/foundationdb/backup_agent/backup_agent
logdir = /var/log/foundationdb

[backup_agent.1]
EOT
}