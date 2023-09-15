# TheMatrix
An AI agent, Neo, uses various search algorithms to formulate a plan to rescue hostages in a grid-based game, while minimizing the number of hostages killed.
Neo's decision making is optimized through the use of heurisitc functions.

## Introduction
In our problem, we have an n*m grid The area the hostages are held in can be thought of as an m × n grid of cells where 5 <= m, n <=15. Initially, a grid cell is either free or contains one of the following: Neo, a hostage, a pill, a pad, an agent, or the telephone booth. The Hostages have an initial damage that increase with time, and Neo’s damage increase with any kill action that he performs on Agents, but Neo can take a Pill to reduce the damage taken by him and the Hostages by 20, and the Pads are used to let Neo fly from one Pad to the other. The objective of our search problem is to let Neo find a way to rescue the hostages by escorting them to the Telephone Booth while trying to minimize the number of agents killed, in addition to killing any hostage who dies as they turn into mutated agents.

## Search

### Search Problem
Generally speaking, a search problem is defined as a 5-tuple consisting of:
1. **A set of operators:** actions available
2. **An initial state**
3. **A state space:** which is the set of states reachable from the initial state using any sequence of the defined operators.
4. **A goal test:** which is applied to a state to know whether it is a goal state.
5. **A path cost function:** which is a function that assigns cost to a sequence of actions.


In our implementation, any class that inherits the generic abstract search problem class named Problem will have the following functions:
- `State getInitialState()`: which returns the initial state (which is stored as a variable in this class).
- `List<Operator> getOperators()`: which returns all the operators of our problem (which are stored as a variable in this class).
- `Boolean testGoal(State state)`: which returns true if this state is a goal state and returns false otherwise.
- `List<Node> expand(Node node, List<Operator> operators)`: which apply all the operators taken as in input to the input node.

### Search Tree Node
The search Tree node class named Node consists of 4 attributes:
- `Node parent`: the parent of the current node.
- `Cost pathCost`: the cost required to reach this node from the initial node.
- `Operator operator`: the operator applied to the parent node to reach the current node.
- `State state`: the state that corresponds to the current node.

### Operator
- The operator class named Operator consists of the following attributes:
- `Operation operation`: which is the action itself.
- `Cost cost`: which is the cost of this operation.

### Generic Search Algorithm
The generic search algorithm class GeneralSearch contains the function search which takes as an input the Problem that we want to solve, the Queue which is the data structure that depends on the Search algorithm chosen that contains the expanded nodes, the qing function which applies the operators to the node in the beginning of the queue to expand it.

### Various Search Algorithms
The following are algorithms that are used to search a tree data structure for a node that passes the goal test.
- **Breadth-First Search (BFS):** It begins at the root of the tree and passes through all nodes at the current depth level before moving on to nodes at the next depth level. In order to do so we used a queue to put the expanded nodes in.
- **Depth-First Search (DFS):** It begins at the root of the tree and passes through all nodes along each branch before backtracking. In order to achieve that we used a stack where we put the nodes that we expanded in.
- **Iterative-Deepening Search (IDS):** It traverse the tree similar to the DFS algorithm but with an initial limit on the depth that increases if we didn’t find a goal state.
- **Uniform-Cost Search (UCS):** This algorithm orders the nodes according to their cost using a priority queue that orders based on: deaths, kills and depth, with the order of the priority given to the nodes with the lowest deaths, kills and depth respectively.
- **Best-First Search:** The nodes are sorted using an evaluation function. A node with a lower evaluation function value is favored over one with a greater cost. Best-First Search is divided into 2 algorithms: Greedy and A*, where each algorithm use a different evaluation function in order to sort the nodes.


> In all these search algorithms, we used a TreeMap to place any visited state in order to avoid repeating it, and all these classes extends the class Queue mentioned in the generic search algorithm class.

### Heuristic Functions
Heuristic functions calculate the estimated cheapest cost of getting from one state to another. These functions are used in informed search to help the search algorithm choose the node that might be closer to the goal state. The heuristic functions classes are called FirstFunction and SecondFunction.

### Evaluation Functions
The evaluation functions uses the heuristic functions to get an estimate for a node to reach the goal, and the evaluation functions are used by the Best-First search to give an estimate for each node to sort them. Let n be the current node, g(n) be the actual cost of the node, h(n) be the estimated cost by the heuristic and f(n) be the cost that will be used in the Best-First Search algorithms:
- Greedy Evaluation Function: `f(n) = h(n)`
- A* Evaluation Function: `f(n)=h(n)+g(n)`

## The Matrix

