.PHONY: local-network local local-down local-restart local-db local-db-down local-db-restart local-app local-app-down local-app-restart local-eureka local-eureka-down local-eureka-restart local-gateway local-gateway-down local-gateway-restart local-board-c local-board-c-down local-board-c-restart local-board-q local-board-q-down local-board-q-restart local-notification-c local-notification-c-down local-notification-c-restart local-notification-q local-notification-q-down local-notification-q-restart local-order-c local-order-c-down local-order-c-restart local-order-q local-order-q-down local-order-q-restart local-product-c local-product-c-down local-product-c-restart local-product-q local-product-q-down local-product-q-restart local-user-auth-c local-user-auth-c-down local-user-auth-c-restart local-user-auth-q local-user-auth-q-down local-user-auth-q-restart

local-network:
	docker network create lucycato_network
local:
	make local-db
	make local-app
local-down:
	make local-app-down
	make local-db-down
local-restart:
	make local-down
	make local

local-db:
	docker compose -f docker-compose-db.yml up -d
local-db-down:
	docker compose -f docker-compose-db.yml down
local-db-restart:
	make local-db-down
	make local-db

local-app:
	./gradlew docker && docker compose -f docker-compose-infra.yml up -d && docker compose -f docker-compose.yml up -d
local-app-down:
	docker compose -f docker-compose.yml down && docker compose -f docker-compose-infra.yml down
local-app-restart:
	make local-app-down
	make local-app

local-eureka:
	./gradlew eureka-server:docker && docker compose -f docker-compose-infra.yml up -d eureka-server
local-eureka-down:
	docker compose -f docker-compose-infra.yml down eureka-server
local-eureka-restart:
	make local-eureka-down
	make local-eureka

local-gateway:
	./gradlew gateway-server:docker && docker compose -f docker-compose-infra.yml up -d gateway-server
local-gateway-down:
	docker compose -f docker-compose-infra.yml down gateway-server
local-gateway-restart:
	make local-gateway-down
	make local-gateway

local-board-c:
	./gradlew board-command-service:docker && docker compose -f docker-compose.yml up -d board-command-service
local-board-c-down:
	docker compose -f docker-compose.yml down board-command-service
local-board-c-restart:
	make local-board-c-down
	make local-board-c

local-board-q:
	./gradlew board-query-service:docker && docker compose -f docker-compose.yml up -d board-query-service
local-board-q-down:
	docker compose -f docker-compose.yml down board-query-service
local-board-q-restart:
	make local-board-q-down
	make local-board-q

local-notification-c:
	./gradlew notification-command-service:docker && ./gradlew board-command-service:docker && docker compose -f docker-compose.yml up -d notification-command-service
local-notification-c-down:
	docker compose -f docker-compose.yml down notification-command-service
local-notification-c-restart:
	make local-notification-c-down
	make local-notification-c

local-notification-q:
	./gradlew notification-query-service:docker && docker compose -f docker-compose.yml up -d notification-query-service
local-notification-q-down:
	docker compose -f docker-compose.yml down notification-query-service
local-notification-q-restart:
	make local-notification-q-down
	make local-notification-q

local-order-c:
	./gradlew order-command-service:docker && docker compose -f docker-compose.yml up -d order-command-service
local-order-c-down:
	docker compose -f docker-compose.yml down order-command-service
local-order-c-restart:
	make local-order-c-down
	make local-order-c

local-order-q:
	./gradlew order-query-service:docker && docker compose -f docker-compose.yml up -d order-query-service
local-order-q-down:
	docker compose -f docker-compose.yml down order-query-service
local-order-q-restart:
	make local-order-q-down
	make local-order-q

local-product-c:
	./gradlew product-command-service:docker && docker compose -f docker-compose.yml up -d product-command-service
local-product-c-down:
	docker compose -f docker-compose.yml down product-command-service
local-product-c-restart:
	make local-product-c-down
	make local-product-c

local-product-q:
	./gradlew product-query-service:docker && docker compose -f docker-compose.yml up -d product-query-service
local-product-q-down:
	docker compose -f docker-compose.yml down product-query-service
local-product-q-restart:
	make local-product-q-down
	make local-product-q

local-user-auth-c:
	./gradlew user-auth-command-service:docker && docker compose -f docker-compose.yml up -d user-auth-command-service
local-user-auth-c-down:
	docker compose -f docker-compose.yml down user-auth-command-service
local-user-auth-c-restart:
	make local-user-auth-c-down
	make local-user-auth-c

local-user-auth-q:
	./gradlew user-auth-query-service:docker && docker compose -f docker-compose.yml up -d user-auth-query-service
local-user-auth-q-down:
	docker compose -f docker-compose.yml down user-auth-query-service
local-user-auth-q-restart:
	make local-user-auth-q-down
	make local-user-auth-q
