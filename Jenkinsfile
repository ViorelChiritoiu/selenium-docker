pipeline {
    agent none
    stages {
        stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17-focal'
                    args '-u root -v /tmp/m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Image') {
            steps {
                script {
                    app = docker.build('vinsdocker/selenium')
                }
            }
        }

        stage('Push Image'){
            steps{
                script {
                    // registry url is blank for dockerhub
                    docker.withRegistry('', 'dockerhub-credentials') {
                        app.push("latest")
                    }
                }
            }
        }
    }
}