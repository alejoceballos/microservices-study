#!/bin/zsh

# Need "chmod +x build-n-run.sh" to be able to run

logEnabled=false

log() {
  [[ $logEnabled = true ]]
}

build_n_run() {

  should_process() {
    if log; then echo "Testing [$1] vs. [$2]"; fi

    setopt shwordsplit

    testNum=0
    for ap in $1; do
      for cp in $2; do
          testNum=$((testNum+1))
          if log; then echo "Test $testNum: [$ap] vs. [$cp]"; fi

          if [[ $ap = "$cp" ]]; then
            return 0
          fi
      done
    done

    unsetopt shwordsplit
    return 1
  }


  docker_clear() {
    accountsParams=(all services accounts)
    if should_process "$accountsParams" "$1"; then
      if log; then echo "Accounts container: stopping, removing"; fi
      docker container stop accounts || return
      docker container rm accounts || return
      docker image rm alejoceballos/ms_study_accounts:v1 || return
    fi

    cardsParams=(all services cards)
    if should_process "$cardsParams" "$1"; then
      if log; then echo "Cards container: stopping, removing"; fi
      docker container stop cards || return
      docker container rm cards || return
      docker image rm alejoceballos/ms_study_cards:v1 || return
    fi

    loansParams=(all services loans)
    if should_process "$loansParams" "$1"; then
      if log; then echo "Loans container: stopping, removing"; fi
      docker container stop loans || return
      docker container rm loans || return
      docker image rm alejoceballos/ms_study_loans:v1 || return
    fi

    discoveryParams=(all infra discovery)
    if should_process "$discoveryParams" "$1"; then
      if log; then echo "Discovery container: stopping, removing"; fi
      docker container stop discoveryserver || return
      docker container rm discoveryserver || return
      docker image rm alejoceballos/ms_study_discoveryserver:v1 || return
    fi

    configParams=(all infra config)
    if should_process "$configParams" "$1"; then
      if log; then echo "Config container: stopping, removing"; fi
      docker container stop configserver || return
      docker container rm configserver || return
      docker image rm alejoceballos/ms_study_configserver:v1 || return
    fi

    gatewayParams=(all infra gateway)
    if should_process "$gatewayParams" "$1"; then
      if log; then echo "Gateway container: stopping, removing"; fi
      docker container stop gatewayserver || return
      docker container rm gatewayserver || return
      docker image rm alejoceballos/ms_study_gatewayserver:v1 || return
    fi
  }

  build_images() {
    cd docker || return

    configParams=(all infra config)
    if should_process "$configParams" "$1"; then
      if log; then echo "Config image: recreating"; fi
      cd ../configserver || return
      mvn clean install jib:dockerBuild -DskipTests || return
    fi

    discoveryParams=(all infra discovery)
    if should_process "$discoveryParams" "$1"; then
      if log; then echo "Discovery image: recreating"; fi
      cd ../discoveryserver || return
      mvn clean install jib:dockerBuild -DskipTests || return
    fi

    accountsParams=(all services accounts)
    if should_process "$accountsParams" "$1"; then
      if log; then echo "Accounts image: recreating"; fi
      cd ../accounts || return
      mvn clean install jib:dockerBuild -DskipTests || return
    fi

    cardsParams=(all services cards)
    if should_process "$cardsParams" "$1"; then
      if log; then echo "Cards image: recreating"; fi
      cd ../cards || return
      mvn clean install jib:dockerBuild -DskipTests || return
    fi

    loansParams=(all services loans)
    if should_process "$loansParams" "$1"; then
      if log; then echo "Loans image: recreating"; fi
      cd ../loans || return
      mvn clean install jib:dockerBuild -DskipTests
    fi

    gatewayParams=(all infra gateway)
    if should_process "$gatewayParams" "$1"; then
      if log; then echo "Gateway image: recreating"; fi
      cd ../gatewayserver || return
      mvn clean install jib:dockerBuild -DskipTests
    fi

    cd ..  || return
  }

  run_docker() {
    if log; then echo "Running Docker Compose"; fi
    cd docker/momo-bank || return
    docker compose up -d || return
    cd ../.. || return
  }

  clean_build_run() {
    docker_clear "$1"
    build_images "$1"
    run_docker
  }

  allAllowedParams=(all services infra accounts cards loans config discovery gateway)

  if [ -z "$1" ]; then
    echo "No parameter found, 'all' will be considered"
    params=(all)
  else
    params=("$@")
    echo "Parameter(s) [$params] found"

    if ! should_process "$allAllowedParams" "$params"; then
      echo "Accepted values are: $allAllowedParams"
      params=()
    fi
  fi

  if [ ! ${#params[@]} -eq 0 ]; then
    docker -v || return
    clean_build_run "$params";
  fi
}

build_n_run "$@"
