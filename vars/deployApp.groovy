def call(String appName, String imageName, String hostPort, String containerPort) { 
    echo "=== [Shared Library] Deploying Container ===" 
    sh "docker stop ${appName} || true" 
    sh "docker rm ${appName} || true" 
    sh "docker run -d --name ${appName} -p ${hostPort}:${containerPort} ${imageName}:latest" 
}
