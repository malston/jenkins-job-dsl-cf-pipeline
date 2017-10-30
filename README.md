# jenkins-job-dsl-cf-pipeline

Based on the hard effort that has been done in [Spring-Cloud-Pipelines](https://github.com/spring-cloud/spring-cloud-pipelines/tree/master/jenkins#declarative-pipeline--blue-ocean).

### Quick Demo
1. Run `cd ./jenkins`
2. Run `./start.sh <github-user> <github-deploy-key-or-password> <github-org>`
3. Use the startup wizard to manually add libs-release-local http://localhost:8081/artifactory/ Maven repo to Artifactory.
4. Run `./tools/deploy-infra.sh`
5. Run `./tools/remove-prod-tags.sh <github-org>` (only need to do this if you've pushed these apps w/ the pipeline to prod already)
6. Open jenkins and configure [credentials](http://localhost:8080/credentials/)
7. [Configure](http://localhost:8080/job/jenkins-pipeline-cf-seed/configure) the seed job parameters for the environment
8. [Build](http://localhost:8080/job/jenkins-pipeline-cf-seed/) the seed job

