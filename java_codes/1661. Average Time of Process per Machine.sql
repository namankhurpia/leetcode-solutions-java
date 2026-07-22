# Write your MySQL query statement below

#method 1
select m1.machine_id, round(avg(m2.timestamp - m1.timestamp),3)  AS 'processing_time' 
from Activity m1, Activity m2  
where m1.activity_type = 'start' and m2.activity_type = 'end' and m1.machine_id = m2.machine_id 
GROUP BY machine_id;

#method 2 
select a1.machine_id , round(avg(a2.timestamp - a1.timestamp),3) AS 'processing_time' 
from Activity a1  JOIN Activity a2 
ON a1.machine_id = a2.machine_id 
and a1.activity_type = 'start' and a2.activity_type = 'end'
GROUP BY machine_id; 

#method 3
select machine_id, 
round( 
  (select avg(timestamp) from Activity a2 where activity_type = 'end' and a2.machine_id = a1.machine_id )-
  (select avg(timestamp) from Activity a2 where activity_type = 'start' and a2.machine_id = a1.machine_id ) ,3) as 'processing_time' 
  from Activity a1 
  GROUP BY machine_id;
  