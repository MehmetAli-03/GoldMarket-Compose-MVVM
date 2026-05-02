# 🥇 ProGold - Live Market Tracker & Calculator

![Kotlin](https://img.shields.io/badge/Kotlin-B125EA?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=android&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-121E3E?style=for-the-badge)
![MVVM](https://img.shields.io/badge/Architecture-MVVM-success?style=for-the-badge)
![Material 3](https://img.shields.io/badge/Material%20Design%203-3DDC84?style=for-the-badge&logo=android&logoColor=white)

## 📱 About the Project

**ProGold** is a modern, fast, and responsive Android application designed to track real-time gold market prices and provide instant investment calculations. 

Moving away from legacy XML layouts, this project is built entirely from scratch using **Jetpack Compose** to deliver a seamless and dynamic User Interface. Under the hood, it strictly follows the **MVVM (Model-View-ViewModel)** architecture to ensure a clean separation of concerns, making the codebase scalable, maintainable, and highly testable. Data fetching is securely handled via **Retrofit**, providing live market updates directly to the user.

## ✨ Key Features

* **Real-Time Market Data:** Fetches and displays live buy/sell prices of various gold assets via REST API.
* **Instant Investment Calculator:** Users can input custom grams/amounts to instantly calculate their total buying cost and selling profit.
* **Reactive UI:** Leverages Compose's declarative state management (`State Hoisting`, `Recomposition`) for zero-lag calculations.
* **Premium UX/UI:** Features Edge-to-Edge display, custom color palettes, smart input validations (Regex), and instant clear actions.
* **Clean Architecture:** Strict separation between Network, Logic, and UI layers.

## 🛠️ Tech Stack & Architecture

* **Language:** Kotlin
* **UI Toolkit:** Jetpack Compose
* **Architecture Pattern:** MVVM (Model-View-ViewModel)
* **Networking:** Retrofit2 & Gson Converter
* **State Management:** Compose State, ViewModel
* **Design System:** Material Design 3


## 🧠 What I Learned

Building this project was a deep dive into modern Android development. It significantly improved my understanding of:
1. Shifting from imperative UI (XML) to declarative UI (Jetpack Compose).
2. Managing complex states across different composables without prop-drilling.
3. Handling network requests asynchronously using Retrofit and mapping JSON responses to Kotlin data classes.
4. Structuring a project efficiently using the MVVM pattern for better code readability.

## 🚀 How to Run

1. Clone this repository:
   ```bash
   git clone [https://github.com/MehmetAli-03/ProGold-Tracker.git](https://github.com/MehmetAli-03/ProGold-Tracker.git)
