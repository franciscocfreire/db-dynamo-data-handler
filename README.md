### Pre-req
- Docker
- Terraform
- AWS CLI
- [download DynamoDB Workbench](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/workbench.settingup.html)

### Install Terraform
```
curl -fsSL https://apt.releases.hashicorp.com/gpg | sudo apt-key add -
sudo apt-add-repository "deb [arch=amd64] https://apt.releases.hashicorp.com $(lsb_release -cs) main"
sudo apt-get update && sudo apt-get install -y terraform
terraform -v
which terraform
```

### Configure DynamoDB Workbench
- Click Amazon DynamoDB Launch
- Select Operation builder
- Add connection
- Select DynamoDB local
- Chose a name connection, hostname = localhost, port = 4566
