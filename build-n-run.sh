#!/bin/bash

# Need "chmod +x build-n-run.sh" to be able to run

docker_clear() {
  if [[ "$1" = "services" ]] || [[ "$1" = "all" ]]; then
    docker container stop accounts
    docker container rm accounts
    docker image rm alejoceballos/ms_study_accounts:v1

    docker container stop cards
    docker container rm cards
    docker image rm alejoceballos/ms_study_cards:v1

    docker container stop loans
    docker container rm loans
    docker image rm alejoceballos/ms_study_loans:v1
  fi

  if [[ "$1" = "all" ]]; then
    docker container stop eurekaserver
    docker container rm eurekaserver
    docker image rm alejoceballos/ms_study_eurekaserver:v1

    docker container stop configserver
    docker container rm configserver
    docker image rm alejoceballos/ms_study_configserver:v1
  fi
}

build_images() {
  cd docker || exit

  if [[ "$1" = "all" ]]; then
    cd ../configserver || exit
    mvn clean install jib:dockerBuild -DskipTests

    cd ../eurekaserver || exit
    mvn clean install jib:dockerBuild -DskipTests
  fi

  if [[ "$1" = "services" ]] || [[ "$1" = "all" ]]; then
    cd ../accounts || exit
    mvn clean install jib:dockerBuild -DskipTests

    cd ../cards || exit
    mvn clean install jib:dockerBuild -DskipTests

    cd ../loans || exit
    mvn clean install jib:dockerBuild -DskipTests
  fi

  cd .. || exit
}

run_docker() {
  cd docker/momo-bank || exit
  docker compose up -d
}

build_n_run() {
  docker_clear "$1"
  build_images "$1"
  run_docker
}

if [ -z "$1" ]; then
  echo "No parameter found, 'all' will be considered"
  build_n_run "all";
else
  echo "Parameter $1 found"
  params=("all" "services")

  match=0
  for p in "${params[@]}"; do
      if [[ $p = "$1" ]]; then
          match=1
          break
      fi
  done

  if [[ $match = 0 ]]; then
    echo "Only 'empty', 'all' and 'services' values are accepted"
  else
    build_n_run "$1"
  fi
fi

