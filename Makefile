set-up:
	@docker network create confluent-ecosystem

clean:
	@docker network rm confluent-ecosystem

ms-email-producer-build:
	@./gradlew clean ms-email-producer:build

ms-email-producer-up: ms-email-producer-build
	@docker-compose -f configs/ms-email-producer/docker-compose.yaml up --build --force-recreate -d

ms-email-producer-down:
	@docker-compose -f configs/ms-email-producer/docker-compose.yaml down

ms-email-producer-restart: ms-email-producer-down ms-email-producer-up

ms-email-consumer-build:
	@./gradlew clean ms-email-consumer:build

ms-email-consumer-up: ms-email-consumer-build
	@docker-compose -p ms-email-consumer -f configs/ms-email-consumer/docker-compose.yaml up --build --force-recreate -d --scale ms-email-consumer=2

ms-email-consumer-down:
	@docker-compose -f configs/ms-email-consumer/docker-compose.yaml down

ms-email-consumer-restart: ms-email-consumer-down ms-email-consumer-up

confluent-up:
	@docker-compose -f configs/confluent/docker-compose.yaml up --build --force-recreate -d

confluent-down:
	@docker-compose -f configs/confluent/docker-compose.yaml down

send-email-message:
	@curl -H "Content-Type: application/json" -X POST "http://localhost:8080/email-messages" -d '{"from":"no-reply@shahinnazarov.com","to":["me@shahinnazarov.com"],"cc":[],"bcc":[],"attachments":{},"content":"Test Content"}'

ms-email-consumer-logs:
	@docker-compose -f configs/ms-email-consumer/docker-compose.yaml logs --tail=100 --follow

up: confluent-up ms-email-consumer-up ms-email-producer-up

down: ms-email-producer-down ms-email-consumer-down confluent-down
