#!/bin/bash

export SENSITIVE_CREDENTIALS=/secure/creds
#export PORT=8082
# Script to fetch credentials from the Credential Store
#SECRET_NAME="MYSQL_ROOT_PASSWORD"
#SECRET_VALUE=$(aws secretsmanager get-secret-value --secret-id $SECRET_NAME --query SecretString --output text)
#export $SENSITIVE_CREDENTIALS="$SECRET_VALUE"

echo "############ Running as admin ##############"
exec java -jar /app/hello-service.jar



