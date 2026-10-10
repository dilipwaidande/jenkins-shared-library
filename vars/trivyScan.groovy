def call(String target) {
    echo "=========================================="
    echo "Running Trivy Vulnerability Scan on: ${target}"
    echo "=========================================="

    // Check if target is a file/directory or a Docker image tag
    if (target.contains(":") || target.contains("/")) {
        // Scan Docker Container Image
        sh """
            trivy image --severity HIGH,CRITICAL --exit-code 1 ${target}
        """
    } else {
        // Scan Filesystem / Directory
        sh """
            trivy fs --severity HIGH,CRITICAL --exit-code 1 ${target}
        """
    }
}