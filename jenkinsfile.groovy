pipeline {
    agent any
    
    environment {
        DOCKERHUB=credentials('docker')
        }

   stages {
       
       stage('checkout') {
        steps {
            
            git branch: 'main', url: 'https://github.com/abhinav1144/abhinav-app.git'
            
            }
        }
        stage('Build') {
            steps {
                sh 'docker build -t abhinavprince/hca:${BUILD_NUMBER} .'
            }
        }
        
        stage('Login') {
            steps {
                sh 'echo $DOCKERHUB | docker login -u abhinavprince -p $DOCKERHUB'
            }
        }
        stage('Push') {
            steps {
                sh 'docker push abhinavprince/hca:${BUILD_NUMBER}'
            }
        }
        stage('rancher-deploy') {
        steps {
            
            rancherRedeploy alwaysPull: true, credential: 'rancher', images: 'abhinavprince/hca:${BUILD_NUMBER}', workload: '/project/c-9psfj:p-kw5kp/workloads/deployment:default:abhinav-app'
            
            }
        }
    }

}
