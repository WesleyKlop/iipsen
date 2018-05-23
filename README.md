# Ticket to Ride

Repository containing the Ticket to Ride javaFX application, build with Java RMI

## Installation

Clone the repository
```bash
git clone https://github.com/WesleyKlop/IIPSEN
cd IIPSEN
```

## Building

Build the app with Gradle  
MacOS / Unix
```bash
./gradlew build
```

Windows
```powershell
gradlew.bat build
```

## Running
Run the app  
MacOS / Unix
```bash
./gradlew run
```

Windows
```powershell
gradlew.bat run
```

## Project layout
```
src
├── main
│   ├── java
│   │   ├── client
│   │   │   ├── ui 
│   │   │   └── views
│   │   ├── game # Shared logic
│   │   │   ├── cards
│   │   │   ├── player
│   │   │   └── routecards
│   │   └── server # Server logic
│   └── resources
│       ├── cards # Card assets
│       └── views # FXML views
└── test
    └── java
        ├── client
        │   ├── ui
        │   └── views
        ├── game
        │   ├── cards
        │   ├── player
        │   └── routecards
        └── server
```