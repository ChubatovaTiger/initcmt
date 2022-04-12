import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {

    vcsRoot(HttpsGithubComChubatovaTigerChubatovaGradleTestsBackup)

    buildType(Ac)
}

object Ac : BuildType({
    name = "ac"

    maxRunningBuilds = 5
    maxRunningBuildsPerBranch = """
        %defaultMask%
        pull/* %pullMask%
    """.trimIndent()

    params {
        param("defaultMask", "1")
        param("pullMask", "2")
    }

    vcs {
        root(HttpsGithubComChubatovaTigerChubatovaGradleTestsBackup)
    }

    steps {
        script {
            scriptContent = "sleep 100"
        }
    }

    features {
        pullRequests {
            provider = github {
                authType = token {
                    token = "credentialsJSON:d674df64-6be8-409d-b5c0-99e908571ff4"
                }
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
    }
})

object HttpsGithubComChubatovaTigerChubatovaGradleTestsBackup : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/ChubatovaGradleTestsBackup"
    url = "https://github.com/ChubatovaTiger/ChubatovaGradleTestsBackup"
    branch = "refs/heads/master"
})
