# Noteally - Aplikacja Java Spring

## Opis projektu

**Noteally** to aplikacja internetowa stworzona w technologii **Java Spring**, której celem jest umożliwienie użytkownikom zapisywania i udostępniania wartościowych informacji. Mogą to być notatki, linki do stron internetowych, listy zakupów, zaproszenia na spotkania, a także inne przydatne dane. Użytkownik ma możliwość przeglądania oraz wyszukiwania zapisanych informacji w dogodnym momencie, a także udostępniania ich innym użytkownikom.

## Struktura danych

### Dane informacji
- **Tytuł**: od 3 do 20 znaków.
- **Treść**: od 5 do 500 znaków.
- **Link**: opcjonalny.
- **Data dodania**: bieżąca data, w formacie `dd-mm-yyyy`.
- **Kategoria**: przypisana do informacji.

### Dane kategorii
- **Nazwa kategorii**: od 3 do 20 znaków, wyłącznie małe litery.

### Dane użytkownika
- **Imię**: od 3 do 20 znaków, wyłącznie litery, pierwsza litera musi być wielka.
- **Nazwisko**: od 3 do 50 znaków, wyłącznie litery, pierwsza litera musi być wielka.
- **Login**: od 3 do 20 znaków, wyłącznie małe litery.
- **Hasło**: co najmniej 5 znaków.
- **Wiek**: minimalny wiek to 18 lat.

## Role użytkowników

W aplikacji przewidziano cztery rodzaje użytkowników, każdy z odmiennym zakresem uprawnień:

- **Admin**: Zarządza użytkownikami aplikacji, ma pełny dostęp do wszystkich zasobów i danych.
- **Limited User**: Użytkownik z ograniczonymi uprawnieniami. Może przeglądać udostępnione informacje, jednak nie ma możliwości ich tworzenia.
- **Full User**: Użytkownik z pełnymi uprawnieniami. Może tworzyć, edytować i usuwać własne informacje, a także udostępniać je innym użytkownikom.
- **Gość (niezalogowany)**: Ma dostęp tylko do strony głównej oraz strony rejestracji. Nie może przeglądać ani tworzyć żadnych informacji.

## Szczegółowe funkcjonalności dla użytkownika Full User

Użytkownik z pełnymi uprawnieniami posiada dostęp do szerokiego zakresu funkcji w aplikacji:

- **Dodawanie, edycja i usuwanie informacji**: Użytkownik może zarządzać swoimi informacjami, dodając nowe notatki, edytując istniejące oraz usuwając te, które uzna za zbędne.
- **Walidacja formularzy**: Każdy formularz w aplikacji, w tym formularze do dodawania i edycji notatek, jest walidowany, co zapewnia poprawność wprowadzanych danych.
- **Dodawanie kategorii**: Użytkownik może tworzyć nowe kategorie, w których będzie organizował swoje informacje.
- **Wyświetlanie udostępnionych informacji**: Użytkownik ma dostęp do widoku informacji, które zostały mu udostępnione przez innych użytkowników.
- **Udostępnianie informacji**: Użytkownik może udostępniać swoje informacje konkretnemu użytkownikowi lub za pomocą wygenerowanego linku.
- **Sortowanie informacji**: Notatki mogą być sortowane według daty dodania, kategorii oraz alfabetycznie, a kryteria i kierunki sortowania są zapamiętywane w sesji użytkownika.
- **Filtrowanie**: Użytkownik może filtrować informacje na podstawie daty (od aktualnej) oraz kategorii (według popularności).
- **Logowanie**: Aplikacja obsługuje system logowania za pomocą formularza. Dane użytkowników są uwierzytelniane przy użyciu mechanizmu Spring Security.
- **Sesje i ciasteczka**: Informacje o kierunku sortowania oraz inne ustawienia użytkownika są zapisywane w ciasteczkach, a sesja jest zarządzana w oparciu o mechanizm sesji Spring.

## Szczegółowe funkcjonalności dla użytkownika niezalogowanego

Użytkownik niezalogowany ma ograniczone możliwości interakcji z aplikacją. Dostępne funkcje obejmują:

- **Rejestracja**: Możliwość założenia nowego konta poprzez formularz rejestracyjny.
- **Walidacja formularza**: Każdy formularz, w tym rejestracyjny, jest walidowany, aby upewnić się, że dane użytkownika są prawidłowe.
- **Strona powitalna**: Gość ma dostęp do strony głównej aplikacji, która pełni funkcję powitalną.
- **Wyświetlanie informacji z udostępnionego linku**: Niezalogowany użytkownik może wyświetlić informacje za pomocą bezpośredniego linku, o ile takie udostępnienie zostało zrealizowane.

## Szczegółowe funkcjonalności dla admina

Administrator aplikacji posiada dodatkowe możliwości zarządzania użytkownikami oraz ich uprawnieniami:

- **Wyświetlanie listy użytkowników**: Admin ma dostęp do pełnej listy zarejestrowanych użytkowników.
- **Zarządzanie rolami**: Admin może przypisywać i zmieniać role użytkowników (np. nadanie roli "Full User" lub "Limited User").

## Elementy techniczne

Aplikacja **Noteally** została zaprojektowana z wykorzystaniem technologii **Spring Framework**. Główne elementy techniczne to:

- **Kontrolery**: Odpowiadają za obsługę żądań użytkowników oraz przekierowania między widokami.
- **Baza danych**: Aplikacja korzysta z bazy danych MySQL, zawierającej co najmniej dwie tabele z relacjami. Dane są zarządzane w oparciu o relacyjny model danych.
- **Widoki**: Formularze w aplikacji są walidowane i zawierają różne elementy interaktywne, zintegrowane za pomocą Thymeleaf.
- **Sesje i ciasteczka**: Dane użytkowników są przechowywane w sesjach, natomiast preferencje, takie jak kryteria sortowania, są zapisywane w ciasteczkach.
- **REST API**: Aplikacja wykorzystuje usługę REST do uwierzytelniania użytkowników oraz innych operacji na danych.
- **Spring Security**: Bezpieczeństwo aplikacji jest zapewnione przez Spring Security, który obsługuje zarówno autentykację, jak i autoryzację użytkowników.
