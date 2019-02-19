## Spring 5 cglib LinkageError


Requirements:
1. Java 11
2. Apache Tomcat 8.5

Steps to reproduce:
1. Execute `./gradlew clean buildExample` 
2. Copy shared libraries from tomcat-output/lib to _tomcat_/lib directory
3. Copy twice tomcat-output/webapps/a.war to _tomcat_/webapps directory, so that you have two same webapplications, eg. a.war and b.war
4. Start Tomcat
5. Go to: http://localhost:8080/b/greeting?param=bar
6. Go to: http://localhost:8080/a/greeting?param=bar (order is important)

Result:

You'll get an exception:

```
Message Request processing failed; nested exception is org.springframework.cglib.core.CodeGenerationException: java.lang.LinkageError-->loader java.net.URLClassLoader @68bbe345 (instance of java.net.URLClassLoader, child of 'app' jdk.internal.loader.ClassLoaders$AppClassLoader) attempted duplicate class definition for com.github.tpucal.spring5cglib.core.SeriousService$$FastClassBySpringCGLIB$$14909946.
```


Full stacktrace:

```
Type Exception Report

Message Request processing failed; nested exception is org.springframework.cglib.core.CodeGenerationException: java.lang.LinkageError-->loader java.net.URLClassLoader @68bbe345 (instance of java.net.URLClassLoader, child of 'app' jdk.internal.loader.ClassLoaders$AppClassLoader) attempted duplicate class definition for com.github.tpucal.spring5cglib.core.SeriousService$$FastClassBySpringCGLIB$$14909946.

Description The server encountered an unexpected condition that prevented it from fulfilling the request.

Exception

org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.springframework.cglib.core.CodeGenerationException: java.lang.LinkageError-->loader java.net.URLClassLoader @68bbe345 (instance of java.net.URLClassLoader, child of 'app' jdk.internal.loader.ClassLoaders$AppClassLoader) attempted duplicate class definition for com.github.tpucal.spring5cglib.core.SeriousService$$FastClassBySpringCGLIB$$14909946.
	org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1013)
	org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:897)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:635)
	org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:882)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:742)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
Root Cause

org.springframework.cglib.core.CodeGenerationException: java.lang.LinkageError-->loader java.net.URLClassLoader @68bbe345 (instance of java.net.URLClassLoader, child of 'app' jdk.internal.loader.ClassLoaders$AppClassLoader) attempted duplicate class definition for com.github.tpucal.spring5cglib.core.SeriousService$$FastClassBySpringCGLIB$$14909946.
	org.springframework.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:526)
	org.springframework.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:363)
	org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData$3.apply(AbstractClassGenerator.java:110)
	org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData$3.apply(AbstractClassGenerator.java:108)
	org.springframework.cglib.core.internal.LoadingCache$2.call(LoadingCache.java:54)
	java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	org.springframework.cglib.core.internal.LoadingCache.createEntry(LoadingCache.java:61)
	org.springframework.cglib.core.internal.LoadingCache.get(LoadingCache.java:34)
	org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:134)
	org.springframework.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:319)
	org.springframework.cglib.reflect.FastClass$Generator.create(FastClass.java:65)
	org.springframework.cglib.proxy.MethodProxy.helper(MethodProxy.java:135)
	org.springframework.cglib.proxy.MethodProxy.init(MethodProxy.java:76)
	org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:216)
	org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:749)
	org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	org.springframework.cache.interceptor.CacheInterceptor.lambda$invoke$0(CacheInterceptor.java:53)
	org.springframework.cache.interceptor.CacheAspectSupport.invokeOperation(CacheAspectSupport.java:365)
	org.springframework.cache.interceptor.CacheAspectSupport.execute(CacheAspectSupport.java:420)
	org.springframework.cache.interceptor.CacheAspectSupport.execute(CacheAspectSupport.java:345)
	org.springframework.cache.interceptor.CacheInterceptor.invoke(CacheInterceptor.java:61)
	org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
	com.github.tpucal.spring5cglib.core.SeriousService$$EnhancerBySpringCGLIB$$85b2c719.getResult(<generated>)
	com.github.tpucal.spring5cglib.web.GreetingController.greeting(GreetingController.java:20)
	java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	java.base/java.lang.reflect.Method.invoke(Method.java:566)
	org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:189)
	org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)
	org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)
	org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)
	org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:800)
	org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)
	org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)
	org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1005)
	org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:897)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:635)
	org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:882)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:742)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
Root Cause

java.lang.LinkageError: loader java.net.URLClassLoader @68bbe345 (instance of java.net.URLClassLoader, child of 'app' jdk.internal.loader.ClassLoaders$AppClassLoader) attempted duplicate class definition for com.github.tpucal.spring5cglib.core.SeriousService$$FastClassBySpringCGLIB$$14909946.
	java.base/java.lang.ClassLoader.defineClass1(Native Method)
	java.base/java.lang.ClassLoader.defineClass(ClassLoader.java:1016)
	java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	java.base/java.lang.reflect.Method.invoke(Method.java:566)
	org.springframework.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:523)
	org.springframework.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:363)
	org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData$3.apply(AbstractClassGenerator.java:110)
	org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData$3.apply(AbstractClassGenerator.java:108)
	org.springframework.cglib.core.internal.LoadingCache$2.call(LoadingCache.java:54)
	java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	org.springframework.cglib.core.internal.LoadingCache.createEntry(LoadingCache.java:61)
	org.springframework.cglib.core.internal.LoadingCache.get(LoadingCache.java:34)
	org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:134)
	org.springframework.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:319)
	org.springframework.cglib.reflect.FastClass$Generator.create(FastClass.java:65)
	org.springframework.cglib.proxy.MethodProxy.helper(MethodProxy.java:135)
	org.springframework.cglib.proxy.MethodProxy.init(MethodProxy.java:76)
	org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:216)
	org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:749)
	org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	org.springframework.cache.interceptor.CacheInterceptor.lambda$invoke$0(CacheInterceptor.java:53)
	org.springframework.cache.interceptor.CacheAspectSupport.invokeOperation(CacheAspectSupport.java:365)
	org.springframework.cache.interceptor.CacheAspectSupport.execute(CacheAspectSupport.java:420)
	org.springframework.cache.interceptor.CacheAspectSupport.execute(CacheAspectSupport.java:345)
	org.springframework.cache.interceptor.CacheInterceptor.invoke(CacheInterceptor.java:61)
	org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
	com.github.tpucal.spring5cglib.core.SeriousService$$EnhancerBySpringCGLIB$$85b2c719.getResult(<generated>)
	com.github.tpucal.spring5cglib.web.GreetingController.greeting(GreetingController.java:20)
	java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	java.base/java.lang.reflect.Method.invoke(Method.java:566)
	org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:189)
	org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)
	org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)
	org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)
	org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:800)
	org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1038)
	org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:942)
	org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1005)
	org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:897)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:635)
	org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:882)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:742)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
Note The full stack trace of the root cause is available in the server logs.
```