# Kotlin Backend Starter

This is the repo of kotlin backend starter kit.

It requires the followings
- JDK 17
- MySQL 8
- Docker 20+, optional, if necessary to execute the unit test cases

## Introduction

This is a starter kit for Kotlin backend development. It includes the following modules:

- app: the RESTful API application modules
  - api: the demo API module
- common: the common module
- domain: the module to define DB models and business logic models
- service: the module to implement business logic
- storage: the storage relates modules
  - mysql: the MySQL storage module, it includes the JPA repository classes

Please follow the rules of modules and add your code in the correct module. 
Also, you may add your own modules if necessary.

Please follow the rules in the `Guideline.md` file.
Also, you may add your own rules if necessary.

## Usage

To use this starter kit, please follow the steps below:
1. Open the project in IntelliJ IDEA.
2. Rename the package name `com.example` to your own package name.
3. Rename DB name `starter` in `application-storage-mysql-default.yml` to your own DB name.
   4. Prepare MySQL 8, create the DB, and import the `sql/ddl.sql` file.
5. Try to start the application in the `:app:api` module and check if it works.
