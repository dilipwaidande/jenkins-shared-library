def call(String sonarServerName = 'SonarQube-Server') {
    echo "=========================================="
    echo "Executing SonarQube Static Code Analysis"
    echo "=========================================="

    // Bind Jenkins SonarQube server configuration
    withSonarQubeEnv(sonarServerName) {
        sh """
            dependency-check --project "DevSecOps App" --scan . --format "HTML" --out "dependency-check-report.html" || true
            sonar-scanner \
              -Dsonar.projectKey=devsecops-app \
              -Dsonar.projectName=devsecops-app \
              -Dsonar.sources=. \
              -Dsonar.java.binaries=.
        """
    }

    echo "Waiting for SonarQube Quality Gate Status..."
    timeout(time: 5, unit: 'MINUTES') {
        script {
            def qg = waitForQualityGate()
            if (qg.status != 'OK') {
                error "Pipeline aborted due to SonarQube Quality Gate failure: ${qg.status}"
            } else {
                echo "SonarQube Quality Gate passed successfully!"
            }
        }
    }
}