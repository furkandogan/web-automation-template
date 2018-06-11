# Zingat Web Automation

<p align="center">
  <img id="header" height="214" width="500" src="https://github.com/zingat/automation-test-bdd/blob/master/docker_configs/docker_zalenium/images/logo_zalenium_wide.png" />
  <a href="https://cucumber.io/docs/reference/jvm">
    <img src="https://cucumber.io/images/cucumber-logo.svg" height="110" />
  </a>
</p>

# Zalenium
Docker üzerinde çalışan bir selenium grid konteyneridir. Testlerinizi Firefox ve Chrome'da çalıştırmak için kullanılır.[docker-selenium] (https://github.com/elgalu/docker-selenium).

## Getting Started

#### Prerequisites
* Pull the [docker-selenium](https://github.com/elgalu/docker-selenium) image. `docker pull elgalu/selenium`

#### Set it up
* Make sure your docker daemon is running (e.g. `docker info` works without errors).
* `docker pull dosel/zalenium`

#### Run it
Zalenium uses docker to scale on-demand, therefore we need to give it the `docker.sock` full access, this is known as
"Docker alongside docker".

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
