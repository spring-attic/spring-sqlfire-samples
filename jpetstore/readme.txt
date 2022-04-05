=========================================
== Spring JPetStore sample application ==
=========================================

@author Juergen Hoeller
@author Andrei Stefan


1. MOTIVATION

Features a Spring-managed middle tier with Spring Data and SQLFire as data access
strategy, in combination with Spring's transaction and DAO abstractions.
Can work with local JDBC transactions or JTA, with the latter on two databases.
Uses the same data model and demo contents as the original JPetStore.
See the context definitions "WEB-INF/dataAccessContext-local.xml" respectively
"WEB-INF/dataAccessContext-jta.xml" for details.

Offers two alternative web tier implementations with the same user interface:
one based on Spring's web MVC, and one based on Struts 1.2. The latter is close
to the original JPetStore but reworked for JSTL, to make the JSP implementations
as comparable as possible. See "WEB-INF/web.xml", "WEB-INF/petstore-servlet.xml",
and "WEB-INF/struts-config.xml" for details.

Compared to the original JPetStore, this implementation is significantly
improved in terms of internal structure and loose coupling: Leveraging Spring's
application context concept, there's a central place for wiring application
objects now. The most notable improvement is the former PetStoreLogic, now
called PetStoreFacade: It is no longer concerned with configuration, resource,
or transaction details.

Note that the Spring-based web tier implementation is deliberately similar to
the Struts-based one and does not aim to improve in terms of in-place error
messages or the like. The inclusion of two web tier alternatives outlines the
differences as well as the similarities in the respective programming model,
and also illustrates the different configuration styles.

2. BUILD AND DEPLOYMENT

This directory contains the web app source. For deployment, it needs to be built 
with Apache Maven. The only requirements are JDK >=1.5 and Maven >=2.0.8.

Run "mvn package" in this directory to build the war files. The war file 
(jpetstore-1.0.0-SNAPSHOT.war) will be created in the "target" directory.

To execute the application with SQLFire DB settings (the default), please follow these 
steps (these assume Tomcat 7 and JDK 6 are used):
- If necessary, install a functional J2SE Java Developers Kit (JDK). Tomcat 7.0.xx 
requires JDK 6.0 or later. The JDK 'bin' directory should be included in your PATH. 
The JAVA_HOME environment variable should point to the JDK installation directory. 
The JDK can be downloaded from: 
https://www.oracle.com/technetwork/java/javase/downloads/index.html

- If not already installed, download and install Tomcat 7.0.xx. The steps outlined 
below were based on testing on Windows XP SP3. I recommend installing the Windows 
service and control the start/stop commands through the Tomcat Windows service.

- If not already installed, download and install SQLFire 
(https://communities.vmware.com/community/vmtn/appplatform/vfabric_sqlfire?view=overview).

- If not already installed, download and install Maven (with version >= 2.0.8). 
Also, add [Maven_dir]\bin to the PATH environment variable.

- After downloading the SQLFire jar file, place it in the folder of your choice and 
run the following command: java.exe -jar vFabric_SQLFire_103_Installer.jar (the sample application
was tested with the SQLFire 1.0.3 Professional version). The installation will require to specify 
the directory where SQLFire will be installed into. In the specified directory, SQLFire will 
be installed, by default, in vFabric_SQLFire_103 folder.

- Agree with EULA license and continue with the installation.

- For the application to be able to connect to SQLFire cluster, it needs the classes and 
drivers SQLFire comes bundled with. For this, SQLFire jars have to be copied to Tomcat's 
lib directory. Copy [SQLFire_DIR]\lib\sqlfire.jar and [SQLFire_DIR]\lib\sqlfireclient.jar 
to [Tomcat_DIR]\lib.

- Under the directory where SQLFire folder was installed create another two directories: 
server1 and server2.

- Using sqlf command start two SQLFire server instances that should reside inside the folders 
that were just created:
sqlf server start -dir=../../server1 -client-port=1527 -mcast-port=12333
sqlf server start -dir=../../server2 -client-port=1528 -mcast-port=12333
The unique -client-port designations indicate that each server will listen for thin client 
connections on different port (1527 and 1528, respectively). This is necessary because 
both servers bind to the localhost address by default. The -mcast-port option indicates 
that servers in this cluster will discover each other using multicast port 12333. 
SQLFire instances can discover each other through either multicast messaging or through a 
TCP location service, which is called a locator. The locator runs as a separate process to 
which each new SQLFire instance first connects, in order to discover the list of available 
peers. These instructions use multicast for server discovery. 

- The data associated with the application should be loaded into SQLFire. This is accomplished 
by using the files that can be found under jpetstore\db\sqlfire. Execute the following serie 
of commands, one by one using the files that can be found under [SAMPLE_APPLICATION]\db\sqlfire:
[SQLFire_DIR]\bin>sqlf.exe
sqlf> protocol 'jdbc:sqlfire:';
sqlf> connect '//localhost:1527' as myConnection;
sqlf> run '[SAMPLE_APPLICATION]\db\sqlfire\jpetstore-sqlfire-schema.sql';
sqlf> run '[SAMPLE_APPLICATION]\db\sqlfire\jpetstore-sqlfire-dataload.sql';

- Build the war file: "mvn package"

- Start the Tomcat service and, after the startup procedure has finished, copy the application 
war file created earlier to [Tomcat_DIR]\webapps. After the deployment has finished, open up 
a browser and go to http://localhost:8080/jpetstore-1.0.0-SNAPSHOT

