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

	vpc_security_group_ids = var.security_group
	
	key_name = var.key_name

	provisioner "file" {
		connection {
			type		= "ssh"
			host		= self.private_ip
			user		= "ubuntu"
			private_key	= file("./provision/${var.key_name}.pem")
		}

		source      = "./provision"
		destination = "~/"
	}
	
	provisioner "remote-exec" {
		connection {
			type		= "ssh"
			host		= self.private_ip
			user		= "ubuntu"
			private_key	= file("./provision/${var.key_name}.pem")
		}
		
		inline = [
			"sudo chmod -R 777 ~/provision/scripts/",
			# "sudo ~/provision/install-fdbtop.sh",
			"sudo ~/provision/scripts/install-fdb.sh",
			"sudo cp ~/provision/confs/storage-ebs.conf /etc/foundationdb/foundationdb.conf",
			"fdbcli --exec 'configure single ssd'",
			"sudo python3 /usr/lib/foundationdb/make_public.py",
		]
	}
}

resource "aws_instance" "fdb_storages" {
	depends_on = [aws_instance.fdb_coordinator]
	for_each = toset(formatlist("%d", range(1, var.number_of_storages + 1)))

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

	vpc_security_group_ids = var.security_group
	
	key_name = var.key_name

	provisioner "file" {
		connection {
			type		= "ssh"
			host		= self.private_ip
			user		= "ubuntu"
			private_key	= file("./provision/${var.key_name}.pem")
		}

		source      = "./provision"
		destination = "~/"
	}
	
	provisioner "remote-exec" {
		connection {
			type		= "ssh"
			host		= self.private_ip
			user		= "ubuntu"
			private_key	= file("./provision/${var.key_name}.pem")
		}
		
		inline = [
			"sudo chmod -R 777 ~/provision/scripts/",
			"sudo ~/provision/scripts/install-fdb.sh",
			"sudo cp ~/provision/confs/storage-ebs.conf /etc/foundationdb/foundationdb.conf",
			"fdbcli --exec 'configure single ssd'",
			"sudo chmod 600 ~/provision/${var.key_name}.pem",
			"scp -3 -i ~/provision/${var.key_name}.pem -o StrictHostKeyChecking=no ubuntu@${aws_instance.fdb_coordinator.private_ip}:/etc/foundationdb/fdb.cluster ubuntu@${self.private_ip}:~/fdb.cluster",
			"sudo mv -f ~/fdb.cluster /etc/foundationdb/fdb.cluster",
			"sudo service foundationdb restart"
		]
	}
}

resource "aws_instance" "fdb_transactions" {
	depends_on = [aws_instance.fdb_coordinator]
	for_each = toset(formatlist("%d", range(1, var.number_of_transactions + 1)))

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

	vpc_security_group_ids = var.security_group
	
	key_name = var.key_name

	provisioner "file" {
		connection {
			type		= "ssh"
			host		= self.private_ip
			user		= "ubuntu"
			private_key	= file("./provision/${var.key_name}.pem")
		}

		source      = "./provision"
		destination = "~/"
	}
	
	provisioner "remote-exec" {
		connection {
			type		= "ssh"
			host		= self.private_ip
			user		= "ubuntu"
			private_key	= file("./provision/${var.key_name}.pem")
		}
		
		inline = [
			"sudo chmod -R 777 ~/provision/scripts/",
			"sudo ~/provision/scripts/install-fdb.sh",
			"sudo cp ~/provision/confs/transaction-ebs.conf /etc/foundationdb/foundationdb.conf",
			"fdbcli --exec 'configure single ssd'",
			"sudo chmod 600 ~/provision/${var.key_name}.pem",
			"scp -3 -i ~/provision/${var.key_name}.pem -o StrictHostKeyChecking=no ubuntu@${aws_instance.fdb_coordinator.private_ip}:/etc/foundationdb/fdb.cluster ubuntu@${self.private_ip}:~/fdb.cluster",
			"sudo mv -f ~/fdb.cluster /etc/foundationdb/fdb.cluster",
			"sudo service foundationdb restart"
		]
	}
}