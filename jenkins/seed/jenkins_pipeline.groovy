import javaposse.jobdsl.dsl.DslFactory

DslFactory factory = this

// remove::start[CF]
String repos = 'https://github.com/marcingrzejszczak/github-analytics,https://github.com/marcingrzejszczak/github-webhook'
// remove::end[CF]

// meta-seed
factory.job('meta-seed') {
	scm {
		git {
			remote {
				github('malston/jenkins-job-dsl-cf-pipeline')
			}
			branch('${TOOLS_BRANCH}')
			extensions {
				submoduleOptions {
					recursive()
				}
			}
		}
	}
	steps {
		gradle("clean build")
		dsl {
			external('jenkins/seed/jenkins_pipeline.groovy')
			removeAction('DISABLE')
			removeViewAction('DELETE')
			ignoreExisting(false)
			lookupStrategy('SEED_JOB')
			additionalClasspath([
				'jenkins/src/main/groovy', 'jenkins/src/main/resources'
			].join("\n"))
		}
	}
	wrappers {
		parameters {
			stringParam('TOOLS_BRANCH', 'master', "The branch with pipeline functions")
		}
	}
}

// remove::start[CF]
factory.job('jenkins-pipeline-cf-seed') {
	scm {
		git {
			remote {
				github('malston/jenkins-job-dsl-cf-pipeline')
			}
			branch('${TOOLS_BRANCH}')
			extensions {
				submoduleOptions {
					recursive()
				}
			}
		}
	}
	wrappers {
		parameters {
			// Common
			stringParam('REPOS', repos,
				"Provide a comma separated list of repos. If you want the project name to be different then repo name, " +
					"first provide the name and separate the url with \$ sign")
			stringParam('GIT_CREDENTIAL_ID', 'git', 'ID of the credentials used to push tags to git repo')
			stringParam('GIT_SSH_CREDENTIAL_ID', 'gitSsh', 'ID of the ssh credentials used to push tags to git repo')
			booleanParam('GIT_USE_SSH_KEY', false, 'Should ssh key be used for git')
			stringParam('JDK_VERSION', 'jdk8', 'ID of Git installation')
			stringParam('M2_SETTINGS_REPO_ID', 'artifactory-local', "Name of the server ID in Maven's settings.xml")
			stringParam('REPO_WITH_BINARIES', 'http://artifactory:8081/artifactory/libs-release-local', "Address to hosted JARs")
			stringParam('REPO_WITH_BINARIES_CREDENTIAL_ID', 'repo-with-binaries', "Credential ID of repo with binaries")
			stringParam('GIT_EMAIL', 'email@example.com', "Email used to tag the repo")
			stringParam('GIT_NAME', 'Pivo Tal', "Name used to tag the repo")
			stringParam('APP_MEMORY_LIMIT', '256m', "How much memory should be used by the infra apps (Eureka, Stub Runner etc.) ")
			stringParam('TOOLS_REPOSITORY', 'https://github.com/malston/jenkins-job-dsl-cf-pipeline', "The URL containing pipeline functions repository")
			stringParam('TOOLS_BRANCH', 'master', "The branch with pipeline functions")
			booleanParam('AUTO_DEPLOY_TO_STAGE', false, 'Should deployment to stage be automatic')
			booleanParam('AUTO_DEPLOY_TO_PROD', false, 'Should deployment to prod be automatic')
			booleanParam('API_COMPATIBILITY_STEP_REQUIRED', true, 'Should api compatibility step be present')
			booleanParam('DB_ROLLBACK_STEP_REQUIRED', true, 'Should DB rollback step be present')
			booleanParam('DEPLOY_TO_STAGE_STEP_REQUIRED', true, 'Should deploy to stage step be present')
			stringParam('PAAS_TYPE', 'cf', "Which PAAS do you want to choose")

			stringParam('PAAS_TEST_API_URL', 'api.local.pcfdev.io', 'URL to CF Api for test env')
			stringParam('PAAS_STAGE_API_URL', 'api.local.pcfdev.io', 'URL to CF Api for stage env')
			stringParam('PAAS_PROD_API_URL', 'api.local.pcfdev.io', 'URL to CF Api for prod env')
			stringParam('PAAS_TEST_ORG', 'pcfdev-org', 'Name of the CF organization for test env')
			stringParam('PAAS_TEST_SPACE', 'pcfdev-test', 'Name of the CF space for test env')
			stringParam('PAAS_STAGE_ORG', 'pcfdev-org', 'Name of the CF organization for stage env')
			stringParam('PAAS_STAGE_SPACE', 'pcfdev-stage', 'Name of the CF space for stage env')
			stringParam('PAAS_PROD_ORG', 'pcfdev-org', 'Name of the CF organization for prod env')
			stringParam('PAAS_PROD_SPACE', 'pcfdev-prod', 'Name of the CF space for prod env')
			stringParam('JAVA_BUILDPACK_URL', 'https://github.com/cloudfoundry/java-buildpack.git#v3.8.1', "The URL to the Java buildpack to be used by CF")
			stringParam('PAAS_TEST_CREDENTIAL_ID', 'cf-test', 'ID of the CF credentials for test environment')
			stringParam('PAAS_STAGE_CREDENTIAL_ID', 'cf-stage', 'ID of the CF credentials for stage environment')
			stringParam('PAAS_PROD_CREDENTIAL_ID', 'cf-prod', 'ID of the CF credentials for prod environment')
			stringParam('PAAS_HOSTNAME_UUID', '', "Additional suffix for the route. In a shared environment the default routes can be already taken")
		}
	}
	steps {
		gradle("clean build")
		dsl {
			external('jenkins/jobs/jenkins_pipeline_sample*.groovy')
			removeAction('DISABLE')
			removeViewAction('DELETE')
			ignoreExisting(false)
			lookupStrategy('SEED_JOB')
			additionalClasspath([
				'jenkins/src/main/groovy', 'jenkins/src/main/resources'
			].join("\n"))
		}
	}
}
// remove::end[CF]
