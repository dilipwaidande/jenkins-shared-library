def call(String imageName, String buildNumber) { 
    echo "=== [Shared Library] Building Docker Image ===" 
    sh "docker build -t ${imageName}:${buildNumber} -t ${imageName}:latest ." 
}
