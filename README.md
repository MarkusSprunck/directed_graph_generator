# Directed Graph Generator

This Spring Boot 2.1 application generates UML component diagrams from Excel Sheets. The purpose is to get proper architectural 
description of an application landscape with minimal effort.

Minimal Input Data
------------------

All applications and dependencies have to be stored in a table with a fixed column format. All cells have to be text format.

- **Example sheet for applications**
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/images/table-apps.png)

- **Example sheet for links**
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/images/table-links.png)


URL Parameters
--------------

Based on URL parameters you can define what you will see in the UML diagram. This is important in the case you have 
hundreds of applications.

- *filter* - select the applications you like to see
- *file* - load an alternative file, the default value is data.xlsx
- *package* - filter by component name
- *showLinks* - the default value is false
- *showComplex* - the default value is true
- *strict*  - show just the applications in the filter, the default value is false
- *colorMode* - decide what is the first stereotype. Allowed values are {"status", "location", "cluster"}
- *type* - selects the type of diagram, the default value is "graph". Allowed values are {"graph", "component"}
- *title* - changes the title of the diagram, the default value is "UML Components".


Development
-----------

- The application has been developed with IntellJ IDEA CE (version 2018.2.4) in Kotlin 
- UML generation is based on _plantUML_ and this needs _GraphVis_ to run. You find the installation packages 
here https://graphviz.gitlab.io/download/
- Java 1.8 JRE will be needed to start the Spring Boot 2.1 application
- A docker image will be created and started during the build, but you may also run without docker. In the case you use 
docker, just mount the path /usr/local/bin/data/ in the container to a local folder.


Expected Results
----------------

The generated html files need no additional libraries, so you may open them in the browser and save them as file.

1) Home screen (http://localhost:8080) :
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/images/home.png)

2) Applications in components view with first stereotype is the status
(http://localhost:8080/diagram?type=component&showLinks=true&colorMode=status):
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/images/status.png)

3) Applications in components view with first stereotype is the cluster
(http://localhost:8080/diagram?type=component&showLinks=true&colorMode=cluster):
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/images/cluster.png)

4) Applications in components view reduced information
(http://localhost:8080/diagram?type=component&showLinks=true&colorMode=status&showComplex=true&filter=ID01-ID02):
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/images/reduced.png)

5) Applications in components view with simple 
(http://localhost:8080/diagram?type=component&showLinks=true&showComplex=false):
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/images/simple.png)

6) Directed graph
(http://localhost:8080/diagram?type=graph):
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/images/directed-graph.png)





