# SWEN221 Tetris

A simple Java implementation of the classic **Tetris** game, developed as part of the **SWEN221: Software Design and Development** course at Victoria University of Wellington.

---

## 📂 Project Structure
- `swen221.tetris.gui` → Handles the graphical interface.
- `swen221.tetris.logic` → Contains the game logic (board, rules, etc).
- `swen221.tetris.tetromino` → Defines the Tetromino shapes (I, J, L, O, S, T, Z).
- `swen221.tetris.tests` → Unit tests (JUnit 5).

---

## ⚙️ Requirements
- **Java 21** (or any Java ≥ 17 should work)
- **JUnit 5** (for running tests)

---

## 🛠️ Build & Run
Compile the project with `javac`, then run it with `java`.

```bash
# Compile all Java files into bin/
javac -d bin -cp src $(find src -name "*.java")

# Run the main class (adjust if your entry point differs)
java -cp bin swen221.tetris.gui.Main

