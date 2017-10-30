# jenkins-job-dsl-cf-pipeline

Based on the hard effort that has been done in [Spring-Cloud-Pipelines](https://github.com/spring-cloud/spring-cloud-pipelines/tree/master/jenkins#step-by-step).

### Quick Demo
1. Run `cd ./jenkins`
2. Run `./start.sh <github-user> <github-deploy-key-or-password> <github-org>`
3. Use the startup wizard to manually add libs-release-local http://localhost:8081/artifactory/ Maven repo to Artifactory.
4. Optionally, configure **Maven Snapshot Version Behavior**. If you want to use snapshot versions of your deployed artifacts then the pipeline needs to be able to pull down the latest snapshot from the `libs-snapshot-local` repo without specifying the `unique` version which is based on a timestamp. The pipeline requires you to set this to `non-unique` which uses a self-overriding naming pattern of `artifactId-version-SNAPSHOT.type` so that the pipeline can download the latest snapshot without needing the timestamp.
5. Run `./tools/deploy-infra.sh`
6. Run `./tools/remove-prod-tags.sh <github-org>` (only need to do this if you've pushed these apps w/ the pipeline to prod already)
7. Open jenkins and configure [credentials](http://localhost:8080/credentials/)
8. [Configure](http://localhost:8080/job/jenkins-pipeline-cf-seed/configure) the seed job parameters for the environment
9. [Build](http://localhost:8080/job/jenkins-pipeline-cf-seed/) the seed job

