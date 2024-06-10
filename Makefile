.PHONY: local-network local local-down local-restart local-db local-db-down local-db-restart local-app local-app-down local-app-restart service service-down service-restart

SERVICES := eureka gateway board-command-service board-query-service notification-command-service notification-query-service order-command-service order-query-service product-command-service product-query-service user-auth-command-service user-auth-query-service

local-network:
	docker network create lucycato_network

local: local-db local-app

local-down: local-app-down local-db-down

local-restart: local-down local

local-db:
	docker compose -f docker-compose-db.yml up -d

local-db-down:
	docker compose -f docker-compose-db.yml down

local-db-restart: local-db-down local-db

local-app:
	./gradlew docker
	docker compose -f docker-compose-infra.yml up -d
	docker compose -f docker-compose.yml up -d

local-app-down:
	docker compose -f docker-compose.yml down
	docker compose -f docker-compose-infra.yml down

local-app-restart: local-app-down local-app

# make service-restart service=eureka-server
service:
	./gradlew $(service):docker
	docker compose -f docker-compose.yml up -d $(service)

service-down:
	docker compose -f docker-compose.yml down $(service)

service-restart: service-down service

$(foreach svc, $(SERVICES), \
  $(eval .PHONY: $(svc) $(svc)-down $(svc)-restart) \
  $(eval $(svc): service) \
  $(eval $(svc)-down: service-down) \
  $(eval $(svc)-restart: service-restart) \
)
