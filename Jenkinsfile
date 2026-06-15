pipeline {
    agent {label 'Linux-Build'}

    tools {
        jdk 'java8'
    }

    stages {
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
    }
}
