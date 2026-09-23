def call(String imageName, String buildNumber, String credentialsId) { 
    echo "=== [Shared Library] Pushing Docker Image ===" 
    withCredentials([usernamePassword(credentialsId: credentialsId, passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) { 
        sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin" 
        sh "docker push ${imageName}:${buildNumber}" sh "docker push ${imageName}:latest" 
    } 
}
