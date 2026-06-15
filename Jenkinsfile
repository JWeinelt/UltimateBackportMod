pipeline {
    agent {label  'linux'}

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
