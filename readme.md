<h1>Stream log analyzer</h1>

<h2>Description</h2>
[link](https://habr.com/ru/articles/738874/)

<h2>Build</h2>
Images need to be prepared, the building process is described in the article at the following [link](https://struchkov.dev/blog/ru/build-docker-image-for-spring-boot/)<br/>
see dir ./docker

```bash
>> mvn clean install dependency:copy-dependencies
>> jdeps --ignore-missing-deps -q -recursive --multi-release 9 --print-module-deps --class-path 'target/dependency/*' target/*.jar
>> jlink --add-modules [youre_modules_here] --strip-debug --no-man-pages --no-header-files --compress=2 --output javaruntime
>> mkdir build-app && cd build-app
>> java -Djarmode=layertools -jar ../target/*.jar extract
```
<h2>Run</h2>