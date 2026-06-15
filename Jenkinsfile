pipeline {

    tools {
        jdk 'java8'
    }

    stages {
        stage('Build') {
            agent {label 'Linux-Build'}
            steps {
                sh './gradlew build'
            }
        }
    }
}
