run:
	mvn spring-boot:run

dev:
	mvn spring-boot:run -Dspring-boot.run.profiles=dev -e -X

build:
	mvn clean package

test:
	mvn test

setup-db:
	docker run -d \
		--name postgres \
		-e POSTGRES_USER=postgres \
		-e POSTGRES_PASSWORD=postgres \
		-e POSTGRES_DB=mini_crm \
		-p 5432:5432 postgres:15

down-db:
	docker rm -f postgres
