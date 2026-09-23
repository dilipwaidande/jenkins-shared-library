def call(String imageName, String buildNumber, String credentialsId) { 
    echo "=== [Shared Library] Pushing Docker Image ===" 
    withCredentials([usernamePassword(credentialsId: credentialsId, passwordVariable: 'DOCKER\_PASS', usernameVariable: 'DOCKER\_USER')]) { 
        sh "echo \\$DOCKER\_PASS | docker login -u \\$DOCKER\_USER --password-stdin" 
        sh "docker push ${imageName}:${buildNumber}" sh "docker push ${imageName}:latest" 
    } 
}
