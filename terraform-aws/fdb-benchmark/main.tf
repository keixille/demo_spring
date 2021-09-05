terraform {
	required_providers {
		aws = {
			source  = "hashicorp/aws"
			version = "~> 3.27"
		}
	}
	required_version = ">= 0.14.9"
}

provider "aws" {
	profile = "default"
	region  = "us-east-1"
}

resource "aws_instance" "fdb_coordinator" {
	ami           = var.ami
	instance_type = var.instance_type
	
	subnet_id = var.subnet_id
	
	root_block_device {
		volume_size = var.volume_size
		volume_type = var.volume_type
	}
	
	tags = {
		Name	= "${var.tag_prefix_name}-coor"
		Project	= var.tag_project
		Task	= var.tag_task
	}

	vpc_security_group_ids = [
		"sg-0c6f5cf2fab60ccd3"
	]
	
	key_name = "ssh_personal"
	
	provisioner "remote-exec" {
		connection {
			type		= "ssh"
			host		= self.private_ip
			user		= "ubuntu"
			private_key	= file("./ssh_personal.pem")
		}
		
		inline = [
			"sudo apt update",
			"sudo apt -y install npm",
			"sudo npm install fdbtop",
			"sudo wget https://www.foundationdb.org/downloads/6.3.15/ubuntu/installers/foundationdb-clients_6.3.15-1_amd64.deb",
			"sudo wget https://www.foundationdb.org/downloads/6.3.15/ubuntu/installers/foundationdb-server_6.3.15-1_amd64.deb",
			"sudo dpkg -i foundationdb-clients_6.3.15-1_amd64.deb foundationdb-server_6.3.15-1_amd64.deb",
			"fdbcli --exec 'configure single ssd'",
			"sudo python3 /usr/lib/foundationdb/make_public.py",
			"echo '${var.storage_conf}' | sudo tee /etc/foundationdb/foundationdb.conf"
		]
	}
}

resource "aws_instance" "fdb_storages" {
	# depends_on = [aws_instance.fdb_coordinator]
	for_each = toset(formatlist("%d", range(1, var.number_of_storages)))

	ami           = var.ami
	instance_type = var.instance_type
	
	subnet_id = var.subnet_id
	
	root_block_device {
		volume_size = var.volume_size
		volume_type = var.volume_type
	}
	
	tags = {
		Name	= "${var.tag_prefix_name}-${each.key}"
		Project	= var.tag_project
		Task	= var.tag_task
	}

	vpc_security_group_ids = [
		"sg-0c6f5cf2fab60ccd3"
	]
	
	key_name = "ssh_personal"

	provisioner "file" {
		connection {
			type		= "ssh"
			host		= self.private_ip
			user		= "ubuntu"
			private_key	= file("./ssh_personal.pem")
		}

		source      = "./ssh_personal.pem"
		destination = "~/ssh_personal.pem"
	}
	
	provisioner "remote-exec" {
		connection {
			type		= "ssh"
			host		= self.private_ip
			user		= "ubuntu"
			private_key	= file("./ssh_personal.pem")
		}
		
		inline = [
			"sudo wget https://www.foundationdb.org/downloads/6.3.15/ubuntu/installers/foundationdb-clients_6.3.15-1_amd64.deb",
			"sudo wget https://www.foundationdb.org/downloads/6.3.15/ubuntu/installers/foundationdb-server_6.3.15-1_amd64.deb",
			"sudo dpkg -i foundationdb-clients_6.3.15-1_amd64.deb foundationdb-server_6.3.15-1_amd64.deb",
			"fdbcli --exec 'configure single ssd'",
			"echo '${var.storage_conf}' | sudo tee /etc/foundationdb/foundationdb.conf",
			"sudo chmod 600 ~/ssh_personal.pem",
			"scp -3 -i ~/ssh_personal.pem -o StrictHostKeyChecking=no ubuntu@${aws_instance.fdb_coordinator.private_ip}:/etc/foundationdb/fdb.cluster ubuntu@${self.private_ip}:~/fdb.cluster",
			"sudo mv -f ~/fdb.cluster /etc/foundationdb/fdb.cluster",
			"sudo service foundationdb restart"
		]
	}
}

resource "aws_instance" "fdb_transactions" {
	depends_on = [aws_instance.fdb_coordinator]
	for_each = toset(formatlist("%d", range(1, var.number_of_transactions)))

	ami           = var.ami
	instance_type = var.instance_type
	
	subnet_id = var.subnet_id
	
	root_block_device {
		volume_size = var.volume_size
		volume_type = var.volume_type
	}
	
	tags = {
		Name	= "${var.tag_prefix_name}-${each.key}"
		Project	= var.tag_project
		Task	= var.tag_task
	}

	vpc_security_group_ids = [
		"sg-0c6f5cf2fab60ccd3"
	]
	
	key_name = "ssh_personal"

	provisioner "file" {
		connection {
			type		= "ssh"
			host		= self.private_ip
			user		= "ubuntu"
			private_key	= file("./ssh_personal.pem")
		}

		source      = "./ssh_personal.pem"
		destination = "~/ssh_personal.pem"
	}
	
	provisioner "remote-exec" {
		connection {
			type		= "ssh"
			host		= self.private_ip
			user		= "ubuntu"
			private_key	= file("./ssh_personal.pem")
		}
		
		inline = [
			"sudo wget https://www.foundationdb.org/downloads/6.3.15/ubuntu/installers/foundationdb-clients_6.3.15-1_amd64.deb",
			"sudo wget https://www.foundationdb.org/downloads/6.3.15/ubuntu/installers/foundationdb-server_6.3.15-1_amd64.deb",
			"sudo dpkg -i foundationdb-clients_6.3.15-1_amd64.deb foundationdb-server_6.3.15-1_amd64.deb",
			"fdbcli --exec 'configure single ssd'",
			"echo '${var.storage_conf}' | sudo tee /etc/foundationdb/foundationdb.conf",
			"sudo chmod 600 ~/ssh_personal.pem",
			"scp -3 -i ~/ssh_personal.pem -o StrictHostKeyChecking=no ubuntu@${aws_instance.fdb_coordinator.private_ip}:/etc/foundationdb/fdb.cluster ubuntu@${self.private_ip}:~/fdb.cluster",
			"sudo mv -f ~/fdb.cluster /etc/foundationdb/fdb.cluster",
			"sudo service foundationdb restart"
		]
	}
}