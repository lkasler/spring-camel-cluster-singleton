import java.time.format.DateTimeFormatter

pipeline {
    agent {
        label 'slave-6'
    }

    tools {
        jdk 'Java SE Development Kit 8'
    }

    environment {
        maven = 'Maven 3.6.0'
        namespace = "${env.JOB_NAME.replace("/", "-").replace("%2F", "-")}-${BUILD_NUMBER}"
        version = "${java.time.LocalDate.now().format(DateTimeFormatter.ofPattern('yy.D'))}-${env.GIT_COMMIT.take(7)}"
        imageName = "spring-camel-cluster-singleton:${version}-autotest"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build atrifact') {
            steps {
                withMaven(maven: "${maven}") {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build image') {
            steps {
                script {
                    final pom = readMavenPom file: 'pom.xml'

                    sh """
                        buildah bud --tag ${imageName} --build-arg APP_VERSION=${pom.version} .
                        buildah push ${imageName} docker://docker.khb.hu/poc/${imageName}
                        buildah rmi ${imageName}
                    """
                }
            }
        }

        stage('Deploy k8s') {
            steps {
                dir("scripts/kubernetes") {
                    sh """
                        kubectl create namespace ${namespace}
                        kustomize edit set image app-image=${imageName}
                        kustomize edit set namespace ${namespace}
                        kustomize build . | kubectl apply -f -
                    """
                }
            }
        }
    }

    post {
        cleanup {
            cleanWs()
        }
    }
}