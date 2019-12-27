# build标签

build标签下的maven工程的路径设置：souce，resouce，test

```xml
<build>
    <!--打包出来的文件名-->
    <finalName>Test</finalName>
    <!-- 添加多个source：java\scala -->
    <source>
        <sourceDirectory>src.main.scala</sourceDirectory>
        <sourceDirectory>src.main.java</sourceDirectory>
    </source>
    <!--resource路径-->
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>true</filtering>
        </resource>
    </resources>
    
    <plugins>
        <!--这块写插件-->
    </plugins>
<build>
```



# Plugins

参考:<https://blog.csdn.net/t1dmzks/article/details/81198480>

##  maven-assembly-plugin

此插件控制maven工程打成各种类型的包

可以将依赖打入到jar包内部

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-assembly-plugin</artifactId>
    <version>2.5.5</version>
    <configuration>
        <archive>
            <manifest>
                <!--入口类-->
                <mainClass>com.xx.xxx</mainClass>
            </manifest>
        </archive>
        <descriptorRefs>
            <!--打入依赖到jar包-->
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
    </configuration>
    <executions>
        <execution>
            <id>make-assembly</id>
            <phase>package</phase>
            <goals>
                <!--执行一次-->
                <goal>single</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

## maven-jar-plugin

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>2.4</version>
    <configuration>
        <archive>
            <manifest>
                <!-- jar启动入口类-->
                <mainClass>com.test.Test</mainClass>
            </manifest>
        </archive>
        <!-- jar包的位置-->
        <outputDirectory>${project.build.directory}/lib</outputDirectory>
        <includes>
            <!-- 打jar包时，只打包class文件 -->
            <!-- 有时候可能需要一些其他文件，这边可以配置，包括剔除的文件等等-->
            <include>**/*.class</include>
        </includes>
    </configuration>
</plugin>
```

## maven-compiler-plugin

```xml
<plugin>  
       <groupId>org.apache.maven.plugins</groupId>  
       <artifactId>maven-compiler-plugin</artifactId>  
       <version>3.1</version>  
       <configuration>  
            <source>1.8</source>  
            <target>1.8</target>  
        </configuration>  
</plugin>
```




