```python
create table FS_ASSIGN_SOCIAL
(
    EMP_NO          VARCHAR2(7)         not null,
    SNO             NUMBER(8)           not null
        constraint FK_FS_ASSIGN_SOCIAL
            references FS_ASSIGN
                on delete cascade,
)
```

on delete cascade - 级联删除

意味着当主表的数据删除时，对应的子表的数据也会被删除

