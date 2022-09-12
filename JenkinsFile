pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    parameters {
        credentials credentialType: 'com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl', defaultValue: '9728c166-4ad4-4349-8314-a291552f1020', description: 'UI credentials for qase.io(email and password)', name: 'UI credentials for qase.io', required: false
        password defaultValue: '567843fe1830148b87fe314fa2af79691f543a93', description: 'API token for qase.io', name: 'API token for qase.io'
        choice choices: ['smoke.xml', 'regression.xml', 'testng.xml'], description: 'Choose .xml file for running tests', name: 'TEST_SET'
    }

    stages {
        stage('Build') {
            steps {
                withCredentials([usernamePassword(credentialsId: '9728c166-4ad4-4349-8314-a291552f1020', passwordVariable: 'password', usernameVariable: 'username'), string(credentialsId: 'Token', variable: 'token')]) {
                    // Get some code from a GitHub repository
                    git 'https://github.com/viktoria-aparina/Diploma_QASE.git'

                    //Run Maven on a Unix agent.
                    //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                    // To run Maven on a Windows agent, use
                    bat "mvn clean test -DsuiteXmlFile=src/main/resources/testng.xml"
                }
            }
        }

        stage('Allure') {
            steps {
                script {
                    allure([
                            includeProperties: false,
                            jdk              : '',
                            properties       : [],
                            reportBuildPolicy: 'ALWAYS',
                            results          : [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
}