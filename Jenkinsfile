pipeline {
    agent any
    environment {
        ACR_NAME = 'akasbiacrpfa'
        ACR_URL = "${ACR_NAME}.azurecr.io"
        SONARQUBE_ENV = 'SonarQube'
        SONARQUBE_TOKEN = 'squ_be323c1480893e4211190de55cf4978d45266d1b'
        SLACK_CHANNEL = '#pfa'
        SLACK_WEBHOOK_URL = credentials('slack-webhook')
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/AkasbiYasser/scrum-app.git', credentialsId: 'github-token'
            }
        }

        stage('Build with Maven') {
            steps {
                dir('scrum-backend') {
                    sh '''
                        mvn clean install
                    '''
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(SONARQUBE_ENV) {
                    dir('scrum-backend') {
                        sh '''
                            sonar-scanner -Dsonar.projectKey=scrum-backend \
                                          -Dsonar.sources=. \
                                          -Dsonar.java.binaries=target/classes \
                                          -Dsonar.host.url=http://4.221.188.204:9000 \
                                          -Dsonar.login=${SONARQUBE_TOKEN}
                        '''
                    }
                    dir('scrum-frontend') {
                        sh '''
                            sonar-scanner -Dsonar.projectKey=scrum-frontend \
                                          -Dsonar.sources=. \
                                          -Dsonar.host.url=http://4.221.188.204:9000 \
                                          -Dsonar.login=${SONARQUBE_TOKEN}
                        '''
                    }
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker-compose build'
            }
        }

        stage('Push Docker Images to ACR') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'ACR', passwordVariable: 'ACR_PASSWORD', usernameVariable: 'ACR_USERNAME')]) {
                        sh '''
                            docker login ${ACR_URL} -u ${ACR_USERNAME} -p ${ACR_PASSWORD}
                            docker push ${ACR_URL}/scrum-backend:latest
                            docker push ${ACR_URL}/scrum-frontend:latest
                            docker push ${ACR_URL}/scrum-mongo:latest
                        '''
                    }
                }
            }
        }

        stage('Deploy to AKS') {
            steps {
                script {
                    withCredentials([file(credentialsId: 'aks-cluster', variable: 'KUBECONFIG')]) {
                        sh '''
                            kubectl apply -f k8s/scrum-backend-deployment.yaml --kubeconfig=$KUBECONFIG
                            kubectl apply -f k8s/scrum-frontend-deployment.yaml --kubeconfig=$KUBECONFIG
                            kubectl apply -f k8s/scrum-mongo-deployment.yaml --kubeconfig=$KUBECONFIG
                        '''
                    }
                }
            }
        }
    }

    post {
        success {
            script {
                sh '''
                    curl -X POST -H "Content-type: application/json" \
                        --data '{"text":"Build and deployment successful."}' \
                        ${SLACK_WEBHOOK_URL}
                '''
            }
        }
        failure {
            script {
                sh '''
                    curl -X POST -H "Content-type: application/json" \
                        --data '{"text":"Build and deployment failed."}' \
                        ${SLACK_WEBHOOK_URL}
                '''
            }
        }
    }
}
