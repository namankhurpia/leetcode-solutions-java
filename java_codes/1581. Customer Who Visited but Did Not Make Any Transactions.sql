# Write your MySQL query statement below

select Visits.customer_id , COUNT(*) AS count_no_trans from Visits LEFT JOIN Transactions ON Visits.visit_id = Transactions.visit_id where Transactions.visit_id IS NULL GROUP BY Visits.customer_id;