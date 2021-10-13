      
```sql
-- 查询所有字段

select COLUMN_NAME

from all_tab_columns

where table_name='FS_ENTRANT_INFO'

AND OWNER = 'SFSC'

;

  

  

-- 查询主键

select a.column_name 

from all_cons_columns a, all_constraints b 

where a.constraint_name = b.constraint_name 

 and b.constraint_type = 'P' 

 and a.table_name = 'DC_CHILD'

 AND a.OWNER = b.OWNER 

 AND a.OWNER = 'DATACENTER'

 ;

-- 查看索引

select *--t.COLUMN_NAME

from all_ind_columns t,all_indexes i 

where t.index_name = i.index_name 

and t.table_name = i.table_name 

and t.table_name = 'FS_FINC_OUT_CARD_DTL_DEL'

AND t.TABLE_OWNER = i.TABLE_OWNER 

AND t.TABLE_OWNER = 'SFSC'

AND i.UNIQUENESS = 'UNIQUE'

;

  

-- 查看表格的注释

SELECT COMMENTS 

FROM all_tab_comments

WHERE OWNER = 'SFSC'

AND TABLE_NAME = 'WF_CHILD'

;

  

-- 查看字段的注释

SELECT *

FROM ALL_COL_COMMENTS 

WHERE OWNER = 'SFSC'

AND TABLE_NAME = 'WF_CHILD'

```