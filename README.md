# Insect Movement Simulation Game

## Overview
Simulate a game where different types of insects move on a board, collecting food and interacting based on specific rules. The goal is to maximize the food collected while navigating the board and avoiding other insects.

## Insects and Movements
Ants: Move vertically, horizontally, and diagonally.   
Butterflies: Move only vertically and horizontally.     
Spiders: Move only diagonally.    
Grasshoppers: Jump vertically and horizontally, skipping odd fields.     
## Rules
### Movement Directions:    
Ants: N, E, S, W, NE, SE, SW, NW    
Butterflies & Grasshoppers: N, E, S, W    
Spiders: NE, SE, SW, NW    
### Interactions:    
Insects can see food points but not other insects.         
An insect encountering another of a different color is killed (except Grasshoppers jumping over).     
### Prioritization:     
Directions with the most food are chosen.       
If food amounts are equal, the order of preference is: N, E, S, W, NE, SE, SW, NW.     
## Game Flow     
Each insect moves one by one, based on input order.
Moves are decided by the amount of food in each direction.
The game continues until all insects have moved or are killed.
## Input File Format
First Line: Board size    
𝐷 (4 ≤ 𝐷 ≤ 1000)    
Second Line: Number of insects 𝑁 (1 ≤ 𝑁 ≤ 16)    
Third Line: Number of food points 𝑀 (1 ≤ 𝑀 ≤ 200)    
Next 𝑁 Lines: Color InsectType XCoordinate YCoordinate   
Next 𝑀 Lines: FoodAmount XCoordinate YCoordinate    
## Error Handling
Check for violations such as invalid board size, duplicate insects, entities in the same position, etc.
Only the first error encountered should be reported and the program terminated.
## Output
For each insect, output the chosen direction and the amount of food eaten in the format:

Example of input and corresponding output in input.txt, output.txt files.
