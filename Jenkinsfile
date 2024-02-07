pipeline {
    agent any

    stages {

        stage('Build Jar'){
            steps{
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image') {
            steps {
                bat "docker build -t=chiritoiuviorel/selenium:latest ."
            }
        }

        stage('Push Image') {
            environment {
                DOCKER_HUB = credentials('dockerhub-credentials')
            }
            steps {
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat "docker push chiritoiuviorel/selenium:latest"
                bat "docker tag chiritoiuviorel/selenium:latest chiritoiuviorel/selenium:${env.BUILD_NUMBER}"
                bat "docker push chiritoiuviorel/selenium:${env.BUILD_NUMBER}"
            }
        }
    }

    post {
        always {
            bat "docker logout"
        }
    }
}