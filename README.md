# My Personal Project: The Zoo 

## Zoo Management System

The application will simulate a **zoo management system**. In the application, there will various types of animals 
with individual statistics that can be managed to ensure that the zoo is in a satisfactory state. Features include 
checking if an animal will survive in its current state. 

The application can be used by **animal owners** with a scope from zoos and barns, to domestic usage. It can help them 
to organize and keep track of the animals and corresponding data. Additionally, it can simply be used by individuals
to simulate a zoo and provide **educational information** on the animals. 

I have always liked researching animals and enjoy seeing them in the wild. However, there seems to be a *lack of services
that explicitly highlight animals and their statistics*. When my course instructor shared his passion for nature 
and animals, I was inspired and thought that a zoo management system application would help display 
the information.

## User Stories 

In the context of a zoo management application:

- As a user, I want to be able to create a habitat and add it to a list of habitats
- As a user, I want to be able to add and move animals around to different habitats
- As a user, I want to be able to select a habitat and view all the animal's and their statistics
- As a user, I want to be able to be notified if my habitat is overcrowded
- As a user, I want to be able to have the *option* to save or not save my zoo to file before quitting the application.
- As a user, when I start the application, I want to be given the *option* to load my to-do list from file.

## Phase 4: Task 2

Thu Mar 31 22:22:05 PDT 2022
Saved Zoo.
Thu Mar 31 22:22:16 PDT 2022
Renamed Zoo.
Thu Mar 31 22:22:23 PDT 2022
Created Habitat for Zoo.
Thu Mar 31 22:22:30 PDT 2022
Viewed Habitats in Zoo.
Thu Mar 31 22:22:38 PDT 2022
Viewed Habitats in Zoo.
Thu Mar 31 22:22:44 PDT 2022
Saved Zoo.
Thu Mar 31 22:22:48 PDT 2022
Loaded Zoo. 

## Phase 4: Task 3

- Extract and Rename lines in the moveAnimal function from the Zoo class and create more helpers for easier understanding
- Make the application *more robust* by handling more exceptions instead of using REQUIRES statements 
(moveAnimal and findHabitat function)
- Improve the cohesion of the application by creating a new class named Logger to store all our logEvent functions
- Improve the coupling in the ZooApp class by abstracting out similar print statements in the console user interface and
making them methods to create uniformly formatted output