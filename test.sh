#!/usr/bin/env bash

KEYCLOAK_URL="http://localhost:8085/auth"
KEYCLOAK_CLIENT_ID="controle-gastos-frontend"
KEYCLOAK_USERNAME="root"
KEYCLOAK_PASSWORD="root"
KEYCLOAK_GRANT_TYPE="password"
KEYCLOAK_REALM="CONTROLE-GASTOS"

TOKEN=$(curl -s -X POST "$KEYCLOAK_URL/realms/$KEYCLOAK_REALM/protocol/openid-connect/token" \
             -H 'Content-Type: application/x-www-form-urlencoded' \
             -d "client_id=$KEYCLOAK_CLIENT_ID" \
             -d "username=$KEYCLOAK_USERNAME" \
             -d "password=$KEYCLOAK_PASSWORD" \
             -d "grant_type=$KEYCLOAK_GRANT_TYPE" \ | jq -r '.access_token')

if [ "x$TOKEN" = "x" ] || [ "$TOKEN" = "null" ]; then
    echo "Erro ao retornar TOKEN de $KEYCLOAK_URL"
    echo "Verifique se o client $KEYCLOAK_CLIENT_ID está com a opção 'Direct Access Grants Enabled' habilitada."
    exit 1
fi

curl "$@" \
     -H "Authorization: Bearer $TOKEN" | jq
