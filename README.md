# spring-camel-cluster-singleton
Camel based cluster singleton service using camel's core FileLockClusterService.


## Camel Leader Election on Kubernetes and Openshift, based of file cluster service

Based on blog article post: https://www.nicolaferraro.me/2017/10/17/creating-clustered-singleton-services-on-kubernetes/

Original gitlab link: [https://github.com/nicolaferraro/spring-camel-cluster-singleton.git](https://github.com/nicolaferraro/spring-camel-cluster-singleton.git)

## Should be changed to File Cluster Service

Service: ```FileLockClusterService```

Config:  [Camel Cluster Service](https://camel.apache.org/manual/latest/clustering.html)

```
camel.component.file.cluster.service.enabled = true
camel.component.file.cluster.service.id = ${random.uuid}
camel.component.file.cluster.service.root = ${java.io.tmpdir}
camel.component.file.cluster.service.cluster-labels[group]=${project.groupId}
camel.component.file.cluster.service.cluster-labels[app]=${project.artifactId}
```

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


