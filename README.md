# Directed Graph Generator

This Springboot application generates UML component diagrams from Excel Sheets. The purpose is to get a proper architectural description of an application landscape with minimal effort.

Minimal Input Data
------------------

All applications (nodes) and dependencies (links) have to be stored in a table with a
fixed column format.

**Example table for applications**
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/table-apps.png)

**Example table for links**
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/table-links.png)


URL Parameters
--------------

Based on URL parameters you can define what you will see in the diagram. This is important in the case you have hundreds of application.

- *filter* - select the applications you like to see
- *file* - load an alternative file, the default value is data.xlsx
- *package* - filter by component name
- *showLinks* - the default value is false
- *strict*  - show just the applications in the filter, the default value is false
- *colorMode* - decide what is the first stereotype. Allowed values are {"status", "location", "cluster"}
- *type* - selects the type of diagram, the default value is "graph". Allowed values are {"graph", "component"}


Needed Installation
-------------------

The solution is based on *plantUML* and this needs *GraphVis* to run. You find the installation packages here https://graphviz.gitlab.io/download/

Expected Result
---------------

The generated html files need no additional libraries, so you may open them in the browser and save them as file.

1) Home screen (http://localhost:8080) :
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/home.png)

2) Applications in components view with first stereotype is the status
(http://localhost:8080/diagram?type=component&showLinks=true&colorMode=status):
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/status.png)

3) Applications in components view with first stereotype is the cluster
(http://localhost:8080/diagram?type=component&showLinks=true&colorMode=cluster):
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/cluster.png)

4) Applications in components view with first stereotype is the location
(http://localhost:8080/diagram?type=component&showLinks=true&colorMode=location):
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/location.png)

5) Directed graph
(http://localhost:8080/diagram?type=graph):
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/directed-graph.png)

