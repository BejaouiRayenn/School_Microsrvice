                                            Microservice de gestion scolaire développé avec **Spring Boot** et **Spring Data JPA**
                                                     dans le cadre du module Java Avancée / Architectures Logicielles — ISG Bizerte



## 📋 Table des matières

- À propos du projet
- Fonctionnalités
- Architecture
- Diagramme de classe
- schéma de la base de données
- Technologies utilisées
- Prérequis
- Installation
- Configuration
- API Endpoints
- Tests Postman
- Structure du projet


---

## 📖 À propos du projet

Ce projet est un **microservice REST complet** permettant de gérer un système scolaire.
Il met en pratique les concepts fondamentaux de **Java Avancée** et des **Architectures Logicielles** :

- ✅ Conception d'un microservice avec **Spring Boot**
- ✅ Persistance des données avec **Spring Data JPA**
- ✅ Architecture en couches **(Controller → Service → Repository)**
- ✅ Exposition d'**API REST** avec opérations CRUD complètes
- ✅ Gestion des relations JPA **(OneToOne, OneToMany, ManyToMany)**
- ✅ Gestion des exceptions personnalisées
- ✅ Sérialisation JSON sans boucle infinie

---

## ✨ Fonctionnalités

**Fonctionnalité**                     **Description** 

Gestion des écoles                     Créer et consulter des écoles avec leurs entités associées 
Gestion des départements                Associer des départements à une école
Gestion des étudiants                   Créer des étudiants avec adresse et école 
Gestion des adresses                    Adresse unique par étudiant (One-To-One) 
Gestion des instructeurs                Gérer les instructeurs et leurs cours
Gestion des cours                        Associer des cours à plusieurs instructeurs 

---

## 🏗️ Architecture

Ce projet respecte une **architecture en couches** 

```
┌──────────────────────────────────────────────────┐
│            🌐 REST Controller Layer               │
│         (TestSchoolController.java)               │
│     Reçoit les requêtes HTTP / Renvoie JSON       │
├──────────────────────────────────────────────────┤
│              💼 Service Layer                     │
│   (SchoolService.java / SchoolServiceImpl.java)   │
│         Contient la logique métier                │
├──────────────────────────────────────────────────┤
│            🗄️ Repository Layer                    │
│    (SchoolRepository, StudentRepository, ...)     │
│    Accès aux données via Spring Data JPA          │
├──────────────────────────────────────────────────┤
│           🐘 PostgreSQL Database                  │
│              Base de données                      │
└──────────────────────────────────────────────────┘
```

---

## 📊 Diagramme de classes

```
                    ┌─────────────────┐
                    │     School      │
                    │─────────────────│
                    │ idSchool        │
                    │ name            │
                    │ phone           │
                    └────────┬────────┘
                             │
          ┌──────────────────┼──────────────────┐
          │ 1..*             │ *                 │ *
          ▼                  ▼                   ▼
  ┌──────────────┐  ┌──────────────────┐  ┌──────────────────┐
  │  Department  │  │     Student      │  │   Instructor     │
  │──────────────│  │──────────────────│  │──────────────────│
  │ idDepartment │  │ idStudent        │  │ idInstructor     │
  │ name         │  │ name             │  │ name             │
  └──────────────┘  │ birthDate        │  └────────┬─────────┘
                    └────────┬─────────┘           │ *
                             │ 1                   │
                             ▼                     ▼ *
                    ┌──────────────────┐  ┌──────────────────┐
                    │    Address       │  │     Course       │
                    │──────────────────│  │──────────────────│
                    │ idAddress        │  │ idCourse         │
                    │ street           │  │ name             │
                    │ city             │  └──────────────────┘
                    │ postalCode       │
                    └──────────────────┘
```

### Relations JPA utilisées

| Relation | Entre | Type | Navigabilité |
|----------|-------|------|-------------|
| `@OneToMany` | School → Department | Bidirectionnel | School ↔ Department |
| `@OneToMany` | School → Student | Bidirectionnel | School ↔ Student |
| `@OneToMany` | School → Instructor | Bidirectionnel | School ↔ Instructor |
| `@OneToOne` | Student → Address | Unidirectionnel | Student → Address |
| `@ManyToMany` | Instructor ↔ Course | Bidirectionnel | Instructor ↔ Course |

---

## 🗃️ Schéma de la base de données

