

Input: 
Employee table:
+-----+-------+------------+-----------+
| id  | name  | department | managerId |
+-----+-------+------------+-----------+
| 101 | John  | A          | None      |
| 102 | Dan   | A          | 101       |
| 103 | James | A          | 101       |
| 104 | Amy   | A          | 101       |
| 105 | Anne  | A          | 101       |
| 106 | Ron   | B          | 101       |
+-----+-------+------------+-----------+
Output: 
+------+
| name |
+------+
| John |
+------+

Write a solution to find managers with at least five direct reports.

Return the result table in any order.

The result format is in the following example.



select name from Employee as t1 JOIN(select managerId from Employee group by managerId HAVING COUNT(ManagerId)>=5) as t2 on t1.id = t2.managerId 
