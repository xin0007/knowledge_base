[[DB_TAG]] #db 

where 和 join on顺序：

先 where 再进行join on的条件

-----
逻辑上一个query的执行顺序（不是实际）：
1. FROM 
2. ON 
3. JOIN 
4. WHERE 
5. GROUP BY 
6. WITH CUBE or WITH ROLLUP 
7. HAVING 
8. SELECT 
9. DISTINCT 
10. ORDER BY 
11. TOP 