```
t_school                    t_department
─────────────────           ──────────────────────
PK_school        ◄──────── school_PK_school (FK)
cl_name_school              pk_department
cl_phone                    cl_name

t_student                   t_address
─────────────────────────   ──────────────────
PK_Student                  PK_ADDRESS
cl_name        ◄─────────── address_PK_ADDRESS (FK)
cl_birthdate                cl_street
school_PK_school (FK)       cl_city
address_PK_ADDRESS (FK)     cl_postal_Code

t_instructor                t_course
─────────────────           ──────────────
pk_Instructor               pk_course
name_Instructor             name_course
school_PK_school (FK)

t_instructor_t_course  (Table de jointure Many-To-Many)
──────────────────────────────────────
t_instructor_pk_Instructor (FK)
courses_pk_course (FK)
```

---

## 🛠️ Technologies utilisées

| Technologie | Version | Rôle |
|-------------|---------|------|
| ☕ **Java** | 17+ | Langage de programmation |
| 🍃 **Spring Boot** | 3.x | Framework principal |
| 🗄️ **Spring Data JPA** | 3.x | Persistance et accès aux données |
| 🌐 **Spring Web** | 3.x | Exposition des API REST |
| 🐘 **PostgreSQL** | 15 | Base de données relationnelle |
| 🔧 **Lombok** | Latest | Réduction du code boilerplate |
| ♻️ **Spring DevTools** | Latest | Rechargement automatique |
| 📦 **Maven** | 3.9 | Gestion des dépendances |

### Dépendances `pom.xml`

```xml
<dependencies>
    <!-- Spring Web : API REST -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA : persistance -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Lombok : réduction boilerplate -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- DevTools : hot reload -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
</dependencies>
```

---

## ✅ Prérequis

Avant de commencer, assure-toi d'avoir installé :

- [x] **Java 17+** → vérifier : `java -version`
- [x] **Maven 3.9+** → vérifier : `mvn -version`
- [x] **PostgreSQL 15+** → vérifier : `psql --version`
- [x] **Git** → vérifier : `git --version`
- [x] **Postman** → pour tester les API REST


## ⚙️ Configuration

Fichier `src/main/resources/application.properties` :

```properties
# Base de données
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/schooldb}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:ton_mot_de_passe}
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Serveur
server.port=8080
```

---

## 🌐 API Endpoints

**Base URL :** `http://localhost:8080/api`

### 🏫 School

| Méthode | Endpoint | Description | Status |
|---------|----------|-------------|--------|
| `POST` | `/schools` | Créer une école avec departments, students et instructors | `201 Created` |
| `GET` | `/schools/{id}` | Retourner une école par son id | `200 OK` |

### 👨‍🎓 Student

| Méthode | Endpoint | Description | Status |
|---------|----------|-------------|--------|
| `POST` | `/schools/{schoolId}/students` | Créer un étudiant avec son adresse | `201 Created` |
| `GET` | `/students` | Lister tous les étudiants | `200 OK` |

### 👨‍🏫 Instructor

| Méthode | Endpoint | Description | Status |
|---------|----------|-------------|--------|
| `POST` | `/schools/{schoolId}/instructors` | Créer un instructeur avec ses cours | `201 Created` |
| `GET` | `/instructors?name={nom}` | Lister les instructeurs par nom | `200 OK` |
| `GET` | `/instructors/{id}` | Retourner un instructeur par id | `200 OK` |
| `GET` | `/instructors/{id}/courses` | Lister les cours d'un instructeur | `200 OK` |
| `POST` | `/instructors/{id}/courses` | Ajouter un cours à un instructeur existant | `201 Created` |

### 📚 Course

| Méthode | Endpoint | Description | Status |
|---------|----------|-------------|--------|
| `GET` | `/courses/{id}` | Retourner un cours par son id | `200 OK` |


---

## 🧪 Tests Postman



### A — Créer une School complète

```json
POST http://localhost:8080/api/schools

{
  "name": "ISG Bizerte",
  "phone": 12345678,
  "departments": [
    {"name": "Informatique"},
    {"name": "Finance"}
  ],
  "student": [
    {
      "name": "Ahmed Ben Ali",
      "birthDate": "2000-01-15",
      "address": {
        "street": "Rue de la Liberté",
        "city": "Bizerte",
        "postalCode": "7000"
      }
    }
  ],
  "instructor": [
    {
      "name": "Prof. Neji",
      "courses": [
        {"name": "Java Avancé"},
        {"name": "Spring Boot"}
      ]
    }
  ]
}
```

