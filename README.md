# Efficient Storage of Vehicle Locations for Vehicle Hiring Applications

### 1. Introduction:
Vehicle hiring companies such as Uber, Careem, and Jeeny rely on fast localization of available vehicles near potential customers. Such efficient localization requires specialized data structures, and the goal of this project is to implement and use one such data structure. 

### 2. Storing vehicles locations:
Vehicles and customers are localized using GPS devices available in mobile phones, and these devices determine the longitude and latitude coordinates as floating-point numbers. To simplify the task, however, we will divide the geographical area under consideration into small cells and use integers to identify each location on the map (see Figure 1). 

![page1image3766704](https://user-images.githubusercontent.com/68791351/146197484-1d29ebae-2094-4946-8e56-d74065824ed1.jpeg)

Figure 1: The city is divided into small cells so that a location is identified by two integer coordinates x and y. Note that a single cell may contain several vehicles and customers. 
When a customer request a ride, only vehicles within a certain range of this customer will be contacted (see Figure 2).

![page2image3928256](https://user-images.githubusercontent.com/68791351/146197553-e608b230-5f36-4990-b048-d127ec0e1e4d.png)

Figure 2: Only vehicles lying within a square of side 2r centered at the customer are contacted (shown in green). To store locations for efficient search, we use a tree structure similar to a BST.

### 3. Tools: 
Eclipse
