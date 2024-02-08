FROM bellsoft/liberica-openjdk-alpine:11

# Install curl and jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# add the required files to run tests
ADD target/docker-resources .
ADD runner.sh runner.sh
RUN dos2unix runner.sh

# Start the runner.sh to run tests
ENTRYPOINT sh runner.sh
