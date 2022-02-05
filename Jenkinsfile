pipeline {
   agent any

   tools {
      maven "Maven 3.5"
      jdk "JDK 11"
   }

   environment {
       JAVA_HOME="${tool 'JDK 11'}"
   }

   stages {

      stage('OWASP Dependency Check') {
         steps {
             sh 'mvn org.owasp:dependency-check-maven:check'
         }
      }

      stage('Maven install') {
         steps {
             sh 'mvn clean verify'
         }
      }

      stage('Push Image') {
         steps {
            script {
               docker.withRegistry('https://ecs-globpidck01.otc.ihkmun', 'global_docker') {
                   sh  "docker push ecs-globpidck01.otc.ihkmun/dgg/bl:1.4.0"
               }
            }
         }
      }
   }
}
