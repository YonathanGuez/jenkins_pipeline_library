# Learn Jenkins Basic: Shared Library Repository 

```
(root)
+- src                     # Groovy source files
|   +- test
|       +- first
|           +- GlobalVars.groovy  # for test.first.Bar class
+- vars
|   +- hello.groovy          # for global 'hello' variable
    +- gitCheckout.groovy    # globa checkout git 
+- resources               # resource files (external libraries only)
|   +- org
|       +- hello
|           +- name.json    # static helper data for org.hello.name
```

## How to build Jenkins Pipeline library with GitHub:

### Get access token with GitHub:
<p align="center">
  <img width="800" height="500" src="https://github.com/YonathanGuez/test_jenkins/blob/master/img/token_github.png">
</p>

when you have the token do a copy before to reload because it will be hide

### Set credentiel in Jenkins with access token from GitHub : 
<p align="center">
  <img width="800" height="500" src="https://github.com/YonathanGuez/test_jenkins/blob/master/img/jenkins_set_credentiel.png">
</p>

The name is only for identify what kind of credentiel you use 

### Set Github Pipeline :

I change the name of my github link is not test_jenkins but jenkins_pipeline_library:
Manage Jenkins » Configure System » Global Pipeline Libraries as many libraries as necessary can be configured.

<p align="center">
  <img width="800" height="500" src="https://github.com/YonathanGuez/test_jenkins/blob/master/img/jenkins_set_pipeline_source_code.png">
</p>


### How to run my Test : 

We Build a Job with the option Pipeline
<p align="center">
  <img width="800" height="500" src="https://github.com/YonathanGuez/test_jenkins/blob/master/img/jenkins_config_pipeline3.png">
</p>

For test this we will build our pipeline into the job with pipeline script
<p align="center">
  <img width="800" height="500" src="https://github.com/YonathanGuez/test_jenkins/blob/master/img/jenkins_pipeline_conf.png">
</p>

Test echo and Call Variable:
```
@Library('test_jenkins')_

stage('Demo') {

  echo 'Hello World'

  hello 'yo'

}
```
The 'hello' will call the function call into : vars/hello.groovy
The result will print at the end : Hello, yo.

We will run the Job and see if the job can automaticly download my githut link 
and run my global function 
<p align="center">
  <img width="800" height="500" src="https://github.com/YonathanGuez/test_jenkins/blob/master/img/jenkins_test_build.png">
</p>

### Call Source file : GlobalVars
```
@Library('test_jenkins')_

stage('Demo') {
  echo test.first.GlobalVars.foo
}
```
the result will print : bar

### Call Resources: 
```
@Library('test_jenkins')_

stage('Demo') {
  def data = libraryResource 'org/hello/name.json'
  echo data
}
```
the result will print : [{"name": "test"},{"name": "yo"}]

### Sumup with the Script:
```
@Library('test_jenkins')_

node{
	stage('Test Hello Word Simple') {
		echo 'Hello World'
	}
	stage('Test hello from variable vars') {
		hello 'yo'
	}
	stage('Test GlobalVars from Source src ') {
		echo test.first.GlobalVars.foo
	}
	stage('Test Print Resources name.json') {
		def data = libraryResource 'org/hello/name.json'
		echo data
	}
}

```

<p align="center">
  <img width="800" height="500" src="https://github.com/YonathanGuez/test_jenkins/blob/master/img/test_script_var_src_resources.png">
</p>
For more documentation : 
https://www.jenkins.io/doc/book/pipeline/shared-libraries/

## Create Git Checkout in Jenkins Pipeline library :

You will use the vars/gitCheckout.groovy :
```
#!/usr/bin/env groovy
def call(Map stageParams) {
    checkout([
            $class: 'GitSCM',
            branches: [[name:  stageParams.branch ]],
            userRemoteConfigs: [[ url: stageParams.url ]]
    ])
}
```
You need to git branch and Url of github for use this function
This is an example in jenkins Declarative:
```
@Library('test_jenkins@master') _
pipeline {
    agent any
    stages {
        stage('Git Checkout') {
            steps {
                gitCheckout(
                        branch: "master",
                        url: "https://github.com/NAME_USER/NAME_REPO.git"
                )
            }
        }
    }
}
```
## How to Use Declarative in Jenkins:

[Example Jenkins Declarative:](https://github.com/YonathanGuez/jenkins_declarative)

Link Github with example in link with this library:
 - Basic Pipeline
 - Use Parameters
 - Use the function gitCheckout with Parameters
