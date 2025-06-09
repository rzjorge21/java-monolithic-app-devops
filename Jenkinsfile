pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'jdk-17'
    }

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        // stage('Testing') {
        //     steps {
        //         sh 'mvn test'
        //     }
        //     post {
        //         always {
        //             junit 'target/surefire-reports/*.xml'
        //             jacoco execPattern: 'target/jacoco.exec'
        //         }
        //     }
        // }

        // stage('SonarQube Analysis') {
        //     steps {
        //       withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_TOKEN')]) {
        //           withSonarQubeEnv('SonarQube') {
        //               sh 'mvn sonar:sonar -Dsonar.projectKey=monolito -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_TOKEN'
        //           }
        //       }
        //     }
        // }

        // stage('Quality Gate') {
        //     steps {
        //         timeout(time: 1, unit: 'MINUTES') {
        //             waitForQualityGate abortPipeline: true
        //         }
        //     }
        // }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Upload to Artifactory') {
            options {
                timeout(time: 2, unit: 'MINUTES')
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'ARTIFACTORY_CREDENTIALS', usernameVariable: 'ART_USER', passwordVariable: 'ART_PASS')]) {
                  sh '''
                  curl -u$ART_USER:$ART_PASS -T target/*.jar "http://localhost:8081/artifactory/libs-release-local/monolito.jar"
                  '''
              }
            }
        }
    }
}
