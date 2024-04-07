# COSC2391 Further Programming | RMIT University

## Assignment 1 - Semester 1 2024

### Overview
Burrito King is a console-based application designed to simulate a fast-food restaurant's order and sales management system. It allows for order placement, sales report generation, stock management, and menu price updates.

### Features
- **Place Orders**: Customers can order individual items such as burritos, fries, and sodas, or choose a meal combo. The preparation time for each food item is simulated within the system.
- **Sales Reports**: Users can generate reports detailing total sales in dollars, individual item sales, and leftover fries stock.
- **Price Updates**: Users have the ability to update the selling price of any menu item.
- **Inventory Management**: The application tracks the availability of fries and initiates cooking in batches whenever the existing stock is insufficient to meet an order.

## Getting Started

Follow these instructions to get fp-assignment-1 running on your local machine.

### Installation

1. Clone the repository or download the zip source code to your local machine.
```
git clone https://github.com/ejbito/fp-assignment-1.git
```
2. Navigate to the project's root directory
```
cd fp-assignment-1
```
3. Compile the source code using the Java compiler. This should be done from the src directory, and you need to include all the Java files
```
javac -d bin src/helpers/*.java src/interfaces/*.java src/menu/*.java src/*.java
```
This will compile the .java files and place the resulting .class files into the bin directory.

### Running the Application
To start the application, navigate to the bin directory and run the App class:
```
cd bin
```
```
java App
```
