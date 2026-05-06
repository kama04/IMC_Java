# IMC Java

IMC Java is an Android application developed in Java for calculating and tracking BMI, called **IMC** in French: *Indice de Masse Corporelle*.

The app allows users to enter personal information, calculate their BMI, view the result category, save calculation history, and manage sport activities using a local SQLite database.

---

## Features

- BMI / IMC calculation
- User input validation
- Result category based on age and gender
- Local history saved with SQLite
- List of previous BMI results
- Sport activity tracking
- Search for saved sport activities
- Simple Android UI with multiple screens

---

## Technologies Used

- Java
- Android Studio
- SQLite
- Material Design Components
- Gradle Kotlin DSL

---

## Project Structure

```text
IMC_Java/
├── app/
│   ├── src/main/java/com/example/imc/
│   │   ├── MainActivity.java
│   │   ├── Accueil.java
│   │   ├── CalculActivity.java
│   │   ├── ResultActivity.java
│   │   ├── ResultsActivityList.java
│   │   ├── SportActivity.java
│   │   ├── Form.java
│   │   ├── ImcUtils.java
│   │   ├── MaSQLiteDataBase.java
│   │   └── SQLiteSportDataBase.java
│   └── src/main/res/
├── build.gradle.kts
└── settings.gradle.kts
```

---

## Main Screens

### Home Screen
Navigation to the main app features.

### BMI Calculation Screen
Users can enter:
- Name
- Age
- Height
- Weight
- Gender

### Result Screen
Displays:
- Calculated BMI
- BMI category

### History Screen
Shows saved BMI calculations stored locally.

### Sport Screen
Displays and searches sport activities.

### Form Screen
Allows adding a new sport activity.

---

## How BMI Is Calculated

The BMI formula is:

```text
BMI = weight / height²
```

Where:

- weight is in kilograms
- height is in meters

The app calculates BMI and assigns a category such as:

- Insuffisance pondérale
- Poids normal
- Surpoids
- Obésité
- Obésité importante

---

## Installation

### Clone the repository

```bash
git clone https://github.com/kama04/IMC_Java.git
```

### Open the project

Open the project in Android Studio.

### Sync Gradle

Let Gradle synchronize the dependencies.

### Run the application

Run the app on:
- an Android emulator
- or a physical Android device

---

## Requirements

- Android Studio
- Android SDK
- Minimum SDK: 24
- Java 11

---

## Database

The application uses SQLite databases.

### BMI Results Database

**Database:** `ResultsIMC.db`

**Table:** `T_results`

Stored data:
- Date
- First name
- Height
- Weight
- Age
- Gender
- BMI value
- BMI category

---

### Sport Activities Database

**Database:** `Sports.db`

**Table:** `T_sports`

Stored data:
- Date
- Sport name
- Duration
- Comment

---

## Author

Created by [kama04](https://github.com/kama04)

---

## License

This project is for educational purposes.
