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
        k8s = 'k8s-master-1'
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
            environment {
                imageName = "spring-camel-cluster-singleton:${version}-autotest"
            }

            steps {
                script {
                    final pom = readMavenPom file: 'pom.xml'
                }

                sh """
                    buildah bud --tag ${imageName} --build-arg APP_VERSION=${pom.version} .
                    buildah push ${imageName} docker://docker.khb.hu/poc/${imageName}
                    buildah rmi ${imageName}
                """
            }
        }

//        stage('Deploy k8s') {
//            steps {
//
//            }
//        }
    }

//    post {
//        cleanup {
//
//        }
//    }
}