.PHONY: local-app local-app-down local-db local-db-down local-network

local-start: local-app local-db

local-down: local-app-downmake local-db-down

local-network:
	docker network create lucycato_network
local-app:
	./gradlew docker && docker compose -f docker-compose.yml up -d


local-app-down:
	docker compose -f docker-compose.yml down

local-db:
	docker compose -f docker-compose-db.yml up -d

local-db-down:
	docker compose -f docker-compose-db.yml down
