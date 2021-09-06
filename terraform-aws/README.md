# How to use
### 1. Install AWS CLI and Terraform by run the script
```
chmod 777 ${filename}
sudo ./${filename}
```
| File | Description | Verify |
| --- | --- | --- |
| install-aws-cli2.sh | Install AWS CLI | aws --version |
| install-terraform.sh | Install Terraform | terraform -v |

### 2. Copy project folder that you want to use

| Folder | Description |
| --- | ----------- |
| fdb-instances | Create 1 FDB cluster |

### 3. Type the terraform command to the console in respective folder

| Command | Description |
| --- | ----------- |
| init | Initialize module and state |
| plan | Checking script syntax |
| apply | Run the script and save current state |
| destroy | Terminate the current state |
