pipeline {
    agent any

    stages {
        stage('Clone Git repo') {
            steps {
                bat "mvn clean"
            }
        }
        stage("Test") {
            steps {
                bat "mvn test"
            }
        }
        stage("Package") {
            steps {
                bat "mvn package"
            }
        }
    }
}

