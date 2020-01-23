delete from reservation;
delete from room;
delete from hotel;
delete from customer;

INSERT INTO customer (id, first_name, last_name) VALUES (-1, 'FirstName', 'LastName');

INSERT INTO hotel (id, city, hotel_name) VALUES (-1, 'Warsaw', 'BestHotel');

INSERT INTO hotel (id, city, hotel_name) VALUES (-2, 'Oslo', 'MarinaHotel');

INSERT INTO room (id, hotel_id, availability, room_number, room_type, daily_price)
 VALUES (-1, -1, true, 123, 'ONE', 150),
  (-2, -1, true, 124, 'DOUBLE', 250),
  (-3, -1, true, 125, 'QUEEN', 400),
  (-4, -2, true, 1235, 'ONE', 230),
  (-5, -2, true, 1236, 'DOUBLE', 280),
  (-6, -2, true, 1237, 'QUEEN', 530);

INSERT INTO reservation  (id, room_id, start_time, end_time, creation_time, customer_id, cancelled)
VALUES (-1, -1, '2020-03-12', '2020-03-18', now(), -1, false),
 (-2, -1, '2020-04-1', '2020-04-18', now(), -1, false ),
 (-3, -2, '2020-04-1', '2020-04-18', now(), -1, false ),
 (-4, -3, '2020-04-1', '2020-04-18', now(), -1, false ),
 (-5, -5, '2020-04-1', '2020-04-18', now(), -1, false );