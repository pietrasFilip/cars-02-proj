insert into cars_bodies values (1, 'BLACK', 'HATCHBACK');

insert into components values (1, 'ABS'),
                              (2, 'AIR CONDITIONING');

insert into cars_bodies_with_components values (1, 1),
                                               (1, 2);

insert into engines values (1, 'DIESEL', 210.0);

insert into wheels values (1, 'SUMMER', 'PIRELLI', 18),
                          (2, 'SUMMER', 'PIRELLI', 19);

insert into cars values (1, 'AUDI', 120, 12000, 1, 1, 1),
                        (2, 'BMW', 150, 10000, 1, 1, 2);