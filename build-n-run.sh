#!/bin/bash

# Need "chmod +x build-n-run.sh" to be able to run

docker_clear() {
  docker container stop $(docker ps -a -q)
  docker container rm $(docker ps -a -q)

  docker image rm alejoceballos/ms_study_configserver:v1
  docker image rm alejoceballos/ms_study_accounts:v1
  docker image rm alejoceballos/ms_study_cards:v1
  docker image rm alejoceballos/ms_study_loans:v1
}

build_all() {
  cd configserver || exit
  mvn clean install jib:dockerBuild -DskipTests

  cd ../accounts || exit
  mvn clean install jib:dockerBuild -DskipTests

  cd ../cards || exit
  mvn clean install jib:dockerBuild -DskipTests

  cd ../loans || exit
  mvn clean install jib:dockerBuild -DskipTests
FO
  cd ..
}

run_docker() {
  cd docker/momo-bank || exit
  docker compose up -d
}

build_n_run() {
  docker_clear
  build_all
  run_docker
}

build_n_run
