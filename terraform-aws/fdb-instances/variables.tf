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