# Ticket to Ride

Repository containing the Ticket to Ride javaFX application, build with Java RMI

## Installation

Clone the repository
```bash
git clone https://github.com/WesleyKlop/IIPSEN
cd IIPSEN
```

## Developing
Dunno just press the button in IntelliJ

## Testing
We should really write unit tests

## Project layout
```
src
├── main
│   ├── java
│   │   ├── client
│   │   │   └── ui          # JavaFX controllers, views
│   │   ├── game            # Shared logic
│   │   │   ├── cards
│   │   │   ├── player
│   │   │   └── routecards
│   │   ├── server          # Server logic
│   │   └── util            # Utility classes
│   └── resources
│       ├── cards           # Card assets
│       └── views           # FXML views
└── test                    # Basically the same as main but with tests
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