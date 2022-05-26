## Build docker image

```shell
docker build . -t ceballos/bank/account-service
```

## Inspect image

```shell
docker image inspect ceballos/bank/account-service
```
```
[
    {
        "Id": "sha256:4e0af9cd0e96a0d182c50e8557034bd38ca0e910970926a922332714f16636cc",
        "RepoTags": [
            "ceballos/bank/account-service:latest"
        ],
        "RepoDigests": [],
        "Parent": "",
        "Comment": "buildkit.dockerfile.v0",
        "Created": "2022-05-06T02:21:07.131545Z",
        "Container": "",
        "ContainerConfig": {
            "Hostname": "",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": null,
            "Cmd": null,
            "Image": "",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": null,
            "OnBuild": null,
            "Labels": null
        },
        "DockerVersion": "",
        "Author": "https://github.com/alejoceballos",
        "Config": {
            "Hostname": "",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/openjdk-17/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin",
                "JAVA_HOME=/usr/local/openjdk-17",
                "LANG=C.UTF-8",
                "JAVA_VERSION=17.0.2"
            ],
            "Cmd": null,
            "Image": "",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": [
                "java",
                "-jar",
                "/account-service.jar"
            ],
            "OnBuild": null,
            "Labels": null
        },
        "Architecture": "amd64",
        "Os": "linux",
        "Size": 448150712,
        "VirtualSize": 448150712,
        "GraphDriver": {
            "Data": {
                "LowerDir": "/var/lib/docker/overlay2/ce32cee93c620fd0e1839070bef5e76e17fa70c23bf34e6eefe359705c19044e/diff:/var/lib/docker/overlay2/d107ac509268ec83fd8f0d2b232800cc90fdf8e010076a963ce45d1eaccfac99/diff:/var/lib/docker/overlay2/28bf6be05fd742c85ed0a53084fc1f0728241c86ed43df84feeae758b738ff00/diff",
                "MergedDir": "/var/lib/docker/overlay2/ylvuoe9kzll6mcfrdy78fgkvv/merged",
                "UpperDir": "/var/lib/docker/overlay2/ylvuoe9kzll6mcfrdy78fgkvv/diff",
                "WorkDir": "/var/lib/docker/overlay2/ylvuoe9kzll6mcfrdy78fgkvv/work"
            },
            "Name": "overlay2"
        },
        "RootFS": {
            "Type": "layers",
            "Layers": [
                "sha256:9c1b6dd6c1e6be9fdd2b1987783824670d3b0dd7ae8ad6f57dc3cea5739ac71e",
                "sha256:13a34b6fff7804cf7f6e8f52a4cf25ceb2e32fc35a6f39e8158074c64831ebf0",
                "sha256:6be690267e47ddcfd965449d2af70a9eca9879f9436948ee83d7f4ad473b8e64",
                "sha256:fbe998168269e9c4f05e759f454f9de48e4ab5bcd085dc6066fd2325d10b267d"
            ]
        },
        "Metadata": {
            "LastTagTime": "2022-05-06T02:21:07.725172Z"
        }
    }
]
```

## Create a container
Run a container in:
- -d 
  - Detached mode
- -p 8080:8080
  - Mapping port `8080` in localhost to `8080` in the container
- --name account-service
  - Naming the container `account-service`
- ceballos/bank/account-service
  - Based on this image

Ex:
```shell
docker run -d -p 8080:8080 --name account-service ceballos/bank/account-service
```

### Other Docker commands
`docker exec -it <container> <command>`

`docker logs <container> -f`

`docker pause <container>` and `docker unpause <container>`

`docker stop <container>` vs `docker kill <container>`
