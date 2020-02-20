# spring-camel-cluster-singleton
Camel based cluster singleton service using camel's core FileLockClusterService or KubernetesClusterService.


## Camel Leader Election using config based ClusterService

Based on blog article post: https://www.nicolaferraro.me/2017/10/17/creating-clustered-singleton-services-on-kubernetes/

Original gitlab link: [https://github.com/nicolaferraro/spring-camel-cluster-singleton.git](https://github.com/nicolaferraro/spring-camel-cluster-singleton.git)

The app at the moment support file and kubernetes based Cluster Service implementations.

## Enable File Cluster Service

Service: ```FileLockClusterService```

Config:  [Camel Cluster Service](https://camel.apache.org/manual/latest/clustering.html)

First comment kubernetes based config lines in ```application.properties``` if there are present then set/uncomment the file lock based clustering like this:

```
camel.component.file.cluster.service.enabled = true
camel.component.file.cluster.service.id = ${random.uuid}
camel.component.file.cluster.service.root = ${java.io.tmpdir}
camel.component.file.cluster.service.cluster-labels[group]=${project.groupId}
camel.component.file.cluster.service.cluster-labels[app]=${project.artifactId}
```

## Enable Kubernetes Cluster Service

First comment file based clustering lines  in ```application.properties``` if there are present then set/uncomment the kubernetes based clustering like this:

```
camel.component.kubernetes.cluster.service.enabled=true
camel.component.kubernetes.cluster.service.cluster-labels[group]=${project.groupId}
camel.component.kubernetes.cluster.service.cluster-labels[app]=${project.artifactId}
```


Deploy to kubernetes wih command: 
```eval $(minikube docker-env)```
```mvn fabric8:deploy```
For the above programs use the same terminal session otherwise you will be phasing image pull error in kubernetes

Check the cluster singleton service in the logs:




## To configure endpoints

The master service endpoint uri format is the following:

```master:namespace:delegateUri```

Where you can define any endpoint, queue, timer, etc

The above consuming jms foo endpoint and delegates to other queue:
```from("master:lock1:jms:foo").to("activemq:wine")```

See: ```ClasterRoutes.java```

## Choose Cluster Service based on environment

By default file based cluster leader election is enabled, setting config to:

```camel.component.file.cluster.service.enabled = true```

To enable kubernetes cluster service you should disable file based cluster
and enable kubernetes config:

```camel.component.kubernetes.cluster.service.enabled=true```