**Réponse attendue `201 Created` :**
```json
{
  "idSchool": 1,
  "name": "ISG Bizerte",
  "phone": 12345678,
  "departments": [
    {"idDepartment": 1, "name": "Informatique"},
    {"idDepartment": 2, "name": "Finance"}
  ],
  "student": [{"idStudent": 1, "name": "Ahmed Ben Ali", ...}],
  "instructor": [{"idInstructor": 1, "name": "Prof. Neji", ...}]
}
```

---

### B — Retourner une School par id

```
GET http://localhost:8080/api/schools/1
```

---

### C — Créer un Student

```json
POST http://localhost:8080/api/schools/1/students

{
  "name": "Sarra Mansour",
  "birthDate": "2001-05-20",
  "address": {
    "street": "Avenue Habib Bourguiba",
    "city": "Tunis",
    "postalCode": "1000"
  }
}
```

---

### D — Lister tous les Students

```
GET http://localhost:8080/api/students
```

---

### E — Créer un Instructor avec ses cours

```json
POST http://localhost:8080/api/schools/1/instructors

{
  "name": "Prof. Jabbar",
  "courses": [
    {"name": "Microservices"},
    {"name": "Architecture Logicielle"}
  ]
}
```

---

### F — Lister les Instructors par nom

```
GET http://localhost:8080/api/instructors?name=Prof. Neji
```

---

### G — Retourner un Instructor par id

```
GET http://localhost:8080/api/instructors/1
```

---

### H — Retourner un Course par id

```
GET http://localhost:8080/api/courses/1
```

---

### I — Lister les courses d'un Instructor

```
GET http://localhost:8080/api/instructors/1/courses
```

---

### J — Ajouter un cours à un Instructor existant

```json
POST http://localhost:8080/api/instructors/1/courses

{
  "name": "Docker & Kubernetes"
}
```

---

## 📁 Structure du projet

```
school/
│
├── 📄 pom.xml
├── 📄 README.md
│
└── src/
    └── main/
        ├── java/edu/isgb/school/
        │   │
        │   ├── 📁 entities/
        │   │   ├── School.java          ← Entité principale
        │   │   ├── Department.java      ← OneToMany avec School
        │   │   ├── Student.java         ← ManyToOne School, OneToOne Address
        │   │   ├── Address.java         ← OneToOne avec Student
        │   │   ├── Instructor.java      ← ManyToOne School, ManyToMany Course
        │   │   └── Course.java          ← ManyToMany avec Instructor
        │   │
        │   ├── 📁 repository/
        │   │   ├── SchoolRepository.java
        │   │   ├── StudentRepository.java
        │   │   ├── InstructorRepository.java  ← findByName()
        │   │   ├── CourseRepository.java
        │   │   ├── DepartmentRepository.java
        │   │   └── AddressRepository.java
        │   │
        │   ├── 📁 services/
        │   │   ├── SchoolService.java         ← Interface
        │   │   └── SchoolServiceImpl.java     ← Implémentation
        │   │
        │   ├── 📁 exeptions/
        │   │   └── ResourceNotFoundException.java
        │   │
        │   └── 📁 test/
        │       └── TestSchoolController.java  ← REST Controller
        │
        └── resources/
            └── application.properties
```

---

## 💡 Concepts clés implémentés

### Mappings JPA testés

| Mapping | Cas testé | Schéma BD obtenu |
|---------|-----------|-----------------|
| `@OneToMany` bidirectionnel | School ↔ Department | FK dans `t_department` ✅ |
| `@OneToOne` unidirectionnel | Student → Address | FK dans `t_student` ✅ |
| `@ManyToMany` bidirectionnel | Instructor ↔ Course | Table de jointure `t_instructor_t_course` ✅ |

### Annotations importantes

```java

@JsonIgnore
// Ignore un champ lors de la sérialisation (côté enfant des relations)

@Transactional(readOnly = true)
// Optimise les requêtes en lecture seule

@RequiredArgsConstructor
// Injection par constructeur (Lombok)

                                                **Ce projet est réalisé dans un cadre académique — **ISG Bizerte** — Année universitaire 2025/2026**.



