```sql
SELECT c.CustomerId,CompanyName FROM Customers c  
WHERE EXISTS(  
SELECT OrderID FROM Orders o WHERE o.CustomerID=c.CustomerID)
```

这里的exists里面，只会返回Boolean值，所以不用在乎子查询里面select什么东西，也可以select 1，无所谓。

**查询里查询与其对应的结果 如果存在，返回ture则输出,反之返回false则不输出,再根据主查询中的每一行去子查询里去查询.**