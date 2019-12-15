# Maven工具

## Maven生成jar包

参考:<https://blog.csdn.net/t1dmzks/article/details/81198480>

在pom.xml文件中配置Maven插件

1. build标签：

   ```xml
   <build>
       <!--打包出来的文件名-->
       <finalName>Test</finalName>
       <plugins>
           <!--这块写插件-->
       </plugins>
   <build>
   ```

2. maven-compiler-plugin—编译插件

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

3. maven-jar-plugin—打jar包插件

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

4. maven-dependency-plugin—拷贝项目所有依赖的插件