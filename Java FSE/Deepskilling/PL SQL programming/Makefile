COMPOSE := docker compose
SQLPLUS := sqlplus -s system/Oracle123@//localhost:1521/XEPDB1

.PHONY: up down logs status wait init schema run run1 run2 run3 run4 run5 run6 run7 clean

up:
	$(COMPOSE) up -d --wait oracle

down:
	$(COMPOSE) down

logs:
	$(COMPOSE) logs -f oracle

status:
	$(COMPOSE) ps

wait:
	@echo "Oracle XE is ready."

schema: up
	$(COMPOSE) exec -T oracle bash -lc "printf 'exit\n' | $(SQLPLUS) @/workspace/00_schema_and_sample_data.sql"

run: up
	@if [ -z "$(FILE)" ]; then echo "Usage: make run FILE=01_control_structures.sql"; exit 1; fi
	$(COMPOSE) exec -T oracle bash -lc "printf 'exit\n' | $(SQLPLUS) @/workspace/$(FILE)"

run1: schema
	$(COMPOSE) exec -T oracle bash -lc "printf 'exit\n' | $(SQLPLUS) @/workspace/01_control_structures.sql"

run2: schema
	$(COMPOSE) exec -T oracle bash -lc "printf 'exit\n' | $(SQLPLUS) @/workspace/02_error_handling.sql"

run3: schema
	$(COMPOSE) exec -T oracle bash -lc "printf 'exit\n' | $(SQLPLUS) @/workspace/03_stored_procedures.sql"

run4: schema
	$(COMPOSE) exec -T oracle bash -lc "printf 'exit\n' | $(SQLPLUS) @/workspace/04_functions.sql"

run5: schema
	$(COMPOSE) exec -T oracle bash -lc "printf 'exit\n' | $(SQLPLUS) @/workspace/05_triggers.sql"

run6: schema
	$(COMPOSE) exec -T oracle bash -lc "printf 'exit\n' | $(SQLPLUS) @/workspace/06_cursors.sql"

run7: schema
	$(COMPOSE) exec -T oracle bash -lc "printf 'exit\n' | $(SQLPLUS) @/workspace/07_packages.sql"

clean: down
	$(COMPOSE) down -v
