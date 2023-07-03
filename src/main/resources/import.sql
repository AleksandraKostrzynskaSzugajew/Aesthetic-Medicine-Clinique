insert into category(name) values ("twarz");
insert into category(name) values ("szyja");
insert into category(name) values ("dekolt");
insert into category(name) values ("depilacja laserowa");

insert into specialty(name) values ("chirurg onkolog");
insert into specialty(name) values ("chirurg plastyk");
insert into specialty(name) values ("kosmetyczka");
insert into specialty(name) values ("podolog");
insert into specialty(name) values ("kosmetolog");
insert into specialty(name) values ("fizjoterapeuta");

insert into treatment (name, duration, cost) values ("mikrodermabrazja", 120, 300.0);
insert into treatment (name, duration, cost) values ("lifting", 120, 300.0);
insert into treatment (name, duration, cost) values ("oczyszczanie", 120, 300.0);

# insert into employees( city, first_name, house_number, last_name, phone_number1, phone_number2, postcode, street
# ) values ("warszawa", "Adam", "20/22", "Nowak", "123456", "1234567", "04-432", "Zolta");
#
# insert into employees( city, first_name, house_number, last_name, phone_number1, phone_number2, postcode, street
# ) values ("lodz", "Wieslaw", "2/22", "Dzik", "123456", "1234567", "04-472", "Zielona");
#
# insert into employees( city, first_name, house_number, last_name, phone_number1, phone_number2, postcode, street
# ) values ("Gansk", "Alina", "202", "Malec", "123456", "1234567", "55-432", "Rozowa");

insert into employees(city, first_name, last_name, name) VALUE ("warsaw", "Adam", "kot","adam");
insert into employees(city, first_name, last_name, name) VALUE ("warsaw", "Ola", "lalal","Ola");

# insert into schedule (date, end_time, start_time, employee_id
# ) values ("2023-07-21", "17:00", "10:00", 1);

insert into role(name) values("admin");
insert into role(name) values("user");
insert into role(name) values("employee");


insert into user (email, password) values("a@wp.pl", "$2a$10$aJ5N4DqDiO.OXDvEBPQDZu.PQKQfyMfQx.G1FikKOHHO/uOsuPpHy");