# TODO/notatki:
- Walidacja/errory
- filtrowanie
- naprawa wylogowania
- zapamietywanie kryteriow sortowania
- confirm pasword
- backend DLA KUBY z filtrowaniem po dacie 

#               Java Spring Projekt -> Noteally

- Aplikacja do zapisywania i udostępniania wartych uwagi informacji, 
np. strony www, listy zakupów, zaproszenia na spotkanie, notatki.
- Dane informacji: tytuł (3-20), treść (5-500), [link], data dodania 
(aktualna, format dd-mm-yyyy), kategoria
- Dane kategorii: nazwa (3-20, małe litery)
- Dane użytkownika: imię (3-20, litery, pierwsza duża), nazwisko 
(3-50, litery, pierwsza duża), login (3-20, małe litery), hasło (co 
najmniej 5 znaków), wiek (min. 18 lat)

#                       OPIS
- Aplikacja do zapisywania i udostępniania wartych uwagi 
informacji, np. strony www, listy zakupów, zaproszenia 
na spotkanie.
- Główną funkcjonalnością jest umożliwienie zapisania na 
swoim koncie ciekawych informacji, udostępniania ich 
innym oraz wygodnego przeglądania i przeszukiwania w 
dogodnym czasie.

#               1. Role Użytkowników
- admin - zarządza użytkownikami,
- uż. z ograniczonymi prawami (limited user) - jest
zarejestrowany, może przegladać udostępnione linki, ale nie
może tworzyć informacji,
- uż. z pełnymi prawami (full user) - może tworzyć informacje i
udostępniać je innym, przeglądać w oddzielnym widoku
informacje udostępnione dla niego
- użytkownik niezalogowany - ma dostęp tylko do strony
początkowej i strony rejestracji

#       2. Szczegółowe funkcjonalności, full user (19p)
- dodanie/edycja/usunięcie przez siebie zebranych informacji - 5p.
- Walidacja formularza - 1p.
- Edycja na danych bieżących - 1p.
- Dodanie nowej kategorii - 1p.
- Wyświetlenie udostępnionych przez innych informacji - 2p.
- udostępnienie : ze wskazaniem na konkretnego użytkownika lub w linku - 1p.
- Wyświetlanie “swoich” informacji: sortowanie w obu kierunkach (data, 
kategoria, alfabetycznie) - 2p.
- Zapamiętanie kierunków i kryteriów sortowania - 1p.
- Filtrowanie według daty (od aktualnej) i kategorii (od najbardziej popularnej) - 2p.
- Logowanie - 1p.
- Zapis do bazy danych dopiero przy wylogowaniu/wygaśnięciu sesji - 2p.

#       3. Szczegółowe Funkcjonalności, Niezalogowany (4p)
- Rejestracja - 1p.
- Walidacja formularza - 1p.
- Strona powitalna - 1p.

#       4. Szczegółowe Funkcjonalności, admin (2p)
- Wyśw. listy użytkowników - 1p.
- Zarządzanie rolami - 1p.

#       5. Elementy Techniczne (25p.)
- Kontrolery - 2p.
- Baza danych (co najmniej 2 tabele z relacją) - 5p.
- Widoki: formularze z walidacją (3 różne elementy), 
    5 różnych znaczników Thymeleafa - 3p.+2p.
- Sesja - 2p.
- Ciasteczka - 1p.
- Usługa REST (do uwierzytelniania użytkowników) - 3p.
- Klient REST - 2p.
- Spring Security - 5p. (z bazą danych), 3p. (w pamięci)

#       6. Dodatkowe Elementy
- Dokumentacja z odniesieniem do wszystkich 
elementów punktowanych
- Wygląd - wystarczy Bootstrap (Mateusz Ogarnie Frontend)
- Prezentacja na forum grupy
- Termin - ostatnie planowe zajęcia
