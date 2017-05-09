# IoT Hackathon - Vorgehen
1) [Zusammenbauen der Bricks/Bricklets und der Waage](https://www.tinkerforge.com/en/doc/Hardware/Bricklets/Load_Cell.html#scale-kit)

2) Installieren des [Brick Daemons](https://www.tinkerforge.com/en/doc/Software/Brickd.html#brickd) und [Brick Viewer](https://www.tinkerforge.com/en/doc/Software/Brickv.html#brickv)

3) Anschließen über USB

![Brick Viewer - Load Cell](http://i.imgur.com/Keai4kA.png)

4) Einwählen in WLAN

![Brick Viewer - WLAN](http://i.imgur.com/hoSjjj5.png)

5) [Erstellen eines neuen Maven-Projekts](https://www.tinkerforge.com/en/doc/Software/API_Bindings_Java.html)

6) Testen des Load Cell Bricklet [Beispiels](https://www.tinkerforge.com/en/doc/Software/Bricklets/LoadCell_Bricklet_Java.html#load-cell-bricklet-java-examples) über USB/WLAN

7) Implementieren eines WeightListeners/WeightReachedListeners um den Zustand der Waage auszulesen und eine Nachricht zu senden, falls eine leere Kanne für ein bestimmtes Zeitintervall auf der Waage steht

8) RESTful Service mit Jetty Server

Beispiel: ```[{"uid":"B12","refill":false,"level":"NO_CAN","weight":0}]```

9) Erstellen eines neuen Angular 2 Projekts über angular/cli

10) Auslesen und Darstellen als einfache Liste

![Browser Screenshot](http://i.imgur.com/RYPUKVc.png)
 
11) Fixieren auf der Holzplatten
