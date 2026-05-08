## Task Manager

A modular JavaFX desktop application for managing daily tasks with clean architecture and persistent storage.

## Features

- Add, edit, and delete tasks
- Mark tasks complete or mark all complete
- Category filtering (Work, School, Personal, All Tasks)
- JSON‑based persistent storage
- Modal popups for task creation and editing
- Modular, maintainable UI components

## Architecture Overview
Component‑based, MVC‑inspired structure:

### UI
- MainView — root layout and coordinator
- SidebarView — categories + action buttons
- TaskListView — displays tasks
- AddTaskPopup / EditTaskPopup — modal dialogs

### Logic
- TaskManager — task operations
- TaskStorage — JSON persistence
- Task — data model

This separation keeps logic, UI, and storage clean and scalable.

## Project Structure
```text
src/main/java/org/example/
|-- Main.java
|-- MainView.java
|-- SidebarView.java
|-- TaskListView.java
|-- AddTaskPopup.java
|-- EditTaskPopup.java
|-- Task.java
|-- TaskManager.java
`-- TaskStorage.java
```

## Tech Stack
- Java 17
- JavaFX
- Maven
- Gson

## Running the App
- Clone the repository
- Open in IntelliJ
- Build with Maven
- Run Main.java

## Roadmap
- Priorities & due dates
- Search & sorting
- CSS styling
- Dark mode
- Reminders
