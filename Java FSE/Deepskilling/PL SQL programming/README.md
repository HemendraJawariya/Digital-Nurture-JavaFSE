# PL SQL Programming Exercises

This folder contains Oracle PL/SQL exercises split by question, plus a Docker-based local runtime so you can execute everything without manual Oracle installation.

## Files

- [00_schema_and_sample_data.sql](00_schema_and_sample_data.sql) - schema reset + sample data
- [01_control_structures.sql](01_control_structures.sql) - Exercise 1
- [02_error_handling.sql](02_error_handling.sql) - Exercise 2
- [03_stored_procedures.sql](03_stored_procedures.sql) - Exercise 3
- [04_functions.sql](04_functions.sql) - Exercise 4
- [05_triggers.sql](05_triggers.sql) - Exercise 5
- [06_cursors.sql](06_cursors.sql) - Exercise 6
- [07_packages.sql](07_packages.sql) - Exercise 7
- [docker-compose.yml](docker-compose.yml) - Oracle XE container setup
- [Makefile](Makefile) - run commands

## Prerequisites

1. Docker installed and running.
2. Docker Compose v2 available (`docker compose version`).
3. `make` available (`make --version`).

## Full Run Process

1. Open terminal in this folder:

```bash
cd "/workspaces/Digital-Nurture-JavaFSE/Java FSE/Deepskilling/PL SQL programming"
```

2. Start Oracle XE container:

```bash
make up
```

3. Create/reset schema and insert sample data:

```bash
make schema
```

4. Run an exercise:

```bash
make run1
```

or run any specific file:

```bash
make run FILE=02_error_handling.sql
make run FILE=03_stored_procedures.sql
make run FILE=04_functions.sql
make run FILE=05_triggers.sql
make run FILE=06_cursors.sql
make run FILE=07_packages.sql
```

## Easy Cases 

Use these exact commands first:

```bash
make up
make schema
make run1
```

Then open SQL*Plus and run very simple checks:

```bash
docker compose exec oracle bash -lc "sqlplus system/Oracle123@//localhost:1521/XEPDB1"
```

```sql
SELECT Name, Balance FROM Customers;
SELECT AccountID, Balance FROM Accounts;
SELECT LoanID, InterestRate FROM Loans;
```

Expected idea:

- You should see rows in `Customers` and `Accounts`.
- `make run1` should execute without SQL errors.
- If rows are missing, run `make schema` again.

## Check Database Data (Terminal)

1. Open SQL*Plus inside container:

```bash
docker compose exec oracle bash -lc "sqlplus system/Oracle123@//localhost:1521/XEPDB1"
```

2. Run queries:

```sql
SELECT table_name FROM user_tables ORDER BY table_name;

SELECT * FROM Customers;
SELECT * FROM Accounts;
SELECT * FROM Transactions;
SELECT * FROM Loans;
SELECT * FROM Employees;

SELECT * FROM ErrorLog ORDER BY LogID;
SELECT * FROM AuditLog ORDER BY AuditID;
```

3. Exit SQL*Plus:

```sql
EXIT;
```

## Connection Details (for SQL Developer)

- Host: `localhost`
- Port: `1521`
- Service name: `XEPDB1`
- Username: `system`
- Password: `Oracle123`

## Useful Commands

```bash
make status   # show container status
make logs     # follow Oracle logs
make down     # stop container
make clean    # stop container and remove volumes
```

## Troubleshooting

1. `make schema` returns code 130:
This means the command was interrupted (Ctrl+C or terminal interruption). Re-run:

```bash
make schema
```

2. Oracle not ready / startup delay:
Run `make logs` and wait until Oracle startup completes, then re-run `make schema`.

3. Duplicate table/data errors:
Schema script is resettable. Run `make schema` again; it drops and recreates tables.

4. Port 1521 already in use:
Stop other Oracle containers/services using 1521, then run `make up` again.