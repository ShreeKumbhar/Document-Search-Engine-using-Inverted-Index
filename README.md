# 🔍 Mini Google — Document Search Engine

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Data%20Structure-Inverted%20Index-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Lookup%20Complexity-O(1)%20Average-brightgreen?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Language-100%25%20Java-orange?style=for-the-badge&logo=java" />
</p>

<p align="center">
  A high-performance, console-based document search engine built in pure Java — implementing the same core indexing logic used by large-scale search engines like Google.
</p>

---

## 📌 What This Project Does

Given a directory of text files, this engine:

1. **Crawls** all `.txt` files recursively across nested folders
2. **Tokenizes** raw text — normalizing case, stripping punctuation, and handling whitespace
3. **Indexes** every word using an **Inverted Index** data structure
4. **Ranks** search results by term frequency — most relevant files appear first
5. **Serves queries** in **O(1) average time** via HashMap-based lookups

> Think of it as a local, offline version of a search engine that can index and search your own documents instantly.

---

## ⚙️ Core Data Structure — Inverted Index

The heart of this project is the Inverted Index, implemented as a nested HashMap:

```
HashMap<String, HashMap<String, Integer>>
       │                │         │
    keyword          filename   frequency
```

**Example:**

```
"gravity" → {
    "formation.txt" → 11,
    "intro.txt"     → 11,
    "culture.txt"   → 7,
    "physics.txt"   → 4,
    "eht.txt"       → 1
}
```

This mirrors how real-world search engines build their posting lists — mapping terms to the documents that contain them, along with relevance signals (here, term frequency).

---

## 🏗️ Architecture

The project follows a clean, single-responsibility design. Each class has one job:

```
┌────────────────────────────────────────────────────────┐
│                        Main.java                       │
│          Entry point · User I/O · Orchestration        │
└────────────────────────┬───────────────────────────────┘
                         │
           ┌─────────────▼──────────────┐
           │       SearchEngine.java     │
           │   Ties all modules together │
           └──┬───────────┬─────────────┘
              │           │
   ┌──────────▼──┐   ┌────▼──────────────┐
   │ FileLoader  │   │  InvertedIndex    │
   │  .java      │   │  .java            │
   │ Recursive   │   │ HashMap<String,   │
   │ dir scan +  │   │  HashMap<String,  │
   │ file reader │   │   Integer>>       │
   └──────────┬──┘   └────▲──────────────┘
              │           │
         ┌────▼───────────┴──┐
         │   Tokenizer.java  │
         │ Lowercase · strip │
         │ punctuation ·     │
         │ split tokens      │
         └───────────────────┘
```

| Class | Responsibility |
|---|---|
| `Main.java` | Entry point, manages user interaction loop |
| `FileLoader.java` | Recursively walks directories, reads `.txt` file content |
| `Tokenizer.java` | Normalizes text: lowercase, removes special characters, splits tokens |
| `InvertedIndex.java` | Builds and stores the `word → {file → frequency}` mapping |
| `SearchEngine.java` | Accepts a query, fetches from the index, sorts and formats results |

---

## 🚀 Performance

| Metric | Value |
|---|---|
| **Indexing Speed** | ~5 files indexed in 20 ms |
| **Vocabulary (sample data)** | 1,330 unique words |
| **Lookup Time Complexity** | O(1) average (HashMap) |
| **Indexing Time Complexity** | O(N × M) where N = files, M = avg tokens per file |
| **Space Complexity** | O(V × D) where V = vocabulary size, D = number of documents |

---

## 📂 Project Structure

```
Document-Search-Engine-using-Inverted-Index/
│
├── src/
│   └── com/
│       └── minigoogle/
│           ├── Main.java            # Application entry point
│           ├── FileLoader.java      # Recursive file crawling & reading
│           ├── Tokenizer.java       # Text normalization & tokenization
│           ├── InvertedIndex.java   # Core inverted index data structure
│           └── SearchEngine.java    # Query handling & ranked result output
│
├── sample_data/
│   └── black_hole_data/             # Sample .txt dataset for testing
│       ├── intro.txt
│       ├── formation.txt
│       ├── physics.txt
│       ├── culture.txt
│       └── eht.txt
│
├── out/                             # Compiled .class files
└── README.md
```

---

## 🖥️ Getting Started

### Prerequisites

- Java Development Kit (JDK) **17 or higher**
- Any terminal / command-line interface

### 1. Clone the Repository

```bash
git clone https://github.com/ShreeKumbhar/Document-Search-Engine-using-Inverted-Index.git
cd Document-Search-Engine-using-Inverted-Index
```

### 2. Compile

```bash
javac -d out src/com/minigoogle/*.java
```

### 3. Run

```bash
java -cp out com.minigoogle.Main
```

### 4. Use It

```
=========================================
   Mini Google - File Based Search Engine
=========================================

Enter directory path to index: /your/path/to/sample_data/black_hole_data

[INFO] Starting Indexing Process...
[INFO] Indexing Complete. Indexed 5 files in 20 ms.
[INFO] Vocabulary Size: 1330 words.

Search (type ':q' to exit): gravity

Found 5 result(s):
 - File: formation.txt | Frequency: 11
 - File: intro.txt     | Frequency: 11
 - File: culture.txt   | Frequency: 7
 - File: physics.txt   | Frequency: 4
 - File: eht.txt       | Frequency: 1
```

> 💡 **Quick Test:** Use the included `sample_data/black_hole_data` directory — just provide its absolute path when prompted.

---

## 🧠 Key Concepts Demonstrated

| Concept | Application |
|---|---|
| **Inverted Index** | Core data structure — maps terms to documents with frequencies |
| **HashMap & Nested Maps** | O(1) average lookup time for keyword queries |
| **Recursive File I/O** | Walking nested directory trees to find all `.txt` files |
| **String Processing** | Regex-based tokenization, normalization pipeline |
| **Frequency-Based Ranking** | Results sorted by term frequency as a relevance signal |
| **Modular OOP Design** | Clean separation of concerns across 5 single-responsibility classes |
| **Time & Space Complexity Analysis** | Documented and justified for indexing and query phases |

---

## 🔮 Planned Improvements

- [ ] **Phrase Search** — Query for exact phrases like `"black hole"`
- [ ] **Boolean Operators** — Support `AND`, `OR`, `NOT` in queries
- [ ] **Index Persistence** — Save index to disk (JSON/binary) and reload without re-indexing
- [ ] **Multi-format Support** — Extend to PDF, DOCX, and CSV files
- [ ] **TF-IDF Ranking** — Replace raw frequency with TF-IDF for smarter relevance
- [ ] **Web Interface** — Expose search via a lightweight REST API or browser UI

---

## 🛠️ Tech Stack

- **Language:** Java 17
- **Data Structures:** `HashMap`, `ArrayList`, `List`
- **I/O:** `java.nio.file` (NIO) for recursive file traversal
- **Build:** Manual `javac` compilation (no external build tools required)

---

## 👤 Author

**Shree Kumbhar**
- GitHub: [@ShreeKumbhar](https://github.com/ShreeKumbhar)

---

## 📄 License

This project is open source and available for educational and personal use.
