# spring-webflux-project
This is a mini project to take a look at reactive programming.Reactive programming has been a talk in the developer's community for a while now.
Spring WebFlux is a reactive web framework based on a reactive HTTP layer and such apps can be deployed on Netty or Undertow or Jetty/Tomcat/any Servlet 3.1 container.

<img width="951" alt="R" src="https://user-images.githubusercontent.com/67476155/169942825-faf58132-e38c-4d89-a62f-22ab8b899f6b.png">

# Why Spring WebFlux?
Blocking threads consume resources. For latency-sensitive workloads which need to handle a large number of concurrent requests, the non-blocking async programming model is more efficient.
This is particularly relevant for mobile applications and interconnected microservices, and generally for scenarios with many clients and uneven workloads.
Reactive programming aids program structure in terms of data flows and change propagation through them. As a result, in a completely non-blocking environment, we can achieve increased concurrency while maximizing resource utilization.
A read call to the database in the reactive model does not block the caller thread while data is fetched. The call returns a publisher that others can subscribe to right away. 
After the event occurs, the subscriber can process it and even generate more events. It is not necessary for the publisher and subscriber to be in the same thread. 
This allows us to make greater use of available threads, resulting in higher overall concurrency.

![TRM-10-FINAL](https://user-images.githubusercontent.com/67476155/169943371-385b9846-851c-4348-9d9c-c37f307c2a2a.png)

# Spring in one their article said: 
The goal of Spring WebFlux is to offer Spring developers a non-blocking event-loop style programming model similar to node.js. 

# Advantages of using reactive APIs.
Asynchronous and Non-Blocking → Reactive programming gives the flexibility to write asynchronous and Non-Blocking applications.

Event/Message Driven→ The system will generate events or messages for any activity. For example, the data coming from the database is treated as a stream of events.

Better utilization of system resources → As the threads are asynchronous and non-blocking, the threads will not be hogged for the I/O. With fewer threads, we could able to support more user requests.
Scale based on the load

Move away from thread per request → With the reactive APIs, we are moving away from thread per request model as the threads are asynchronous and non-blocking. Once the request is made, it creates an event with the server and the request thread will be released to handle other requests.

Support for backpressure → Gracefully we can handle the pressure from one system to on to the other system by applying back pressure to avoid denial of service.
Predictable application response time → As the threads are asynchronous and non-blocking, the application response time is predictable under the load.
A reactive application is never given up in overload conditions. Back-pressure is a key aspect of a reactive application. It is a mechanism to ensure that the reactive application doesn't overwhelm the consumers. It tests aspects for the reactive application. It tests the system response gracefully under any load.

The back-pressure mechanism ensures that the system is resilient under load. In a back-pressure condition, the system makes itself scalable by applying other resources to help distribute the load.
We have a client application that requests data from another service, for example. The service can publish events at 1000 times per second, but the client application can only handle them at 200 times per second.

The client application should buffer the remaining data to process in this instance. The client application may buffer more data in subsequent calls, eventually running out of memory. 
This has a cascading effect on other programs that are dependent on the client app.To avoid this, the client application can request that the service buffer events at their end and push them at the client application's rate. This is referred to as backpressure.

In conclusion, using the Spring Framework's WebFlux stack, developers may create reactive, non-blocking apps and APIs. WebFlux provides annotations that are remarkably similar to those used in Spring MVC applications, making the shift to reactive programming easier for developers.

![how-source-code-spring-webflux-works](https://user-images.githubusercontent.com/67476155/169943002-0bc05032-0786-4eb8-81a2-2fae950e61fc.jpg)
