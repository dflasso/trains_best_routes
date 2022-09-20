# Library trains best routes

<p align="center">
  <img src="https://instatecno.com/wp-content/uploads/2018/10/Java-11.jpg" width="200" alt="Java" />
</p>

# Overwrite
Context:  The local commuter railroad services a number of towns in Kiwiland.  Because of monetary concerns, all of the tracks are 'one-way.'  That is, a route from Kaitaia to Invercargill does not imply the existence of a route from Invercargill to Kaitaia.  In fact, even if both of these routes do happen to exist, they are distinct and are not necessarily the same distance!

The purpose of this problem is to help the railroad provide its customers with information about the routes.  In particular, you will compute the distance along a certain route, the number of different routes between two towns, and the shortest route between two towns.

Input:  A directed graph where a node represents a town and an edge represents a route between two towns.  The weighting of the edge represents the distance between the two towns.  A given route will never appear more than once, and for a given route, the starting and ending town will not be the same town.

Output: For test input 1 through 5, if no such route exists, output 'NO SUCH ROUTE'.  Otherwise, follow the route as given; do not make any extra stops!  For example, the first problem means to start at city A, then travel directly to city B (a distance of 5), then directly to city C (a distance of 4).

## Graph nodes and edges
![Graph nodes and edges](https://raw.githubusercontent.com/dflasso/trains_best_routes/main/docs/design/Diagramas-Dany_Lasso-Grafo_de_rutas.jpg)

# Get Starting

Assuming that you have a JDK 11 or more and maven
1. Ejecute command

    ```mvn clean build install```

2. Import Dependency in **pom.xml** of target project
```xml
    <dependency>
 		<groupId>com.dany-lasso.solutions</groupId>
 		<artifactId>trains</artifactId>
 		<version>1.0-SNAPSHOT</version>
 	</dependency>
```

# Deployment 
If you need publish this librery in specify repository.

1. Add in **pom.xml**
```xml
  <distributionManagement>
    <repository>
      <id>corp1</id>
      <name>Corporate Repository</name>
      <url>https://repo...</url>
      <layout>default</layout>
    </repository>
    <snapshotRepository>
      <id>propSnap</id>
      <name>Propellors Snapshots</name>
      <url>https://repo...</url>
    </snapshotRepository>
  </distributionManagement>
```

2. **Setup your settings.xml with credencials of private repository**

3. Ejecute command
```mvn deploy```

Reference: [Docs](https://maven.apache.org/pom.html#repository)

# Solution Architecture
[Class Diagram](https://github.com/dflasso/trains_best_routes/blob/main/docs/Diagramas-Dany_Lasso-Diagrama%20de%20Clases.drawio.pdf)

# TDD
All feactures have a unit test, and funtional test. First the test was designed and then the functionality


##  Unit Test with JUnit
Ejecute command

```mvn clean build test```

### [Provider Distance Between Nodes By Nearest Node Test]()
1. The distance of the route A-B-C.

2. The distance of the route A-D.

3. The distance of the route A-D-C.

4. The distance of the route A-E-B-C-D.

5. The distance of the route A-E-D.

### [Provider All Routes V1 Test]()
6. The number of trips starting at C and ending at C with a maximum of 3 stops.  In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).

7. The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).

10. The number of different routes from C to C with a distance of less than 30.  In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.

### [Provider Distance Between Nodes Optimal Route Test]()
8. The length of the shortest route (in terms of distance to travel) from A to C.

9. The length of the shortest route (in terms of distance to travel) from B to B

# Methodology

Following an agile methodology

# Version Code Strategies
This project used git flow. Each requirement was created such as a new branch according your category: feacture, bug, support, hotfix.

Depends of branch, was merged to develop or main.

#  Code standards
- The coding of the microservices was carried out following the SOLID principles
- The code style was based on the Zen of Python

## Stay in touch

- Author - [Dany Lasso](https://dflasso.github.io/)
- Linkedin - [Dany Lasso](https://www.linkedin.com/in/dany-lasso-10683b124/)
- Twitter - [@dany_f_lasso](https://twitter.com/dany_f_lasso)
- Email - [dannylasso.a@gmail.com](mailto:dannylasso.a@gmail.com)

## License

Nest is [MIT licensed](LICENSE).
