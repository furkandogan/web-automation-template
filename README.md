# Web Automation Template

<p align="center">
  <img id="header" height="214" width="500" src="https://github.com/furkandogan/web-automation-template/blob/master/docker_configs/docker_zalenium/images/logo_zalenium_wide.png" />
  <a href="https://cucumber.io/docs/reference/jvm">
    <img src="https://cucumber.io/images/cucumber-logo.svg" height="110" />
  </a>
</p>

# Zalenium
Docker üzerinde çalışan bir selenium grid konteyneridir. Testlerinizi Firefox ve Chrome'da çalıştırmak için kullanılır.[docker-selenium] (https://github.com/elgalu/docker-selenium).

## Başlarken

#### Ön Koşullar
*[docker-selenium](https://github.com/elgalu/docker-selenium) imajını `docker pull elgalu/selenium` komutu ile indirin.

#### Set it up
* Docker servisinin çalıştığından emin ol (Örneğin; `docker info` hatasız çalışır).
* Docker zalenium imajını `docker pull dosel/zalenium` komutu ile indirin.

#### Run it

* Basic usage, without any of the integrated cloud testing platforms enabled:

  ```sh
    docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/seluser/videos \
      --privileged dosel/zalenium start
  ```

* You can also try our one line installer and starter (it will check for the latest images and ask for missing
dependencies.)

  ```sh
    curl -sSL https://raw.githubusercontent.com/dosel/t/i/p | bash -s start
  ```

* After the output, you should see the DockerSeleniumStarter node in the [grid](http://localhost:4444/grid/console)
* Now you can point your Selenium tests to [http://localhost:4444/wd/hub](http://localhost:4444/wd/hub)
* Stop it: `docker stop zalenium`

## Additional features



#### Building

If you want to verify your changes locally with the existing tests (please double check that the Docker daemon is
running and that you can do `docker ps`):
* Building the image

    ```sh
      docker run --rm -ti --name zalenium -p 4444:4444 \
        -v /var/run/docker.sock:/var/run/docker.sock \
        -v /tmp/videos:/home/seluser/videos \
        --privileged zalenium:latest start
    ```
    or
    
    `docker compose up`

## Running your tests
* To run a test what you want which browser, run `mvn install -Dbrowser=<BROWSER>`
  * Note - use command line properties to set additional webdriver capabilities
* To run parallel tests, run `mvn install -Dparallel.test.count=<COUNT>`
* To run parallel tests on stage, run `mvn install -P stage-web-test`
* To run parallel tests on preprod, run `mvn install -P preprod-web-test`
* To run parallel tests on prod, run `mvn install -P prod-web-test`

## How it works

# web-automation-template
