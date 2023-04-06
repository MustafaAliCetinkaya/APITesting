select * from SPARTANS;

select name from spartans where gender='Female';

select * from spartans where gender='Female' and spartan_id between 101 and 111;

select * from spartans where UPDATED_AT is not null;

select spartan_id,name,gender,phone
from spartans
where spartan_id = 15;

select * from SPARTANS where name like 'Ma%';

select * from SPARTANS where name like '%ee';

select * from SPARTANS where name like '%eli%';
