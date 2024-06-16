
## Utilització de l'aplicació

### Objectiu

L'objectiu és fer una aplicació que obtingui les dades d'una API per a poder mostrar-les per pantalla. S'utilitzarà una pantalla principal anomenada Home on es mostrarà una llista dels fotògrafs obtinguts amb els seus nom i cognoms i foto corresponent. La segona pantalla serà PhotographerDetail i es mostrarà quan seleccionem un element de la llista de fotògrafs. Hi haurà la foto, els nom i cognoms i la descripció del fotògraf.

### Obtenció de dades

Per a l'obtenció de dades he utilitzat Retrofit, llibreria altament utilitzada per a l'ús d'APIs des d'aplicacions Android, que també permet l'autenticació mitjançant BasicAuth podent fer servir les credencials donades a l'enunciat. S'han utilitzat corrutines per tal de bloquejar el mínim possible l'aplicació mentres s'està fent la crida.

### Llista 1
És la pantalla principal de l'aplicació, es mostra una llista amb només els noms i cognoms i la fotografia corresponent a cada fotògraf. S'ha utilitzat una arquitectura Model-Vista-VistaModel per l''obtenció de les dades a través de l'API. Al seleccionar un element de la llista es navega a pantalla que mostra els detalls del fotògraf seleccionat passant els noms i cognoms, foto i descripció.

### Detall
És la pantalla que mostra els detalls del fotògraf seleccionat a la pantalla Home. Les dades arriben mitjançant la navegació Compose. Com que no necessita cap tipus de connexió amb l'API, no ha fet falta implementar una arquitectura MVVM.

### Persistència
S'ha utilitzat Realm per a la persistència de les dades.
#### - Escriptura de les dades
Les dades s'obtenen de l'API i es mostren per pantalla. Un cop estan mostrades es guarden a la base de dades. He escollit aquest ordre ja que així l'usuari veu les dades el més aviat possible i mentrestant es van escrivint les dades a la base de dades.
#### - Lectura de les dades
Les dades es llegeixen de la base de dades quan s'engega l'aplicació. Després s'aniran actualitzant a mida que es facin les crides corresponents a l'API. No fa falta que es llegeixin un altre cop ja que en cas que s'haguessin d'actualitzar les dades es mostrarien directament les obtenides a través de l'API (com he explicat a l'apartat d'escriptura de dades).

## Llibreries externes utilitzades

### - androidx-navigation-compose

Permet la navegació entre objectes composables, en aquest cas per a poder navegar a HomeScreen i des d'allà poder passar totes les dades necessàries a PhotographerDetailScreen.

### - kotlinx-coroutines-android & kotlinx-coroutines-core

Permet l'ús de les corrutines de Kotlin i s'ha fet servir tant per l'escriptura de dades a la base de dades local (Realm) com per obtenir les dades de la API a través de Retrofit.

### - kotlinx-serialization-json

Serialització de l'objecte HomeScreen per a ser utilitzat per la navegació entre components composables.
Serialització de la data class PhotographerDetailScreen per a poder passar les dades necessàries des de HomeScreen.

### - retrofit

Permet la crida d'endpoints, en aquest cas, des de l'API proporcionada a l'enunciat. Permet l'autenticació amb BasicAuth (usuari i contrassenya).

## Plugins externs utilitzats

### - realm-kotlin (juntament amb la llibreria library-sync)

Creació d'una base de dades local per a la persistència de les dades obtingudes a través de l'API proporcionada. Es guarden les dades quan s'obtenen de l'API i es recuperen cada cop que es navega a HomeScreen.