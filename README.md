# TowerDefense

A Tower Defense type game in JAVA with an implementation of a pathfinding algorithm.



# Usage

**Build the project **

1.  Clone the repository: 
`git clone git@gaufre.informatique.univ-paris-diderot.fr:ripasart/projet-tour-de-defense.git`
 or 
`git clone https://gaufre.informatique.univ-paris-diderot.fr/ripasart/projet-tour-de-defense.git`

2. Enter the folder:
`cd projet-tour-de-defense`

3. Only if you are on a SCRIPT computer (in one of the TP rooms):
`source SCRIPT/envsetup`

This will setup the GRADLE_OPTS environment variable so that gradle uses the SCRIPT proxy for downloading its dependencies. It will also use a custom trust store (the one installed in the system is apparently broken... ).

4. Run gradle wrapper (it will download all dependencies, including gradle itself)
`./gradlew build`



**Running the software**

Currently, it can be run through gradle too: 
`./gradlew run `
