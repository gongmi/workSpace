#insert into product values (null, "¿ïÍþ°åÐ¬","²»ºÃ¿´",190, 180, now(),6);

select * from product;
#insert into user values (null, "aa", "bb", "cc", "ee", now());
#select * from salesorder;
#select count(*) from user where username='gongmi'




#select p.id productid, p.name pname, p.descr pdescr, 
#p.normalprice,p.memberprice, p.pdate, p.categoryid ,
#c.id categoryid, c.name cname, c.descr cdescr,
# c.pid, c.cno, c.grade
#   from product p join category c on (p.categoryid = c.id)
#    where p.name like '%Ð¬'