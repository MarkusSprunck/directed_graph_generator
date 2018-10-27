# Directed Graph Generator

This Springboot application generates UML component diagrams from Excel Sheets.

The purpose is to get a proper architectural description of an application landscape with minimal
effort.

Minimal Input Data
------------------

All applications (nodes) and dependencies (links) have to be stored in a table with a
fixed column format.

Example table for applications:
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/table-apps.png)

Example table for links:
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/table-links.png)


Needed Installation
-------------------

The solution is based on *plantUML* and this needs *GraphVis* to run. You find the installation packages here https://graphviz.gitlab.io/download/

Expected Result
---------------

The generated html files need no additional libraries, so you may open them in the browser and save them as file.

1) Home screen (http://localhost:8080) :
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/home.png)

2) Applications in components view with first stereotype is the status:
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/status.png)

3) Applications in components view with first stereotype is the cluster:
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/cluster.png)

4) Applications in components view with first stereotype is the location:
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/location.png)

5) Directed graph:
![alt text](https://github.com/MarkusSprunck/directed_graph_generator/blob/master/directed-graph.png)