### Functions
The matrix which is a subclass of the Problem ADT, and it contains the following as the following:
- `Boolean testGoal(State state)`: which sees whether this state is a goal state or not; this is done in our problem by checking if all the following conditions are met:
  1. If there aren’t any alive hostages in the grid
  2. If there aren’t any carried hostages (alive or dead) 3. If there aren’t any mutated agents
  4. If Neo’s damage is less than 100
  5. If Neo is at the Telephone Booth
- `initOperators()`: which initialize all the operators in our problem and add them to the list of operators of our problem.
- `String genGrid()`: which generates a random grid that satisfies all the conditions of the grid mentioned in the description.
- `String solve(String grid, String strategy, Boolean visualize)`: which generate the grid object from the string, create a qing function that depends on the input strategy and display the steps followed by Neo in the solution if there is any when visualize is set to true.


### State
Our state consists of the following attributes:
- `Byte x,y`: which is Neo’s position in the grid.
- `Byte damage`: which is Neo’s Damage
- `List<Byte> carriedDamages`: which is the damage of the currently carried hostages.
- `Byte remCarry`: which is the remaining number of hostages that Neo can still carry.
- `Grid grid`: which is our n*m grid, and it consists of a 2d array of cells which holds everything to represent the state. And the state contains the following main functions:
- `Void move(int dx, int dy)`: which changes Neo’s position.
- `Void transform(int x, int y)`: which changes Neo’s position to the position of the
corresponding pad.
- `Void carry(int x, int y)`: which let Neo carry the hostage at position x and y.
- `Void healNeo()`: which heals Neo.
- `Void healCarriedHostages()`: which heals all the hostages.
- `Void kill()`: which increases Neo’s damage.
- `String toString()`: which visualize the current state using a grid.
- `Int compareTo(State o)`: which compares whether this state and state o are the same.

### The Matrix Operators:
The operators of our problem are divided into 3 classes, where each class contains the relevant operations related to it, and the 3 classes are HandleNeo, HandleHostages and HandleAgents. The operators are:
1. `kill` which is in HandleAgents class.
2. `drop` which is in HandleHostages class.
3. `carry` which is in HandleHostages class.
4. `moveUp` which is in HandleNeo class.
5. `moveDown` which is in HandleNeo class.
6. `moveLeft` which is in HandleNeo class.
7. `moveRight` which is in HandleNeo class.
8. `fly which` is in HandleNeo class.
9. `takePill` which is in HandleNeo class.

    
> All these operators are functions that take as an input the parent state and the current state, and in each function we check whether applying this operator to the parent state is valid or not. In each of the 3 classes, there is an expand function that applies all these operators and returns a list of all the expanded nodes.

## The Matrix Heuristic Functions:
### First Function (Hostages that will turn to Mutated Agents):
The first heuristic function estimate a cost for the kills that Neo has to perform to reach the goal state by checking which hostages will eventually turn into Mutated Agents; this can be done by getting the minimum distance between Neo and each hostage, and the minimum distance between each Hostage and the Telephone Booth (the minimum distance takes into account the pads, so we see all combinations of how Neo can reach the Hostages/Telephone booth and use the minimum distance), and then we see the effect of these distances on each Hostage’s damage; if his damage reaches 100 then eventually Neo will have to kill him. We perform this operation on all hostages and we increment the number of kills by the number of hostages that will eventually die. In order to make this function admissible, we assume that all the pills in the grid were taken so that we are sure that this hostage will live or turn into mutated agents even after taking all the pills in the grid. This heuristic is admissible since it assumes that we can take all the pills without affecting our paths to all hostages, and it also assumes that Neo will go to each hostage immediately from this state which isn’t guaranteed since Neo has a carry limit.

### Second Function (Mutated Agents+Telephone Booth):
The second heuristic function estimate a cost for the kills that Neo has to perform to reach the goal state by checking 2 things: the number of Mutated Agents and whether the Telephone Booth is reachable from Neo’s current location or not; if the telephone booth path is blocked by Agents only (since the Mutated agents estimated kills will be taken into account in the next step) and there are no pads that will help Neo reach the Telephone booth then we increment the number of estimated kills by one, in addition to incrementing the number of kills by the number of mutated agents. This heuristic also calculates the distance of the closest Mutated Agent to the Telephone Booth and adds it to the depth (the minimum distance takes into account the pads, so we can see all combinations of how Neo can return to the booth using the least distance after killing the Mutated Agent). This heuristic is always admissible since Neo always has to kill the mutated agents in order to reach the goal state and he also should have a path to the Telephone booth in order to reach the goal state.


