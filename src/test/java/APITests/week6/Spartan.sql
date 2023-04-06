select * from SPARTANS;

select name from spartans where gender='Female';

select * from spartans where gender='Female' and spartan_id between 101 and 111;

select spartan_id,name,gender,phone
from spartans
where spartan_id = 15;