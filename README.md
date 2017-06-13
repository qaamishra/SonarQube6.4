# SonarQube6.4


Sonar plugiin for Intellj Idea
https://plugins.jetbrains.com/idea/plugin/7238-sonarqube-community-plugin
http://www.sonarlint.org/intellij/


# Setup SonarQube server on local 

1. Download the latest sonar qube server : https://www.sonarqube.org/downloads/
2. Unzip
3. Go to bin/<your os folder>
4. Double click on StartSonar.bat (in case of windows : sonarqube-6.2\bin\windows-x86-64)
5. Hit http://localhost:9000 and you should see the dashboard of SonarQube.

Login admin/admin


# Setup POM.xml ( OLD WAY)

You just need to add the sonar plugin under build/plugins tag.

```sh
</build>
    </plugins>
        <plugin>
           <groupId>org.codehaus.mojo</groupId>
           <artifactId>sonar-maven-plugin</artifactId>
           <version>2.5</version>
        </plugin>
    </plugins>
</build>
```

Command to run through maven : 
mvn sonar:sonar -Dsonar.jdbc:h2:tcp://localhost:9092/sonar -Dsonar.host.url=http://localhost:9090


# SonarQube Scanner for Maven (March 2017 Version)

( NEW WAY)
New SonarQube (here 6.4) just need the mvn dependency
```sh
<!-- https://mvnrepository.com/artifact/org.sonarsource.scanner.maven/sonar-maven-plugin -->
<dependency>
    <groupId>org.sonarsource.scanner.maven</groupId>
    <artifactId>sonar-maven-plugin</artifactId>
    <version>3.3.0.603</version>
</dependency>
```
And no plugin

On a clean project mvn clean install, simply run nmvn sonar:sonar


# Adding Profile :
If you want to fix the localhost and jdbc ,you need to add the profile

```sh
<!--If profile is set you just need to hit mvn sonar:sonar-->
<profiles>
    <profile>
        <id>sonar</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <sonar.jdbc.url>jdbc:h2:tcp://localhost:9092/sonar</sonar.jdbc.url>
            <!-- EXAMPLE FOR MYSQL -->
           <!-- <sonar.jdbc.url>jdbc:mysql://localhost:3306/sonar?useUnicode=true&amp;characterEncoding=utf8</sonar.jdbc.url>
            <sonar.jdbc.username>sonar</sonar.jdbc.username>
            <sonar.jdbc.password>sonar</sonar.jdbc.password>-->

            <!-- optional URL to server. Default value is http://localhost:9000 -->
            <sonar.host.url>http://localhost:9000</sonar.host.url>
        </properties>
    </profile>
</profiles>
```
Command to run through maven : mvn sonar:sonar



# Code coverage plugin JaCoCo


JaCoCo will generate the offline index.html in target/site/index.html,without the sonar qube server just do mvn clean package

This gets executed at the test phase.

```sh
<build>
    <plugins>
          <plugin>
                    <groupId>org.jacoco</groupId>
                    <artifactId>jacoco-maven-plugin</artifactId>
                    <version>0.5.5.201112152213</version>
                    <configuration>
                        <destFile>${basedir}/target/coverage-reports/jacoco-unit.exec</destFile>
                        <dataFile>${basedir}/target/coverage-reports/jacoco-unit.exec</dataFile>
                    </configuration>
                    <executions>
                        <execution>
                            <id>jacoco-initialize</id>
                            <goals>
                                <goal>prepare-agent</goal>
                            </goals>
                        </execution>
                        <execution>
                            <id>jacoco-site</id>
                            <phase>package</phase>
                            <goals>
                                <goal>report</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <configuration>
                        <source>1.5</source>
                        <target>1.5</target>
                    </configuration>
                </plugin>
                <plugin>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>sonar-maven-plugin</artifactId>
            <version>2.5</version>
        </plugin>
    </plugins>
</build>
```
Command to run through maven : mvn clean package

Note : This wont publish result to sonarqube

# Read here for more latest information
https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner+for+Maven
