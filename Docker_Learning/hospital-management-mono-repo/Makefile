.PHONY: all

# List of directories to process
DIRS = api-gateway patient-service directory-service case-service appointment-service

# Default target
build_all:
	@for dir in $(DIRS); do \
		echo "Building $$dir..."; \
		cd $$dir && mvn clean install docker:build -Dmaven.test.skip=true && cd ..; \
	done

run_all:
	@for dir in $(DIRS); do \
		echo "Running $$dir..."; \
		docker run -dit --rm dnyanyog.org/$$dir:latest; \
	done
