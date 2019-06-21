---


---

<h1 id="progetto-programmazione-ad-oggetti">Progetto programmazione ad oggetti</h1>
<p>Progetto per il corso di programmazione ad oggetti del secondo anno di ingegneria informatica e dell’automazione (Università politecnica delle Marche).</p>
<h2 id="descrizione">Descrizione</h2>
<p>L’ applicazione sviluppata consente la gestione di dati formato Json a partire da un file csv. Lapplicazione si occupa di parsare i dati dal file di partenza e, tramite opportune Api rest Get/Post, restituisce in formato Json statistiche ottenute dai dati di partenza o comunque una visualizzazione di dati di interesse.<br>
L’applicazione è sviluppata in java con l’utilizzo del framework Spring.</p>
<h2 id="composizione">Composizione</h2>
<p>L’ applicazione segue il pattern MVC caratteristico del framework utilizzato (Spring), di cui abbiamo implementato il Model ed il Controller. E’ presente anche un package Utils dove costruiamo le classi esterne al pattern.</p>
<ul>
<li><a href="./src/main/java/com/dagomiliano/progettoesame/model"><strong>Model</strong></a></li>
<li><a href="./src/main/java/com/dagomiliano/progettoesame/controller"><strong>Controller</strong></a></li>
<li><a href="./src/main/java/com/dagomiliano/progettoesame/utils"><strong>Utils</strong></a></li>
</ul>
<h2 id="diagrammi-uml">Diagrammi UML</h2>
<p><a href="./resources/uml/img/useCase.jpg"><img src="./resources/uml/img/useCase.jpg"> <strong>Use case diagram</strong></a><br>
<a href="./resources/uml/img/class.jpg"><img src="./resources/uml/img/class.jpg"> <strong>Class diagram</strong><br>
</a><a href="./resources/uml/img/sequence.jpg"><img src="./resources/uml/img/sequence.jpg"><strong>Sequence diagram</strong></a></p>

