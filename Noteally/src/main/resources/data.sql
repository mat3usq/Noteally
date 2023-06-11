delete from notes;
delete from catalogs;
delete from users;

insert into users(age, email, login, name, password, role, surname) values
(18, 'testMail@mail.com', 'testLogin', 'testName', 'testPassword', 'LOGGED', 'testSurname');

insert into catalogs(name, user_id) values
('Ogólne', '1');

insert into catalogs(name, user_id) values
('testCatalog', '1');

insert into notes(content, date, link, title, catalog_id) values
('test note 1', CURRENT_DATE, 'testLink1', 'testNote1', '1');

insert into notes(content, date, link, title, catalog_id) values
('test note 2', CURRENT_DATE, 'testLink2', 'testNote2', '1');

insert into notes(content, date, link, title, catalog_id) values
('test note 3', CURRENT_DATE, 'testLink3', 'testNote3', '2');

insert into notes(content, date, link, title, catalog_id) values
('test note 4', CURRENT_DATE, 'testLink4', 'testNote4', '2');

