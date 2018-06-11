#!/bin/bash

mvn clean install -f /automation-test-bdd/pom.xml --settings /automation-test-bdd/docker_configs/settings.xml --global-settings /automation-test-bdd/docker_configs/settings.xml -P${profile} -Dbuild=${version} -Dparallel.test.count=${count} -Dbrowser.type=${browserType}
echo "TEST AUTOMATION STEP HAS BEEN COMPLETED"