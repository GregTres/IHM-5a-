# TP1_IHM2_Polytech

Le but de ce premier TP est d'implémenter un Range Slider, puis un Home Finder à partir de ce range Slider.

Nous avons réalisé l'ensemble des consignes demandées pour ce TP, y compris le home finder et le bonus.
Il y a deux package : homeFinder et rangeSlider.

##RangeSlider

Le package rangeSlider contient 4 classes basées sur une architecture MVC.
Le main créé une frame et ajoute à cette frame deux range slider avec des valeures différentes.
Un range slider est donc composé d'une vue, qui étend JPanel et qui permet de paint le range slider. Cette vue est connectée au model et au controller et c'est ici que nous ajoutons nos listeners.
Le model contient nos constantes et variables.
De plus la vue ajoute donc des listeners qui sont basés sur la classe Controller. C'est dans cette classe que nous détectons les actions faites par l'utilisateur (MouseAdapter nous permet de récuperer les cliques (mouseClick), drag (mouseDrag) etc.).

Pour essayer les range slider il faut lancer la classe main du package rangeSlider. Ensuite il est possible de cliquer entre les deux curseurs, cela va ramener le curseur le plus près à l'endroit du clique. Il est aussi possible de drag le curseur. Une zone noir indique l'intervalle entre les deux curseurs.

##HomeFinder

Le package homeFinder contient 4 classes : Home, Map, HomeFinderFrame et Main.
Home est la classe représentant une maison (position, nombre de pieces et prix).
HomeFinderFrame permet de créer une frame contenant la Map et les 4 range slider permettant de faire varier respectivement le nombre de pièces, le prix, la distance par rapport à A et la distance par rapport à B.
Map permet de créer la map ainsi que toutes les intéractions entre celle ci et l'utilisateur.

Pour tester notre home finder il faut lancer la classe Main du package homeFinder. Cela créé 10 maison aux caractéristiques aléatoires et les disposent sur la map, ainsi que notre 4 range sliders. Il est possible de faire varier les range Slider de nombre de pieces et de prix, cela fera disparaitre les points correspondant sur la carte.
(Bonus) Enfin il est possible de cliquer 2 fois n'importe où sur la map, ce qui créera deux points A et B. Puis il est possible de faire varier la distance autour de ces deux points grâce aux deux derniers sliders. Seul les maisons présente dans l'intervalle min-max des deux range sliders seront présentes, toutes les autres vont disparaitre. Pour mieux visualiser cela nous avons rajouté des cercles bleus représentant la distance max et distance min des points A et B.
