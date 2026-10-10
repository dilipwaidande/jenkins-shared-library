def call(String imageName, String imageTag, String dockerHubUser, String credentialsId = 'docker-credentials') {
    echo "=========================================="
    echo "Building & Pushing Container Image"
    echo "=========================================="

    def fullImage = "${dockerHubUser}/${imageName}:${imageTag}"

    // 1. Build the Docker Image
    sh "docker build -t ${fullImage} ."

    // 2. Authenticate and Push securely using Jenkins Credentials
    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"
        sh "docker push ${fullImage}"
    }

    echo "Image successfully pushed to Docker Hub: ${fullImage}"
}
