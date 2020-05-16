# How to use jenkins

Learn Jenkins Basic

## How to build Jenkins Pipeline with GitHub:

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

I change the name of my github link is not test_jenkins but jenkins_pipeline_setting:

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

code Pipeline:
```
@Library('test_jenkins')_

stage('Demo') {

  echo 'Hello World'

  hello 'yo'

}
```

We will run the Job and see if the job can automaticly download my githut link 
and run my global function 
<p align="center">
  <img width="800" height="500" src="https://github.com/YonathanGuez/test_jenkins/blob/master/img/jenkins_test_build.png">
</p>


