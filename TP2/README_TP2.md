# TP2_IHM2_Polytech Gregory trestour Antoine rivoire

## TP2 IHM Avancée - INFO5 Polytech - 2019-2020

**Marking menu**

Pour ce TP nous avons réalisé un marking menu basé sur un modele MVC (package markingMenu).  
Pour utiliser ce marking menu il est necessaire de faire un clic droit, cela ouvre le menu. Ensuite il suffit de déplacer la souris vers l'outil ou la couleur désirée. Une fois qu'un outil ou une couleur est sélectionnée le menu disparait automatiquement. Nous avons suivi l'énoncé et décidé de faire une menu semblable au menu Maya avec un choix parmis 8 différentes possibilitées. Il y a donc 7 outils et 7 couleurs ainsi qu'un chois "annuler" à chaque étape.  
Ce menu est utilisé dans la classe Paint du package paint.


**Paint**

Ici nous utilisons notre marking menu pour sélectionner les différents éléments.  
Nous avons mis un outils de base : "pen" et une couleur de base "black". De plus l'utilisateur à la possiblité de choisir un mode expert en cliquant sur le bouton associé en haut de l'écran. Ce mode désactive l'affichage et ne laisse apparaitre que le trait représentant le mouvement de l'utilisateur. Ce mode est exclusivement réservé aux experts qui connaissent parfaitement la disposition des outils et couleurs. Il peut être enlevé à tout moment en recliquant sur le bouton du haut.  
Si l'utilisateur est dans un bord de la fenêtre et ne peux pas accéder à son outils en bougeant sa souris, il a la possibilité de déplacer son menu en cliquant autre part sur l'écran. Cela aura comme conséquence de mettre le centre du menu à l'endroit du nouveau clic effectué par l'utilisateur.
