# discoverAR

## Overview

This repo contains the files required to run the web app editor on a server. The front-end and back-end code are both hosted in this repo.

## Pre-requisites
Please follow the download instructions for each of the following.
* [IntelliJ Ultimate (for Spring)](https://www.jetbrains.com/idea/download/) (optional, but recommended)
* [Java SDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven ^3.6.2](https://maven.apache.org/download.cgi)
* [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli)

## Set up

The following instructions are for Window OS. Instructions for *nix systems are unavailable at this moment.

### Application
Run the following commands in the home directory.

#### Build
`mvn clean package`

#### Local Heroku Server
`heroku local web -f Procfile.dev`

### Java
Run the following commands in the home directory

#### Install Dependencies
`mvn clean install`
 
#### Start Spring Boot Server
`mvn spring-boot:run`
To run without building `npm`, add the `-Dskip.npm` flag.

### JavaScript
The JavaScript project is located under the `web` directory. Run the following commands under the `web` directory.

#### Install Dependencies
`npm i`

#### Build
`npm run build`

#### Local Server
`npm run start`

### TSLint
TSLint was installed to standardize code formatting in the web project.

#### Formatting check
`npm run lint`

#### Fix formatting
`npm run lint --fix`
