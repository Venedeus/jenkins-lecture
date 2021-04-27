pipeline {
    agent any

    stages {
        stage('Clone Git repo') {
            steps {
                bat "rmdir /s /q jenkins-lecture"
                bat "git clone https://github.com/Venedeus/jenkins-lecture.git"
                bat "mvn clean -f jenkins-lecture"
            }
        }
        stage("Test") {
            steps {
                bat "mvn test -f jenkins-lecture"
            }
        }
        stage("Package") {
            steps {
                bat "mvn package -f jenkins-lecture"
            }
        }
    }
}

