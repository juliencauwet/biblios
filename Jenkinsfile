pipeline {
    agent any
    tools {
        maven 'Apache Maven 3.5.2'
    }
    stages{
        stage('Checkout') {
            steps {
                git 'https://github.com/juliencauwet/biblios'
            }
        }
        stage('Tests') {
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml'
                }
                failure {
                    error 'The tests failed'
                }
            }
        }
        stage('Push to DockerHub') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Deploy') {
            steps {
                sh "docker stop biblioback || true && docker rm biblioback || true"
                sh "docker pull jaycecordon/biblioback:latest"
                sh "docker run -d --name biblioback -p 28082:28082 --restart always --memory-swappiness=0 jaycecordon/biblioback:latest"
            }
        }
    }
}
