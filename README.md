# Mini Google - File-Based Search Engine

A high-performance, console-based search engine implemented in pure Java (JDK 17). This project mimics the core indexing logic of major search engines (like Google) using an **Inverted Index** data structure to provide O(1) average time complexity for keyword lookups.

## 🚀 Features

- **Inverted Indexing**: Maps keywords to file locations for instant search results.
- **Recursive File Crawling**: Automatically detects and indexes all `.txt` files in a nested directory structure.
- **Smart Tokenization**:
  - Converts text to lowercase.
  - Removes punctuation and special characters.
  - Handles various whitespace scenarios.
- **Ranked Results**: Displays search results with frequency counts, allowing users to see the most relevant files first.
- **Interactive Console UI**: Simple and efficient command-line interface.

## 🛠️ Architecture

The project follows a clean, modular design pattern:

- **`FileLoader`**: Recursively scans directories and reads file content.
- **`Tokenizer`**: Normalizes raw text into searchable tokens.
- **`InvertedIndex`**: The core data structure (`HashMap<String, HashMap<String, Integer>>`) that stores mappings of `Word -> { FileName -> Frequency }`.
- **`SearchEngine`**: Orchestrates the search logic and formats results.
- **`Main`**: The entry point ensuring a smooth user experience.

## 📋 Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher.
- A terminal or command-line interface.

## 🔧 Installation & Usage

### 1. Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/mini-google.git
cd mini-google
```

### 2. Compile the Code
Navigate to the project root directory and compile the Java source files:
```bash
javac -d out src/com/minigoogle/*.java
```

### 3. Run the Application
Start the search engine:
```bash
java -cp out com.minigoogle.Main
```

### 4. Use the Search Engine
1. When prompted, enter the **absolute path** to the directory you want to index.
   - Example: `/Users/username/documents/my_text_files`
   - *Tip: You can use the included `sample_data/black_hole_data` for testing.*
2. Once indexing is complete, type any word to search for it.
3. Type `:q` to exit.

## 📂 Project Structure

```
├── src
│   └── com
│       └── minigoogle
│           ├── FileLoader.java      # File reading logic
│           ├── InvertedIndex.java   # Data structure handling
│           ├── Main.java            # Entry point
│           ├── SearchEngine.java    # Search logic
│           └── Tokenizer.java       # Text processing
├── sample_data/                     # Test files
│   └── black_hole_data/             # Larger dataset for performance testing
└── README.md
```

## 🧪 Example Execution

```text
=========================================
   Mini Google - File Based Search Engine
=========================================

Enter directory path to index: /Users/sakshi/project/sample_data/black_hole_data

[INFO] Starting Indexing Process...
[INFO] Indexing Complete. Indexed 5 files in 20 ms.
[INFO] Vocabulary Size: 1330 words.

Search (type ':q' to exit): gravity
Found 5 result(s):
 - File: formation.txt | Frequency: 11
 - File: intro.txt | Frequency: 11
 - File: culture.txt | Frequency: 7
 - File: physics.txt | Frequency: 4
 - File: eht.txt | Frequency: 1
```

## 🔮 Future Improvements

- [ ] Support for Phrase Search ("black hole").
- [ ] Support for other file formats (PDF, DOCX).
- [ ] Persistence (Save/Load index to disk).
- [ ] GUI / Web Interface.
